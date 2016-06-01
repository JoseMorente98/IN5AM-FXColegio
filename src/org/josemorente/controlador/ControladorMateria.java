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
import org.josemorente.beans.Materia;
import org.josemorente.database.SQLDatabaseConnection;

/**
 *
 * @author José Morente
 */
public class ControladorMateria {
    private static ControladorMateria instance;
    private ArrayList<Materia> arrayListMateria;

    private ControladorMateria() {
        this.arrayListMateria = new ArrayList<>();
    }

    public static ControladorMateria getInstance() {
        if (instance == null) {
            instance = new ControladorMateria();
        }
        return instance;
    }
    
    //Agregar Materia
    public void agregarMateria(String nombre, String descripcion) {
        String query = "INSERT INTO Materia(nombre, descripcion) VALUES('" +nombre+ "', '" +descripcion+ "');";
        SQLDatabaseConnection.getInstance().executeQuery(query);
    }

    //Mostrar Materia
    public ArrayList<Materia> getArrayList() {
        arrayListMateria.clear();
        ResultSet resultSet = SQLDatabaseConnection.getInstance().query("SELECT * FROM Materia");
        try {
            while(resultSet.next()){
                Materia materia = new Materia();
                materia.setIdMateria(resultSet.getInt("idMateria"));
                materia.setNombre(resultSet.getString("nombre"));
                materia.setDescripcion(resultSet.getString("descripcion"));
                arrayListMateria.add(materia);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControladorMateria.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arrayListMateria;
    }
    
    //Modificar Materia
    public void modificarMateria(String nombre, String descripcion, int idMateria) {
        String query = "UPDATE Materia SET nombre = '" + nombre + "', descripcion = '" + descripcion + "' WHERE idMateria = " + idMateria +";";
        SQLDatabaseConnection.getInstance().executeQuery(query);
    }
    
    //Eliminar Materia
    public void eliminarMateria(int idMateria) {
        String query = "DELETE FROM Materia WHERE idMateria = " + idMateria + ";";
        SQLDatabaseConnection.getInstance().executeQuery(query);
    }
    
    //Buscar Materia
    public Materia buscar(int idMateria) {
        for (Materia materia: arrayListMateria) {
            if (materia.getIdMateria() == idMateria) {
                return materia;
            }
        }
        return null;
    }
    
    //Mostrar Solo Materias
    public ArrayList<Materia> getArrayListMateria() {
        arrayListMateria.clear();
        ResultSet resultSet = SQLDatabaseConnection.getInstance().query("SELECT * FROM Materia;");
        try {
            while(resultSet.next()) {
                Materia materia = new Materia();
                materia.setNombre(resultSet.getString("nombre"));
                arrayListMateria.add(materia);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControladorProfesor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arrayListMateria;
    }
}