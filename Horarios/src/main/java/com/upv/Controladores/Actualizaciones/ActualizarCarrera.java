package com.upv.Controladores.Actualizaciones;

import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ActualizarCarrera implements Initializable {
    private Stage prevStage;

    @FXML private JFXTextField idTxtF;
    @FXML private JFXTextField nombreTxtF;

    public void setPrevStage(Stage prevStage){
        this.prevStage = prevStage;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
