/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.josemorente.beans;

/**
 *
 * @author José Morente
 */
public class AlumnoST {
    private int idAlumnoST;
    private Alumno alumno;
    private SeccionTecnica seccionTecnica;

    public AlumnoST() {
    }

    public int getIdAlumnoST() {
        return idAlumnoST;
    }

    public void setIdAlumnoST(int idAlumnoST) {
        this.idAlumnoST = idAlumnoST;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public SeccionTecnica getSeccionTecnica() {
        return seccionTecnica;
    }

    public void setSeccionTecnica(SeccionTecnica seccionTecnica) {
        this.seccionTecnica = seccionTecnica;
    }

}
