package com.upv.Controladores.Actualizaciones;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.upv.expeciones.Mensajes;
import com.upv.utils.Parametized;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import upv.poo.basededatos.ManagerConnection;
import upv.poo.datos.carreras.Carreras;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ActualizarCarrera implements Initializable, Parametized<Carreras.Carrera> {
    public JFXButton cancelarBtn;
    public JFXButton registrarBtn;
    private Stage prevStage;

    @FXML private JFXTextField idTxtF;
    @FXML private JFXTextField nombreTxtF;
    private Carreras.Carrera carrera;

    public void setPrevStage(Stage prevStage){
        this.prevStage = prevStage;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.idTxtF.setDisable(true);
        this.registrarBtn.setOnAction((event)->agregar());
    }

    @Override
    public void setParameter(Carreras.Carrera value) {
        this.carrera = value;
        onChangeValueInfo();
    }

    @Override
    public Carreras.Carrera getParameter() {
        return carrera;
    }

    @Override
    public void onChangeValueInfo() {
        if (this.carrera!=null){
            this.idTxtF.setText(String.valueOf(this.carrera.getId()));
            this.nombreTxtF.setText(this.carrera.getNombre());
            this.registrarBtn.setDisable(false);
        }else{
            this.registrarBtn.setDisable(true);
        }
    }
    void agregar(){
//        System.out.println(this.nombreTxtF.getText());
        if (!this.nombreTxtF.getText().equals("")){
            try {
                this.carrera.setNombre(this.nombreTxtF.getText());
                ManagerConnection.getInstance().updateCarrera(this.carrera);
                Mensajes.setMensaje("Actualizado correctamente","", Alert.AlertType.INFORMATION);
            } catch (SQLException | ClassNotFoundException e) {
                Mensajes.setMensaje(e, e.getMessage());
            }
        }else{
            Mensajes.setMensaje("Dato imcompleto","", Alert.AlertType.WARNING);
        }
    }
}
