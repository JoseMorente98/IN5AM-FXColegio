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
public class SeccionTecnica {
    private int idSeccionTecnica;
    private String nombre;
    private String descripcion;
    
    public SeccionTecnica() {    
    }

    public int getIdSeccionTecnica() {
        return idSeccionTecnica;
    }

    public void setIdSeccionTecnica(int idSeccionTecnica) {
        this.idSeccionTecnica = idSeccionTecnica;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return this.nombre;
    }
    
}
