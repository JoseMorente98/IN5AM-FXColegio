/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.josemorente.gui;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
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
import org.josemorente.beans.Alumno;
import org.josemorente.beans.Carrera;
import org.josemorente.beans.Grado;
import org.josemorente.controlador.ControladorAlumno;
import org.josemorente.controlador.ControladorCarrera;
import org.josemorente.controlador.ControladorGrado;
import org.josemorente.controlador.ControladorMateria;

/**
 *
 * @author José Morente
 */
public class CRUDAlumno {
    public static CRUDAlumno instance;
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
    private TableView<Alumno> tableViewAlumno;
    private TableColumn<Alumno, Integer> tableColumnIdAlumno;
    private TableColumn<Alumno, String> tableColumnNombre;
    private TableColumn<Alumno, String> tableColumnApellido;
    private TableColumn<Alumno, Date> tableColumnFecha;
    private TableColumn<Alumno, Grado> tableColumnGrado;
    private TableColumn<Alumno, Carrera> tableColumnCarrera;
    private TableColumn<Alumno, String> tableColumnJornada;
    private TableColumn<Alumno, Integer> tableColumnTelefono;
    private TableColumn<Alumno, String> tableColumnDireccion;
    private ObservableList<Alumno> observableList;

    private CRUDAlumno() {
    }

    public static CRUDAlumno getInstance() {
        if (instance == null) {
            instance = new CRUDAlumno();
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
        
        textTitulo = new Text("Alumnos");
        textTitulo.setFill(Color.WHITESMOKE);
        textTitulo.setFont(Font.font(Font.getDefault().getFamily(), 25));
        gridPane.add(textTitulo, 0, 0);
        
        hBoxBuscar = new HBox(10);
        
        textFieldBuscar = new TextField();
        textFieldBuscar.setPromptText("Buscar Alumno");
        
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
                hBoxCRUD.getChildren().addAll(gridPane, AgregarAlumno.getInstance().getGridPane());
            }
        });
        
        buttonModificar = new Button("Modificar");
        buttonModificar.setId("buttonModificar");
        buttonModificar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                hBoxCRUD.getChildren().clear();
                if (tableViewAlumno.getSelectionModel().getSelectedItem() != null) {
                    hBoxCRUD.getChildren().addAll(gridPane, ModificarAlumno.getInstance().getGridPane((Alumno) tableViewAlumno.getSelectionModel().getSelectedItem()));
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
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
                if (tableViewAlumno.getSelectionModel().getSelectedItem() != null) {
                    hBoxCRUD.getChildren().addAll(gridPane, VerAlumno.getInstance().getGridPane((Alumno) tableViewAlumno.getSelectionModel().getSelectedItem()));
                } else {
                    hBoxCRUD.getChildren().add(gridPane);
                }
            }
        });
        
        hBoxButtons.getChildren().addAll(buttonNuevo, buttonModificar, buttonEliminar, buttonActualizar, 
                buttonVer);
        gridPane.add(hBoxButtons, 0, 2);
        
        tableColumnIdAlumno = new TableColumn<>();
        tableColumnIdAlumno.setText("ID Alumno");
        tableColumnIdAlumno.setCellValueFactory(new PropertyValueFactory<>("idAlumno"));
        tableColumnIdAlumno.setMinWidth(20);
        
        tableColumnNombre = new TableColumn<>();
        tableColumnNombre.setText("Nombres");
        tableColumnNombre.setCellValueFactory(new PropertyValueFactory<>("nombres"));
        tableColumnNombre.setMinWidth(100);
        
        tableColumnApellido = new TableColumn<>();
        tableColumnApellido.setText("Apellidos");
        tableColumnApellido.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        tableColumnApellido.setMinWidth(100);
        
        tableColumnFecha = new TableColumn<>();
        tableColumnFecha.setText("Fecha de Nacimiento");
        tableColumnFecha.setCellValueFactory(new PropertyValueFactory<>("fechaNacimiento"));
        tableColumnFecha.setMinWidth(100);
        
        tableColumnGrado = new TableColumn<>();
        tableColumnGrado.setText("Grado");
        tableColumnGrado.setCellValueFactory(new PropertyValueFactory<>("Grado"));
        tableColumnGrado.setMinWidth(75);
        
        tableColumnCarrera = new TableColumn<>();
        tableColumnCarrera.setText("Carrera");
        tableColumnCarrera.setCellValueFactory(new PropertyValueFactory<>("Carrera"));
        tableColumnCarrera.setMinWidth(75);
        
        tableColumnJornada = new TableColumn<>();
        tableColumnJornada.setText("Jornada");
        tableColumnJornada.setCellValueFactory(new PropertyValueFactory<>("jornada"));
        tableColumnJornada.setMinWidth(75);
        
        tableColumnTelefono = new TableColumn<>();
        tableColumnTelefono.setText("No. Teléfono");
        tableColumnTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        tableColumnTelefono.setMinWidth(75);
        
        tableColumnDireccion = new TableColumn<>();
        tableColumnDireccion.setText("Dirección");
        tableColumnDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        tableColumnDireccion.setMinWidth(125);
        
        actualizarObservableList();
        tableViewAlumno = new TableView<>(observableList);
        tableViewAlumno.getColumns().addAll(tableColumnIdAlumno, tableColumnNombre, 
                tableColumnApellido, tableColumnFecha, tableColumnGrado, tableColumnCarrera, 
                tableColumnJornada, tableColumnTelefono, tableColumnDireccion);
        gridPane.add(tableViewAlumno, 0, 3, 2, 1);
        
        hBoxCRUD.getChildren().add(gridPane);
        return hBoxCRUD;
    }
    
    public void actualizarTableViewItems() {
        actualizarObservableList();
        tableViewAlumno.setItems(observableList);
    }
       
    public void actualizarObservableList() {
        observableList = FXCollections.observableArrayList(ControladorAlumno.getInstance().getArrayList());
    }
}

class AgregarAlumno {
    public static AgregarAlumno instance;
    private GridPane gridPane;
    private Text textTitulo;
    private Label labelNombre;
    private TextField textFieldNombre;
    private Label labelApellido;
    private TextField textFieldApellido;
    private Label labelFecha;
    private DatePicker datePicker;
    private Label labelGrado;
    private ComboBox<Grado> comboBoxGrado;
    private Label labelCarrera;
    private ComboBox<Carrera> comboBoxCarrera;
    private Label labelJornada;
    private ChoiceBox choiceBoxJornada;
    private Label labelTelefono;
    private TextField textFieldTelefono;
    private Label labelDireccion;
    private TextField textFieldDireccion;
    private Button buttonAgregar;
    private Button buttonCerrar;
    private Date date;
    private int a = 0;
    
    private ObservableList<Grado> observableListGrado;
    private ObservableList<Carrera> observableListCarrera;

    private AgregarAlumno() {
        actualizarObservableListCarrera();
        actualizarObservableListGrado();
    }

    public static AgregarAlumno getInstance() {
        if (instance == null) {
            instance = new AgregarAlumno();
        }
        return instance;
    }

    public GridPane getGridPane() {
        gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setGridLinesVisible(false);
        gridPane.setPadding(new Insets(25, 25, 25, 25));
        
        textTitulo = new Text("Agregar Alumno");
        textTitulo.setId("titulo");
        textTitulo.setFill(Color.WHITESMOKE);
        gridPane.add(textTitulo, 0, 0);
        
        labelNombre = new Label("Nombres :");
        labelNombre.setId("labels");
        gridPane.add(labelNombre, 0, 1);
        
        textFieldNombre = new TextField();
        textFieldNombre.setPromptText("Nombres del Alumno");
        gridPane.add(textFieldNombre, 1, 1, 2, 1);
        
        labelApellido = new Label("Apellidos :");
        labelApellido.setId("labels");
        gridPane.add(labelApellido, 0, 2);
                
        textFieldApellido = new TextField();
        textFieldApellido.setPromptText("Apellidos del Alumno");
        gridPane.add(textFieldApellido, 1, 2, 2, 1);
        
        labelFecha = new Label("Fecha de Nacimiento :");
        labelFecha.setId("labels");
        gridPane.add(labelFecha, 0, 3);
        
        datePicker = new DatePicker();
        datePicker.setPromptText("Fecha de Nacimiento");
        gridPane.add(datePicker, 1, 3, 2, 1);
        
        labelGrado = new Label("Grado :");
        labelGrado.setId("labels");
        gridPane.add(labelGrado, 0, 4);
        
        comboBoxGrado = new ComboBox<>(observableListGrado);
        gridPane.add(comboBoxGrado, 1, 4, 2, 1);
        
        labelCarrera = new Label("Carrera :");
        labelCarrera.setId("labels");
        gridPane.add(labelCarrera, 0, 5);
        
        comboBoxCarrera = new ComboBox<>(observableListCarrera);
        gridPane.add(comboBoxCarrera, 1, 5, 2, 1);
        
        labelJornada = new Label("Jornada :");
        labelJornada.setId("labels");
        gridPane.add(labelJornada, 0, 6);
        
        choiceBoxJornada = new ChoiceBox();
        choiceBoxJornada.getItems().addAll("Matutina", "Vespertina");
        gridPane.add(choiceBoxJornada, 1, 6, 2, 1);
        
        labelTelefono = new Label("No. Teléfono :");
        labelTelefono.setId("labels");
        gridPane.add(labelTelefono, 0, 7);
        
        textFieldTelefono = new TextField();
        textFieldTelefono.setPromptText("Teléfono de los Padres");
        gridPane.add(textFieldTelefono, 1, 7, 2, 1);
        
        labelDireccion = new Label("Dirección :");
        labelDireccion.setId("labels");
        gridPane.add(labelDireccion, 0, 8);
        
        textFieldDireccion = new TextField();
        textFieldDireccion.setPromptText("Dirección del Alumno");
        gridPane.add(textFieldDireccion, 1, 8, 2, 1);
        
        buttonAgregar = new Button("Agregar");
        buttonAgregar.setId("buttonAgregar");
        buttonAgregar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                agregarAlumno(textFieldNombre.getText(), 
                        textFieldApellido.getText(), 
                        datePicker.getValue(), 
                        comboBoxGrado.getSelectionModel().getSelectedItem(), 
                        comboBoxCarrera.getSelectionModel().getSelectedItem(), 
                        choiceBoxJornada.getSelectionModel().getSelectedItem().toString(), 
                        a = Integer.parseInt(textFieldTelefono.getText()), 
                        textFieldDireccion.getText());
                CRUDAlumno.getInstance().reiniciarhBoxCRUD();
                CRUDAlumno.getInstance().actualizarTableViewItems();
            }
        });
        
        buttonCerrar = new Button("Cerrar");
        buttonCerrar.setId("buttonCerrar");
        buttonCerrar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                CRUDAlumno.getInstance().reiniciarhBoxCRUD();
            }
        });
        
        gridPane.add(buttonAgregar, 1, 9);
        gridPane.add(buttonCerrar, 2, 9, 2, 1);
        return gridPane;
    }
    
    private void agregarAlumno(String nombres, String apellidos, LocalDate fechaNacimiento, Grado grado, Carrera carrera, 
            String jornada, int telefono, String direccion) {
        ControladorAlumno.getInstance().agregarAlumno(nombres, 
                apellidos, 
                fechaNacimiento, 
                grado.getIdGrado(), 
                carrera.getIdCarrera(), 
                jornada, 
                telefono, 
                direccion);
    }
    
    public void actualizarObservableListGrado() {
        observableListGrado = FXCollections.observableArrayList(ControladorGrado.getInstance().getArrayList());
    }
    
    public void actualizarObservableListCarrera() {
        observableListCarrera = FXCollections.observableArrayList(ControladorCarrera.getInstance().getArrayList());
    }
    
    public void actualizarComboBoxGrado() {
        actualizarObservableListGrado();
        comboBoxGrado.setItems(observableListGrado);
    }
    
    public void actualizarComboBoxCarrera() {
        actualizarObservableListCarrera();
        comboBoxCarrera.setItems(observableListCarrera);
    }
}

class ModificarAlumno {
    public static ModificarAlumno instance;
    private GridPane gridPane;
    private Text textTitulo;
    private Label labelNombre;
    private TextField textFieldNombre;
    private Label labelApellido;
    private TextField textFieldApellido;
    private Label labelFecha;
    private DatePicker datePicker;
    private Label labelGrado;
    private ComboBox<Grado> comboBoxGrado;
    private Label labelCarrera;
    private ComboBox<Carrera> comboBoxCarrera;
    private Label labelJornada;
    private ChoiceBox choiceBoxJornada;
    private Label labelTelefono;
    private TextField textFieldTelefono;
    private Label labelDireccion;
    private TextField textFieldDireccion;
    private Button buttonModificar;
    private Button buttonCerrar;
    private String stringTelefono;
    private Date date;
    private int a = 0;
    
    private ObservableList<Grado> observableListGrado;
    private ObservableList<Carrera> observableListCarrera;

    private ModificarAlumno() {
        actualizarObservableListCarrera();
        actualizarObservableListGrado();
    }

    public static ModificarAlumno getInstance() {
        if (instance == null) {
            instance = new ModificarAlumno();
        }
        return instance;
    }

    public GridPane getGridPane(Alumno alumno) {
        gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setGridLinesVisible(false);
        gridPane.setPadding(new Insets(25, 25, 25, 25));
        
        textTitulo = new Text("Modificar Alumno");
        textTitulo.setId("titulo");
        textTitulo.setFill(Color.WHITESMOKE);
        gridPane.add(textTitulo, 0, 0);
        
        labelNombre = new Label("Nombres :");
        labelNombre.setId("labels");
        gridPane.add(labelNombre, 0, 1);
        
        textFieldNombre = new TextField();
        textFieldNombre.setText(alumno.getNombres());
        textFieldNombre.setPromptText("Nombres del Alumno");
        gridPane.add(textFieldNombre, 1, 1, 2, 1);
        
        labelApellido = new Label("Apellidos :");
        labelApellido.setId("labels");
        gridPane.add(labelApellido, 0, 2);
                
        textFieldApellido = new TextField();
        textFieldApellido.setText(alumno.getApellidos());
        textFieldApellido.setPromptText("Apellidos del Alumno");
        gridPane.add(textFieldApellido, 1, 2, 2, 1);
        
        labelFecha = new Label("Fecha de Nacimiento :");
        labelFecha.setId("labels");
        gridPane.add(labelFecha, 0, 3);
        
        datePicker = new DatePicker();
        datePicker.setPromptText("Fecha de Nacimiento");
        date = new Date(); 
        date = alumno.getFechaNacimiento();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        LocalDate localDateNuevo = LocalDate.of(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH) + 1,
                calendar.get(Calendar.DAY_OF_MONTH));
        datePicker.setValue(localDateNuevo);
        gridPane.add(datePicker, 1, 3, 2, 1);
        
        labelGrado = new Label("Grado :");
        labelGrado.setId("labels");
        gridPane.add(labelGrado, 0, 4);
        
        comboBoxGrado = new ComboBox<>(observableListGrado);
        comboBoxGrado.setValue(alumno.getGrado());
        gridPane.add(comboBoxGrado, 1, 4, 2, 1);
        
        labelCarrera = new Label("Carrera :");
        labelCarrera.setId("labels");
        gridPane.add(labelCarrera, 0, 5);
        
        comboBoxCarrera = new ComboBox<>(observableListCarrera);
        comboBoxCarrera.setValue(alumno.getCarrera());
        gridPane.add(comboBoxCarrera, 1, 5, 2, 1);
        
        labelJornada = new Label("Jornada :");
        labelJornada.setId("labels");
        gridPane.add(labelJornada, 0, 6);
        
        choiceBoxJornada = new ChoiceBox();
        choiceBoxJornada.setValue(alumno.getJornada());
        choiceBoxJornada.getItems().addAll("Matutina", "Vespertina");
        gridPane.add(choiceBoxJornada, 1, 6, 2, 1);
        
        labelTelefono = new Label("No. Teléfono :");
        labelTelefono.setId("labels");
        gridPane.add(labelTelefono, 0, 7);
        
        textFieldTelefono = new TextField();
        stringTelefono = String.valueOf(alumno.getTelefono());
        textFieldTelefono.setText(stringTelefono);
        textFieldTelefono.setPromptText("Teléfono de los Padres");
        gridPane.add(textFieldTelefono, 1, 7, 2, 1);
        
        labelDireccion = new Label("Dirección :");
        labelDireccion.setId("labels");
        gridPane.add(labelDireccion, 0, 8);
        
        textFieldDireccion = new TextField();
        textFieldDireccion.setText(alumno.getDireccion());
        textFieldDireccion.setPromptText("Dirección del Alumno");
        gridPane.add(textFieldDireccion, 1, 8, 2, 1);
        
        buttonModificar = new Button("Modificar");
        buttonModificar.setId("buttonModificar");
        buttonModificar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                modificarAlumno(textFieldNombre.getText(), 
                        textFieldApellido.getText(), 
                        datePicker.getValue(), 
                        comboBoxGrado.getSelectionModel().getSelectedItem(), 
                        comboBoxCarrera.getSelectionModel().getSelectedItem(), 
                        choiceBoxJornada.getSelectionModel().getSelectedItem().toString(), 
                        a = Integer.parseInt(textFieldTelefono.getText()), 
                        textFieldDireccion.getText(),
                        alumno.getIdAlumno());
                CRUDAlumno.getInstance().reiniciarhBoxCRUD();
                CRUDAlumno.getInstance().actualizarTableViewItems();
            }
        });
        
        buttonCerrar = new Button("Cerrar");
        buttonCerrar.setId("buttonCerrar");
        buttonCerrar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                CRUDAlumno.getInstance().reiniciarhBoxCRUD();
            }
        });
        
        gridPane.add(buttonModificar, 1, 9);
        gridPane.add(buttonCerrar, 2, 9, 2, 1);
        return gridPane;
    }
    
    private void modificarAlumno(String nombres, String apellidos, LocalDate fechaNacimiento, Grado grado, Carrera carrera, 
            String jornada, int telefono, String direccion, int idAlumno) {
        ControladorAlumno.getInstance().modificarAlumno(nombres, 
                apellidos, 
                fechaNacimiento, 
                grado.getIdGrado(), 
                carrera.getIdCarrera(), 
                jornada, 
                telefono, 
                direccion,
                idAlumno);
    }
    
    public void actualizarObservableListGrado() {
        observableListGrado = FXCollections.observableArrayList(ControladorGrado.getInstance().getArrayList());
    }
    
    public void actualizarObservableListCarrera() {
        observableListCarrera = FXCollections.observableArrayList(ControladorCarrera.getInstance().getArrayList());
    }
}

class VerAlumno {
    public static VerAlumno instance;
    private GridPane gridPane;
    private Text textTitulo;
    private Label labelNombre;
    private TextField textFieldNombre;
    private Label labelApellido;
    private TextField textFieldApellido;
    private Label labelFecha;
    private DatePicker datePicker;
    private Label labelGrado;
    private ComboBox<Grado> comboBoxGrado;
    private Label labelCarrera;
    private ComboBox<Carrera> comboBoxCarrera;
    private Label labelJornada;
    private TextField textFieldJornada;
    private Label labelTelefono;
    private TextField textFieldTelefono;
    private Label labelDireccion;
    private TextField textFieldDireccion;
    private Button buttonCerrar;
    private String stringTelefono;
    private Date date;
    private int a = 0;

    private VerAlumno() {
    }

    public static VerAlumno getInstance() {
        if (instance == null) {
            instance = new VerAlumno();
        }
        return instance;
    }

    public GridPane getGridPane(Alumno alumno) {
        gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setGridLinesVisible(false);
        gridPane.setPadding(new Insets(25, 25, 25, 25));
        
        textTitulo = new Text("Vista de Alumno");
        textTitulo.setId("titulo");
        textTitulo.setFill(Color.WHITESMOKE);
        gridPane.add(textTitulo, 0, 0);
        
        labelNombre = new Label("Nombres :");
        labelNombre.setId("labels");
        gridPane.add(labelNombre, 0, 1);
        
        textFieldNombre = new TextField();
        textFieldNombre.setText(alumno.getNombres());
        textFieldNombre.setEditable(false);
        gridPane.add(textFieldNombre, 1, 1, 2, 1);
        
        labelApellido = new Label("Apellidos :");
        labelApellido.setId("labels");
        gridPane.add(labelApellido, 0, 2);
                
        textFieldApellido = new TextField();
        textFieldApellido.setText(alumno.getApellidos());
        textFieldApellido.setEditable(false);
        gridPane.add(textFieldApellido, 1, 2, 2, 1);
        
        labelFecha = new Label("Fecha de Nacimiento :");
        labelFecha.setId("labels");
        gridPane.add(labelFecha, 0, 3);
        
        datePicker = new DatePicker();
        datePicker.setEditable(false);
        date = new Date(); 
        date = alumno.getFechaNacimiento();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        LocalDate localDateNuevo = LocalDate.of(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH) + 1,
                calendar.get(Calendar.DAY_OF_MONTH));
        datePicker.setValue(localDateNuevo);
        gridPane.add(datePicker, 1, 3, 2, 1);
        
        labelGrado = new Label("Grado :");
        labelGrado.setId("labels");
        gridPane.add(labelGrado, 0, 4);
        
        comboBoxGrado = new ComboBox<>();
        comboBoxGrado.setValue(alumno.getGrado());
        comboBoxGrado.setEditable(false);
        gridPane.add(comboBoxGrado, 1, 4, 2, 1);
        
        labelCarrera = new Label("Carrera :");
        labelCarrera.setId("labels");
        gridPane.add(labelCarrera, 0, 5);
        
        comboBoxCarrera = new ComboBox<>();
        comboBoxCarrera.setValue(alumno.getCarrera());
        comboBoxCarrera.setEditable(false);
        gridPane.add(comboBoxCarrera, 1, 5, 2, 1);
        
        labelJornada = new Label("Jornada :");
        labelJornada.setId("labels");
        gridPane.add(labelJornada, 0, 6);
        
        textFieldJornada = new TextField();
        textFieldJornada.setText(alumno.getJornada());
        textFieldJornada.setEditable(false);
        gridPane.add(textFieldJornada, 1, 6, 2, 1);
        
        labelTelefono = new Label("No. Teléfono :");
        labelTelefono.setId("labels");
        gridPane.add(labelTelefono, 0, 7);
        
        textFieldTelefono = new TextField();
        stringTelefono = String.valueOf(alumno.getTelefono());
        textFieldTelefono.setText(stringTelefono);
        textFieldTelefono.setEditable(false);
        gridPane.add(textFieldTelefono, 1, 7, 2, 1);
        
        labelDireccion = new Label("Dirección :");
        labelDireccion.setId("labels");
        gridPane.add(labelDireccion, 0, 8);
        
        textFieldDireccion = new TextField();
        textFieldDireccion.setText(alumno.getDireccion());
        textFieldDireccion.setEditable(false);
        gridPane.add(textFieldDireccion, 1, 8, 2, 1);
        
        buttonCerrar = new Button("Cerrar");
        buttonCerrar.setId("buttonCerrar");
        buttonCerrar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                CRUDAlumno.getInstance().reiniciarhBoxCRUD();
            }
        });
        
        gridPane.add(buttonCerrar, 1, 9);
        return gridPane;
    }
}