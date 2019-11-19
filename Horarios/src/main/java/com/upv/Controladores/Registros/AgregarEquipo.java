package com.upv.Controladores.Registros;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.upv.expeciones.Mensajes;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import upv.poo.basededatos.ManagerConnection;
import upv.poo.datos.aulas.Categorias;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AgregarEquipo implements Initializable {
    public JFXButton cancelarBtn;
    public JFXButton agregarBtn;
    private Stage prevStage;
    @FXML private JFXTextField nombreTxtF;
    @FXML private JFXTextArea descripcionTxtA;
    @FXML private JFXComboBox<Categorias.Categoria> categoriaCBox;

    public void setPrevStage(Stage prevStage) {
        this.prevStage = prevStage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Categorias.Categoria> categorias;
        try {
            categorias = FXCollections.observableArrayList(
                    ManagerConnection.getInstance().getCategorias().getCategorias()
            );
            this.categoriaCBox.setItems(categorias);
            this.agregarBtn.setOnAction((event -> agregar()));
        } catch (SQLException | ClassNotFoundException e) {
            Mensajes.setMensaje(e, e.getMessage());
        }
    }

    public void salir(){
        this.prevStage.close();
    }

    private void agregar(){
        if (!this.nombreTxtF.getText().equals("") &&
        !this.descripcionTxtA.getText().equals("") &&
        this.categoriaCBox.getSelectionModel().getSelectedItem()!=null){
            try {
                ManagerConnection.getInstance().insertEquipo(
                        this.nombreTxtF.getText(),
                        this.descripcionTxtA.getText(),
                        this.categoriaCBox.getSelectionModel().getSelectedItem().getId()
                );
                Mensajes.setMensaje("Agregado correctamente","", Alert.AlertType.INFORMATION);
            } catch (Exception e) {
                Mensajes.setMensaje(e, e.getMessage());
            }
        }else{
            Mensajes.setMensaje("Datos imcompletos","", Alert.AlertType.WARNING);
        }

    }
}
