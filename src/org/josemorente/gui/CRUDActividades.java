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
import javafx.scene.control.ChoiceBox;
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
import org.josemorente.beans.Actividad;
import org.josemorente.controlador.ControladorActividad;

/**
 *
 * @author José Morente
 */
public class CRUDActividades {
private static CRUDActividades instance;
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
    private TableView<Actividad> tableViewActividad;
    private TableColumn<Actividad, Integer> tableColumnIdActividad;
    private TableColumn<Actividad, String> tableColumnTipoActividad;
    private TableColumn<Actividad, String> tableColumnNombre;
    private TableColumn<Actividad, Integer> tableColumnPunteo;
    private ObservableList<Actividad> observableList;
    
    private CRUDActividades() {
    }

    public static CRUDActividades getInstance() {
        if (instance == null) {
            instance = new CRUDActividades();
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
        
        textTitulo = new Text("Actividades");
        textTitulo.setFill(Color.WHITESMOKE);
        textTitulo.setFont(Font.font(Font.getDefault().getFamily(), 25));
        gridPane.add(textTitulo, 0, 0);
        
        hBoxBuscar = new HBox(10);
        
        textFieldBuscar = new TextField();
        textFieldBuscar.setPromptText("Buscar Materia");
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
                hBoxCRUD.getChildren().addAll(gridPane, AgregarActividad.getInstance().getGridPane());
            };
        });
        
        buttonModificar = new Button("Modificar");
        buttonModificar.setId("buttonModificar");
        buttonModificar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                hBoxCRUD.getChildren().clear();
                if (tableViewActividad.getSelectionModel().getSelectedItem() != null) {
                    hBoxCRUD.getChildren().addAll(gridPane, ModificarActividad.getInstance().getGridPane((Actividad) tableViewActividad.getSelectionModel().getSelectedItem()));
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
                if(tableViewActividad.getSelectionModel().getSelectedItem() != null) {
                    ControladorActividad.getInstance().eliminarActividad(tableViewActividad.getSelectionModel().getSelectedItem().getIdActividad());
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
                if (tableViewActividad.getSelectionModel().getSelectedItem() != null) {
                    hBoxCRUD.getChildren().addAll(gridPane, VerActividad.getInstance().getGridPane((Actividad) tableViewActividad.getSelectionModel().getSelectedItem()));
                } else {
                    hBoxCRUD.getChildren().add(gridPane);
                }
            }
        });
        
        hBoxButtons.getChildren().addAll(buttonNuevo, buttonModificar, buttonEliminar, buttonActualizar, 
                buttonVer);
        gridPane.add(hBoxButtons, 0, 2);
        
        tableColumnIdActividad = new TableColumn<>();
        tableColumnIdActividad.setText("ID Actividad");
        tableColumnIdActividad.setCellValueFactory(new PropertyValueFactory<>("idActividad") );
        tableColumnIdActividad.setMinWidth(100);
        
        tableColumnTipoActividad = new TableColumn<>();
        tableColumnTipoActividad.setText("Tipo de Actividad");
        tableColumnTipoActividad.setCellValueFactory(new PropertyValueFactory<>("tipoActividad"));
        tableColumnTipoActividad.setMinWidth(200);
        
        tableColumnNombre = new TableColumn<>();
        tableColumnNombre.setText("Actividad");
        tableColumnNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tableColumnNombre.setMinWidth(225);
        
        tableColumnPunteo = new TableColumn<>();
        tableColumnPunteo.setText("Punteo");
        tableColumnPunteo.setCellValueFactory(new PropertyValueFactory<>("punteo"));
        tableColumnPunteo.setMinWidth(100);
        
        actualizarObservableList();
        tableViewActividad = new TableView<>(observableList);
        tableViewActividad.getColumns().addAll(tableColumnIdActividad, tableColumnTipoActividad,
                tableColumnNombre , tableColumnPunteo);
        
        gridPane.add(tableViewActividad, 0, 3, 2, 1);
        
        buttonAtras = new Button("<< Atrás");
        buttonAtras.setId("buttonCerrar");
        buttonAtras.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                hBoxCRUD.getChildren().clear();
                hBoxCRUD.getChildren().add(CRUDActividad.getInstance().gethBoxCRUD());
            }
        });
        gridPane.add(buttonAtras, 0, 4);
        hBoxCRUD.getChildren().add(gridPane);
        
        return hBoxCRUD;
    }
    
    public void actualizarTableViewItems() {
        actualizarObservableList();
        tableViewActividad.setItems(observableList);
    }
       
    public void actualizarObservableList() {
        observableList = FXCollections.observableArrayList(ControladorActividad.getInstance().getArrayList());
    }
    
    public void actualizarTableBusqueda(String nombre) {
        observableList = FXCollections.observableArrayList(ControladorActividad.getInstance().search(nombre));
        tableViewActividad.setItems(observableList);
    }
}

class AgregarActividad {
    private static AgregarActividad instance;
    private GridPane gridPane;
    private Text textTitulo;
    private Label labelTipoActividad;
    private ChoiceBox choiceBoxTipoActividad;
    private Label labelNombre;
    private TextField textFieldNombre;
    private Label labelPunteo;
    private TextField textFieldPunteo;
    private Button buttonAgregar;
    private Button buttonCerrar;
    private int x = 0;

    private AgregarActividad() {
    }

    public static AgregarActividad getInstance() {
        if (instance == null) {
            instance = new AgregarActividad();
        }
        return instance;
    }

    public GridPane getGridPane() {
        gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setGridLinesVisible(false);
        gridPane.setPadding(new Insets(25, 25, 25, 25));
        
        textTitulo = new Text("Agregar Materia");
        textTitulo.setId("titulo");
        textTitulo.setFill(Color.WHITESMOKE);
        gridPane.add(textTitulo, 0, 0);
        
        labelTipoActividad = new Label("Nombre :");
        labelTipoActividad.setId("labels");
        gridPane.add(labelTipoActividad, 0, 1);
        
        choiceBoxTipoActividad = new ChoiceBox();
        choiceBoxTipoActividad.getItems().addAll("Hoja de Trabajo", "Tarea", "Investigación", 
                "Exámen Parcial", "PMA", "Exámen Bimestral");
        gridPane.add(choiceBoxTipoActividad, 1, 1, 2, 1);
        
        labelNombre = new Label("Nombre :");
        labelNombre.setId("labels");
        gridPane.add(labelNombre, 0, 2);
        
        textFieldNombre = new TextField();
        textFieldNombre.setPromptText("Nombre de Actividad");
        gridPane.add(textFieldNombre, 1, 2, 2, 1);
        
        labelPunteo = new Label("Valor de Actividad");
        labelPunteo.setId("labels");
        gridPane.add(labelPunteo, 0, 3);
                
        textFieldPunteo = new TextField();
        textFieldPunteo.setPromptText("Punteo");
        gridPane.add(textFieldPunteo, 1, 3, 2, 1);
        
        buttonAgregar = new Button("Agregar");
        buttonAgregar.setId("buttonAgregar");
        buttonAgregar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ControladorActividad.getInstance().agregarActividades(choiceBoxTipoActividad.getSelectionModel().getSelectedItem().toString(), 
                        textFieldNombre.getText(), 
                        x = Integer.parseInt(textFieldPunteo.getText()));
                CRUDActividades.getInstance().reiniciarhBoxCRUD();
                CRUDActividades.getInstance().actualizarTableViewItems();
            }
        });
        
        buttonCerrar = new Button("Cerrar");
        buttonCerrar.setId("buttonCerrar");
        buttonCerrar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                CRUDActividades.getInstance().reiniciarhBoxCRUD();
            }
        });
        
        gridPane.add(buttonAgregar, 1, 4);
        gridPane.add(buttonCerrar, 2, 4, 2, 1);
        return gridPane;
    }
    
}

class ModificarActividad {
    private static ModificarActividad instance;
    private GridPane gridPane;
    private Text textTitulo;
    private Label labelTipoActividad;
    private ChoiceBox choiceBoxTipoActividad;
    private Label labelNombre;
    private TextField textFieldNombre;
    private Label labelPunteo;
    private TextField textFieldPunteo;
    private Button buttonModificar;
    private Button buttonCerrar;
    private int y = 0;

    private ModificarActividad() {
    }

    public static ModificarActividad getInstance() {
        if (instance == null) {
            instance = new ModificarActividad();
        }
        return instance;
    }

    public GridPane getGridPane(Actividad actividad) {
        gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setGridLinesVisible(false);
        gridPane.setPadding(new Insets(25, 25, 25, 25));
        
        textTitulo = new Text("Agregar Materia");
        textTitulo.setId("titulo");
        textTitulo.setFill(Color.WHITESMOKE);
        gridPane.add(textTitulo, 0, 0);
        
        labelTipoActividad = new Label("Nombre :");
        labelTipoActividad.setId("labels");
        gridPane.add(labelTipoActividad, 0, 1);
        
        choiceBoxTipoActividad = new ChoiceBox();
        choiceBoxTipoActividad.setValue(actividad.getTipoActividad());
        choiceBoxTipoActividad.getItems().addAll("Hoja de Trabajo", "Tarea", "Investigación", 
                "Exámen Parcial", "PMA", "Exámen Bimestral");
        gridPane.add(choiceBoxTipoActividad, 1, 1, 2, 1);
        
        labelNombre = new Label("Nombre :");
        labelNombre.setId("labels");
        gridPane.add(labelNombre, 0, 2);
        
        textFieldNombre = new TextField();
        textFieldNombre.setText(actividad.getNombre());
        textFieldNombre.setPromptText("Nombre de Actividad");
        gridPane.add(textFieldNombre, 1, 2, 2, 1);
        
        labelPunteo = new Label("Valor de Actividad");
        labelPunteo.setId("labels");
        gridPane.add(labelPunteo, 0, 3);
                
        textFieldPunteo = new TextField();
        String x = String.valueOf(actividad.getPunteo());
        textFieldPunteo.setText(x);
        textFieldPunteo.setPromptText("Punteo");
        gridPane.add(textFieldPunteo, 1, 3, 2, 1);
        
        buttonModificar = new Button("Modificar");
        buttonModificar.setId("buttonModificar");
        buttonModificar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ControladorActividad.getInstance().modificarActividades(choiceBoxTipoActividad.getSelectionModel().getSelectedItem().toString(), 
                        textFieldNombre.getText(), 
                        y = Integer.parseInt(textFieldPunteo.getText()), 
                        actividad.getIdActividad());
                CRUDActividades.getInstance().reiniciarhBoxCRUD();
                CRUDActividades.getInstance().actualizarTableViewItems();
            }
        });
        
        buttonCerrar = new Button("Cerrar");
        buttonCerrar.setId("buttonCerrar");
        buttonCerrar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                CRUDActividades.getInstance().reiniciarhBoxCRUD();
            }
        });
        
        gridPane.add(buttonModificar, 1, 4);
        gridPane.add(buttonCerrar, 2, 4, 2, 1);
        return gridPane;
    }
}

class VerActividad {
    private static VerActividad instance;
    private GridPane gridPane;
    private Text textTitulo;
    private Label labelTipoActividad;
    private TextField textFieldTipoActividad;
    private Label labelNombre;
    private TextField textFieldNombre;
    private Label labelPunteo;
    private TextField textFieldPunteo;
    private Button buttonModificar;
    private Button buttonCerrar;
    private int y = 0;

    private VerActividad() {
    }

    public static VerActividad getInstance() {
        if (instance == null) {
            instance = new VerActividad();
        }
        return instance;
    }

    public GridPane getGridPane(Actividad actividad) {
        gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setGridLinesVisible(false);
        gridPane.setPadding(new Insets(25, 25, 25, 25));
        
        textTitulo = new Text("Agregar Materia");
        textTitulo.setId("titulo");
        textTitulo.setFill(Color.WHITESMOKE);
        gridPane.add(textTitulo, 0, 0);
        
        labelTipoActividad = new Label("Nombre :");
        labelTipoActividad.setId("labels");
        gridPane.add(labelTipoActividad, 0, 1);
        
        textFieldTipoActividad = new TextField();
        textFieldTipoActividad.setText(actividad.getTipoActividad());
        textFieldTipoActividad.setEditable(false);
        gridPane.add(textFieldTipoActividad, 1, 1, 2, 1);
        
        labelNombre = new Label("Nombre :");
        labelNombre.setId("labels");
        gridPane.add(labelNombre, 0, 2);
        
        textFieldNombre = new TextField();
        textFieldNombre.setText(actividad.getNombre());
        textFieldNombre.setEditable(false);
        gridPane.add(textFieldNombre, 1, 2, 2, 1);
        
        labelPunteo = new Label("Valor de Actividad");
        labelPunteo.setId("labels");
        gridPane.add(labelPunteo, 0, 3);
                
        textFieldPunteo = new TextField();
        String x = String.valueOf(actividad.getPunteo());
        textFieldPunteo.setText(x);
        textFieldPunteo.setEditable(false);
        gridPane.add(textFieldPunteo, 1, 3, 2, 1);
        
        buttonCerrar = new Button("Cerrar");
        buttonCerrar.setId("buttonCerrar");
        buttonCerrar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                CRUDActividades.getInstance().reiniciarhBoxCRUD();
            }
        });
        
        gridPane.add(buttonCerrar, 1, 4, 2, 1);
        return gridPane;
    }
}