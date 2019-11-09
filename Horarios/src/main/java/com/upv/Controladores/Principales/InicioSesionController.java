package com.upv.Controladores.Principales;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import com.jfoenix.controls.*;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class InicioSesionController implements Initializable {
    private Stage prevStage;

    @FXML private JFXTextField usuarioTxtF;
    @FXML private JFXPasswordField contrasenaTxtF;

    public void setPrevStage(Stage prevStage){
        this.prevStage = prevStage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void ingresar() throws IOException {
        //Obtenecion de informacion de las cajas de datos
        String usuario = this.usuarioTxtF.getText();
        String contrasena = this.contrasenaTxtF.getText();

        if(usuario.equals("admin") && contrasena.equals("admin")){
            irPrincipal();
        }
    }

    private void irPrincipal() throws IOException {
        Stage stageBusqueda = new Stage(); //Creacion de nuevo Escenario
        stageBusqueda.setTitle("Motor de Búsqueda"); //Poner su titulo
        FXMLLoader getBusqueda = new FXMLLoader(getClass().getResource("/view/principal.fxml")); //Obtener la informacion del escenario
        Pane paneBusqueda = getBusqueda.load(); //En un pane poner los datos

        PrincipalController principalController = getBusqueda.getController(); //Clase la cual

        principalController.setPrevStage(prevStage); //Asiganmos escenario del otro

        Scene escenaBusqueda = new Scene(paneBusqueda); //Asiganar el panel a escena
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //Obtenecion de tamaño de pantalla

        //Asignacion de tamaño del escenario
        stageBusqueda.setWidth(screenSize.width);
        stageBusqueda.setHeight(screenSize.height);
        stageBusqueda.setResizable(false); //Mover el tamaño

        stageBusqueda.setScene(escenaBusqueda); //Asignamos escenario

        prevStage.close(); //Cerramos pantalla de login
        stageBusqueda.show(); //Mostramos pantalla de busqueda
    }

}
