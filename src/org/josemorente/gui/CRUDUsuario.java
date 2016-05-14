/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.josemorente.gui;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.util.Duration;
import org.josemorente.beans.Usuario;
import org.josemorente.controlador.ControladorUsuario;

/**
 *
 * @author José Morente
 */
public class CRUDUsuario {
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
    private TableView tableView;
    private TableColumn<Usuario, Integer> tableColumnIdUsuario;
    private TableColumn<Usuario, Boolean> tableColumnActivo;
    private TableColumn<Usuario, String> tableColumnUsuario;
    private TableColumn<Usuario, String> tableColumnPassword;
    private ObservableList observableList;
    
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
        gridPane.add(textTitulo, 0, 0);
        
        hBoxBuscar = new HBox(10);
        
        textFieldBuscar = new TextField();
        textFieldBuscar.setPromptText("Buscar Usuario");
        
        buttonBuscar = new Button("Buscar");
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
        buttonNuevo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                hBoxCRUD.getChildren().clear();
                hBoxCRUD.getChildren().addAll(gridPane, CrearUsuario.getInstance().getGridPane());
            }
        });
        
        buttonModificar = new Button("Modificar");
        buttonModificar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        buttonEliminar = new Button("Eliminar");
        buttonEliminar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        buttonActualizar = new Button("Actualizar");
        buttonActualizar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                actualizarTableViewItems();
            }
        });
        
        hBoxButtons.getChildren().addAll(buttonNuevo, buttonModificar, buttonEliminar, buttonActualizar);
        gridPane.add(hBoxButtons, 0, 2);
        
        tableColumnIdUsuario = new TableColumn<>();
        tableColumnIdUsuario.setText("ID Usuario");
        tableColumnIdUsuario.setCellValueFactory(new PropertyValueFactory<>("idUsuario") );
        tableColumnIdUsuario.setMinWidth(100);
        
        tableColumnActivo = new TableColumn<>();
        tableColumnActivo.setText("Activo");
        tableColumnActivo.setCellValueFactory(new PropertyValueFactory<>("activo"));
        tableColumnActivo.setMinWidth(50);
        
        tableColumnUsuario = new TableColumn<>();
        tableColumnUsuario.setText("Usuarios");
        tableColumnUsuario.setCellValueFactory(new PropertyValueFactory<>("usuario"));
        tableColumnUsuario.setMinWidth(200);
        
        tableColumnPassword = new TableColumn<>();
        tableColumnPassword.setText("Password");
        tableColumnPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        tableColumnPassword.setMinWidth(200);
        
        tableView = new TableView<>(observableList);
        tableView.getColumns().addAll(tableColumnIdUsuario, tableColumnActivo, 
                tableColumnUsuario, tableColumnPassword);
        
        gridPane.add(tableView, 0, 3, 2, 1);
        
        hBoxCRUD.getChildren().add(gridPane);
        
        return hBoxCRUD;
    }
    
    public void actualizarTableViewItems() {
        actualizarObservableList();
        tableView.setItems(observableList);
    }
       
    public void actualizarObservableList() {
        observableList = FXCollections.observableList(ControladorUsuario.getInstance().getArrayList());
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
    private PasswordField passwordFieldPassword;
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
        gridPane.add(textTitulo, 0, 0);
        
        labelActivo = new Label("Activo");
        gridPane.add(labelActivo, 0, 1);
        
        checkBoxActivo = new CheckBox();
        gridPane.add(checkBoxActivo, 1, 1, 2, 1);
        
        labelNombre = new Label("Usuario :");
        gridPane.add(labelNombre, 0, 2);
        
        textFieldNombre = new TextField();
        gridPane.add(textFieldNombre , 1, 2, 2, 1);
        
        labelPassword = new Label("Contraseña :");
        gridPane.add(labelPassword, 0, 3);
        
        passwordFieldPassword = new PasswordField();
        gridPane.add(passwordFieldPassword, 1, 3, 2, 1);
        
        buttonAgregar = new Button("Agregar");
        buttonAgregar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ControladorUsuario.getInstance().agregarUsuario(checkBoxActivo.selectedProperty().getValue(), 
                        textFieldNombre.getText(), 
                        passwordFieldPassword.getText());
                CRUDUsuario.getInstance().reiniciarhBoxCRUD();
                CRUDUsuario.getInstance().actualizarTableViewItems();
            }
        });
        
        buttonCerrar = new Button("Cerrar");
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