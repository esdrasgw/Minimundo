package classes.database;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

import classes.product.Produto;

public class ProductDbFunctions {
    public void InsertProduct(Connection con, Produto produto)
    {
        try {
            String query = String.format("INSERT INTO Produto (nome, descricao, volume, preco, peso, entregue, dataEntrega) VALUES (%s);", produto.toString());

            Statement statement = con.createStatement();
            
            statement.executeUpdate(query);
            System.out.println("Produto inserido com sucesso");
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void ProductArrived(Connection con, int idProduto, String dataEntrega)
    {
        try {
            //#region Checks

                String checkQuery = String.format("SELECT entregue FROM Produto WHERE id = %d", idProduto);
                Statement checkStatement = con.createStatement();
                ResultSet result = checkStatement.executeQuery(checkQuery);
                
                while (result.next())
                {
                    boolean isEntregue = result.getBoolean("entregue");
                    if (isEntregue) {
                        System.out.println("Produto já entregue, por favor verifique o ID do produto.");
                        return;
                    }
                }

            //#endregion

            String query = String.format("UPDATE Produto SET entregue = true, dataEntrega = '" + dataEntrega + "' WHERE id = %d;", idProduto);
            Statement statement = con.createStatement();
            statement.executeUpdate(query);
            System.out.println("Produto chegou com sucesso");
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
