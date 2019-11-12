package com.upv.Controladores.Principales;

import com.jfoenix.controls.JFXComboBox;
import com.upv.expeciones.Mensajes;
import com.upv.utils.Parametized;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import upv.poo.basededatos.ManagerConnection;
import upv.poo.datos.grupos.Grupos;
import upv.poo.datos.login.Login;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static javafx.collections.FXCollections.observableArrayList;

public class Horarios implements Initializable, Parametized<Login> {
    @FXML private JFXComboBox materiaCBox;
    @FXML private Label maxMatLbl;
    @FXML private Label dispMatLbl;
    @FXML private JFXComboBox<Grupos.Grupo> grupoCBox;
    @FXML private JFXComboBox turnoCBox;
    @FXML private JFXComboBox profesorCBox;
    @FXML private Label maxProfTxtF;
    @FXML private Label dispProfTxtF;
    @FXML private Label reprobacionLbl;
    @FXML private TableView horariosTable;
    private Login login;
    private upv.poo.datos.grupos.Grupos.Grupo grupoSelected;
//    private

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.grupoCBox.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    grupoSelected = newValue;
                    onChangeValueInfo();
                });
    }

    @Override
    public void setParameter(Login value) {
        this.login = value;
        onChangeValueInfo();
    }

    @Override
    public Login getParameter() {
        return this.login;
    }

    @Override
    public void onChangeValueInfo() {
        if (this.login!=null){
            ObservableList<Grupos.Grupo> grupos;
            try {
                grupos = observableArrayList(ManagerConnection.getInstance().getGrupos(this.login).getGrupos());
                this.grupoCBox.setItems(grupos);
            } catch (SQLException | ClassNotFoundException e) {
                Mensajes.setMensaje(e, e.getMessage());
            }
        }else{
            this.grupoCBox.setDisable(true);
            this.materiaCBox.setDisable(true);
            this.profesorCBox.setDisable(true);
            this.turnoCBox.setDisable(true);
        }
    }
}
