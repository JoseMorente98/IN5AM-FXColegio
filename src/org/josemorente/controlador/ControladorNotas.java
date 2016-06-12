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
import org.josemorente.beans.Alumno;
import org.josemorente.database.SQLDatabaseConnection;

/**
 *
 * @author José Morente
 */
public class ControladorNotas {
    private static ControladorNotas instance;
    private ArrayList<Alumno> arrayList;

    private ControladorNotas() {
        this.arrayList = new ArrayList<>();
    }

    public static ControladorNotas getInstance() {
        if (instance == null) {
            instance = new ControladorNotas();
        }
        return instance;
    }

    public ArrayList<Alumno> getArrayList(int idSeccionTecnica) {
        arrayList.clear();
        String query = "SELECT * FROM Alumno WHERE idAlumno IN(SELECT idAlumno "
                + "FROM SeccionAlumno WHERE idSeccionTecnica = " + idSeccionTecnica + ");";
        ResultSet resultSet = SQLDatabaseConnection.getInstance().query(query);
        try {
            while (resultSet.next()) {
                Alumno alumno = new Alumno();
                alumno.setIdAlumno(resultSet.getInt("idAlumno"));
                alumno.setNombres(resultSet.getString("nombres"));
                alumno.setApellidos(resultSet.getString("apellidos"));
                arrayList.add(alumno);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControladorNotas.class.getName()).log(Level.SEVERE, null, ex);
        }
        SQLDatabaseConnection.getInstance().desconectar();
        return arrayList;
    }
}
