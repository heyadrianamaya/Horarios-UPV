package com.upv.Controladores.Principales;

import com.jfoenix.controls.JFXListView;
import com.upv.expeciones.Mensajes;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import upv.poo.basededatos.ManagerConnection;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class PlanesDeEstudio implements Initializable {
    //Datos plan de estudios
    @FXML private Label claveLbl;
    @FXML private Label nombreLbl;
    @FXML private Label nivelLbl;
    @FXML private Label carreraLbl;

    @FXML private JFXListView<upv.poo.datos.plandeestudios.PlanesDeEstudio.PlanDeEstudio> planesList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<upv.poo.datos.plandeestudios.PlanesDeEstudio.PlanDeEstudio> planDeEstudios;
        try {
            planDeEstudios = FXCollections.observableArrayList(
                    ManagerConnection.getInstance().getPlanesEstudio().getPlanesDeEstudio()
            );
            this.planesList.setItems(planDeEstudios);
        } catch (SQLException | ClassNotFoundException e) {
            Mensajes.setMensaje(e, e.getMessage());
        }
    }
}
