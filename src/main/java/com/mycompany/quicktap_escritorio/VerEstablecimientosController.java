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
public class VerEstablecimientosController implements Initializable {

    @FXML
    private TableView tabla;
    
    @FXML
    private TableColumn<List<Object>, Object> columCaja;

    @FXML
    private TableColumn<List<Object>, Object> columEstablecimiento;
    
    private String usuario;
    
    /**
     * Inicializa la ventana
     * @param url
     * @param rb 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //Obtiene la caja generada en cada establecimiento
        Task<ArrayList<ArrayList<Object>>> task = new Task<ArrayList<ArrayList<Object>>>() {
            @Override
            protected ArrayList<ArrayList<Object>> call() throws Exception {
                ArrayList<Object> data = new ArrayList<Object>();

                data.add(usuario);
                Message peticion = new Message("SERVIDOR", "GET_CAJAS", data);

                ArrayList<ArrayList<Object>> datosEstablecimientos = new ArrayList<>();
                try {
                    App.out.writeObject(peticion);
                    Message mensajeRespuesta = (Message) App.in.readObject();

                    ArrayList<Object> datosRecibidos = (ArrayList<Object>) mensajeRespuesta.getData();

                    for (Object listado : datosRecibidos) {
                        datosEstablecimientos.add((ArrayList<Object>) listado);
                    }

                } catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
                return datosEstablecimientos;
            }
        };

        task.setOnSucceeded(event -> {
            ArrayList<ArrayList<Object>> datosEstablecimientos = task.getValue();

            // Agregar las columnas a la tabla
            columEstablecimiento.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<Object>, Object>, ObservableValue<Object>>() {
                public ObservableValue<Object> call(TableColumn.CellDataFeatures<List<Object>, Object> c) {
                    return new SimpleObjectProperty<Object>(c.getValue().get(0));
                }
            });

            columCaja.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<Object>, Object>, ObservableValue<Object>>() {
                public ObservableValue<Object> call(TableColumn.CellDataFeatures<List<Object>, Object> c) {
                    return new SimpleObjectProperty<Object>(c.getValue().get(1));
                }
            });

            // Asignar las filas a la tabla
            tabla.setItems(FXCollections.observableArrayList(datosEstablecimientos));
        });

        Thread thread = new Thread(task);
        thread.start();
        
    }   
    
    /**
     * Establece el usuario de la ventana principal
     * @param usuario El nombre del usuario logeado
     */
    public void setUsuario(String usuario){
        this.usuario = usuario;
    }
    
    
    
}
