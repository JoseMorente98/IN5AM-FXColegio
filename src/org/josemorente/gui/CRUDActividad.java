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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import org.josemorente.beans.Actividad;
import org.josemorente.beans.Alumno;
import org.josemorente.beans.Asignacion;
import org.josemorente.beans.Bimestre;
import org.josemorente.beans.Calificaciones;
import org.josemorente.beans.Materia;
import org.josemorente.beans.Profesor;
import org.josemorente.beans.SeccionAcademica;
import org.josemorente.beans.SeccionTecnica;
import org.josemorente.controlador.ControladorActividad;
import org.josemorente.controlador.ControladorAlumno;
import org.josemorente.controlador.ControladorAsignacion;
import org.josemorente.controlador.ControladorCalificacion;
import org.josemorente.controlador.ControladorMateria;
import org.josemorente.controlador.ControladorProfesor;

/**
 *
 * @author José Morente
 */
public class CRUDActividad {
private static CRUDActividad instance;
    private HBox hBoxCRUD;
    private GridPane gridPane;
    private Text textTitulo;
    private HBox hBoxButtons;
    private Button buttonNuevo;
    private Button buttonCalificar;
    private Button buttonActualizar;
    private TableColumn<Calificaciones, Integer> tableColumnidCalificacion;
    private TableColumn<Calificaciones, Integer> tableColumnMateria;
    private TableColumn<Calificaciones, Integer> tableColumnBimestre;
    private TableColumn<Calificaciones, Integer> tableColumnActividad;
    private TableColumn<Calificaciones, Integer> tableColumnAlumno;
    private TableColumn<Calificaciones, Integer> tableColumnNota;
    private TableView<Calificaciones> tableView;
    private ObservableList<Calificaciones> observableList;
    
    private CRUDActividad() {
    }

    public static CRUDActividad getInstance() {
        if (instance == null) {
            instance = new CRUDActividad();
        }
        return instance;
    }
    
    public void reiniciarhBoxCRUD() {
        hBoxCRUD.getChildren().clear();
        hBoxCRUD.getChildren().add(gridPane);
        actualizarTableViewItems();
    }
    
    public void actualizarObservableList() {
        observableList = FXCollections.observableArrayList(ControladorCalificacion.getInstance().getArrayList());
    }
    
    public void actualizarTableViewItems() {
        actualizarObservableList();
        tableView.setItems(observableList);
    }

    public HBox gethBoxCRUD() {
        hBoxCRUD = new HBox();
        
        gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));
        
        textTitulo = new Text("Calificaciones");
        textTitulo.setFill(Color.WHITESMOKE);
        textTitulo.setFont(Font.font(Font.getDefault().getFamily(), 25));
        gridPane.add(textTitulo, 0, 0);
        
        buttonNuevo = new Button("Actividad");
        buttonNuevo.setId("buttonNuevo");
        buttonNuevo.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    hBoxCRUD.getChildren().clear();   
                    hBoxCRUD.getChildren().add(CRUDActividades.getInstance().gethBoxCRUD());
            }
        });
        
        buttonCalificar = new Button("Calificar");
        buttonCalificar.setId("buttonCalificar");
        buttonCalificar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                hBoxCRUD.getChildren().clear();   
                hBoxCRUD.getChildren().addAll(gridPane, AgregarCalificacion.getInstance().getGridPane());
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
        
        hBoxButtons = new HBox(10);
        
        hBoxButtons.getChildren().addAll(buttonNuevo, buttonCalificar, buttonActualizar);
        gridPane.add(hBoxButtons, 0, 1);
        
        tableColumnidCalificacion = new TableColumn<>();
        tableColumnidCalificacion.setText("ID");
        tableColumnidCalificacion.setCellValueFactory(new PropertyValueFactory<>("idCalificacion") );
        tableColumnidCalificacion.setMinWidth(50);
        
        tableColumnMateria = new TableColumn<>();
        tableColumnMateria.setText("Materia");
        tableColumnMateria.setCellValueFactory(new PropertyValueFactory<>("Materia"));
        tableColumnMateria.setMinWidth(100);
        
        tableColumnBimestre = new TableColumn<>();
        tableColumnBimestre.setText("Bimestre");
        tableColumnBimestre.setCellValueFactory(new PropertyValueFactory<>("Bimestre"));
        tableColumnBimestre.setMinWidth(100);
        
        tableColumnActividad = new TableColumn<>();
        tableColumnActividad.setText("Actividad");
        tableColumnActividad.setCellValueFactory(new PropertyValueFactory<>("Actividad"));
        tableColumnActividad.setMinWidth(150);
        
        tableColumnAlumno= new TableColumn<>();
        tableColumnAlumno.setText("Alumno");
        tableColumnAlumno.setCellValueFactory(new PropertyValueFactory<>("Alumno"));
        tableColumnAlumno.setMinWidth(200);
        
        tableColumnNota= new TableColumn<>();
        tableColumnNota.setText("Nota");
        tableColumnNota.setCellValueFactory(new PropertyValueFactory<>("valor"));
        tableColumnNota.setMinWidth(100);
        
        actualizarObservableList();
        tableView = new TableView<>(observableList);
        tableView.getColumns().addAll(tableColumnidCalificacion, tableColumnMateria,
                tableColumnBimestre, tableColumnActividad, tableColumnAlumno, tableColumnNota);
        
        gridPane.add(tableView, 0, 2, 2, 1);
        
        hBoxCRUD.getChildren().addAll(gridPane);
        return hBoxCRUD;
    }
    
}

class AgregarCalificacion {
    private static AgregarCalificacion instance;
    private GridPane gridPane;
    private Text textTitulo;
    private Label labelMateria;
    private ComboBox<Materia> comboBoxMateria;
    private Label labelBimestre;
    private ComboBox<Bimestre> comboBoxBimestre;
    private Label labelActividad;
    private ComboBox<Actividad> comboBoxActividad;
    private Label labelAlumno;
    private ComboBox<Alumno> comboBoxAlumno;
    private Label labelNota;
    private TextField textFieldNota; 
    private Button buttonAgregar;
    private Button buttonCerrar;
    private int x = 0;
    private ObservableList<Materia> observableListMateria;
    private ObservableList<Bimestre> observableListBimestre;
    private ObservableList<Actividad> observableListActividad;
    private ObservableList<Alumno> observableListAlumno;
    
    private AgregarCalificacion() {
        actualizarObservableListActividad();
        actualizarObservableListAlumno();
        actualizarObservableListBimestre();
        actualizarObservableListMateria();
    }

    public static AgregarCalificacion getInstance() {
        if (instance == null) {
            instance = new AgregarCalificacion();
        }
        return instance;
    }

    public GridPane getGridPane() {
        gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setGridLinesVisible(false);
        gridPane.setPadding(new Insets(25, 25, 25, 25));
        
        textTitulo = new Text("Calificaciones");
        textTitulo.setId("titulo");
        textTitulo.setFill(Color.WHITESMOKE);
        gridPane.add(textTitulo, 0, 0, 2, 1);
        
        labelMateria = new Label("Materia :");
        labelMateria.setId("labels");
        gridPane.add(labelMateria, 0, 2);
        
        comboBoxMateria = new ComboBox<>(observableListMateria);
        gridPane.add(comboBoxMateria, 1, 2, 2, 1);
        
        labelBimestre = new Label("Bimestre :");
        labelBimestre.setId("labels");
        gridPane.add(labelBimestre, 0, 3);
        
        comboBoxBimestre = new ComboBox<>(observableListBimestre);
        gridPane.add(comboBoxBimestre, 1, 3, 2, 1);
        
        labelActividad = new Label("Actividad :");
        labelActividad.setId("labels");
        gridPane.add(labelActividad, 0, 4);
        
        comboBoxActividad = new ComboBox<>(observableListActividad);
        gridPane.add(comboBoxActividad, 1, 4, 2, 1);
        
        labelAlumno = new Label("Alumno :");
        labelAlumno.setId("labels");
        gridPane.add(labelAlumno, 0, 5);
        
        comboBoxAlumno = new ComboBox<>(observableListAlumno);
        gridPane.add(comboBoxAlumno, 1, 5, 2, 1);
        
        labelNota = new Label("Nota :");
        labelNota.setId("labels");
        gridPane.add(labelNota, 0, 6);
        
        textFieldNota = new TextField();
        textFieldNota.setPromptText("Nota del Alumno");
        gridPane.add(textFieldNota, 1, 6, 2, 1);
        
        buttonAgregar = new Button("Asignar");
        buttonAgregar.setId("buttonAgregar");
        buttonAgregar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ControladorCalificacion.getInstance().agregarCalificacion(comboBoxMateria.getSelectionModel().getSelectedItem().getIdMateria(), 
                        comboBoxBimestre.getSelectionModel().getSelectedItem().getIdBimestre(), 
                        comboBoxActividad.getSelectionModel().getSelectedItem().getIdActividad(), 
                        comboBoxAlumno.getSelectionModel().getSelectedItem().getIdAlumno(), 
                        x = Integer.parseInt(textFieldNota.getText()));
                CRUDActividad.getInstance().reiniciarhBoxCRUD();
            }
        });
        
        buttonCerrar = new Button("Cerrar");
        buttonCerrar.setId("buttonCerrar");
        buttonCerrar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                CRUDActividad.getInstance().reiniciarhBoxCRUD();
            }
        });
        
        gridPane.add(buttonAgregar, 1, 7);
        gridPane.add(buttonCerrar, 2, 7, 2, 1);
        
        return gridPane;
    }
    
    public void actualizarObservableListMateria() {
        observableListMateria = FXCollections.observableArrayList(ControladorMateria.getInstance().getArrayList());
    }
    
    public void actualizarObservableListBimestre() {
        observableListBimestre = FXCollections.observableArrayList(ControladorCalificacion.getInstance().getArrayListBimestre());
    }
    
    public void actualizarObservableListActividad() {
        observableListActividad = FXCollections.observableArrayList(ControladorActividad.getInstance().getArrayList());
    }
    
    public void actualizarObservableListAlumno() {
        observableListAlumno = FXCollections.observableArrayList(ControladorAlumno.getInstance().getArrayList());
    }
}