package com.mycompany.quicktap_escritorio;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.stage.StageStyle;
import message.Message;


/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    
    
    public static ObjectInputStream in;
    public static ObjectOutputStream out;
    public static Socket socket;

    @Override
    public void start(Stage stage) throws IOException {
        
        try {
            socket = new Socket("localhost", 4444);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
            
            Message mensaje = new Message("CONEXION","CONEXION",new ArrayList<Object>());
            out.writeObject(mensaje);
            
            Message respuesta = (Message) in.readObject();
            System.out.println(respuesta.getData().get(0));

        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Se ha producido un error de conexión con el servidor.");
            alert.showAndWait();
            System.exit(1);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        scene = new Scene(loadFXML("login"), 681, 468);
        //scene = new Scene(loadFXML("principal"), 914, 652);
        stage.setTitle("QuickTap - Login");
        stage.setScene(scene);
        //stage.initStyle(StageStyle.UNDECORATED);
        stage.show();

        

        
        
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        
        
        
        launch();
        
    }
    
    
    
    

}