<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title>月光边境</title>
<meta name="keywords" content="关键词" />
<meta name="description" content="描述" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<%@ include file="/WEB-INF/jsp/common/style.jsp"%>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/common/nav.jsp"%>
	<div class="admin">
		<div class="panel admin-panel">
			<div class="panel-head">
				<form action="${ctx}/project/list">
					<input type="button" class="button border-green" value="新增" onclick="openView('新增','${ctx}/project/toAdd')" />
					<input type="button" class="button border-red" value="删除" onclick="deleteForm('dataForm',this)" action="${ctx}/project/deleteAll" />
				</form>
			</div>
			<form id="dataForm" method="post">
				<table class="table table-hover">
					<thead>
						<tr>
							<th><input type="checkbox" class="checkall" name="checkall" checkfor="id" /></th>
							<th>name</th>
							<th>remark</th>
							<th>createTime</th>
							<th>操作</th>
						</tr>
					</thead>
					<c:forEach items="${projects }" var="project">
						<tr>
							<td>
								<input type="checkbox" name="id" value="${project.id }" />
							</td>
							<td>${project.name }</td>
							<td>${project.remark }</td>
							<td><fmt:formatDate value="${project.createTime }" pattern="yyyy-MM-dd"/></td>
							<td>
								<a class="button border-green button-little" href="${ctx}/api/list?projectId=${project.id }">查看</a>
								<a class="button border-blue button-little" href="javascript:;" onclick="openView('修改','${ctx}/project/toEdit?id=${project.id }')">修改</a> 
								<a class="button border-yellow button-little" href="javascript:;" onclick="ajaxDeleteData('${ctx}/project/delete?id=${project.id }')">删除</a>
							</td>
						</tr>
					</c:forEach>
				</table>
			</form>
			<div class="panel-foot text-center">
			</div>
		</div>
	</div>
</body>
</html>