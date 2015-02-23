<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="languageTLD" prefix="language" %>

<script type="text/javascript">
	function executeRegistration(event){
		if(event.which == 13) { //Si es enter
			event.preventDefault();
			registerUser();      
		}
	}

	function registerUser(){
		$('#regButton')[0].disabled = true;
		showLoading("Registering user...");
		
		$.ajax({
			async: true,
			type: "POST",
			url: "Registration",
			data: { isAjax: "true", name: document.RegistrationForm.name.value, email: document.RegistrationForm.email.value, pwd: document.RegistrationForm.pwd.value },
			contentType: "application/x-www-form-urlencoded; charset=utf-8",
			dataType: "json",
			success: function (data) { taskComplete(); showResult("registryDiv", "regButton", data, false); },
			failure: function (errMsg) { taskComplete(); $('#regButton')[0].disabled = false; alert(errMsg); },
			error: function(XMLHttpRequest, textStatus, errorThrown) { taskComplete(); $('#regButton')[0].disabled = false; console.log("ERROR EN AJAX - " + textStatus+" - "+errorThrown);}
		});
	}
</script>
		
		
<!-- Capa de registro -->
<div id="registryDiv" class="form1 minWidth700px">

	<label><language:getMessage key="registration.title"/></label>
	
	<br><br>
	
	<form action="Registration" name="RegistrationForm" method="post" style="position: relative;">
	
		<input type="text" name="name" id="name" placeholder="<language:getMessage key="registration.name"/>"/>
		<input type="text" name="email" id="email" placeholder="<language:getMessage key="registration.email"/>"/>
		<input type="password" name="pwd" id="pwd" placeholder="<language:getMessage key="registration.pwd"/>" onkeypress="javascript:executeRegistration(event);"/>
	
		<button type="button" id="regButton" onclick="javascript:registerUser();" draggable="true"><language:getMessage key="registration.button.send"/></button>
		
	</form>
	
</div>
<!-- Fin de la capa de registro -->