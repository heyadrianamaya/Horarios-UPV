package com.upv.Controladores.Principales;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.upv.Controladores.Actualizaciones.ActualizarAula;
import com.upv.Controladores.Asignaciones.AsignacionEquipo;
import com.upv.Controladores.Registros.AgregarAula;
import com.upv.Controladores.Registros.AgregarCategoria;
import com.upv.Controladores.Registros.AgregarEquipo;
import com.upv.expeciones.Mensajes;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
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
        } catch (SQLException | ClassNotFoundException e) {
            Mensajes.setMensaje(e, e.getMessage());
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
}
