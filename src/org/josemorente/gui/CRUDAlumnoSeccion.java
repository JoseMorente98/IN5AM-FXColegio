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
import org.josemorente.beans.AlumnoSecciones;
import org.josemorente.beans.SeccionAcademica;
import org.josemorente.beans.SeccionTecnica;
import org.josemorente.controlador.ControladorAlumno;
import org.josemorente.controlador.ControladorAlumnoSecciones;
import org.josemorente.controlador.ControladorSeccionAcademica;
import org.josemorente.controlador.ControladorSeccionTecnica;

/**
 *
 * @author José Morente
 */
public class CRUDAlumnoSeccion {
    private static CRUDAlumnoSeccion instance;
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
    private TableView<AlumnoSecciones> tableViewAsignacion;
    private TableColumn<AlumnoSecciones, Integer> tableColumnIdAsignacion;
    private TableColumn<AlumnoSecciones, Integer> tableColumnAlumno;
    private TableColumn<AlumnoSecciones, Integer> tableColumnSeccionT;
    private TableColumn<AlumnoSecciones, Integer> tableColumnSeccionA;
    private ObservableList<AlumnoSecciones> observableListAsignacion;
    
    private CRUDAlumnoSeccion() {
    }

    public static CRUDAlumnoSeccion getInstance() {
        if (instance == null) {
            instance = new CRUDAlumnoSeccion();
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
        
        textTitulo = new Text("Alumno & Secciones");
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
                hBoxCRUD.getChildren().addAll(gridPane, AgregarAsignacion.getInstance().getGridPane());
            }
        });
        
        buttonEliminar = new Button("Quitar");
        buttonEliminar.setId("buttonEliminar");
        buttonEliminar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (tableViewAsignacion.getSelectionModel().getSelectedItem() != null) {
                    ControladorAlumnoSecciones.getInstance().quitarSecciones(tableViewAsignacion.getSelectionModel().getSelectedItem().getIdAlumnoSecciones());
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
        tableColumnIdAsignacion.setCellValueFactory(new PropertyValueFactory<>("idAlumnoSecciones") );
        tableColumnIdAsignacion.setMinWidth(125);
        
        tableColumnAlumno = new TableColumn<>();
        tableColumnAlumno.setText("Nombre Alumno");
        tableColumnAlumno.setCellValueFactory(new PropertyValueFactory<>("Alumno"));
        tableColumnAlumno.setMinWidth(175);
        
        tableColumnSeccionT = new TableColumn<>();
        tableColumnSeccionT.setText("Sección Técnica");
        tableColumnSeccionT.setCellValueFactory(new PropertyValueFactory<>("SeccionTecnica"));
        tableColumnSeccionT.setMinWidth(175);
        
        tableColumnSeccionA = new TableColumn<>();
        tableColumnSeccionA.setText("Sección Académica");
        tableColumnSeccionA.setCellValueFactory(new PropertyValueFactory<>("SeccionAcademica"));
        tableColumnSeccionA.setMinWidth(175);
        
        actualizarObservableList();
        tableViewAsignacion = new TableView<>(observableListAsignacion);
        tableViewAsignacion.getColumns().addAll(tableColumnIdAsignacion, tableColumnAlumno, 
                tableColumnSeccionT, tableColumnSeccionA);
        
        gridPane.add(tableViewAsignacion, 0, 3, 2, 1);
       
        hBoxCRUD.getChildren().add(gridPane);
        return hBoxCRUD;
    }
    
    public void actualizarTableViewItems() {
        actualizarObservableList();
        tableViewAsignacion.setItems(observableListAsignacion);
    }
       
    public void actualizarObservableList() {
        observableListAsignacion = FXCollections.observableArrayList(ControladorAlumnoSecciones.getInstance().getArrayList());
    }
    
}

class AgregarAsignacion {
    private static AgregarAsignacion instance;
    private GridPane gridPane;
    private Text textTitulo;
    private Label labelAlumno;
    private ComboBox<Alumno> comboBoxAlumno;
    private Label labelST;
    private ComboBox<SeccionTecnica> comboBoxST;
    private Label labelSA;
    private ComboBox<SeccionAcademica> comboBoxSA;
    private Button buttonAgregar;
    private Button buttonCerrar;
    private ObservableList<Alumno> observableListAlumno;
    private ObservableList<SeccionTecnica> observableListST;
    private ObservableList<SeccionAcademica> observableListSA;
    
    private AgregarAsignacion() {
        actualizarObservableListAlumno();
        actualizarObservableListSA();
        actualizarObservableListST();
    }

    public static AgregarAsignacion getInstance() {
        if (instance == null) {
            instance = new AgregarAsignacion();
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
        
        labelST = new Label("S. Técnica :");
        labelST.setId("labels");
        gridPane.add(labelST, 0, 2);
                
        comboBoxST = new ComboBox<>(observableListST);
        gridPane.add(comboBoxST, 1, 2, 2, 1); 
        
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
                        comboBoxST.getSelectionModel().getSelectedItem(),
                        comboBoxSA.getSelectionModel().getSelectedItem());
                CRUDAlumnoSeccion.getInstance().reiniciarhBoxCRUD();
                CRUDAlumnoSeccion.getInstance().actualizarTableViewItems();
            }
        });
        
        buttonCerrar = new Button("Cerrar");
        buttonCerrar.setId("buttonCerrar");
        buttonCerrar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                CRUDAlumnoSeccion.getInstance().reiniciarhBoxCRUD();
            }
        });
        
        gridPane.add(buttonAgregar, 1, 4);
        gridPane.add(buttonCerrar, 2, 4, 2, 1);
        
        return gridPane;
    }
    
    public void agregarAsignacion(Alumno alumno, SeccionTecnica seccionTecnica, 
            SeccionAcademica seccionAcademica) {
        ControladorAlumnoSecciones.getInstance().agregarSecciones(alumno.getIdAlumno(), 
                seccionTecnica.getIdSeccionTecnica(),
                seccionAcademica.getIdSeccionAcademica());
    }
    
    private void actualizarObservableListAlumno() {
        observableListAlumno = FXCollections.observableArrayList(ControladorAlumno.getInstance().getArrayList());
    }
    
    private void actualizarObservableListSA() {
        observableListSA = FXCollections.observableArrayList(ControladorSeccionAcademica.getInstance().getArrayList());
    }
    
    private void actualizarObservableListST() {
        observableListST = FXCollections.observableArrayList(ControladorSeccionTecnica.getInstance().getArrayList());
    }
}
