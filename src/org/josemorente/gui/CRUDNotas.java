/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.josemorente.gui;

import java.util.HashMap;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import org.josemorente.beans.Alumno;
import org.josemorente.beans.SeccionTecnica;
import org.josemorente.controlador.ControladorNotas;
import org.josemorente.controlador.ControladorSeccionTecnica;
import org.josemorente.utilidades.ReportGenerator;

/**
 *
 * @author José Morente
 */
public class CRUDNotas {
    private static CRUDNotas instance;
    private HBox hBoxCRUD;
    private HBox hBoxAlumnos;
    private GridPane gridPane;
    private Text textTitulo;
    private ListView<SeccionTecnica> listViewST;
    private ObservableList<SeccionTecnica> observableList;
    private TableView<Alumno> tableViewAlumno;
    private TableColumn<Alumno, Integer> tableColumnIdAlumno;
    private TableColumn<Alumno, String> tableColumnNombre;
    private TableColumn<Alumno, String> tableColumnApellido;
    private ObservableList<Alumno> observableListA;
    private Button buttonNota;

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
        listViewST.setOnMouseClicked(new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
        tableColumnIdAlumno = new TableColumn<>();
        tableColumnIdAlumno.setText("Carnet");
        tableColumnIdAlumno.setCellValueFactory(new PropertyValueFactory<>("idAlumno"));
        tableColumnIdAlumno.setMinWidth(125);
        
        tableColumnNombre = new TableColumn<>();
        tableColumnNombre.setText("Nombres");
        tableColumnNombre.setCellValueFactory(new PropertyValueFactory<>("nombres"));
        tableColumnNombre.setMinWidth(150);
        
        tableColumnApellido = new TableColumn<>();
        tableColumnApellido.setText("Apellidos");
        tableColumnApellido.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        tableColumnApellido.setMinWidth(150);
        
        actualizarObservableListA(listViewST.getSelectionModel().getSelectedItem().getIdSeccionTecnica());
        tableViewAlumno = new TableView<>(observableListA);
        tableViewAlumno.getColumns().addAll(tableColumnIdAlumno, tableColumnNombre, 
                tableColumnApellido);
        tableViewAlumno.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                buttonNota = new Button("Boleta");
                buttonNota.setId("buttonNota");
                buttonNota.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if (tableViewAlumno.getSelectionModel().getSelectedItem() != null) {
                        reporte(tableViewAlumno.getSelectionModel().getSelectedItem().getIdAlumno());
                    }
                }
            });
         gridPane.add(buttonNota, 2, 5);
            }
        });
        
        gridPane.add(tableViewAlumno, 1, 5);
        }
        });
        gridPane.add(listViewST, 0, 5);
        
        hBoxCRUD.getChildren().add(gridPane);
        return hBoxCRUD;
    }
    
    public void reporte(int idAlumno) {
        HashMap hashMap = new HashMap();
        hashMap.put("idAlumno", idAlumno);
        ReportGenerator.getInstance().generate(hashMap, "Boleta.jasper", "Notas de Bimestre");
    }
    
    public void actualizarObservableList() {
        observableList = FXCollections.observableArrayList(ControladorSeccionTecnica.getInstance().getArrayList());
    }
    
    public void actualizarObservableListA(int idSeccionTecnica) {
        observableListA = FXCollections.observableArrayList(ControladorNotas.getInstance().getArrayList(idSeccionTecnica));
    }
}
