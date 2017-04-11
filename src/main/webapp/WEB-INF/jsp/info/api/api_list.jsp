<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
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
<%@ include file="/WEB-INF/jsp/common/style.jsp"%>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/common/nav.jsp"%>
	<div class="admin">
		<div class="panel admin-panel">
			<div class="panel-head">
				<form id="searchForm" action="${ctx}/api/list">
					<input type="button" class="button border-green" value="新增文件夹" onclick="toAddFolder()" />
					<input type="button" class="button border-blue" value="新增API" onclick="toAddAPI()" />
					<select class="input input-auto" name="projectId" style="margin-left: 50px" onchange="$(this).parents('form').submit()">
					<c:forEach items="${projects }" var="project">
						<option value="${project.id }"${project.id==pd.projectId?'selected':'' }>项目 - ${project.name }</option>
					</c:forEach>
					</select>
					
					<input type="text" id="importUrl" value="" class="input input-auto border-green" size="40px" placeholder="json地址"> 
					<input type="button" value="导入" onclick="importJson()" class="button  bg-green" style="border-left: 0 none;margin-left: -10px;border-top-left-radius:0;border-bottom-left-radius:0" />
					<input type="button" value="导出html" onclick="ajaxDeal('${ctx}/api/exportHtml?projectId=${pd.projectId }')" class="button bg-yellow" />
<%-- 					<input type="button" value="导出word" onclick="ajaxDeal('${ctx}/api/export/word?projectId=${pd.projectId }')" class="button bg-blue" /> --%>
				</form>
			</div>
			<form id="dataForm" method="post">
				<table class="table">
					<thead>
						<tr>
							<th width="20px"><span class="icon-folder-open-o text-big" onclick="openClose(this)"></span></th>
							<th width="250px">名称</th>
							<th width="80px">请求方式</th>
							<th width="300px">请求地址</th>
							<th width="120px">操作</th>
						</tr>
					</thead>
					<c:forEach items="${folders }" var="folder">
							<tr class="panel-head">
								<td><span class="icon-folder-open-o text-big" onclick="openClose(this)" data-folderId="${folder.id }"></span></td>
								<td colspan="3"><strong>${folder.name }</strong> - ${folder.remark }</td>
								<td>
								<a class="button border-blue button-little icon-edit" href="javascript:;" onclick="openView('修改','${ctx}/folder/toEdit?id=${folder.id }')"></a>
								<a class="button border-red button-little icon-trash-o" href="javascript:;" onclick="ajaxDeleteData('${ctx}/folder/delete?id=${folder.id }')"></a>
								</td>
							</tr>
							<c:forEach items="${folder.apis }" var="api">
							<tr data-id="${api.id }" data-sort="${api.sort }" data-folderId="${folder.id }">
								<td>
								</td>
								<td>${api.name }</td>
								<td><strong
									class="font-weight ${api.method=='GET'?'text-green':'' }${api.method=='POST'?'text-yellow':'' }${api.method=='PUT'?'text-blue':'' }${api.method=='DELETE'?'text-red':'' }">${api.method }</strong></td>
								<td>${api.url }</td>
								<td>
									<a class="button border-green button-little" href="${ctx}/api/view?id=${api.id }" target="_blank">查看</a>
									<a class="button border-blue button-little" href="${ctx}/api/toEdit?id=${api.id }">修改</a> 
									<a class="button border-yellow button-little" href="javascript:;" onclick="moveUp('${api.id }')">上移</a>
									<a class="button border-red button-little" href="javascript:;" onclick="ajaxDeleteData('${ctx}/api/delete?id=${api.id }')">删除</a>
								</td>
							</tr>
							</c:forEach>
					</c:forEach>	
				</table>
			</form>
			<div class="panel-foot text-center">
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	function toAddFolder(){
		var projectId = $(':input[name="projectId"]').val();
		if(projectId==null){
			layer.msg('请先创建项目');
			return;
		}
		openView('新增文件夹','${ctx}/folder/toAdd?projectId='+projectId);	
	}
	function toAddAPI(){
		var projectId = $(':input[name="projectId"]').val();
		if(projectId==null){
			layer.msg('请先创建项目');
			return;
		}
		location.href ='${ctx}/api/toAdd?projectId='+projectId;
	}
	
	function openClose(obj){
		if(!$(obj).attr('data-folderId')){
			if($(obj).hasClass('icon-folder-o')){
				$('.icon-folder-o').removeClass('icon-folder-o').addClass('icon-folder-open-o');
				$('tr[data-folderId]').show();
			}else{
				$('.icon-folder-open-o').removeClass('icon-folder-open-o').addClass('icon-folder-o');
				$('tr[data-folderId]').hide();
			}
			return;
		}
		if($(obj).hasClass('icon-folder-o')){
			$(obj).removeClass('icon-folder-o').addClass('icon-folder-open-o');
			$('tr[data-folderId="'+$(obj).attr('data-folderId')+'"]').show();
		}else{
			$(obj).removeClass('icon-folder-open-o').addClass('icon-folder-o');
			$('tr[data-folderId="'+$(obj).attr('data-folderId')+'"]').hide();
		}
	}

	function importJson(){
		var url = $.trim($('#importUrl').val());
		var projectId = $(':input[name="projectId"]').val();
		if(url==''){
			return;
		}
		if(projectId==null){
			layer.msg('请先创建项目');
			return;
		}
		$.ajax({
			url:'${ctx}/api/import?url='+url+"&projectId="+projectId,
			dataType:'text',
			success:function(data){
				if(data=='success'){
					$('#searchForm').submit();
				}
			}
		});
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
		ajaxDeal('${ctx}/api/deal?id=' + $cur.attr('data-id') + '&sort=' + sort1);
		ajaxDeal('${ctx}/api/deal?id=' + $prev.attr('data-id') + '&sort=' + sort2);
		$prev.attr('data-sort',sort2).before($cur.attr('data-sort',sort1));
	}
</script>
</html>