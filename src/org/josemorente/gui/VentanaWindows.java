/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.josemorente.gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.josemorente.controlador.ControladorUsuario;

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
    private MenuItem menuItemSalir;
    private MenuItem menuItemDesconectar;
    private Stage stage;
    private TabPane tabPane;
    private Tab tabUsuario;
    private Tab tabProfesor;
    private Tab tabMateria;
    private Tab tabCarrera;
    private Tab tabGrado;
    private Tab tabSeccionTecnica;
    private Tab tabSeccionAcademica;
    private Tab tabAlumno;
    private Tab tabAsignacionSA;
    private Tab tabAsignacionST;
    private Tab tabAsignacion;
    private StackPane stackPane;

    public static VentanaWindows getInstance() {
        if (instance == null) {
            instance = new VentanaWindows();
        }
        return instance;
    }
    
    public void principal(Stage stagePrimary) {
        stage = stagePrimary;
        
        stackPane = new StackPane();
        stackPane.setId("stackPane-aplicacion");
        stackPane.getChildren().addAll(getTabPane());
        stackPane.setMinSize(1100, 600);
        
        vBoxPrincipal = new VBox();
        vBoxPrincipal.getChildren().addAll(getMenuBar(), stackPane);
        
        Scene scene;
        scene = new Scene(vBoxPrincipal, 1250, 620);
        scene.getStylesheets().add("/org/josemorente/recursos/aplicacion.css");
        
        stage.getIcons().add(new Image("/org/josemorente/recursos/Colegio.ico"));
        stage.setResizable(true);
        stage.setTitle("FXColegio v. 1.0 ");
        stage.setScene(scene);
        stage.show();
    }
    
    @Override
    public void start(Stage stagePrimary) {
        Login.getInstance().Login();
        Login.getInstance().addEventFilter(WindowEvent.WINDOW_HIDING, new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                if (ControladorUsuario.getInstance().getAutenticar()) {
                    principal(stagePrimary);
                }
             }
        });
    }
    
    private MenuBar getMenuBar() {
        menuBar = new MenuBar();
        
        menuArchivo = new Menu("_Archivo");
        
        menuUsuario = new Menu("_Usuario");
        
        menuItemSalir = new MenuItem("_Salir");
        menuItemSalir.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.close();
            }
        });
        menuItemDesconectar = new MenuItem("_Desconectar");
        menuItemDesconectar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.close();
                ControladorUsuario.getInstance().desautenticar();
                Login.getInstance().Login();
            }
        });
        menuArchivo.getItems().addAll(menuUsuario, menuItemSalir);
        menuUsuario.getItems().add(menuItemDesconectar);
        menuBar.getMenus().addAll(menuArchivo);
        return menuBar;
    }
    
    public TabPane getTabPane() {
        tabPane = new TabPane();
        tabPane.setId("tabPa");
        
        tabUsuario = new Tab("Usuarios");
        tabUsuario.setId("tabPane");
        tabUsuario.setContent(CRUDUsuario.getInstance().gethBoxCRUD());
        
        tabProfesor = new Tab("Profesores");
        tabProfesor.setId("tabPane");
        tabProfesor.setContent(CRUDProfesor.getInstance().gethBoxCRUD());
        
        tabMateria = new Tab("Materias");
        tabMateria.setId("tabPane");
        tabMateria.setContent(CRUDMateria.getInstance().gethBoxCRUD());
        
        tabCarrera = new Tab("Carreras");
        tabCarrera.setId("tabPane");
        tabCarrera.setContent(CRUDCarrera.getInstance().gethBoxCRUD());
        
        tabGrado = new Tab("Grados");
        tabGrado.setId("tabPane");
        tabGrado.setContent(CRUDGrado.getInstance().gethBoxCRUD());
        
        tabSeccionTecnica = new Tab("Secciones Técnicas");
        tabSeccionTecnica.setId("tabPane");
        tabSeccionTecnica.setContent(CRUDSeccionTecnica.getInstance().gethBoxCRUD());
        
        tabSeccionAcademica = new Tab("Secciones Académicas");
        tabSeccionAcademica.setId("tabPane");
        tabSeccionAcademica.setContent(CRUDSeccionAcademica.getInstance().gethBoxCRUD());
                
        tabAlumno = new Tab("Alumnos");
        tabAlumno.setId("tabPane");
        tabAlumno.setContent(CRUDAlumno.getInstance().gethBoxCRUD());
        
        tabAsignacion = new Tab("Profesor & Materia");
        tabAsignacion.setId("tabPane");
        tabAsignacion.setContent(CRUDProfesorMateria.getInstance().gethBoxCRUD());
        
        tabAsignacionSA = new Tab("Alumno & Académicas");
        tabAsignacionSA.setId("tabPane");
        tabAsignacionSA.setContent(CRUDAlumnoSA.getInstance().gethBoxCRUD());
        
        tabAsignacionST = new Tab("Alumno & Técnicas");
        tabAsignacionST.setId("tabPane");
        tabAsignacionST.setContent(CRUDAlumnoST.getInstance().gethBoxCRUD());
        
        tabPane.getTabs().addAll(tabUsuario, tabProfesor, tabMateria, tabAsignacion, tabCarrera, tabGrado, tabSeccionAcademica, tabSeccionTecnica,
                tabAlumno, tabAsignacionST, tabAsignacionSA);
        return tabPane;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
