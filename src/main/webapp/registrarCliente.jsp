<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:url value="/registrarCliente" var="linkServletNovoCliente"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registrar Novo Cliente</title>
</head>
<body>
	<form action="${linkServletNovoCliente}" method="post">
		<p>Nome: <input type="text" name="nome"/></p>
		<p>Pessoa Física ou Jurídica? <select name="tipoPessoa">
			<option value="FISICA">Pessoa Física </option> 
			<option value="JURIDICA">Pessoa Jurídica</option>
		</select></p>
		<p>CPF/CNPJ: <input type=text name="cpfCnpj"/></p>
		<p>Telefone: <input type=text name="telefone"/></p>
		<p>Endereço: <input type="text" name="endereco"/></p>
		<p>Está recebendo ou enviando? : <select name="tipoCliente">
			<option value="DESTINATARIO">Recebendo </option>
			<option value="REMETENTE">Enviando </option>
		</select></p>
		<p>ID do Produto <input type=text name="produto"/></p>
		<p><input type="submit" value="Enviar"/></p>
	</form>
	
	<a href = "index.html">Voltar ao início</a>
</body>
</html>