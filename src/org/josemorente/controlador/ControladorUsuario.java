/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.josemorente.controlador;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.josemorente.beans.Usuario;
import org.josemorente.database.SQLDatabaseConnection;

/**
 *
 * @author José Morente
 */
public class ControladorUsuario {
    public static ControladorUsuario instance;
    private ArrayList<Usuario> arrayListUsuario;
    
    private ControladorUsuario() {
        this.arrayListUsuario = new ArrayList<>();
    }

    public static ControladorUsuario getInstance() {
        if (instance == null) {
            instance = new ControladorUsuario();
        }
        return instance;
    }
    
    //Mostrar Usuario
    public ArrayList<Usuario> getArrayList() {
        arrayListUsuario.clear();
        ResultSet resultSet = SQLDatabaseConnection.getInstance().query("SELECT * FROM Usuario");
        try {
            while(resultSet.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(resultSet.getInt("idUsuario"));
                usuario.setActivo(resultSet.getBoolean("activo"));
                usuario.setUsuario(resultSet.getString("usuario"));
                usuario.setPassword(resultSet.getString("password"));
                arrayListUsuario.add(usuario);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControladorUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        SQLDatabaseConnection.getInstance().desconectar();
        return arrayListUsuario;
    }
    
    //Agregar Usuario
    public void agregarUsuario(boolean activo, String usuario, String password) {
        SQLDatabaseConnection.getInstance().executeQuery("INSERT INTO Usuario(activo, usuario, password) VALUES('" + activo + " ',' " + usuario + " ',' " + password + " '); ");
    }
    
    //Modificar Usuarios
    
    //Eliminar Usuarios
    
    
}
