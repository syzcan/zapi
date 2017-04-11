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
				<form action="${ctx}/folder/list">
					<input type="button" class="button border-green" value="新增" onclick="toAdd()" />
					<input type="button" class="button border-red" value="删除" onclick="deleteForm('dataForm',this)" action="${ctx}/folder/deleteAll" />
					<select class="input input-auto" name="projectId" style="margin-left: 50px" onchange="$(this).parents('form').submit()">
					<c:forEach items="${projects }" var="project">
						<option value="${project.id }"${project.id==page.pd.projectId?'selected':'' }>项目 - ${project.name }</option>
					</c:forEach>
					</select>
				</form>
			</div>
			<form id="dataForm" method="post">
				<table class="table table-hover">
					<thead>
						<tr>
							<th><input type="checkbox" class="checkall" name="checkall" checkfor="id" /></th>
							<th>name</th>
							<th>remark</th>
							<th>操作</th>
						</tr>
					</thead>
					<c:forEach items="${folders }" var="folder">
						<tr data-id="${folder.id }" data-sort="${folder.sort }">
							<td>
								<input type="checkbox" name="id" value="${folder.id }" />
							</td>
							<td>${folder.name }【${folder.apiSize }】</td>
							<td>${folder.remark }</td>
							<td>
								<a class="button border-blue button-little" href="javascript:;" onclick="openView('修改','${ctx}/folder/toEdit?id=${folder.id }')">修改</a> 
								<a class="button border-yellow button-little" href="javascript:;" onclick="moveUp('${folder.id }')">上移</a>
<%-- 								<a class="button border-yellow button-little" href="javascript:;" onclick="ajaxDeleteData('${ctx}/folder/delete?id=${folder.id }')">删除</a> --%>
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
<script type="text/javascript">
function toAdd(){
	var projectId = $(':input[name="projectId"]').val();
	if(projectId==null){
		layer.msg('请先创建项目');
		return;
	}
	openView('新增','${ctx}/folder/toAdd?projectId='+projectId);	
}

function moveUp(id){
	$cur = $('tr[data-id="'+id+'"]');
	$prev = $cur.prev('[data-id]');
	if($prev.length < 1){
		return;
	}
	var sort1 = $prev.attr('data-sort');
	var sort2 = $cur.attr('data-sort');
	//后台修改
	ajaxDeal('${ctx}/folder/deal?id=' + $cur.attr('data-id') + '&sort=' + sort1);
	ajaxDeal('${ctx}/folder/deal?id=' + $prev.attr('data-id') + '&sort=' + sort2);
	$prev.attr('data-sort',sort2).before($cur.attr('data-sort',sort1));
}
</script>
</html>