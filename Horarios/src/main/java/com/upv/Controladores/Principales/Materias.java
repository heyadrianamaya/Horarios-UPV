package com.upv.Controladores.Principales;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.upv.expeciones.Mensajes;
import com.upv.utils.ChangeValues;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
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
            //createColumns();
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

                String[][] informacion = new String[materias.maxPosicion()+1][10];

                for (int pos = 0; pos <=  materias.maxPosicion(); pos++) {
                    for (int cuatri = 1; cuatri <=10; cuatri++) {
                        if(pos == 0){
                            informacion[pos][cuatri-1] =  "Cuatrimestre " + String.valueOf(cuatri);
                        }else{
                            if(materias.getMateria(cuatri,pos) != null){
                                System.out.println("cuatri: " + cuatri + " " + materias.getMateria(cuatri,pos).getNombre());
                                informacion[pos][cuatri-1] =  materias.getMateria(cuatri,pos).getNombre();
                            }
                        }
                        //System.out.println("Nada");
                    }
                }
                //FINALLY ADDED TO TableView
                setOnTable(informacion);
            } catch (Exception e) {
                Mensajes.setMensaje(e, e.getMessage());
            }
        }else{

        }
    }

    private void setOnTable(String[][] dataSource){
        planesTable.getColumns().clear();
        int a = 0;
        ObservableList<ObservableList<String>> data = FXCollections.observableArrayList();
        for (String[] row : dataSource) {
            if(a!=0){
                data.add(FXCollections.observableArrayList(row));
            }
            a++;
        }
        planesTable.setItems(data);
        for (int i = 0; i < dataSource[0].length; i++) {
            final int currentColumn = i;
            TableColumn<ObservableList<String>, String> column = new TableColumn<>(dataSource[0][i]);
            column.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().get(currentColumn)));
            column.setEditable(true);
            column.setCellFactory(TextFieldTableCell.forTableColumn());
            column.setOnEditCommit(
                    (TableColumn.CellEditEvent<ObservableList<String>, String> t) -> {
                        t.getTableView().getItems().get(t.getTablePosition().getRow()).set(t.getTablePosition().getColumn(), t.getNewValue());
                    }
            );
            planesTable.getColumns().add(column);
        }
    }

}
