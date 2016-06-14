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
import org.josemorente.beans.SeccionTecnica;
import org.josemorente.database.SQLDatabaseConnection;

/**
 *
 * @author José Morente
 */
public class ControladorSeccionTecnica {
    private static ControladorSeccionTecnica instance;
    private ArrayList<SeccionTecnica> arrayListTecnica;

    private ControladorSeccionTecnica() {
        this.arrayListTecnica = new ArrayList<>();
    }

    public static ControladorSeccionTecnica getInstance() {
        if (instance == null) {
            instance = new ControladorSeccionTecnica();
        }
        return instance;
    }
    
    //Agregar Seccion Tecnica
    public void agregarST(String nombre, String descripcion) {
        String query = "INSERT INTO SeccionTecnica(nombre, descripcion) VALUES('" + nombre + "', '" + descripcion + "');";
        SQLDatabaseConnection.getInstance().executeQuery(query);
    }
    
    //Mostrar Seccion Tecnica
    public ArrayList<SeccionTecnica> getArrayList() {
        arrayListTecnica.clear();
        ResultSet resultSet = SQLDatabaseConnection.getInstance().query("SELECT * FROM SeccionTecnica");
        try {
            while(resultSet.next()) {
                SeccionTecnica seccionTecnica = new SeccionTecnica();
                seccionTecnica.setIdSeccionTecnica(resultSet.getInt("idSeccionTecnica"));
                seccionTecnica.setNombre(resultSet.getString("nombre"));
                seccionTecnica.setDescripcion(resultSet.getString("descripcion"));
                arrayListTecnica.add(seccionTecnica);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControladorSeccionTecnica.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arrayListTecnica;
    }
    
    //Modificar Seccion Tecnica
    public void modificarST(String nombre, String descripcion, int idSeccionTecnica) {
        String query = "UPDATE SeccionTecnica SET nombre = '" + nombre + "', descripcion = '" + descripcion + "' WHERE idSeccionTecnica = " + idSeccionTecnica + ";";
        SQLDatabaseConnection.getInstance().executeQuery(query);
    }
    
    //Eliminar Seccion Tecnica
    public void eliminarST(int idSeccionTecnica) {
        String query = "DELETE SeccionTecnica WHERE idSeccionTecnica = " + idSeccionTecnica + ";";
        SQLDatabaseConnection.getInstance().executeQuery(query);
    }
    
    //Buscar Seccion Tecnica
    public SeccionTecnica buscar(int idSeccionTecnica) {
        for (SeccionTecnica seccionTecnica: arrayListTecnica) {
            if (seccionTecnica.getIdSeccionTecnica()== idSeccionTecnica) {
                return seccionTecnica;
            }
        }
        return null;
    }
    
    //Buscar Seccion Tecnica
    public ArrayList<SeccionTecnica> search(String nombre){
        ArrayList<SeccionTecnica> resultado = new ArrayList<>(); 
        for(SeccionTecnica seccionTecnica: getArrayList()){
             if(seccionTecnica.getNombre().toUpperCase().contains(nombre.toUpperCase())){
                 resultado.add(seccionTecnica);
             }
         }
         return resultado;
    }
}