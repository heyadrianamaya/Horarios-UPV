package com.upv.Controladores.Principales;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.upv.Controladores.Actualizaciones.ActualizarCarrera;
import com.upv.Controladores.Actualizaciones.ActualizarMaestro;
import com.upv.Controladores.Extras.CompartirMaestros;
import com.upv.Controladores.Registros.AgregarCarrera;
import com.upv.expeciones.Mensajes;
import com.upv.utils.ChangeValues;
import com.upv.utils.Parametized;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import upv.poo.basededatos.ManagerConnection;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Carreras implements Initializable, ChangeValues {
    private Stage prevStage;

    @FXML private JFXButton agregarBtn;
    @FXML private JFXListView<upv.poo.datos.carreras.Carreras.Carrera> carrerasList;
    @FXML private Label idCarreraLbl;
    @FXML private Label nombreCarreraLbl;
    @FXML private JFXButton eliminarBtn;
    @FXML private JFXButton actualizarBtn;

    private upv.poo.datos.carreras.Carreras.Carrera carreraSelected;

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
            this.carrerasList.getSelectionModel().selectedItemProperty()
                    .addListener((observable, oldValue, newValue) -> {
                        carreraSelected = newValue;
                        onChangeValueInfo();
                    });
            onChangeValueInfo();
            this.eliminarBtn.setOnAction((e)->eliminar());
        }catch (Exception e){
            Mensajes.setMensaje(e, e.getMessage());
        }

    }

    public void actualizar() throws IOException {
        abrirPantalla(1);
    }
    public void agregar() throws IOException{
        abrirPantalla(2);
    }
    public void eliminar(){
        if (Mensajes.setAvisoConfirmacion("Eliminar carrera",
                this.carreraSelected.toString(), Alert.AlertType.CONFIRMATION).get() == ButtonType.OK){
            try {
                ManagerConnection.getInstance().deleteCarrera(this.carreraSelected.getId());
            } catch (SQLException | ClassNotFoundException e) {
                Mensajes.setMensaje(e, e.getMessage());
            }
        }
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
                actualizarController.setPrevStage(stagePantalla); //Asiganmos escenario del otro
                if (this.carreraSelected!=null){
                    actualizarController.setParameter(this.carreraSelected);
                }

                scenePantalla = new Scene(panePantalla); //Asiganar el panel a escena
                stagePantalla.setScene(scenePantalla); //Asignamos escenario
                stagePantalla.initModality(Modality.APPLICATION_MODAL);
                stagePantalla.initOwner(prevStage);
                stagePantalla.show(); //Mostramos pantalla de huella
                break;
            case 2:
                stagePantalla.setTitle("Agregar carrera"); //Poner su titulo
                getFXML = new FXMLLoader(getClass().getResource("/view/agregarCarrera.fxml")); //Obtener la informacion del escenario
                panePantalla = getFXML.load(); //En un pane poner los datos

                AgregarCarrera agregarCarrera = getFXML.getController(); //Clase la cual
                agregarCarrera.setPrevStage(stagePantalla); //Asiganmos escenario del otro

                scenePantalla = new Scene(panePantalla); //Asiganar el panel a escena
                stagePantalla.setScene(scenePantalla); //Asignamos escenario
                stagePantalla.initModality(Modality.APPLICATION_MODAL);
                stagePantalla.initOwner(prevStage);
                stagePantalla.show(); //Mostramos pantalla de huella
                break;
        }
    }

    @Override
    public void onChangeValueInfo() {
        if (this.carreraSelected!=null){
            this.idCarreraLbl.setText(String.valueOf(this.carreraSelected.getId()));
            this.nombreCarreraLbl.setText(this.carreraSelected.getNombre());
            this.actualizarBtn.setVisible(true);
            this.eliminarBtn.setVisible(true);
        }else{
            this.idCarreraLbl.setText("");
            this.nombreCarreraLbl.setText("");
            this.actualizarBtn.setVisible(false);
            this.eliminarBtn.setVisible(false);
        }
    }
}
