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
public class AlumnoSA {
    private int idAlumnoSA;
    private Alumno alumno;
    private SeccionAcademica seccionAcademica;

    public AlumnoSA() {
    }

    public int getIdAlumnoSA() {
        return idAlumnoSA;
    }

    public void setIdAlumnoSA(int idAlumnoSA) {
        this.idAlumnoSA = idAlumnoSA;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public SeccionAcademica getSeccionAcademica() {
        return seccionAcademica;
    }

    public void setSeccionAcademica(SeccionAcademica seccionAcademica) {
        this.seccionAcademica = seccionAcademica;
    }
    
    
}
