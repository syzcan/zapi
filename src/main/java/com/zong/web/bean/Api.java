package com.zong.web.bean;


import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @desc api实体类
 * @author zong
 * @date 2017年02月13日
 */
@XmlRootElement
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Api implements Serializable {
	private String id;
	private String folderId;
	private String projectId;
	private String name;
	private String method;
	private String url;
	private String remark;
	private String requestBody;
	private String curl;
	private String response;
	private String beanMethod;
	private Integer sort;
	
	private String headerParam;
	private String urlParam;
	private String bodyParam;
	private String resultParam;
	private List<Parameter> headerParams;
	private List<Parameter> urlParams;
	private List<Parameter> bodyParams;
	private List<Parameter> resultParams;
	
	public Api(){
	}
	
	public String getId(){
		return id;
	}
	
	public void setId(String id){
		this.id = id;
	}
	
	public String getFolderId(){
		return folderId;
	}
	
	public void setFolderId(String folderId){
		this.folderId = folderId;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getMethod(){
		return method;
	}
	
	public void setMethod(String method){
		this.method = method;
	}
	
	public String getUrl(){
		return url;
	}
	
	public void setUrl(String url){
		this.url = url;
	}
	
	public String getRemark(){
		return remark;
	}
	
	public void setRemark(String remark){
		this.remark = remark;
	}
	
	public String getResponse(){
		return response;
	}
	
	public void setResponse(String response){
		this.response = response;
	}

	public String getHeaderParam() {
		return headerParam;
	}

	public void setHeaderParam(String headerParam) {
		this.headerParam = headerParam;
	}

	public String getUrlParam() {
		return urlParam;
	}

	public void setUrlParam(String urlParam) {
		this.urlParam = urlParam;
	}

	public String getBodyParam() {
		return bodyParam;
	}

	public void setBodyParam(String bodyParam) {
		this.bodyParam = bodyParam;
	}

	public String getResultParam() {
		return resultParam;
	}

	public void setResultParam(String resultParam) {
		this.resultParam = resultParam;
	}

	public List<Parameter> getHeaderParams() {
		return headerParams;
	}

	public void setHeaderParams(List<Parameter> headerParams) {
		this.headerParams = headerParams;
	}

	public List<Parameter> getUrlParams() {
		return urlParams;
	}

	public void setUrlParams(List<Parameter> urlParams) {
		this.urlParams = urlParams;
	}

	public List<Parameter> getBodyParams() {
		return bodyParams;
	}

	public void setBodyParams(List<Parameter> bodyParams) {
		this.bodyParams = bodyParams;
	}

	public List<Parameter> getResultParams() {
		return resultParams;
	}

	public void setResultParams(List<Parameter> resultParams) {
		this.resultParams = resultParams;
	}

	public String getBeanMethod() {
		return beanMethod;
	}

	public void setBeanMethod(String beanMethod) {
		this.beanMethod = beanMethod;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getRequestBody() {
		return requestBody;
	}

	public void setRequestBody(String requestBody) {
		this.requestBody = requestBody;
	}

	public String getCurl() {
		return curl;
	}

	public void setCurl(String curl) {
		this.curl = curl;
	}

}