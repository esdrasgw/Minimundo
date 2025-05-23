<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List, models.Client.ClientModel" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lista de Clientes</title>
</head>
<body>
	<c:if test="${ not empty cliente }">
		Cliente ${cliente.nome} cadastrado!
	</c:if>
	
	<h2>Lista de Clientes:</h2>

	<table border="1" cellpadding="5">
		<tr>
			<th>Nome</th>
			<th>CPF/CNPJ</th>
			<th>Telefone</th>
			<th>Endereço</th>
			<th>Tipo Pessoa</th>
			<th>Tipo Cliente</th>
			<th>ID Produto</th>
		</tr>
		<c:forEach items="${listaClientes}" var="cliente">
			<tr>
				<td>${cliente.nome}</td>
				<td>${cliente.cpfCnpj}</td>
				<td>${cliente.telefone}</td>
				<td>${cliente.endereco}</td>
				<td>${cliente.tipoPessoa}</td>
				<td>${cliente.tipoCliente}</td>
				<td>${cliente.idProduto}</td>
			</tr>
		</c:forEach>
	</table>
	
	<a href = "index.html">Voltar ao início</a>
	
</body>
</html>
