/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.josemorente.gui;

import java.util.Date;
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
import org.josemorente.beans.Alumno;
import org.josemorente.beans.Carrera;
import org.josemorente.beans.Grado;

/**
 *
 * @author José Morente
 */
public class CRUDAlumno {
    public static CRUDAlumno instance;
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
    private TableView<Alumno> tableViewAlumno;
    private TableColumn<Alumno, Integer> tableColumnIdAlumno;
    private TableColumn<Alumno, String> tableColumnNombre;
    private TableColumn<Alumno, String> tableColumnApellido;
    private TableColumn<Alumno, Date> tableColumnFecha;
    private TableColumn<Alumno, Grado> tableColumnGrado;
    private TableColumn<Alumno, Carrera> tableColumnCarrera;
    private TableColumn<Alumno, String> tableColumnJornada;
    private TableColumn<Alumno, Integer> tableColumnTelefono;
    private TableColumn<Alumno, String> tableColumnDireccion;
    private ObservableList<Alumno> observableList;

    private CRUDAlumno() {
    }

    public static CRUDAlumno getInstance() {
        if (instance == null) {
            instance = new CRUDAlumno();
        }
        return instance;
    }
    
    public HBox gethBoxCRUD() {
        hBoxCRUD = new HBox();
        
        gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));
        
        textTitulo = new Text("Alumnos");
        textTitulo.setFill(Color.WHITESMOKE);
        textTitulo.setFont(Font.font(Font.getDefault().getFamily(), 25));
        gridPane.add(textTitulo, 0, 0);
        
        hBoxBuscar = new HBox(10);
        
        textFieldBuscar = new TextField();
        textFieldBuscar.setPromptText("Buscar Alumno");
        
        buttonBuscar = new Button("Buscar");
        buttonBuscar.setStyle("-fx-base: rgb(17,71,138);");
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
        
        tableColumnIdAlumno = new TableColumn<>();
        tableColumnIdAlumno.setText("ID Alumno");
        tableColumnIdAlumno.setCellValueFactory(new PropertyValueFactory<>("idAlumno"));
        tableColumnIdAlumno.setMinWidth(50);
        
        tableColumnNombre = new TableColumn<>();
        tableColumnNombre.setText("Nombres");
        tableColumnNombre.setCellValueFactory(new PropertyValueFactory<>("nombres"));
        tableColumnNombre.setMinWidth(125);
        
        tableColumnApellido = new TableColumn<>();
        tableColumnApellido.setText("Apellidos");
        tableColumnApellido.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        tableColumnApellido.setMinWidth(125);
        
        tableColumnFecha = new TableColumn<>();
        tableColumnFecha.setText("Fecha de Nacimiento");
        tableColumnFecha.setCellValueFactory(new PropertyValueFactory<>("fechaNacimiento"));
        tableColumnFecha.setMinWidth(100);
        
        tableColumnGrado = new TableColumn<>();
        tableColumnGrado.setText("Grado");
        tableColumnGrado.setCellValueFactory(new PropertyValueFactory<>("Grado"));
        tableColumnGrado.setMinWidth(75);
        
        tableColumnCarrera = new TableColumn<>();
        tableColumnCarrera.setText("Carrera");
        tableColumnCarrera.setCellValueFactory(new PropertyValueFactory<>("idAlumno"));
        tableColumnCarrera.setMinWidth(75);
        
        tableColumnJornada = new TableColumn<>();
        tableColumnJornada.setText("Jornada");
        tableColumnJornada.setCellValueFactory(new PropertyValueFactory<>("jornada"));
        tableColumnJornada.setMinWidth(75);
        
        tableColumnTelefono = new TableColumn<>();
        tableColumnTelefono.setText("No. Teléfono");
        tableColumnTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        tableColumnTelefono.setMinWidth(75);
        
        tableColumnDireccion = new TableColumn<>();
        tableColumnDireccion.setText("Dirección");
        tableColumnDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        tableColumnDireccion.setMinWidth(125);
        
        tableViewAlumno = new TableView<>(observableList);
        tableViewAlumno.getColumns().addAll(tableColumnIdAlumno, tableColumnNombre, 
                tableColumnApellido, tableColumnFecha, tableColumnGrado, tableColumnCarrera, 
                tableColumnJornada, tableColumnTelefono, tableColumnDireccion);
        gridPane.add(tableViewAlumno, 0, 3, 2, 1);
        
        hBoxCRUD.getChildren().add(gridPane);
        return hBoxCRUD;
    }
}