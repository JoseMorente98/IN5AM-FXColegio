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
import org.josemorente.beans.AlumnoSecciones;
import org.josemorente.database.SQLDatabaseConnection;

/**
 *
 * @author José Morente
 */
public class ControladorAlumnoSecciones {
    private static ControladorAlumnoSecciones instance;
    private ArrayList<AlumnoSecciones> arrayList;

    private ControladorAlumnoSecciones() {
        this.arrayList = new ArrayList<>();
    }
    
    public static ControladorAlumnoSecciones getInstance() {
        if (instance == null) {
            instance = new ControladorAlumnoSecciones();
        }
        return instance;
    }

    public void agregarSecciones(int idAumno, int idSeccionTecnica, int idSeccionAcademica) {
        String query = "INSERT INTO SeccionAlumno(idAlumno, idSeccionTecnica, idSeccionAcademica) VALUES (" + idAumno + ", " + idSeccionTecnica + ", " + idSeccionAcademica + ");";
        SQLDatabaseConnection.getInstance().executeQuery(query);
    }
    
    public ArrayList<AlumnoSecciones> getArrayList() {
        arrayList.clear();
        ResultSet resultSet = SQLDatabaseConnection.getInstance().query("SELECT * FROM SeccionAlumno");
        try {
            while (resultSet.next()) {
                AlumnoSecciones secciones = new AlumnoSecciones();
                secciones.setIdAlumnoSecciones(resultSet.getInt("idSeccionAlumno"));
                secciones.setAlumno(ControladorAlumno.getInstance().buscar(resultSet.getInt("idAlumno")));
                secciones.setSeccionTecnica(ControladorSeccionTecnica.getInstance().buscar(resultSet.getInt("idSeccionTecnica")));
                secciones.setSeccionAcademica(ControladorSeccionAcademica.getInstance().buscar(resultSet.getInt("idSeccionAcademica")));
                arrayList.add(secciones);
                        }
        } catch (SQLException ex) {
            Logger.getLogger(ControladorAlumnoSecciones.class.getName()).log(Level.SEVERE, null, ex);
        }
        SQLDatabaseConnection.getInstance().desconectar();
        return arrayList;
    }
    
    public void quitarSecciones(int idSeccionAlumno) {
            String query = "DELETE SeccionAlumno WHERE " + idSeccionAlumno + ";";
            SQLDatabaseConnection.getInstance().executeQuery(query);
    }
    
}