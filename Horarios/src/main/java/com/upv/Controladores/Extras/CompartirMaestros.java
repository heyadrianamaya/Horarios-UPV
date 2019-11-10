package com.upv.Controladores.Extras;

import com.jfoenix.controls.JFXCheckBox;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class CompartirMaestros implements Initializable {
    private Stage prevStage;

    @FXML private JFXCheckBox mecaCBtn;
    @FXML private JFXCheckBox itiCBtn;
    @FXML private JFXCheckBox manuCBtn;
    @FXML private JFXCheckBox pymesCBtn;

    public void setPrevStage(Stage prevStage){
        this.prevStage = prevStage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
