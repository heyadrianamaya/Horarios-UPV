package com.upv.Controladores.Principales;

import com.jfoenix.controls.JFXListView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class PlanesDeEstudio implements Initializable {
    //Datos plan de estudios
    @FXML private Label claveLbl;
    @FXML private Label nombreLbl;
    @FXML private Label nivelLbl;
    @FXML private Label carreraLbl;

    @FXML private JFXListView planesList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
