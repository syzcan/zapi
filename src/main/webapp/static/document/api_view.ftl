<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title>月光边境</title>
<meta name="keywords" content="关键词" />
<meta name="description" content="描述" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="pintuer.css" />
<style type="text/css">
pre {
	font-size: 12px;
	padding: 0px;
	margin: 0px;
	background: #fafafa;
}

pre code {
	font-family: Menlo, Monaco, Consolas, "Courier New",
		monospace color : #333;
}
</style>
</head>
<body>
	<div class="container">
		<div class="panel-head text-center">
			<h1>${folder.remark }</h1>
		</div>
		<table class="table">
			<tr>
				<td colspan="2">${api.name }</td>
			</tr>
			<tr>
				<td width="150px">详细说明</td>
				<td>${api.remark }</td>
			</tr>
			<tr>
				<td>请求方式</td>
				<td><strong class="font-weight
					<#if api.method=='GET'> text-green</#if><#if api.method=='POST'> text-yellow</#if>
					<#if api.method=='PUT'> text-blue</#if><#if api.method=='DELETE'> text-red</#if>">
					${api.method }</strong></td>
			</tr>
			<tr>
				<td>请求地址</td>
				<td>${api.url }</td>
			</tr>
		</table>

		<#if (api.headerParams?size>0) >
		<table class="margin-big-top table table-hover">
			<thead>
				<tr>
					<th width="150px"><span class="badge bg-green">header参数名</span></th>
					<th width="100px">类型</th>
					<th width="80px">必需</th>
					<th>描述</th>
				</tr>
			</thead>
			<tbody>
				<#list api.headerParams as parameter>
					<tr>
						<td>${parameter.name }</td>
						<td>${parameter.dataType }</td>
						<td><#if parameter.required=='1'>是</#if><#if parameter.required=='0'>否</#if></td>
						<td>${parameter.remark }</td>
					</tr>
				</#list>
			</tbody>
		</table>
		</#if>
		<#if (api.urlParams?size>0) >
		<table class="margin-big-top table table-hover">
			<thead>
				<tr>
					<th width="150px"><span class="badge bg-green">url参数名</span></th>
					<th width="100px">类型</th>
					<th width="80px">必需</th>
					<th>描述</th>
				</tr>
			</thead>
			<tbody>
				<#list api.urlParams as parameter>
					<tr>
						<td>${parameter.name }</td>
						<td>${parameter.dataType }</td>
						<td><#if parameter.required=='1'>是</#if><#if parameter.required=='0'>否</#if></td>
						<td>${parameter.remark }</td>
					</tr>
				</#list>
			</tbody>
		</table>
		</#if>
		<#if (api.bodyParams?size>0) >
		<table class="margin-big-top table table-hover">
			<thead>
				<tr>
					<th width="150px"><span class="badge bg-green">body参数名</span></th>
					<th width="100px">类型</th>
					<th width="80px">必需</th>
					<th>描述</th>
				</tr>
			</thead>
			<tbody>
				<#list api.bodyParams as parameter>
					<tr>
						<td>${parameter.name }</td>
						<td>${parameter.dataType }</td>
						<td><#if parameter.required=='1'>是</#if><#if parameter.required=='0'>否</#if></td>
						<td>${parameter.remark }</td>
					</tr>
				</#list>
			</tbody>
		</table>
		</#if>

		<div class="margin-top padding">返回示例：</div>
		<pre class="border padding"><code>${api.response }</code></pre>
		<#if (api.resultParams?size>0) >
		<table class="margin-big-top table table-hover">
			<thead>
				<tr>
					<th width="150px"><span class="badge bg-main">返回参数名</span></th>
					<th width="100px">类型</th>
					<th width="80px">必需</th>
					<th>描述</th>
				</tr>
			</thead>
			<tbody>
				<#list api.resultParams as parameter>
					<tr>
						<td>${parameter.name }</td>
						<td>${parameter.dataType }</td>
						<td><#if parameter.required=='1'>是</#if><#if parameter.required=='0'>否</#if></td>
						<td>${parameter.remark }</td>
					</tr>
				</#list>
			</tbody>
		</table>
		</#if>
	</div>
</body>
</html>