package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Product.ProductModel;

@WebServlet("/registrarProduto")
public class RegistrarProduto extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
	        String nome = request.getParameter("nome");
	        String descricao = request.getParameter("descricao");

	        if (nome == null || nome.isEmpty() || descricao == null || descricao.isEmpty()) {
	            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Nome e descrição são obrigatórios.");
	            return;
	        }

	        int volume = Integer.parseInt(request.getParameter("volume"));
	        float preco = Float.parseFloat(request.getParameter("preco"));
	        float peso = Float.parseFloat(request.getParameter("peso"));
	        boolean entregue = Boolean.parseBoolean(request.getParameter("entregue"));

	        String dataEntregaString = request.getParameter("dataEntrega");
	        Date dataEntrega = null;
	        if (dataEntregaString != null && !dataEntregaString.isEmpty()) {
	            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	            dataEntrega = sdf.parse(dataEntregaString);
	        }
	        
	        DatabaseController db = new DatabaseController();
			Connection con = new ConnectionController().ConnectToDatabase();
			
			ProductModel produto = new ProductModel(nome, descricao, volume, preco, peso, entregue, dataEntrega);
			
			db.Insert(con, produto);
			
			response.sendRedirect("listaProdutos");
	    } catch (NumberFormatException | ParseException e) {
	        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Erro ao converter os parâmetros: " + e.getMessage());
	    } catch (Exception e) {
	        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro interno do servidor: " + e.getMessage());
	    }
	}
}
