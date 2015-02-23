<%@page import="eu.fmm.sw.workers.SessionWorker"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="languageTLD" prefix="language" %>

<%@page import="eu.fmm.sw.beans.User"%>
<%@page import="eu.fmm.sw.SessionConstants"%>

<div id="userInfo" class="type1">
	<%
	User user = SessionWorker.getUser(request.getSession());
	if(!user.isAnonymous()){%>
	
		Hay usuario logado
		
		<button type="button" id="logoutButton" onclick="javascript:logoutUser();" draggable="true"><language:getMessage key="logout.button.send"/></button>
	<%
	}
	else {%>
		No ha accedido ningún usuario
	<%}%>
</div>