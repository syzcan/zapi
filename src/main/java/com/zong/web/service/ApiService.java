package com.zong.web.service;

import java.util.List;

import com.zong.util.PageData;
import com.zong.web.bean.Api;

/**
 * @desc api业务接口类
 * @author zong
 * @date 2017年02月13日
 */
public interface ApiService {

	/**
	 * 新增api
	 * 
	 * @param api
	 * @throws Exception
	 */
	public void addApi(Api api) throws Exception;
	
	/**
	 * 删除api
	 * 
	 * @param api
	 */
	public void deleteApi(Api api) throws Exception;
	
	/**
	 * 删除多个api
	 * @param ids
	 */
	public void deleteApis(String[] ids) throws Exception;
	
	/**
	 * 修改api
	 * 
	 * @param api
	 * @throws Exception
	 */
	public void editApi(Api api) throws Exception;

	/**
	 * 根据id查询api
	 * 
	 * @param api
	 * @return
	 */
	public Api loadApi(Api api);

	/**
	 * 查询全部api
	 * 
	 * @param pageData
	 * @return
	 */
	public List<Api> findApi(PageData pageData);

	public void importJson(String json,String projectId)  throws Exception;

	public void deal(Api api);
}
