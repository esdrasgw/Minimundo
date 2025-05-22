package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Insertable;
import models.Database.ConnectionModel;
import models.Database.DatabaseModel;

@WebServlet("/listaProdutos")
public class ListaProdutos extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DatabaseModel db = new DatabaseModel();
		Connection con = new ConnectionModel().ConnectToDatabase(); 
		
		System.out.println(con);
		List<Insertable> list = db.SelectAllFromTable(con, "Produto");
		
		request.setAttribute("listaProduto", list);
		
		RequestDispatcher rd = request.getRequestDispatcher("/listaProdutos.jsp");
		rd.forward(request, response);
	}
}
