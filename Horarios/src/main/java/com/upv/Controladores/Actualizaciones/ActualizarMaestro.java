package com.upv.Controladores.Actualizaciones;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ActualizarMaestro implements Initializable {
    private Stage prevStage;
    @FXML private JFXTextField nombreTxt;
    @FXML private JFXTextField telefonoTxt;
    @FXML private JFXComboBox nivelCBox;
    @FXML private JFXComboBox carreraCBox;
    @FXML private JFXComboBox contratoCBox;
    @FXML private JFXTextField imrTxt;

    public void setPrevStage(Stage prevStage){
        this.prevStage = prevStage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
