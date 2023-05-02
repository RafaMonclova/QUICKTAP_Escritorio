/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.quicktap_escritorio;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;
import message.Message;

/**
 * FXML Controller class
 *
 * @author rafam
 */
public class VerTrabajadoresController implements Initializable {

    @FXML
    private TableView tabla;
    
    @FXML
    private TableColumn<List<Object>, Object> columRoles;

    @FXML
    private TableColumn<List<Object>, Object> columUsuario;
    
    private String usuario;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Task<ArrayList<ArrayList<Object>>> task = new Task<ArrayList<ArrayList<Object>>>() {
            @Override
            protected ArrayList<ArrayList<Object>> call() throws Exception {
                ArrayList<Object> data = new ArrayList<Object>();

                data.add(usuario);
                Message peticion = new Message("SERVIDOR", "GET_TRABAJADORES_CONECTADOS", data);

                ArrayList<ArrayList<Object>> datosUsuarios = new ArrayList<>();
                try {
                    App.out.writeObject(peticion);
                    Message mensajeRespuesta = (Message) App.in.readObject();

                    ArrayList<Object> datosRecibidos = (ArrayList<Object>) mensajeRespuesta.getData();

                    for (Object listado : datosRecibidos) {
                        datosUsuarios.add((ArrayList<Object>) listado);
                        System.out.println(listado);
                    }

                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                return datosUsuarios;
            }
        };

        task.setOnSucceeded(event -> {
            ArrayList<ArrayList<Object>> datosUsuarios = task.getValue();
            
            // Agregar las columnas a la tabla
            columUsuario.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<Object>, Object>, ObservableValue<Object>>() {
                public ObservableValue<Object> call(TableColumn.CellDataFeatures<List<Object>, Object> c) {
                    return new SimpleObjectProperty<Object>(c.getValue().get(0));
                }
            });

            columRoles.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<Object>, Object>, ObservableValue<Object>>() {
                public ObservableValue<Object> call(TableColumn.CellDataFeatures<List<Object>, Object> c) {
                    return new SimpleObjectProperty<Object>(c.getValue().get(1));
                }
            });

            // Asignar las filas a la tabla
            tabla.setItems(FXCollections.observableArrayList(datosUsuarios));
        });

        Thread thread = new Thread(task);
        thread.start();
        
    }   
    
    public void setUsuario(String usuario){
        this.usuario = usuario;
    }
    
    
    
}
