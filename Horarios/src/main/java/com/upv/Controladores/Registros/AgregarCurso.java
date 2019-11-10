package com.upv.Controladores.Registros;

import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AgregarCurso implements Initializable {
    private Stage prevStage;
    @FXML private JFXTextField nombreTxtF;
    @FXML private JFXTextField detallesTxtF;

    public void setPrevStage(Stage prevStage) {
        this.prevStage = prevStage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
