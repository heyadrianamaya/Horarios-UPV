package com.upv.Controladores.Registros;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AgregarMaestro implements Initializable {
    private Stage prevStage;

    @FXML private JFXTextField nombreTxtF;
    @FXML private JFXTextField telefonoTxtF;
    @FXML private JFXComboBox nivelCBox;
    @FXML private JFXComboBox carreraCBox;
    @FXML private JFXComboBox contratoCBox;
    @FXML private JFXTextField imrTxtF;
    @FXML private JFXTextField usuarioTxtF;
    @FXML private JFXTextField contrasenaTxtF;

    public void setPrevStage(Stage prevStage){
        this.prevStage = prevStage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void salir(){
        this.prevStage.close();
    }
}
