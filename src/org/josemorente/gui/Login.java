/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.josemorente.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author José Morente
 */
public class Login {
    public static Login instance;
    private TextField textFieldUsuario;
    private PasswordField passwordFieldPassword;
    private Button buttonLogin;
    private Label labelError;
    private Stage stage;
    private VBox vBoxPrincipal;
    private GridPane gridPane;
    private Text textTitulo;
    private Text textTituloA;
    private Text textTituloB;

    private Login() {
    }

    public static Login getInstance() {
        if (instance == null) {
            instance = new Login();
        }
        return instance;
    }
    
    public void Login(Stage stagePrimary) {
        stage = stagePrimary;
        String usuario = "admin";
        String password = "password";
        labelError = new Label();
                
        gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));
        
        textTitulo = new Text("LOGIN");
        textTitulo.setFill(Color.DARKBLUE);
        textTitulo.setFont(Font.font(Font.getDefault().getFamily(), 35));
        gridPane.add(textTitulo, 0, 0);
        
        textTituloA = new Text("Nombre de Usuario:");
        textFieldUsuario = new TextField();
        textFieldUsuario.setPromptText("Usuario (admin)");
        gridPane.add(textTituloA ,  0, 1, 1, 1);
        gridPane.add(textFieldUsuario ,  0, 2, 1, 1);
        
        textTituloB = new Text("Contraseña de Usuario:");
        passwordFieldPassword = new PasswordField();
        passwordFieldPassword.setPromptText("Contraseña (admin)");
        gridPane.add(textTituloB, 0, 3, 1, 1);
        gridPane.add(passwordFieldPassword, 0, 4, 1, 1);
        
        buttonLogin = new Button("Ingresar");
        buttonLogin.setStyle("-fx-base : BLACK;");//Rojo Oscuro
        buttonLogin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                reiniciar();
                if(textFieldUsuario.getText().equals("admin") && passwordFieldPassword.getText().equals("admin")){
                    VentanaWindows.getInstance().Principal(stagePrimary);
                } else {
                    mensajeError();
                }
            }
        });
        
        gridPane.add(buttonLogin, 0, 6, 1, 1);
        
        vBoxPrincipal = new VBox();
        vBoxPrincipal.getChildren().addAll(gridPane);
        
        Scene scene;
        scene = new Scene(vBoxPrincipal, 300, 400);
        
        stage.setTitle("Login FXColegio");
        stage.setScene(scene);
        stage.show();
        
    }
    
    public void mensajeError() {
        labelError.setText("Usuario ó contraseña Incorrecta");
        gridPane.add(labelError, 0, 5);
    }
    
    public void reiniciar() {
        vBoxPrincipal.getChildren().clear();
        vBoxPrincipal.getChildren().add(gridPane);
    }
    
}
