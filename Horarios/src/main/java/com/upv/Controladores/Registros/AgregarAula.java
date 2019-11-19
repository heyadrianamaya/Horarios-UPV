package com.upv.Controladores.Registros;

import com.jfoenix.controls.JFXButton;
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

public class AgregarAula implements Initializable {
    public JFXButton agregarBtn;
    public JFXButton cancelarBtn;
    private Stage prevStage;

    @FXML private JFXTextField claveTxtF;
    @FXML private JFXTextField nombreTxtF;
    @FXML private JFXTextField tipoTxtF;
    @FXML private JFXTextField capacidadTxtF;

    public void setPrevStage(Stage prevStage) {
        this.prevStage = prevStage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.agregarBtn.setOnAction((event)->agregar());
    }

    public void salir(){
        this.prevStage.close();
    }

    private void agregar(){
        if (!this.nombreTxtF.getText().equals("") && !this.claveTxtF.getText().equals("") &&
        !this.capacidadTxtF.getText().equals("") && !this.tipoTxtF.getText().equals("")){
            try {
                ManagerConnection.getInstance().insertAula(
                        this.claveTxtF.getText(), this.nombreTxtF.getText(),
                        this.tipoTxtF.getText(), Integer.parseInt(this.capacidadTxtF.getText())
                );
                Mensajes.setMensaje("Agregado exitosamente","", Alert.AlertType.INFORMATION);
            } catch (Exception e) {
                Mensajes.setMensaje(e, e.getMessage());
            }
        }
    }
}
