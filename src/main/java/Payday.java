import java.sql.*;

public class Payday {
    static String db,uname,pswd;
    static Connection con;
    static Statement st;
    static ResultSet rs;
    public static void main(String[] args) {
        new LaunchPage();
        DBConnect();
//        new LoginPage();

    }
    
    public static void DBConnect(){
        db = "banksystem";
        uname = "root";
        pswd = "regorego2006";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://127.0.0.1:3308/" + db + "?serverTimezone=UTC";
            con = DriverManager.getConnection(url, uname, pswd);
            st = con.createStatement();

            System.out.println("Connected to MySQL database successfully!");

        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Failed to connect to database: " + e.getMessage());
        }
    }
}
