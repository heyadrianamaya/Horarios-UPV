package com.upv.Controladores.Registros;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.upv.expeciones.Mensajes;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import upv.poo.basededatos.ManagerConnection;
import upv.poo.datos.aulas.Categorias;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AgregarEquipo implements Initializable {
    private Stage prevStage;
    @FXML private JFXTextField nombreTxtF;
    @FXML private JFXTextArea descripcionTxtA;
    @FXML private JFXComboBox<Categorias.Categoria> categoriaCBox;

    public void setPrevStage(Stage prevStage) {
        this.prevStage = prevStage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Categorias.Categoria> categorias;
        try {
            categorias = FXCollections.observableArrayList(
                    ManagerConnection.getInstance().getCategorias().getCategorias()
            );
            this.categoriaCBox.setItems(categorias);
        } catch (SQLException | ClassNotFoundException e) {
            Mensajes.setMensaje(e, e.getMessage());
        }
    }

    public void salir(){
        this.prevStage.close();
    }
}
