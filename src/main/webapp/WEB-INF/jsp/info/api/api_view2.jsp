<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title>月光边境</title>
<meta name="keywords" content="关键词" />
<meta name="description" content="描述" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<link rel="stylesheet" href="${ctx }/static/css/pintuer.css" />
</head>
<body>
	<div class="container">
		<div class="panel-head text-center">
			<h1>${empty folder.remark?folder.name:folder.remark }</h1>
		</div>
		<table class="table table-bordered">
			<tr>
				<td colspan="2">${api.name }</td>
			</tr>
			<tr>
				<td width="150px">详细说明：</td>
				<td>${api.remark }</td>
			</tr>
			<tr>
				<td>请求方式：</td>
				<td><span
					class="font-weight ${api.method=='GET'?'text-green':'' }${api.method=='POST'?'text-yellow':'' }${api.method=='PUT'?'text-blue':'' }${api.method=='DELETE'?'text-red':'' }">${empty api.method?'不限定':api.method }</span></td>
			</tr>
			<tr>
				<td>请求地址：</td>
				<td>${api.url }</td>
			</tr>
		</table>

		<table class="margin-big-top table table-hover table-bordered" style="${fn:length(api.headerParams)==0?'display:none':''}">
			<thead>
				<tr class="current">
					<th width="150px"><span class="badge bg-green">header参数名</span></th>
					<th width="100px">类型</th>
					<th width="80px">必需</th>
					<th>描述</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${api.headerParams }" var="parameter">
					<tr>
						<td>${parameter.name }</td>
						<td>${parameter.dataType }</td>
						<td>${parameter.required==1?'是':'否' }</td>
						<td>${parameter.remark }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<table class="margin-big-top table table-hover table-bordered" style="${fn:length(api.urlParams)==0?'display:none':''}">
			<thead>
				<tr class="current">
					<th width="150px"><span class="badge bg-green">url参数名</span></th>
					<th width="100px">类型</th>
					<th width="80px">必需</th>
					<th>描述</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${api.urlParams }" var="parameter">
					<tr>
						<td>${parameter.name }</td>
						<td>${parameter.dataType }</td>
						<td>${parameter.required==1?'是':'否' }</td>
						<td>${parameter.remark }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="margin-top padding">request示例：</div>
		<pre class="border padding bg text-small"><code>${api.curl }</code></pre>
		<c:if test="${!empty api.requestBody }">
			<div class="margin-top padding">Request Body：</div>
			<pre class="border padding bg text-small"><code>${api.requestBody }</code></pre>
			<table class="margin-big-top table table-hover table-bordered" style="${fn:length(api.bodyParams)==0?'display:none':''}">
				<thead>
					<tr class="current">
						<th width="150px"><span class="badge bg-green">body参数名</span></th>
						<th width="100px">类型</th>
						<th width="80px">必需</th>
						<th>描述</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${api.bodyParams }" var="parameter">
						<tr>
							<td>${parameter.name }</td>
							<td>${parameter.dataType }</td>
							<td>${parameter.required==1?'是':'否' }</td>
							<td>${parameter.remark }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
		
		<div class="margin-top padding">response示例：</div>
		<pre class="border padding bg text-small"><code>${api.response }</code></pre>
		<table class="margin-big-top table table-hover table-bordered" style="${fn:length(api.resultParams)==0?'display:none':''}">
			<thead>
				<tr class="current">
					<th width="150px"><span class="badge bg-main">返回参数名</span></th>
					<th width="100px">类型</th>
					<th width="80px">必需</th>
					<th>描述</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${api.resultParams }" var="parameter">
					<tr>
						<td>${parameter.name }</td>
						<td>${parameter.dataType }</td>
						<td>${parameter.required==1?'是':'否' }</td>
						<td>${parameter.remark }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>