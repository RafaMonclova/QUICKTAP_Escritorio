/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.quicktap_escritorio;

import com.jfoenix.controls.JFXListView;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.legacy.MFXLegacyComboBox;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.util.Duration;
import message.Message;
import netscape.javascript.JSObject;
import se.walkercrou.places.GooglePlaces;
import se.walkercrou.places.Place;
import se.walkercrou.places.exception.GooglePlacesException;
import se.walkercrou.places.exception.NoResultsFoundException;

/**
 * FXML Controller class
 *
 * @author rafam
 */
public class PrincipalController implements Initializable {

    @FXML
    private ImageView burger;

    @FXML
    private AnchorPane panelMenu;

    @FXML
    private MFXButton btnAñadirEstabl;

    @FXML
    private MFXButton btnAñadirPropietario;

    @FXML
    private MFXButton btnHome;

    @FXML
    private MFXButton btnInfoUsuario;

    @FXML
    private MFXButton btnMenu;

    @FXML
    private MFXButton btnSalir;

    @FXML
    private MFXButton btnLabelUsuario;

    @FXML
    private Label labelVentanaActual;

    @FXML
    private AnchorPane panelAltaEstabl;

    @FXML
    private GridPane panelInicio;

    @FXML
    private MFXTextField coordsEstabl;

    @FXML
    private MFXTextField direccionEstabl;

    @FXML
    private MFXTextField nombreEstabl;

    @FXML
    private ListView<String> listViewDirecciones;

    List<Place> places;

    @FXML
    private MFXPasswordField passwUsuario;

    @FXML
    private MFXTextField nombreUsuario;

    @FXML
    private MFXTextField correoUsuario;

    @FXML
    private AnchorPane panelAltaUsuario;

    
    @FXML
    private ListView<String> listViewEstablecimientos;


    @Override
    public void initialize(URL url, ResourceBundle rb) {

        btnMenu.setOnMouseClicked(event -> togglePanelLateral(panelMenu));
        listViewDirecciones.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                try {
                    int indice = listViewDirecciones.getSelectionModel().getSelectedIndex();
                    Place p = places.get(indice);
                    direccionEstabl.setText(p.getAddress());
                    coordsEstabl.setText(p.getLatitude() + "," + p.getLongitude());
                } catch (NullPointerException | IndexOutOfBoundsException ex) {

                }

            }
        });
        

    }

    //Establece el nombre del usuario logeado
    public void setUsuario(String usuario) {

        btnLabelUsuario.setText(usuario);

    }

    // Método para animar la aparición/desaparición del panel lateral
    private void togglePanelLateral(AnchorPane panelLateral) {
        TranslateTransition transicion = new TranslateTransition(Duration.seconds(0.3), panelLateral);
        if (panelLateral.isVisible()) {
            // Si el panel lateral está visible, ocultarlo
            transicion.setToX(-panelLateral.getWidth());
            transicion.setOnFinished(event -> panelLateral.setVisible(false));
            disableButtons(false);
        } else {
            // Si el panel lateral está oculto, mostrarlo
            panelLateral.setVisible(true);
            transicion.setToX(0);
            disableButtons(true);
        }
        transicion.play();
    }

    //Activa o desactiva los botones del panel lateral
    private void disableButtons(boolean activado) {
        if (activado) {
            btnSalir.setDisable(true);
            btnInfoUsuario.setDisable(true);
            btnAñadirEstabl.setDisable(true);
            btnAñadirPropietario.setDisable(true);
            btnHome.setDisable(true);
        } else {
            btnSalir.setDisable(false);
            btnInfoUsuario.setDisable(false);
            btnAñadirEstabl.setDisable(false);
            btnAñadirPropietario.setDisable(false);
            btnHome.setDisable(false);
        }

    }

    @FXML
    private void panelInicio(ActionEvent e) {
        labelVentanaActual.setText("Inicio");
        panelInicio.setVisible(true);
        panelAltaEstabl.setVisible(false);
        panelAltaUsuario.setVisible(false);
    }

    @FXML
    private void panelAltaEstabl(ActionEvent e) {
        labelVentanaActual.setText("Nuevo Establecimiento");
        panelAltaEstabl.setVisible(true);
        panelInicio.setVisible(false);
        panelAltaUsuario.setVisible(false);

    }

    @FXML
    private void panelAltaUsuario(ActionEvent e) {
        labelVentanaActual.setText("Nuevo Usuario");
        panelAltaUsuario.setVisible(true);
        panelAltaEstabl.setVisible(false);
        panelInicio.setVisible(false);
        
        //Carga los establecimientos disponibles
        Task<ArrayList<String>> task = new Task<ArrayList<String>>() {
            @Override
            protected ArrayList<String> call() throws Exception {
                Message peticion = new Message("ESTABL_QUERY", new ArrayList<Object>());
                ArrayList<String> listaEstabl = new ArrayList<String>();
                try {
                    App.out.writeObject(peticion);
                    Message mensajeRespuesta = (Message) App.in.readObject();
                    listaEstabl = (ArrayList<String>) mensajeRespuesta.getData().get(0);

                } catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
                return listaEstabl;
            }
        };

        task.setOnSucceeded(event -> {
            ArrayList<String> listaEstabl = task.getValue();
            listViewEstablecimientos.getItems().clear();
            listViewEstablecimientos.getItems().addAll(listaEstabl);
        });

        Thread thread = new Thread(task);
        thread.start();

    }

    //Rellena el ListView con las direcciones de los establecimientos que coinciden con el nombre introducido
    @FXML
    public void buscarDireccion(ActionEvent e) {

        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {

                if (nombreEstabl.getText().isEmpty()) {
                    listViewDirecciones.getItems().clear();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Búsqueda");
                    alert.setHeaderText("Información");
                    alert.setContentText("Debe rellenar el campo 'nombre'");
                    alert.showAndWait();
                } else {

                    try {
                        GooglePlaces client = new GooglePlaces("AIzaSyAaP6rsOVudwTFDitm7dKUbSJpTBabVyFU");

                        places = client.getPlacesByQuery(nombreEstabl.getText(), GooglePlaces.MAXIMUM_RESULTS);

                    } catch (GooglePlacesException ex) {

                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Búsqueda");
                        alert.setHeaderText("Información");
                        alert.setContentText("Sin resultados");
                        alert.showAndWait();

                    }

                }

                return null;
            }
        };

        task.setOnSucceeded(event -> {

            ArrayList<String> direcciones = new ArrayList<String>();

            for (Place place : places) {

                direcciones.add(place.getAddress());

            }

            listViewDirecciones.getItems().clear();
            listViewDirecciones.getItems().addAll(direcciones);

        });

        Thread thread = new Thread(task);
        thread.start();

    }

    @FXML
    public void añadirEstabl(ActionEvent e) {

        if (nombreEstabl.getText().isEmpty() || direccionEstabl.getText().isEmpty() || coordsEstabl.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Información");
            alert.setHeaderText("ERROR");
            alert.setContentText("Debe rellenar todos los campos");
            alert.showAndWait();

        } else {
            Task<Boolean> task = new Task<Boolean>() {
                @Override
                protected Boolean call() throws Exception {
                    ArrayList<Object> data = new ArrayList<Object>();

                    data.add(nombreEstabl.getText());
                    data.add(direccionEstabl.getText());
                    data.add(coordsEstabl.getText());
                    Message peticion = new Message("INSERT_ESTABL", data);
                    boolean respuesta = false;
                    try {
                        App.out.writeObject(peticion);
                        Message mensajeRespuesta = (Message) App.in.readObject();
                        respuesta = (boolean) mensajeRespuesta.getData().get(0);

                    } catch (IOException ex) {
                        Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    return respuesta;
                }
            };

            task.setOnSucceeded(event -> {
                boolean respuesta = task.getValue();

                if (respuesta) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Información");
                    alert.setHeaderText("Correcto");
                    alert.setContentText("Establecimiento dado de alta.");
                    alert.showAndWait();
                    nombreEstabl.setText("");
                    direccionEstabl.setText("");
                    coordsEstabl.setText("");
                } else {
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

    @FXML
    public void añadirUsuario(ActionEvent e) {

        //Comprueba que los campos no estén vacíos
        if (nombreUsuario.getText().isEmpty() || correoUsuario.getText().isEmpty() || passwUsuario.getText().isEmpty() 
                || listViewEstablecimientos.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Información");
            alert.setHeaderText("ERROR");
            alert.setContentText("Debe rellenar todos los campos");
            alert.showAndWait();

        } else {
            Task<Boolean> task = new Task<Boolean>() {
                @Override
                protected Boolean call() throws Exception {
                    ArrayList<Object> data = new ArrayList<Object>();
                    
                    ArrayList<String> roles = new ArrayList<String>();
                    ArrayList<String> establecimientos = new ArrayList<String>();
                    roles.add("propietario"); //Rol propietario, ya que damos de alta solo propietarios desde esta cuenta
                    establecimientos.add(listViewEstablecimientos.getSelectionModel().getSelectedItem());
                    
                    data.add(nombreUsuario.getText());
                    data.add(correoUsuario.getText());
                    data.add(passwUsuario.getText());
                    data.add(roles);
                    data.add(establecimientos);
                    
                    Message peticion = new Message("INSERT_USER", data);
                    boolean respuesta = false;
                    try {
                        App.out.writeObject(peticion);
                        Message mensajeRespuesta = (Message) App.in.readObject();
                        respuesta = (boolean) mensajeRespuesta.getData().get(0);

                    } catch (IOException ex) {
                        Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    return respuesta;
                }
            };

            task.setOnSucceeded(event -> {
                boolean respuesta = task.getValue();

                if (respuesta) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Información");
                    alert.setHeaderText("Correcto");
                    alert.setContentText("Usuario dado de alta.");
                    alert.showAndWait();
                    nombreUsuario.setText("");
                    correoUsuario.setText("");
                    passwUsuario.setText("");
                } else {
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
    
    

}
