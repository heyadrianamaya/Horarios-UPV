package com.upv.Controladores.Principales;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.upv.Controladores.Actualizaciones.ActualizarAula;
import com.upv.Controladores.Asignaciones.AsignacionEquipo;
import com.upv.Controladores.Registros.AgregarAula;
import com.upv.Controladores.Registros.AgregarCategoria;
import com.upv.Controladores.Registros.AgregarEquipo;
import com.upv.expeciones.Mensajes;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import upv.poo.basededatos.ManagerConnection;
import upv.poo.datos.aulas.Categorias;
import upv.poo.datos.aulas.Equipos;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Aulas implements Initializable{
    private Stage prevStage;

    @FXML private Label claveLbl;
    @FXML private Label nombreLbl;
    @FXML private Label tipoLbl;
    @FXML private Label capacidadLbl;
    @FXML private JFXButton asignarEquipoBtn;
    @FXML private JFXButton actualizarBtn;
    @FXML private JFXListView<Equipos.Equipo> equiposList;
    @FXML private JFXButton agregarEquipoBtn;
    @FXML private JFXListView<Categorias.Categoria> categoriasList;
    @FXML private JFXButton agregarCategoriaBtn;
    @FXML private JFXButton agregarBtn;
    @FXML private JFXListView<upv.poo.datos.aulas.Aulas.Aula> planesList;
    private upv.poo.datos.aulas.Aulas.Aula aula;

    public void setPrevStage(Stage prevStage){
        this.prevStage = prevStage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            ObservableList<upv.poo.datos.aulas.Aulas.Aula> aulas;
            ObservableList<Equipos.Equipo> equipos;
            ObservableList<Categorias.Categoria>categorias;
            aulas = FXCollections.observableArrayList(ManagerConnection.getInstance().getAulas().getAulas());
            equipos = FXCollections.observableArrayList(ManagerConnection.getInstance().getEquipos().getEquipos());
            categorias = FXCollections.observableArrayList(ManagerConnection.getInstance().getCategorias().getCategorias());
            this.planesList.setItems(aulas);
            this.categoriasList.setItems(categorias);
            this.equiposList.setItems(equipos);
            this.planesList.getSelectionModel().selectedItemProperty()
                    .addListener((observable, oldValue, newValue) -> {
                        aula = newValue;
                        onChangeValuesInfo();
                    });
            onChangeValuesInfo();
        } catch (SQLException | ClassNotFoundException e) {
            Mensajes.setMensaje(e, e.getMessage());
            this.asignarEquipoBtn.setDisable(true);
            this.actualizarBtn.setDisable(true);
            this.agregarBtn.setDisable(true);
            this.agregarCategoriaBtn.setDisable(true);
            this.agregarEquipoBtn.setDisable(true);
        }
    }

    public void actualizarAula() throws IOException {
        abrirPantalla(1);
    }
    public void asignarEquipo() throws IOException {
        abrirPantalla(2);
    }
    public void agregarCategoria() throws IOException {
        abrirPantalla(3);
    }
    public void agregarEquipo() throws IOException {
        abrirPantalla(4);
    }
    public void agregarAula() throws IOException {
        abrirPantalla(5);
    }

    private void abrirPantalla(int pantalla) throws IOException {
        Stage stagePantalla = new Stage(); //Creacion de nuevo Escenario
        FXMLLoader getFXML;
        Pane panePantalla; //En un pane poner los datos
        Scene scenePantalla;

        switch(pantalla){
            case 1: //Actualizar aula
                stagePantalla.setTitle("Actualizar Aula"); //Poner su titulo
                getFXML = new FXMLLoader(getClass().getResource("/view/actualizarAula.fxml")); //Obtener la informacion del escenario
                panePantalla = getFXML.load(); //En un pane poner los datos

                ActualizarAula actualizarController = getFXML.getController(); //Clase la cual
                actualizarController.setPrevStage(prevStage); //Asiganmos escenario del otro
                if (this.aula!=null){
                    actualizarController.setParameter(aula);
                }

                scenePantalla = new Scene(panePantalla); //Asiganar el panel a escena
                stagePantalla.setScene(scenePantalla); //Asignamos escenario
                stagePantalla.initModality(Modality.APPLICATION_MODAL);
                stagePantalla.initOwner(prevStage);
                stagePantalla.show(); //Mostramos pantalla de actualizar aula
                break;
            case 2: //Asignar equipo a aula
                stagePantalla.setTitle("Asignar equipo a aula"); //Poner su titulo
                getFXML = new FXMLLoader(getClass().getResource("/view/asignarEquipo.fxml")); //Obtener la informacion del escenario
                panePantalla = getFXML.load(); //En un pane poner los datos

                AsignacionEquipo asignacionEquipo = getFXML.getController(); //Clase la cual
                asignacionEquipo.setPrevStage(prevStage); //Asiganmos escenario del otro

                scenePantalla = new Scene(panePantalla); //Asiganar el panel a escena
                stagePantalla.setScene(scenePantalla); //Asignamos escenario
                stagePantalla.initModality(Modality.APPLICATION_MODAL);
                stagePantalla.initOwner(prevStage);
                stagePantalla.show(); //Mostramos pantalla de asignar equipo
                break;
            case 3: //Agregar categoria
                stagePantalla.setTitle("Agregar Categoria"); //Poner su titulo
                getFXML = new FXMLLoader(getClass().getResource("/view/agregarCategoria.fxml")); //Obtener la informacion del escenario
                panePantalla = getFXML.load(); //En un pane poner los datos

                AgregarCategoria agregarCategoria = getFXML.getController(); //Clase la cual
                agregarCategoria.setPrevStage(prevStage); //Asiganmos escenario del otro

                scenePantalla = new Scene(panePantalla); //Asiganar el panel a escena
                stagePantalla.setScene(scenePantalla); //Asignamos escenario
                stagePantalla.initModality(Modality.APPLICATION_MODAL);
                stagePantalla.initOwner(prevStage);
                stagePantalla.show(); //Mostramos pantalla de agregar categoria
                break;
            case 4: //Agregar equipo
                stagePantalla.setTitle("Agregar equipo"); //Poner su titulo
                getFXML = new FXMLLoader(getClass().getResource("/view/agregarEquipo.fxml")); //Obtener la informacion del escenario
                panePantalla = getFXML.load(); //En un pane poner los datos

                AgregarEquipo agregarEquipo = getFXML.getController(); //Clase la cual
                agregarEquipo.setPrevStage(prevStage); //Asiganmos escenario del otro

                scenePantalla = new Scene(panePantalla); //Asiganar el panel a escena
                stagePantalla.setScene(scenePantalla); //Asignamos escenario
                stagePantalla.initModality(Modality.APPLICATION_MODAL);
                stagePantalla.initOwner(prevStage);
                stagePantalla.show(); //Mostramos pantalla de de agregar equipo
                break;
            case 5: //Agregar aula
                stagePantalla.setTitle("Agregar aula"); //Poner su titulo
                getFXML = new FXMLLoader(getClass().getResource("/view/agregarAula.fxml")); //Obtener la informacion del escenario
                panePantalla = getFXML.load(); //En un pane poner los datos

                AgregarAula agregarAula = getFXML.getController(); //Clase la cual
                agregarAula.setPrevStage(prevStage); //Asiganmos escenario del otro

                scenePantalla = new Scene(panePantalla); //Asiganar el panel a escena
                stagePantalla.setScene(scenePantalla); //Asignamos escenario
                stagePantalla.initModality(Modality.APPLICATION_MODAL);
                stagePantalla.initOwner(prevStage);
                stagePantalla.show(); //Mostramos pantalla de de agregar aula
                break;
        }
    }

    private void onChangeValuesInfo(){
        if (this.aula!=null){
            System.out.println(this.aula);
            this.nombreLbl.setText(this.aula.getNombre());
            this.capacidadLbl.setText(this.aula.getCapacidad() + " alumnos");
            this.claveLbl.setText(this.aula.getId());
            this.tipoLbl.setText(this.aula.getTipo());
            this.actualizarBtn.setVisible(true);
            this.asignarEquipoBtn.setVisible(true);
        }else{
            this.nombreLbl.setText("");
            this.capacidadLbl.setText("");
            this.claveLbl.setText("");
            this.tipoLbl.setText("");
            this.actualizarBtn.setVisible(false);
            this.asignarEquipoBtn.setVisible(false);
        }
    }
}
