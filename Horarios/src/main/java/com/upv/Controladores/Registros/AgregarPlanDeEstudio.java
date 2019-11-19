package com.upv.Controladores.Registros;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.upv.expeciones.Mensajes;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import upv.poo.basededatos.ManagerConnection;
import upv.poo.datos.carreras.Carreras;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AgregarPlanDeEstudio implements Initializable {
    public JFXButton cancelarBtn;
    public JFXButton agregarBtn;
    public JFXComboBox<Carreras.Carrera> carreraComboBox;
    private Stage prevStage;
    @FXML private JFXTextField claveTxtF;
    @FXML private JFXTextField nombreTxtF;
    @FXML private JFXTextField nivelTxtF;

    public void setPrevStage(Stage prevStage) {
        this.prevStage = prevStage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.agregarBtn.setOnAction((event)-> agregar());
        try {
            ObservableList<Carreras.Carrera> carreraObservableList;
            carreraObservableList = FXCollections.observableArrayList(
                    ManagerConnection.getInstance().getCarreras().getCarreras()
            );
            this.carreraComboBox.setItems(carreraObservableList);
        } catch (SQLException | ClassNotFoundException e) {
            Mensajes.setMensaje(e, e.getMessage());
        }
    }

    public void salir(){
        this.prevStage.close();
    }

    private void agregar(){
        if (!this.nombreTxtF.getText().equals("") &&
                this.carreraComboBox.getSelectionModel().getSelectedItem()!=null
                && !this.nivelTxtF.getText().equals("")
                && !this.claveTxtF.getText().equals("")){
            try {
                ManagerConnection.getInstance().insertPlanEstudio(this.claveTxtF.getText(),
                        this.nombreTxtF.getText(), this.nivelTxtF.getText(),this.carreraComboBox.getSelectionModel().getSelectedItem().getId());
                Mensajes.setMensaje("Agregado exitosamente","", Alert.AlertType.INFORMATION);
            } catch (SQLException | ClassNotFoundException e) {
                Mensajes.setMensaje(e, e.getMessage());
            }
        }else{
            Mensajes.setMensaje("Faltan datos","", Alert.AlertType.WARNING);
        }
    }
}
