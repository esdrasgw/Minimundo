<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List,exemplos.Empresa"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
	<body>
	<c:if test="${ not empty empresa }">
		Empresa ${ empresa } cadastrada!
	</c:if>
	
	Lista de Empresas: <br/>
	
	<ul>
		<c:forEach items="${listaEmpresas}" var="empresa">	
			<li> ${ empresa.nome } - <fmt:formatDate value="${empresa.dataAbertura }" pattern="dd/MM/yyyy"/> 
			<a href="minimundo/mostraEmpresa?id=${empresa.id}">Editar</a>
			<a href="/minimundo/removeEmpresa?id=${empresa.id}">Remover</a>
			</li>
		</c:forEach>
	</ul>
	</body>
</html>