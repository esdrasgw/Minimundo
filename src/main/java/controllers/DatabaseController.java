package controllers;

import java.sql.Connection;

import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import models.Insertable;
import models.Client.ClientModel;
import models.Client.TipoCliente;
import models.Client.TipoPessoa;
import models.Product.ProductModel;

import java.sql.ResultSet;

public class DatabaseController {

    public List<Insertable> SelectAllFromTable(Connection con, String tableName)
    {
        List<Insertable> list = new ArrayList<>();
        ClientModel client = null;
        ProductModel product = null;

        try {
            String query = "SELECT * FROM " + tableName + " ORDER BY 1";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            
            while (rs.next()) 
            {
            	if (tableName == "Cliente")
            	{
	            	String nome = rs.getString("nome");
	    			String cpfCnpj = rs.getString("cpfCnpj");
	    			String telefone = rs.getString("telefone"); 
	    			String endereco= rs.getString("endereco"); 
	    			TipoCliente tipoCliente = TipoCliente.valueOf(rs.getString("tipoCliente")); 
	    			TipoPessoa tipoPessoa = TipoPessoa.valueOf(rs.getString("tipoPessoa")); 
	    			int idProduto = rs.getInt("idProduto");
	            	client = new ClientModel(nome, cpfCnpj, telefone, endereco, tipoCliente, tipoPessoa, idProduto);
	            	
	                list.add(client);
            	}
            	else if (tableName == "Produto")
            	{
            		int productId = rs.getInt("id");
            		String nome = rs.getString("nome");
            		String descricao = rs.getString("descricao");
            		int volume = Integer.valueOf(rs.getString("volume"));
            		String precoString = rs.getString("preco").replace(",", ".");
            		float peso = Float.valueOf(rs.getString("peso"));
            		Boolean entregue = rs.getString("entregue").contains("t");
            		Date dataEntregue = null;
            		
            		if (entregue)
            		{
            			String dataEntregueString = rs.getString("dataEntrega");
            			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            			dataEntregue = sdf.parse(dataEntregueString);
            		}
            		
            		float preco = Float.valueOf(precoString.replace("R$",""));
            		
            		product = new ProductModel(nome, descricao, volume, preco, peso, entregue, dataEntregue);
            		product.setId(productId);
            		list.add(product);
            	}
            }
            return list;
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public void UpdateRow(Connection con, String tableName, String columnName, String newValue, String condition)
    {
        try {
            String query = String.format("UPDATE %s SET %s = '%s' WHERE %s", tableName, columnName, newValue, condition);
            Statement statement = con.createStatement();
            statement.executeUpdate(query);
            System.out.println("Linha atualizada com sucesso");
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void Insert(Connection con, Insertable model)
    {
    	String query = null;
    	try {
    		if (model instanceof ClientModel)
    		{
    			ClientModel client = (ClientModel)model;
    			
                String checkQuery = String.format("SELECT id FROM Produto WHERE id = %d", client.getIdProduto());
                Statement checkStatement = con.createStatement();
                ResultSet rs = checkStatement.executeQuery(checkQuery);
    			
    			if (!rs.next()) {
                    System.out.println("Produto não encontrado, por favor verifique o ID do produto.");
                    return;
                }

                if (client.getTipoPessoa() == TipoPessoa.FISICA && client.getCpfCnpj().length() != 11) {
                    System.out.println("CPF inválido, por favor verifique o CPF.");
                    return;
                } else if (client.getTipoPessoa() == TipoPessoa.JURIDICA && client.getCpfCnpj().length() != 14) {
                    System.out.println("CNPJ inválido, por favor verifique o CNPJ.");
                    return;
                }
                
                if (client.getTipoCliente() == TipoCliente.REMETENTE)
                {                	
                	query = String.format("INSERT INTO Cliente (nome, cpfCnpj, telefone, endereco, TipoCliente, TipoPessoa, idProduto) VALUES (%s);"
                    		+ "UPDATE Produto SET remetenteId = %d WHERE id = %d;", client.toString(), client.getId(), client.getIdProduto());
                }
                else 
                {
                	ProductArrived(con, client.getIdProduto(), new Date().toString());
                	query = String.format("INSERT INTO Cliente (nome, cpfCnpj, telefone, endereco, TipoCliente, TipoPessoa, idProduto) VALUES (%s);"
                		+ "UPDATE Produto SET destinatarioId = %d WHERE id = %d;", client.toString(), client.getId(), client.getIdProduto());
                	
               	}
    		}
            
    		else if (model instanceof ProductModel)
        	{
            	ProductModel product = (ProductModel)model;
            	query = String.format("INSERT INTO Produto (nome, descricao, volume, preco, peso, entregue, dataEntrega) VALUES (%s);", product.toString());
        	}
    		
    		Statement statement = con.createStatement();
    		statement.executeUpdate(query);
    		System.out.println(model.typeName() + " inserido!");
    		
    	} catch (Exception e) {
    		System.out.println(e);
    	}
    }
    
    public void ProductArrived(Connection con, int productId, String dataEntrega)
    {
        try {
                String checkQuery = String.format("SELECT entregue FROM Produto WHERE id = %d", productId);
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

            String query = String.format("UPDATE Produto SET entregue = true, dataEntrega = '" + dataEntrega + "' WHERE id = %d;", productId);
            Statement statement = con.createStatement();
            statement.executeUpdate(query);
            System.out.println("Produto chegou com sucesso");
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
