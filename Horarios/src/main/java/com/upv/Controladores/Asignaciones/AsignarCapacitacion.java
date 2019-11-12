package com.upv.Controladores.Asignaciones;

import com.jfoenix.controls.JFXComboBox;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AsignarCapacitacion implements Initializable {
    private Stage prevStage;

    @FXML private JFXComboBox categoriaCBox;

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
