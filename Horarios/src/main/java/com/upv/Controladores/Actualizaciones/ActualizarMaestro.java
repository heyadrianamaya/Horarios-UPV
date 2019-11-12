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
import javafx.stage.Stage;
import upv.poo.basededatos.ManagerConnection;
import upv.poo.datos.carreras.Carreras;
import upv.poo.datos.usuarios.Usuarios;
import upv.poo.utils.TipoUsuario;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ActualizarMaestro implements Initializable, Parametized<Usuarios.Usuario> {
    private Stage prevStage;
    public JFXTextField usuarioTxt;
    public JFXTextField contrasenaTxt;
    public JFXButton actualizarBtn;
    public JFXButton cancelarBtn;
    @FXML private JFXTextField nombreTxt;
    @FXML private JFXTextField telefonoTxt;
    @FXML private JFXComboBox<String> nivelCBox;
    @FXML private JFXComboBox<Carreras.Carrera> carreraCBox;
    @FXML private JFXComboBox<String> contratoCBox;
    @FXML private JFXTextField imrTxt;
    private Usuarios.Usuario usuario;

    public void setPrevStage(Stage prevStage){
        this.prevStage = prevStage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Carreras.Carrera> carreras;
        try {
            carreras = FXCollections.observableArrayList(
                    ManagerConnection.getInstance().getCarreras().getCarreras()
            );
            this.carreraCBox.setItems(carreras);
            this.actualizarBtn.setOnAction((actionEvent)->agregar());
            this.usuarioTxt.setDisable(true);
            this.usuarioTxt.setEditable(false);
        } catch (SQLException | ClassNotFoundException e) {
            Mensajes.setMensaje(e, e.getMessage());
        }
    }

    @Override
    public void setParameter(Usuarios.Usuario value) {
        this.usuario = value;
        this.onChangeValueInfo();
    }

    @Override
    public Usuarios.Usuario getParameter() {
        return this.usuario;
    }

    @Override
    public void onChangeValueInfo() {
        if (this.usuario!=null){
            try {
            this.usuarioTxt.setText(this.usuario.getClave());
            this.contrasenaTxt.setText(this.usuario.getPassword());
            this.telefonoTxt.setText(this.usuario.getTelefono());
            this.imrTxt.setText(String.valueOf(this.usuario.getIMR()));
            this.nombreTxt.setText(this.usuario.getNombre());
            this.actualizarBtn.setDisable(false);
            this.nivelCBox.getItems().addAll("-","Dr.","M.C","Lic.");
            this.contratoCBox.getItems().addAll("-","PA","PTC");
            this.contratoCBox.setValue(this.usuario.getContrato());
            this.nivelCBox.setValue(this.usuario.getNivel());
            this.carreraCBox.setValue(ManagerConnection.getInstance().getCarreras().getCarrera(this.usuario.getId_carrera()));
            } catch (SQLException | ClassNotFoundException e) {
                Mensajes.setMensaje(e, e.getMessage());
                this.actualizarBtn.setDisable(true);
            }
        }else{
            this.actualizarBtn.setDisable(true);
        }
    }
    void agregar(){
        if (!this.nombreTxt.getText().equals("") && !this.telefonoTxt.getText().equals("")
                && !this.nivelCBox.getSelectionModel().getSelectedItem().equals("") &&
                this.carreraCBox.getSelectionModel().getSelectedItem()!=null &&
                this.contratoCBox.getSelectionModel().getSelectedItem()!=null &&
                !this.imrTxt.getText().equals("") && !this.usuarioTxt.getText().equals("")
                && !this.contrasenaTxt.getText().equals("")){
            try {
                this.usuario.setClave(this.usuarioTxt.getText());
                this.usuario.setPassword(this.contrasenaTxt.getText());
                this.usuario.setTipo(TipoUsuario.PROFESOR);
                this.usuario.setId_carrera(this.carreraCBox.getSelectionModel().getSelectedItem().getId());
                this.usuario.setNombre(this.nombreTxt.getText());
                this.usuario.setNivel(this.nivelCBox.getSelectionModel().getSelectedItem());
                this.usuario.setNivel(this.nivelCBox.getSelectionModel().getSelectedItem());
                this.usuario.setContrato(this.contratoCBox.getSelectionModel().getSelectedItem());
                this.usuario.setIMR(Integer.parseInt(this.imrTxt.getText()));
                ManagerConnection.getInstance().updateUsuario(
                        this.usuario
                );
                Mensajes.setMensaje("Actualizado correctamente","Actualizado", Alert.AlertType.INFORMATION);
            } catch (SQLException | ClassNotFoundException e) {
                Mensajes.setMensaje(e, e.getMessage());
            }
        }else{
            Mensajes.setMensaje("Datos imcompletos","", Alert.AlertType.WARNING);
        }
    }
}
