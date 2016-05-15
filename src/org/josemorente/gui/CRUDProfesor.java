/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.josemorente.gui;

import javafx.collections.FXCollections;
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
import org.josemorente.beans.Profesor;

/**
 *
 * @author José Morentes
 */
public class CRUDProfesor {
    public static CRUDProfesor instance;
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
    private TableView<Profesor> tableViewProfesor;
    private TableColumn<Profesor, Integer> tableColumnIdProfesor;
    private TableColumn<Profesor, String> tableColumnNombre;
    private TableColumn<Profesor, String> tableColumnApellidos;
    private TableColumn<Profesor, String> tableColumnFechaNacimiento;
    private TableColumn<Profesor, String> tableColumnDpi;
    private TableColumn<Profesor, String> tableColumnDireccion;
    private TableColumn<Profesor, Integer> tableColumnTelefono;
    private ObservableList observableList;
    
    private CRUDProfesor() {
    }

    public static CRUDProfesor getInstance() {
        if (instance == null ) {
            instance = new CRUDProfesor();
        }
        return instance;
    }

    public HBox gethBoxCRUD() {
        hBoxCRUD = new HBox();
        
        gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));
        
        textTitulo = new Text("Profesores");
        textTitulo.setFill(Color.DARKBLUE);
        textTitulo.setFont(Font.font(Font.getDefault().getFamily(), 20));
        gridPane.add(textTitulo, 0, 0);
        
        hBoxBuscar = new HBox(10);
        
        textFieldBuscar = new TextField();
        textFieldBuscar.setPromptText("Buscar Profesor");
        
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
        
        
        tableColumnIdProfesor = new TableColumn<>();
        tableColumnIdProfesor.setText("ID Profesor");
        tableColumnIdProfesor.setCellValueFactory(new PropertyValueFactory<>("idProfesor") );
        tableColumnIdProfesor.setMinWidth(50);
        
        tableColumnNombre = new TableColumn<>();
        tableColumnNombre.setText("Nombres");
        tableColumnNombre.setCellValueFactory(new PropertyValueFactory<>("nombres"));
        tableColumnNombre.setMinWidth(125);
        
        tableColumnApellidos = new TableColumn<>();
        tableColumnApellidos.setText("Apellidos");
        tableColumnApellidos.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        tableColumnApellidos.setMinWidth(125);
        
        tableColumnFechaNacimiento = new TableColumn<>();
        tableColumnFechaNacimiento.setText("Fecha de Nacimiento");
        tableColumnFechaNacimiento.setCellValueFactory(new PropertyValueFactory<>("fechaNacimiento"));
        tableColumnFechaNacimiento.setMinWidth(100);
        
        tableColumnDpi = new TableColumn<>();
        tableColumnDpi.setText("DPI");
        tableColumnDpi.setCellValueFactory(new PropertyValueFactory<>("dpi"));
        tableColumnDpi.setMinWidth(125);
        
        tableColumnDireccion = new TableColumn<>();
        tableColumnDireccion.setText("Dirección");
        tableColumnDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        tableColumnDireccion.setMinWidth(125);
        
        tableColumnTelefono = new TableColumn<>();
        tableColumnTelefono.setText("No. Teléfono");
        tableColumnTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        tableColumnTelefono.setMinWidth(75);
        
        tableViewProfesor = new TableView<>(observableList);
        tableViewProfesor.getColumns().addAll(tableColumnIdProfesor, tableColumnNombre,
                tableColumnApellidos, tableColumnFechaNacimiento, tableColumnDpi,
                tableColumnDireccion, tableColumnTelefono);
        
        gridPane.add(tableViewProfesor, 0, 3, 2, 1);
       
        hBoxCRUD.getChildren().add(gridPane);
        
        return hBoxCRUD;
    }
    
    public void actualizarTableViewItems() {
        actualizarObservableList();
     //   tableView.setItems(observableList);
    }
       
    public void actualizarObservableList() {
     //   observableList = FXCollections.observableList();
    }
    
}
