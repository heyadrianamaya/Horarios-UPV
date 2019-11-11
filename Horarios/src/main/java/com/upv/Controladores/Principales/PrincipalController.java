package com.upv.Controladores.Principales;

import com.upv.expeciones.Mensajes;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import upv.poo.basededatos.ManagerConnection;
import upv.poo.datos.login.Login;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class PrincipalController implements Initializable {
    @FXML private Label nombreUsuario;
    @FXML private BorderPane pantallaBorder;
    @FXML private Pane panelSecundario;
    private Stage prevStage;
    private Login login;

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) throws IOException {
        this.login = login;
        if (this.login!=null){
            this.nombreUsuario.setText(this.login.getUsuario());
            switch (this.login.getTipoUsuario()){
                case SIN_AUTO:
                    System.out.println("Sin autorizacion");
                    break;
                case DIRECTOR:
                    System.out.println("Director");
                    break;
                case PROFESOR:
                    System.out.println("Profesor");
                    break;
                case ADMIN:
                    System.out.println("Administrador");
                    break;
            }
            disponibilidad();
        }
    }

    public void setPrevStage(Stage prevStage){
        this.prevStage = prevStage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void cambiarPantalla(int pantalla) throws IOException {
        FXMLLoader getFXML;
        switch (pantalla){
            case 1: //Disponibilidad
                getFXML = new FXMLLoader(getClass().getResource("/view/disponibilidad.fxml")); //Obtener la informacion del escenario
                panelSecundario = getFXML.load();

                break;
            case 2: //Materias
                getFXML = new FXMLLoader(getClass().getResource("/view/materias.fxml")); //Obtener la informacion del escenario
                panelSecundario = getFXML.load();
                break;
            case 3: //Maestros
                getFXML = new FXMLLoader(getClass().getResource("/view/maestros.fxml")); //Obtener la informacion del escenario
                panelSecundario = getFXML.load();
                Maestros maestrosController = getFXML.getController(); //Clase la cual
                maestrosController.setPrevStage(prevStage); //Asiganmos escenario del otro
                break;
            case 4: //Carreras
                getFXML = new FXMLLoader(getClass().getResource("/view/carreras.fxml")); //Obtener la informacion del escenario
                panelSecundario = getFXML.load();
                Carreras carrerasController = getFXML.getController(); //Clase la cual
                carrerasController.setPrevStage(prevStage); //Asiganmos escenario del otro
            break;
            case 5: //Planes de estudio
                getFXML = new FXMLLoader(getClass().getResource("/view/planesDeEstudio.fxml")); //Obtener la informacion del escenario
                panelSecundario = getFXML.load();
                break;
            case 6: //Aulas
                getFXML = new FXMLLoader(getClass().getResource("/view/aulas.fxml")); //Obtener la informacion del escenario
                panelSecundario = getFXML.load();
                Aulas aulasController = getFXML.getController(); //Clase la cual
                aulasController.setPrevStage(prevStage); //Asiganmos escenario del otro
                break;
            case 7: //Grupos
                getFXML = new FXMLLoader(getClass().getResource("/view/grupos.fxml")); //Obtener la informacion del escenario
                panelSecundario = getFXML.load();
                Grupos gruposController = getFXML.getController(); //Clase la cual
                gruposController.setPrevStage(prevStage); //Asiganmos escenario del otro
                break;
            case 8: //Horarios
                getFXML = new FXMLLoader(getClass().getResource("/view/horarios.fxml")); //Obtener la informacion del escenario
                panelSecundario = getFXML.load();
                break;
            case 9:
                getFXML = new FXMLLoader(getClass().getResource("/view/puntosDeConfianza.fxml")); //Obtener la informacion del escenario
                panelSecundario = getFXML.load();
                break;
            case 10:
                getFXML = new FXMLLoader(getClass().getResource("/view/chat.fxml")); //Obtener la informacion del escenario
                panelSecundario = getFXML.load();
                break;
            case 11: //Ingles
                getFXML = new FXMLLoader(getClass().getResource("/view/ingles.fxml")); //Obtener la informacion del escenario
                panelSecundario = getFXML.load();
                Ingles inglesController = getFXML.getController(); //Clase la cual
                inglesController.setPrevStage(prevStage); //Asiganmos escenario del otro
                break;
        }
        pantallaBorder.setCenter(panelSecundario);
    }

    public void disponibilidad() throws IOException {
        cambiarPantalla(1);
    }
    public void materias() throws IOException {
        cambiarPantalla(2);
    }
    public void maestros() throws IOException {
        cambiarPantalla(3);
    }
    public void carreras() throws IOException {
        cambiarPantalla(4);
    }
    public void planes() throws IOException {
        cambiarPantalla(5);
    }
    public void aulas() throws IOException {
        cambiarPantalla(6);
    }
    public void grupos() throws IOException {
        cambiarPantalla(7);
    }
    public void horarios() throws IOException {
        cambiarPantalla(8);
    }
    public void puntos() throws IOException {
        cambiarPantalla(9);
    }
    public void chat() throws IOException {
        cambiarPantalla(10);
    }
    public void ingles() throws IOException {
        cambiarPantalla(11);
    }
}
