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
import org.josemorente.beans.Grado;
import org.josemorente.controlador.ControladorGrado;

/**
 *
 * @author José Morente
 */
public class CRUDGrado implements CRUDInterfaz {
    private static CRUDGrado instance;
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
    private Button buttonVer;
    private TableView<Grado> tableViewGrado;
    private TableColumn<Grado, Integer> tableColumnIdGrado;
    private TableColumn<Grado, String> tableColumnNombre;
    private TableColumn<Grado, String> tableColumnDescripcion;
    private ObservableList<Grado> observableList;
    
    private CRUDGrado() {
    }

    public static CRUDGrado getInstance() {
        if (instance == null) {
            instance = new CRUDGrado();
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
        
        textTitulo = new Text("Grados");
        textTitulo.setFill(Color.WHITESMOKE);
        textTitulo.setFont(Font.font(Font.getDefault().getFamily(), 25));
        gridPane.add(textTitulo, 0, 0);
        
        hBoxBuscar = new HBox(10);
        
        textFieldBuscar = new TextField();
        textFieldBuscar.setPromptText("Buscar Grado");
        textFieldBuscar.textProperty().addListener((newValue) -> {
            actualizarTableBusqueda(textFieldBuscar.getText().trim());
        });
        
        hBoxBuscar.getChildren().addAll(textFieldBuscar);
        gridPane.add(hBoxBuscar, 0, 1);
        
        hBoxButtons = new HBox(10);
        
        buttonNuevo = new Button("Nuevo");
        buttonNuevo.setId("buttonNuevo");
        buttonNuevo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                hBoxCRUD.getChildren().clear();
                hBoxCRUD.getChildren().addAll(gridPane, AgregarGrado.getInstance().getGridPane());
            }
        });
        
        buttonModificar = new Button("Modificar");
        buttonModificar.setId("buttonModificar");
        buttonModificar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                hBoxCRUD.getChildren().clear();
                if (tableViewGrado.getSelectionModel().getSelectedItem() != null) {
                    hBoxCRUD.getChildren().addAll(gridPane, ModificarGrado.getInstance().getGridPane((Grado) tableViewGrado.getSelectionModel().getSelectedItem()));
                } else {
                    hBoxCRUD.getChildren().add(gridPane);
                }
            }
        });
        
        buttonEliminar = new Button("Eliminar");
        buttonEliminar.setId("buttonEliminar");
        buttonEliminar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (tableViewGrado.getSelectionModel().getSelectedItem() != null) {
                    ControladorGrado.getInstance().eliminarGrado(tableViewGrado.getSelectionModel().getSelectedItem().getIdGrado());
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
                hBoxCRUD.getChildren().clear();
                if (tableViewGrado.getSelectionModel().getSelectedItem() != null) {
                    hBoxCRUD.getChildren().addAll(gridPane, VerGrado.getInstance().getGridPane((Grado) tableViewGrado.getSelectionModel().getSelectedItem()));
                } else {
                    hBoxCRUD.getChildren().add(gridPane);
                }
            }
        });
        
        hBoxButtons.getChildren().addAll(buttonNuevo, buttonModificar, buttonEliminar, buttonActualizar, 
                buttonVer);
        gridPane.add(hBoxButtons, 0, 2);
        
        tableColumnIdGrado = new TableColumn<>();
        tableColumnIdGrado.setText("ID Grado");
        tableColumnIdGrado.setCellValueFactory(new PropertyValueFactory<>("idGrado") );
        tableColumnIdGrado.setMinWidth(100);
        
        tableColumnNombre = new TableColumn<>();
        tableColumnNombre.setText("Grados");
        tableColumnNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tableColumnNombre.setMinWidth(125);
        
        tableColumnDescripcion = new TableColumn<>();
        tableColumnDescripcion.setText("Descripción");
        tableColumnDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        tableColumnDescripcion.setMinWidth(275);
        
        actualizarObservableList();
        tableViewGrado = new TableView<>(observableList);
        tableViewGrado.getColumns().addAll(tableColumnIdGrado, tableColumnNombre,
                tableColumnDescripcion);
        
        gridPane.add(tableViewGrado, 0, 3, 2, 1);
       
        hBoxCRUD.getChildren().add(gridPane);
        return hBoxCRUD;
    }
    public void actualizarTableViewItems() {
        actualizarObservableList();
        tableViewGrado.setItems(observableList);
    }
       
    public void actualizarObservableList() {
        observableList = FXCollections.observableArrayList(ControladorGrado.getInstance().getArrayList());
    }
    
    public void actualizarTableBusqueda(String nombre) {
        observableList = FXCollections.observableArrayList(ControladorGrado.getInstance().search(nombre));
        tableViewGrado.setItems(observableList);
    }
}

class AgregarGrado {
    private static AgregarGrado instance;
    private GridPane gridPane;
    private Text textTitulo;
    private Label labelNombre;
    private TextField textFieldNombre;
    private Label labelDescripcion;
    private TextField textFieldDescripcion;
    private Button buttonAgregar;
    private Button buttonCerrar;

    private AgregarGrado() {
    }

    public static AgregarGrado getInstance() {
        if (instance == null) {
            instance = new AgregarGrado();
        }
        return instance;
    }

    public GridPane getGridPane() {
        gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setGridLinesVisible(false);
        gridPane.setPadding(new Insets(25, 25, 25, 25));
        
        textTitulo = new Text("Agregar Grado");
        textTitulo.setId("titulo");
        textTitulo.setFill(Color.WHITESMOKE);
        gridPane.add(textTitulo, 0, 0);
        
        labelNombre = new Label("Nombre :");
        labelNombre.setId("labels");
        gridPane.add(labelNombre, 0, 1);
        
        textFieldNombre = new TextField();
        textFieldNombre.setPromptText("Nombre del Grado");
        gridPane.add(textFieldNombre, 1, 1, 2, 1);
        
        labelDescripcion = new Label("Descripción :");
        labelDescripcion.setId("labels");
        gridPane.add(labelDescripcion, 0, 2);
                
        textFieldDescripcion = new TextField();
        textFieldDescripcion.setPromptText("Descripción del Grado");
        gridPane.add(textFieldDescripcion, 1, 2, 2, 1);
        
        buttonAgregar = new Button("Agregar");
        buttonAgregar.setId("buttonAgregar");
        buttonAgregar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ControladorGrado.getInstance().agregarGrado(textFieldNombre.getText(), 
                        textFieldDescripcion.getText());
                CRUDGrado.getInstance().reiniciarhBoxCRUD();
                CRUDGrado.getInstance().actualizarTableViewItems();
            }
        });
        
        buttonCerrar = new Button("Cerrar");
        buttonCerrar.setId("buttonCerrar");
        buttonCerrar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                CRUDGrado.getInstance().reiniciarhBoxCRUD();
            }
        });
        
        gridPane.add(buttonAgregar, 1, 3);
        gridPane.add(buttonCerrar, 2, 3 , 2, 1);
        return gridPane;
    }
}

class ModificarGrado {
    private static ModificarGrado instance;
    private GridPane gridPane;
    private Text textTitulo;
    private Label labelNombre;
    private TextField textFieldNombre;
    private Label labelDescripcion;
    private TextField textFieldDescripcion;
    private Button buttonModificar;
    private Button buttonCerrar;

    private ModificarGrado() {
    }

    public static ModificarGrado getInstance() {
        if (instance == null) {
            instance = new ModificarGrado();
        }
        return instance;
    }
    
    public GridPane getGridPane(Grado grado) {
        gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setGridLinesVisible(false);
        gridPane.setPadding(new Insets(25, 25, 25, 25));
        
        textTitulo = new Text("Modificar Grado");
        textTitulo.setId("titulo");
        textTitulo.setFill(Color.WHITESMOKE);
        gridPane.add(textTitulo, 0, 0);
        
        labelNombre = new Label("Nombre :");
        labelNombre.setId("labels");
        gridPane.add(labelNombre, 0, 1);
        
        textFieldNombre = new TextField();
        textFieldNombre.setPromptText("Nombre de Grado");
        textFieldNombre.setText(grado.getNombre());
        gridPane.add(textFieldNombre, 1, 1, 2, 1);
        
        labelDescripcion = new Label("Descripción :");
        labelDescripcion.setId("labels");
        gridPane.add(labelDescripcion, 0, 2);
                
        textFieldDescripcion = new TextField();
        textFieldDescripcion.setPromptText("Descripción de Grado");
        textFieldDescripcion.setText(grado.getDescripcion());
        gridPane.add(textFieldDescripcion, 1, 2, 2, 1);
        
        buttonModificar = new Button("Modificar");
        buttonModificar.setId("buttonModificar");
        buttonModificar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ControladorGrado.getInstance().modificarGrado(textFieldNombre.getText(), 
                        textFieldDescripcion.getText(), 
                        grado.getIdGrado());
                CRUDGrado.getInstance().reiniciarhBoxCRUD();
                CRUDGrado.getInstance().actualizarTableViewItems();
            }
        });
        
        buttonCerrar = new Button("Cerrar");
        buttonCerrar.setId("buttonCerrar");
        buttonCerrar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                CRUDGrado.getInstance().reiniciarhBoxCRUD();
            }
        });
        
        gridPane.add(buttonModificar, 1, 3);
        gridPane.add(buttonCerrar, 2, 3 , 2, 1);
        return gridPane;
    }
}

class VerGrado {
    private static VerGrado instance;
    private GridPane gridPane;
    private Text textTitulo;
    private Label labelNombre;
    private TextField textFieldNombre;
    private Label labelDescripcion;
    private TextField textFieldDescripcion;
    private Button buttonCerrar;

    private VerGrado() {
    }

    public static VerGrado getInstance() {
        if (instance == null) {
            instance = new VerGrado();
        }
        return instance;
    }
    
    public GridPane getGridPane(Grado grado) {
        gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setGridLinesVisible(false);
        gridPane.setPadding(new Insets(25, 25, 25, 25));
        
        textTitulo = new Text("Vista de Grado");
        textTitulo.setId("titulo");
        textTitulo.setFill(Color.WHITESMOKE);
        gridPane.add(textTitulo, 0, 0);
        
        labelNombre = new Label("Nombre :");
        labelNombre.setId("labels");
        gridPane.add(labelNombre, 0, 1);
        
        textFieldNombre = new TextField();
        textFieldNombre.setEditable(false);
        textFieldNombre.setText(grado.getNombre());
        gridPane.add(textFieldNombre, 1, 1, 2, 1);
        
        labelDescripcion = new Label("Descripción :");
        labelDescripcion.setId("labels");
        gridPane.add(labelDescripcion, 0, 2);
                
        textFieldDescripcion = new TextField();
        textFieldDescripcion.setEditable(false);
        textFieldDescripcion.setText(grado.getDescripcion());
        gridPane.add(textFieldDescripcion, 1, 2, 2, 1);
        
        buttonCerrar = new Button("Cerrar");
        buttonCerrar.setId("buttonCerrar");
        buttonCerrar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                CRUDGrado.getInstance().reiniciarhBoxCRUD();
            }
        });
        
        gridPane.add(buttonCerrar, 1, 3);
        return gridPane;
    }
}