package controllers;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Client.ClientModel;
import models.Client.TipoCliente;
import models.Client.TipoPessoa;
import models.Database.ConnectionModel;
import models.Database.DatabaseModel;

@WebServlet("/registrarCliente")
public class RegistrarCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome = request.getParameter("nome");
		TipoPessoa tipoPessoa = TipoPessoa.valueOf(request.getParameter("tipoPessoa"));
		String cpfCnpj = request.getParameter("cpfCnpj");
		String telefone = request.getParameter("telefone");
		String endereco = request.getParameter("endereco");
		TipoCliente tipoCliente = TipoCliente.valueOf(request.getParameter("tipoCliente"));
		int productId = Integer.valueOf(request.getParameter("produto"));
		
		DatabaseModel db = new DatabaseModel();
		Connection con = new ConnectionModel().ConnectToDatabase();
		
		ClientModel client = new ClientModel(nome, cpfCnpj, telefone, endereco, tipoCliente, tipoPessoa, productId);
		
		db.Insert(con, client);
		
		response.sendRedirect("listaClientes");
	}
}
