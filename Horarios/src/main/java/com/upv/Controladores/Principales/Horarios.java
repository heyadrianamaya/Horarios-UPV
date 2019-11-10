package com.upv.Controladores.Principales;

import com.jfoenix.controls.JFXComboBox;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class Horarios implements Initializable {
    @FXML private JFXComboBox materiaCBox;
    @FXML private Label maxMatLbl;
    @FXML private Label dispMatLbl;
    @FXML private JFXComboBox grupoCBox;
    @FXML private JFXComboBox turnoCBox;
    @FXML private JFXComboBox profesorCBox;
    @FXML private Label maxProfTxtF;
    @FXML private Label dispProfTxtF;
    @FXML private Label reprobacionLbl;
    @FXML private TableView horariosTable;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
