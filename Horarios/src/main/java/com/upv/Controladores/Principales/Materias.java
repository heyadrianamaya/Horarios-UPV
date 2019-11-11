package com.upv.Controladores.Principales;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.upv.expeciones.Mensajes;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import upv.poo.basededatos.ManagerConnection;
import upv.poo.datos.plandeestudios.PlanesDeEstudio;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Materias implements Initializable {
    @FXML private JFXTextField archivoTxtF;
    @FXML private JFXComboBox<PlanesDeEstudio.PlanDeEstudio> planesCBox;
    @FXML private TableView planesTable;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            ObservableList<PlanesDeEstudio.PlanDeEstudio> estudios =
                    FXCollections.observableArrayList(ManagerConnection.getInstance().getPlanesEstudio().getPlanesDeEstudio());
            this.planesCBox.setItems(estudios);
        } catch (SQLException | ClassNotFoundException e) {
            Mensajes.setMensaje(e, e.getMessage());
        }
    }

    public void seleccionArchivo(){
        System.out.println("Abrir");
    }
}
