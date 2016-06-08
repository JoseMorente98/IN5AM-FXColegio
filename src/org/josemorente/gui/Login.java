/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.josemorente.gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.josemorente.controlador.ControladorUsuario;

/**
 *
 * @author José Morente
 */
public class Login extends Stage {
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
    private StackPane stackPane;
    private Scene scene;

    public static Login getInstance() {
        if (instance == null) {
            instance = new Login();
        }
        return instance;
    }

    public GridPane getGridPane() {
        gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));
        
        textTitulo = new Text("LOGIN");
        textTitulo.setId("textTitulo");
        textTitulo.setFill(Color.WHITESMOKE);
        textTitulo.setFont(Font.font(Font.getDefault().getFamily(), 35));
        gridPane.add(textTitulo, 0, 0);
        
        textTituloA = new Text("Nombre de Usuario:");
        textTituloA.setFill(Color.WHITESMOKE);
        textTituloA.setFont(Font.font(Font.getDefault().getFamily(), 14));
        textFieldUsuario = new TextField();
        textFieldUsuario.setPromptText("Usuario ");
        gridPane.add(textTituloA ,  0, 1, 1, 1);
        gridPane.add(textFieldUsuario ,  0, 2, 1, 1);
        
        textTituloB = new Text("Contraseña de Usuario:");
        textTituloB.setFill(Color.WHITESMOKE);
        textTituloB.setFont(Font.font(Font.getDefault().getFamily(), 14));
        passwordFieldPassword = new PasswordField();
        passwordFieldPassword.setPromptText("Contraseña");
        gridPane.add(textTituloB, 0, 3, 1, 1);
        gridPane.add(passwordFieldPassword, 0, 4, 1, 1);
        
        buttonLogin = new Button("Ingresar");
        buttonLogin.setId("buttonLogin");
        buttonLogin.setDefaultButton(true);
        buttonLogin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ingresar(textFieldUsuario.getText(), passwordFieldPassword.getText());
            }
        });
        gridPane.add(buttonLogin, 0, 6, 1, 1);
        
        return gridPane;
    }
    
    public void Login() {
        stackPane = new StackPane();
        stackPane.setId("stackPane-login");
        stackPane.getChildren().addAll(getGridPane());
        stackPane.setMinSize(300, 400);
        
        vBoxPrincipal = new VBox();
        vBoxPrincipal.getChildren().addAll(stackPane);
        
        scene = new Scene(vBoxPrincipal, 300, 400);
        scene.getStylesheets().add("/org/josemorente/recursos/login.css");
        
        this.setResizable(false);
        this.setScene(scene);
        this.getIcons().add(new Image("/org/josemorente/recursos/Colegio.ico"));
        this.setTitle("Login FXColegio");
        this.show();
    }
    
     private void ingresar(String nombre, String clave) {
        if (ControladorUsuario.getInstance().autenticar(nombre, clave)) {
            this.close();
        }
    }
    
}