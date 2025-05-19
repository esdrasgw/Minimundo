package classes.database;

import java.sql.Connection;
import java.sql.Statement;

import classes.users.Cliente;
import classes.users.TipoPessoa;

public class ClientDbFunctions {
    public void InsertClient(Connection con, Cliente cliente)
    {
        try {
            
            //#region Checks
            
            String checkQuery = String.format("SELECT %s FROM Produto WHERE id = %d", cliente.getIdProduto(), cliente.getIdProduto());
            Statement checkStatement = con.createStatement();
            checkStatement.executeQuery(checkQuery);
            if (!checkStatement.getResultSet().next()) {
                System.out.println("Produto não encontrado, por favor verifique o ID do produto.");
                return;
            }

            if (cliente.getCpfCnpj().length() != 11 && cliente.getTipoPessoa() == TipoPessoa.FISICA) {
                System.out.println("CPF inválido, por favor verifique o CPF.");
                return;
            } else if (cliente.getCpfCnpj().length() != 14 && cliente.getTipoPessoa() == TipoPessoa.JURIDICA) {
                System.out.println("CNPJ inválido, por favor verifique o CNPJ.");
                return;
            }

            //#endregion

            String query = String.format("INSERT INTO Cliente (nome, cpfCnpj, telefone, endereco, TipoCliente, TipoPessoa, idProduto) VALUES (%s);", cliente.toString());

            Statement statement = con.createStatement();
            statement.executeUpdate(query);
            System.out.println("Cliente inserido com sucesso");
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
