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
import org.josemorente.beans.SeccionAcademica;
import org.josemorente.beans.SeccionTecnica;
import org.josemorente.database.SQLDatabaseConnection;

/**
 *
 * @author José Morente
 */
public class ControladorSeccionAcademica {
    private static ControladorSeccionAcademica instance;
    private ArrayList<SeccionAcademica> arrayListSA; 
    
    private ControladorSeccionAcademica() {
        this.arrayListSA = new ArrayList<>();
    }

    public static ControladorSeccionAcademica getInstance() {
        if (instance == null) {
            instance = new ControladorSeccionAcademica();
        }
        return instance;
    }
    
    //Agregar Seccion Academica
    public void agregarSA(String nombre, String descripcion) {
        String query = "INSERT INTO SeccionAcademica(nombre, descripcion) VALUES('" + nombre + "', '" + descripcion + "');";
        SQLDatabaseConnection.getInstance().executeQuery(query);
    }

    //Mostrar Seccion Academica
    public ArrayList<SeccionAcademica> getArrayList() {
        arrayListSA.clear();
        ResultSet resultSet = SQLDatabaseConnection.getInstance().query("SELECT * FROM SeccionAcademica");
        try {
            while (resultSet.next()) {
                SeccionAcademica seccionAcademica = new SeccionAcademica();
                seccionAcademica.setIdSeccionAcademica(resultSet.getInt("idSeccionAcademica"));
                seccionAcademica.setNombre(resultSet.getString("nombre"));
                seccionAcademica.setDescripcion(resultSet.getString("descripcion"));
                arrayListSA.add(seccionAcademica);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControladorSeccionAcademica.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arrayListSA;
    }
    
    //Modificar Seccion Academica
    public void modificarSA(String nombre, String descripcion, int idSeccionAcademica) {
        String query = "UPDATE SeccionAcademica SET nombre = '" + nombre + "', descripcion = '" + descripcion + "' WHERE idSeccionAcademica = " + idSeccionAcademica + ";";
        SQLDatabaseConnection.getInstance().executeQuery(query);
    }
    
    //Eliminar Seccion Academica
    public void eliminarSA(int idSeccionAcademica) {
        String query = "DELETE SeccionAcademica WHERE idSeccionAcademica = " + idSeccionAcademica + ";";
        SQLDatabaseConnection.getInstance().executeQuery(query);
    }
    
    //Buscar Seccion Academica
    public SeccionAcademica buscar(int idSeccionAcademica) {
        for (SeccionAcademica seccionAcademica: arrayListSA) {
            if (seccionAcademica.getIdSeccionAcademica() == idSeccionAcademica) {
                return seccionAcademica;
            }
        }
        return null;
    }
    
    //Buscar Seccion Tecnica
    public ArrayList<SeccionAcademica> search(String nombre){
        ArrayList<SeccionAcademica> resultado = new ArrayList<>(); 
        for(SeccionAcademica seccionAcademica: getArrayList()){
             if(seccionAcademica.getNombre().toUpperCase().contains(nombre.toUpperCase())){
                 resultado.add(seccionAcademica);
             }
         }
         return resultado;
    }
}