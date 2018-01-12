/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package petcare.control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nguye
 */
public class MyMSSQLControl {
    public static final String DATABASE_NAME = "PETCARE";
    public static final String SERVER_NAME = "NACO";
    public static Connection getConnect(){
        Connection connection = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String connectionUrl = "jdbc:sqlserver://" + SERVER_NAME + ":1433;databaseName=" + DATABASE_NAME
                    +";integratedSecurity=true;";
            connection = DriverManager.getConnection(connectionUrl);
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException: "+ ex.toString());
            Logger.getLogger(MyMSSQLControl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            System.out.println("SQL Exception:" + ex.toString());
            Logger.getLogger(MyMSSQLControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
    }

}
