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
import org.josemorente.beans.Asignacion;
import org.josemorente.beans.Materia;
import org.josemorente.database.SQLDatabaseConnection;

/**
 *
 * @author José Morente
 */
public class ControladorAsignacion {
    private static ControladorAsignacion instance;
    private ArrayList<Asignacion> arrayListAsignacion;

    private ControladorAsignacion() {
        this.arrayListAsignacion = new ArrayList<>();
    }

    public static ControladorAsignacion getInstance() {
        if (instance == null) {
            instance = new ControladorAsignacion();
        }
        return instance;
    }

    //Agregar Asignacion
    public void agregarAsignacion(int idProfesor, int idMateria, int idSeccionT, int idSeccionA) {
        String query = "INSERT INTO ProfesorMateriaSeccion(idProfesor, idMateria, idSeccionTecnica, idSeccionAcademica)"
                + " VALUES (" + idProfesor + ", " + idMateria + ", " + idSeccionT + ", " + idSeccionA + ");";
        SQLDatabaseConnection.getInstance().executeQuery(query);
    }
    
    //Mostrar Asignación
    public ArrayList<Asignacion> getArrayListAsignacion() {
        arrayListAsignacion.clear();
        ResultSet resultSet = SQLDatabaseConnection.getInstance().query("SELECT * FROM ProfesorMateriaSeccion;");
        try {
            while (resultSet.next()) {
                Asignacion asignacion = new Asignacion();
                asignacion.setIdAsignacion(resultSet.getInt("idProfesorMateriaSeccion"));
                asignacion.setProfesor(ControladorProfesor.getInstance().buscar(resultSet.getInt("idProfesor")));
                asignacion.setMateria(ControladorMateria.getInstance().buscar(resultSet.getInt("idMateria")));
                asignacion.setSeccionTecnica(ControladorSeccionTecnica.getInstance().buscar(resultSet.getInt("idSeccionTecnica")));
                asignacion.setSeccionAcademica(ControladorSeccionAcademica.getInstance().buscar(resultSet.getInt("idSeccionAcademica")));
                arrayListAsignacion.add(asignacion);
            }   
        } catch (SQLException ex) {
            Logger.getLogger(ControladorAsignacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        SQLDatabaseConnection.getInstance().desconectar();
        return arrayListAsignacion;
    }
        
    //Quitar Asignacion
    public void quitarAsignacion(int idAsignacion) {
        String query = "DELETE ProfesorMateriaSeccion WHERE idProfesorMateriaSeccion = " + idAsignacion + ";";
        SQLDatabaseConnection.getInstance().executeQuery(query);
    }
    
}
