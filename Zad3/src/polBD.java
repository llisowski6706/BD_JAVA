
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Properties;
import java.lang.ClassNotFoundException;



public class polBD {
    public static Connection programPolaczenie() {
        //Polaczenie z baza danch SQL
        Connection pol = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://serwer2166393.home.pl/34037340_lis?autoReconnect=true&useSSL=false";
            String driverName = "com.mysql.jdbc.Driver";

            pol = DriverManager.getConnection(url, "34037340_lis","HasloTest123");

            System.out.println("Okk");
        }
        catch (ClassNotFoundException | SQLException ex){
            System.out.println("Ok");
            Logger.getLogger(polBD.class.getName()).log(Level.SEVERE, null, ex);
        }


        return pol;
    }

}