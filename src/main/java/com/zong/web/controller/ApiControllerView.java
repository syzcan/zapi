package com.zong.web.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zong.util.Config;
import com.zong.util.Freemarker;
import com.zong.util.PageData;
import com.zong.web.base.controller.BaseController;
import com.zong.web.bean.Api;
import com.zong.web.bean.Folder;
import com.zong.web.bean.Parameter;
import com.zong.web.bean.Project;
import com.zong.web.dbclient.bean.ColumnField;
import com.zong.web.dbclient.bean.Table;
import com.zong.web.dbclient.service.JdbcCodeService;
import com.zong.web.service.ApiService;
import com.zong.web.service.FolderService;
import com.zong.web.service.ProjectService;

/**
 * @desc 控制层ApiController
 * @author zong
 * @date 2017年02月13日
 */
@Controller
@RequestMapping(value = "/api")
public class ApiControllerView extends BaseController {
	private final static Logger logger = Logger.getLogger(ApiControllerView.class);

	@Autowired
	private ApiService apiService;
	@Autowired
	private FolderService folderService;
	@Autowired
	private ProjectService projectService;

	/**
	 * api首页
	 */
	@RequestMapping(value = "/index")
	public String index(String projectId, Model model) {
		List<Folder> folders = folderService.findFolder(new PageData("projectId", projectId));
		for (Folder folder : folders) {
			folder.setApis(apiService.findApi(new PageData().put("folderId", folder.getId())));
		}
		model.addAttribute("folders", folders);
		return "/info/api/api_index";
	}

	/**
	 * 查询api详情
	 */
	@RequestMapping(value = "/view")
	public String view(Api api, Folder folder, Model model) {
		api = apiService.loadApi(api);
		model.addAttribute("api", api);
		folder.setId(api.getFolderId());
		folder = folderService.loadFolder(folder);
		model.addAttribute("folder", folder);
		
		List<Folder> folders = folderService.findFolder(new PageData("projectId", api.getProjectId()));
		for (Folder f : folders) {
			f.setApis(apiService.findApi(new PageData().put("folderId", f.getId())));
		}
		model.addAttribute("folders", folders);
		return "/info/api/api_view";
	}

	/**
	 * 从json连接导入api
	 */
	@ResponseBody
	@RequestMapping(value = "/import")
	public String importApi(String url, String projectId, Model model) {
		String msg = "success";
		try {
			Response response = Jsoup.connect(url).method(Method.GET).execute();
			apiService.importJson(response.body(), projectId);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			msg = e.toString();
		}
		return msg;
	}

	/**
	 * 导出api文档
	 */
	@ResponseBody
	@RequestMapping(value = "/export/{type}")
	public String export(@PathVariable String type, String projectId, Model model) {
		String msg = "success";
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH.mm");
			String dateString = dateFormat.format(new Date());
			String docPath = "E:/zzzzzz/";
			String ftlPath = getRequest().getServletContext().getRealPath("/static/document");

			List<Folder> folders = folderService.findFolder(new PageData("projectId", projectId));

			for (Folder folder : folders) {
				List<Api> apis = apiService.findApi(new PageData("folderId", folder.getId()));
				List<Api> apiList = new ArrayList<Api>();
				for (Api api : apis) {
					api = apiService.loadApi(api);
					apiList.add(api);
					// 生成细页
					if (type.equals("html")) {
						Map map = new HashMap();
						map.put("api", api);
						map.put("folder", folder);
						Freemarker.printFile("api_view.ftl", ftlPath, map,
								docPath + dateString + "/" + api.getBeanMethod() + "_" + api.getSort() + ".html");
					}
					// 处理特殊字符
					dealParam(api);
				}
				folder.setApis(apiList);
			}
			Map index = new HashMap();
			index.put("folders", folders);
			// 生成首页
			if (type.equals("html")) {
				Freemarker.printFile("api_index.ftl", ftlPath, index, docPath + dateString + "/index.html");
				FileUtils.copyFile(
						new File(getRequest().getServletContext().getRealPath("/static/css/pintuer.css")),
						new File(docPath + dateString + "/pintuer.css"));
			} else if (type.equals("word")) {
				// 生成word，必须处理特殊字符
				Freemarker.printFile("api_word.ftl", ftlPath, index, docPath + "接口文档_" + dateString + ".doc");
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
			msg = e.toString();
		}
		return msg;
	}

	private Api dealParam(Api api) {
		List<Parameter> list = api.getResultParams();
		for (Parameter parameter : list) {
			parameter.setRemark(parameter.getRemark().replace("<", "&lt;").replace(">", "&gt;"));
		}
		return api;
	}

	@ResponseBody
	@RequestMapping(value = "/exportHtml")
	public String exportHtml(Api api, Model model) {
		String msg = "success";
		try {
			String dateString = new SimpleDateFormat("yyyy-MM-dd_HH.mm").format(new Date());
			String docPath = "E:/zzzzzz/" + dateString + "/";
			String url = "http://127.0.0.1:" + getRequest().getLocalPort() + getRequest().getContextPath();

			Response response = Jsoup.connect(url + "/api/index?projectId=" + api.getProjectId()).method(Method.GET)
					.execute();
			Document doc = Jsoup.parse(response.body());
			doc.select("link").attr("href", "pintuer.css");
			for (Element e : doc.select("a[data-path]")) {
				e.attr("href", e.attr("data-path"));
			}
			com.zong.util.FileUtils.writeTxt(docPath + "index.html", doc.toString());

			List<Api> apis = apiService.findApi(new PageData("projectId", api.getProjectId()));
			for (Api apiObj : apis) {
				response = Jsoup.connect(url + "/api/view?id=" + apiObj.getId()).method(Method.GET).execute();
				doc = Jsoup.parse(response.body());
				doc.select("link").attr("href", "pintuer.css");
				for (Element e : doc.select("a[data-path]")) {
					e.attr("href", e.attr("data-path"));
				}
				com.zong.util.FileUtils.writeTxt(docPath + apiObj.getBeanMethod() + "_" + apiObj.getSort() + ".html",
						doc.toString());
			}
			FileUtils.copyFile(new File(getRequest().getServletContext().getRealPath("/static/css/pintuer.css")),
					new File(docPath + "/pintuer.css"));
		} catch (Exception e) {
			logger.error(e.toString(), e);
			msg = e.toString();
		}
		return msg;
	}

	@ResponseBody
	@RequestMapping(value = "/{dbname}/tables")
	public List<Table> db(@PathVariable String dbname, Model model) {
		return new JdbcCodeService().showTables(dbname);
	}

	@ResponseBody
	@RequestMapping(value = "/{dbname}/tables/{tableName}")
	public List<ColumnField> db(@PathVariable String dbname, @PathVariable String tableName, Model model) {
		return new JdbcCodeService().showTableColumns(dbname, tableName);
	}

	/**
	 * 查询api列表
	 */
	@RequestMapping(value = "/list")
	public String list(Model model) {
		PageData pd = super.getPageData();
		List<Project> projects = projectService.findProject(new PageData());
		if (pd.get("projectId") == null && !projects.isEmpty()) {
			pd.put("projectId", projects.get(0).getId());
		}
		List<Folder> folders = folderService.findFolder(pd);
		for (Folder folder : folders) {
			folder.setApis(apiService.findApi(new PageData().put("folderId", folder.getId())));
		}
		model.addAttribute("folders", folders);
		model.addAttribute("projects", projects);
		return "/info/api/api_list";
	}

	/**
	 * 新增api页面
	 */
	@RequestMapping(value = "/toAdd")
	public String toAdd(Api api, Model model) {
		model.addAttribute("api", api);
		Project project = new Project();
		project.setId(api.getProjectId());
		model.addAttribute("project", projectService.loadProject(project));
		model.addAttribute("folders", folderService.findFolder(new PageData("projectId", api.getProjectId())));
		model.addAttribute("configData", Config.getConfigData());
		return "/info/api/api_add";
	}

	/**
	 * 新增api
	 */
	@RequestMapping(value = "/add")
	public String add(Api api, Model model) {
		try {
			apiService.addApi(api);
			model.addAttribute("msg", "success");
		} catch (Exception e) {
			logger.error(e.toString(), e);
			model.addAttribute("msg", e.toString());
		}
		return "redirect:/api/list?projectId=" + api.getProjectId();
	}

	/**
	 * 修改api页面
	 */
	@RequestMapping(value = "/toEdit")
	public String toEdit(Api api, Model model) {
		api = apiService.loadApi(api);
		model.addAttribute("api", api);
		Project project = new Project();
		project.setId(api.getProjectId());
		model.addAttribute("project", projectService.loadProject(project));
		model.addAttribute("folders", folderService.findFolder(new PageData("projectId", api.getProjectId())));
		model.addAttribute("configData", Config.getConfigData());
		return "/info/api/api_edit";
	}

	/**
	 * 修改api
	 */
	@RequestMapping(value = "/edit")
	public String edit(Api api, Model model) {
		try {
			apiService.editApi(api);
			model.addAttribute("msg", "success");
		} catch (Exception e) {
			logger.error(e.toString(), e);
			model.addAttribute("msg", e.toString());
		}
		return "redirect:/api/list?projectId=" + api.getProjectId();
	}

	@ResponseBody
	@RequestMapping(value = "/deal")
	public String deal(Api api, Model model) {
		String msg = "success";
		try {
			apiService.deal(api);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			msg = e.toString();
		}
		return msg;
	}

	/**
	 * 删除api
	 */
	@ResponseBody
	@RequestMapping(value = "/delete")
	public String delete(Api api) {
		String msg = "success";
		try {
			apiService.deleteApi(api);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			msg = e.toString();
		}
		return msg;
	}

	/**
	 * 删除多个api
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteAll")
	public String deleteAll() {
		String msg = "success";
		try {
			apiService.deleteApis(getRequest().getParameterValues("id"));
		} catch (Exception e) {
			logger.error(e.toString(), e);
			msg = e.toString();
		}
		return msg;
	}

}
