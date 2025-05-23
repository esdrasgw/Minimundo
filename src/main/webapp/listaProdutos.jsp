<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List, models.Product.ProductModel" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lista de Produtos</title>
</head>
<body>
	<c:if test="${ not empty produto }">
		Produto ${produto.nome} cadastrado!
	</c:if>
	
	<h2>Lista de Produtos:</h2>

	<table border="1" cellpadding="5">
		<tr>
			<th>Id</th>
			<th>Nome</th>
			<th>Descrição</th>
			<th>Estoque</th>
			<th>Preço</th>
			<th>Peso</th>
			<th>Produto Entregue?</th>
			<th>Data de Entrega</th>
			<th>ID Cliente Destinatário</th>
			<th>ID Cliente Remetente</th>
		</tr>
			
		<c:forEach items="${listaProdutos}" var="produto">
			<tr>
				<td>${produto.idProduto}</td>
				<td>${produto.nome}</td>
				<td>${produto.descricao}</td>
				<td>${produto.volume}</td>
				<td>R$ ${produto.preco}</td>
				<td>${produto.peso} kg</td>
				<td><c:choose>
					<c:when test="${produto.entregue=='true'}">Sim</c:when>
					<c:otherwise>Não</c:otherwise>
				</c:choose>
				</td>
				<td>
					<fmt:formatDate value="${produto.dataEntrega}" pattern="dd/MM/yyyy" />
  				</td>
  				<td>${produto.destinatarioId}</td>
  				<td>${produto.remetenteId }</td>
			</tr>
		</c:forEach>
	</table>
	
	<a href = "index.html">Voltar ao início</a>
</body>
</html>
