/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.josemorente.gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 *
 * @author José Morente
 */
public class CRUDSecciones {
    public static CRUDSecciones instance;
    private HBox hBoxCRUD;
    private GridPane gridPane;
    private Text textTitulo;
    private HBox hBoxCentro;
    private HBox hBoxCentro1;
    private Button buttonSeccionA;
    private Button buttonSeccionT;
    private StackPane stackPane;
    private ImageView imageView;
    private ImageView imageView1;
    private Label labelTexto;

    private CRUDSecciones() {
    }

    public static CRUDSecciones getInstance() {
        if (instance == null) {
            instance = new CRUDSecciones();
        }
        return instance;
    }

    public void reiniciarhBoxCRUD() {
        hBoxCRUD.getChildren().clear();
        hBoxCRUD.getChildren().add(gridPane);
    }
    
    public HBox gethBoxCRUD() {
        hBoxCRUD = new HBox();
        
        gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));
        
        textTitulo = new Text("Agregar Secciones");
        textTitulo.setFill(Color.WHITESMOKE);
        textTitulo.setFont(Font.font(Font.getDefault().getFamily(), 25));
        gridPane.add(textTitulo, 0, 0);
        
        labelTexto = new Label("Seleccione el tipo de sección.");
        labelTexto.setId("labels");
        gridPane.add(labelTexto, 0, 1);
        
        imageView = new ImageView(new Image("/org/josemorente/recursos/Acad.png"));
        imageView.setFitWidth(200);
        imageView.setPreserveRatio(true);
        
        hBoxCentro = new HBox();
        hBoxCentro.getChildren().addAll(imageView);
        gridPane.add(hBoxCentro, 0, 2);
        
        imageView1 = new ImageView(new Image("/org/josemorente/recursos/Tec.png"));
        imageView1.setFitWidth(200);
        imageView1.setPreserveRatio(true);
        
        hBoxCentro1 = new HBox();
        hBoxCentro1.getChildren().addAll(imageView1);
        gridPane.add(hBoxCentro1, 1, 2, 2, 1);
        
        buttonSeccionA = new Button("Académicas");
        buttonSeccionA.setId("buttonSA");
        buttonSeccionA.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                hBoxCRUD.getChildren().clear();
                hBoxCRUD.getChildren().add(CRUDSeccionAcademica.getInstance().gethBoxCRUD());
            }
        });
        gridPane.add(buttonSeccionA, 0, 3);
        
        buttonSeccionT = new Button("Técnicas");
        buttonSeccionT.setId("buttonST");
        buttonSeccionT.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                hBoxCRUD.getChildren().clear();
                hBoxCRUD.getChildren().add(CRUDSeccionTecnica.getInstance().gethBoxCRUD());
            }
        });
        gridPane.add(buttonSeccionT, 1, 3, 2, 1);
        
        hBoxCRUD.getChildren().addAll(gridPane);
        return hBoxCRUD;
    }
    
}
