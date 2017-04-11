<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title>月光边境</title>
<meta name="keywords" content="关键词" />
<meta name="description" content="描述" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="pintuer.css" />
</head>
<body>
	<table class="table">
					<thead>
						<tr>
							<th width="250px">名称</th>
							<th width="80px">请求方式</th>
							<th width="300px">请求地址</th>
							<th width="250px">描述</th>
							<th width="120px">操作</th>
						</tr>
					</thead>
					<#list folders as folder>
						<#if (folder.apis?size>0) >
							<tr class="panel-head">
								<td colspan="5"><strong>${folder.name }</strong> - <strong class="text-red">${folder.remark }</strong></td>
							</tr>
							<#list folder.apis as api>
							<tr data-id="${api.id }" data-sort="${api.sort }">
								<td>${api.name }</td>
								<td><strong class="font-weight
					<#if api.method=='GET'> text-green</#if><#if api.method=='POST'> text-yellow</#if>
					<#if api.method=='PUT'> text-blue</#if><#if api.method=='DELETE'> text-red</#if>">
					${api.method }</strong>
								</td>
								<td>${api.url }</td>
								<td>${api.remark }</td>
								<td>
									<a class="button border-green button-little" href="${api.beanMethod}_${api.sort}.html" target="_blank">查看</a>
								</td>
							</tr>
							</#list>
						</#if>
					</#list>	
				</table>
</body>
</html>