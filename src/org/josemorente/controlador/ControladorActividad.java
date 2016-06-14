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
import org.josemorente.beans.Actividad;
import org.josemorente.beans.Materia;
import org.josemorente.database.SQLDatabaseConnection;

/**
 *
 * @author José Morentes
 */
public class ControladorActividad {
    private static ControladorActividad instance;
    private ArrayList<Actividad> arrayListActividad;
    private ArrayList<Materia> arrayListMateria;

    private ControladorActividad() {
        this.arrayListActividad = new ArrayList<>();
    }

    public static ControladorActividad getInstance() {
        if (instance == null) {
            instance = new ControladorActividad();
        }
        return instance;
    }

    //Agregar Actividades
    public void agregarActividades(String tipoActividad, String nombre, int punteo) {
        String query = "INSERT INTO Actividad(tipoActividad, nombre, valor) VALUES('" + tipoActividad + "', '" + nombre + "', " + punteo + ");";
        SQLDatabaseConnection.getInstance().executeQuery(query);
    }
    
    //Modificar Actividades
    public void modificarActividades(String tipoActividad, String nombre, int punteo, int idActividad) {
        String query = "UPDATE Actividad SET tipoActividad = '" + tipoActividad + "', nombre = '" + nombre + "', valor = " + punteo + " WHERE idActividad = " + idActividad +";";
        SQLDatabaseConnection.getInstance().executeQuery(query);
    }
    
    //Mostrar Actividades
    public ArrayList<Actividad> getArrayList() {
        arrayListActividad.clear();
        ResultSet resultSet = SQLDatabaseConnection.getInstance().query("SELECT * FROM Actividad;");
        try {
            while(resultSet.next()) {
                Actividad actividad = new Actividad();
                actividad.setIdActividad(resultSet.getInt("idActividad"));
                actividad.setTipoActividad(resultSet.getString("tipoActividad"));
                actividad.setNombre(resultSet.getString("nombre"));
                actividad.setPunteo(resultSet.getInt("valor"));
                arrayListActividad.add(actividad); 
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControladorActividad.class.getName()).log(Level.SEVERE, null, ex);
        }
        SQLDatabaseConnection.getInstance().desconectar();
        return arrayListActividad;
    }
    
    //Eliminar Actividad
    public void eliminarActividad(int idActividad) {
        String query = "DELETE Actividad WHERE idActividad = " + idActividad + ";";
        SQLDatabaseConnection.getInstance().executeQuery(query);
    }
    
    //Buscar Actividades
    public Actividad buscar(int idActividad) {
        for (Actividad actividad: arrayListActividad) {
            if (actividad.getIdActividad()== idActividad) {
                return actividad;
            }
        }
        return null;
    }

    //Materia
    public ArrayList<Materia> getArrayListMateria(int idProfesor) {
        arrayListMateria.clear();
        String query = "SELECT * FROM Materia WHERE idMateria "
                + "IN(SELECT idMateria FROM ProfesorMateriaSeccion WHERE idProfesor = " + idProfesor + ");";
        ResultSet resultSet = SQLDatabaseConnection.getInstance().query(query);
        try {
            while(resultSet.next()) {
                Materia materia = new Materia();
                materia.setIdMateria(resultSet.getInt("idMateria"));
                materia.setNombre(resultSet.getString("nombre"));
                arrayListMateria.add(materia);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControladorActividad.class.getName()).log(Level.SEVERE, null, ex);
        }
        SQLDatabaseConnection.getInstance().desconectar();
        return arrayListMateria;
    }
    
    public ArrayList<Actividad> search(String nombre){
        ArrayList<Actividad> resultado = new ArrayList<>(); 
        for(Actividad actividad: getArrayList()){
             if(actividad.getNombre().toUpperCase().contains(nombre.toUpperCase())){
                 resultado.add(actividad);
             }
         }
         return resultado;
    }
}
