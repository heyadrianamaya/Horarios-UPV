package com.upv.Controladores.Principales;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import upv.poo.basededatos.ManagerConnection;
import upv.poo.datos.plandeestudios.PlanesDeEstudio;

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
        ObservableList<upv.poo.datos.grupos.Grupos.Grupo> grupos;
//        grupos = FXCollections.observableArrayList(ManagerConnection.getInstance().getGrupos());
    }
}
