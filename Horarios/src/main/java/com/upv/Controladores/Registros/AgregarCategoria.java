package com.upv.Controladores.Registros;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.upv.expeciones.Mensajes;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import upv.poo.basededatos.ManagerConnection;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AgregarCategoria implements Initializable {
    public JFXButton cancelarBtn;
    public JFXButton agregarBtn;
    private Stage prevStage;
    @FXML private JFXTextField nombreTxtF;
    @FXML private JFXTextArea descripcionTxtA;

    public void setPrevStage(Stage prevStage) {
        this.prevStage = prevStage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.agregarBtn.setOnAction((event -> agregar()));
    }

    public void salir(){
        this.prevStage.close();
    }
    private void agregar(){
        if (!this.nombreTxtF.getText().equals("") &&
        !this.descripcionTxtA.getText().equals("")){
            try {
                ManagerConnection.getInstance().insertCategoria(
                        this.nombreTxtF.getText(),
                        this.descripcionTxtA.getText()
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
