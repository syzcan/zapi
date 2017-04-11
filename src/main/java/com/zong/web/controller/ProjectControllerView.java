package com.zong.web.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zong.util.PageData;
import com.zong.web.base.controller.BaseController;
import com.zong.web.bean.Project;
import com.zong.web.service.ProjectService;

/**
 * @desc 控制层ProjectController
 * @author zong
 * @date 2017年02月14日
 */
@Controller
@RequestMapping(value = "/project")
public class ProjectControllerView extends BaseController {
	private final static Logger logger = Logger.getLogger(ProjectControllerView.class);

	@Autowired
	private ProjectService projectService;

	/**
	 * 查询project列表
	 */
	@RequestMapping(value = "/list")
	public String list(Model model) {
		model.addAttribute("projects", projectService.findProject(new PageData()));
		return "/info/project/project_list";
	}
	
	/**
	 * 查询project详情
	 */
	@RequestMapping(value = "/view")
	public String view(Project project, Model model) {
		model.addAttribute("project", projectService.loadProject(project));
		return "/info/project/project_view";
	}
	
	/**
	 * 新增project页面
	 */
	@RequestMapping(value = "/toAdd")
	public String toAdd() {
		return "/info/project/project_add";
	}

	/**
	 * 新增project
	 */
	@RequestMapping(value = "/add")
	public String add(Project project, Model model) {
		try {
			projectService.addProject(project);
			model.addAttribute("msg", "success");
		} catch (Exception e) {
			logger.error(e.toString(), e);
			model.addAttribute("msg", e.toString());
		}
		return "/common/save_result";
	}

	/**
	 * 修改project页面
	 */
	@RequestMapping(value = "/toEdit")
	public String toEdit(Project project, Model model) {
		model.addAttribute("project", projectService.loadProject(project));
		return "/info/project/project_edit";
	}

	/**
	 * 修改project
	 */
	@RequestMapping(value = "/edit")
	public String edit(Project project, Model model) {
		try {
			projectService.editProject(project);
			model.addAttribute("msg", "success");
		} catch (Exception e) {
			logger.error(e.toString(), e);
			model.addAttribute("msg", e.toString());
		}
		return "/common/save_result";
	}

	/**
	 * 删除project
	 */
	@ResponseBody
	@RequestMapping(value = "/delete")
	public String delete(Project project) {
		String msg = "success";
		try {
			projectService.deleteProject(project);
		} catch (Exception e) {
			logger.error(e.toString(), e);
			msg = e.toString();
		}
		return msg;
	}
	
	/**
	 * 删除多个project
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteAll")
	public String deleteAll() {
		String msg = "success";
		try {
			projectService.deleteProjects(getRequest().getParameterValues("id"));
		} catch (Exception e) {
			logger.error(e.toString(), e);
			msg = e.toString();
		}
		return msg;
	}
	
}
