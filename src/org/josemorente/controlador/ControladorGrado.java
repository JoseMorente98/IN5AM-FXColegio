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
public class ControladorGrado {
    public static ControladorGrado instance;

    public static ControladorGrado getInstance() {
        if (instance == null) {
            instance = new ControladorGrado();
        }
        return instance;
    }
    
    
}
