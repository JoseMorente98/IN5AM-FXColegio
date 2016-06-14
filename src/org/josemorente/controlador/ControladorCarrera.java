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
import org.josemorente.beans.Carrera;
import org.josemorente.database.SQLDatabaseConnection;

/**
 *
 * @author José Morente
 */
public class ControladorCarrera {
    private static ControladorCarrera instance;
    private ArrayList<Carrera> arrayListCarrera;

    public ControladorCarrera() {
        this.arrayListCarrera = new ArrayList<>();
    }

    public static ControladorCarrera getInstance() {
        if (instance == null) {
            instance = new ControladorCarrera();
        }
        return instance;
    }
    
    //Agregar Carrera
    public void agregarCarrera(String nombre, String descripcion) {
        String query = "INSERT INTO Carrera(nombre, descripcion) VALUES('" + nombre + "', '" + descripcion + "');";
        SQLDatabaseConnection.getInstance().executeQuery(query);
    }
    
    //Mostrar Carrera
    public ArrayList<Carrera> getArrayList() {
        arrayListCarrera.clear();
        ResultSet resultSet = SQLDatabaseConnection.getInstance().query("SELECT * FROM Carrera");
        try {
            while (resultSet.next()) {
                Carrera carrera = new Carrera();
                carrera.setIdCarrera(resultSet.getInt("idCarrera"));
                carrera.setNombre(resultSet.getString("nombre"));
                carrera.setDescripcion(resultSet.getString("descripcion"));
                arrayListCarrera.add(carrera);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControladorCarrera.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arrayListCarrera;
    }
    
    //Modificar Carrera
    public void modificarCarrera(String nombre, String descripcion, int idCarrera) {
        String query = "UPDATE Carrera SET nombre = '" + nombre + "', descripcion = '" + descripcion + "' WHERE idCarrera = " + idCarrera + ";";
        SQLDatabaseConnection.getInstance().executeQuery(query);
    }
    
    //Eliminar Carrera
    public void eliminarCarrera(int idCarrera) {
        String query = "DELETE Carrera WHERE idCarrera = " + idCarrera + ";";
        SQLDatabaseConnection.getInstance().executeQuery(query);
    }
    
    //Buscar Carrera
    public Carrera buscar(int idCarrera) {
        for(Carrera carrera: arrayListCarrera) {
            if (carrera.getIdCarrera() == idCarrera) {
                return carrera;
            }
        }
        return null;
    }
    
    public ArrayList<Carrera> search(String nombre){
        ArrayList<Carrera> resultado = new ArrayList<>(); 
        for(Carrera carrera: getArrayList()){
             if(carrera.getNombre().toUpperCase().contains(nombre.toUpperCase())){
                 resultado.add(carrera);
             }
         }
         return resultado;
    }
}