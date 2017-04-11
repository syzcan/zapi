<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="dialog open margin">
	<div class="dialog-head text-right">
		<input type="button" class="button border-main" value="返回" onclick="history.back()" />
	</div>
	<div class="dialog-body">
		<input type="hidden" name="id" value="${api.id }" id="dataId" />
		<input type="hidden" name="projectId" value="${api.projectId }" />
		<div class="form-group">
			<div class="label">
				<label>项目</label>
			</div>
			<div class="field">
				<input type="text" class="input input-auto" value="${project.name }" readonly="readonly" />
			</div>
		</div>
		<div class="form-group">
			<div class="label">
				<label>文件夹</label>
			</div>
			<div class="field">
				<select class="input input-auto" name="folderId">
					<c:forEach items="${folders }" var="folder">
						<option value="${folder.id }"
							${folder.id==api.folderId?'selected':'' }>${folder.name }</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="form-group">
			<div class="label">
				<label>名称</label>
			</div>
			<div class="field">
				<input type="text" class="input input-auto" name="name"
					value="${api.name }" data-validate="required:请填写信息" size="30" />
			</div>
		</div>
		<div class="form-group">
			<div class="label">
				<label>请求方式</label>
			</div>
			<div class="field">
				<select class="input input-auto" name="method">
					<option value="GET" ${'GET'==api.method?'selected':'' }>GET</option>
					<option value="POST" ${'POST'==api.method?'selected':'' }>POST</option>
					<option value="PUT" ${'PUT'==api.method?'selected':'' }>PUT</option>
					<option value="DELETE" ${'DELETE'==api.method?'selected':'' }>DELETE</option>
					<option value="" ${''==api.method?'selected':'' }>不限定</option>
				</select>
			</div>
		</div>
		<div class="form-group">
			<div class="label">
				<label>请求地址</label>
			</div>
			<div class="field">
				<input type="text" class="input input-auto" name="url"
					value="${api.url }" data-validate="required:请填写信息" size="50" />
				<button class="button bg-blue" type="button" onclick="importUrlParam()">参数</button>
			</div>
		</div>
		<div class="form-group">
			<div class="label">
				<label>描述</label>
			</div>
			<div class="field">
				<textarea class="input" id="remarkEditor" name="remark"
					style="width: 600px; height: 80px;">${api.remark }</textarea>
			</div>
		</div>
		<div class="form-group margin-bottom">
			<div class="label">
				<label>header参数</label>
			</div>
			<div class="field">
				<input type="hidden" name="headerParam" />
				<table class="table">
					<thead>
						<tr>
							<th width="150px"><span class="badge bg-green">参数名</span></th>
							<th width="100px">类型</th>
							<th width="80px">必需</th>
							<th>描述</th>
							<th width="50px"><a class="icon-plus-circle text-green" href="javascript:;" onclick="addParam('header_param')"></a></th>
						</tr>
					</thead>
					<tbody id="header_param">
						<c:forEach items="${api.headerParams }" var="parameter">
							<tr>
								<td><input type="text" class="input" data-name="name"
									value="${parameter.name }" /></td>
								<td><input type="text" class="input" data-name="dataType"
									value="${parameter.dataType }" /></td>
								<td><select class="input input-auto" data-name="required"><option
											value="0">否</option>
										<option value="1" ${parameter.required==1?'selected':'' }>是</option>
								</select></td>
								<td><input type="text" class="input" data-name="remark"
									value="${parameter.remark }" /></td>
								<td><a class="icon-minus-circle text-red"
									href="javascript:;"
									onclick="$(this).parent().parent().remove()"></a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<div class="form-group margin-bottom">
			<div class="label">
				<label>url参数</label>
			</div>
			<div class="field">
				<input type="hidden" name="urlParam" />
				<table class="table">
					<thead>
						<tr>
							<th width="150px"><span class="badge bg-green">参数名</span></th>
							<th width="100px">类型</th>
							<th width="80px">必需</th>
							<th>描述</th>
							<th width="50px"><a class="icon-plus-circle text-green" href="javascript:;" onclick="addParam('url_param')"></a></th>
						</tr>
					</thead>
					<tbody id="url_param">
						<c:forEach items="${api.urlParams }" var="parameter">
							<tr>
								<td><input type="text" class="input" data-name="name"
									value="${parameter.name }" /></td>
								<td><input type="text" class="input" data-name="dataType"
									value="${parameter.dataType }" /></td>
								<td><select class="input input-auto" data-name="required"><option
											value="0">否</option>
										<option value="1" ${parameter.required==1?'selected':'' }>是</option>
								</select></td>
								<td><input type="text" class="input" data-name="remark"
									value="${parameter.remark }" /></td>
								<td><a class="icon-minus-circle text-red"
									href="javascript:;"
									onclick="$(this).parent().parent().remove()"></a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<div class="form-group">
			<div class="label">
				<label>Request示例</label>
			</div>
			<div class="field">
				<input type="text" class="input" name="curl"
					value="${api.curl }" data-validate="required:请填写信息" size="30" />
			</div>
		</div>
		<div class="form-group margin-bottom">
			<div class="label">
				<label>Request Body</label>
			</div>
			<div class="field">
				<textarea class="input" name="requestBody"
					style="width: 600px; height: 200px;">${api.requestBody }</textarea>
			</div>
		</div>
		<div class="form-group">
			<div class="label">
				<label>从库导入参数</label>
			</div>
			<div class="field">
				<select class="input input-auto db_box" data-target="table_box1">
					<option value="">--数据源--</option>
					<c:forEach items="${configData.dbs }" var="db" varStatus="vs">
						<option value="${db['dbname'] }">${db['dbname'] }</option>
					</c:forEach>
				</select> <select id="table_box1" class="input input-auto">
					<option value="">--数据表--</option>
				</select>
				<button class="button bg-blue" type="button" onclick="importParam('table_box1','body_param')">导入</button>
			</div>
		</div>
		<div class="form-group margin-bottom">
			<div class="label">
				<label>body参数</label>
			</div>
			<div class="field">
				<input type="hidden" name="bodyParam" />
				<table class="table">
					<thead>
						<tr>
							<th width="150px"><span class="badge bg-green">参数名</span></th>
							<th width="100px">类型</th>
							<th width="80px">必需</th>
							<th>描述</th>
							<th width="50px"><a class="icon-plus-circle text-green" href="javascript:;" onclick="addParam('body_param')"></a></th>
						</tr>
					</thead>
					<tbody id="body_param">
						<c:forEach items="${api.bodyParams }" var="parameter">
							<tr>
								<td><input type="text" class="input" data-name="name"
									value="${parameter.name }" /></td>
								<td><input type="text" class="input" data-name="dataType"
									value="${parameter.dataType }" /></td>
								<td><select class="input input-auto" data-name="required"><option
											value="0">否</option>
										<option value="1" ${parameter.required==1?'selected':'' }>是</option>
								</select></td>
								<td><input type="text" class="input" data-name="remark"
									value="${parameter.remark }" /></td>
								<td><a class="icon-minus-circle text-red"
									href="javascript:;"
									onclick="$(this).parent().parent().remove()"></a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<div class="form-group">
			<div class="label">
				<label>Response示例</label>
			</div>
			<div class="field">
				<textarea class="input" id="remarkEditor" name="response"
					style="width: 600px; height: 200px;">${api.response }</textarea>
			</div>
		</div>
		<div class="form-group">
			<div class="label">
				<label>从库导入参数</label>
			</div>
			<div class="field">
				<select class="input input-auto db_box" data-target="table_box2">
					<option value="">--数据源--</option>
					<c:forEach items="${configData.dbs }" var="db" varStatus="vs">
						<option value="${db['dbname'] }">${db['dbname'] }</option>
					</c:forEach>
				</select> <select id="table_box2" class="input input-auto">
					<option value="">--数据表--</option>
				</select>
				<button class="button bg-blue" type="button" onclick="importParam('table_box2','result_param')">导入</button>
			</div>
		</div>
		<div class="form-group margin-bottom">
			<div class="label">
				<label>返回参数</label>
			</div>
			<div class="field">
				<input type="hidden" name="resultParam" />
				<table class="table">
					<thead>
						<tr>
							<th width="150px"><span class="badge bg-green">参数名</span></th>
							<th width="100px">类型</th>
							<th width="80px">必需</th>
							<th>描述</th>
							<th width="50px"><a class="icon-plus-circle text-green" href="javascript:;" onclick="addParam('result_param')"></a></th>
						</tr>
					</thead>
					<tbody id="result_param">
						<c:forEach items="${api.resultParams }" var="parameter">
							<tr>
								<td><input type="text" class="input" data-name="name"
									value="${parameter.name }" /></td>
								<td><input type="text" class="input" data-name="dataType"
									value="${parameter.dataType }" /></td>
								<td><select class="input input-auto" data-name="required"><option
											value="0">否</option>
										<option value="1" ${parameter.required==1?'selected':'' }>是</option>
								</select></td>
								<td><input type="text" class="input" data-name="remark"
									value="${parameter.remark }" /></td>
								<td><a class="icon-minus-circle text-red"
									href="javascript:;"
									onclick="$(this).parent().parent().remove()"></a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<div class="dialog-foot">
			<button class="button bg-green" type="button" onclick="saveApi(this)">保存</button>
		</div>
	</div>
</div>
<script>
	$(function() {
		$('.db_box[data-target]').change(function() {
			changeDatasource($(this));
		});
	});
	function changeDatasource($db) {
		var html = '<option value="">--数据表--</option>';
		$.ajax({
			url : '${ctx}/api/' + $db.val() + '/tables.json',
			dataType : 'json',
			success : function(data) {
				$.each(data, function(i, n) {
					var comment = n.comment;
					if(comment==null){
						comment = '';
					}
					comment = comment.substring(0,20);
					if(comment!=''){
						comment = ' - ' + comment;
					}
					html += '<option value="'+n.tableName+'">' + n.tableName + comment + '</option>';
				});
				$('#'+$db.attr('data-target')).html(html);
			},
			error : function() {
				$('#'+$db.attr('data-target')).html(html);
			}
		});
	}

	function importParam(tableId,nodeId) {
		var dbname = $('.db_box[data-target="'+tableId+'"]').val();
		var tableName = $('#'+tableId).val();
		if (dbname == '' || tableName == '') {
			layer.alert('请选择对应的数据库和表');
			return;
		}
		var html = '';
		$.ajax({
			url : '${ctx}/api/' + dbname + '/tables/' + tableName + '.json',
			dataType : 'json',
			success : function(data) {
				$.each(data, function(i, n) {
					html += '<tr>';
					html += '<td><input type="text" class="input" data-name="name" value="' + n.field + '"/></td>';
					html += '<td><input type="text" class="input" data-name="dataType" value="' + n.type+ '"/></td>';
					html += '<td><select class="input input-auto" data-name="required"><option value="0">否</option><option value="1"' + (n.canNull == 'NO' ? 'selected' : '')+ '>是</option></select></td>';
					html += '<td><input type="text" class="input" data-name="remark" value="' + n.remark + '"/></td>';
					html += '<td><a class="icon-minus-circle text-red" href="javascript:;" onclick="$(this).parent().parent().remove()"></a></td></tr>';
				});
				$('#'+nodeId).append(html);
			}
		});
	}
	
	function importUrlParam(){
		$.each($('input[name="url"]').val().replace('http://','').replace('.json','').split('/'),function(i,n){
			if(n.indexOf(':')>-1){
				var flag = true;
				$.each($('#url_param input'),function(i,v){
					if($(v).val()==n.replace(':','')){
						flag = false;
					}
				});
				if(flag){
					addParam('url_param');
					$('#url_param tr:eq(-1) input').first().val(n.replace(':',''));
					$('#url_param tr:eq(-1) select').val('1');
					if(n.replace(':','')=='mpi'){
						$('#url_param tr:eq(-1) input').last().val('当前用户唯一标识mpi');
					}
					if(n.replace(':','')=='id'){
						$('#url_param tr:eq(-1) input').last().val('随访流水号');
					}
					if(n.replace(':','')=='djjgDm'){
						$('#url_param tr:eq(-1) input').last().val('登记机构代码');
					}
				}
			}
		});
	}
	
	function addParam(id){
		if(id=='header_param'){
			$('#'+id).append('<tr><td><input type="text" class="input" value="token" data-name="name"/></td><td><input type="text" class="input" value="String" data-name="dataType"/></td><td><select class="input input-auto" data-name="required"><option value="0">否</option><option value="1" selected>是</option></select></td><td><input type="text" class="input" value="用户授权码" data-name="remark"/></td><td><a class="icon-minus-circle text-red" href="javascript:;" onclick="$(this).parent().parent().remove()"></a></td></tr>');
			return;
		}
		$('#'+id).append('<tr><td><input type="text" class="input" data-name="name"/></td><td><input type="text" class="input" value="String" data-name="dataType"/></td><td><select class="input input-auto" data-name="required"><option value="0">否</option><option value="1">是</option></select></td><td><input type="text" class="input" data-name="remark"/></td><td><a class="icon-minus-circle text-red" href="javascript:;" onclick="$(this).parent().parent().remove()"></a></td></tr>');
	}
	
	function saveApi(obj){
		//组装参数
		$('input[name="headerParam"]').val(packParam('header_param'));
		$('input[name="urlParam"]').val(packParam('url_param'));
		$('input[name="bodyParam"]').val(packParam('body_param'));
		$('input[name="resultParam"]').val(packParam('result_param'));
		$(obj).parents('form').submit();
	}
	
	function packParam(id_param){
		var params = '[';
		$.each($('#'+id_param+' tr'),function(i,n){
			if($(n).find(':input').first().val()!=''){
				var param = '{';
				$.each($(n).find(':input'),function(j,k){
					param += '"'+$(k).attr('data-name')+'":"'+$(k).val()+'",';
				});
				param = cutEndComma(param);
				param += '},';
				params += param;
			}
		});
		params = cutEndComma(params);
		params += ']';
		return params;
	}
	
	//删除结尾的逗号
	function cutEndComma(str){
		if(str.charAt(str.length-1)==','){
			str = str.substr(0,str.length-1);
		}
		return str;
	}
</script>