package com.zong.web.bean;


import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @desc parameter实体类
 * @author zong
 * @date 2017年02月13日
 */
@XmlRootElement
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Parameter implements Serializable {
	private String id;
	private String apiId;
	private String name;
	private String type;
	private String required;
	private String dataType;
	private String remark;
    private Integer sort;
	
	public Parameter(){
	}
	
	public String getId(){
		return id;
	}
	
	public void setId(String id){
		this.id = id;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getType(){
		return type;
	}
	
	public void setType(String type){
		this.type = type;
	}
	
	public String getRequired(){
		return required;
	}
	
	public void setRequired(String required){
		this.required = required;
	}
	
	public String getDataType(){
		return dataType;
	}
	
	public void setDataType(String dataType){
		this.dataType = dataType;
	}
	
	public String getRemark(){
		return remark;
	}
	
	public void setRemark(String remark){
		this.remark = remark;
	}

	public String getApiId() {
		return apiId;
	}

	public void setApiId(String apiId) {
		this.apiId = apiId;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	
}