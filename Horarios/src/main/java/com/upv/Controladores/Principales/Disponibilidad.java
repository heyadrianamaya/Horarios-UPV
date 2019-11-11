package com.upv.Controladores.Principales;

import com.jfoenix.controls.JFXComboBox;
import com.upv.expeciones.Mensajes;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import upv.poo.basededatos.ManagerConnection;
import upv.poo.datos.carreras.Carreras;
import upv.poo.datos.usuarios.Usuarios;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Disponibilidad implements Initializable {
    @FXML private JFXComboBox<Usuarios.Usuario> profesoresCBox;
    @FXML private JFXComboBox turnoCBox;
    @FXML private TableView disponiblesTable;
    private Usuarios usuarios;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Inicia ventana");
        this.usuarios = new Usuarios();
        try {
            setValuesProfesoresCBox(ManagerConnection.getInstance().getCarreras().getCarrera(1));
        } catch (SQLException | ClassNotFoundException e) {
            Mensajes.setMensaje(e, e.getMessage());
        }
        ObservableList<Usuarios.Usuario> usuarios = FXCollections.observableArrayList(this.usuarios.getUsuarios());
        this.profesoresCBox.setItems(usuarios);
    }
    public void setValuesProfesoresCBox(Carreras.Carrera c) throws SQLException, ClassNotFoundException {
        this.usuarios = ManagerConnection.getInstance().getUsuarios(c);

    }
}
