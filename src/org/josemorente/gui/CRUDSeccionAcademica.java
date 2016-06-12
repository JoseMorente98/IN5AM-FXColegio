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
import org.josemorente.beans.SeccionAcademica;
import org.josemorente.controlador.ControladorSeccionAcademica;

/**
 *
 * @author José Morente
 */
public class CRUDSeccionAcademica {
   public static CRUDSeccionAcademica instance;
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
    private TableView<SeccionAcademica> tableViewSeccionAcademica;
    private TableColumn<SeccionAcademica, Integer> tableColumnIdSeccion;
    private TableColumn<SeccionAcademica, String> tableColumnNombre;
    private TableColumn<SeccionAcademica, String> tableColumnDescripcion;
    private ObservableList<SeccionAcademica> observableList;

    private CRUDSeccionAcademica() {
    }

    public static CRUDSeccionAcademica getInstance() {
        if (instance == null) {
            instance = new CRUDSeccionAcademica();
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
        
        textTitulo = new Text("Secciones Académicas");
        textTitulo.setFill(Color.WHITESMOKE);
        textTitulo.setFont(Font.font(Font.getDefault().getFamily(), 25));
        gridPane.add(textTitulo, 0, 0);
        
        hBoxBuscar = new HBox(10);
        
        textFieldBuscar = new TextField();
        textFieldBuscar.setPromptText("Buscar Seccion");
        
        hBoxBuscar.getChildren().addAll(textFieldBuscar);
        gridPane.add(hBoxBuscar, 0, 1);
        
        hBoxButtons = new HBox(10);
        
        buttonNuevo = new Button("Nuevo");
        buttonNuevo.setId("buttonNuevo");
        buttonNuevo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                hBoxCRUD.getChildren().clear();
                hBoxCRUD.getChildren().addAll(gridPane, AgregarSeccionAcademica.getInstance().getGridPane());
            }
        });
        
        buttonModificar = new Button("Modificar");
        buttonModificar.setId("buttonModificar");
        buttonModificar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                hBoxCRUD.getChildren().clear();
                if (tableViewSeccionAcademica.getSelectionModel().getSelectedItem() != null) {
                    hBoxCRUD.getChildren().addAll(gridPane, ModificarSeccionAcademica.getInstance().getGridPane((SeccionAcademica) tableViewSeccionAcademica.getSelectionModel().getSelectedItem()));
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
                if (tableViewSeccionAcademica.getSelectionModel().getSelectedItem() != null) {
                    ControladorSeccionAcademica.getInstance().eliminarSA(tableViewSeccionAcademica.getSelectionModel().getSelectedItem().getIdSeccionAcademica());
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
                if (tableViewSeccionAcademica.getSelectionModel().getSelectedItem() != null) {
                    hBoxCRUD.getChildren().addAll(gridPane, VerSeccionAcademica.getInstance().getGridPane((SeccionAcademica) tableViewSeccionAcademica.getSelectionModel().getSelectedItem()));
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
        tableColumnIdSeccion.setCellValueFactory(new PropertyValueFactory<>("idSeccionAcademica") );
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
        tableViewSeccionAcademica = new TableView<>(observableList);
        tableViewSeccionAcademica.getColumns().addAll(tableColumnIdSeccion, tableColumnNombre,
                tableColumnDescripcion);
        
        gridPane.add(tableViewSeccionAcademica, 0, 3, 2, 1);
       
        hBoxCRUD.getChildren().add(gridPane);
        return hBoxCRUD;
    }
    
    public void actualizarTableViewItems() {
        actualizarObservableList();
        tableViewSeccionAcademica.setItems(observableList);
    }
       
    public void actualizarObservableList() {
        observableList = FXCollections.observableArrayList(ControladorSeccionAcademica.getInstance().getArrayList());
    }
}

class AgregarSeccionAcademica {
    private static AgregarSeccionAcademica instance;
    private GridPane gridPane;
    private Text textTitulo;
    private Label labelNombre;
    private TextField textFieldNombre;
    private Label labelDescripcion;
    private TextField textFieldDescripcion;
    private Button buttonAgregar;
    private Button buttonCerrar;

    private AgregarSeccionAcademica() {
    }

    public static AgregarSeccionAcademica getInstance() {
        if (instance == null) {
            instance = new AgregarSeccionAcademica();
        }
        return instance;
    }

    public GridPane getGridPane() {
        gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setGridLinesVisible(false);
        gridPane.setPadding(new Insets(25, 25, 25, 25));
        
        textTitulo = new Text("Agregar Sección Académica");
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
                ControladorSeccionAcademica.getInstance().agregarSA(textFieldNombre.getText(), 
                        textFieldDescripcion.getText());
                CRUDSeccionAcademica.getInstance().reiniciarhBoxCRUD();
                CRUDSeccionAcademica.getInstance().actualizarTableViewItems();
            }
        });
        
        buttonCerrar = new Button("Cerrar");
        buttonCerrar.setId("buttonCerrar");
        buttonCerrar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                CRUDSeccionAcademica.getInstance().reiniciarhBoxCRUD();
            }
        });
        
        gridPane.add(buttonAgregar, 1, 3);
        gridPane.add(buttonCerrar, 2, 3 , 2, 1);
        return gridPane;
    }  
}

class ModificarSeccionAcademica {
    private static ModificarSeccionAcademica instance;
    private GridPane gridPane;
    private Text textTitulo;
    private Label labelNombre;
    private TextField textFieldNombre;
    private Label labelDescripcion;
    private TextField textFieldDescripcion;
    private Button buttonModificar;
    private Button buttonCerrar;

    private ModificarSeccionAcademica() {
    }

    public static ModificarSeccionAcademica getInstance() {
        if (instance == null) {
            instance = new ModificarSeccionAcademica();
        }
        return instance;
    }
    
    public GridPane getGridPane(SeccionAcademica seccionAcademica) {
        gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setGridLinesVisible(false);
        gridPane.setPadding(new Insets(25, 25, 25, 25));
        
        textTitulo = new Text("Modificar Sección Académica");
        textTitulo.setId("titulo");
        textTitulo.setFill(Color.WHITESMOKE);
        gridPane.add(textTitulo, 0, 0, 2, 1);
        
        labelNombre = new Label("Nombre :");
        labelNombre.setId("labels");
        gridPane.add(labelNombre, 0, 1);
        
        textFieldNombre = new TextField();
        textFieldNombre.setPromptText("Nombre de Sección");
        textFieldNombre.setText(seccionAcademica.getNombre());
        gridPane.add(textFieldNombre, 1, 1, 2, 1);
        
        labelDescripcion = new Label("Descripción :");
        labelDescripcion.setId("labels");
        gridPane.add(labelDescripcion, 0, 2);
                
        textFieldDescripcion = new TextField();
        textFieldDescripcion.setPromptText("Descripción de Sección");
        textFieldDescripcion.setText(seccionAcademica.getDescripcion());
        gridPane.add(textFieldDescripcion, 1, 2, 2, 1);
        
        buttonModificar = new Button("Modificar");
        buttonModificar.setId("buttonModificar");
        buttonModificar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ControladorSeccionAcademica.getInstance().modificarSA(textFieldNombre.getText(), 
                        textFieldDescripcion.getText(), 
                        seccionAcademica.getIdSeccionAcademica());
                CRUDSeccionAcademica.getInstance().reiniciarhBoxCRUD();
                CRUDSeccionAcademica.getInstance().actualizarTableViewItems();
            }
        });
        
        buttonCerrar = new Button("Cerrar");
        buttonCerrar.setId("buttonCerrar");
        buttonCerrar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                CRUDSeccionAcademica.getInstance().reiniciarhBoxCRUD();
            }
        });
        
        gridPane.add(buttonModificar, 1, 3);
        gridPane.add(buttonCerrar, 2, 3 , 2, 1);
        return gridPane;
    }
}

class VerSeccionAcademica {
    private static VerSeccionAcademica instance;
    private GridPane gridPane;
    private Text textTitulo;
    private Label labelNombre;
    private TextField textFieldNombre;
    private Label labelDescripcion;
    private TextField textFieldDescripcion;
    private Button buttonCerrar;

    private VerSeccionAcademica() {
    }

    public static VerSeccionAcademica getInstance() {
        if (instance == null) {
            instance = new VerSeccionAcademica();
        }
        return instance;
    }
    
    public GridPane getGridPane(SeccionAcademica seccionAcademica) {
        gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setGridLinesVisible(false);
        gridPane.setPadding(new Insets(25, 25, 25, 25));
        
        textTitulo = new Text("Vista de Sección Académica");
        textTitulo.setId("titulo");
        textTitulo.setFill(Color.WHITESMOKE);
        gridPane.add(textTitulo, 0, 0, 2, 1);
        
        labelNombre = new Label("Nombre :");
        labelNombre.setId("labels");
        gridPane.add(labelNombre, 0, 1);
        
        textFieldNombre = new TextField();
        textFieldNombre.setEditable(false);
        textFieldNombre.setText(seccionAcademica.getNombre());
        gridPane.add(textFieldNombre, 1, 1, 2, 1);
        
        labelDescripcion = new Label("Descripción :");
        labelDescripcion.setId("labels");
        gridPane.add(labelDescripcion, 0, 2);
                
        textFieldDescripcion = new TextField();
        textFieldDescripcion.setEditable(false);
        textFieldDescripcion.setText(seccionAcademica.getDescripcion());
        gridPane.add(textFieldDescripcion, 1, 2, 2, 1);
        
        buttonCerrar = new Button("Cerrar");
        buttonCerrar.setId("buttonCerrar");
        buttonCerrar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                CRUDSeccionAcademica.getInstance().reiniciarhBoxCRUD();
            }
        });
        
        gridPane.add(buttonCerrar, 1, 3);
        return gridPane;
    }
}