/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.quicktap_escritorio;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXFilterComboBox;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXProgressSpinner;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.legacy.MFXLegacyComboBox;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
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
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javafx.util.Duration;
import message.Message;
import netscape.javascript.JSObject;
import se.walkercrou.places.GooglePlaces;
import se.walkercrou.places.Place;
import se.walkercrou.places.exception.GooglePlacesException;
import se.walkercrou.places.exception.NoResultsFoundException;
import utilidades.FileUtils;
import utilidades.SHA;

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
    private Button btnLabelUsuario;

    @FXML
    private Label labelVentanaActual;

    @FXML
    private AnchorPane panelAltaEstabl;

    @FXML
    private GridPane panelInicio;

    @FXML
    private MFXTextField latitudEstabl;

    @FXML
    private MFXTextField longitudEstabl;

    @FXML
    private MFXTextField direccionEstabl;

    @FXML
    private MFXTextField nombreEstabl;

    @FXML
    private MFXButton btnAltaEstabl;

    @FXML
    private MFXButton btnModificarEstabl;

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
    private AnchorPane panelVerEstabl;

    @FXML
    private AnchorPane panelDatosPropietario;

    @FXML
    private MFXTextField nombreUsuarioInfo;

    @FXML
    private MFXPasswordField passwUsuarioInfo;

    @FXML
    private MFXTextField correoUsuarioInfo;

    @FXML
    private AnchorPane panelEditarRolesEstabl;

    @FXML
    private MFXButton btnAltaUsuario;

    @FXML
    private MFXButton btnModificarUsuario;

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

    @FXML
    private TableColumn<List<Object>, Object> columCorreo;

    @FXML
    private TableColumn<List<Object>, Object> columNombre;

    ArrayList<ArrayList<Object>> usuarios;

    @FXML
    private TableView tablaEstabl;

    @FXML
    private TableColumn<List<Object>, Object> columNombreEstabl;

    @FXML
    private TableColumn<List<Object>, Object> columDireccEstabl;

    //@FXML
    //private TableColumn<List<Object>, Object> columPassw;
    @FXML
    private MFXProgressSpinner spinner;

    @FXML
    MFXFilterComboBox<String> comboFiltroPropietario;

    @FXML
    private MFXTextField campoBuscarPropietario;

    @FXML
    private MFXTextField campoBuscarEstabl;

    @FXML
    private MFXTextField nombreUsuarioModificar;

    @FXML
    private MFXTextField correoUsuarioModificar;

    @FXML
    private MFXTextField passwUsuarioModificar;

    @FXML
    MFXFilterComboBox<String> comboFiltroEstabl;

    private String usuarioCargado;
    private String propietarioCargado;
    private String passwUsuarioModificarAntigua; //Contraseña temporal trabajador
    ArrayList<ArrayList<Object>> establecimientos;

    private double xOffset = 0;
    private double yOffset = 0;

    private ContextMenu contextMenuEstabl = new ContextMenu();
    private ContextMenu contextMenuPropietarios = new ContextMenu();
    private ContextMenu contextMenuPropietariosRolesEstabl = new ContextMenu();

    private String establCargado;

    /**
     * Inicializa la ventana, con los eventos de los botones y listas
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        comboFiltroPropietario.getItems().addAll("Nombre", "Correo");
        comboFiltroEstabl.getItems().addAll("Nombre", "Dirección");

        //Añade el evento drag and drop al panel de propietario, editar roles y establecimientos, y al panel de añadir establecimiento
        panelEditarRolesEstabl.setOnMousePressed(this::onDragPanePressed);
        panelEditarRolesEstabl.setOnMouseDragged(this::onDragPaneDragged);

        panelAltaEstabl.setOnMousePressed(this::onDragPanePressed);
        panelAltaEstabl.setOnMouseDragged(this::onDragPaneDragged);

        panelDatosPropietario.setOnMousePressed(this::onDragPanePressed);
        panelDatosPropietario.setOnMouseDragged(this::onDragPanePressed);

        spinner.setVisible(false); //Spinner de carga

        btnMenu.setOnMouseClicked(event -> togglePanelLateral(panelMenu)); //Habilita el panel lateral

        //Agrega un evento al hacer click en un elemento de la lista de direcciones
        listViewDirecciones.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                try {
                    int indice = listViewDirecciones.getSelectionModel().getSelectedIndex();
                    Place p = places.get(indice); //Obtiene el objeto Place y establece sus datos en los campos de texto
                    direccionEstabl.setText(p.getAddress());
                    latitudEstabl.setText(p.getLatitude() + "");
                    longitudEstabl.setText(p.getLongitude() + "");
                } catch (NullPointerException | IndexOutOfBoundsException ex) {

                }

            }
        });

        actualizarDashboard(); //Actualiza el dashboard en el inicio

        campoBuscarPropietario.textProperty().addListener((observable, oldValue, newValue) -> {

            buscarPropietario(newValue);

        });

        //TABLA ESTABLECIMIENTO
        MenuItem editarEstabl = new MenuItem("Editar");
        editarEstabl.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Obtener la fila seleccionada
                ArrayList<Object> filaSeleccionada = (ArrayList<Object>) tablaEstabl.getSelectionModel().getSelectedItem();

                // Verificar si hay un usuario seleccionado
                if (filaSeleccionada != null) {
                    //Carga los roles y establecimientos para ser editados
                    establCargado = (String) filaSeleccionada.get(0);
                    modificarEstabl(establCargado);
                }
            }
        });

        MenuItem borrarEstabl = new MenuItem("Borrar");
        borrarEstabl.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Obtener la fila seleccionada
                ArrayList<Object> filaSeleccionada = (ArrayList<Object>) tablaEstabl.getSelectionModel().getSelectedItem();

                // Verificar si hay un usuario seleccionado
                if (filaSeleccionada != null) {
                    establCargado = (String) filaSeleccionada.get(0);
                    borrarEstabl(establCargado);
                }
            }
        });

        contextMenuEstabl.getItems().addAll(editarEstabl, borrarEstabl);

        tablaEstabl.setContextMenu(contextMenuEstabl);

        tablaEstabl.setRowFactory(tv -> {
            TableRow row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getButton() == MouseButton.SECONDARY && (!row.isEmpty())) {
                    contextMenuEstabl.show(tablaEstabl, event.getScreenX(), event.getScreenY());
                }
            });
            return row;
        });

        //TABLA PROPIETARIOS
        MenuItem editarPropietario = new MenuItem("Editar");
        editarPropietario.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Obtener la fila seleccionada
                ArrayList<Object> filaSeleccionada = (ArrayList<Object>) tablaPropietarios.getSelectionModel().getSelectedItem();

                // Verificar si hay un usuario seleccionado
                if (filaSeleccionada != null) {
                    //Carga los roles y establecimientos para ser editados
                    propietarioCargado = (String) filaSeleccionada.get(0);
                    modificarPropietario(propietarioCargado);
                }
            }
        });

        MenuItem borrarPropietario = new MenuItem("Borrar");
        borrarPropietario.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Obtener la fila seleccionada
                ArrayList<Object> filaSeleccionada = (ArrayList<Object>) tablaPropietarios.getSelectionModel().getSelectedItem();

                // Verificar si hay un usuario seleccionado
                if (filaSeleccionada != null) {
                    propietarioCargado = (String) filaSeleccionada.get(0);
                    borrarPropietario(propietarioCargado);
                }
            }
        });

        MenuItem editarRolesPropietario = new MenuItem("Editar roles/Establecimientos");
        editarRolesPropietario.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Obtener la fila seleccionada
                ArrayList<Object> filaSeleccionada = (ArrayList<Object>) tablaPropietarios.getSelectionModel().getSelectedItem();

                // Verificar si hay un usuario seleccionado
                if (filaSeleccionada != null) {
                    //Carga los roles y establecimientos para ser editados
                    usuarioCargado = (String) filaSeleccionada.get(0);
                    cargarRolesEstabl(usuarioCargado);
                }
            }
        });

        contextMenuPropietarios.getItems().addAll(editarPropietario, editarRolesPropietario, borrarPropietario);

        tablaPropietarios.setContextMenu(contextMenuPropietarios);

        tablaPropietarios.setRowFactory(tv -> {
            TableRow row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getButton() == MouseButton.SECONDARY && (!row.isEmpty())) {
                    contextMenuPropietarios.show(tablaPropietarios, event.getScreenX(), event.getScreenY());
                }
            });
            return row;
        });

        campoBuscarEstabl.textProperty().addListener((observable, oldValue, newValue) -> {

            buscarEstablecimiento(newValue);

        });

    }

    /**
     * Evento al hacer click sobre un panel
     *
     * @param event Evento
     */
    @FXML
    private void onDragPanePressed(MouseEvent event) {
        //Guarda la posición inicial del ratón cuando se presiona el botón
        xOffset = event.getSceneX();
        yOffset = event.getSceneY();
    }

    /**
     * Evento para arrastrar el panel en la ventana
     *
     * @param event Evento
     */
    @FXML
    private void onDragPaneDragged(MouseEvent event) {
        //Calcula la nueva posición del AnchorPane mientras se arrastra
        double newX = event.getSceneX() - xOffset;
        double newY = event.getSceneY() - yOffset;

        //Obtiene la ventana principal o el contenedor padre del AnchorPane
        //y ajusta los límites
        panelEditarRolesEstabl.setLayoutX(newX);
        panelEditarRolesEstabl.setLayoutY(newY);

        panelAltaEstabl.setLayoutX(newX);
        panelAltaEstabl.setLayoutY(newY);

        panelDatosPropietario.setLayoutX(newX);
        panelDatosPropietario.setLayoutY(newY);
    }

    /**
     * Establece el nombre del usuario logeado
     *
     * @param usuario El nombre del usuario
     */
    public void setUsuario(String usuario) {

        btnLabelUsuario.setText(usuario);

    }

    /**
     * Método para animar la aparición/desaparición del panel lateral
     *
     * @param panelLateral El panel lateral
     */
    private void togglePanelLateral(AnchorPane panelLateral) {
        TranslateTransition transicion = new TranslateTransition(Duration.seconds(0.3), panelLateral);
        if (panelLateral.isVisible()) {
            //Si el panel lateral está visible, ocultarlo
            transicion.setToX(-panelLateral.getWidth());
            transicion.setOnFinished(event -> panelLateral.setVisible(false));
            disableButtons(false);
        } else {
            //Si el panel lateral está oculto, mostrarlo
            panelLateral.setVisible(true);
            transicion.setToX(0);
            disableButtons(true);
        }
        transicion.play();
    }

    /**
     * Activa o desactiva los botones del panel lateral
     *
     * @param activado Indicador para activar o desactivar los botones del panel
     */
    private void disableButtons(boolean activado) {

        btnSalir.setDisable(activado);
        btnInfoUsuario.setDisable(activado);
        btnAñadirEstabl.setDisable(activado);
        btnAñadirPropietario.setDisable(activado);
        btnHome.setDisable(activado);
        btnModificarProp.setDisable(activado);

    }

    /**
     * Habilita el panel de inicio y actualiza el dashboard
     *
     * @param e Evento
     */
    @FXML
    private void panelInicio(ActionEvent e) {
        labelVentanaActual.setText("Inicio");
        panelInicio.setVisible(true);
        panelVerEstabl.setVisible(false);
        panelAltaUsuario.setVisible(false);
        panelInfoUsuario.setVisible(false);
        panelModificarProp.setVisible(false);

        actualizarDashboard();

    }

    /**
     * Cierra el panel de editar roles y establecimientos sin guardar cambios
     *
     * @param e Evento
     */
    @FXML
    private void cerrarPanelEditarRolesEstabl(ActionEvent e) {
        panelEditarRolesEstabl.setVisible(false);
    }

    /**
     * Actualiza el dashboard con los datos del servidor
     */
    public void actualizarDashboard() {
        //Obtiene del servidor los datos a mostrar en el panel de inicio
        Task<ArrayList<Object>> task = new Task<ArrayList<Object>>() {
            @Override
            protected ArrayList<Object> call() throws Exception {
                Message peticion = new Message("SERVIDOR", "DATOS_SERVIDOR", new ArrayList<Object>());
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

        task.setOnRunning(event -> {

            spinner.setVisible(true);

        });

        task.setOnSucceeded(event -> {
            ArrayList<Object> listaDatos = task.getValue();

            //Establece los datos obtenidos del servidor en los paneles flotantes
            db_nuevosClientes.setText((int) listaDatos.get(0) + "");
            db_ultimoCliente.setText((String) listaDatos.get(1));

            db_clientesConectados.setText((int) listaDatos.get(2) + "");
            db_ipServidor.setText((String) listaDatos.get(3));

            db_totalClientes.setText((long) listaDatos.get(4) + "");
            db_fechaRecarga.setText((String) listaDatos.get(5));

            db_errores.setText((int) listaDatos.get(6) + "");

            spinner.setVisible(false);
        });

        Thread thread = new Thread(task);
        thread.start();
    }

    /**
     * Habilita el panel de alta de establecimientos
     *
     * @param e Evento
     */
    @FXML
    private void panelVerEstabl(ActionEvent e) {
        labelVentanaActual.setText("Listado de Establecimientos");
        panelVerEstabl.setVisible(true);
        panelInicio.setVisible(false);
        panelAltaUsuario.setVisible(false);
        panelInfoUsuario.setVisible(false);
        panelModificarProp.setVisible(false);

        rellenarEstablecimientos(e);

    }

    @FXML
    private void panelAltaEstabl(ActionEvent e) {
        //Abre la ventana usada para dar de alta un nuevo establecimiento, usada también para modificar sus datos
        panelAltaEstabl.setVisible(true);
        btnAltaEstabl.setVisible(true);
        btnModificarEstabl.setVisible(false);
    }

    /**
     * Habilita el panel de alta de usuario
     *
     * @param e Evento
     */
    @FXML
    private void panelAltaUsuario(ActionEvent e) {
        labelVentanaActual.setText("Nuevo Usuario");
        panelAltaUsuario.setVisible(true);
        panelVerEstabl.setVisible(false);
        panelInicio.setVisible(false);
        panelInfoUsuario.setVisible(false);
        panelModificarProp.setVisible(false);

        //Carga los establecimientos disponibles
        Task<ArrayList<String>> task = new Task<ArrayList<String>>() {
            @Override
            protected ArrayList<String> call() throws Exception {
                Message peticion = new Message("ESTABLECIMIENTO", "GET_ESTABLECIMIENTOS", new ArrayList<Object>());
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

        task.setOnRunning(event -> {

            spinner.setVisible(true);

        });

        task.setOnSucceeded(event -> {
            ArrayList<String> listaEstabl = task.getValue();
            listViewEstablecimientos.getItems().clear();
            listViewEstablecimientos.getItems().addAll(listaEstabl);

            spinner.setVisible(false);
        });

        Thread thread = new Thread(task);
        thread.start();

    }

    /**
     * Habilita el panel para ver y modificar los roles y establecimientos de
     * los propietarios
     *
     * @param e Evento
     */
    @FXML
    private void panelModificarProp(ActionEvent e) {
        labelVentanaActual.setText("Listado de propietarios");
        panelModificarProp.setVisible(true);
        panelVerEstabl.setVisible(false);
        panelInicio.setVisible(false);
        panelAltaUsuario.setVisible(false);
        panelInfoUsuario.setVisible(false);
        comboFiltroPropietario.setValue(comboFiltroPropietario.getItems().get(0));

        //Carga los usuarios obtenidos del servidor
        Task<ArrayList<ArrayList<Object>>> task = new Task<ArrayList<ArrayList<Object>>>() {
            @Override
            protected ArrayList<ArrayList<Object>> call() throws Exception {
                ArrayList<Object> data = new ArrayList<Object>();

                Message peticion = new Message("USUARIO", "GET_PROPIETARIOS", data);
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

        task.setOnRunning(event -> {

            spinner.setVisible(true);

        });

        task.setOnSucceeded(event -> {
            usuarios = task.getValue();

            // Agregar las columnas a la tabla
            columNombre.setCellValueFactory(new Callback<CellDataFeatures<List<Object>, Object>, ObservableValue<Object>>() {
                public ObservableValue<Object> call(CellDataFeatures<List<Object>, Object> c) {
                    return new SimpleObjectProperty<Object>(c.getValue().get(0));
                }
            });

            columCorreo.setCellValueFactory(new Callback<CellDataFeatures<List<Object>, Object>, ObservableValue<Object>>() {
                public ObservableValue<Object> call(CellDataFeatures<List<Object>, Object> c) {
                    return new SimpleObjectProperty<Object>(c.getValue().get(1));
                }
            });

            /*
            columPassw.setCellValueFactory(new Callback<CellDataFeatures<List<Object>, Object>, ObservableValue<Object>>() {
                public ObservableValue<Object> call(CellDataFeatures<List<Object>, Object> c) {
                    return new SimpleObjectProperty<Object>(c.getValue().get(2));
                }
            });
             */
            // Asignar las filas a la tabla
            tablaPropietarios.setItems(FXCollections.observableArrayList(usuarios));

            spinner.setVisible(false);

        });

        Thread thread = new Thread(task);
        thread.start();

    }

    /**
     * Habilita el panel de información del usuario logeado
     *
     * @param e Evento
     */
    @FXML
    private void panelInfoUsuario(ActionEvent e) {
        labelVentanaActual.setText("Perfil de Usuario");
        panelInfoUsuario.setVisible(true);
        panelAltaUsuario.setVisible(false);
        panelVerEstabl.setVisible(false);
        panelInicio.setVisible(false);
        panelModificarProp.setVisible(false);

        //Carga la información del usuario
        Task<ArrayList<Object>> task = new Task<ArrayList<Object>>() {
            @Override
            protected ArrayList<Object> call() throws Exception {
                ArrayList<Object> data = new ArrayList<Object>();
                data.add(btnLabelUsuario.getText());
                Message peticion = new Message("USUARIO", "GET_DATOS_USUARIO", data);
                ArrayList<Object> datosUsuario = new ArrayList<Object>();

                try {
                    App.out.writeObject(peticion);
                    Message mensajeRespuesta = (Message) App.in.readObject();

                    datosUsuario.add((String) mensajeRespuesta.getData().get(0));
                    datosUsuario.add((String) mensajeRespuesta.getData().get(1));

                } catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
                return datosUsuario;
            }
        };

        task.setOnRunning(event -> {

            spinner.setVisible(true);

        });

        task.setOnSucceeded(event -> {
            ArrayList<Object> datosUsuario = task.getValue();
            nombreUsuarioInfo.setText((String) datosUsuario.get(0));
            correoUsuarioInfo.setText((String) datosUsuario.get(1));
            //passwUsuarioInfo.setText((String) datosUsuario.get(2));
            //passwUsuarioModificar = (String) datosUsuario.get(2);

            spinner.setVisible(false);

        });

        Thread thread = new Thread(task);
        thread.start();

    }

    /**
     * Habilita el panel para editar roles y establecimientos del usuario
     * seleccionado
     *
     * @param usuario El propietario seleccionado en la tabla de propietarios
     */
    public void cargarRolesEstabl(String usuario) {

        panelEditarRolesEstabl.setVisible(true);

        //Carga los roles y establecimientos del usuario, y los disponibles
        Task<ArrayList<Object>> task = new Task<ArrayList<Object>>() {
            @Override
            protected ArrayList<Object> call() throws Exception {
                ArrayList<Object> data = new ArrayList<>();
                data.add(usuario);

                Message peticionDatosUsuario = new Message("USUARIO", "GET_DATOS_USUARIO", data);
                Message peticionEstablecimientos = new Message("ESTABLECIMIENTO", "GET_ESTABLECIMIENTOS", new ArrayList<>());
                Message peticionRoles = new Message("ROL", "GET_ROLES", new ArrayList<>());

                ArrayList<Object> datosRolesEstablecimientos = new ArrayList<>();

                ArrayList<String> listaRolesDisponibles = new ArrayList<String>();
                ArrayList<String> listaRolesUsuario = new ArrayList<String>();
                ArrayList<String> listaEstablDisponibles = new ArrayList<String>();
                ArrayList<String> listaEstablUsuario = new ArrayList<String>();
                try {
                    App.out.writeObject(peticionDatosUsuario);
                    Message mensajeRespuestaDatosUsuario = (Message) App.in.readObject();
                    listaRolesUsuario = (ArrayList<String>) mensajeRespuestaDatosUsuario.getData().get(3);//Set de Roles
                    listaEstablUsuario = (ArrayList<String>) mensajeRespuestaDatosUsuario.getData().get(4);//Set de Establecimiento

                    App.out.writeObject(peticionEstablecimientos);
                    Message mensajeRespuestaEstablecimientos = (Message) App.in.readObject();
                    listaEstablDisponibles = (ArrayList<String>) mensajeRespuestaEstablecimientos.getData().get(0);

                    App.out.writeObject(peticionRoles);
                    Message mensajeRespuestaRoles = (Message) App.in.readObject();
                    listaRolesDisponibles = (ArrayList<String>) mensajeRespuestaRoles.getData().get(0);

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

        task.setOnRunning(event -> {

            spinner.setVisible(true);

        });

        task.setOnSucceeded(event -> {
            ArrayList<Object> datosRolesEstablecimientos = task.getValue();
            rolesDisponibles.getItems().clear();
            rolesUsuarioInfo.getItems().clear();
            establDisponibles.getItems().clear();
            establUsuarioInfo.getItems().clear();

            rolesDisponibles.getItems().addAll((ArrayList<String>) datosRolesEstablecimientos.get(0));
            rolesUsuarioInfo.getItems().addAll((ArrayList<String>) datosRolesEstablecimientos.get(1));
            establDisponibles.getItems().addAll((ArrayList<String>) datosRolesEstablecimientos.get(2));
            establUsuarioInfo.getItems().addAll((ArrayList<String>) datosRolesEstablecimientos.get(3));

            spinner.setVisible(false);

        });

        Thread thread = new Thread(task);
        thread.start();

    }

    /**
     * Cierra el panel de edición de roles y establecimientos, y actualiza los
     * datos nuevos
     *
     * @param e Evento
     */
    @FXML
    public void cerrarEditarRoles(ActionEvent e) {
        panelEditarRolesEstabl.setVisible(false);

        //Envía al servidor el nuevo listado de roles y establecimientos para el propietario
        Task<Boolean> task = new Task<Boolean>() {
            @Override
            protected Boolean call() throws Exception {
                ArrayList<Object> data = new ArrayList<>();
                data.add(usuarioCargado);

                ArrayList<String> listaRolesUsuario = new ArrayList<String>(rolesUsuarioInfo.getItems());
                ArrayList<String> listaEstablUsuario = new ArrayList<String>(establUsuarioInfo.getItems());
                data.add(listaRolesUsuario);
                data.add(listaEstablUsuario);

                Message peticion = new Message("USUARIO", "ACTUALIZAR_ROLES_ESTABL", data);

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

        task.setOnRunning(event -> {

            spinner.setVisible(true);

        });

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

            spinner.setVisible(false);
        });

        Thread thread = new Thread(task);
        thread.start();
    }

    /**
     * Añade un rol del listado al propietario, comprobando que no lo tenga ya
     * asignado
     *
     * @param e Evento
     */
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

    /**
     * Quita un rol del listado al propietario, comprobando que no pueda
     * quedarse sin rol
     *
     * @param e
     */
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

    /**
     * Añade un establecimiento del listado al propietario, comprobando que no
     * lo tenga ya asignado
     *
     * @param e
     */
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

    /**
     * Quita el establecimiento del listado al propietario, comprobando que no
     * se quede sin ningún establecimiento
     *
     * @param e
     */
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

    /**
     * Actualiza los datos del usuario en la BDD. Solo actualiza usuario, correo
     * o contraseña
     *
     * @param e
     */
    @FXML
    private void actualizarDatosUsuario(ActionEvent e) {

        //Solo envía los datos si se rellena la contraseña
        if (!passwUsuarioInfo.getText().isEmpty()) {

            //Envía los nuevos datos al servidor
            Task<Boolean> task = new Task<Boolean>() {
                @Override
                protected Boolean call() throws Exception {

                    ArrayList<Object> nuevosDatos = new ArrayList<Object>();
                    nuevosDatos.add(btnLabelUsuario.getText());
                    nuevosDatos.add(nombreUsuarioInfo.getText());
                    nuevosDatos.add(correoUsuarioInfo.getText());
                    nuevosDatos.add(SHA.encrypt(passwUsuarioInfo.getText()));

                    Message peticion = new Message("USUARIO", "ACTUALIZAR_DATOS", nuevosDatos);
                    boolean respuesta = false;
                    try {
                        App.out.writeObject(peticion);
                        Message mensajeRespuesta = (Message) App.in.readObject();
                        respuesta = (boolean) mensajeRespuesta.getData().get(0);

                        if (respuesta) {
                            Platform.runLater(() -> btnLabelUsuario.setText(nombreUsuarioInfo.getText()));
                            
                        }

                    } catch (IOException ex) {
                        Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    return respuesta;
                }
            };

            task.setOnRunning(event -> {

                spinner.setVisible(true);
                panelInfoUsuario.setDisable(true);

            });

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

                spinner.setVisible(false);
                panelInfoUsuario.setDisable(false);

            });

            Thread thread = new Thread(task);
            thread.start();

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Error al modificar los datos");
            alert.setContentText("Debe introducir una contraseña.");
            alert.showAndWait();
        }

    }

    /**
     * Rellena el ListView con las direcciones de los establecimientos que
     * coinciden con el nombre introducido
     *
     * @param e Evento
     */
    @FXML
    public void buscarDireccion(ActionEvent e) {

        //Utiliza la api de Google para obtener los datos del establecimiento
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
                        //Clave de API de Google
                        GooglePlaces client = new GooglePlaces("AIzaSyAaP6rsOVudwTFDitm7dKUbSJpTBabVyFU");

                        //Consulta a la API por nombre
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

        task.setOnRunning(event -> {

            spinner.setVisible(true);
            panelAltaEstabl.setDisable(true);

        });

        task.setOnSucceeded(event -> {

            //Añade los objetos Place al listado en memoria, para mostrarlos en el listview
            ArrayList<String> direcciones = new ArrayList<String>();

            for (Place place : places) {

                direcciones.add(place.getAddress());

            }

            listViewDirecciones.getItems().clear();
            listViewDirecciones.getItems().addAll(direcciones);
            spinner.setVisible(false);
            panelAltaEstabl.setDisable(false);

        });

        Thread thread = new Thread(task);
        thread.start();

    }

    /**
     * Añade un nuevo establecimiento a la BDD
     *
     * @param e Evento
     */
    @FXML
    public void añadirNuevoEstabl(ActionEvent e) {

        //Comprueba que los campos no estén vacíos
        if (nombreEstabl.getText().isEmpty() || direccionEstabl.getText().isEmpty() || latitudEstabl.getText().isEmpty()
                || longitudEstabl.getText().isEmpty()) {
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
                    data.add(direccionEstabl.getText()); //Rellenado por el objeto Place obtenido de la consulta a la API
                    data.add(latitudEstabl.getText()); //Rellenado por el objeto Place obtenido de la consulta a la API
                    data.add(longitudEstabl.getText()); //Rellenado por el objeto Place obtenido de la consulta a la API
                    Message peticion = new Message("ESTABLECIMIENTO", "INSERTAR", data);
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

            task.setOnRunning(event -> {

                spinner.setVisible(true);
                panelAltaEstabl.setDisable(true);

            });

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
                    latitudEstabl.setText("");
                    longitudEstabl.setText("");
                    listViewDirecciones.getItems().clear();

                    cerrarVentanaEstabl(e);
                    rellenarEstablecimientos(e);

                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Información");
                    alert.setHeaderText("ERROR");
                    alert.setContentText("El correo introducido ya existe");
                    alert.showAndWait();
                }

                spinner.setVisible(false);
                panelAltaEstabl.setDisable(false);

            });

            Thread thread = new Thread(task);
            thread.start();
        }

    }

    /**
     * Añade un nuevo usuario propietario a la BDD
     *
     * @param e Evento
     */
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
                    data.add(SHA.encrypt(passwUsuario.getText()));
                    data.add(roles);
                    data.add(establecimientos);

                    Message peticion = new Message("USUARIO", "INSERTAR", data);
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

            task.setOnRunning(event -> {

                spinner.setVisible(true);
                panelAltaUsuario.setDisable(true);

            });

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

                spinner.setVisible(false);
                panelAltaUsuario.setDisable(false);

            });

            Thread thread = new Thread(task);
            thread.start();
        }

    }

    /**
     * Envía al servidor el nombre del usuario que cierra sesión, y vuelve a la
     * pantalla de login
     *
     * @param e Evento
     */
    @FXML
    public void logout(ActionEvent e) {

        Task<Boolean> task = new Task<Boolean>() {
            @Override
            protected Boolean call() throws Exception {
                ArrayList<Object> data = new ArrayList<Object>();
                data.add(btnLabelUsuario.getText());

                Message peticion = new Message("SERVIDOR", "LOGOUT", data);
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
                    stage.initStyle(StageStyle.UNDECORATED);
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

    private void buscarPropietario(String textoFiltro) {

        // Crear un Predicate que filtre las filas según el nombre
        Predicate<List<Object>> filtro = fila -> {

            String campo = "";
            switch (comboFiltroPropietario.getValue()) {
                case "Nombre":
                    campo = fila.get(0).toString();
                    break;
                case "Correo":
                    campo = fila.get(1).toString();
                    break;
            }

            return campo.contains(textoFiltro); // Verificar si el nombre contiene el texto de filtro
        };

        // Aplicar el filtro a la lista de datos
        List<List<Object>> datosFiltrados = usuarios.stream()
                .filter(filtro)
                .collect(Collectors.toList());

        // Actualizar la tabla con los datos filtrados
        tablaPropietarios.setItems(FXCollections.observableArrayList(datosFiltrados));
    }

    private void buscarEstablecimiento(String textoFiltro) {

        // Crear un Predicate que filtre las filas según el nombre
        Predicate<List<Object>> filtro = fila -> {

            String campo = "";
            switch (comboFiltroPropietario.getValue()) {
                case "Nombre":
                    campo = fila.get(0).toString();
                    break;
                case "Dirección":
                    campo = fila.get(1).toString();
                    break;
            }

            return campo.contains(textoFiltro); // Verificar si el nombre contiene el texto de filtro
        };

        // Aplicar el filtro a la lista de datos
        List<List<Object>> datosFiltrados = establecimientos.stream()
                .filter(filtro)
                .collect(Collectors.toList());

        // Actualizar la tabla con los datos filtrados
        tablaEstabl.setItems(FXCollections.observableArrayList(datosFiltrados));
    }

    @FXML
    public void verRegistroErrores(ActionEvent e) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("verRegistroErrores.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            VerRegistroErroresController controller = fxmlLoader.<VerRegistroErroresController>getController();
            //controller.setUsuario(btnLabelUsuario.getText());

            Stage stage = new Stage();
            stage.setTitle("QuickTap - Registro de errores");
            stage.setScene(new Scene(root1, 284, 400));
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public void modificarEstabl(String establecimiento) {

        //Abre la ventana usada para dar de alta un nuevo establecimiento, usada también para modificar sus datos
        panelAltaEstabl.setVisible(true);
        btnAltaEstabl.setVisible(false);
        btnModificarEstabl.setVisible(true);

        //Carga la información del establecimiento
        Task<ArrayList<Object>> task = new Task<ArrayList<Object>>() {
            @Override
            protected ArrayList<Object> call() throws Exception {
                ArrayList<Object> data1 = new ArrayList<Object>();

                //Datos para buscar el establecimiento
                data1.add(establecimiento);

                Message peticion = new Message("ESTABLECIMIENTO", "GET_DATOS_ESTABLECIMIENTO", data1);

                ArrayList<Object> datosEstablecimiento = new ArrayList<>();

                try {
                    App.out.writeObject(peticion);
                    Message mensajeRespuesta = (Message) App.in.readObject();

                    datosEstablecimiento = new ArrayList<>(mensajeRespuesta.getData());

                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                return datosEstablecimiento;
            }
        };

        task.setOnRunning(event -> {

            spinner.setVisible(true);

        });

        task.setOnSucceeded(event -> {
            ArrayList<Object> datosEstablecimiento = task.getValue();

            nombreEstabl.setText((String) datosEstablecimiento.get(0));
            direccionEstabl.setText((String) datosEstablecimiento.get(1));
            double latitud = (double) datosEstablecimiento.get(2);
            double longitud = (double) datosEstablecimiento.get(3);
            latitudEstabl.setText(latitud + "");
            longitudEstabl.setText(longitud + "");

            spinner.setVisible(false);

        });

        Thread thread = new Thread(task);
        thread.start();

    }

    public void modificarPropietario(String propietario) {

        panelDatosPropietario.setVisible(true);

        //Carga la información del propietario
        Task<ArrayList<Object>> task = new Task<ArrayList<Object>>() {
            @Override
            protected ArrayList<Object> call() throws Exception {
                ArrayList<Object> data1 = new ArrayList<Object>();

                //Datos para buscar el propietario
                data1.add(propietario);

                Message peticion = new Message("USUARIO", "GET_DATOS_USUARIO", data1);

                ArrayList<Object> datosUsuario = new ArrayList<>();

                try {
                    App.out.writeObject(peticion);
                    Message mensajeRespuesta = (Message) App.in.readObject();

                    datosUsuario = new ArrayList<>(mensajeRespuesta.getData());

                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                return datosUsuario;
            }
        };

        task.setOnRunning(event -> {

            spinner.setVisible(true);

        });

        task.setOnSucceeded(event -> {
            ArrayList<Object> datosUsuario = task.getValue();

            nombreUsuarioModificar.setText((String) datosUsuario.get(0));
            correoUsuarioModificar.setText((String) datosUsuario.get(1));
            passwUsuarioModificarAntigua = (String) datosUsuario.get(2); //Se guarda la contraseña original del usuario

            spinner.setVisible(false);

        });

        Thread thread = new Thread(task);
        thread.start();

    }

    /**
     * Actualiza los datos de un establecimiento en la BDD
     *
     * @param e Evento
     */
    @FXML
    private void actualizarDatosEstabl(ActionEvent e) {

        //Envía los nuevos datos al servidor
        Task<Boolean> task = new Task<Boolean>() {
            @Override
            protected Boolean call() throws Exception {

                ArrayList<Object> nuevosDatos = new ArrayList<Object>();

                nuevosDatos.add(establCargado);

                nuevosDatos.add(nombreEstabl.getText());
                nuevosDatos.add(direccionEstabl.getText());
                nuevosDatos.add(latitudEstabl.getText());
                nuevosDatos.add(longitudEstabl.getText());

                Message peticion = new Message("ESTABLECIMIENTO", "ACTUALIZAR_DATOS_ESTABLECIMIENTO", nuevosDatos);
                boolean respuesta = false;
                try {
                    App.out.writeObject(peticion);
                    Message mensajeRespuesta = (Message) App.in.readObject();
                    respuesta = (boolean) mensajeRespuesta.getData().get(0);

                    if (respuesta) {
                        cerrarVentanaEstabl(e);
                        rellenarEstablecimientos(e);

                    }

                } catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
                return respuesta;
            }
        };

        task.setOnRunning(event -> {

            spinner.setVisible(true);

        });

        task.setOnSucceeded(event -> {
            boolean respuesta = task.getValue();

            if (respuesta) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Información");
                alert.setHeaderText("Correcto");
                alert.setContentText("Datos actualizados.");
                alert.showAndWait();
                nombreEstabl.setText("");
                direccionEstabl.setText("");
                latitudEstabl.setText("");
                longitudEstabl.setText("");
                listViewDirecciones.getItems().clear();

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText("Error al modificar los datos");
                alert.setContentText("Revise los datos introducidos.");
                alert.showAndWait();
            }

            spinner.setVisible(false);

        });

        Thread thread = new Thread(task);
        thread.start();

    }

    @FXML
    public void cerrarVentanaEstabl(ActionEvent e) {

        nombreEstabl.setText("");
        direccionEstabl.setText("");
        latitudEstabl.setText("");
        longitudEstabl.setText("");

        listViewDirecciones.getItems().clear();

        panelAltaEstabl.setVisible(false);

    }

    /**
     * Rellena la tabla de establecimientos
     *
     * @param e Evento
     */
    @FXML
    public void rellenarEstablecimientos(ActionEvent e) {

        Task<ArrayList<ArrayList<Object>>> task = new Task<ArrayList<ArrayList<Object>>>() {
            @Override
            protected ArrayList<ArrayList<Object>> call() throws Exception {
                ArrayList<Object> data = new ArrayList<Object>();

                Message peticion = new Message("ESTABLECIMIENTO", "GET_DATOS_ESTABLECIMIENTOS", data);

                ArrayList<ArrayList<Object>> productos = new ArrayList<>();
                try {
                    App.out.writeObject(peticion);
                    Message mensajeRespuesta = (Message) App.in.readObject();

                    ArrayList<Object> datosRecibidos = (ArrayList<Object>) mensajeRespuesta.getData();

                    for (Object listado : datosRecibidos) {
                        productos.add((ArrayList<Object>) listado);
                    }

                } catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
                return productos;
            }
        };

        task.setOnRunning(event -> {

            spinner.setVisible(true);

        });

        task.setOnSucceeded(event -> {
            establecimientos = task.getValue();

            // Agregar las columnas a la tabla
            columNombreEstabl.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<Object>, Object>, ObservableValue<Object>>() {
                public ObservableValue<Object> call(TableColumn.CellDataFeatures<List<Object>, Object> c) {
                    return new SimpleObjectProperty<Object>(c.getValue().get(0));
                }
            });

            columDireccEstabl.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<Object>, Object>, ObservableValue<Object>>() {
                public ObservableValue<Object> call(TableColumn.CellDataFeatures<List<Object>, Object> c) {
                    return new SimpleObjectProperty<Object>(c.getValue().get(1));
                }
            });

            // Asignar las filas a la tabla
            tablaEstabl.setItems(FXCollections.observableArrayList(establecimientos));

            spinner.setVisible(false);
        });

        Thread thread = new Thread(task);
        thread.start();

    }

    /**
     * Borra un producto de la BDD
     *
     * @param producto El nombre del producto a borrar
     * @param establecimiento El establecimiento al que pertenece el producto
     */
    private void borrarEstabl(String establecimiento) {

        //Envía los nuevos datos al servidor
        Task<Boolean> task = new Task<Boolean>() {
            @Override
            protected Boolean call() throws Exception {

                ArrayList<Object> data = new ArrayList<Object>();

                data.add(establecimiento);

                Message peticion = new Message("ESTABLECIMIENTO", "BORRAR", data);
                boolean respuesta = false;
                try {
                    App.out.writeObject(peticion);
                    Message mensajeRespuesta = (Message) App.in.readObject();
                    respuesta = (boolean) mensajeRespuesta.getData().get(0);

                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                return respuesta;
            }
        };

        task.setOnRunning(event -> {

            spinner.setVisible(true);

        });

        task.setOnSucceeded(event -> {
            boolean respuesta = task.getValue();

            if (respuesta) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Información");
                alert.setHeaderText("Correcto");
                alert.setContentText("Establecimiento eliminado.");
                alert.showAndWait();
                rellenarEstablecimientos(null);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText("Error al eliminar el establecimiento");
                alert.setContentText("Compruebe que no tenga productos, pedidos o categorías asociadas.");
                alert.showAndWait();
            }

            spinner.setVisible(false);

        });

        Thread thread = new Thread(task);
        thread.start();

    }

    /**
     * Actualiza los datos de un trabajador en la BDD
     *
     * @param e Evento
     */
    @FXML
    private void actualizarDatosPropietario(ActionEvent e) {

        //Comprueba que los campos no estén vacíos
        if (nombreUsuarioModificar.getText().isEmpty() || correoUsuarioModificar.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Información");
            alert.setHeaderText("ERROR");
            alert.setContentText("Debe rellenar todos los campos");
            alert.showAndWait();

        } else {
            //Envía los nuevos datos al servidor
            Task<Boolean> task = new Task<Boolean>() {
                @Override
                protected Boolean call() throws Exception {

                    ArrayList<Object> nuevosDatos = new ArrayList<Object>();

                    nuevosDatos.add(propietarioCargado);

                    nuevosDatos.add(nombreUsuarioModificar.getText());
                    nuevosDatos.add(correoUsuarioModificar.getText());

                    //Comprueba si se ha modificado la contraseña. Si no se introduce una, se envía la contraseña original almacenada en memoria
                    if (passwUsuarioModificar.getText().isEmpty()) {
                        nuevosDatos.add(passwUsuarioModificarAntigua);
                    } //Si se modifica, envía la nueva contraseña encriptada
                    else {
                        nuevosDatos.add(SHA.encrypt(passwUsuarioModificar.getText()));
                    }

                    Message peticion = new Message("USUARIO", "ACTUALIZAR_DATOS", nuevosDatos);
                    boolean respuesta = false;
                    try {
                        App.out.writeObject(peticion);
                        Message mensajeRespuesta = (Message) App.in.readObject();
                        respuesta = (boolean) mensajeRespuesta.getData().get(0);

                        if (respuesta) {
                            cerrarVentanaPropietario(e);
                            panelModificarProp(e);
                        }

                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    return respuesta;
                }
            };

            task.setOnRunning(event -> {

                spinner.setVisible(true);

            });

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

                spinner.setVisible(false);

            });

            Thread thread = new Thread(task);
            thread.start();
        }

    }

    @FXML
    public void cerrarVentanaPropietario(ActionEvent e) {

        nombreUsuarioModificar.setText("");
        correoUsuarioModificar.setText("");
        passwUsuarioModificar.setText("");

        panelDatosPropietario.setVisible(false);

    }

    /**
     * Borra un propietario de la BDD
     *
     * @param propietario El nombre del propietario a borrar
     */
    private void borrarPropietario(String propietario) {

        //Envía los nuevos datos al servidor
        Task<Boolean> task = new Task<Boolean>() {
            @Override
            protected Boolean call() throws Exception {

                ArrayList<Object> data = new ArrayList<Object>();

                data.add(propietario);

                Message peticion = new Message("USUARIO", "BORRAR", data);
                boolean respuesta = false;
                try {
                    App.out.writeObject(peticion);
                    Message mensajeRespuesta = (Message) App.in.readObject();
                    respuesta = (boolean) mensajeRespuesta.getData().get(0);

                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                return respuesta;
            }
        };

        task.setOnRunning(event -> {

            spinner.setVisible(true);

        });

        task.setOnSucceeded(event -> {
            boolean respuesta = task.getValue();

            if (respuesta) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Información");
                alert.setHeaderText("Correcto");
                alert.setContentText("Propietario dado de baja.");
                alert.showAndWait();
                panelModificarProp(null);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText("Error al dar de baja al propietario");
                alert.showAndWait();
            }

            spinner.setVisible(false);

        });

        Thread thread = new Thread(task);
        thread.start();

    }

}
