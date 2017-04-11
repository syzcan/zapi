package com.zong.web.service;

import java.util.List;

import com.zong.util.PageData;
import com.zong.web.bean.Folder;

/**
 * @desc folder业务接口类
 * @author zong
 * @date 2017年02月13日
 */
public interface FolderService {

	/**
	 * 新增folder
	 * 
	 * @param folder
	 * @throws Exception
	 */
	public void addFolder(Folder folder) throws Exception;
	
	/**
	 * 删除folder
	 * 
	 * @param folder
	 */
	public void deleteFolder(Folder folder) throws Exception;
	
	/**
	 * 删除多个folder
	 * @param ids
	 */
	public void deleteFolders(String[] ids) throws Exception;
	
	/**
	 * 修改folder
	 * 
	 * @param folder
	 * @throws Exception
	 */
	public void editFolder(Folder folder) throws Exception;

	/**
	 * 根据id查询folder
	 * 
	 * @param folder
	 * @return
	 */
	public Folder loadFolder(Folder folder);

	/**
	 * 查询全部folder
	 * 
	 * @param pageData
	 * @return
	 */
	public List<Folder> findFolder(PageData pageData);

	public void deal(Folder folder);
}
