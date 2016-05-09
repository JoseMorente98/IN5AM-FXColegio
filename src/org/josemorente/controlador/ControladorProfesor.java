/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.josemorente.controlador;

/**
 *
 * @author José Morente
 */
public class ControladorProfesor {
    public static ControladorProfesor instance;

    public static ControladorProfesor getInstance() {
        if(instance == null) {
            instance = new ControladorProfesor();
        }
        return instance;
    }
    
    
}
