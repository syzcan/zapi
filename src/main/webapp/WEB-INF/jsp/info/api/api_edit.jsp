<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/common/style.jsp"%>
<%@ include file="/WEB-INF/jsp/common/nav.jsp"%>
<form action="${ctx}/api/edit" class="form-x" method="post">
	<%@ include file="api_form.jsp"%>
</form>