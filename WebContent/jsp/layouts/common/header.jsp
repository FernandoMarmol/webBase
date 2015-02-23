<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="languageTLD" prefix="language" %>

<div id="bodyHeader">
	
	<div id="loadingDiv"><language:getMessage key="loading.primary.message"/></div>

	<span id="showTime"></span>
	
	<div style="position: absolute; top: 15%; width: 100%; line-height: 1; font-size: 30px; text-align: center;"><!-- QUÉ APLICACIÓN--></div>
	
	<%@include file="/jsp/common/userInfo.jsp" %>
	
</div>