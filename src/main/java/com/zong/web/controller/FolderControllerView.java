package com.zong.web.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zong.util.PageData;
import com.zong.web.base.controller.BaseController;
import com.zong.web.bean.Folder;
import com.zong.web.bean.Project;
import com.zong.web.service.FolderService;
import com.zong.web.service.ProjectService;

/**
 * @desc 控制层FolderController
 * @author zong
 * @date 2017年02月13日
 */
@Controller
@RequestMapping(value = "/folder")
public class FolderControllerView extends BaseController {
	private final static Logger logger = Logger.getLogger(FolderControllerView.class);

	@Autowired
	private FolderService folderService;
	@Autowired
	private ProjectService projectService;

	/**
	 * 查询folder列表
	 */
	@RequestMapping(value = "/list")
	public String list(Model model) {
		PageData pd = super.getPageData();
		List<Project> projects = projectService.findProject(new PageData());
		if (pd.get("projectId") == null && !projects.isEmpty()) {
			pd.put("projectId", projects.get(0).getId());
		}
		model.addAttribute("projects", projects);
		model.addAttribute("folders", folderService.findFolder(pd));
		return "/info/folder/folder_list";
	}
	
	/**
	 * 查询folder详情
	 */
	@RequestMapping(value = "/view")
	public String view(Folder folder, Model model) {
		model.addAttribute("folder", folderService.loadFolder(folder));
		return "/info/folder/folder_view";
	}
	
	/**
	 * 新增folder页面
	 */
	@RequestMapping(value = "/toAdd")
	public String toAdd(Folder folder, Model model) {
		model.addAttribute("folder", folder);
		model.addAttribute("projects", projectService.findProject(new PageData()));
		return "/info/folder/folder_add";
	}

	/**
	 * 新增folder
	 */
	@RequestMapping(value = "/add")
	public String add(Folder folder, Model model) {
		try {
			folderService.addFolder(folder);
			model.addAttribute("msg", "success");
		} catch (Exception e) {
			logger.error(e.toString(), e);
			model.addAttribute("msg", e.toString());
		}
		return "/common/save_result";
	}

	/**
	 * 修改folder页面
	 */
	@RequestMapping(value = "/toEdit")
	public String toEdit(Folder folder, Model model) {
		model.addAttribute("folder", folderService.loadFolder(folder));
		model.addAttribute("projects", projectService.findProject(new PageData()));
		return "/info/folder/folder_edit";
	}

	/**
	 * 修改folder
	 */
	@RequestMapping(value = "/edit")
	public String edit(Folder folder, Model model) {
		try {
			folderService.editFolder(folder);
			model.addAttribute("msg", "success");
		} catch (Exception e) {
			logger.error(e.toString(), e);
			model.addAttribute("msg", e.toString());
		}
		return "/common/save_result";
	}
	
	@ResponseBody
	@RequestMapping(value = "/deal")
	public String deal(Folder folder, Model model) {
		String msg = "success";
		try {
			folderService.deal(folder);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			msg = e.toString();
		}
		return msg;
	}

	/**
	 * 删除folder
	 */
	@ResponseBody
	@RequestMapping(value = "/delete")
	public String delete(Folder folder) {
		String msg = "success";
		try {
			folderService.deleteFolder(folder);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			msg = e.toString();
		}
		return msg;
	}
	
	/**
	 * 删除多个folder
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteAll")
	public String deleteAll() {
		String msg = "success";
		try {
			folderService.deleteFolders(getRequest().getParameterValues("id"));
		} catch (Exception e) {
			logger.error(e.toString(), e);
			msg = e.toString();
		}
		return msg;
	}
	
}
