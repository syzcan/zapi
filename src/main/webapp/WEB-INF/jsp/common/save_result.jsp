<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/common/style.jsp"%>
<script type="text/javascript">
	if ("${msg}" == "success") {
		layer.load(1);
		//parent.layer.msg('操作成功');
		parent.window.location.reload();
	}
</script>
<c:if test="${msg!='success'}">
	<div style="color: red">${msg }</div>
</c:if>