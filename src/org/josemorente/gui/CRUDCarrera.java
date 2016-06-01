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
import org.josemorente.beans.Carrera;
import org.josemorente.controlador.ControladorCarrera;
import org.josemorente.controlador.ControladorMateria;

/**
 *
 * @author José Morente
 */
public class CRUDCarrera {
    public static CRUDCarrera instance;
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
    private TableView<Carrera> tableViewCarrera;
    private TableColumn<Carrera, Integer> tableColumnIdCarrera;
    private TableColumn<Carrera, String> tableColumnNombre;
    private TableColumn<Carrera, String> tableColumnDescripcion;
    private ObservableList observableList;

    public CRUDCarrera() {
    }

    public static CRUDCarrera getInstance() {
        if (instance == null) {
            instance = new CRUDCarrera();
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
        
        textTitulo = new Text("Carreras");
        textTitulo.setFill(Color.WHITESMOKE);
        textTitulo.setFont(Font.font(Font.getDefault().getFamily(), 25));
        gridPane.add(textTitulo, 0, 0);
        
        hBoxBuscar = new HBox(10);
        
        textFieldBuscar = new TextField();
        textFieldBuscar.setPromptText("Buscar Carrera");
        
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
        
        buttonNuevo = new Button("Nuevo");
        buttonNuevo.setId("buttonNuevo");
        buttonNuevo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                hBoxCRUD.getChildren().clear();
                hBoxCRUD.getChildren().addAll(gridPane, AgregarCarrera.getInstance().getGridPane());
            }
        });
        
        buttonModificar = new Button("Modificar");
        buttonModificar.setId("buttonModificar");
        buttonModificar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                hBoxCRUD.getChildren().clear();
                if (tableViewCarrera.getSelectionModel().getSelectedItem() != null) {
                    hBoxCRUD.getChildren().addAll(gridPane, ModificarCarrera.getInstance().getGridPane((Carrera) tableViewCarrera.getSelectionModel().getSelectedItem()));
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
                if (tableViewCarrera.getSelectionModel().getSelectedItem() != null) {
                    ControladorCarrera.getInstance().eliminarCarrera(tableViewCarrera.getSelectionModel().getSelectedItem().getIdCarrera());
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
                if (tableViewCarrera.getSelectionModel().getSelectedItem() != null) {
                    hBoxCRUD.getChildren().addAll(gridPane, VerCarrera.getInstance().getGridPane((Carrera) tableViewCarrera.getSelectionModel().getSelectedItem()));
                } else {
                    hBoxCRUD.getChildren().add(gridPane);
                }
            }
        });
        
        hBoxButtons.getChildren().addAll(buttonNuevo, buttonModificar, buttonEliminar, buttonActualizar, 
                buttonVer);
        gridPane.add(hBoxButtons, 0, 2);
        
        tableColumnIdCarrera = new TableColumn<>();
        tableColumnIdCarrera.setText("ID Carrera");
        tableColumnIdCarrera.setCellValueFactory(new PropertyValueFactory<>("idCarrera") );
        tableColumnIdCarrera.setMinWidth(100);
        
        tableColumnNombre = new TableColumn<>();
        tableColumnNombre.setText("Carreras");
        tableColumnNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tableColumnNombre.setMinWidth(125);
        
        tableColumnDescripcion = new TableColumn<>();
        tableColumnDescripcion.setText("Descripción");
        tableColumnDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        tableColumnDescripcion.setMinWidth(275);
        
        actualizarObservableList();
        tableViewCarrera = new TableView<>(observableList);
        tableViewCarrera.getColumns().addAll(tableColumnIdCarrera, tableColumnNombre,
                tableColumnDescripcion);
        
        gridPane.add(tableViewCarrera, 0, 3, 2, 1);
       
        hBoxCRUD.getChildren().add(gridPane);
        
        return hBoxCRUD;
    }
    
    public void actualizarTableViewItems() {
        actualizarObservableList();
        tableViewCarrera.setItems(observableList);
    }
       
    public void actualizarObservableList() {
        observableList = FXCollections.observableArrayList(ControladorCarrera.getInstance().getArrayList());
    }
}

class AgregarCarrera {
    private static AgregarCarrera instance;
    private GridPane gridPane;
    private Text textTitulo;
    private Label labelNombre;
    private TextField textFieldNombre;
    private Label labelDescripcion;
    private TextField textFieldDescripcion;
    private Button buttonAgregar;
    private Button buttonCerrar;

    private AgregarCarrera() {
    }

    public static AgregarCarrera getInstance() {
        if (instance == null) {
            instance = new AgregarCarrera();
        }
        return instance;
    }

    public GridPane getGridPane() {
        gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setGridLinesVisible(false);
        gridPane.setPadding(new Insets(25, 25, 25, 25));
        
        textTitulo = new Text("Agregar Carrera");
        textTitulo.setId("titulo");
        textTitulo.setFill(Color.WHITESMOKE);
        gridPane.add(textTitulo, 0, 0);
        
        labelNombre = new Label("Nombre :");
        labelNombre.setId("labels");
        gridPane.add(labelNombre, 0, 1);
        
        textFieldNombre = new TextField();
        textFieldNombre.setPromptText("Nombre de la Carrera");
        gridPane.add(textFieldNombre, 1, 1, 2, 1);
        
        labelDescripcion = new Label("Descripción :");
        labelDescripcion.setId("labels");
        gridPane.add(labelDescripcion, 0, 2);
                
        textFieldDescripcion = new TextField();
        textFieldDescripcion.setPromptText("Descripción de la Carrera");
        gridPane.add(textFieldDescripcion, 1, 2, 2, 1);
        
        buttonAgregar = new Button("Agregar");
        buttonAgregar.setId("buttonAgregar");
        buttonAgregar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ControladorCarrera.getInstance().agregarCarrera(textFieldNombre.getText(), 
                        textFieldDescripcion.getText());
                CRUDCarrera.getInstance().reiniciarhBoxCRUD();
                CRUDCarrera.getInstance().actualizarTableViewItems();
            }
        });
        
        buttonCerrar = new Button("Cerrar");
        buttonCerrar.setId("buttonCerrar");
        buttonCerrar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               CRUDCarrera.getInstance().reiniciarhBoxCRUD();
            }
        });
        
        gridPane.add(buttonAgregar, 1, 3);
        gridPane.add(buttonCerrar, 2, 3 , 2, 1);
        return gridPane;
    }
}

class ModificarCarrera {
    private static ModificarCarrera instance;
    private GridPane gridPane;
    private Text textTitulo;
    private Label labelNombre;
    private TextField textFieldNombre;
    private Label labelDescripcion;
    private TextField textFieldDescripcion;
    private Button buttonModificar;
    private Button buttonCerrar;

    private ModificarCarrera() {
    }

    public static ModificarCarrera getInstance() {
        if (instance == null) {
            instance = new ModificarCarrera();
        }
        return instance;
    }
    
    public GridPane getGridPane(Carrera carrera) {
        gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setGridLinesVisible(false);
        gridPane.setPadding(new Insets(25, 25, 25, 25));
        
        textTitulo = new Text("Modificar Carrera");
        textTitulo.setId("titulo");
        textTitulo.setFill(Color.WHITESMOKE);
        gridPane.add(textTitulo, 0, 0);
        
        labelNombre = new Label("Nombre :");
        labelNombre.setId("labels");
        gridPane.add(labelNombre, 0, 1);
        
        textFieldNombre = new TextField();
        textFieldNombre.setPromptText("Nombre de Carrera");
        textFieldNombre.setText(carrera.getNombre());
        gridPane.add(textFieldNombre, 1, 1, 2, 1);
        
        labelDescripcion = new Label("Descripción :");
        labelDescripcion.setId("labels");
        gridPane.add(labelDescripcion, 0, 2);
                
        textFieldDescripcion = new TextField();
        textFieldDescripcion.setPromptText("Descripción de Carrera");
        textFieldDescripcion.setText(carrera.getDescripcion());
        gridPane.add(textFieldDescripcion, 1, 2, 2, 1);
        
        buttonModificar = new Button("Modificar");
        buttonModificar.setId("buttonModificar");
        buttonModificar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ControladorCarrera.getInstance().modificarCarrera(textFieldNombre.getText(), 
                        textFieldDescripcion.getText(), 
                        carrera.getIdCarrera());
                CRUDCarrera.getInstance().reiniciarhBoxCRUD();
                CRUDCarrera.getInstance().actualizarTableViewItems();
            }
        });
        
        buttonCerrar = new Button("Cerrar");
        buttonCerrar.setId("buttonCerrar");
        buttonCerrar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                CRUDCarrera.getInstance().reiniciarhBoxCRUD();
            }
        });
        
        gridPane.add(buttonModificar, 1, 3);
        gridPane.add(buttonCerrar, 2, 3 , 2, 1);
        return gridPane;
    }
}

class VerCarrera {
    private static VerCarrera instance;
    private GridPane gridPane;
    private Text textTitulo;
    private Label labelNombre;
    private TextField textFieldNombre;
    private Label labelDescripcion;
    private TextField textFieldDescripcion;
    private Button buttonCerrar;

    private VerCarrera() {
    }

    public static VerCarrera getInstance() {
        if (instance == null) {
            instance = new VerCarrera();
        }
        return instance;
    }
    
    public GridPane getGridPane(Carrera carrera) {
        gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setGridLinesVisible(false);
        gridPane.setPadding(new Insets(25, 25, 25, 25));
        
        textTitulo = new Text("Vista de Carrera");
        textTitulo.setId("titulo");
        textTitulo.setFill(Color.WHITESMOKE);
        gridPane.add(textTitulo, 0, 0);
        
        labelNombre = new Label("Nombre :");
        labelNombre.setId("labels");
        gridPane.add(labelNombre, 0, 1);
        
        textFieldNombre = new TextField();
        textFieldNombre.setEditable(false);
        textFieldNombre.setText(carrera.getNombre());
        gridPane.add(textFieldNombre, 1, 1, 2, 1);
        
        labelDescripcion = new Label("Descripción :");
        labelDescripcion.setId("labels");
        gridPane.add(labelDescripcion, 0, 2);
                
        textFieldDescripcion = new TextField();
        textFieldDescripcion.setEditable(false);
        textFieldDescripcion.setText(carrera.getDescripcion());
        gridPane.add(textFieldDescripcion, 1, 2, 2, 1);
        
        buttonCerrar = new Button("Cerrar");
        buttonCerrar.setId("buttonCerrar");
        buttonCerrar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                CRUDCarrera.getInstance().reiniciarhBoxCRUD();
            }
        });
        
        gridPane.add(buttonCerrar, 1, 3);
        return gridPane;
    }
}