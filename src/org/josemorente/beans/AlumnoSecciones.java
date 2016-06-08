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
public class AlumnoSecciones {
    private int idAlumnoSecciones;
    private Alumno alumno;
    private SeccionAcademica seccionAcademica;
    private SeccionTecnica seccionTecnica;

    public int getIdAlumnoSecciones() {
        return idAlumnoSecciones;
    }

    public void setIdAlumnoSecciones(int idAlumnoSecciones) {
        this.idAlumnoSecciones = idAlumnoSecciones;
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

    public SeccionTecnica getSeccionTecnica() {
        return seccionTecnica;
    }

    public void setSeccionTecnica(SeccionTecnica seccionTecnica) {
        this.seccionTecnica = seccionTecnica;
    }

}
