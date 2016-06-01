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
import org.josemorente.beans.Alumno;
import org.josemorente.database.SQLDatabaseConnection;

/**
 *
 * @author José Morente
 */
public class ControladorAlumno {
    private static ControladorAlumno instance;
    private ArrayList<Alumno> arrayListAlumno;

    private ControladorAlumno() {
        this.arrayListAlumno = new ArrayList<>();
    }

    public static ControladorAlumno getInstance() {
        if (instance == null) {
            instance = new ControladorAlumno();
        }
        return instance;
    }
    
    //Agregar Alumno
    public void agregarAlumno(String nombres, String apellidos, LocalDate fechaNacimiento, int idGrado, int idCarrera, 
            String jornada, int telefono, String direccion) {
        String query = "INSERT INTO Alumno(nombres, apellidos, fechaNacimiento, idGrado, idCarrera, jornada, telefono, direccion) "
                + "VALUES('"+nombres+"', '"+apellidos+"', '"+fechaNacimiento+"', "+idGrado+", "+idCarrera+", '"+jornada+"', "+telefono+", '"+direccion+"');";
        SQLDatabaseConnection.getInstance().executeQuery(query);
    }

    //Mostrar Alumno
    public ArrayList<Alumno> getArrayList() {
        arrayListAlumno.clear();
        ResultSet resultSet = SQLDatabaseConnection.getInstance().query("SELECT * FROM Alumno");
        try {
            while(resultSet.next()) {
                Alumno alumno = new Alumno();
                alumno.setIdAlumno(resultSet.getInt("idAlumno"));
                alumno.setNombres(resultSet.getString("nombres"));
                alumno.setApellidos(resultSet.getString("apellidos"));
                alumno.setFechaNacimiento(resultSet.getDate("fechaNacimiento"));
                alumno.setGrado(ControladorGrado.getInstance().buscar(resultSet.getInt("idGrado")));
                alumno.setCarrera(ControladorCarrera.getInstance().buscar(resultSet.getInt("idCarrera")));
                alumno.setJornada(resultSet.getString("jornada"));
                alumno.setTelefono(resultSet.getInt("telefono"));
                alumno.setDireccion(resultSet.getString("direccion"));
                arrayListAlumno.add(alumno);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControladorAlumno.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arrayListAlumno;
    }
    
    //Modificar Alumno
    public void modificarAlumno(String nombres, String apellidos, LocalDate fechaNacimiento, int idGrado, int idCarrera, 
            String jornada, int telefono, String direccion, int idAlumno) {
        String query =  "UPDATE Alumno SET nombres = '" + nombres + "', apellidos = '" + apellidos + "', fechaNacimiento = '" + fechaNacimiento + "', idGrado = " + idGrado + ", "
                + "idCarrera = " + idCarrera + ", jornada = '" + jornada + "', telefono = " + telefono + ", direccion = '" + direccion + "' WHERE idAlumno = " + idAlumno+ ";";
        SQLDatabaseConnection.getInstance().executeQuery(query);
    }
    
    //Eliminar Alumno
    public void eliminarAlumno(int idAlumno) {
        String query = "DELETE Alumno WHERE idAlumno = " + idAlumno + ";";
        SQLDatabaseConnection.getInstance().executeQuery(query);
    }
    
    //Buscar Alumno
    public Alumno buscar(int idAlumno) {
        for(Alumno alumno: arrayListAlumno) {
            if (alumno.getIdAlumno() == idAlumno) {
                return alumno;
            }
        }
        return null;
    }
}
