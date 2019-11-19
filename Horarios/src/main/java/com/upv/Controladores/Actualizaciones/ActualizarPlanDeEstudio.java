package com.upv.Controladores.Actualizaciones;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.upv.expeciones.Mensajes;
import com.upv.utils.Parametized;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import upv.poo.basededatos.ManagerConnection;
import upv.poo.datos.carreras.Carreras;
import upv.poo.datos.plandeestudios.PlanesDeEstudio;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ActualizarPlanDeEstudio implements Initializable, Parametized<PlanesDeEstudio.PlanDeEstudio> {
    public JFXButton actualizarBtn;
    public JFXComboBox<Carreras.Carrera> carreraComboBox;
    private Stage prevStage;
    @FXML private JFXTextField claveTxtF;
    @FXML private JFXTextField nombreTxtF;
    @FXML private JFXTextField nivelTxtF;
    @FXML private JFXTextField carreraTxtF;
    private PlanesDeEstudio.PlanDeEstudio planDeEstudio;

    public void setPrevStage(Stage prevStage) {
        this.prevStage = prevStage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            ObservableList<Carreras.Carrera> carreraObservableList;
            carreraObservableList = FXCollections.observableArrayList(
                    ManagerConnection.getInstance().getCarreras().getCarreras()
            );
            this.carreraComboBox.setItems(carreraObservableList);
            this.actualizarBtn.setOnAction((event)->actualizar());
        } catch (SQLException | ClassNotFoundException e) {
            Mensajes.setMensaje(e, e.getMessage());
        }
    }

    @Override
    public void setParameter(PlanesDeEstudio.PlanDeEstudio value) {
        this.planDeEstudio = value;
        onChangeValueInfo();
    }

    @Override
    public PlanesDeEstudio.PlanDeEstudio getParameter() {
        return this.planDeEstudio;
    }

    @Override
    public void onChangeValueInfo() {
        if (this.planDeEstudio!=null){
            this.nombreTxtF.setText(this.planDeEstudio.getNombre());
            //CAMBIAR POR UN COMBOBOX
            try {
                ObservableList<Carreras.Carrera> carreraObservableList;
                carreraObservableList = FXCollections.observableArrayList(
                        ManagerConnection.getInstance().getCarreras().getCarreras()
                );
                this.carreraComboBox.setItems(carreraObservableList);
                this.carreraComboBox.setValue(ManagerConnection.getInstance().getCarreras().getCarrera(this.planDeEstudio.getId_carrera()));
                this.claveTxtF.setText(this.planDeEstudio.getClave());
                this.nivelTxtF.setText(this.planDeEstudio.getNivel());
                this.actualizarBtn.setDisable(false);
            } catch (SQLException | ClassNotFoundException e) {
                Mensajes.setMensaje(e, e.getMessage());
            }
        }else{
            this.actualizarBtn.setDisable(true);
            this.nombreTxtF.setText("");
            //CAMBIAR POR UN COMBOBOX
            this.carreraTxtF.setText("");
            this.claveTxtF.setText("");
            this.nivelTxtF.setText("");
        }
    }

    public Stage getPrevStage() {
        return prevStage;
    }

    public void salir(){
        this.prevStage.close();
    }

    public void actualizar(){
        if (!this.nombreTxtF.getText().equals("") &&
                this.carreraComboBox.getSelectionModel().getSelectedItem()!=null
                && !this.nivelTxtF.getText().equals("")
                && !this.claveTxtF.getText().equals("")){
            try {
                this.planDeEstudio.setClave(this.claveTxtF.getText());
                this.planDeEstudio.setNivel(this.nivelTxtF.getText());
                this.planDeEstudio.setNombre(this.nombreTxtF.getText());
                this.planDeEstudio.setId_carrera(this.carreraComboBox.getSelectionModel().getSelectedItem().getId());
                ManagerConnection.getInstance().updatePlanEstudio(this.planDeEstudio);
                Mensajes.setMensaje("Actualizado exitosamente","", Alert.AlertType.INFORMATION);
            } catch (SQLException | ClassNotFoundException e) {
                Mensajes.setMensaje(e, e.getMessage());
            }
        }else{
            Mensajes.setMensaje("Faltan datos","", Alert.AlertType.WARNING);
        }
    }
}
