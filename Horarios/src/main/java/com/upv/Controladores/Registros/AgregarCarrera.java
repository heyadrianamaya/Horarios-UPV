package com.upv.Controladores.Registros;

import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AgregarCarrera implements Initializable {
    private Stage prevStage;
    @FXML private JFXTextField idCarreraTxtF;
    @FXML private JFXTextField nombreCarreraTxtF;

    public void setPrevStage(Stage prevStage) {
        this.prevStage = prevStage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void salir(){
        this.prevStage.close();
    }
}
