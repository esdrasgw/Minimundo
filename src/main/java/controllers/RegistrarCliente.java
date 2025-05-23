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

@WebServlet("/registrarCliente")
public class RegistrarCliente extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nome = request.getParameter("nome");
        String tipoPessoaStr = request.getParameter("tipoPessoa");
        String cpfCnpj = request.getParameter("cpfCnpj");
        String telefone = request.getParameter("telefone");
        String endereco = request.getParameter("endereco");
        String tipoClienteStr = request.getParameter("tipoCliente");
        String produtoStr = request.getParameter("produto");

        if (nome == null || nome.isEmpty() ||
            tipoPessoaStr == null || tipoPessoaStr.isEmpty() ||
            cpfCnpj == null || cpfCnpj.isEmpty() ||
            telefone == null || telefone.isEmpty() ||
            endereco == null || endereco.isEmpty() ||
            tipoClienteStr == null || tipoClienteStr.isEmpty() ||
            produtoStr == null || produtoStr.isEmpty()) {
            
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Todos os campos são obrigatórios.");
            return;
        }

        try {
            TipoPessoa tipoPessoa = TipoPessoa.valueOf(tipoPessoaStr);
            TipoCliente tipoCliente = TipoCliente.valueOf(tipoClienteStr);
            int productId = Integer.parseInt(produtoStr);

            ClientModel client = new ClientModel(nome, cpfCnpj, telefone, endereco, tipoCliente, tipoPessoa, productId);

            try (Connection con = new ConnectionController().ConnectToDatabase()) {
                DatabaseController db = new DatabaseController();
                db.Insert(con, client);
            }

            response.sendRedirect("listaClientes");

        } catch (IllegalArgumentException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Tipo de pessoa ou cliente inválido.");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro interno ao registrar cliente.");
        }
    }
}
