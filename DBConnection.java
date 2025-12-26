import java.sql.*;
public class DBConnection{
    private static final String url="jdbc:mysql://localhost:3306/stockdb";
    private static final String user="root";
    private static final String password="";

    public static Connection getConnection() {
        try{

            Class.forName("com.mysql.cj.jdbc.Driver");
        
        return DriverManager.getConnection(url, user, password);
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

}