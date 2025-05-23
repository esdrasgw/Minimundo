<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:url value="/registrarProduto" var="linkServletNovoProduto"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registrar Novo Produto</title>
</head>
<body>
	<form action="${linkServletNovoProduto}" method="post">
		<p>Nome: <input type="text" name="nome" /></p>
		<p>Descrição: <input type="text" name="descricao" /></p>
		<p>Volume: <input type="text" name="volume" /></p>
		<p>Preço: <input type="text" name="preco" /></p>
		<p>Peso: <input type="text" name="peso" /></p>

		<details>
			<summary>Produto já foi entregue?</summary>
			<p>
				<input type="checkbox" name="entregue" value="true" /> Sim
			</p>
			<p>
				Data de Entrega: <input type="date" name="dataEntrega" />
			</p>
		</details>

		<p><input type="submit" value="Enviar" /></p>
	</form>
	
	<a href = "index.html">Voltar ao início</a>
</body>
</html>
