package com.zong.web.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zong.util.PageData;
import com.zong.web.bean.Folder;
import com.zong.web.dao.FolderMapper;

/**
 * @desc folder业务实现类
 * @author zong
 * @date 2017年02月13日
 */
@Service
public class FolderServiceImpl implements FolderService {
	@Autowired
	private FolderMapper folderMapper;

	/**
	 * 新增folder
	 * 
	 * @param folder
	 * @throws Exception
	 */
	@Transactional(rollbackFor = Exception.class)
	public void addFolder(Folder folder) throws Exception {
		folder.setId(UUID.randomUUID().toString().trim().replaceAll("-", ""));
		folder.setCreateTime(new Date());
		folder.setSort(folderMapper.findFolder(new PageData("projectId",folder.getProjectId())).size()+1);
		folderMapper.insert(folder);
	}
	
	/**
	 * 删除folder
	 * 
	 * @param folder
	 */
	@Transactional(rollbackFor = Exception.class)
	public void deleteFolder(Folder folder) throws Exception {
		folderMapper.delete(folder);
	}
	
	/**
	 * 删除多个folder
	 * @param ids
	 */
	public void deleteFolders(String[] ids) throws Exception {
		if(ids!=null && ids.length>0){
			Folder folder = new Folder();
			for (String id : ids) {
				folder.setId(id);
				folderMapper.delete(folder);
			}
		}
	}

	/**
	 * 修改folder
	 * 
	 * @param folder
	 * @throws Exception
	 */
	@Transactional(rollbackFor = Exception.class)
	public void editFolder(Folder folder) throws Exception {
		folderMapper.update(folder);
	}

	/**
	 * 根据id查询folder
	 * 
	 * @param folder
	 * @return
	 */
	public Folder loadFolder(Folder folder) {
		return folderMapper.load(folder);
	}

	/**
	 * 查询全部folder
	 * 
	 * @param pageData
	 * @return
	 */
	public List<Folder> findFolder(PageData pageData) {
		return folderMapper.findFolder(pageData);
	}

	@Transactional(rollbackFor = Exception.class)
	public void deal(Folder folder) {
		folderMapper.update(folder);
	}
}
