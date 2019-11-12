package com.upv.Controladores.Registros;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.upv.expeciones.Mensajes;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import upv.poo.basededatos.ManagerConnection;
import upv.poo.datos.plandeestudios.PlanesDeEstudio;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AgregarGrupo implements Initializable{
    public JFXButton cancelarBtn;
    public JFXButton agregarBtn;
    private Stage prevStage;
    @FXML private JFXTextField claveTxtF;
    @FXML private JFXComboBox<String> cuatrimestreCBox;
    @FXML private JFXComboBox<PlanesDeEstudio.PlanDeEstudio> planCBox;

    public void setPrevStage(Stage prevStage) {
        this.prevStage = prevStage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.cuatrimestreCBox.getItems().addAll("1","2","3","4","5","6","7","8","9");
        ObservableList<PlanesDeEstudio.PlanDeEstudio> planDeEstudios;
        try {
            planDeEstudios = FXCollections.observableArrayList(ManagerConnection.getInstance().getPlanesEstudio().getPlanesDeEstudio());
            this.planCBox.setItems(planDeEstudios);
        } catch (SQLException | ClassNotFoundException e) {
            Mensajes.setMensaje(e, e.getMessage());
        }
    }
}
