package com.upv.Controladores.Principales;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.upv.Controladores.Actualizaciones.ActualizarPlanDeEstudio;
import com.upv.Controladores.Registros.AgregarPlanDeEstudio;
import com.upv.expeciones.Mensajes;
import com.upv.utils.ChangeValues;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import upv.poo.basededatos.ManagerConnection;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class PlanesDeEstudio implements Initializable, ChangeValues {
    public JFXButton eliminarBtn;
    public JFXButton actualizarBtn;
    public JFXButton agregarBtn;
    //Datos plan de estudios
    @FXML private Label claveLbl;
    @FXML private Label nombreLbl;
    @FXML private Label nivelLbl;
    @FXML private Label carreraLbl;
    @FXML private JFXListView<upv.poo.datos.plandeestudios.PlanesDeEstudio.PlanDeEstudio> planesList;
    private upv.poo.datos.plandeestudios.PlanesDeEstudio.PlanDeEstudio planDeEstudioSelected;
    private Stage prevStage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<upv.poo.datos.plandeestudios.PlanesDeEstudio.PlanDeEstudio> planDeEstudios;
        try {
            planDeEstudios = FXCollections.observableArrayList(
                    ManagerConnection.getInstance().getPlanesEstudio().getPlanesDeEstudio()
            );
            this.planesList.setItems(planDeEstudios);
            this.planesList.getSelectionModel().selectedItemProperty()
                    .addListener((observable, oldValue, newValue) -> {
                        planDeEstudioSelected = newValue;
                        onChangeValueInfo();
                    });
            onChangeValueInfo();
        } catch (SQLException | ClassNotFoundException e) {
            Mensajes.setMensaje(e, e.getMessage());
        }
    }

    public void agregar() throws IOException {
        abrirPantalla(2);
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
                stagePantalla.setTitle("Actualizar plan de estudio"); //Poner su titulo
                getFXML = new FXMLLoader(getClass().getResource("/view/actualizarPlanDeEstudio.fxml")); //Obtener la informacion del escenario
                panePantalla = getFXML.load(); //En un pane poner los datos

                ActualizarPlanDeEstudio actualizarPlanDeEstudio = getFXML.getController(); //Clase la cual
                if (this.planDeEstudioSelected!=null)
                    actualizarPlanDeEstudio.setParameter(this.planDeEstudioSelected);
                actualizarPlanDeEstudio.setPrevStage(stagePantalla); //Asiganmos escenario del otro

                scenePantalla = new Scene(panePantalla); //Asiganar el panel a escena
                stagePantalla.setScene(scenePantalla); //Asignamos escenario
                stagePantalla.initModality(Modality.APPLICATION_MODAL);
                stagePantalla.initOwner(prevStage);
                stagePantalla.show(); //Mostramos pantalla de huella
                break;
            case 2:
                stagePantalla.setTitle("Agregar plan de estudio"); //Poner su titulo
                getFXML = new FXMLLoader(getClass().getResource("/view/agregarPlanDeEstudio.fxml")); //Obtener la informacion del escenario
                panePantalla = getFXML.load(); //En un pane poner los datos

                AgregarPlanDeEstudio agregarPlanDeEstudio = getFXML.getController(); //Clase la cual
                agregarPlanDeEstudio.setPrevStage(stagePantalla); //Asiganmos escenario del otro

                scenePantalla = new Scene(panePantalla); //Asiganar el panel a escena
                stagePantalla.setScene(scenePantalla); //Asignamos escenario
                stagePantalla.initModality(Modality.APPLICATION_MODAL);
                stagePantalla.initOwner(prevStage);
                stagePantalla.show(); //Mostramos pantalla de huella
                break;
        }
    }

    public void setPrevStage(Stage prevStage) {
        this.prevStage = prevStage;
    }

    public Stage getPrevStage() {
        return prevStage;
    }

    @Override
    public void onChangeValueInfo() {
        if (this.planDeEstudioSelected!=null){
            try {
                this.nombreLbl.setText(this.planDeEstudioSelected.getNombre());
                this.carreraLbl.setText(String.valueOf(
                        ManagerConnection.getInstance().getCarreras().getCarrera(this.planDeEstudioSelected.getId_carrera())
                ));

                this.nivelLbl.setText(this.planDeEstudioSelected.getNivel());
                this.claveLbl.setText(this.planDeEstudioSelected.getClave());
                this.actualizarBtn.setVisible(true);
                this.eliminarBtn.setVisible(true);
            } catch (SQLException | ClassNotFoundException e) {
                Mensajes.setMensaje(e, e.getMessage());
                this.nombreLbl.setText("");
                this.carreraLbl.setText("");
                this.nivelLbl.setText("");
                this.claveLbl.setText("");
                this.actualizarBtn.setVisible(false);
                this.eliminarBtn.setVisible(false);
            }
        }else{
            this.nombreLbl.setText("");
            this.carreraLbl.setText("");
            this.nivelLbl.setText("");
            this.claveLbl.setText("");
            this.actualizarBtn.setVisible(false);
            this.eliminarBtn.setVisible(false);
        }
    }
}
