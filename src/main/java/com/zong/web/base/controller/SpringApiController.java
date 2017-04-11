package com.zong.web.base.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/spring")
public class SpringApiController {

	@RequestMapping("/api")
	public String test(HttpServletRequest request) {
		WebApplicationContext wc = (WebApplicationContext) request
				.getAttribute(DispatcherServlet.WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		RequestMappingHandlerMapping bean = wc.getBean(RequestMappingHandlerMapping.class);
		Map<RequestMappingInfo, HandlerMethod> handlerMethods = bean.getHandlerMethods();
		request.setAttribute("list", handlerMethods);
		return "/test";
	}

	@ResponseBody
	@RequestMapping("/api_json")
	public String api(String domain, HttpServletRequest request) {
		WebApplicationContext wc = (WebApplicationContext) request
				.getAttribute(DispatcherServlet.WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		RequestMappingHandlerMapping bean = wc.getBean(RequestMappingHandlerMapping.class);
		Map<RequestMappingInfo, HandlerMethod> handlerMethods = bean.getHandlerMethods();
		request.setAttribute("list", handlerMethods);
		List<Map> list = new ArrayList();
		Map folders = new HashMap();
		String collectionId = UUID.randomUUID().toString();
		for (RequestMappingInfo mappingInfo : handlerMethods.keySet()) {
			if (mappingInfo.getPatternsCondition().toString().indexOf("/spring/api") > -1) {
				continue;
			}
			if (mappingInfo.getPatternsCondition().toString().indexOf("/api/") == -1) {
				continue;
			}
			HandlerMethod method = handlerMethods.get(mappingInfo);
			String folderId = UUID.randomUUID().toString();
			Map folder = (Map) folders.get(method.getBean());
			if (folder == null) {
				folder = new HashMap();
				folder.put("id", folderId);
				folder.put("name", method.getBean());
				folder.put("description", "");
				folder.put("order", new ArrayList());
				folder.put("owner", 0);
				folders.put(method.getBean(), folder);
			}
			Map map = new HashMap();
			String url = mappingInfo.getPatternsCondition().toString().replace("[", "").replace("]", "") + ".json";
			url = url.replaceAll("\\{", ":").replaceAll("\\}", "");

			map.put("id", UUID.randomUUID());
			map.put("headers", "");
			map.put("url", (domain == null || domain.equals("") ? "http://localhost:8080" : domain) + url);
			map.put("preRequestScript", null);
			map.put("pathVariables", new HashMap());
			map.put("method", mappingInfo.getMethodsCondition().toString().replace("[", "").replace("]", ""));

			map.put("bean", folder.get("name"));
			map.put("beanMethod", folder.get("name") + "_" + method.getMethod().getName());

			map.put("data", null);
			map.put("dataMode", "params");
			map.put("tests", null);
			map.put("currentHelper", "normal");
			map.put("helperAttributes", new HashMap());
			map.put("time", System.currentTimeMillis());
			map.put("name", url);
			map.put("description", url);
			map.put("collectionId", collectionId);
			map.put("responses", new ArrayList());
			map.put("folderId", folder.get("id"));
			list.add(map);
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Map postMan = new HashMap();
		Collection<Map> foldersValue = folders.values();
		for (Map map : foldersValue) {
			for (Map data : list) {
				if (map.get("id").equals(data.get("folderId"))) {
					((List) map.get("order")).add(data.get("id"));
				}
			}
		}
		postMan.put("id", collectionId);
		postMan.put("folders", folders.values());
		postMan.put("order", new ArrayList());
		postMan.put("requests", list);
		postMan.put("description", "");
		postMan.put("name", "项目接口" + dateFormat.format(new Date()));
		postMan.put("timestamp", System.currentTimeMillis());
		postMan.put("owner", 0);
		postMan.put("remoteLink", "");
		postMan.put("public", false);
		ObjectMapper mapper = new ObjectMapper();
		String result = "";
		try {
			result = mapper.writeValueAsString(postMan);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return result;
	}
}
