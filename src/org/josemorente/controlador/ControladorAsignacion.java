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
    private Materia materia = new Materia();

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
    public void agregarAsignacion(int idProfesor, int idMateria) {
        String query = "INSERT INTO ProfesorMateria(idProfesor, idMateria) VALUES(" + idProfesor + ", " + idMateria + ");";
        SQLDatabaseConnection.getInstance().executeQuery(query);
    }
    
    //Mostrar Asignación
    public ArrayList<Asignacion> getArrayList() {
        arrayListAsignacion.clear();
        ResultSet resultSet = SQLDatabaseConnection.getInstance().query("SELECT * FROM ProfesorMateria;");
        try {
            while (resultSet.next()) {
                Asignacion asignacion = new Asignacion();
                asignacion.setIdAsignacion(resultSet.getInt("idProfesorMateria"));
                asignacion.setProfesor(ControladorProfesor.getInstance().buscar(resultSet.getInt("idProfesor")));
                asignacion.setMateria(ControladorMateria.getInstance().buscar(resultSet.getInt("idMateria")));
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
        String query = "DELETE ProfesorMateria WHERE idProfesorMateria = " + idAsignacion+ ";";
        SQLDatabaseConnection.getInstance().executeQuery(query);
    }
}
