/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.josemorente.gui;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.josemorente.database.SQLDatabaseConnection;

/**
 *
 * @author José Morente
 */
public class VentanaWindows extends Application {
    public static VentanaWindows instance;
    private VBox vBoxPrincipal;
    private MenuBar menuBar;
    private Menu menuArchivo;
    private Menu menuUsuario;
    private MenuItem menuItemConectar;
    private MenuItem menuItemDesconectar;
    private MenuItem menuItemSalir;
    private Stage stage;
    private TabPane tabPane;
    private Tab tabUsuario;
    
    private MenuBar getMenuBar() {
        menuBar = new MenuBar();
        
        menuArchivo = new Menu("_Archivo");
        
        menuUsuario = new Menu("_Usuario");
        
        menuItemConectar = new MenuItem("_Conectar");
        
        menuItemDesconectar = new MenuItem("_Desconectar");
        menuUsuario.getItems().addAll(menuItemConectar, menuItemDesconectar);
        
        menuItemSalir = new MenuItem("_Salir");
        menuItemSalir.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.close();
            }
        });
        menuArchivo.getItems().addAll(menuUsuario, menuItemSalir);
        menuBar.getMenus().add(menuArchivo);
        return menuBar;
    }
    
    public TabPane getTabPane() {
        tabPane = new TabPane();
        
        tabUsuario = new Tab("Usuarios");
        tabUsuario.setContent(CRUDUsuario.getInstance().gethBoxCRUD());
        
        tabPane.getTabs().add(tabUsuario);
        return tabPane;
    }
    
    @Override
    public void start(Stage stagePrimary) {
        stage = stagePrimary;
        vBoxPrincipal = new VBox();
        vBoxPrincipal.getChildren().addAll(getMenuBar(), getTabPane());
        
        Scene scene;
        scene = new Scene(vBoxPrincipal, 950, 600);
        
        stage.setTitle("FXColegio v. 1.0.0.0 ");
        stage.setScene(scene);
        stage.show();
        
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
