package com.zong.web.service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zong.util.PageData;
import com.zong.util.UUIDUtil;
import com.zong.web.bean.Api;
import com.zong.web.bean.Folder;
import com.zong.web.bean.Parameter;
import com.zong.web.dao.ApiMapper;
import com.zong.web.dao.FolderMapper;
import com.zong.web.dao.ParameterMapper;

/**
 * @desc api业务实现类
 * @author zong
 * @date 2017年02月13日
 */
@Service
public class ApiServiceImpl implements ApiService {
	@Autowired
	private ApiMapper apiMapper;
	@Autowired
	private ParameterMapper parameterMapper;
	@Autowired
	private FolderMapper folderMapper;

	private ObjectMapper objectMapper = new ObjectMapper();

	/**
	 * 新增api
	 * 
	 * @param api
	 * @throws Exception
	 */
	@Transactional(rollbackFor = Exception.class)
	public void addApi(Api api) throws Exception {
		api.setId(UUID.randomUUID().toString().trim().replaceAll("-", ""));
		apiMapper.insert(api);
		// 保存参数
		saveParam(api.getHeaderParam(), "header", api.getId());
		saveParam(api.getUrlParam(), "url", api.getId());
		saveParam(api.getBodyParam(), "body", api.getId());
		saveParam(api.getResultParam(), "result", api.getId());
	}

	/**
	 * 删除api
	 * 
	 * @param api
	 */
	@Transactional(rollbackFor = Exception.class)
	public void deleteApi(Api api) throws Exception {
		apiMapper.delete(api);
	}

	/**
	 * 删除多个api
	 * 
	 * @param ids
	 */
	public void deleteApis(String[] ids) throws Exception {
		if (ids != null && ids.length > 0) {
			Api api = new Api();
			for (String id : ids) {
				api.setId(id);
				apiMapper.delete(api);
			}
		}
	}

	/**
	 * 修改api
	 * 
	 * @param api
	 * @throws Exception
	 */
	@Transactional(rollbackFor = Exception.class)
	public void editApi(Api api) throws Exception {
		apiMapper.update(api);
		// 保存参数，先删除再插入
		parameterMapper.deleteByApi(api);
		saveParam(api.getHeaderParam(), "header", api.getId());
		saveParam(api.getUrlParam(), "url", api.getId());
		saveParam(api.getBodyParam(), "body", api.getId());
		saveParam(api.getResultParam(), "result", api.getId());
	}

	private void saveParam(String json, String type, String apiId) throws Exception {
		if(json==null || json.equals("")){
			return;
		}
		List<Parameter> list = objectMapper.readValue(json, new TypeReference<List<Parameter>>() {
		});
		for (Parameter parameter : list) {
			parameter.setId(UUIDUtil.get());
			parameter.setApiId(apiId);
			parameter.setType(type);
			parameter.setSort(list.indexOf(parameter) + 1);
			parameterMapper.insert(parameter);
		}
	}

	/**
	 * 根据id查询api
	 * 
	 * @param api
	 * @return
	 */
	public Api loadApi(Api api) {
		api = apiMapper.load(api);
		PageData pd = new PageData();
		pd.put("apiId", api.getId());
		api.setHeaderParams(parameterMapper.findParameter(pd.put("type", "header")));
		api.setUrlParams(parameterMapper.findParameter(pd.put("type", "url")));
		api.setBodyParams(parameterMapper.findParameter(pd.put("type", "body")));
		api.setResultParams(parameterMapper.findParameter(pd.put("type", "result")));
		return api;
	}
	
	/**
	 * 查询全部api
	 * 
	 * @param pageData
	 * @return
	 */
	public List<Api> findApi(PageData pageData) {
		return apiMapper.findApi(pageData);
	}

	@Transactional(rollbackFor = Exception.class)
	public void importJson(String json, String projectId) throws Exception {
		Map map = objectMapper.readValue(json, Map.class);
		List<Map> folders = (List<Map>) map.get("folders");
		List<Map> requests = (List<Map>) map.get("requests");
		int sortFolder = 1;
		for (Map folderMap : folders) {
			Folder folder = new Folder();
			folder.setId(folderMap.get("id").toString());
			folder.setProjectId(projectId);
			folder.setCreateTime(new Date());
			folder.setName(folderMap.get("name").toString());
			folder.setRemark(folderMap.get("description").toString());
			folder.setSort(sortFolder++);
			folderMapper.insert(folder);
			int sort = 1;
			for (Map request : requests) {
				if (request.get("folderId").toString().equals(folder.getId())) {
					Api api = new Api();
					api.setId(request.get("id").toString());
					api.setFolderId(request.get("folderId").toString());
					api.setProjectId(projectId);
					api.setBeanMethod(request.get("beanMethod").toString());
					api.setMethod(request.get("method").toString());
					api.setName(request.get("beanMethod").toString());
					api.setUrl(request.get("name").toString());
					api.setRemark("");
					api.setRequestBody("");
					api.setCurl("");
					api.setResponse("");
					api.setSort(sort++);
					apiMapper.insert(api);
				}
			}
		}
	}

	@Transactional(rollbackFor = Exception.class)
	public void deal(Api api) {
		apiMapper.update(api);
	}
}
