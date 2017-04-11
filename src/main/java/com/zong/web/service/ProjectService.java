package com.zong.web.service;

import java.util.List;

import com.zong.util.PageData;
import com.zong.web.bean.Project;

/**
 * @desc project业务接口类
 * @author zong
 * @date 2017年02月14日
 */
public interface ProjectService {

	/**
	 * 新增project
	 * 
	 * @param project
	 * @throws Exception
	 */
	public void addProject(Project project) throws Exception;
	
	/**
	 * 删除project
	 * 
	 * @param project
	 */
	public void deleteProject(Project project) throws Exception;
	
	/**
	 * 删除多个project
	 * @param ids
	 */
	public void deleteProjects(String[] ids) throws Exception;
	
	/**
	 * 修改project
	 * 
	 * @param project
	 * @throws Exception
	 */
	public void editProject(Project project) throws Exception;

	/**
	 * 根据id查询project
	 * 
	 * @param project
	 * @return
	 */
	public Project loadProject(Project project);

	/**
	 * 查询全部project
	 * 
	 * @param pageData
	 * @return
	 */
	public List<Project> findProject(PageData pageData);
}
