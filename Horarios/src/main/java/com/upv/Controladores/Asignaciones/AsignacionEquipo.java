package com.upv.Controladores.Asignaciones;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.upv.expeciones.Mensajes;
import com.upv.utils.Parametized;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import upv.poo.basededatos.ManagerConnection;
import upv.poo.datos.aulas.Aulas;
import upv.poo.datos.aulas.Equipos;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AsignacionEquipo implements Initializable, Parametized<Aulas.Aula> {
    private Stage prevStage;

    @FXML private JFXComboBox<Equipos.Equipo> equipoCBox;
    @FXML private JFXTextField requeridoTxtF;
    @FXML private Label cantDisponibleLbl;
    @FXML private JFXButton agregarBtn;
    @FXML private JFXButton cancelarBtn;

    private Aulas.Aula aula;
    private Equipos.Equipo equipoSelected;

    public Aulas.Aula getAula() {
        return aula;
    }

    public void setAula(Aulas.Aula aula) {
        this.aula = aula;
    }

    public void setPrevStage(Stage prevStage){
        this.prevStage = prevStage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Equipos.Equipo> equipoObservableList;
        try {
            equipoObservableList = FXCollections.observableArrayList(
                    ManagerConnection.getInstance().getEquipos().getEquipos()
            );
            this.equipoCBox.setItems(equipoObservableList);
            this.equipoCBox.getSelectionModel().selectedItemProperty()
                    .addListener((observable, oldValue, newValue) -> {
                        equipoSelected = newValue;
                        onChangeValueInfo();
                    });
            onChangeValueInfo();
        } catch (SQLException | ClassNotFoundException e) {
            Mensajes.setMensaje(e, e.getMessage());
            this.agregarBtn.setDisable(true);
        }
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
        if(this.equipoSelected!=null){
            this.cantDisponibleLbl.setText("");
            this.requeridoTxtF.setDisable(false);
            this.agregarBtn.setDisable(false);
        }else{
            this.requeridoTxtF.setDisable(true);
            this.agregarBtn.setDisable(true);
        }
    }

    public void salir(){
        this.prevStage.close();
    }
}
