package com.zong.web.dao;

import java.util.List;

import com.zong.util.PageData;
import com.zong.web.bean.Folder;

public interface FolderMapper {

	/**
	 * 根据对象主键查询
	 * @param folder
	 */
	Folder load(Folder folder);
	
	/**
	 * 根据条件查询全部
	 * 
	 * @param pageData
	 * @return
	 */
	List<Folder> findFolder(PageData pageData);
	
	/**
	 * 根据对象主键删除
	 * @param folder
	 */
	void delete(Folder folder);
	
	/**
	 * 插入对象全部属性的字段
	 * @param folder
	 */
	void insert(Folder folder);
	
	/**
	 * 根据主键更新对象不为空属性的字段
	 * @param folder
	 */
	void update(Folder folder);
	
}