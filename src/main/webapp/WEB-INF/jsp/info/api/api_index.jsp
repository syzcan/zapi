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
<c:set var="domain" value="http://127.0.0.1" />
<link rel="stylesheet" href="${ctx }/static/css/pintuer.css" />
<style>
.nav .active a {
	font-weight: normal;
	color: #0a8
}
</style>
</head>
<body>
	<div style="width: 1000px; margin: 20px auto">
		<div class="panel-head text-center">
			<h1>健康档案浏览器接口文档</h1>
		</div>
		<div class="margin-top">
			<div class="float-left height"
				style="width: 180px; overflow-x: hidden;">
				<ul class="nav">
					<c:forEach items="${folders }" var="folder">
						<c:if test="${fn:length(folder.apis)>0 }">
							<li><strong>${folder.name }</strong>
								<ul>
									<c:forEach items="${folder.apis }" var="apiV">
										<li ${apiV.id==api.id?'class="active"':'' }
											id="${apiV.beanMethod }_${apiV.sort}"><a
											href="${ctx }/api/view?id=${apiV.id}"
											data-path="${apiV.beanMethod }_${apiV.sort}.html">${apiV.name }</a>
										</li>
									</c:forEach>
								</ul></li>
						</c:if>
					</c:forEach>
				</ul>
			</div>
			<div class="float-left margin-big-left" style="width: 800px">
				<table class="table">
					<tr>
						<td width="150px">详细说明：</td>
						<td>${api.remark }</td>
					</tr>
					<tr>
						<td>请求方式：</td>
						<td><strong
							class="font-weight ${api.method=='GET'?'text-green':'' }${api.method=='POST'?'text-yellow':'' }${api.method=='PUT'?'text-blue':'' }${api.method=='DELETE'?'text-red':'' }">${empty api.method?'不限定':api.method }</strong></td>
					</tr>
					<tr>
						<td>请求地址：</td>
						<td>${api.url }</td>
					</tr>
				</table>


			</div>
		</div>
	</div>
</body>
</html>