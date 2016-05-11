/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.josemorente.gui;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

/**
 *
 * @author José Morentes
 */
public class CRUDUsuario {
    public static CRUDUsuario instance;
    private HBox hBoxCRUD;
    private GridPane gridPane;
    private Text textTitle;
    private HBox hBoxBuscar;
    private TextField textFieldBuscar;
    private Button buttonBuscar;
    private HBox hBoxButtons;
    private Button buttonNuevo;
    private Button buttonModificar;
    private Button buttonEliminar;
    private Button buttonActualizar;
    
    public CRUDUsuario() {
    }

    public static CRUDUsuario getInstance() {
        if (instance == null) {
            instance = new CRUDUsuario();
        }
        return instance;
    }
    
    
    
}
