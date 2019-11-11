package com.upv.Controladores.Principales;

import com.jfoenix.controls.JFXComboBox;
import com.upv.expeciones.Mensajes;
import com.upv.utils.Parametized;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import upv.poo.basededatos.ManagerConnection;
import upv.poo.datos.carreras.Carreras;
import upv.poo.datos.login.Login;
import upv.poo.datos.usuarios.Usuarios;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Disponibilidad implements Initializable, Parametized<Login> {
    @FXML private JFXComboBox<Usuarios.Usuario> profesoresCBox;
    @FXML private JFXComboBox turnoCBox;
    @FXML private TableView disponiblesTable;
    private Usuarios usuarios;
    private Login usuario;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
    public void setValuesProfesoresCBox(Carreras.Carrera c) throws SQLException, ClassNotFoundException {
        this.usuarios = ManagerConnection.getInstance().getUsuarios(c);
        ObservableList<Usuarios.Usuario> usuarios = FXCollections.observableArrayList(this.usuarios.getUsuarios());
        this.profesoresCBox.setItems(usuarios);
    }

    @Override
    public void setParameter(Login value) {
        this.usuario = value;
        onChangeValueInfo();
    }

    @Override
    public Login getParameter() {
        return this.usuario;
    }

    @Override
    public void onChangeValueInfo() {
        try {
            this.setValuesProfesoresCBox(ManagerConnection.getInstance().getCarreras().getCarrera(this.usuario.getIdCarrera()));
        } catch (SQLException | ClassNotFoundException e) {
            Mensajes.setMensaje(e, e.getMessage());
        }
    }
}
