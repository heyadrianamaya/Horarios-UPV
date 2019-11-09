package com.upv;

import com.upv.Controladores.Principales.InicioSesionController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Hello world!
 *
 */
public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Inicio de Sesión"); //Titulo pantalla inicial
        FXMLLoader obtenerSesion = new FXMLLoader(getClass().getResource("/view/iniciarSesion.fxml")); //Obtener datos de pantalla

        Pane paneSesion = obtenerSesion.load(); //Obtenemos los datos

        InicioSesionController claseLogin = obtenerSesion.getController();

        claseLogin.setPrevStage(primaryStage);

        Scene escenaSesion = new Scene(paneSesion);
        primaryStage.setResizable(false); //Mover el tamaño
        primaryStage.setScene(escenaSesion); //Subimos la escena
        primaryStage.show(); //Mostramos la pantalla
    }

    public static void main(String[] args) {
        launch(args);
    }

}
