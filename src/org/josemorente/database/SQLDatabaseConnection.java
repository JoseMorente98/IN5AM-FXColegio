/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.josemorente.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.josemorente.utilidades.PropertiesLoader;

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
    private PreparedStatement preparedStatement;
            
    private SQLDatabaseConnection() {
        
    }
    
    public static SQLDatabaseConnection getInstance() {
        if (instance == null) {
            instance = new SQLDatabaseConnection();
        }
        return instance;
    }
    
    private void conectar() {
        HashMap<String, String> hashMap = PropertiesLoader.getInstance().load("conexion.properties");
         connectionString = "jdbc:sqlserver://" + hashMap.get("serverName") + ":" + hashMap.get("portNumber") + ";"
            + "databaseName=" + hashMap.get("databaseName")+ ";"
            + "user=" + hashMap.get("user") + ";"
            + "password=" + hashMap.get("password") + ";"
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
    
    public Connection getConnection() {
        conectar();
        return connection;
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
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
        } catch (SQLException ex) {
            Logger.getLogger(SQLDatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultSet;
    }
    
    public void executeQuery(String query) {
        conectar();
        try {
            preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.execute();
            resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                System.out.println("Generated: " +  resultSet.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SQLDatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
