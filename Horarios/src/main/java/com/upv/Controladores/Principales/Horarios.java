package com.upv.Controladores.Principales;

import com.jfoenix.controls.JFXComboBox;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

public class Horarios {
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
}
