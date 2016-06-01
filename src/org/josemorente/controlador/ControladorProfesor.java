/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.josemorente.controlador;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.josemorente.beans.Materia;
import org.josemorente.beans.Profesor;
import org.josemorente.database.SQLDatabaseConnection;

/**
 *
 * @author José Morente
 */
public class ControladorProfesor {
    public static ControladorProfesor instance;
    private ArrayList<Profesor> arrayListProfesor;
    private ArrayList<Materia> arrayListMateria;
    private ArrayList<Profesor> arrayListProfesorMateria;

    public static ControladorProfesor getInstance() {
        if (instance == null) {
            instance = new ControladorProfesor();
        }
        return instance;
    }
    
    private ControladorProfesor() {
        arrayListProfesor = new ArrayList<>();
        arrayListMateria = new ArrayList<>();
        arrayListProfesorMateria = new ArrayList<>();
    }
    
    //Agregar Profesor
    public void agregar(String nombres, String apellidos, LocalDate fechaNacimiento, String dpi, int telefono, String direccion) {
        String query = "INSERT INTO Profesor(nombres, apellidos, fechaNacimiento, dpi, telefono, direccion) VALUES('" + nombres + "', '" + apellidos + "', '" + fechaNacimiento + "', '" + dpi + "'," + telefono +", '" + direccion + "');";
        SQLDatabaseConnection.getInstance().executeQuery(query);
    }
    
    //Mostrar Profesor
    public ArrayList<Profesor> getArrayList() {
        arrayListProfesor.clear();
        ResultSet resultSet = SQLDatabaseConnection.getInstance().query("SELECT * FROM Profesor");
        
        try {
            while(resultSet.next()) {
                Profesor profesor = new Profesor();
                profesor.setIdProfesor(resultSet.getInt("idProfesor"));
                profesor.setNombres(resultSet.getString("nombres"));
                profesor.setApellidos(resultSet.getString("apellidos"));
                profesor.setDpi(resultSet.getString("dpi"));
                profesor.setTelefono(resultSet.getInt("telefono"));
                profesor.setDireccion(resultSet.getString("direccion"));
                profesor.setFechaNacimiento(resultSet.getDate("fechaNacimiento"));
                arrayListProfesor.add(profesor);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControladorProfesor.class.getName()).log(Level.SEVERE, null, ex);
        }
        SQLDatabaseConnection.getInstance().desconectar();
        return arrayListProfesor;
    }

    //Modificar Profesor
    public void modificarProfesor(String nombres, String apellidos, LocalDate fechaNacimiento, String dpi, int telefono, String direccion, int idProfesor) {
        String query = "UPDATE Profesor SET nombres = '" + nombres + "', apellidos = '" + apellidos + "', fechaNacimiento = '" + fechaNacimiento + "', dpi = '"  + dpi + "', telefono = "  + telefono + ", direccion = '" + direccion + "' WHERE idProfesor = " + idProfesor + ";";
        SQLDatabaseConnection.getInstance().executeQuery(query);
    }
    
    //Eliminar Profesor
    public void eliminar(int idProfesor) {
        String query = "DELETE FROM Profesor WHERE idProfesor = " +idProfesor;
        SQLDatabaseConnection.getInstance().executeQuery(query);
    }
    
    //Buscar Profesor
    public Profesor buscar(int idProfesor) {
        for(Profesor profesor: arrayListProfesor) {
            if (profesor.getIdProfesor() == idProfesor) {
                return profesor;
            }
        }
        return null;
    }
    
}
