import classes.product.*;
import classes.users.*;
import classes.database.*;
import java.sql.Connection;
import java.util.Calendar;
import java.util.Date;
import java.math.BigDecimal;

public class App {
    public static void main(String[] args) throws Exception {
        
        DbFunctions db = new DbFunctions();
        ClientDbFunctions clientDb = new ClientDbFunctions();
        ProductDbFunctions productDb = new ProductDbFunctions();

        Connection con = db.ConnectToDatabase("postgres", "postgres", "1234");

        //clientDb.InsertClient(con, new Cliente("Teste", "12345111627801", "928134821", "Rua teste", TipoCliente.DESTINATARIO, TipoPessoa.JURIDICA, 1));
        //productDb.InsertProduct(con, new Produto("Produto Teste4", "Descricao do produto teste", 10, new BigDecimal(4),1.09, false, null));

        //productDb.ProductArrived(con, 3, "2023-10-10");
        db.SelectAllFromTable(con, "Produto");
    }
}
