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
public class ControladorCarrera {
    public static ControladorCarrera instance;

    public static ControladorCarrera getInstance() {
        if (instance == null) {
            instance = new ControladorCarrera();
        }
        return instance;
    }
    
    
}
