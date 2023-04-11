package com.mycompany.proyecto_pruebainsert;

import io.github.palexdev.materialfx.controls.MFXFilterComboBox;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import message.Message;

public class InsertController implements Initializable{

    @FXML
    private MFXTextField mailField;

    @FXML
    private MFXTextField nameField;

    @FXML
    private MFXPasswordField passwordField;

    @FXML
    private MFXFilterComboBox<String> roleCombo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Task<ArrayList<String>> task = new Task<ArrayList<String>>() {
            @Override
            protected ArrayList<String> call() throws Exception {
                Message peticion = new Message("ROLE_QUERY", new ArrayList<Object>());
                ArrayList<String> listaRoles = new ArrayList<String>();
                try {
                    App.out.writeObject(peticion);
                    Message mensajeRespuesta = (Message) App.in.readObject();
                    listaRoles = (ArrayList<String>)mensajeRespuesta.getData().get(0);
                   
                } catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
                return listaRoles;
            }
        };
        
        task.setOnSucceeded(event -> {
            ArrayList<String> listaRoles = task.getValue();
            roleCombo.getItems().addAll(listaRoles);
            roleCombo.setValue(listaRoles.get(0));
        });

        Thread thread = new Thread(task);
        thread.start();
        
    }
    
    @FXML
    public void addUser(ActionEvent e){
        
        Task<Boolean> task = new Task<Boolean>() {
            @Override
            protected Boolean call() throws Exception {
                ArrayList<Object> data = new ArrayList<Object>();
                ArrayList<String> rols = new ArrayList<String>();
                rols.add(roleCombo.getValue());
                
                data.add(nameField.getText());
                data.add(mailField.getText());
                data.add(passwordField.getText());
                data.add(rols);
                Message peticion = new Message("INSERT_USER", data);
                boolean respuesta = false;
                System.out.println(rols);
                try {
                    App.out.writeObject(peticion);
                    Message mensajeRespuesta = (Message) App.in.readObject();
                    respuesta = (boolean)mensajeRespuesta.getData().get(0);
                   
                } catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
                return respuesta;
            }
        };
        
        task.setOnSucceeded(event -> {
            boolean respuesta = task.getValue();
            
            if(respuesta){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Información");
                alert.setHeaderText("Correcto");
                alert.setContentText("Usuario dado de alta.");
                alert.showAndWait();
            }
            else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Información");
                alert.setHeaderText("ERROR");
                alert.setContentText("El correo introducido ya existe");
                alert.showAndWait();
            }
            
            
        });

        Thread thread = new Thread(task);
        thread.start();
        
    }
    
    
}