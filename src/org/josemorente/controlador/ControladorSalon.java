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
import org.josemorente.beans.Salon;
import org.josemorente.database.SQLDatabaseConnection;

/**
 *
 * @author José Morente
 */
public class ControladorSalon {
    private static ControladorSalon instance;
    private ArrayList<Salon> arrayListSalon;

    private ControladorSalon() {
        this.arrayListSalon = new ArrayList<>();
    }

    public static ControladorSalon getInstance() {
        if (instance == null) {
            instance = new ControladorSalon();
        }
        return instance;
    }
    
    //Agregar Salon
    public void agregarSalon(String nombre, String descripcion) {
        String query = "INSERT INTO Salon(nombre, descripcion) VALUES('" + nombre + "', '" + descripcion + "');";
        SQLDatabaseConnection.getInstance().executeQuery(query);
    }
    
    //Modificar Salon
    public void modificarSalon(String nombre, String descripcion, int idSalon) {
        String query = "UPDATE Salon SET nombre = '" + nombre + "', descripcion = '" + descripcion + "' WHERE idSalon = " + idSalon + ";";
        SQLDatabaseConnection.getInstance().executeQuery(query);
    }

    //Mostrar Salon
    public ArrayList<Salon> getArrayList() {
        arrayListSalon.clear();
        ResultSet resultSet = SQLDatabaseConnection.getInstance().query("SELECT * FROM Salon;");
        try {
            while(resultSet.next()) {
                Salon salon = new Salon();
                salon.setIdSalon(resultSet.getInt("idSalon"));
                salon.setNombre(resultSet.getString("nombre"));
                salon.setDescripcion(resultSet.getString("descripcion"));
                arrayListSalon.add(salon);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControladorSalon.class.getName()).log(Level.SEVERE, null, ex);
        }
        SQLDatabaseConnection.getInstance().desconectar();
        return arrayListSalon;
    }
    
    //Eliminar Salon
    public void eliminarSalon(int idSalon) {
        String query = "DELETE Salon WHERE idSalon = " + idSalon + ";";
        SQLDatabaseConnection.getInstance().executeQuery(query);
    }
    
    //Buscar Salon
    public Salon buscar(int idSalon) {
        for (Salon salon: arrayListSalon) {
            if (salon.getIdSalon()== idSalon) {
                return salon;
            }
        }
        return null;
    }
    
}
