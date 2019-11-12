package com.upv.Controladores.Extras;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.upv.utils.Parametized;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import upv.poo.datos.usuarios.Usuarios;

import java.net.URL;
import java.util.ResourceBundle;

public class CompartirMaestros implements Initializable, Parametized<Usuarios.Usuario> {
    public JFXButton compartirBtn;
    public JFXButton cancelarBtn;
    private Stage prevStage;
    @FXML private JFXCheckBox mecaCBtn;
    @FXML private JFXCheckBox itiCBtn;
    @FXML private JFXCheckBox manuCBtn;
    @FXML private JFXCheckBox pymesCBtn;
    private Usuarios.Usuario usuarioSelected;

    public void setPrevStage(Stage prevStage){
        this.prevStage = prevStage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void salir(){
        this.prevStage.close();
}
    @Override
    public void setParameter(Usuarios.Usuario value) {
        this.usuarioSelected = value;
        onChangeValueInfo();
    }

    @Override
    public Usuarios.Usuario getParameter() {
        return this.usuarioSelected;
    }

    @Override
    public void onChangeValueInfo() {
        if(this.usuarioSelected!=null){
            if (this.usuarioSelected.getId_carrera() == 1){
                this.itiCBtn.setDisable(true);
                this.itiCBtn.setSelected(true);
            }else if (this.usuarioSelected.getId_carrera() == 2){
                this.mecaCBtn.setDisable(true);
                this.mecaCBtn.setSelected(true);
            }else if(this.usuarioSelected.getId_carrera()==3){
                this.pymesCBtn.setDisable(true);
                this.pymesCBtn.setSelected(true);
            }else if(this.usuarioSelected.getId_carrera() == 4){
                this.manuCBtn.setDisable(true);
                this.manuCBtn.setSelected(true);
            }
            this.compartirBtn.setDisable(false);
        }else{
            this.compartirBtn.setDisable(true);
        }
    }
}
