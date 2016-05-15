/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.josemorente.gui;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import org.josemorente.beans.Materia;

/**
 *
 * @author José Morente
 */
public class CRUDMateria {
    public static CRUDMateria instance;
    private HBox hBoxCRUD;
    private GridPane gridPane;
    private Text textTitulo;
    private HBox hBoxBuscar;
    private TextField textFieldBuscar;
    private Button buttonBuscar;
    private HBox hBoxButtons;
    private Button buttonNuevo;
    private Button buttonModificar;
    private Button buttonEliminar;
    private Button buttonActualizar;
    private TableView<Materia> tableViewMateria;
    private TableColumn<Materia, Integer> tableColumnIdMateria;
    private TableColumn<Materia, String> tableColumnNombre;
    private TableColumn<Materia, String> tableColumnDescripcion;
    private ObservableList observableList;
    
    private CRUDMateria() {
    }

    public static CRUDMateria getInstance() {
        if (instance == null) {
            instance = new CRUDMateria();
        }
        return instance;
    }

    public HBox gethBoxCRUD() {
        hBoxCRUD = new HBox();
        
        gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));
        
        textTitulo = new Text("Materias");
        textTitulo.setFill(Color.DARKBLUE);
        textTitulo.setFont(Font.font(Font.getDefault().getFamily(), 20));
        gridPane.add(textTitulo, 0, 0);
        
        hBoxBuscar = new HBox(10);
        
        textFieldBuscar = new TextField();
        textFieldBuscar.setPromptText("Buscar Materia");
        
        buttonBuscar = new Button("Buscar");
        buttonBuscar.setStyle("-fx-base: rgb(0,0,132);");
        buttonBuscar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        }); 
        
        hBoxBuscar.getChildren().addAll(textFieldBuscar, buttonBuscar);
        gridPane.add(hBoxBuscar, 0, 1);
        
        hBoxButtons = new HBox(10);
        
        buttonNuevo = new Button("Nuevo");
        buttonNuevo.setStyle("-fx-base: rgb(0,120,0);");
        buttonNuevo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        buttonModificar = new Button("Modificar");
        buttonModificar.setStyle("-fx-base : rgb(85,210,10);");// Verde Limon
        buttonModificar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        buttonEliminar = new Button("Eliminar");
        buttonEliminar.setStyle("-fx-base : rgb(200,0,0);");//Rojo Oscuro
        buttonEliminar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        buttonActualizar = new Button("Actualizar");
        buttonActualizar.setStyle("-fx-base : rgb(0,128,192);"); //Azul Marino
        buttonActualizar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               // actualizarTableViewItems();
            }
        });
        
        hBoxButtons.getChildren().addAll(buttonNuevo, buttonModificar, buttonEliminar, buttonActualizar);
        gridPane.add(hBoxButtons, 0, 2);
        
        tableColumnIdMateria = new TableColumn<>();
        tableColumnIdMateria.setText("ID Materia");
        tableColumnIdMateria.setCellValueFactory(new PropertyValueFactory<>("idMateria") );
        tableColumnIdMateria.setMinWidth(50);
        
        tableColumnNombre = new TableColumn<>();
        tableColumnNombre.setText("Materias");
        tableColumnNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tableColumnNombre.setMinWidth(125);
        
        tableColumnDescripcion = new TableColumn<>();
        tableColumnDescripcion.setText("Descripción");
        tableColumnDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        tableColumnDescripcion.setMinWidth(300);
        
        tableViewMateria = new TableView<>(observableList);
        tableViewMateria.getColumns().addAll(tableColumnIdMateria, tableColumnNombre,
                tableColumnDescripcion);
        
        gridPane.add(tableViewMateria, 0, 3, 2, 1);
       
        hBoxCRUD.getChildren().add(gridPane);
        
        return hBoxCRUD;
    }
    
    
    
}
