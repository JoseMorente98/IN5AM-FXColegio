/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.josemorente.controlador;

/**
 *
 * @author José Morentes
 */
public class ControladorAlumno {
    public static ControladorAlumno instance;

    public static ControladorAlumno getInstance() {
        if(instance == null) {
            instance = new ControladorAlumno();
        }
        return instance;
    }
    
    
    
    
}
