package com.upv.Controladores.Registros;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AgregarEquipo implements Initializable {
    private Stage prevStage;
    @FXML private JFXTextField nombreTxtF;
    @FXML private JFXTextArea descripcionTxtA;
    @FXML private JFXComboBox categoriaCBox;

    public void setPrevStage(Stage prevStage) {
        this.prevStage = prevStage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
