package com.upv.Controladores.Actualizaciones;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.upv.utils.Parametized;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import upv.poo.datos.plandeestudios.PlanesDeEstudio;

import java.net.URL;
import java.util.ResourceBundle;

public class ActualizarPlanDeEstudio implements Initializable, Parametized<PlanesDeEstudio.PlanDeEstudio> {
    public JFXButton cancelarBtn;
    public JFXButton actualizarBtn;
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
            this.carreraTxtF.setText(String.valueOf(this.planDeEstudio.getId_carrera()));
            this.claveTxtF.setText(this.planDeEstudio.getClave());
            this.nivelTxtF.setText(this.planDeEstudio.getNivel());
            this.actualizarBtn.setDisable(false);
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
}
