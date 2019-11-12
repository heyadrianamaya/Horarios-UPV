package com.upv.Controladores.Principales;

import com.upv.expeciones.Mensajes;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import com.jfoenix.controls.*;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import upv.poo.basededatos.ManagerConnection;
import upv.poo.datos.login.Login;

import java.awt.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class InicioSesionController implements Initializable {
    private Stage prevStage;

    @FXML private JFXTextField usuarioTxtF;
    @FXML private JFXPasswordField contrasenaTxtF;
    private Login login;

    public void setPrevStage(Stage prevStage){
        this.prevStage = prevStage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.contrasenaTxtF.setOnKeyPressed((e)->{
            if (e.getCode() == KeyCode.ENTER){
                try {
                    ingresar();
                } catch (IOException ex) {
                    Mensajes.setMensaje(ex, ex.getMessage());
                }
            }
        });
        this.usuarioTxtF.setOnKeyPressed((e)->{
            if (e.getCode() == KeyCode.ENTER){
                try {
                    ingresar();
                } catch (IOException ex) {
                    Mensajes.setMensaje(ex, ex.getMessage());
                }
            }
        });
    }

    public void ingresar() throws IOException {
        //Obtenecion de informacion de las cajas de datos
        String usuario = this.usuarioTxtF.getText();
        String contrasena = this.contrasenaTxtF.getText();
        try {
            this.login = ManagerConnection.getInstance().getLogin(usuario, contrasena);
            if (this.login!=null){
                irPrincipal();
            }else{
                Mensajes.setMensaje("Acceso denegado","Ingresa correctamente los datos", Alert.AlertType.WARNING);
            }
        } catch (SQLException | ClassNotFoundException e) {
            Mensajes.setMensaje(e, e.getMessage());
        }
    }

    private void irPrincipal() throws IOException {
        Stage stageBusqueda = new Stage(); //Creacion de nuevo Escenario
        stageBusqueda.setTitle("Iniciar sesion"); //Poner su titulo
        FXMLLoader getBusqueda = new FXMLLoader(getClass().getResource("/view/principal.fxml")); //Obtener la informacion del escenario
        Pane paneBusqueda = getBusqueda.load(); //En un pane poner los datos

        PrincipalController principalController = getBusqueda.getController(); //Clase la cual

        principalController.setPrevStage(prevStage); //Asiganmos escenario del otro
        principalController.setLogin(this.login);

        Scene escenaBusqueda = new Scene(paneBusqueda); //Asiganar el panel a escena
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //Obtenecion de tamaño de pantalla

        //Asignacion de tamaño del escenario
        stageBusqueda.setWidth(screenSize.width);
        stageBusqueda.setHeight(screenSize.height);
        stageBusqueda.setResizable(false); //Mover el tamaño
        //stageBusqueda.setFullScreen(true);

        stageBusqueda.setScene(escenaBusqueda); //Asignamos escenario

        prevStage.close(); //Cerramos pantalla de login
        stageBusqueda.show(); //Mostramos pantalla de busqueda
    }

}
