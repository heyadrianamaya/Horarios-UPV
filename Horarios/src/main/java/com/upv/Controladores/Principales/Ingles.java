package com.upv.Controladores.Principales;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.upv.Controladores.Actualizaciones.ActualizarCarrera;
import com.upv.Controladores.Registros.AgregarCarrera;
import com.upv.Controladores.Registros.AgregarCurso;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Ingles implements Initializable {
    private Stage prevStage;

    @FXML private JFXListView cursosList;
    @FXML private Label nombreLbl;
    @FXML private Label detallesLbl;
    @FXML private JFXComboBox turnoCBox;
    @FXML private TableView cursosTable;

    public void setPrevStage(Stage prevStage){

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void agregar() throws IOException{
        abrirPantalla(1);
    }

    private void abrirPantalla(int pantalla) throws IOException {
        Stage stagePantalla = new Stage(); //Creacion de nuevo Escenario
        FXMLLoader getFXML;
        Pane panePantalla; //En un pane poner los datos
        Scene scenePantalla;

        switch(pantalla){
            case 1:
                stagePantalla.setTitle("Agregar curso capacitación"); //Poner su titulo
                getFXML = new FXMLLoader(getClass().getResource("/view/agregarCurso.fxml")); //Obtener la informacion del escenario
                panePantalla = getFXML.load(); //En un pane poner los datos

                AgregarCurso agregarCurso = getFXML.getController(); //Clase la cual
                agregarCurso.setPrevStage(stagePantalla); //Asiganmos escenario del otro

                scenePantalla = new Scene(panePantalla); //Asiganar el panel a escena
                stagePantalla.setScene(scenePantalla); //Asignamos escenario
                stagePantalla.initModality(Modality.APPLICATION_MODAL);
                stagePantalla.initOwner(prevStage);
                stagePantalla.show(); //Mostramos pantalla de huella
                break;
        }
    }
}
