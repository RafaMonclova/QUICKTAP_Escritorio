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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.util.Callback;
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
public class AdminController implements Initializable {

    @FXML
    private ImageView burger;

    @FXML
    private AnchorPane panelMenu;

    @FXML
    private MFXButton btnAñadirEstabl;

    @FXML
    private MFXButton btnModificarProp;

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
    private AnchorPane panelModificarProp;

    @FXML
    private ListView<String> listViewEstablecimientos;

    @FXML
    private AnchorPane panelInfoUsuario;

    @FXML
    private MFXTextField nombreUsuarioInfo;

    @FXML
    private MFXPasswordField passwUsuarioInfo;

    @FXML
    private MFXTextField correoUsuarioInfo;

    @FXML
    private AnchorPane panelEditarRolesEstabl;

    @FXML
    private ListView<String> rolesUsuarioInfo;

    @FXML
    private ListView<String> rolesDisponibles;

    @FXML
    private ListView<String> establDisponibles;

    @FXML
    private ListView<String> establUsuarioInfo;

    @FXML
    private Label db_clientesConectados;

    @FXML
    private Label db_errores;

    @FXML
    private Label db_nuevosClientes;

    @FXML
    private Label db_totalClientes;

    @FXML
    private Label db_fechaRecarga;

    @FXML
    private Label db_ultimoCliente;

    @FXML
    private Label db_ipServidor;

    @FXML
    private TableView tablaPropietarios;

    private ArrayList<ArrayList<Object>> propietarios;

    @FXML
    private TableColumn<List<Object>, Object> columCorreo;

    @FXML
    private TableColumn<List<Object>, Object> columNombre;

    @FXML
    private TableColumn<List<Object>, Object> columPassw;
    
    private String usuarioCargado;

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

        actualizarDashboard();

        // Agrega un evento al hacer clic en una fila
        tablaPropietarios.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 1) { // Si se hizo un solo clic
                    // Obtiene la fila seleccionada
                    ArrayList<Object> filaSeleccionada = (ArrayList<Object>) tablaPropietarios.getSelectionModel().getSelectedItem();
                    if (filaSeleccionada != null) {
                        System.out.println(filaSeleccionada.get(0));
                        System.out.println(filaSeleccionada.get(1));
                        System.out.println(filaSeleccionada.get(2));

                        //Carga los roles y establecimientos para ser editados
                        usuarioCargado = (String) filaSeleccionada.get(0);
                        cargarRolesEstabl(usuarioCargado);

                    }
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
            btnModificarProp.setDisable(true);
        } else {
            btnSalir.setDisable(false);
            btnInfoUsuario.setDisable(false);
            btnAñadirEstabl.setDisable(false);
            btnAñadirPropietario.setDisable(false);
            btnHome.setDisable(false);
            btnModificarProp.setDisable(false);
        }

    }

    @FXML
    private void panelInicio(ActionEvent e) {
        labelVentanaActual.setText("Inicio");
        panelInicio.setVisible(true);
        panelAltaEstabl.setVisible(false);
        panelAltaUsuario.setVisible(false);
        panelInfoUsuario.setVisible(false);
        panelModificarProp.setVisible(false);

        actualizarDashboard();

    }

    public void actualizarDashboard() {
        //Obtiene del servidor los datos a mostrar en el panel de inicio
        Task<ArrayList<Object>> task = new Task<ArrayList<Object>>() {
            @Override
            protected ArrayList<Object> call() throws Exception {
                Message peticion = new Message("SERVER_DATA_QUERY", new ArrayList<Object>());
                ArrayList<Object> listaDatos = new ArrayList<Object>();
                try {
                    App.out.writeObject(peticion);
                    Message mensajeRespuesta = (Message) App.in.readObject();

                    listaDatos = mensajeRespuesta.getData();

                } catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
                return listaDatos;
            }
        };

        task.setOnSucceeded(event -> {
            ArrayList<Object> listaDatos = task.getValue();

            //Establece los datos obtenidos del servidor en los paneles
            db_nuevosClientes.setText((int) listaDatos.get(0) + "");
            db_ultimoCliente.setText((String) listaDatos.get(1));

            db_clientesConectados.setText((int) listaDatos.get(2) + "");
            db_ipServidor.setText((String) listaDatos.get(3));

            db_totalClientes.setText((long) listaDatos.get(4) + "");
            db_fechaRecarga.setText((String) listaDatos.get(5));

            db_errores.setText((int) listaDatos.get(6) + "");
        });

        Thread thread = new Thread(task);
        thread.start();
    }

    @FXML
    private void panelAltaEstabl(ActionEvent e) {
        labelVentanaActual.setText("Nuevo Establecimiento");
        panelAltaEstabl.setVisible(true);
        panelInicio.setVisible(false);
        panelAltaUsuario.setVisible(false);
        panelInfoUsuario.setVisible(false);
        panelModificarProp.setVisible(false);

    }

    @FXML
    private void panelAltaUsuario(ActionEvent e) {
        labelVentanaActual.setText("Nuevo Usuario");
        panelAltaUsuario.setVisible(true);
        panelAltaEstabl.setVisible(false);
        panelInicio.setVisible(false);
        panelInfoUsuario.setVisible(false);
        panelModificarProp.setVisible(false);

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

    @FXML
    private void panelModificarProp(ActionEvent e) {
        labelVentanaActual.setText("Listado de propietarios");
        panelModificarProp.setVisible(true);
        panelAltaEstabl.setVisible(false);
        panelInicio.setVisible(false);
        panelAltaUsuario.setVisible(false);
        panelInfoUsuario.setVisible(false);

        //Carga los usuarios obtenidos del servidor
        Task<ArrayList<ArrayList<Object>>> task = new Task<ArrayList<ArrayList<Object>>>() {
            @Override
            protected ArrayList<ArrayList<Object>> call() throws Exception {
                ArrayList<Object> data = new ArrayList<Object>();

                Message peticion = new Message("PROP_QUERY", data);
                ArrayList<ArrayList<Object>> usuarios = new ArrayList<>();

                try {
                    App.out.writeObject(peticion);
                    Message mensajeRespuesta = (Message) App.in.readObject();

                    ArrayList<Object> datosRecibidos = (ArrayList<Object>) mensajeRespuesta.getData();

                    for (Object listado : datosRecibidos) {
                        usuarios.add((ArrayList<Object>) listado);
                    }

                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                return usuarios;
            }
        };

        task.setOnSucceeded(event -> {
            ArrayList<ArrayList<Object>> usuarios = task.getValue();

            /*
            for(Object o : usuarios){
                
                ArrayList<Object> datosUsuario = (ArrayList<Object>) o;
                
                
                    
                System.out.println(datosUsuario.get(0));
                System.out.println(datosUsuario.get(1)); 
                System.out.println(datosUsuario.get(2));
                
                
            }
             */
            // Agregar las columnas a la tabla
            //TableColumn<List<Object>, Object> columnaNombre = new TableColumn<>("Columna 1");
            columNombre.setCellValueFactory(new Callback<CellDataFeatures<List<Object>, Object>, ObservableValue<Object>>() {
                public ObservableValue<Object> call(CellDataFeatures<List<Object>, Object> c) {
                    return new SimpleObjectProperty<Object>(c.getValue().get(0));
                }
            });

            //          tablaPropietarios.getColumns().add(columNombre);
            //TableColumn<List<Object>, Object> columnaCorreo = new TableColumn<>("Columna 2");
            columCorreo.setCellValueFactory(new Callback<CellDataFeatures<List<Object>, Object>, ObservableValue<Object>>() {
                public ObservableValue<Object> call(CellDataFeatures<List<Object>, Object> c) {
                    return new SimpleObjectProperty<Object>(c.getValue().get(1));
                }
            });
//            tablaPropietarios.getColumns().add(columCorreo);

            //TableColumn<List<Object>, Object> columnaPassw = new TableColumn<>("Contraseña");
            columPassw.setCellValueFactory(new Callback<CellDataFeatures<List<Object>, Object>, ObservableValue<Object>>() {
                public ObservableValue<Object> call(CellDataFeatures<List<Object>, Object> c) {
                    return new SimpleObjectProperty<Object>(c.getValue().get(2));
                }
            });

            // Asignar las filas a la tabla
            tablaPropietarios.setItems(FXCollections.observableArrayList(usuarios));

        });

        Thread thread = new Thread(task);
        thread.start();

    }

    @FXML
    private void panelInfoUsuario(ActionEvent e) {
        labelVentanaActual.setText("Perfil de Usuario");
        panelInfoUsuario.setVisible(true);
        panelAltaUsuario.setVisible(false);
        panelAltaEstabl.setVisible(false);
        panelInicio.setVisible(false);
        panelModificarProp.setVisible(false);

        //Carga la información del usuario
        Task<ArrayList<Object>> task = new Task<ArrayList<Object>>() {
            @Override
            protected ArrayList<Object> call() throws Exception {
                ArrayList<Object> data = new ArrayList<Object>();
                data.add(btnLabelUsuario.getText());
                Message peticion = new Message("USER_DATA_QUERY", data);
                ArrayList<Object> datosUsuario = new ArrayList<Object>();
                ArrayList<String> rolesUsuario = new ArrayList<String>();
                ArrayList<String> establUsuario = new ArrayList<String>();
                try {
                    App.out.writeObject(peticion);
                    Message mensajeRespuesta = (Message) App.in.readObject();

                    datosUsuario.add((String) mensajeRespuesta.getData().get(0));
                    datosUsuario.add((String) mensajeRespuesta.getData().get(1));
                    datosUsuario.add((String) mensajeRespuesta.getData().get(2));
                    rolesUsuario = (ArrayList<String>) mensajeRespuesta.getData().get(3);
                    datosUsuario.add(rolesUsuario);

                    if (rolesUsuario.contains("trabajador") || rolesUsuario.contains("propietario")) {
                        establUsuario = (ArrayList<String>) mensajeRespuesta.getData().get(4);
                        datosUsuario.add(establUsuario);
                    }

                } catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
                return datosUsuario;
            }
        };

        task.setOnSucceeded(event -> {
            ArrayList<Object> datosUsuario = task.getValue();
            nombreUsuarioInfo.setText((String) datosUsuario.get(0));
            correoUsuarioInfo.setText((String) datosUsuario.get(1));
            passwUsuarioInfo.setText((String) datosUsuario.get(2));
//            rolesUsuarioInfo.getItems().clear();
//            rolesUsuarioInfo.getItems().addAll((ArrayList<String>) datosUsuario.get(3));
//            establUsuarioInfo.getItems().clear();
//            establUsuarioInfo.getItems().addAll((ArrayList<String>) datosUsuario.get(4));
        });

        Thread thread = new Thread(task);
        thread.start();

    }

    public void cargarRolesEstabl(String usuario) {

        panelEditarRolesEstabl.setVisible(true);

        Task<ArrayList<Object>> task = new Task<ArrayList<Object>>() {
            @Override
            protected ArrayList<Object> call() throws Exception {
                ArrayList<Object> data = new ArrayList<>();
                data.add(usuario);

                Message peticion = new Message("EDIT_ROLE_ESTABL_QUERY", data);

                ArrayList<Object> datosRolesEstablecimientos = new ArrayList<>();

                ArrayList<String> listaRolesDisponibles = new ArrayList<String>();
                ArrayList<String> listaRolesUsuario = new ArrayList<String>();
                ArrayList<String> listaEstablDisponibles = new ArrayList<String>();
                ArrayList<String> listaEstablUsuario = new ArrayList<String>();
                try {
                    App.out.writeObject(peticion);
                    Message mensajeRespuesta = (Message) App.in.readObject();
                    listaRolesDisponibles = (ArrayList<String>) mensajeRespuesta.getData().get(0);
                    listaRolesUsuario = (ArrayList<String>) mensajeRespuesta.getData().get(1);
                    listaEstablDisponibles = (ArrayList<String>) mensajeRespuesta.getData().get(2);
                    listaEstablUsuario = (ArrayList<String>) mensajeRespuesta.getData().get(3);

                    datosRolesEstablecimientos.add(listaRolesDisponibles);
                    datosRolesEstablecimientos.add(listaRolesUsuario);
                    datosRolesEstablecimientos.add(listaEstablDisponibles);
                    datosRolesEstablecimientos.add(listaEstablUsuario);

                } catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
                return datosRolesEstablecimientos;
            }
        };

        task.setOnSucceeded(event -> {
            ArrayList<Object> datosRolesEstablecimientos = task.getValue();
            rolesDisponibles.getItems().clear();
            rolesUsuarioInfo.getItems().clear();
            establDisponibles.getItems().clear();
            establUsuarioInfo.getItems().clear();

            establDisponibles.getItems().addAll((ArrayList<String>) datosRolesEstablecimientos.get(0));
            rolesDisponibles.getItems().addAll((ArrayList<String>) datosRolesEstablecimientos.get(1));
            establUsuarioInfo.getItems().addAll((ArrayList<String>) datosRolesEstablecimientos.get(2));
            rolesUsuarioInfo.getItems().addAll((ArrayList<String>) datosRolesEstablecimientos.get(3));
        });

        Thread thread = new Thread(task);
        thread.start();

    }

    @FXML
    public void cerrarEditarRoles(ActionEvent e) {
        panelEditarRolesEstabl.setVisible(false);
        
        Task<Boolean> task = new Task<Boolean>() {
            @Override
            protected Boolean call() throws Exception {
                ArrayList<Object> data = new ArrayList<>();
                data.add(usuarioCargado);
                
                ArrayList<String> listaRolesUsuario = new ArrayList<String>(rolesUsuarioInfo.getItems());
                ArrayList<String> listaEstablUsuario = new ArrayList<String>(establUsuarioInfo.getItems());
                data.add(listaRolesUsuario);
                data.add(listaEstablUsuario);
                
                Message peticion = new Message("ROLE_ESTABL_UPDATE", data);

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
                alert.setContentText("Datos actualizados.");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText("Error al modificar los datos");
                alert.setContentText("Revise los datos introducidos.");
                alert.showAndWait();
            }
        });

        Thread thread = new Thread(task);
        thread.start();
    }

    @FXML
    public void añadirRol(ActionEvent e) {

        if (rolesUsuarioInfo.getItems().contains(rolesDisponibles.getSelectionModel().getSelectedItem())) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ERROR");
            alert.setHeaderText("Error al añadir rol");
            alert.setContentText("El usuario ya tiene el rol seleccionado.");
            alert.showAndWait();
        } else {
            rolesUsuarioInfo.getItems().add(rolesDisponibles.getSelectionModel().getSelectedItem());
        }

    }

    @FXML
    public void quitarRol(ActionEvent e) {

        if (rolesUsuarioInfo.getItems().size() == 1) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ERROR");
            alert.setHeaderText("Error al quitar rol");
            alert.setContentText("El usuario debe tener al menos un rol.");
            alert.showAndWait();
        } else {
            rolesUsuarioInfo.getItems().remove(rolesUsuarioInfo.getSelectionModel().getSelectedItem());
        }

    }

    @FXML
    public void añadirEstabl(ActionEvent e) {

        if (establUsuarioInfo.getItems().contains(establDisponibles.getSelectionModel().getSelectedItem())) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ERROR");
            alert.setHeaderText("Error al añadir establecimiento");
            alert.setContentText("El usuario ya tiene el establecimiento seleccionado.");
            alert.showAndWait();
        } else {
            establUsuarioInfo.getItems().add(establDisponibles.getSelectionModel().getSelectedItem());
        }

    }

    @FXML
    public void quitarEstabl(ActionEvent e) {

        if (establUsuarioInfo.getItems().size() == 1) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ERROR");
            alert.setHeaderText("Error al quitar establecimiento");
            alert.setContentText("El usuario debe tener al menos un establecimiento.");
            alert.showAndWait();
        } else {
            establUsuarioInfo.getItems().remove(establUsuarioInfo.getSelectionModel().getSelectedItem());
        }

    }

    //Modifica los datos del usuario en la base de datos. 
    //En este caso, se envían al servidor los roles y los establecimientos, ya que no tocamos datos de clientes (sin establecimiento vinculado)
    @FXML
    private void actualizarDatosUsuario(ActionEvent e) {

        //Envía los nuevos datos al servidor
        Task<Boolean> task = new Task<Boolean>() {
            @Override
            protected Boolean call() throws Exception {

                ArrayList<Object> nuevosDatos = new ArrayList<Object>();
                nuevosDatos.add(btnLabelUsuario.getText());
                nuevosDatos.add(nombreUsuarioInfo.getText());
                nuevosDatos.add(correoUsuarioInfo.getText());
                nuevosDatos.add(passwUsuarioInfo.getText());

//                ArrayList<String> listaRoles = new ArrayList<String>(rolesUsuarioInfo.getItems());
//                nuevosDatos.add(listaRoles);
//
//                ArrayList<String> listaEstabl = new ArrayList<String>(establUsuarioInfo.getItems());
//                nuevosDatos.add(listaEstabl);
                Message peticion = new Message("USER_UPDATE", nuevosDatos);
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
                alert.setContentText("Datos actualizados.");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText("Error al modificar los datos");
                alert.setContentText("Revise los datos introducidos.");
                alert.showAndWait();
            }

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
    public void añadirNuevoEstabl(ActionEvent e) {

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

                    //Añade rol propietario y trabajador al nuevo propietario
                    roles.add("propietario"); //Rol propietario
                    roles.add("trabajador"); //Rol trabajador
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

    //Envía al servidor el nombre del usuario que cierra sesión, y vuelve a la pantalla de login
    @FXML
    public void logout(ActionEvent e) {

        Task<Boolean> task = new Task<Boolean>() {
            @Override
            protected Boolean call() throws Exception {
                ArrayList<Object> data = new ArrayList<Object>();
                data.add(btnLabelUsuario.getText());

                Message peticion = new Message("LOG_OUT", data);
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
                alert.setHeaderText("Sesión cerrada");
                alert.setContentText("Se ha desconectado del sistema");
                alert.showAndWait();

                //Crea un stage de la ventana principal, enviando el nombre del usuario que logea
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
                    Parent root1 = (Parent) fxmlLoader.load();
                    Stage stage = new Stage();
                    stage.setTitle("QuickTap - Login");
                    stage.setScene(new Scene(root1, 681, 468));
                    stage.show();

                    //Oculta la ventana de Login
                    final Node source = (Node) e.getSource();
                    final Stage currentStage = (Stage) source.getScene().getWindow();
                    currentStage.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText("Error al desconectar");
                alert.setContentText("Ha habido un problema al cerrar la sesión");
                alert.showAndWait();
            }

            try {
                App.setRoot("login");
            } catch (IOException ex) {
                Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

        Thread thread = new Thread(task);
        thread.start();

    }

}
