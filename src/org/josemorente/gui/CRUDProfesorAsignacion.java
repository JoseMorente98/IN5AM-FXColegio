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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import org.josemorente.beans.Asignacion;
import org.josemorente.beans.Materia;
import org.josemorente.beans.Profesor;
import org.josemorente.beans.SeccionAcademica;
import org.josemorente.beans.SeccionTecnica;
import org.josemorente.controlador.ControladorAsignacion;
import org.josemorente.controlador.ControladorMateria;
import org.josemorente.controlador.ControladorProfesor;
import org.josemorente.controlador.ControladorSeccionAcademica;
import org.josemorente.controlador.ControladorSeccionTecnica;

/**
 *
 * @author José Morente
 */
public class CRUDProfesorAsignacion {
    private static CRUDProfesorAsignacion instance;
    private HBox hBoxCRUD;
    private GridPane gridPane;
    private Text textTitulo;
    private HBox hBoxBuscar;
    private TextField textFieldBuscar;
    private Button buttonBuscar;
    private HBox hBoxButtons;
    private Button buttonNuevo;
    private Button buttonEliminar;
    private Button buttonActualizar;
    private Button buttonVer;
    private TableView<Asignacion> tableViewAsignacion;
    private TableColumn<Asignacion, Integer> tableColumnIdAsignacion;
    private TableColumn<Asignacion, Integer> tableColumnProfesor;
    private TableColumn<Asignacion, Integer> tableColumnMateria;
    private TableColumn<Asignacion, Integer> tableColumnSeccionT;
    private TableColumn<Asignacion, Integer> tableColumnSeccionA;
    private ObservableList<Asignacion> observableListAsignacion;
    
    private CRUDProfesorAsignacion() {
        actualizarObservableList();
    }

    public static CRUDProfesorAsignacion getInstance() {
        if (instance == null) {
            instance = new CRUDProfesorAsignacion();
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
        
        textTitulo = new Text("Asignación de Materia & Sección");
        textTitulo.setFill(Color.WHITESMOKE);
        textTitulo.setFont(Font.font(Font.getDefault().getFamily(), 25));
        gridPane.add(textTitulo, 0, 0);
        
        hBoxButtons = new HBox(10);
        
        buttonNuevo = new Button("Asignar");
        buttonNuevo.setId("buttonNuevo");
        buttonNuevo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                hBoxCRUD.getChildren().clear();
                hBoxCRUD.getChildren().addAll(gridPane, AgregarProfesorMateria.getInstance().getGridPane());
            }
        });
        
        buttonEliminar = new Button("Quitar");
        buttonEliminar.setId("buttonEliminar");
        buttonEliminar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (tableViewAsignacion.getSelectionModel().getSelectedItem() != null) {
                    ControladorAsignacion.getInstance().quitarAsignacion(tableViewAsignacion.getSelectionModel().getSelectedItem().getIdAsignacion());
                    actualizarTableViewItems();
                }
            }
        });
        
        buttonActualizar = new Button("Actualizar");
        buttonActualizar.setId("buttonActualizar");
        buttonActualizar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               actualizarTableViewItems();
            }
        });
        
        hBoxButtons.getChildren().addAll(buttonNuevo, buttonEliminar, buttonActualizar);
        gridPane.add(hBoxButtons, 0, 2);
        
        tableColumnIdAsignacion = new TableColumn<>();
        tableColumnIdAsignacion.setText("ID Asignación");
        tableColumnIdAsignacion.setCellValueFactory(new PropertyValueFactory<>("idAsignacion") );
        tableColumnIdAsignacion.setMinWidth(125);
        
        tableColumnProfesor = new TableColumn<>();
        tableColumnProfesor.setText("Profesor");
        tableColumnProfesor.setCellValueFactory(new PropertyValueFactory<>("Profesor"));
        tableColumnProfesor.setMinWidth(200);
        
        tableColumnMateria = new TableColumn<>();
        tableColumnMateria.setText("Materia");
        tableColumnMateria.setCellValueFactory(new PropertyValueFactory<>("Materia"));
        tableColumnMateria.setMinWidth(150);
        
        tableColumnSeccionT = new TableColumn<>();
        tableColumnSeccionT.setText("Sección Técnica");
        tableColumnSeccionT.setCellValueFactory(new PropertyValueFactory<>("SeccionTecnica"));
        tableColumnSeccionT.setMinWidth(150);
        
        tableColumnSeccionA = new TableColumn<>();
        tableColumnSeccionA.setText("Sección Académica");
        tableColumnSeccionA.setCellValueFactory(new PropertyValueFactory<>("SeccionAcademica"));
        tableColumnSeccionA.setMinWidth(150);
        
        tableViewAsignacion = new TableView<>(observableListAsignacion);
        tableViewAsignacion.getColumns().addAll(tableColumnIdAsignacion, tableColumnProfesor,
                tableColumnMateria, tableColumnSeccionT, tableColumnSeccionA);
        
        gridPane.add(tableViewAsignacion, 0, 3, 2, 1);
       
        hBoxCRUD.getChildren().add(gridPane);
        return hBoxCRUD;
    }
    public void actualizarTableViewItems() {
        actualizarObservableList();
        tableViewAsignacion.setItems(observableListAsignacion);
    }
       
    public void actualizarObservableList() {
        observableListAsignacion = FXCollections.observableArrayList(ControladorAsignacion.getInstance().getArrayListAsignacion());
    }
}

class AgregarProfesorMateria {
    private static AgregarProfesorMateria instance;
    private GridPane gridPane;
    private Text textTitulo;
    private Label labelProfesor;
    private ComboBox<Profesor> comboBoxProfesor;
    private Label labelMateria;
    private ComboBox<Materia> comboBoxMateria;
    private Label labelSeccionT;
    private ComboBox<SeccionTecnica> comboBoxSeccionT;
    private Label labelSeccionA;
    private ComboBox<SeccionAcademica> comboBoxSeccionA;
    private Button buttonAgregar;
    private Button buttonCerrar;
    private ObservableList<Profesor> observableListProfesor;
    private ObservableList<Materia> observableListMateria;
    private ObservableList<SeccionTecnica> observableListSeccionT;
    private ObservableList<SeccionAcademica> observableListSeccionA;
    
    private AgregarProfesorMateria() {
        actualizarObservableListMateria();
        actualizarObservableListProfesor();
        actualizarObservableListSeccionA();
        actualizarObservableListSeccionT();
    }

    public static AgregarProfesorMateria getInstance() {
        if (instance == null) {
            instance = new AgregarProfesorMateria();
        }
        return instance;
    }

    public GridPane getGridPane() {
        gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setGridLinesVisible(false);
        gridPane.setPadding(new Insets(25, 25, 25, 25));
        
        textTitulo = new Text("Asignación de Materias");
        textTitulo.setId("titulo");
        textTitulo.setFill(Color.WHITESMOKE);
        gridPane.add(textTitulo, 0, 0, 2, 1);
        
        labelProfesor = new Label("Profesor :");
        labelProfesor.setId("labels");
        gridPane.add(labelProfesor, 0, 1);
        
        comboBoxProfesor = new ComboBox<>(observableListProfesor);
        gridPane.add(comboBoxProfesor, 1, 1, 2, 1);
        
        labelMateria = new Label("Materia :");
        labelMateria.setId("labels");
        gridPane.add(labelMateria, 0, 2);
                
        comboBoxMateria = new ComboBox<>(observableListMateria);
        gridPane.add(comboBoxMateria, 1, 2, 2, 1);
        
        labelSeccionT = new Label("Sección T. :");
        labelSeccionT.setId("labels");
        gridPane.add(labelSeccionT, 0, 3);
                
        comboBoxSeccionT = new ComboBox<>(observableListSeccionT);
        gridPane.add(comboBoxSeccionT, 1, 3, 2, 1); 
        
        labelSeccionA = new Label("Sección A. :");
        labelSeccionA.setId("labels");
        gridPane.add(labelSeccionA, 0, 4);
                
        comboBoxSeccionA = new ComboBox<>(observableListSeccionA);
        gridPane.add(comboBoxSeccionA, 1, 4, 2, 1); 
        
        buttonAgregar = new Button("Asignar");
        buttonAgregar.setId("buttonAgregar");
        buttonAgregar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                agregarAsignacion(comboBoxProfesor.getSelectionModel().getSelectedItem(), 
                        comboBoxMateria.getSelectionModel().getSelectedItem(), 
                        comboBoxSeccionT.getSelectionModel().getSelectedItem(), 
                        comboBoxSeccionA.getSelectionModel().getSelectedItem());
                CRUDProfesorAsignacion.getInstance().reiniciarhBoxCRUD();
                CRUDProfesorAsignacion.getInstance().actualizarTableViewItems();
            }
        });
        
        buttonCerrar = new Button("Cerrar");
        buttonCerrar.setId("buttonCerrar");
        buttonCerrar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                CRUDProfesorAsignacion.getInstance().reiniciarhBoxCRUD();
            }
        });
        
        gridPane.add(buttonAgregar, 1, 5);
        gridPane.add(buttonCerrar, 2, 5, 2, 1);
        
        return gridPane;
    }
    
    public void agregarAsignacion(Profesor profesor, Materia materia, SeccionTecnica seccionTecnica, 
            SeccionAcademica seccionAcademica) {
        ControladorAsignacion.getInstance().agregarAsignacion(profesor.getIdProfesor(), 
                materia.getIdMateria(), 
                seccionTecnica.getIdSeccionTecnica(), 
                seccionAcademica.getIdSeccionAcademica());
    }
    
    public void actualizarObservableListProfesor() {
        observableListProfesor = FXCollections.observableArrayList(ControladorProfesor.getInstance().getArrayList());
    }
    
    public void actualizarObservableListMateria() {
        observableListMateria = FXCollections.observableArrayList(ControladorMateria.getInstance().getArrayList());
    }
    
    public void actualizarObservableListSeccionT() {
        observableListSeccionT = FXCollections.observableArrayList(ControladorSeccionTecnica.getInstance().getArrayList());
    }
    
    public void actualizarObservableListSeccionA() {
        observableListSeccionA = FXCollections.observableArrayList(ControladorSeccionAcademica.getInstance().getArrayList());
    }
}