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
public class Asignacion {
    private int idAsignacion;
    private Profesor profesor;
    private Materia materia;
    private SeccionTecnica seccionTecnica;
    private SeccionAcademica seccionAcademica;

    public Asignacion() {
    }

    public int getIdAsignacion() {
        return idAsignacion;
    }

    public void setIdAsignacion(int idAsignacion) {
        this.idAsignacion = idAsignacion;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public SeccionTecnica getSeccionTecnica() {
        return seccionTecnica;
    }

    public void setSeccionTecnica(SeccionTecnica seccionTecnica) {
        this.seccionTecnica = seccionTecnica;
    }

    public SeccionAcademica getSeccionAcademica() {
        return seccionAcademica;
    }

    public void setSeccionAcademica(SeccionAcademica seccionAcademica) {
        this.seccionAcademica = seccionAcademica;
    }
    
}
