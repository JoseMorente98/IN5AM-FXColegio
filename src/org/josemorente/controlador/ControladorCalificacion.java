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
import org.josemorente.beans.Bimestre;
import org.josemorente.beans.Calificaciones;
import org.josemorente.database.SQLDatabaseConnection;

/**
 *
 * @author José Morente
 */
public class ControladorCalificacion {
    private static ControladorCalificacion instance;
    private ArrayList<Calificaciones> arrayListCalificacion;
    private ArrayList<Bimestre> arrayListBimestre;

    private ControladorCalificacion() {
        this.arrayListCalificacion = new ArrayList<>();
        this.arrayListBimestre = new ArrayList<>();
    }

    public static ControladorCalificacion getInstance() {
        if (instance == null) {
            instance = new ControladorCalificacion();
        }
        return instance;
    }
    
    public ArrayList<Bimestre> getArrayListBimestre() {
        arrayListBimestre.clear();
        ResultSet resultSet = SQLDatabaseConnection.getInstance().query("SELECT * FROM Bimestre;");
        try {
            while (resultSet.next()) {
                Bimestre bimestre = new Bimestre();
                bimestre.setIdBimestre(resultSet.getInt("idBimestre"));
                bimestre.setNombre(resultSet.getString("nombre"));
                arrayListBimestre.add(bimestre);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControladorCalificacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        SQLDatabaseConnection.getInstance().desconectar();
        return arrayListBimestre;
    }
    
    //Buscar Bimestre
    public Bimestre buscarBimestre(int idBimestre) {
        for(Bimestre bimestre: arrayListBimestre) {
            if (bimestre.getIdBimestre() == idBimestre) {
                return bimestre;
            }
        }
        return null;
    }
    
    //Agregar Calificaciones
    public void agregarCalificacion(int idMateria, int idBimestre, int idActividad, int idAlumno, int valor) {
        String query = "INSERT INTO ActividadMateriaBimestreAlumno(idMateria, idBimestre, idActividad, idAlumno, valor) "
                + "VALUES(" + idMateria + ", " + idBimestre + ", " + idActividad + ", " + idAlumno + ", " + valor + ");";
        SQLDatabaseConnection.getInstance().executeQuery(query);
    }
    
    //Mostrar Calificaciones
    public ArrayList<Calificaciones> getArrayList() {
        arrayListCalificacion.clear();
        ResultSet resultSet = SQLDatabaseConnection.getInstance().query("SELECT * FROM ActividadMateriaBimestreAlumno;");
        try {
            while(resultSet.next()) {
                Calificaciones calificaciones = new Calificaciones();
                calificaciones.setIdCalificacion(resultSet.getInt("idActividadMateriaBimestre"));
                calificaciones.setMateria(ControladorMateria.getInstance().buscar(resultSet.getInt("idMateria")));
                calificaciones.setBimestre(buscarBimestre(resultSet.getInt("idBimestre")));
                calificaciones.setActividad(ControladorActividad.getInstance().buscar(resultSet.getInt("idActividad")));
                calificaciones.setAlumno(ControladorAlumno.getInstance().buscar(resultSet.getInt("idAlumno")));
                calificaciones.setValor(resultSet.getInt("valor"));
                arrayListCalificacion.add(calificaciones);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControladorCalificacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        SQLDatabaseConnection.getInstance().desconectar();
        return arrayListCalificacion;
    }
    
    
}
