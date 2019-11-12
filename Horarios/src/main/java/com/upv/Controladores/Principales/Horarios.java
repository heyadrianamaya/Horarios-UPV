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
import upv.poo.datos.materias.Materias;
import upv.poo.datos.usuarios.Usuarios;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static javafx.collections.FXCollections.observableArrayList;

public class Horarios implements Initializable, Parametized<Login> {
    @FXML private JFXComboBox<Materias.Materia> materiaCBox;
    @FXML private Label maxMatLbl;
    @FXML private Label dispMatLbl;
    @FXML private JFXComboBox<Grupos.Grupo> grupoCBox;
    @FXML private JFXComboBox<String> turnoCBox;
    @FXML private JFXComboBox<Usuarios.Usuario> profesorCBox;
    @FXML private Label maxProfTxtF;
    @FXML private Label dispProfTxtF;
    @FXML private Label reprobacionLbl;
    @FXML private TableView horariosTable;
    private Login login;
    private upv.poo.datos.grupos.Grupos.Grupo grupoSelected;
    private Materias.Materia materiaSelected;
    private Usuarios.Usuario usuarioSelected;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.grupoCBox.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    grupoSelected = newValue;
                    onChangeValueMaterias();
                });
        this.materiaCBox.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    materiaSelected = newValue;
                    onChangeValueUsuarios();
                });
        this.profesorCBox.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    usuarioSelected = newValue;
                    onChangeValueLabelUsuario();
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
                this.turnoCBox.getItems().addAll("Matutino","Vespertino");
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
    private void onChangeValueMaterias(){
        this.materiaCBox.getItems().clear();
        if (this.grupoSelected!=null){
            ObservableList<Materias.Materia> materias;
//            System.out.println(this.grupoSelected);
            try {
                materias = observableArrayList(ManagerConnection.getInstance().getMaterias(this.grupoSelected).getMaterias());
                this.materiaCBox.setItems(materias);
            } catch (SQLException | ClassNotFoundException e) {
                Mensajes.setMensaje(e, e.getMessage());
            }
        }else{
            this.materiaSelected = null;
        }
    }
    private void onChangeValueUsuarios(){
        this.profesorCBox.getItems().clear();
        if (this.materiaSelected!=null){
            ObservableList<Usuarios.Usuario> usuarios;
//            System.out.println(this.materiaSelected);
            try {
                usuarios = observableArrayList(ManagerConnection.getInstance().getUsuarios(this.materiaSelected).getUsuarios());
                this.profesorCBox.setItems(usuarios);
            } catch (SQLException | ClassNotFoundException e) {
                Mensajes.setMensaje(e, e.getMessage());
            }
        }else{
            this.usuarioSelected = null;
        }
    }
    private void onChangeValueLabelUsuario(){
        if (this.usuarioSelected!=null){
            try{
                this.reprobacionLbl.setText(String.valueOf(this.usuarioSelected.getIMR()));
                if (this.materiaSelected!=null){
                    int[] diasMax = ManagerConnection.getInstance().getHorasSemanalesMaximas(this.usuarioSelected);
                    int[] diasTomados = ManagerConnection.getInstance().getHorasSemanalesTomadas(this.materiaSelected, this.usuarioSelected);
                    int totalDiasMax = 0 ;
                    int totalDiasDispo = 0;
                    StringBuilder textMax = new StringBuilder();
                    StringBuilder textDispo = new StringBuilder();
                    for (int i = 0; i < 5; i++) {
                        textMax.append(diasMax[i]).append(" ");
                        totalDiasMax+=diasMax[i];
                        totalDiasDispo+=(diasMax[i] - diasTomados[i]);
                        textDispo.append((diasMax[i] - diasTomados[i])).append(" ");
                    }
                    this.maxMatLbl.setText(textMax.toString());
                    this.dispMatLbl.setText(textDispo.toString());
                    this.maxProfTxtF.setText(String.valueOf(totalDiasMax));
                    this.dispProfTxtF.setText(String.valueOf(totalDiasDispo));
                }
            }catch (Exception e){
                Mensajes.setMensaje(e, e.getMessage());
            }
        }else{
            this.reprobacionLbl.setText("");
            this.maxMatLbl.setText("");
            this.dispMatLbl.setText("");
            this.maxProfTxtF.setText("");
            this.dispProfTxtF.setText("");
        }
    }
}
