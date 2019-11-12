package com.upv.Controladores.Actualizaciones;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.upv.utils.Parametized;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import upv.poo.datos.aulas.Aulas;

import java.net.URL;
import java.util.ResourceBundle;

public class ActualizarAula implements Initializable , Parametized<Aulas.Aula> {
    private Stage prevStage;
    @FXML private JFXTextField claveTxtF;
    @FXML private JFXTextField nombreTxtF;
    @FXML private JFXComboBox<String> tipoCBox;
    @FXML private JFXTextField capacidadTxtF;
    private Aulas.Aula aula;
    public void setPrevStage(Stage prevStage) {
        this.prevStage = prevStage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @Override
    public void setParameter(Aulas.Aula value) {
        this.aula = value;
        this.onChangeValueInfo();
    }

    @Override
    public Aulas.Aula getParameter() {
        return this.aula;
    }

    @Override
    public void onChangeValueInfo() {
        this.tipoCBox.getItems().addAll("Aula","Laboratorio");
        this.claveTxtF.setText(this.aula.getId());
        this.capacidadTxtF.setText(String.valueOf(this.aula.getCapacidad()));
        this.nombreTxtF.setText(this.aula.getNombre());
        if (this.aula.getTipo().equals("Aula")){
            this.tipoCBox.getSelectionModel().select(0);
        }else{
            this.tipoCBox.getSelectionModel().select(1);
        }
    }

    public void salir(){
        this.prevStage.close();
    }
}
