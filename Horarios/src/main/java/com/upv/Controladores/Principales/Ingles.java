package com.upv.Controladores.Principales;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Ingles implements Initializable {
    private Stage prevStage;

    @FXML private JFXListView cursosList;
    @FXML private Label nombreLbl;
    @FXML private Label detallesLbl;
    @FXML private JFXComboBox turnoCBox;
    @FXML private TableView cursosTable;

    public void setPrevStage(Stage prevStage){

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
