package com.mycompany.quicktap_escritorio;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.dialogs.MFXGenericDialog;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;
import message.Message;

public class LoginController implements Initializable {

    @FXML
    private MFXPasswordField passwordField;

    @FXML
    private TextField mailField;

    @FXML
    private ListView<String> listViewRoles;

    @FXML
    private MFXGenericDialog dialogPane;

    @FXML
    private MFXButton btnContinuar;

    private String nombreUsuario;

    private ArrayList<String> listaRoles = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    public void login(ActionEvent e) {
        Task<Boolean> task = new Task<Boolean>() {
            @Override
            protected Boolean call() throws Exception {
                ArrayList<Object> data = new ArrayList<Object>();
                data.add(mailField.getText());
                data.add(passwordField.getText());
                Message peticion = new Message("LOGIN", data);

                boolean respuesta = false;
                try {
                    App.out.writeObject(peticion);
                    Message mensajeRespuesta = (Message) App.in.readObject();

                    respuesta = (boolean) mensajeRespuesta.getData().get(0);

                    if (respuesta) {
                        nombreUsuario = (String) mensajeRespuesta.getData().get(1);
                        listaRoles = (ArrayList<String>) mensajeRespuesta.getData().get(2);
                    }

                } catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
                return respuesta;
            }
        };

        task.setOnSucceeded(event -> {
            boolean respuesta = task.getValue();

            if (respuesta) {
                dialogPane.setVisible(true);
                listViewRoles.getItems().addAll(listaRoles);

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Login");
                alert.setHeaderText("ERROR");
                alert.setContentText("No existe un usuario con los datos introducidos");
                alert.showAndWait();
            }

        });

        Thread thread = new Thread(task);
        thread.start();

    }

    @FXML
    public void elegirRol(ActionEvent e) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Login");
        alert.setHeaderText("Bienvenido, "+nombreUsuario);
        alert.setContentText("Sesión iniciada");
        alert.showAndWait();

        //CAMBIARLO POR ADMIN
        if (listViewRoles.getSelectionModel().getSelectedItem().equals("trabajador")) {
            //Crea un stage de la ventana principal, enviando el nombre del usuario que logea
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("admin.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                AdminController controller = fxmlLoader.<AdminController>getController();
                controller.setUsuario(nombreUsuario);
                
                Stage stage = new Stage();
                stage.setTitle("QuickTap - Dashboard");
                stage.setScene(new Scene(root1, 914, 652));
                stage.show();

                //Oculta la ventana de Login
                final Node source = (Node) e.getSource();
                final Stage currentStage = (Stage) source.getScene().getWindow();
                currentStage.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }
    
    @FXML
    public void cerrarVentana(ActionEvent e){
        dialogPane.setVisible(false);
    }

    

}
