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
import org.josemorente.beans.AlumnoST;
import org.josemorente.beans.SeccionTecnica;
import org.josemorente.controlador.ControladorAlumno;
import org.josemorente.controlador.ControladorAlumnoST;
import org.josemorente.controlador.ControladorSeccionTecnica;

/**
 *
 * @author José Morente
 */
public class CRUDAlumnoST {
private static CRUDAlumnoST instance;
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
    private TableView<AlumnoST> tableViewAsignacion;
    private TableColumn<AlumnoST, Integer> tableColumnIdAsignacion;
    private TableColumn<AlumnoST, Integer> tableColumnAlumno;
    private TableColumn<AlumnoST, Integer> tableColumnSeccionT;
    private ObservableList<AlumnoST> observableListAsignacion;
    
    private CRUDAlumnoST() {
    }

    public static CRUDAlumnoST getInstance() {
        if (instance == null) {
            instance = new CRUDAlumnoST();
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
        
        textTitulo = new Text("Alumno & Sección Técnica");
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
                hBoxCRUD.getChildren().addAll(gridPane, AgregarAsignacionST.getInstance().getGridPane());
            }
        });
        
        buttonEliminar = new Button("Quitar");
        buttonEliminar.setId("buttonEliminar");
        buttonEliminar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (tableViewAsignacion.getSelectionModel().getSelectedItem() != null) {
                    ControladorAlumnoST.getInstance().quitarAsignacion(tableViewAsignacion.getSelectionModel().getSelectedItem().getIdAlumnoST());
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
        tableColumnIdAsignacion.setCellValueFactory(new PropertyValueFactory<>("idAlumnoST") );
        tableColumnIdAsignacion.setMinWidth(125);
        
        tableColumnAlumno = new TableColumn<>();
        tableColumnAlumno.setText("Nombre Alumno");
        tableColumnAlumno.setCellValueFactory(new PropertyValueFactory<>("Alumno"));
        tableColumnAlumno.setMinWidth(200);
        
        tableColumnSeccionT = new TableColumn<>();
        tableColumnSeccionT.setText("Sección Técnica");
        tableColumnSeccionT.setCellValueFactory(new PropertyValueFactory<>("SeccionTecnica"));
        tableColumnSeccionT.setMinWidth(200);
        
        actualizarObservableList();
        tableViewAsignacion = new TableView<>(observableListAsignacion);
        tableViewAsignacion.getColumns().addAll(tableColumnIdAsignacion, tableColumnAlumno, tableColumnSeccionT);
        
        gridPane.add(tableViewAsignacion, 0, 3, 2, 1);
       
        hBoxCRUD.getChildren().add(gridPane);
        return hBoxCRUD;
    }
    
    public void actualizarTableViewItems() {
        actualizarObservableList();
        tableViewAsignacion.setItems(observableListAsignacion);
    }
       
    public void actualizarObservableList() {
        observableListAsignacion = FXCollections.observableArrayList(ControladorAlumnoST.getInstance().getArrayList());
    }
    
}

class AgregarAsignacionST {
    private static AgregarAsignacionST instance;
    private GridPane gridPane;
    private Text textTitulo;
    private Label labelAlumno;
    private ComboBox<Alumno> comboBoxAlumno;
    private Label labelSA;
    private ComboBox<SeccionTecnica> comboBoxST;
    private Button buttonAgregar;
    private Button buttonCerrar;
    private ObservableList<Alumno> observableListAlumno;
    private ObservableList<SeccionTecnica> observableListST;
    
    private AgregarAsignacionST() {
        actualizarObservableListAlumno();
        actualizarObservableListST();
    }

    public static AgregarAsignacionST getInstance() {
        if (instance == null) {
            instance = new AgregarAsignacionST();
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
        
        labelSA = new Label("S. Técnica :");
        labelSA.setId("labels");
        gridPane.add(labelSA, 0, 3);
                
        comboBoxST = new ComboBox<>(observableListST);
        gridPane.add(comboBoxST, 1, 3, 2, 1); 
        
        buttonAgregar = new Button("Asignar");
        buttonAgregar.setId("buttonAgregar");
        buttonAgregar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                agregarAsignacion(comboBoxAlumno.getSelectionModel().getSelectedItem(), 
                        comboBoxST.getSelectionModel().getSelectedItem());
                CRUDAlumnoST.getInstance().reiniciarhBoxCRUD();
                CRUDAlumnoST.getInstance().actualizarTableViewItems();
            }
        });
        
        buttonCerrar = new Button("Cerrar");
        buttonCerrar.setId("buttonCerrar");
        buttonCerrar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                CRUDAlumnoST.getInstance().reiniciarhBoxCRUD();
            }
        });
        
        gridPane.add(buttonAgregar, 1, 4);
        gridPane.add(buttonCerrar, 2, 4, 2, 1);
        
        return gridPane;
    }
    
    public void agregarAsignacion(Alumno alumno, SeccionTecnica seccionTecnica) {
        ControladorAlumnoST.getInstance().agregarAsignacion(alumno.getIdAlumno(), 
                seccionTecnica.getIdSeccionTecnica());
    }
    
    private void actualizarObservableListAlumno() {
        observableListAlumno = FXCollections.observableArrayList(ControladorAlumno.getInstance().getArrayList());
    }
    
    private void actualizarObservableListST() {
        observableListST = FXCollections.observableArrayList(ControladorSeccionTecnica.getInstance().getArrayList());
    }
}

