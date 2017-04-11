package com.zong.web.base.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.zong.util.PageData;

/**
 * @desc Controller基类，包含表单参数封装等通用操作
 * @author zong
 * @date 2016年3月13日
 */
public class BaseController {

	protected Logger logger = Logger.getLogger(this.getClass());

	/**
	 * new PageData对象
	 * 
	 * @return PageData
	 */
	public PageData getPageData() {
		PageData pd = new PageData(this.getRequest());
		getRequest().setAttribute("pd",pd);
		return pd;
	}

	/**
	 * 得到request对象
	 * 
	 * @return HttpServletRequest
	 */
	public HttpServletRequest getRequest() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		return request;
	}

	/**
	 * httpsession
	 * 
	 * @return HttpSession
	 */
	public HttpSession getSession() {
		return getRequest().getSession();
	}

	public ServletContext getApplication() {
		return getRequest().getServletContext();
	}

	/**
	 * 转发页面消息提示
	 * 
	 * @param msg
	 */
	public void setMsg(String msg) {
		getRequest().setAttribute("msg", msg);
	}

	/**
	 * 项目真实根路径
	 * 
	 * @return
	 */
	public String getRealPath() {
		return getRequest().getServletContext().getRealPath("/");
	}
}
