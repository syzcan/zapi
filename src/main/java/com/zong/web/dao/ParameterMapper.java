package com.zong.web.dao;

import java.util.List;

import com.zong.util.PageData;
import com.zong.web.bean.Api;
import com.zong.web.bean.Parameter;

public interface ParameterMapper {

	/**
	 * 根据对象主键查询
	 * @param parameter
	 */
	Parameter load(Parameter parameter);
	
	/**
	 * 根据条件查询全部
	 * 
	 * @param pageData
	 * @return
	 */
	List<Parameter> findParameter(PageData pageData);
	
	/**
	 * 根据对象主键删除
	 * @param parameter
	 */
	void delete(Parameter parameter);
	
	/**
	 * 插入对象全部属性的字段
	 * @param parameter
	 */
	void insert(Parameter parameter);
	
	/**
	 * 根据主键更新对象不为空属性的字段
	 * @param parameter
	 */
	void update(Parameter parameter);

	void deleteByApi(Api api);
	
}