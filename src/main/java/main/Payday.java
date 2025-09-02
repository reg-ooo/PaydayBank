package main;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import pages.SplashScreen;
import pages.*;

public class Payday {
    public static String db,uname,pswd;
    public static Connection con;
    public static Statement st;
    public static ResultSet rs;

    public static void main(String[] args) {
        //Initalize SplashScreen before launch page
        SplashScreen splash = new SplashScreen();
        splash.showSplash(0);

        //RUNS LAUNCH PAGE AFTER INITIALIZATION
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new LoginPage();
            }
        });

        DBConnect();
    }
    
    public static void DBConnect(){
        db = "banksystem";
        uname = "admin";
        pswd = "]#KCzK9[MeePV8<6YN~o2YOj48dT";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://paydaybank.cluster-chqoc26c4kyy.ap-southeast-1.rds.amazonaws.com:3306/" + db + "?serverTimezone=UTC";
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
