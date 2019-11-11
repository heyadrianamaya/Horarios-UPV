package com.upv.Controladores.Principales;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.upv.Controladores.Actualizaciones.ActualizarCarrera;
import com.upv.Controladores.Actualizaciones.ActualizarMaestro;
import com.upv.Controladores.Extras.CompartirMaestros;
import com.upv.expeciones.Mensajes;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import upv.poo.basededatos.ManagerConnection;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Carreras implements Initializable {
    private Stage prevStage;

    @FXML private JFXButton agregarBtn;
    @FXML private JFXListView<upv.poo.datos.carreras.Carreras.Carrera> carrerasList;
    @FXML private Label idCarreraLbl;
    @FXML private Label nombreCarreraLbl;
    @FXML private JFXButton eliminarBtn;
    @FXML private JFXButton actualizarBtn;

    public void setPrevStage(Stage prevStage){
        this.prevStage = prevStage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            ObservableList<upv.poo.datos.carreras.Carreras.Carrera> carreraObservableList =
                    FXCollections.observableArrayList(
                            ManagerConnection.getInstance().getCarreras().getCarreras()
                );
            this.carrerasList.setItems(carreraObservableList);
        }catch (Exception e){
            Mensajes.setMensaje(e, e.getMessage());
        }

    }

    public void actualizar() throws IOException {
        abrirPantalla(1);
    }

    private void abrirPantalla(int pantalla) throws IOException {
        Stage stagePantalla = new Stage(); //Creacion de nuevo Escenario
        FXMLLoader getFXML;
        Pane panePantalla; //En un pane poner los datos
        Scene scenePantalla;

        switch(pantalla){
            case 1:
                stagePantalla.setTitle("Actualizar Carrera"); //Poner su titulo
                getFXML = new FXMLLoader(getClass().getResource("/view/actualizarCarrera.fxml")); //Obtener la informacion del escenario
                panePantalla = getFXML.load(); //En un pane poner los datos

                ActualizarCarrera actualizarController = getFXML.getController(); //Clase la cual
                actualizarController.setPrevStage(prevStage); //Asiganmos escenario del otro

                scenePantalla = new Scene(panePantalla); //Asiganar el panel a escena
                stagePantalla.setScene(scenePantalla); //Asignamos escenario
                stagePantalla.initModality(Modality.APPLICATION_MODAL);
                stagePantalla.initOwner(prevStage);
                stagePantalla.show(); //Mostramos pantalla de huella
                break;
        }
    }
}
