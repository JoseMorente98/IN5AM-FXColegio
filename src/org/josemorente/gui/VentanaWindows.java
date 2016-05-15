/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.josemorente.gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author José Morente
 */
public class VentanaWindows extends Application {
    public static VentanaWindows instance;
    private VBox vBoxPrincipal;
    private MenuBar menuBar;
    private Menu menuArchivo;
    private MenuItem menuItemSalir;
    private Stage stage;
    private TabPane tabPane;
    private Tab tabUsuario;
    private Tab tabProfesor;
    private Tab tabMateria;
    private Tab tabCarrera;
    
    private MenuBar getMenuBar() {
        menuBar = new MenuBar();
        
        menuArchivo = new Menu("_Archivo");
        
        menuItemSalir = new MenuItem("_Salir");
        menuItemSalir.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.close();
            }
        });
        menuArchivo.getItems().add(menuItemSalir);
        menuBar.getMenus().add(menuArchivo);
        return menuBar;
    }
    
    public TabPane getTabPane() {
        tabPane = new TabPane();
        
        tabUsuario = new Tab("Usuarios");
        tabUsuario.setContent(CRUDUsuario.getInstance().gethBoxCRUD());
        
        tabProfesor = new Tab("Profesores");
        tabProfesor.setContent(CRUDProfesor.getInstance().gethBoxCRUD());
        
        tabMateria = new Tab("Materias");
        tabMateria.setContent(CRUDMateria.getInstance().gethBoxCRUD());
        
        tabCarrera = new Tab("Carreras");
        tabCarrera.setContent(CRUDCarrera.getInstance().gethBoxCRUD());
        
        tabPane.getTabs().addAll(tabUsuario, tabProfesor, tabMateria, tabCarrera);
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
