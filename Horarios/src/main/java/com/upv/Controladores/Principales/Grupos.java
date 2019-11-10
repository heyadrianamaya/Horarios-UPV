package com.upv.Controladores.Principales;

import com.jfoenix.controls.JFXComboBox;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Grupos implements Initializable {
    private Stage prevStage;

    @FXML private TableView horarioTable;
    @FXML private JFXComboBox turnoCBox;
    @FXML private JFXComboBox grupoCBox;

    public void setPrevStage(Stage prevStage){
        this.prevStage = prevStage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
