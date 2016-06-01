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
import org.josemorente.beans.AlumnoST;
import org.josemorente.database.SQLDatabaseConnection;

/**
 *
 * @author José Morente
 */
public class ControladorAlumnoST {
    private static ControladorAlumnoST instance;
    private ArrayList<AlumnoST> arrayListAlumnoST;

    private ControladorAlumnoST() {
        this.arrayListAlumnoST = new ArrayList<>();
    }
    
    public static ControladorAlumnoST getInstance() {
        if (instance == null) {
            instance = new ControladorAlumnoST();
        }
        return instance;
    }
    
    //Agregar Asignacion
    public void agregarAsignacion(int idAlumno, int idSeccionTecnica) {
        String query = "INSERT INTO SeccionTecnicaAlumno(idAlumno, idSeccionTecnica) VALUES(" + idAlumno + ", " + idSeccionTecnica + ");";
        SQLDatabaseConnection.getInstance().executeQuery(query);
    }
    
    //MostrarAsignacion
    public ArrayList<AlumnoST> getArrayList() {
        arrayListAlumnoST.clear();
        ResultSet resultSet = SQLDatabaseConnection.getInstance().query("SELECT * FROM SeccionTecnicaAlumno");
        try {
            while(resultSet.next()) {
                AlumnoST alumnoST = new AlumnoST();
                alumnoST.setIdAlumnoST(resultSet.getInt("idSeccionTecnicaAlumno"));
                alumnoST.setAlumno(ControladorAlumno.getInstance().buscar(resultSet.getInt("idAlumno")));
                alumnoST.setSeccionTecnica(ControladorSeccionTecnica.getInstance().buscar(resultSet.getInt("idSeccionTecnica")));
                arrayListAlumnoST.add(alumnoST);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControladorAlumnoST.class.getName()).log(Level.SEVERE, null, ex);
        }
        SQLDatabaseConnection.getInstance().desconectar();
        return arrayListAlumnoST;
    }
    
    //Quitar Asignacion
    public void quitarAsignacion(int idAlumnoST) {
        String query = "DELETE SeccionTecnicaAlumno WHERE idSeccionTecnicaAlumno = " + idAlumnoST + ";";
        SQLDatabaseConnection.getInstance().executeQuery(query);
    }
    
}