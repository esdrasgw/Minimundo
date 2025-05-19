package classes.database;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

public class DbFunctions {
    
    public Connection ConnectToDatabase(String dbname, String username, String password)
    {
        Connection con = null;

        try {
            Class.forName("org.postgresql.Driver");

            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + dbname, username, password);
            if (con != null) System.out.println("Connected");
            else System.out.println("Connection failed");
        } catch (Exception e) {
            System.out.println(e);   
        }
        return con;
    }

    public void SelectAllFromTable(Connection con, String tableName)
    {
        try {
            String query = "SELECT * FROM " + tableName + " ORDER BY 1";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) 
            {
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    System.out.print(rs.getString(i) + " ");
                }
                System.out.println();

            }
        } catch (Exception e) {
            System.out.println(e);
        }
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
}
