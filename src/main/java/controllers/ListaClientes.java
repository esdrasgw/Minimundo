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

@WebServlet("/listaClientes")
public class ListaClientes extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DatabaseController db = new DatabaseController();
		Connection con = new ConnectionController().ConnectToDatabase(); 
		
		List<Insertable> list = db.SelectAllFromTable(con, "Cliente");
		
		request.setAttribute("listaClientes", list);
		
		RequestDispatcher rd = request.getRequestDispatcher("/listaClientes.jsp");
		rd.forward(request, response);
	}
}
