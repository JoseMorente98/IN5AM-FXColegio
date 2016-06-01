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
import org.josemorente.beans.Alumno;
import org.josemorente.beans.AlumnoSA;
import org.josemorente.beans.SeccionAcademica;
import org.josemorente.controlador.ControladorAlumno;
import org.josemorente.controlador.ControladorAlumnoSA;
import org.josemorente.controlador.ControladorSeccionAcademica;

/**
 *
 * @author José Morente
 */
public class CRUDAlumnoSA {
    private static CRUDAlumnoSA instance;
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
    private TableView<AlumnoSA> tableViewAsignacion;
    private TableColumn<AlumnoSA, Integer> tableColumnIdAsignacion;
    private TableColumn<AlumnoSA, Integer> tableColumnAlumno;
    private TableColumn<AlumnoSA, Integer> tableColumnSeccionA;
    private ObservableList<AlumnoSA> observableListAsignacion;
    
    private CRUDAlumnoSA() {
    }

    public static CRUDAlumnoSA getInstance() {
        if (instance == null) {
            instance = new CRUDAlumnoSA();
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
        
        textTitulo = new Text("Alumno & Sección Académica");
        textTitulo.setFill(Color.WHITESMOKE);
        textTitulo.setFont(Font.font(Font.getDefault().getFamily(), 25));
        gridPane.add(textTitulo, 0, 0);
        
        hBoxBuscar = new HBox(10);
        
        textFieldBuscar = new TextField();
        textFieldBuscar.setPromptText("Buscar Asignación");
        
        buttonBuscar = new Button("Buscar");
        buttonBuscar.setId("buttonBuscar");
        buttonBuscar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        }); 
        
        hBoxBuscar.getChildren().addAll(textFieldBuscar, buttonBuscar);
        gridPane.add(hBoxBuscar, 0, 1);
        
        hBoxButtons = new HBox(10);
        
        buttonNuevo = new Button("Asignar");
        buttonNuevo.setId("buttonNuevo");
        buttonNuevo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                hBoxCRUD.getChildren().clear();
                hBoxCRUD.getChildren().addAll(gridPane, AgregarAsignacionSA.getInstance().getGridPane());
            }
        });
        
        buttonEliminar = new Button("Quitar");
        buttonEliminar.setId("buttonEliminar");
        buttonEliminar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (tableViewAsignacion.getSelectionModel().getSelectedItem() != null) {
                    ControladorAlumnoSA.getInstance().quitarAsignacion(tableViewAsignacion.getSelectionModel().getSelectedItem().getIdAlumnoSA());
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
        
        buttonVer = new Button("Ver");
        buttonVer.setId("buttonVer");
        buttonVer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        hBoxButtons.getChildren().addAll(buttonNuevo, buttonEliminar, buttonActualizar, 
                buttonVer);
        gridPane.add(hBoxButtons, 0, 2);
        
        tableColumnIdAsignacion = new TableColumn<>();
        tableColumnIdAsignacion.setText("ID Asignación");
        tableColumnIdAsignacion.setCellValueFactory(new PropertyValueFactory<>("idAlumnoSA") );
        tableColumnIdAsignacion.setMinWidth(125);
        
        tableColumnAlumno = new TableColumn<>();
        tableColumnAlumno.setText("Nombre Alumno");
        tableColumnAlumno.setCellValueFactory(new PropertyValueFactory<>("Alumno"));
        tableColumnAlumno.setMinWidth(200);
        
        tableColumnSeccionA = new TableColumn<>();
        tableColumnSeccionA.setText("Sección Académica");
        tableColumnSeccionA.setCellValueFactory(new PropertyValueFactory<>("SeccionAcademica"));
        tableColumnSeccionA.setMinWidth(200);
        
        actualizarObservableList();
        tableViewAsignacion = new TableView<>(observableListAsignacion);
        tableViewAsignacion.getColumns().addAll(tableColumnIdAsignacion, tableColumnAlumno, tableColumnSeccionA);
        
        gridPane.add(tableViewAsignacion, 0, 3, 2, 1);
       
        hBoxCRUD.getChildren().add(gridPane);
        return hBoxCRUD;
    }
    
    public void actualizarTableViewItems() {
        actualizarObservableList();
        tableViewAsignacion.setItems(observableListAsignacion);
    }
       
    public void actualizarObservableList() {
        observableListAsignacion = FXCollections.observableArrayList(ControladorAlumnoSA.getInstance().getArrayList());
    }
    
}

class AgregarAsignacionSA {
    private static AgregarAsignacionSA instance;
    private GridPane gridPane;
    private Text textTitulo;
    private Label labelAlumno;
    private ComboBox<Alumno> comboBoxAlumno;
    private Label labelSA;
    private ComboBox<SeccionAcademica> comboBoxSA;
    private Button buttonAgregar;
    private Button buttonCerrar;
    private ObservableList<Alumno> observableListAlumno;
    private ObservableList<SeccionAcademica> observableListSA;
    
    private AgregarAsignacionSA() {
        actualizarObservableListAlumno();
        actualizarObservableListSA();
    }

    public static AgregarAsignacionSA getInstance() {
        if (instance == null) {
            instance = new AgregarAsignacionSA();
        }
        return instance;
    }

    public GridPane getGridPane() {
        gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setGridLinesVisible(false);
        gridPane.setPadding(new Insets(25, 25, 25, 25));
        
        textTitulo = new Text("Asignar Alumno");
        textTitulo.setId("titulo");
        textTitulo.setFill(Color.WHITESMOKE);
        gridPane.add(textTitulo, 0, 0, 2, 1);
        
        labelAlumno = new Label("Alumno :");
        labelAlumno.setId("labels");
        gridPane.add(labelAlumno, 0, 1);
        
        comboBoxAlumno = new ComboBox<>(observableListAlumno);
        gridPane.add(comboBoxAlumno, 1, 1, 2, 1);
        
        labelSA = new Label("S. Académica :");
        labelSA.setId("labels");
        gridPane.add(labelSA, 0, 3);
                
        comboBoxSA = new ComboBox<>(observableListSA);
        gridPane.add(comboBoxSA, 1, 3, 2, 1); 
        
        buttonAgregar = new Button("Asignar");
        buttonAgregar.setId("buttonAgregar");
        buttonAgregar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                agregarAsignacion(comboBoxAlumno.getSelectionModel().getSelectedItem(), 
                        comboBoxSA.getSelectionModel().getSelectedItem());
                CRUDAlumnoSA.getInstance().reiniciarhBoxCRUD();
                CRUDAlumnoSA.getInstance().actualizarTableViewItems();
            }
        });
        
        buttonCerrar = new Button("Cerrar");
        buttonCerrar.setId("buttonCerrar");
        buttonCerrar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                CRUDAlumnoSA.getInstance().reiniciarhBoxCRUD();
            }
        });
        
        gridPane.add(buttonAgregar, 1, 4);
        gridPane.add(buttonCerrar, 2, 4, 2, 1);
        
        return gridPane;
    }
    
    public void agregarAsignacion(Alumno alumno, SeccionAcademica seccionAcademica) {
        ControladorAlumnoSA.getInstance().agregarAsignacion(alumno.getIdAlumno(), 
                seccionAcademica.getIdSeccionAcademica());
    }
    
    private void actualizarObservableListAlumno() {
        observableListAlumno = FXCollections.observableArrayList(ControladorAlumno.getInstance().getArrayList());
    }
    
    private void actualizarObservableListSA() {
        observableListSA = FXCollections.observableArrayList(ControladorSeccionAcademica.getInstance().getArrayList());
    }
}
