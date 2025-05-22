<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<c:if test="${not empty empresa }">
		Empresa ${ empresa } cadastrada
	</c:if>
	
	<c:if test="${empty empresa }">
		Nenhuma empresa cadastrada.
	</c:if>
</body>
</html>