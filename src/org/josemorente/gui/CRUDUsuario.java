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
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import org.josemorente.beans.Usuario;
import org.josemorente.controlador.ControladorUsuario;

/**
 *
 * @author José Morente
 */
public class CRUDUsuario implements CRUDInterfaz {
    public static CRUDUsuario instance;
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
    private TableView<Usuario> tableView;
    private TableColumn<Usuario, Integer> tableColumnIdUsuario;
    private TableColumn<Usuario, Boolean> tableColumnActivo;
    private TableColumn<Usuario, String> tableColumnUsuario;
    private TableColumn<Usuario, String> tableColumnClave;
    private ObservableList<Usuario> observableList;
    
    private CRUDUsuario() {
    }

    public static CRUDUsuario getInstance() {
        if (instance == null) {
            instance = new CRUDUsuario();
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
        
        textTitulo = new Text("Usuarios");
        textTitulo.setFill(Color.WHITESMOKE);
        textTitulo.setFont(Font.font(Font.getDefault().getFamily(), 25));
        gridPane.add(textTitulo, 0, 0);
        
        hBoxBuscar = new HBox(10);
        
        textFieldBuscar = new TextField();
        textFieldBuscar.setPromptText("Buscar Usuario");
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
                hBoxCRUD.getChildren().addAll(gridPane, CrearUsuario.getInstance().getGridPane());
            }
        });
        
        buttonModificar = new Button("Modificar");
        buttonModificar.setId("buttonModificar");
        buttonModificar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                hBoxCRUD.getChildren().clear();
                if (tableView.getSelectionModel().getSelectedItem() != null) {
                    hBoxCRUD.getChildren().addAll(gridPane, ModificarUsuario.getInstance().getGridPane((Usuario) tableView.getSelectionModel().getSelectedItem()));
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
                if (tableView.getSelectionModel().getSelectedItem() != null) {
                    ControladorUsuario.getInstance().eliminarUsuario(tableView.getSelectionModel().getSelectedItem().getIdUsuario());
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
                if (tableView.getSelectionModel().getSelectedItem() != null) {
                    hBoxCRUD.getChildren().addAll(gridPane, VerUsuario.getInstance().getGridPane((Usuario) tableView.getSelectionModel().getSelectedItem()));
                } else {
                    hBoxCRUD.getChildren().add(gridPane);
                }
            }
        });
        
        hBoxButtons.getChildren().addAll(buttonNuevo, buttonModificar, buttonEliminar, buttonActualizar, 
                buttonVer);
        gridPane.add(hBoxButtons, 0, 2);
        
        tableColumnIdUsuario = new TableColumn<>();
        tableColumnIdUsuario.setText("ID Usuario");
        tableColumnIdUsuario.setCellValueFactory(new PropertyValueFactory<>("idUsuario") );
        tableColumnIdUsuario.setMinWidth(100);
        
        tableColumnActivo = new TableColumn<>();
        tableColumnActivo.setText("Activo");
        tableColumnActivo.setCellValueFactory(new PropertyValueFactory<>("activo"));
        tableColumnActivo.setMinWidth(70);
        
        tableColumnUsuario = new TableColumn<>();
        tableColumnUsuario.setText("Usuarios");
        tableColumnUsuario.setCellValueFactory(new PropertyValueFactory<>("usuario"));
        tableColumnUsuario.setMinWidth(190);
        
        tableColumnClave = new TableColumn<>();
        tableColumnClave.setText("Password");
        tableColumnClave.setCellValueFactory(new PropertyValueFactory<>("password"));
        tableColumnClave.setMinWidth(190);
        
        actualizarObservableList();
        tableView = new TableView<>(observableList);
        tableView.getColumns().addAll(tableColumnIdUsuario, tableColumnActivo, 
                tableColumnUsuario, tableColumnClave);
        
        gridPane.add(tableView, 0, 3, 2, 1);

        hBoxCRUD.getChildren().add(gridPane);
        
        return hBoxCRUD;
    }
    
    public void actualizarTableViewItems() {
        actualizarObservableList();
        tableView.setItems(observableList);
    }
       
    public void actualizarObservableList() {
        observableList = FXCollections.observableArrayList(ControladorUsuario.getInstance().getArrayList());
    }
    
    public void actualizarTableBusqueda(String nombre) {
        observableList = FXCollections.observableArrayList(ControladorUsuario.getInstance().buscar(nombre));
        tableView.setItems(observableList);
    }
}

class CrearUsuario {
    private static CrearUsuario instance;
    private GridPane gridPane;
    private Text textTitulo;
    private Label labelActivo;
    private CheckBox checkBoxActivo;
    private Label labelNombre;
    private TextField textFieldNombre;
    private Label labelPassword;
    private PasswordField passwordFieldClave;
    private Button buttonAgregar;
    private Button buttonCerrar;
    
    
    private CrearUsuario() {
    }

    public static CrearUsuario getInstance() {
        if (instance == null) {
            instance = new CrearUsuario();
        }
        return instance;
    }
    
    public GridPane getGridPane() {
        gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setGridLinesVisible(false);
        gridPane.setPadding(new Insets(25, 25, 25, 25));
        
        textTitulo = new Text("Agregar Usuario");
        textTitulo.setId("titulo");
        textTitulo.setFill(Color.WHITESMOKE);
        gridPane.add(textTitulo, 0, 0);
        
        labelActivo = new Label("Activo");
        labelActivo.setId("labels");
        gridPane.add(labelActivo, 0, 1);
        
        checkBoxActivo = new CheckBox();
        gridPane.add(checkBoxActivo, 1, 1, 2, 1);
        
        labelNombre = new Label("Usuario :");
        labelNombre.setId("labels");
        gridPane.add(labelNombre, 0, 2);
        
        textFieldNombre = new TextField();
        textFieldNombre.setPromptText("Nombre de usuario");
        gridPane.add(textFieldNombre , 1, 2, 2, 1);
        
        labelPassword = new Label("Contraseña :");
        labelPassword.setId("labels");
        gridPane.add(labelPassword, 0, 3);
        
        passwordFieldClave = new PasswordField();
        passwordFieldClave.setPromptText("Contraseña de usuario");
        gridPane.add(passwordFieldClave, 1, 3, 2, 1);
        
        buttonAgregar = new Button("Agregar");
        buttonAgregar.setId("buttonAgregar");
        buttonAgregar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ControladorUsuario.getInstance().agregarUsuario(checkBoxActivo.selectedProperty().getValue(), 
                        textFieldNombre.getText(), 
                        passwordFieldClave.getText());
                CRUDUsuario.getInstance().reiniciarhBoxCRUD();
                CRUDUsuario.getInstance().actualizarTableViewItems();
            }
        });
        
        buttonCerrar = new Button("Cerrar");
        buttonCerrar.setId("buttonCerrar");
        buttonCerrar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                CRUDUsuario.getInstance().reiniciarhBoxCRUD();
            }
        });
        
        gridPane.add(buttonAgregar, 1, 4);
        gridPane.add(buttonCerrar, 2, 4, 2, 1);
        return gridPane;
    }
 
}

class ModificarUsuario{
    public static ModificarUsuario instance;
    private GridPane gridPane;
    private Text textTitulo;
    private Label labelActivo;
    private CheckBox checkBoxActivo;
    private Label labelNombre;
    private TextField textFieldNombre;
    private Label labelPassword;
    private PasswordField passwordFieldClave;
    private Button buttonModificar;
    private Button buttonCerrar;

    private ModificarUsuario() {
    }

    public static ModificarUsuario getInstance() {
        if (instance == null) {
            instance = new ModificarUsuario();
        }
        return instance;
    }
    
    public GridPane getGridPane(Usuario usuario){
        gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setGridLinesVisible(false);
        gridPane.setPadding(new Insets(25, 25, 25, 25));
        
        textTitulo = new Text("Modificar Usuario");
        textTitulo.setId("titulo");
        textTitulo.setFill(Color.WHITESMOKE);
        gridPane.add(textTitulo, 0, 0);
        
        labelActivo = new Label("Activo");
        labelActivo.setId("labels");
        gridPane.add(labelActivo, 0, 1);
        
        checkBoxActivo = new CheckBox();
        checkBoxActivo.selectedProperty().set(usuario.isActivo());
        gridPane.add(checkBoxActivo, 1, 1, 2, 1);
        
        labelNombre = new Label("Usuario :");
        labelNombre.setId("labels");
        gridPane.add(labelNombre, 0, 2);
        
        textFieldNombre = new TextField();
        textFieldNombre.setPromptText("Nombre de usuario");
        textFieldNombre.setText(usuario.getUsuario());
        gridPane.add(textFieldNombre , 1, 2, 2, 1);
        
        labelPassword = new Label("Contraseña :");
        labelPassword.setId("labels");
        gridPane.add(labelPassword, 0, 3);
        
        passwordFieldClave = new PasswordField();
        passwordFieldClave.setPromptText("Contraseña de usuario");
        passwordFieldClave.setText(usuario.getPassword());
        gridPane.add(passwordFieldClave, 1, 3, 2, 1);
        
        buttonModificar = new Button("Modificar");
        buttonModificar.setId("buttonModificar");
        buttonModificar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ControladorUsuario.getInstance().modificarUsuario(checkBoxActivo.selectedProperty().getValue(), 
                        textFieldNombre.getText(), 
                        passwordFieldClave.getText(), 
                        usuario.getIdUsuario());
                CRUDUsuario.getInstance().reiniciarhBoxCRUD();
                CRUDUsuario.getInstance().actualizarTableViewItems();
            }
        });
        
        buttonCerrar = new Button("Cerrar");
        buttonCerrar.setId("buttonCerrar");
        buttonCerrar.setOnAction(new  EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                CRUDUsuario.getInstance().reiniciarhBoxCRUD();
            }
        });
        
        gridPane.add(buttonModificar, 1, 4);
        gridPane.add(buttonCerrar, 2, 4, 2, 1);
        return gridPane;
    }
}

class VerUsuario {
    private static VerUsuario instance;
    private GridPane gridPane;
    private Text textTitulo;
    private Label labelActivo;
    private CheckBox checkBoxActivo;
    private Label labelNombre;
    private TextField textFieldNombre;
    private Label labelPassword;
    private PasswordField passwordFieldClave;
    private Button buttonCerrar;

    public VerUsuario() {
    }

    public static VerUsuario getInstance() {
        if (instance == null) {
            instance = new VerUsuario();
        }
        return instance;
    }

    public GridPane getGridPane(Usuario usuario) {
        gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setGridLinesVisible(false);
        gridPane.setPadding(new Insets(25, 25, 25, 25));
        
        textTitulo = new Text("Vista del Usuario");
        textTitulo.setId("titulo");
        textTitulo.setFill(Color.WHITESMOKE);
        gridPane.add(textTitulo, 0, 0);
        
        labelActivo = new Label("Activo");
        labelActivo.setId("labels");
        gridPane.add(labelActivo, 0, 1);
        
        checkBoxActivo = new CheckBox();
        checkBoxActivo.selectedProperty().set(usuario.isActivo());
        gridPane.add(checkBoxActivo, 1, 1, 2, 1);
        
        labelNombre = new Label("Usuario :");
        labelNombre.setId("labels");
        gridPane.add(labelNombre, 0, 2);
        
        textFieldNombre = new TextField();
        textFieldNombre.setPromptText("Nombre de usuario");
        textFieldNombre.setText(usuario.getUsuario());
        textFieldNombre.setEditable(false);
        gridPane.add(textFieldNombre , 1, 2, 2, 1);
        
        labelPassword = new Label("Contraseña :");
        labelPassword.setId("labels");
        gridPane.add(labelPassword, 0, 3);
        
        passwordFieldClave = new PasswordField();
        passwordFieldClave.setPromptText("Contraseña de usuario");
        passwordFieldClave.setText(usuario.getPassword());
        passwordFieldClave.setEditable(false);
        gridPane.add(passwordFieldClave, 1, 3, 2, 1);
        
        buttonCerrar = new Button("Cerrar");
        buttonCerrar.setId("buttonCerrar");
        buttonCerrar.setOnAction(new  EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                CRUDUsuario.getInstance().reiniciarhBoxCRUD();
            }
        });
        
        gridPane.add(buttonCerrar, 1, 4);
        return gridPane;
    }
    
}