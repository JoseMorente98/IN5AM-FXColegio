/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.josemorente.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author José Morentes
 */
public class SQLDatabaseConnection {
    public static SQLDatabaseConnection instance;
    private String connectionString;
            
    private SQLDatabaseConnection() {
        
    }
    
    public static SQLDatabaseConnection getInstance() {
        if (instance == null) {
            instance = new SQLDatabaseConnection();
        }
        return instance;
    }
    
    public void conectar() {
        connectionString = "jdbc:sqlserver://localhost:1433;"
            +"database=localhost;"
            +"user=developer;"
            +"password=developer;"
            +"encryp=true;"
            +"trustServerCertificate=false;"
            +"hostNameInCertificate=*.database.windows.net;"
            +"loginTimeout=30;";
            
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        
        try {
            connection = DriverManager.getConnection(connectionString);
            
            String selectSql;
            selectSql = "SELECT * FROM Profesor;";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(selectSql);
            
            while (resultSet.next()) {
                System.out.println(resultSet.getString(2) + " "
                    + resultSet.getString(3));
            }
            
            } catch (SQLException ex) {
            Logger.getLogger(SQLDatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (resultSet != null) try {
                resultSet.close();
            } catch (Exception e){
                
            }
            if (statement != null) try {
                statement.close();
            } catch (Exception e){
                
            }
            if (connection != null) try {
                connection.close();
            } catch (Exception e){
                
            }
        }
    }
}
