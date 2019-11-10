package com.upv.Controladores.Principales;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class Chat implements Initializable {
    @FXML private JFXListView planesList;
    @FXML private JFXButton actualizarBtn;
    @FXML private JFXTextArea textoTxtF;
    @FXML private JFXTextArea mensajesTxtF;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
