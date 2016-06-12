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
    private Boolean autenticar = Boolean.FALSE ;
    private Usuario autenticarUsuario;
    
    private ControladorUsuario() {
        this.arrayListUsuario = new ArrayList<>();
    }

    public static ControladorUsuario getInstance() {
        if (instance == null) {
            instance = new ControladorUsuario();
        }
        return instance;
    }
    
    
    //Agregar Usuario
    public void agregarUsuario(boolean activo, String nombre, String clave) {
        String query = "INSERT INTO Usuario(activo, nombre, clave) VALUES('" + activo + "','" + nombre + "','" + clave + "'); ";
        SQLDatabaseConnection.getInstance().executeQuery(query);
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
                usuario.setUsuario(resultSet.getString("nombre"));
                usuario.setPassword(resultSet.getString("clave"));
                arrayListUsuario.add(usuario);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControladorUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        SQLDatabaseConnection.getInstance().desconectar();
        return arrayListUsuario;
    }
    
    //Modificar Usuarios
    public void modificarUsuario(boolean activo, String nombre, String clave, int idUsuario) {
        String query;
        if (activo) {
            query = "UPDATE Usuario SET activo = 1, nombre='" + nombre + "', clave='" + clave + "'WHERE idUsuario = " + idUsuario + ";";
        } else{
            query = "UPDATE Usuario SET activo = 0, nombre='" + nombre + "', clave='" + clave + "'WHERE idUsuario = " + idUsuario + ";";
        }
        SQLDatabaseConnection.getInstance().executeQuery(query);
    }
    
    //Eliminar Usuarios
    public void eliminarUsuario(int idUsuario) {
        String query = "DELETE FROM Usuario WHERE idUsuario = " +idUsuario;
        SQLDatabaseConnection.getInstance().executeQuery(query);
    }
    
    //Login Usuario 
    public Boolean autenticar(String nombre, String clave) {
        ResultSet resultSet = SQLDatabaseConnection.getInstance().query("SELECT * FROM Usuario WHERE nombre = '" + nombre + "' AND clave = '" + clave + "';");
        autenticar = Boolean.FALSE;
        try {
            while (resultSet.next()) {
                Usuario u = new Usuario();
                u.setIdUsuario(resultSet.getInt("idUsuario"));
                u.setUsuario(resultSet.getString("nombre"));
                u.setPassword(resultSet.getString("clave"));
                this.autenticarUsuario = u;
                autenticar = Boolean.TRUE;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControladorUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return autenticar;
    }

    public Boolean getAutenticar() {
        return autenticar;
    }

    public Usuario getAutenticarUsuario() {
        return autenticarUsuario;
    }
    
    public void desautenticar() {
        this.autenticar = Boolean.FALSE;
        this.autenticarUsuario = null;
    }
    
    //Buscar Usuario
    public ArrayList<Usuario> buscar(String usuario){
        ArrayList<Usuario> resultado = new ArrayList<>(); 
        for(Usuario search : getArrayList()){
             if(search.getUsuario().toUpperCase().contains(usuario.toUpperCase())){
                 resultado.add(search);
             }
         }
         return resultado;
    }
    
}
