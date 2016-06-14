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
import org.josemorente.beans.Grado;
import org.josemorente.database.SQLDatabaseConnection;

/**
 *
 * @author José Morente
 */
public class ControladorGrado {
    private static ControladorGrado instance;
    private ArrayList<Grado> arrayListGrado;

    private ControladorGrado() {
        this.arrayListGrado = new ArrayList<>();
    }

    public static ControladorGrado getInstance() {
        if (instance == null) {
            instance = new ControladorGrado();
        }
        return instance;
    }
    
    //Agregar Grado
    public void agregarGrado(String nombre, String descripcion) {
        String query = "INSERT INTO Grado(nombre, descripcion) VALUES('" + nombre + "', '" + descripcion + "');";
        SQLDatabaseConnection.getInstance().executeQuery(query);
    }

    //Mostrar Grado
    public ArrayList<Grado> getArrayList() {
        arrayListGrado.clear();
        ResultSet resultSet = SQLDatabaseConnection.getInstance().query("SELECT * FROM Grado");
        try {
            while (resultSet.next()) {
             Grado grado = new Grado();
             grado.setIdGrado(resultSet.getInt("idGrado"));
             grado.setNombre(resultSet.getString("nombre"));
             grado.setDescripcion(resultSet.getString("descripcion"));
             arrayListGrado.add(grado);
            }   
        } catch (SQLException ex) {
            Logger.getLogger(ControladorGrado.class.getName()).log(Level.SEVERE, null, ex);
        }
         return arrayListGrado;
    }
    
    //Modificar Grado
    public void modificarGrado(String nombre, String descripcion, int idGrado) {
        String query = "UPDATE Grado SET nombre = '" + nombre + "', descripcion = '" + descripcion + "' WHERE idGrado = " + idGrado + ";";
        SQLDatabaseConnection.getInstance().executeQuery(query);
    }
    
    //Eliminar Grado
    public void eliminarGrado(int idGrado) {
        String query = "DELETE Grado WHERE idGrado = " + idGrado + ";";
        SQLDatabaseConnection.getInstance().executeQuery(query);
    }
    
    //Buscar Grado
    public Grado buscar(int idGrado) {
        for (Grado grado: arrayListGrado) {
            if (grado.getIdGrado() == idGrado) {
                return grado;
            }
        }
        return null;
    }
    
    public ArrayList<Grado> search(String nombre){
        ArrayList<Grado> resultado = new ArrayList<>(); 
        for(Grado grado: getArrayList()){
             if(grado.getNombre().toUpperCase().contains(nombre.toUpperCase())){
                 resultado.add(grado);
             }
         }
         return resultado;
    }
}