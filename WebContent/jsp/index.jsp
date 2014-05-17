<%@ taglib uri="languageTLD" prefix="language" %>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<title><language:getMessage key="home.title"/></title>
		
		<!-- Javascript -->
		<script type="text/javascript" src="//code.jquery.com/jquery-2.1.1.js"></script>
		
		<!-- CSS -->
		<link rel="stylesheet" href="css/style.css" type="text/css"/>
	</head>
	<body>
		<div id="bodyHeader">Cabecera</div>
		<div id="bodyContent">
			Cuerpo<br>
			<button onclick="javascript:console.log($().jquery);">Versión Jquery</button>
		</div>
		<div id="bodyFooter">Pie</div>
	</body>
</html>