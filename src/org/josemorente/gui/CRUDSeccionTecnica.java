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
import org.josemorente.beans.SeccionTecnica;
import org.josemorente.controlador.ControladorSeccionTecnica;

/**
 *
 * @author José Morente
 */
public class CRUDSeccionTecnica {
    public static CRUDSeccionTecnica instance;
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
    private Button buttonAtras;
    private TableView<SeccionTecnica> tableViewSeccionTecnica;
    private TableColumn<SeccionTecnica, Integer> tableColumnIdSeccion;
    private TableColumn<SeccionTecnica, String> tableColumnNombre;
    private TableColumn<SeccionTecnica, String> tableColumnDescripcion;
    private ObservableList<SeccionTecnica> observableList;

    private CRUDSeccionTecnica() {
    }

    public static CRUDSeccionTecnica getInstance() {
        if (instance == null) {
            instance = new CRUDSeccionTecnica();
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
        
        textTitulo = new Text("Secciones Técnicas");
        textTitulo.setFill(Color.WHITESMOKE);
        textTitulo.setFont(Font.font(Font.getDefault().getFamily(), 25));
        gridPane.add(textTitulo, 0, 0);
        
        hBoxBuscar = new HBox(10);
        
        textFieldBuscar = new TextField();
        textFieldBuscar.setPromptText("Buscar Seccion");
        
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
                hBoxCRUD.getChildren().addAll(gridPane, AgregarSeccionTecnica.getInstance().getGridPane());
            }
        });
        
        buttonModificar = new Button("Modificar");
        buttonModificar.setId("buttonModificar");
        buttonModificar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                hBoxCRUD.getChildren().clear();
                if (tableViewSeccionTecnica.getSelectionModel().getSelectedItem() != null) {
                    hBoxCRUD.getChildren().addAll(gridPane, ModificarSeccionTecnica.getInstance().getGridPane((SeccionTecnica) tableViewSeccionTecnica.getSelectionModel().getSelectedItem()));
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
                if (tableViewSeccionTecnica.getSelectionModel().getSelectedItem() != null) {
                    ControladorSeccionTecnica.getInstance().eliminarST(tableViewSeccionTecnica.getSelectionModel().getSelectedItem().getIdSeccionTecnica());
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
                if (tableViewSeccionTecnica.getSelectionModel().getSelectedItem() != null) {
                    hBoxCRUD.getChildren().addAll(gridPane, VerSeccionTecnica.getInstance().getGridPane((SeccionTecnica) tableViewSeccionTecnica.getSelectionModel().getSelectedItem()));
                } else {
                    hBoxCRUD.getChildren().add(gridPane);
                }
            }
        });
        
        buttonAtras = new Button("<< Atrás");
        buttonAtras.setId("buttonCerrar");
        buttonAtras.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                hBoxCRUD.getChildren().clear();
                hBoxCRUD.getChildren().add(CRUDSecciones.getInstance().gethBoxCRUD());
            }
        });
        
        hBoxButtons.getChildren().addAll(buttonNuevo, buttonModificar, buttonEliminar, buttonActualizar, 
                buttonVer, buttonAtras);
        gridPane.add(hBoxButtons, 0, 2);
        
        tableColumnIdSeccion = new TableColumn<>();
        tableColumnIdSeccion.setText("ID Sección");
        tableColumnIdSeccion.setCellValueFactory(new PropertyValueFactory<>("idSeccionTecnica") );
        tableColumnIdSeccion.setMinWidth(100);
        
        tableColumnNombre = new TableColumn<>();
        tableColumnNombre.setText("Secciones");
        tableColumnNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tableColumnNombre.setMinWidth(125);
        
        tableColumnDescripcion = new TableColumn<>();
        tableColumnDescripcion.setText("Descripción");
        tableColumnDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        tableColumnDescripcion.setMinWidth(275);
        
        actualizarObservableList();
        tableViewSeccionTecnica = new TableView<>(observableList);
        tableViewSeccionTecnica.getColumns().addAll(tableColumnIdSeccion, tableColumnNombre,
                tableColumnDescripcion);
        
        gridPane.add(tableViewSeccionTecnica, 0, 3, 2, 1);
       
        hBoxCRUD.getChildren().add(gridPane);
        return hBoxCRUD;
    }
    
    public void actualizarTableViewItems() {
        actualizarObservableList();
        tableViewSeccionTecnica.setItems(observableList);
    }
       
    public void actualizarObservableList() {
        observableList = FXCollections.observableArrayList(ControladorSeccionTecnica.getInstance().getArrayList());
    }
}

class AgregarSeccionTecnica {
    private static AgregarSeccionTecnica instance;
    private GridPane gridPane;
    private Text textTitulo;
    private Label labelNombre;
    private TextField textFieldNombre;
    private Label labelDescripcion;
    private TextField textFieldDescripcion;
    private Button buttonAgregar;
    private Button buttonCerrar;

    private AgregarSeccionTecnica() {
    }

    public static AgregarSeccionTecnica getInstance() {
        if (instance == null) {
            instance = new AgregarSeccionTecnica();
        }
        return instance;
    }

    public GridPane getGridPane() {
        gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setGridLinesVisible(false);
        gridPane.setPadding(new Insets(25, 25, 25, 25));
        
        textTitulo = new Text("Agregar Sección Técnica");
        textTitulo.setId("titulo");
        textTitulo.setFill(Color.WHITESMOKE);
        gridPane.add(textTitulo, 0, 0, 2, 1);
        
        labelNombre = new Label("Nombre :");
        labelNombre.setId("labels");
        gridPane.add(labelNombre, 0, 1);
        
        textFieldNombre = new TextField();
        textFieldNombre.setPromptText("Nombre de Sección");
        gridPane.add(textFieldNombre, 1, 1, 2, 1);
        
        labelDescripcion = new Label("Descripción :");
        labelDescripcion.setId("labels");
        gridPane.add(labelDescripcion, 0, 2);
                
        textFieldDescripcion = new TextField();
        textFieldDescripcion.setPromptText("Descripción de Sección");
        gridPane.add(textFieldDescripcion, 1, 2, 2, 1);
        
        buttonAgregar = new Button("Agregar");
        buttonAgregar.setId("buttonAgregar");
        buttonAgregar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ControladorSeccionTecnica.getInstance().agregarST(textFieldNombre.getText(), 
                        textFieldDescripcion.getText());
                CRUDSeccionTecnica.getInstance().reiniciarhBoxCRUD();
                CRUDSeccionTecnica.getInstance().actualizarTableViewItems();
            }
        });
        
        buttonCerrar = new Button("Cerrar");
        buttonCerrar.setId("buttonCerrar");
        buttonCerrar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                CRUDSeccionTecnica.getInstance().reiniciarhBoxCRUD();
            }
        });
        
        gridPane.add(buttonAgregar, 1, 3);
        gridPane.add(buttonCerrar, 2, 3 , 2, 1);
        return gridPane;
    }  
}

class ModificarSeccionTecnica {
    private static ModificarSeccionTecnica instance;
    private GridPane gridPane;
    private Text textTitulo;
    private Label labelNombre;
    private TextField textFieldNombre;
    private Label labelDescripcion;
    private TextField textFieldDescripcion;
    private Button buttonModificar;
    private Button buttonCerrar;

    private ModificarSeccionTecnica() {
    }

    public static ModificarSeccionTecnica getInstance() {
        if (instance == null) {
            instance = new ModificarSeccionTecnica();
        }
        return instance;
    }
    
    public GridPane getGridPane(SeccionTecnica seccionTecnica) {
        gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setGridLinesVisible(false);
        gridPane.setPadding(new Insets(25, 25, 25, 25));
        
        textTitulo = new Text("Modificar Sección Técnica");
        textTitulo.setId("titulo");
        textTitulo.setFill(Color.WHITESMOKE);
        gridPane.add(textTitulo, 0, 0, 2, 1);
        
        labelNombre = new Label("Nombre :");
        labelNombre.setId("labels");
        gridPane.add(labelNombre, 0, 1);
        
        textFieldNombre = new TextField();
        textFieldNombre.setPromptText("Nombre de Sección");
        textFieldNombre.setText(seccionTecnica.getNombre());
        gridPane.add(textFieldNombre, 1, 1, 2, 1);
        
        labelDescripcion = new Label("Descripción :");
        labelDescripcion.setId("labels");
        gridPane.add(labelDescripcion, 0, 2);
                
        textFieldDescripcion = new TextField();
        textFieldDescripcion.setPromptText("Descripción de Sección");
        textFieldDescripcion.setText(seccionTecnica.getDescripcion());
        gridPane.add(textFieldDescripcion, 1, 2, 2, 1);
        
        buttonModificar = new Button("Modificar");
        buttonModificar.setId("buttonModificar");
        buttonModificar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ControladorSeccionTecnica.getInstance().modificarST(textFieldNombre.getText(), 
                        textFieldDescripcion.getText(), 
                        seccionTecnica.getIdSeccionTecnica());
                CRUDSeccionTecnica.getInstance().reiniciarhBoxCRUD();
                CRUDSeccionTecnica.getInstance().actualizarTableViewItems();
            }
        });
        
        buttonCerrar = new Button("Cerrar");
        buttonCerrar.setId("buttonCerrar");
        buttonCerrar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                CRUDSeccionTecnica.getInstance().reiniciarhBoxCRUD();
            }
        });
        
        gridPane.add(buttonModificar, 1, 3);
        gridPane.add(buttonCerrar, 2, 3 , 2, 1);
        return gridPane;
    }
}

class VerSeccionTecnica {
    private static VerSeccionTecnica instance;
    private GridPane gridPane;
    private Text textTitulo;
    private Label labelNombre;
    private TextField textFieldNombre;
    private Label labelDescripcion;
    private TextField textFieldDescripcion;
    private Button buttonCerrar;

    private VerSeccionTecnica() {
    }

    public static VerSeccionTecnica getInstance() {
        if (instance == null) {
            instance = new VerSeccionTecnica();
        }
        return instance;
    }
    
    public GridPane getGridPane(SeccionTecnica seccionTecnica) {
        gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setGridLinesVisible(false);
        gridPane.setPadding(new Insets(25, 25, 25, 25));
        
        textTitulo = new Text("Vista de Sección Técnica");
        textTitulo.setId("titulo");
        textTitulo.setFill(Color.WHITESMOKE);
        gridPane.add(textTitulo, 0, 0, 2, 1);
        
        labelNombre = new Label("Nombre :");
        labelNombre.setId("labels");
        gridPane.add(labelNombre, 0, 1);
        
        textFieldNombre = new TextField();
        textFieldNombre.setEditable(false);
        textFieldNombre.setText(seccionTecnica.getNombre());
        gridPane.add(textFieldNombre, 1, 1, 2, 1);
        
        labelDescripcion = new Label("Descripción :");
        labelDescripcion.setId("labels");
        gridPane.add(labelDescripcion, 0, 2);
                
        textFieldDescripcion = new TextField();
        textFieldDescripcion.setEditable(false);
        textFieldDescripcion.setText(seccionTecnica.getDescripcion());
        gridPane.add(textFieldDescripcion, 1, 2, 2, 1);
        
        buttonCerrar = new Button("Cerrar");
        buttonCerrar.setId("buttonCerrar");
        buttonCerrar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                CRUDSeccionTecnica.getInstance().reiniciarhBoxCRUD();
            }
        });
        
        gridPane.add(buttonCerrar, 1, 3);
        return gridPane;
    }
}