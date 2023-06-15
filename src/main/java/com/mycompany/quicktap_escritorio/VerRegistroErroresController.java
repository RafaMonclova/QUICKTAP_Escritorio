/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.quicktap_escritorio;

import io.github.palexdev.materialfx.controls.MFXListView;
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
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;
import message.Message;

/**
 * FXML Controller class
 *
 * @author rafam
 */
public class VerRegistroErroresController implements Initializable {

    @FXML
    private ListView<String> listaviewErrores;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //Obtiene la caja generada en cada establecimiento
        Task<ArrayList<Object>> task = new Task<ArrayList<Object>>() {
            @Override
            protected ArrayList<Object> call() throws Exception {
                Message peticion = new Message("SERVIDOR", "DATOS_SERVIDOR", new ArrayList<>());

                ArrayList<Object> datosServidor = new ArrayList<>();
                try {
                    App.out.writeObject(peticion);
                    Message mensajeRespuesta = (Message) App.in.readObject();

                    datosServidor = mensajeRespuesta.getData();

                    
                } catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
                return datosServidor;
            }
        };

        task.setOnSucceeded(event -> {
            ArrayList<Object> datosServidor = task.getValue();

            ArrayList<String> excepciones = (ArrayList<String>) datosServidor.get(7);
            
            try{
                listaviewErrores.getItems().clear();
                listaviewErrores.getItems().addAll(excepciones);
            }catch(NullPointerException ex){
                
            }
            
            
            
        });

        Thread thread = new Thread(task);
        thread.start();
        
    }    
    
    
    
}
