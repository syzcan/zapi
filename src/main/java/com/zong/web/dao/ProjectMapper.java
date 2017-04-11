package com.zong.web.dao;

import java.util.List;

import com.zong.util.PageData;
import com.zong.web.bean.Project;

public interface ProjectMapper {

	/**
	 * 根据对象主键查询
	 * @param project
	 */
	Project load(Project project);
	
	/**
	 * 根据条件查询全部
	 * 
	 * @param pageData
	 * @return
	 */
	List<Project> findProject(PageData pageData);
	
	/**
	 * 根据对象主键删除
	 * @param project
	 */
	void delete(Project project);
	
	/**
	 * 插入对象全部属性的字段
	 * @param project
	 */
	void insert(Project project);
	
	/**
	 * 根据主键更新对象不为空属性的字段
	 * @param project
	 */
	void update(Project project);
	
}