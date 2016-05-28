/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.josemorente.gui;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
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
import org.josemorente.beans.Profesor;
import org.josemorente.controlador.ControladorProfesor;

/**
 *
 * @author José Morentes
 */
public class CRUDProfesor {
    private static CRUDProfesor instance;
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
    private TableView<Profesor> tableViewProfesor;
    private TableColumn<Profesor, Integer> tableColumnIdProfesor;
    private TableColumn<Profesor, String> tableColumnNombre;
    private TableColumn<Profesor, String> tableColumnApellidos;
    private TableColumn<Profesor, String> tableColumnFechaNacimiento;
    private TableColumn<Profesor, String> tableColumnDpi;
    private TableColumn<Profesor, String> tableColumnDireccion;
    private TableColumn<Profesor, Integer> tableColumnTelefono;
    private ObservableList<Profesor> observableList;
    
    private CRUDProfesor() {
    }

    public static CRUDProfesor getInstance() {
        if (instance == null ) {
            instance = new CRUDProfesor();
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
        
        textTitulo = new Text("Profesores");
        textTitulo.setFill(Color.WHITESMOKE);
        textTitulo.setFont(Font.font(Font.getDefault().getFamily(), 25));
        gridPane.add(textTitulo, 0, 0);
        
        hBoxBuscar = new HBox(10);
        
        textFieldBuscar = new TextField();
        textFieldBuscar.setPromptText("Buscar Profesor");
        
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
                hBoxCRUD.getChildren().addAll(gridPane, CrearProfesor.getInstance().getGridPane());
            }
        });
        
        buttonModificar = new Button("Modificar");
        buttonModificar.setId("buttonModificar");
        buttonModificar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                hBoxCRUD.getChildren().clear();
                if (tableViewProfesor.getSelectionModel().getSelectedItem() != null) {
                    hBoxCRUD.getChildren().addAll(gridPane, ModificarProfesor.getInstance().getGridPane((Profesor) tableViewProfesor.getSelectionModel().getSelectedItem()));
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
                if (tableViewProfesor.getSelectionModel().getSelectedItem() != null) {
                    ControladorProfesor.getInstance().eliminar(tableViewProfesor.getSelectionModel().getSelectedItem().getIdProfesor());
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
                if (tableViewProfesor.getSelectionModel().getSelectedItem() != null) {
                    hBoxCRUD.getChildren().addAll(gridPane, VerProfesor.getInstance().getGridPane((Profesor) tableViewProfesor.getSelectionModel().getSelectedItem()));
                } else {
                    hBoxCRUD.getChildren().add(gridPane);
                }
            }
        });
        
        hBoxButtons.getChildren().addAll(buttonNuevo, buttonModificar, buttonEliminar, buttonActualizar, 
                buttonVer);
        gridPane.add(hBoxButtons, 0, 2);

        tableColumnIdProfesor = new TableColumn<>();
        tableColumnIdProfesor.setText("ID Profesor");
        tableColumnIdProfesor.setCellValueFactory(new PropertyValueFactory<>("idProfesor") );
        tableColumnIdProfesor.setMinWidth(50);
        
        tableColumnNombre = new TableColumn<>();
        tableColumnNombre.setText("Nombres");
        tableColumnNombre.setCellValueFactory(new PropertyValueFactory<>("nombres"));
        tableColumnNombre.setMinWidth(125);
        
        tableColumnApellidos = new TableColumn<>();
        tableColumnApellidos.setText("Apellidos");
        tableColumnApellidos.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        tableColumnApellidos.setMinWidth(125);
        
        tableColumnFechaNacimiento = new TableColumn<>();
        tableColumnFechaNacimiento.setText("Fecha de Nacimiento");
        tableColumnFechaNacimiento.setCellValueFactory(new PropertyValueFactory<>("fechaNacimiento"));
        tableColumnFechaNacimiento.setMinWidth(100);
        
        tableColumnDpi = new TableColumn<>();
        tableColumnDpi.setText("DPI");
        tableColumnDpi.setCellValueFactory(new PropertyValueFactory<>("dpi"));
        tableColumnDpi.setMinWidth(125);

        tableColumnTelefono = new TableColumn<>();
        tableColumnTelefono.setText("No. Teléfono");
        tableColumnTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        tableColumnTelefono.setMinWidth(75);
        
        tableColumnDireccion = new TableColumn<>();
        tableColumnDireccion.setText("Dirección");
        tableColumnDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        tableColumnDireccion.setMinWidth(125);
        
        actualizarObservableList();
        tableViewProfesor = new TableView<>(observableList);
        tableViewProfesor.getColumns().addAll(tableColumnIdProfesor, tableColumnNombre,
                tableColumnApellidos, tableColumnFechaNacimiento, tableColumnDpi, 
                tableColumnTelefono, tableColumnDireccion);
        
        gridPane.add(tableViewProfesor, 0, 3, 2, 1);
       
        hBoxCRUD.getChildren().add(gridPane);
        
        return hBoxCRUD;
    }
    
    public void actualizarTableViewItems() {
        actualizarObservableList();
        tableViewProfesor.setItems(observableList);
    }
       
    public void actualizarObservableList() {
        observableList = FXCollections.observableArrayList(ControladorProfesor.getInstance().getArrayList());
    }
    
}

class CrearProfesor {
    private static CrearProfesor instance;
    private GridPane gridPane;
    private Text textTitulo;
    private Label labelNombre;
    private TextField textFieldNombre;
    private Label labelApellido;
    private TextField textFieldApellido;
    private Label labelDpi;
    private TextField textFieldDpi;
    private Label labelTelefono;
    private TextField textFieldTelefono;
    private Label labelDireccion;
    private TextField textFieldDireccion;
    private Label labelFecha;
     private DatePicker datePicker;
    private Button buttonAgregar;
    private Button buttonCerrar;
    private Date date;
    private int a = 0;
    
    private CrearProfesor() {
    }
    
    public static CrearProfesor getInstance() {
        if (instance == null) {
            instance = new CrearProfesor();
        }
        return instance;
    }

    public GridPane getGridPane() {
        gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setGridLinesVisible(false);
        gridPane.setPadding(new Insets(25, 25, 25, 25));
        
        textTitulo = new Text("Agregar Profesor");
        textTitulo.setId("titulo");
        textTitulo.setFill(Color.WHITESMOKE);
        gridPane.add(textTitulo, 0, 0);
        
        labelNombre = new Label("Nombres :");
        labelNombre.setId("labels");
        gridPane.add(labelNombre, 0, 1);
        
        textFieldNombre = new TextField();
        textFieldNombre.setPromptText("Nombres del Profesor");
        gridPane.add(textFieldNombre, 1, 1, 2, 1);
        
        labelApellido = new Label("Apellidos :");
        labelApellido.setId("labels");
        gridPane.add(labelApellido, 0, 2);
                
        textFieldApellido = new TextField();
        textFieldApellido.setPromptText("Apellidos del Profesor");
        gridPane.add(textFieldApellido, 1, 2, 2, 1);
        
        labelFecha = new Label("Fecha de Nacimiento :");
        labelFecha.setId("labels");
        gridPane.add(labelFecha, 0, 3);
        
        datePicker = new DatePicker();
        datePicker.setPromptText("Fecha de Nacimiento");
        gridPane.add(datePicker, 1, 3, 2, 1);
        
        labelDpi = new Label("DPI :");
        labelDpi.setId("labels");
        gridPane.add(labelDpi, 0, 4);
        
        textFieldDpi = new TextField();
        textFieldDpi.setPromptText("DPI del Profesor");
        gridPane.add(textFieldDpi, 1, 4, 2, 1);
        
        labelTelefono = new Label("No. Teléfono :");
        labelTelefono.setId("labels");
        gridPane.add(labelTelefono, 0, 5);
        
        textFieldTelefono = new TextField();
        textFieldTelefono.setPromptText("Teléfono del Profesor");
        gridPane.add(textFieldTelefono, 1, 5, 2, 1);
        
        labelDireccion = new Label("Dirección :");
        labelDireccion.setId("labels");
        gridPane.add(labelDireccion, 0, 6);
        
        textFieldDireccion = new TextField();
        textFieldDireccion.setPromptText("Dirección del Profesor");
        gridPane.add(textFieldDireccion, 1, 6, 2, 1);
        
        buttonAgregar = new Button("Agregar");
        buttonAgregar.setId("buttonAgregar");
        buttonAgregar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ControladorProfesor.getInstance().agregar(textFieldNombre.getText(),
                            textFieldApellido.getText(),
                            datePicker.getValue(),
                            textFieldDpi.getText(),
                            a = Integer.parseInt(textFieldTelefono.getText()),
                            textFieldDireccion.getText());
                CRUDProfesor.getInstance().reiniciarhBoxCRUD();
                CRUDProfesor.getInstance().actualizarTableViewItems();
            }
        });
        
        buttonCerrar = new Button("Cerrar");
        buttonCerrar.setId("buttonCerrar");
        buttonCerrar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                CRUDProfesor.getInstance().reiniciarhBoxCRUD();
            }
        });
        
        gridPane.add(buttonAgregar, 1, 7);
        gridPane.add(buttonCerrar, 2, 7, 2, 1);
        
        return gridPane;
    } 
}

class ModificarProfesor{
    private static ModificarProfesor instance;
    private GridPane gridPane;
    private Text textTitulo;
    private Label labelNombre;
    private TextField textFieldNombre;
    private Label labelApellido;
    private TextField textFieldApellido;
    private Label labelDpi;
    private TextField textFieldDpi;
    private Label labelTelefono;
    private TextField textFieldTelefono;
    private Label labelDireccion;
    private TextField textFieldDireccion;
    private Label labelFecha;
     private DatePicker datePicker;
    private Button buttonModificar;
    private Button buttonCerrar;
    private String stringTelefono;
    private Date date;
    private int a = 0;

    public ModificarProfesor() {
    }

    public static ModificarProfesor getInstance() {
        if (instance == null) {
            instance = new ModificarProfesor();
        }
        return instance;
    }

    public GridPane getGridPane(Profesor profesor) {
        gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setGridLinesVisible(false);
        gridPane.setPadding(new Insets(25, 25, 25, 25));
        
        textTitulo = new Text("Modificar Profesor");
        textTitulo.setId("titulo");
        textTitulo.setFill(Color.WHITESMOKE);
        gridPane.add(textTitulo, 0, 0);
        
        labelNombre = new Label("Nombres :");
        labelNombre.setId("labels");
        gridPane.add(labelNombre, 0, 1);
        
        textFieldNombre = new TextField();
        textFieldNombre.setPromptText("Nombres del Profesor");
        textFieldNombre.setText(profesor.getNombres());
        gridPane.add(textFieldNombre, 1, 1, 2, 1);
        
        labelApellido = new Label("Apellidos :");
        labelApellido.setId("labels");
        gridPane.add(labelApellido, 0, 2);
                
        textFieldApellido = new TextField();
        textFieldApellido.setPromptText("Apellidos del Profesor");
        textFieldApellido.setText(profesor.getApellidos());
        gridPane.add(textFieldApellido, 1, 2, 2, 1);
        
        labelFecha = new Label("Fecha de Nacimiento :");
        labelFecha.setId("labels");
        gridPane.add(labelFecha, 0, 3);
        
        datePicker = new DatePicker();
        datePicker.setPromptText("Fecha de Nacimiento");
        date = new Date(); 
        date = profesor.getFechaNacimiento();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        LocalDate localDateNuevo = LocalDate.of(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH) + 1,
                calendar.get(Calendar.DAY_OF_MONTH));
        datePicker.setValue(localDateNuevo);
        gridPane.add(datePicker, 1, 3, 2, 1);
        
        labelDpi = new Label("DPI :");
        labelDpi.setId("labels");
        gridPane.add(labelDpi, 0, 4);
        
        textFieldDpi = new TextField();
        textFieldDpi.setPromptText("DPI del Profesor");
        textFieldDpi.setText(profesor.getDpi());
        gridPane.add(textFieldDpi, 1, 4, 2, 1);
        
        labelTelefono = new Label("No. Teléfono :");
        labelTelefono.setId("labels");
        gridPane.add(labelTelefono, 0, 5);
        
        textFieldTelefono = new TextField();
        textFieldTelefono.setPromptText("Teléfono del Profesor");
        stringTelefono = String.valueOf(profesor.getTelefono());
        textFieldTelefono.setText(stringTelefono);
        gridPane.add(textFieldTelefono, 1, 5, 2, 1);
        
        labelDireccion = new Label("Dirección :");
        labelDireccion.setId("labels");
        gridPane.add(labelDireccion, 0, 6);
        
        textFieldDireccion = new TextField();
        textFieldDireccion.setPromptText("Dirección del Profesor");
        textFieldDireccion.setText(profesor.getDireccion());
        gridPane.add(textFieldDireccion, 1, 6, 2, 1);
        
        buttonModificar = new Button("Modificar");
        buttonModificar.setId("buttonModificar");
        buttonModificar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ControladorProfesor.getInstance().modificarProfesor(textFieldNombre.getText(), 
                        textFieldApellido.getText(), 
                        datePicker.getValue(), 
                        textFieldDpi.getText(), 
                        a = Integer.parseInt(textFieldTelefono.getText()), 
                        textFieldDireccion.getText(), 
                        profesor.getIdProfesor());
                CRUDProfesor.getInstance().reiniciarhBoxCRUD();
                CRUDProfesor.getInstance().actualizarTableViewItems();
            }
        });
        
        buttonCerrar = new Button("Cerrar");
        buttonCerrar.setId("buttonCerrar");
        buttonCerrar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                CRUDProfesor.getInstance().reiniciarhBoxCRUD();
            }
        });
        
        gridPane.add(buttonModificar, 1, 7);
        gridPane.add(buttonCerrar, 2, 7, 2, 1);
        
        return gridPane;
    }

}

class VerProfesor {
    private static VerProfesor instance;
    private GridPane gridPane;
    private Text textTitulo;
    private Label labelNombre;
    private TextField textFieldNombre;
    private Label labelApellido;
    private TextField textFieldApellido;
    private Label labelDpi;
    private TextField textFieldDpi;
    private Label labelTelefono;
    private TextField textFieldTelefono;
    private Label labelDireccion;
    private TextField textFieldDireccion;
    private Label labelFecha;
     private DatePicker datePicker;
    private Button buttonCerrar;
    private String stringTelefono;
    private Date date;
    private int a = 0;

    private VerProfesor() {
    }

    public static VerProfesor getInstance() {
        if (instance == null) {
            instance = new VerProfesor();
        }
        return instance;
    }

    public GridPane getGridPane(Profesor profesor) {
        gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setGridLinesVisible(false);
        gridPane.setPadding(new Insets(25, 25, 25, 25));
        
        textTitulo = new Text("Vista del Profesor");
        textTitulo.setId("titulo");
        textTitulo.setFill(Color.WHITESMOKE);
        gridPane.add(textTitulo, 0, 0);
        
        labelNombre = new Label("Nombres :");
        labelNombre.setId("labels");
        gridPane.add(labelNombre, 0, 1);
        
        textFieldNombre = new TextField();
        textFieldNombre.setPromptText("Nombres del Profesor");
        textFieldNombre.setText(profesor.getNombres());
        textFieldNombre.setEditable(false);
        gridPane.add(textFieldNombre, 1, 1, 2, 1);
        
        labelApellido = new Label("Apellidos :");
        labelApellido.setId("labels");
        gridPane.add(labelApellido, 0, 2);
                
        textFieldApellido = new TextField();
        textFieldApellido.setPromptText("Apellidos del Profesor");
        textFieldApellido.setText(profesor.getApellidos());
        textFieldApellido.setEditable(false);
        gridPane.add(textFieldApellido, 1, 2, 2, 1);
        
        labelFecha = new Label("Fecha de Nacimiento :");
        labelFecha.setId("labels");
        gridPane.add(labelFecha, 0, 3);
        
        datePicker = new DatePicker();
        datePicker.setPromptText("Fecha de Nacimiento");
        date = new Date(); 
        date = profesor.getFechaNacimiento();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        LocalDate localDateNuevo = LocalDate.of(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH) + 1,
                calendar.get(Calendar.DAY_OF_MONTH));
        datePicker.setValue(localDateNuevo);
        datePicker.setEditable(false);
        gridPane.add(datePicker, 1, 3, 2, 1);
        
        labelDpi = new Label("DPI :");
        labelDpi.setId("labels");
        gridPane.add(labelDpi, 0, 4);
        
        textFieldDpi = new TextField();
        textFieldDpi.setPromptText("DPI del Profesor");
        textFieldDpi.setText(profesor.getDpi());
        textFieldDpi.setEditable(false);
        gridPane.add(textFieldDpi, 1, 4, 2, 1);
        
        labelTelefono = new Label("No. Teléfono :");
        labelTelefono.setId("labels");
        gridPane.add(labelTelefono, 0, 5);
        
        textFieldTelefono = new TextField();
        textFieldTelefono.setPromptText("Teléfono del Profesor");
        stringTelefono = String.valueOf(profesor.getTelefono());
        textFieldTelefono.setText(stringTelefono);
        textFieldTelefono.setEditable(false);
        gridPane.add(textFieldTelefono, 1, 5, 2, 1);
        
        labelDireccion = new Label("Dirección :");
        labelDireccion.setId("labels");
        gridPane.add(labelDireccion, 0, 6);
        
        textFieldDireccion = new TextField();
        textFieldDireccion.setPromptText("Dirección del Profesor");
        textFieldDireccion.setText(profesor.getDireccion());
        textFieldDireccion.setEditable(false);
        gridPane.add(textFieldDireccion, 1, 6, 2, 1);
        
        buttonCerrar = new Button("Cerrar");
        buttonCerrar.setId("buttonCerrar");
        buttonCerrar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                CRUDProfesor.getInstance().reiniciarhBoxCRUD();
            }
        });
        gridPane.add(buttonCerrar, 1, 7);
        return gridPane;
    }
}