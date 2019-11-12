package com.upv.Controladores.Principales;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.upv.expeciones.Mensajes;
import com.upv.utils.ChangeValues;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;
import upv.poo.basededatos.ManagerConnection;
import upv.poo.datos.plandeestudios.PlanesDeEstudio;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Materias implements Initializable, ChangeValues {
    @FXML private JFXTextField archivoTxtF;
    @FXML private JFXComboBox<PlanesDeEstudio.PlanDeEstudio> planesCBox;
    @FXML private TableView planesTable;
    private ObservableList<ObservableList> data;
    private PlanesDeEstudio.PlanDeEstudio planDeEstudioSelected;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            ObservableList<PlanesDeEstudio.PlanDeEstudio> estudios =
                    FXCollections.observableArrayList(ManagerConnection.getInstance().getPlanesEstudio().getPlanesDeEstudio());
            this.planesCBox.setItems(estudios);
            this.planesCBox.getSelectionModel().selectedItemProperty()
                    .addListener((observable, oldValue, newValue) -> {
                        planDeEstudioSelected = newValue;
                        onChangeValueInfo();
                    });
            onChangeValueInfo();
            this.planesTable.getColumns().clear();
            createColumns();
        } catch (SQLException | ClassNotFoundException e) {
            Mensajes.setMensaje(e, e.getMessage());
        }
    }

    public void seleccionArchivo(){
        System.out.println("Abrir");
    }

    @Override
    public void onChangeValueInfo() {
        if (this.planDeEstudioSelected!=null){
            try {
                upv.poo.datos.materias.Materias materias = ManagerConnection.getInstance().getMaterias(this.planDeEstudioSelected);
                data = FXCollections.observableArrayList();
                for (int pos = 1; pos <=  materias.maxPosicion(); pos++) {
                    ObservableList<upv.poo.datos.materias.Materias.Materia> row = FXCollections.observableArrayList();
                    for (int cuatri = 1; cuatri <=10; cuatri++) {
                        row.add(materias.getMateria(cuatri,pos));
                    }
                    data.add(row);
                }
                //FINALLY ADDED TO TableView
                this.planesTable.setItems(data);
            } catch (Exception e) {
                Mensajes.setMensaje(e, e.getMessage());
            }
        }else{

        }
    }
    private void createColumns(){
        for (int i = 0; i < 10; i++) {
            //We are using non property style for making dynamic table
            final int j = i;
            TableColumn col = new TableColumn("Cuatri " + (i+1));
            col.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>)
                    param ->
                            new SimpleStringProperty(param.getValue().get(j).toString()));

            this.planesTable.getColumns().addAll(col);
            System.out.println("Column ["+i+"] ");
        }
    }
}
