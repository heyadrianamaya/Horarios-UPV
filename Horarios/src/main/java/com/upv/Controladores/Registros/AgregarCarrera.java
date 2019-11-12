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

public class AgregarCarrera implements Initializable {
    public JFXButton cancelarBtn;
    public JFXButton agregarBtn;
    private Stage prevStage;
    @FXML private JFXTextField idCarreraTxtF;
    @FXML private JFXTextField nombreCarreraTxtF;

    public void setPrevStage(Stage prevStage) {
        this.prevStage = prevStage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.agregarBtn.setOnAction((event)->agregar());
    }
    void agregar(){
        if (!this.idCarreraTxtF.getText().equals("") &&
        !this.nombreCarreraTxtF.getText().equals("")){
            try {
                ManagerConnection.getInstance().insertCarrera(
                        Integer.parseInt(this.idCarreraTxtF.getText()),
                        this.nombreCarreraTxtF.getText()
                );
                Mensajes.setMensaje("Agregado correcto","", Alert.AlertType.INFORMATION);
                this.nombreCarreraTxtF.setText("");
                this.idCarreraTxtF.setText("");
            } catch (Exception e) {
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
