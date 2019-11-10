package com.upv.Controladores.Principales;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class Materias implements Initializable {
    @FXML private JFXTextField archivoTxtF;
    @FXML private JFXComboBox planesCBox;
    @FXML private TableView planesTable;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void seleccionArchivo(){
        System.out.println("Abrir");
    }
}
