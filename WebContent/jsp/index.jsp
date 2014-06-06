<%@ taglib uri="languageTLD" prefix="language" %>
<%@ taglib uri="htmlBuilderTLD" prefix="htmlBuilder" %>

<%@ page import="eu.fmm.sw.servlets.UserServlet"%>
<%@ page import="eu.fmm.sw.Constants"%>
<%@ page import="eu.fmm.sw.ddbb.beans.User"%>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<title><language:getMessage key="home.title"/></title>
		
		<!-- CSS -->
		<link rel="stylesheet" href="./css/style.css" type="text/css"/>
		
		<!-- Javascript -->
		<script type="text/javascript" src="//code.jquery.com/jquery-2.1.1.js"></script>
		<script type="text/javascript" src="//cdn.jquerytools.org/1.2.7/full/jquery.tools.min.js"></script>
		<script type="text/javascript" src="./js/common-functions.js"></script>
		<script type="text/javascript" src="./js/init.js"></script>
		
	</head>
	<body>
		<div id="bodyHeader">
			<%-- Cabecera --%>
			<div id="loadingDiv"><language:getMessage key="loading.primary.message"/></div>
		</div>
		
		<div id="bodyContent">
			<%--Cuerpo --%>
			
			<htmlBuilder:form bean="<%=new User()%>" action="<%=UserServlet.PATH_CREATE%>"/>
			
			<br>
			<button class="buttonNormal" onclick="javascript:showMessage('<%=Constants.JS_AJAX_RESULT_INFO %>', $().jquery);">Versi�n Jquery</button>
			
			<div id="result" title="<language:getMessage key="result.message.help"/>" onmouseover="javascript:enlargeDivVertically(this.id, 250);"></div>
			
		</div>
		
		<div id="bodyFooter">
			<%-- Pie --%>
		</div>
		
		<!-- La difeerncia entre estos dos includes son los siguientes:
			1. el primero integrar�a el c�digo de la jsp en esta jsp. De esa manera, si tuviesemos declaradas dos variables con el mismo nombre, una en cada jsp, dar�a error de compilaci�n
			2. el segundo no integrar�a el codigo sino que generar�a el html resultante del jsp y lo pegar�a directamente aqui, por lo que no habr�a problema de duplicidad de variables. Este segundo caso sin embargo es m�s costoso y lento -->
		<!-- < % @ include file="includeExample.jsp" % >
			 < j s p : include page="includeExample.jsp"></ j s p : include> -->		
		
	</body>
</html>