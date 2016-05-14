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
 * @author José Morente
 */
public class SQLDatabaseConnection {
    public static SQLDatabaseConnection instance;
    private String connectionString;
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
            
    private SQLDatabaseConnection() {
        
    }
    
    public static SQLDatabaseConnection getInstance() {
        if (instance == null) {
            instance = new SQLDatabaseConnection();
        }
        return instance;
    }
    
    private void conectar() {
        connectionString = "jdbc:sqlserver://localhost:1433;"
            +"database=FXColegio;"
            +"user=developer;"
            +"password=developer;"
            +"encryp=false;"
            +"trustServerCertificate=false;"
            +"hostNameInCertificate=*.database.windows.net;"
            +"loginTimeout=30;";
        
        try {
            connection = DriverManager.getConnection(connectionString);
            } catch (SQLException ex) {
            Logger.getLogger(SQLDatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void desconectar() {
        if (resultSet != null) try {
            resultSet.close();
        } catch (Exception e){}
        if (statement != null) try {
            statement.close();
        } catch (Exception e){}
        if (connection != null) try {
            connection.close();
        } catch (Exception e){}
        
    }
    
    public ResultSet query(String query) {
        conectar();
        try {
            String selectSql = "SELECT * FROM Usuario;";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            desconectar();
        } catch (SQLException ex) {
            Logger.getLogger(SQLDatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            
        }
        return resultSet;
    }
    
    
}
