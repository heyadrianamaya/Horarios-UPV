package com.upv.Controladores.Principales;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import upv.poo.datos.login.Login;
import upv.poo.utils.TipoUsuario;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PrincipalController implements Initializable {
    public JFXButton disponibilidadBtn;
    public JFXButton materiasBtn;
    public JFXButton maestrosBtn;
    public JFXButton carrerasBtn;
    public JFXButton planesBtn;
    public JFXButton aulasBtn;
    public JFXButton gruposBtn;
    public JFXButton horariosBtn;
    public JFXButton PuntosBtn;
    public JFXButton chatBtn;
    public JFXButton inglesBtn;
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
                    disableAllButtons();
                    break;
                case ADMIN:
                    System.out.println("Administrador");
                    break;
                case DIRECTOR:
                    System.out.println("Director");
                    this.carrerasBtn.setDisable(true);
                case PROFESOR:
                    System.out.println("Profesor");
                    this.aulasBtn.setDisable(true);
                    this.carrerasBtn.setDisable(true);
                    this.gruposBtn.setDisable(true);
                    this.planesBtn.setDisable(true);
                    this.inglesBtn.setDisable(true);
                    this.PuntosBtn.setDisable(true);
                    disponibilidad();
                    break;
            }
        }
    }

    public void setPrevStage(Stage prevStage){
        this.prevStage = prevStage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    private void cambiarPantalla(int pantalla) throws IOException {
        FXMLLoader getFXML;
        switch (pantalla){
            case 1: //Disponibilidad
                getFXML = new FXMLLoader(getClass().getResource("/view/disponibilidad.fxml")); //Obtener la informacion del escenario
                panelSecundario = getFXML.load();
                Disponibilidad disponibilidad = getFXML.getController();
                disponibilidad.setParameter(this.login);
                break;
            case 2: //Materias
                getFXML = new FXMLLoader(getClass().getResource("/view/materias.fxml")); //Obtener la informacion del escenario
                panelSecundario = getFXML.load();
                break;
            case 3: //Maestros
                getFXML = new FXMLLoader(getClass().getResource("/view/maestros.fxml")); //Obtener la informacion del escenario
                panelSecundario = getFXML.load();
                Maestros maestrosController = getFXML.getController(); //Clase la cual
                if (this.login!=null)
                    maestrosController.setParameter(this.login);
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
                if(this.login!=null)
                    gruposController.setParameter(this.login);
                gruposController.setPrevStage(prevStage); //Asiganmos escenario del otro
                break;
            case 8: //Horarios
                getFXML = new FXMLLoader(getClass().getResource("/view/horarios.fxml")); //Obtener la informacion del escenario
                panelSecundario = getFXML.load();
                Horarios horariosController = getFXML.getController();
                if (this.login!=null){
                    horariosController.setParameter(this.login);
                }
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
    private void disableAllButtons(){
        this.aulasBtn.setDisable(true);
        this.carrerasBtn.setDisable(true);
        this.chatBtn.setDisable(true);
        this.disponibilidadBtn.setDisable(true);
        this.gruposBtn.setDisable(true);
        this.horariosBtn.setDisable(true);
        this.maestrosBtn.setDisable(true);
        this.materiasBtn.setDisable(true);
        this.planesBtn.setDisable(true);
        this.inglesBtn.setDisable(true);
        this.PuntosBtn.setDisable(true);
    }
}
