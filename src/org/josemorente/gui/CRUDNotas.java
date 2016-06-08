/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.josemorente.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import org.josemorente.beans.SeccionTecnica;
import org.josemorente.controlador.ControladorSeccionTecnica;

/**
 *
 * @author José Morente
 */
public class CRUDNotas {
    private static CRUDNotas instance;
    private HBox hBoxCRUD;
    private GridPane gridPane;
    private Text textTitulo;
    private ListView<SeccionTecnica> listViewST;
    private ObservableList<SeccionTecnica> observableList;

    private CRUDNotas() {
    }

    public static CRUDNotas getInstance() {
        if (instance == null) {
            instance = new CRUDNotas();
        }
        return instance;
    }

    public HBox gethBoxCRUD() {
        hBoxCRUD = new HBox();
        
        gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));
        
        textTitulo = new Text("Notas");
        textTitulo.setFill(Color.WHITESMOKE);
        textTitulo.setFont(Font.font(Font.getDefault().getFamily(), 25));
        gridPane.add(textTitulo, 0, 0);
        
        actualizarObservableList();
        listViewST = new ListView<>(observableList);
        listViewST.setMaxSize(225, 350);
        listViewST.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        gridPane.add(listViewST, 0, 7);
        
        hBoxCRUD.getChildren().add(gridPane);
        return hBoxCRUD;
    }
    
    public void actualizarObservableList() {
        observableList = FXCollections.observableArrayList(ControladorSeccionTecnica.getInstance().getArrayList());
    }
}
