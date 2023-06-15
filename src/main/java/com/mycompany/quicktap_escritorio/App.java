package com.mycompany.quicktap_escritorio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
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
    
    //Variables para la conexión
    public static ObjectInputStream in;
    public static ObjectOutputStream out;
    public static Socket socket;
    
    
    //Indica si se está conectado o no
    public static boolean conectado = false;
    
    /**
     * Lee los datos de la conexión de un fichero de configuración
     */
    public void leerDatosConexion(){
        BufferedReader br = null;
        String ip;
        int puerto;
        
        
        try {
            br = new BufferedReader(new FileReader("datos_red.txt"));
            String linea;
            
             while((linea = br.readLine()) != null){
                
                String[] valores = linea.split("\\;");
                ip = valores[0];
                puerto = Integer.parseInt(valores[1]);
                
                //Si existe el fichero, se conecta con los valores leídos
                conectar(ip,puerto);
                 
            }
             
            
        } catch (FileNotFoundException ex) {
            
        } catch (IOException ex) {
        }
        
        finally{
            
            try {
                
                if(br != null){
                    br.close();
                }
                
                
            } catch (IOException ex) {
            }
            
            
        }
        
    }
    
    /**
     * Inicia la conexión con el servidor.
     * @param ip IP del servidor remoto
     * @param puerto Puerto de escucha del servidor remoto
     */
    public static boolean conectar(String ip, int puerto){
        
        if(socket != null){
            try {
                socket.close();
            } catch (IOException ex) {
                Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
        //Inicia la conexión al servidor
        try {
            socket = new Socket();
            socket.connect(new InetSocketAddress(ip, puerto), 1000);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
            
            conectado = true;
            
            
            return true;
            
            
            
        } catch (IOException e) {
            
            conectado = false;
            
            
            return false;
            
        }

        
        
        
    }

    @Override
    public void start(Stage stage) throws IOException {
        
        //Carga la ventana de Login
        scene = new Scene(loadFXML("login"), 681, 468);
        stage.setTitle("QuickTap - Login");
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
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