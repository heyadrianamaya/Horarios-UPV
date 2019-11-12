package com.upv.Controladores.Principales;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.upv.Controladores.Registros.AgregarGrupo;
import com.upv.expeciones.Mensajes;
import com.upv.utils.Parametized;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import upv.poo.basededatos.ManagerConnection;
import upv.poo.datos.login.Login;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static javafx.collections.FXCollections.*;

public class Grupos implements Initializable, Parametized<Login> {
    public JFXButton eliminarBtn;
    public JFXButton agregarBtn;
    private Stage prevStage;

    @FXML private TableView horarioTable;
    @FXML private JFXComboBox<String> turnoCBox;
    @FXML private JFXComboBox<upv.poo.datos.grupos.Grupos.Grupo> grupoCBox;
    private Login login;
    private upv.poo.datos.grupos.Grupos.Grupo grupoSelected;

    public void setPrevStage(Stage prevStage){
        this.prevStage = prevStage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.grupoCBox.getSelectionModel().selectedItemProperty().addListener(
                ((observableValue, oldValue, newValue) -> {
                    grupoSelected = newValue;
                    onChangeSelectedGrupo();
                })
        );
        this.turnoCBox.getSelectionModel().selectedItemProperty().addListener(
                ((observableValue, oldValue, newValue) -> {
                    onChangeSelectedGrupo();
                })
        );
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
            ObservableList<upv.poo.datos.grupos.Grupos.Grupo> grupos;
            try {
                grupos = observableArrayList(ManagerConnection.getInstance().getGrupos(this.login).getGrupos());
                this.grupoCBox.setItems(grupos);
                this.turnoCBox.getItems().addAll("Matutino","Vespertino");
            } catch (SQLException | ClassNotFoundException e) {
                Mensajes.setMensaje(e, e.getMessage());
            }
            this.turnoCBox.setDisable(false);
            this.grupoCBox.setDisable(false);
        }else{
            this.turnoCBox.setDisable(true);
            this.grupoCBox.setDisable(true);
        }
    }

    private void onChangeSelectedGrupo(){
        if (this.grupoSelected!=null){
            if (this.turnoCBox.getSelectionModel().getSelectedItem()!=null){
                try {
                    if (this.turnoCBox.getSelectionModel().getSelectedItem().equals("Matutino")){
                        System.out.println(this.grupoSelected);
                        //System.out.println(ManagerConnection.getInstance().getMaterias(this.grupoSelected, true));
                    }else{
                        System.out.println(ManagerConnection.getInstance().getMaterias(this.grupoSelected, false));
                    }
                } catch (SQLException | ClassNotFoundException e) {
                    Mensajes.setMensaje(e, e.getMessage());
                }
            }
        }else{

        }
    }

    private void abrirPantalla(int pantalla) throws IOException {
        Stage stagePantalla = new Stage(); //Creacion de nuevo Escenario
        FXMLLoader getFXML;
        Pane panePantalla; //En un pane poner los datos
        Scene scenePantalla;

        switch(pantalla){
            case 1:
                stagePantalla.setTitle("Agregar grupo"); //Poner su titulo
                getFXML = new FXMLLoader(getClass().getResource("/view/agregarGrupo.fxml")); //Obtener la informacion del escenario
                panePantalla = getFXML.load(); //En un pane poner los datos

                AgregarGrupo agregarGrupo = getFXML.getController(); //Clase la cual
                agregarGrupo.setPrevStage(stagePantalla); //Asiganmos escenario del otro

                scenePantalla = new Scene(panePantalla); //Asiganar el panel a escena
                stagePantalla.setScene(scenePantalla); //Asignamos escenario
                stagePantalla.initModality(Modality.APPLICATION_MODAL);
                stagePantalla.initOwner(prevStage);
                stagePantalla.show(); //Mostramos pantalla de huella
                break;
        }
    }
    public void agregar() throws IOException {
        abrirPantalla(1);
    }
}
