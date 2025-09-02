package data;

import pages.*;
import main.Payday;

public class Users extends Payday{

    public void addUser(String username, String password){
        String query = String.format("INSERT INTO user(username, password) VALUES (\"%s\", \"%s\")", username, password);
        try {
            st.executeUpdate(query);
            System.out.println("User added successfully!");
            new LaunchPage();
        } catch (Exception e) {
            System.out.println("Adding user failed: " + e.getMessage());
        }
    }

    public void loginAccount (String username, String password){
        String query = String.format("SELECT * FROM user WHERE username = \"%s\" AND password = \"%s\"", username, password);
        try{
            rs = st.executeQuery(query);
            if(rs.next()){
                System.out.println("Login successful!");
                new LaunchPage();
            }
            else{
                System.out.println("Login failed!");
            }
        }catch (Exception e){
            System.out.println("Login Error: " + e.getMessage());
        }
    }
}
