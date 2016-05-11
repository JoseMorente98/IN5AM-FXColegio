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
public class ControladorUsuario {
    public static ControladorUsuario instance;
    
    private ControladorUsuario() {
    }

    public static ControladorUsuario getInstance() {
        if (instance == null) {
            instance = new ControladorUsuario();
        }
        return instance;
    }
    
    //Agregar Usuario
    
    //Mostrar Usuarios
    
    //Modificar Usuarios
    
    //Eliminar Usuarios
    
    
}
