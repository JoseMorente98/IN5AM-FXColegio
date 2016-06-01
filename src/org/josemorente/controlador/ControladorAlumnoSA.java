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
import org.josemorente.beans.AlumnoSA;
import org.josemorente.database.SQLDatabaseConnection;

/**
 *
 * @author José Morente
 */
public class ControladorAlumnoSA {
    private static ControladorAlumnoSA instance;
    private ArrayList<AlumnoSA> arrayListAlumnoSA;

    private ControladorAlumnoSA() {
        this.arrayListAlumnoSA = new ArrayList<>();
    }
    
    public static ControladorAlumnoSA getInstance() {
        if (instance == null) {
            instance = new ControladorAlumnoSA();
        }
        return instance;
    }
    
    //Agregar Asignacion
    public void agregarAsignacion(int idAlumno, int idSeccionAcademica) {
        String query = "INSERT INTO SeccionAcademicaAlumno(idAlumno, idSeccionAcademica) VALUES(" + idAlumno + ", " + idSeccionAcademica + ");";
        SQLDatabaseConnection.getInstance().executeQuery(query);
    }
    
    //MostrarAsignacion
    public ArrayList<AlumnoSA> getArrayList() {
        arrayListAlumnoSA.clear();
        ResultSet resultSet = SQLDatabaseConnection.getInstance().query("SELECT * FROM SeccionAcademicaAlumno");
        try {
            while(resultSet.next()) {
                AlumnoSA alumnoSA = new AlumnoSA();
                alumnoSA.setIdAlumnoSA(resultSet.getInt("idSeccionAcademicaAlumno"));
                alumnoSA.setAlumno(ControladorAlumno.getInstance().buscar(resultSet.getInt("idAlumno")));
                alumnoSA.setSeccionAcademica(ControladorSeccionAcademica.getInstance().buscar(resultSet.getInt("idSeccionAcademica")));
                arrayListAlumnoSA.add(alumnoSA);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControladorAlumnoSA.class.getName()).log(Level.SEVERE, null, ex);
        }
        SQLDatabaseConnection.getInstance().desconectar();
        return arrayListAlumnoSA;
    }
    
    //Quitar Asignacion
    public void quitarAsignacion(int idAlumnoSA) {
        String query = "DELETE SeccionAcademicaAlumno WHERE idSeccionAcademicaAlumno = " + idAlumnoSA + ";";
        SQLDatabaseConnection.getInstance().executeQuery(query);
    }
}