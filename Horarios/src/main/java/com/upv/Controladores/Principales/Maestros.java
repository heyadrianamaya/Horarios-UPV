package com.upv.Controladores.Principales;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Maestros {
    @FXML private JFXListView profesoresList;

    //Datos profesor
    @FXML private Label nombreLbl;
    @FXML private Label telefonoLbl;
    @FXML private Label nivelLbl;
    @FXML private Label carreraLbl;
    @FXML private Label contratoLbl;
    @FXML private Label imrLbl;
    //--------------------------------------
    @FXML private JFXTextField nombreTxtF;
    @FXML private JFXTextField telefonoTxtF;
    @FXML private JFXComboBox nivelCBox;
    @FXML private JFXComboBox carreraCBox;
    @FXML private JFXComboBox contratoCBox;
    @FXML private JFXTextField imrTxtF;

    //Datos de acceso
    @FXML private JFXTextField usuarioTxtF;
    @FXML private JFXTextField contrasenaTxtF;
    @FXML private Label usuarioLbl;
    @FXML private Label contrasenaLbl;
}
