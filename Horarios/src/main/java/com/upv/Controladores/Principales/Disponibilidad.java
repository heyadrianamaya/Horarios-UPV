package com.upv.Controladores.Principales;

import com.jfoenix.controls.JFXComboBox;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class Disponibilidad implements Initializable {
    @FXML private JFXComboBox profesoresCBox;
    @FXML private JFXComboBox turnoCBox;
    @FXML private TableView disponiblesTable;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
