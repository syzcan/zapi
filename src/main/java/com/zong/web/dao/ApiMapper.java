package com.zong.web.dao;

import java.util.List;

import com.zong.util.PageData;
import com.zong.web.bean.Api;

public interface ApiMapper {

	/**
	 * 根据对象主键查询
	 * @param api
	 */
	Api load(Api api);
	
	/**
	 * 根据条件查询全部
	 * 
	 * @param pageData
	 * @return
	 */
	List<Api> findApi(PageData pageData);
	
	/**
	 * 根据对象主键删除
	 * @param api
	 */
	void delete(Api api);
	
	/**
	 * 插入对象全部属性的字段
	 * @param api
	 */
	void insert(Api api);
	
	/**
	 * 根据主键更新对象不为空属性的字段
	 * @param api
	 */
	void update(Api api);
	
}