package com.upv.Controladores.Registros;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AgregarGrupo implements Initializable {
    private Stage prevStage;
    @FXML private JFXTextField claveTxtF;
    @FXML private JFXComboBox cuatrimestreCBox;
    @FXML private JFXComboBox planCBox;

    public void setPrevStage(Stage prevStage) {
        this.prevStage = prevStage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
