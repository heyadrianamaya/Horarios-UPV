package com.upv.Controladores.Asignaciones;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AsignacionEquipo implements Initializable{
    private Stage prevStage;

    @FXML private JFXComboBox equipoCBox;
    @FXML private JFXTextField requeridoTxtF;
    @FXML private Label cantDisponibleLbl;

    public void setPrevStage(Stage prevStage){
        this.prevStage = prevStage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
