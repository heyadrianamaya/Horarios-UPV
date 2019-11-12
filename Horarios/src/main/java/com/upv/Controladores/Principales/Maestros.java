package com.upv.Controladores.Principales;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import com.upv.Controladores.Actualizaciones.ActualizarMaestro;
import com.upv.Controladores.Asignaciones.AsignarCapacitacion;
import com.upv.Controladores.Extras.CompartirMaestros;
import com.upv.Controladores.Registros.AgregarMaestro;
import com.upv.expeciones.Mensajes;
import com.upv.utils.ChangeValues;
import com.upv.utils.Parametized;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import upv.poo.basededatos.ManagerConnection;
import upv.poo.datos.carreras.Carreras;
import upv.poo.datos.login.Login;
import upv.poo.datos.usuarios.Usuarios;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Maestros implements Initializable, Parametized<Login> {
    public JFXButton agregarBtn;
    public JFXButton eliminarBtn;
    public JFXButton actualizarBtn;
    public JFXButton compartirBtn;
    public JFXButton asignarBtn;
    private Stage prevStage;

    @FXML private JFXListView<Usuarios.Usuario> profesoresList;

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

    private Login login;
    private Usuarios.Usuario usuarioSelected;

    public void setPrevStage(Stage prevStage){
        this.prevStage = prevStage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void actualizar() throws IOException {
        abrirPantalla(1);
    }
    public void compartir() throws IOException {
        abrirPantalla(2);
    }
    public void asignar() throws IOException {
        abrirPantalla(3);
    }
    public void agregar() throws IOException {
        abrirPantalla(4);
    }
    public void eliminar(){

    }

    private void abrirPantalla(int pantalla) throws IOException {
        Stage stagePantalla = new Stage(); //Creacion de nuevo Escenario
        FXMLLoader getFXML;
        Parent panePantalla; //En un pane poner los datos
        Scene scenePantalla;

        switch(pantalla){
            case 1: //Asignacion maestro
                stagePantalla.setTitle("Actualizar Maestro"); //Poner su titulo
                getFXML = new FXMLLoader(getClass().getResource("/view/actualizarMaestro.fxml")); //Obtener la informacion del escenario
                panePantalla = getFXML.load(); //En un pane poner los datos
                ActualizarMaestro actualizarController = (ActualizarMaestro) getFXML.getController(); //Clase la cual
                actualizarController.setPrevStage(stagePantalla); //Asiganmos escenario del otro
                if(this.usuarioSelected!=null){
                    actualizarController.setParameter(this.usuarioSelected);
                }
                scenePantalla = new Scene(panePantalla); //Asiganar el panel a escena
                stagePantalla.setScene(scenePantalla); //Asignamos escenario
                stagePantalla.initModality(Modality.APPLICATION_MODAL);
                stagePantalla.initOwner(prevStage);
                stagePantalla.show(); //Mostramos pantalla de actualizar maestro
                break;
            case 2: //compartir maestro
                stagePantalla.setTitle("Compartir Maestro"); //Poner su titulo
                getFXML = new FXMLLoader(getClass().getResource("/view/compartirMaestro.fxml")); //Obtener la informacion del escenario
                panePantalla = getFXML.load(); //En un pane poner los datos

                CompartirMaestros compartirMaestros = getFXML.getController(); //Clase la cual
                compartirMaestros.setPrevStage(stagePantalla); //Asiganmos escenario del otro

                scenePantalla = new Scene(panePantalla); //Asiganar el panel a escena
                stagePantalla.setScene(scenePantalla); //Asignamos escenario
                stagePantalla.initModality(Modality.APPLICATION_MODAL);
                stagePantalla.initOwner(prevStage);
                stagePantalla.show(); //Mostramos pantalla de asignar materia
                break;
            case 3: //Asignar capacitacion
                stagePantalla.setTitle("Asignar capacitación a maestro"); //Poner su titulo
                getFXML = new FXMLLoader(getClass().getResource("/view/asignarCapacitacion.fxml")); //Obtener la informacion del escenario
                panePantalla = getFXML.load(); //En un pane poner los datos

                AsignarCapacitacion asignarCapacitacion = getFXML.getController(); //Clase la cual
                asignarCapacitacion.setPrevStage(stagePantalla); //Asiganmos escenario del otro

                scenePantalla = new Scene(panePantalla); //Asiganar el panel a escena
                stagePantalla.setScene(scenePantalla); //Asignamos escenario
                stagePantalla.initModality(Modality.APPLICATION_MODAL);
                stagePantalla.initOwner(prevStage);
                stagePantalla.show(); //Mostramos pantalla de asignar materia
                break;
            case 4: //Agregar maestro
                stagePantalla.setTitle("Agregar Maestro"); //Poner su titulo
                getFXML = new FXMLLoader(getClass().getResource("/view/agregarMaestro.fxml")); //Obtener la informacion del escenario
                panePantalla = getFXML.load(); //En un pane poner los datos

                AgregarMaestro agregarMaestro = getFXML.getController(); //Clase la cual
                agregarMaestro.setPrevStage(stagePantalla); //Asiganmos escenario del otro

                scenePantalla = new Scene(panePantalla); //Asiganar el panel a escena
                stagePantalla.setScene(scenePantalla); //Asignamos escenario
                stagePantalla.initModality(Modality.APPLICATION_MODAL);
                stagePantalla.initOwner(prevStage);
                stagePantalla.show(); //Mostramos pantalla de de agregar maestro
                break;
        }
    }

    @Override
    public void onChangeValueInfo() {
        if(this.usuarioSelected!=null){
            try {
                this.nombreLbl.setText(this.usuarioSelected.getNombre());
                this.carreraLbl.setText(String.valueOf(
                        ManagerConnection.getInstance().getCarreras().getCarrera(this.usuarioSelected.getId_carrera())
                ));
                this.contrasenaLbl.setText(this.usuarioSelected.getPassword());
                this.imrLbl.setText(String.valueOf(this.usuarioSelected.getIMR()));
                this.contratoLbl.setText(this.usuarioSelected.getContrato());
                this.nivelLbl.setText(this.usuarioSelected.getNivel());
                this.telefonoLbl.setText(this.usuarioSelected.getTelefono());
                this.usuarioLbl.setText(this.usuarioSelected.getClave());
                this.asignarBtn.setVisible(true);
                this.eliminarBtn.setVisible(true);
                this.compartirBtn.setVisible(true);
                this.actualizarBtn.setVisible(true);
            } catch (SQLException | ClassNotFoundException e) {
                Mensajes.setMensaje(e, e.getMessage());
                disableAllButtons();
            }
        }else{
            disableAllButtons();
        }
    }

    private void disableAllButtons() {
        this.nombreLbl.setText("");
        this.carreraLbl.setText("");
        this.contrasenaLbl.setText("");
        this.imrLbl.setText("");
        this.contratoLbl.setText("");
        this.nivelLbl.setText("");
        this.telefonoLbl.setText("");
        this.usuarioLbl.setText("");
        this.asignarBtn.setVisible(false);
        this.eliminarBtn.setVisible(false);
        this.compartirBtn.setVisible(false);
        this.actualizarBtn.setVisible(false);
    }

    @Override
    public void setParameter(Login value) {
        this.login = value;
        try {
            Carreras.Carrera carrera = ManagerConnection.getInstance().getCarreras().getCarrera(this.login.getIdCarrera());
            ObservableList<Usuarios.Usuario> items= FXCollections.observableList(ManagerConnection.getInstance().getUsuarios(carrera).getUsuarios());
            this.profesoresList.setItems(items);
            this.profesoresList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                usuarioSelected = newValue;
                onChangeValueInfo();
            });
            onChangeValueInfo();
        } catch (SQLException | ClassNotFoundException e) {
            Mensajes.setMensaje(e, e.getMessage());
            this.asignarBtn.setDisable(true);
            this.eliminarBtn.setDisable(true);
            this.compartirBtn.setDisable(true);
            this.actualizarBtn.setDisable(true);
            this.agregarBtn.setDisable(true);
        }
    }

    @Override
    public Login getParameter() {
        return this.login;
    }
}
