package com.zong.web.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zong.util.PageData;
import com.zong.util.UUIDUtil;
import com.zong.web.bean.Project;
import com.zong.web.dao.ProjectMapper;

/**
 * @desc project业务实现类
 * @author zong
 * @date 2017年02月14日
 */
@Service
public class ProjectServiceImpl implements ProjectService {
	@Autowired
	private ProjectMapper projectMapper;

	/**
	 * 新增project
	 * 
	 * @param project
	 * @throws Exception
	 */
	@Transactional(rollbackFor = Exception.class)
	public void addProject(Project project) throws Exception {
		project.setId(UUIDUtil.get());
		project.setCreateTime(new Date());
		projectMapper.insert(project);
	}
	
	/**
	 * 删除project
	 * 
	 * @param project
	 */
	@Transactional(rollbackFor = Exception.class)
	public void deleteProject(Project project) throws Exception {
		projectMapper.delete(project);
	}
	
	/**
	 * 删除多个project
	 * @param ids
	 */
	public void deleteProjects(String[] ids) throws Exception {
		if(ids!=null && ids.length>0){
			Project project = new Project();
			for (String id : ids) {
				project.setId(id);
				projectMapper.delete(project);
			}
		}
	}

	/**
	 * 修改project
	 * 
	 * @param project
	 * @throws Exception
	 */
	@Transactional(rollbackFor = Exception.class)
	public void editProject(Project project) throws Exception {
		projectMapper.update(project);
	}

	/**
	 * 根据id查询project
	 * 
	 * @param project
	 * @return
	 */
	public Project loadProject(Project project) {
		return projectMapper.load(project);
	}

	/**
	 * 查询全部project
	 * 
	 * @param pageData
	 * @return
	 */
	public List<Project> findProject(PageData pageData) {
		return projectMapper.findProject(pageData);
	}
}
