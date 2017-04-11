<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="layout  bg-inverse " style="padding-bottom:40px">
	<div class="bg-main" style="z-index: 9;position: fixed;width:100%;">
		<div class="navbar">
			<!--描述：LOGO及系统名称-->
			<div class="navbar-head">
				<button class="button icon-navicon" data-target="#navbar1"></button>
				<a href="${ctx }/api/list"><strong class="text-big hidden-l hidden-s">zapi</strong></a>
			</div>
			<!--描述：导航-->
			<div class="navbar-body nav-navicon" id="navbar1">
				<ul class="nav nav-inline nav-menu">
					<li><a class="icon-navicon " href="javascript:;">接口<span
							class="arrow"></span></a>
						<ul class="drop-menu" id="menu-ul">
							<li><a href="${ctx}/project/list">项目</a></li>
							<li><a href="${ctx}/folder/list">文件夹</a></li>
							<li><a href="${ctx}/api/list">API</a></li>
						</ul></li>
				</ul>
			</div>
		</div>
	</div>
</div>
<a class="icon-arrow-circle-up text-large" style="position: fixed;bottom: 20px;right: 20px" href="#"></a>