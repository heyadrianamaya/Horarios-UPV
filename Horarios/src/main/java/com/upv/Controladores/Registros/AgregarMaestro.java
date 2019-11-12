package com.upv.Controladores.Registros;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.upv.Controladores.Principales.PrincipalController;
import com.upv.expeciones.Mensajes;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import upv.poo.basededatos.ManagerConnection;
import upv.poo.datos.carreras.Carreras;
import upv.poo.utils.TipoUsuario;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AgregarMaestro implements Initializable {
    public JFXButton registrarBtn;
    public JFXButton cancelarBtn;
    private Stage prevStage;

    @FXML private JFXTextField nombreTxtF;
    @FXML private JFXTextField telefonoTxtF;
    @FXML private JFXComboBox<String> nivelCBox;
    @FXML private JFXComboBox<Carreras.Carrera> carreraCBox;
    @FXML private JFXComboBox<String> contratoCBox;
    @FXML private JFXTextField imrTxtF;
    @FXML private JFXTextField usuarioTxtF;
    @FXML private JFXTextField contrasenaTxtF;

    public void setPrevStage(Stage prevStage){
        this.prevStage = prevStage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Carreras.Carrera> carreraObservableList;
        try {
            carreraObservableList = FXCollections.observableArrayList(
                    ManagerConnection.getInstance().getCarreras().getCarreras()
            );
            this.carreraCBox.setItems(carreraObservableList);
            this.nivelCBox.getItems().addAll("-","Dr.","M.C","Lic.");
            this.nivelCBox.getSelectionModel().select(0);
            this.contratoCBox.getItems().addAll("-","PA","PTC");
            this.contratoCBox.getSelectionModel().select(0);
            this.registrarBtn.setOnAction(event -> agregar());
        } catch (SQLException | ClassNotFoundException e) {
            Mensajes.setMensaje(e, e.getMessage());
        }
    }
    private void agregar(){
        if (!this.nombreTxtF.getText().equals("") && !this.telefonoTxtF.getText().equals("")
        && !this.nivelCBox.getSelectionModel().getSelectedItem().equals("") &&
        this.carreraCBox.getSelectionModel().getSelectedItem()!=null &&
        this.contratoCBox.getSelectionModel().getSelectedItem()!=null &&
        !this.imrTxtF.getText().equals("") && !this.usuarioTxtF.getText().equals("")
        && !this.contrasenaTxtF.getText().equals("")){
            try {
                ManagerConnection.getInstance().insertUsuario(
                        this.usuarioTxtF.getText(),
                        this.contrasenaTxtF.getText(),
                        TipoUsuario.PROFESOR,
                        this.carreraCBox.getSelectionModel().getSelectedItem().getId(),
                        this.nombreTxtF.getText(),
                        this.nivelCBox.getSelectionModel().getSelectedItem(),
                        this.contratoCBox.getSelectionModel().getSelectedItem(),
                        this.telefonoTxtF.getText(),
                        Integer.parseInt(this.imrTxtF.getText())

                );
                Mensajes.setMensaje("Agregado correctamente","Agregado", Alert.AlertType.INFORMATION);
                this.prevStage.close();
            } catch (SQLException | ClassNotFoundException e) {
                Mensajes.setMensaje(e, e.getMessage());
            }
        }else{
            Mensajes.setMensaje("Datos imcompletos","", Alert.AlertType.WARNING);
        }
    }

    public void salir(){
        this.prevStage.close();
    }

}
