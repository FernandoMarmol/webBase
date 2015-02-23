<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="languageTLD" prefix="language" %>

<%@ page import="eu.fmm.sw.workers.SessionWorker"%>
<%@ page import="eu.fmm.sw.beans.User"%>

<%
	User user = SessionWorker.getUser(request.getSession());
%>

<script type="text/javascript">
	function executeLogin(event){
		if(event.which == 13) { //Si es enter
			event.preventDefault();
			loginUser();      
		}
	}
	
	function loginUser(){
		$("#loginButton")[0].disabled = true;
		showLoading("User logging...");
		
		$.ajax({
			async: true,
			type: "POST",
			url: "Login",
			data: { isAjax: "true", email: document.LoginForm.emailLogin.value, pwd: document.LoginForm.pwdLogin.value },
			contentType: "application/x-www-form-urlencoded; charset=utf-8",
			dataType: "json",
			success: function (data) { taskComplete(); showResult("loginDiv", "loginButton", data, true); },
			failure: function (errMsg) { taskComplete(); $("#loginButton")[0].disabled = false; console.log(errMsg); },
			error: function(XMLHttpRequest, textStatus, errorThrown) { taskComplete(); $("#loginButton")[0].disabled = false; console.log("ERROR EN AJAX - " + textStatus+" - "+errorThrown);}
		});
	}
	
	var isRegistrationLoaded = false;
	function showRegistration(){
		if(!isRegistrationLoaded){
			showLoading("Showing Registration Info...");
			
			$.ajax({
				async: true,
				url: "RegistrationJSP",
				success: function (data) { isRegistrationLoaded = true; taskComplete(); $("#loadFunctionality").append(data); $("#loadFunctionality").animate({ opacity: "show" }, 500, function() { });},
				failure: function (errMsg) { taskComplete(); console.log(errMsg); },
				error: function(XMLHttpRequest, textStatus, errorThrown) {console.log("ERROR EN AJAX - " + textStatus+" - "+errorThrown);}
			});
		}
	}
</script>

<!-- Capa de Login -->
<div id="loginDiv" class="form1">
	<%if(user.isAnonymous()){ %>
		<label><language:getMessage key="login.title"/></label>
		
		<br><br>
		
		<form action="LoginServlet" name="LoginForm" method="post">
		
			<input type="text" name="emailLogin" id="emailLogin" placeholder="<language:getMessage key="login.email"/>"/>
			<input type="password" name="pwdLogin" id="pwdLogin" placeholder="<language:getMessage key="login.pwd"/>" onkeypress="javascript:executeLogin(event);"/>
		
			<button type="button" id="loginButton" onclick="javascript:loginUser();" draggable="true"><language:getMessage key="login.button.send"/></button>
			
			<div id="noRegisteredDiv"><span class="subText">¿No tiene cuenta y desea registrarse?</span>&nbsp;<button type="button" class="subButton" id="regButton" onclick="javascript:showRegistration();" draggable="true"><language:getMessage key="registration.button"/></button></div>
			<div id="loadFunctionality" style="display: none;"></div>
		</form>
	<%}
	else{%>
		<label><language:getMessage key="login.message.user.already.logged"/></label>
		
		<button type="button" id="logoutButton" onclick="javascript:logoutUser();" draggable="true"><language:getMessage key="logout.button.send"/></button>
	<%}%>
	
	<%@include file="/jsp/layouts/common/result.jsp" %>
	
</div>