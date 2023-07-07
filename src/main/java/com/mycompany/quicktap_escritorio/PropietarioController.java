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
import java.net.URISyntaxException;
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
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
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
public class PropietarioController implements Initializable {

    FileChooser fileChooser = new FileChooser();

    File imagenSeleccionada;

    @FXML
    private ImageView burger;

    @FXML
    private AnchorPane panelMenu;

    @FXML
    private MFXButton btnAltaCategoria;

    @FXML
    private MFXButton btnAñadirProducto;

    @FXML
    private MFXButton btnAñadirTrabajador;

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
    private AnchorPane panelAltaProducto;

    @FXML
    private AnchorPane panelAltaCategoria;

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
    private AnchorPane panelVerProducto;

    @FXML
    private ListView<String> listViewEstablecimientos;

    @FXML
    private ListView<String> listViewCategSeleccionadas;

    @FXML
    private AnchorPane panelInfoUsuario;

    @FXML
    private MFXTextField nombreUsuarioInfo;

    @FXML
    private MFXPasswordField passwUsuarioInfo;

    @FXML
    private MFXTextField correoUsuarioInfo;

    @FXML
    private AnchorPane panelEditarRoles;

    @FXML
    private ListView<String> rolesUsuarioInfo;

    @FXML
    private ListView<String> rolesDisponibles;

    @FXML
    private AnchorPane panelEditarEstabl;

    @FXML
    private ListView<String> establDisponibles;

    @FXML
    private ListView<String> establUsuarioInfo;

    @FXML
    private ListView<String> listViewEstablCategoria;

    @FXML
    private MFXTextField nombreCategoria;

    @FXML
    private MFXTextField descripcionCategoria;

    @FXML
    private MFXTextField nombreProducto;

    @FXML
    private MFXTextField descripcionProducto;

    @FXML
    private MFXTextField precioProducto;

    @FXML
    private MFXTextField stockProducto;

    @FXML
    private ImageView imagenProducto;

    @FXML
    private ListView<String> listViewCategorias;

    @FXML
    private Label db_cajaHoy;

    @FXML
    private Label db_fechaRecarga;

    @FXML
    private Label db_numPagos;

    @FXML
    private Label db_pedidosFin;

    @FXML
    private Label db_pedidosPen;

    @FXML
    private Label db_totalPedidos;

    @FXML
    private Label db_totalTrabajadores;

    @FXML
    private Label db_trabajadoresOn;

    @FXML
    private MFXFilterComboBox<String> comboSeleccionarEstabl;

    @FXML
    private MFXFilterComboBox<String> comboSeleccionarEstablListado;

    @FXML
    private TableView tablaProductos;

    @FXML
    private TableColumn<List<Object>, Object> columDescripcionProd;

    @FXML
    private TableColumn<List<Object>, Object> columNombreProd;

    @FXML
    private TableColumn<List<Object>, Object> columPrecioProd;

    @FXML
    private TableColumn<List<Object>, Object> columStockProd;

    private String productoCargado;

    @FXML
    private TableColumn<List<Object>, Object> columPedido;

    @FXML
    private TableColumn<List<Object>, Object> columCliente;

    @FXML
    private TableColumn<List<Object>, Object> columProducto;

    @FXML
    private TableColumn<List<Object>, Object> columCantidad;

    @FXML
    private TableColumn<List<Object>, Object> columEstado;

    @FXML
    private TableColumn<List<Object>, Object> columCategorias;

    @FXML
    private MFXFilterComboBox<String> comboSeleccionarEstablPedidos;

    @FXML
    private MFXFilterComboBox<String> comboSeleccionarEstablTrabajador;

    @FXML
    private AnchorPane panelTrabajadores;

    @FXML
    private AnchorPane panelModificarCategoria;

    @FXML
    private TableView tablaTrabajadores;

    @FXML
    private TableColumn<List<Object>, Object> columNombreTrabaj;

    @FXML
    private TableColumn<List<Object>, Object> columCorreoTrabaj;

    //@FXML
    //private TableColumn<List<Object>, Object> columPasswTrabaj;
    private String trabajadorCargado;
    private String passwTrabajadorModificar; //Contraseña temporal trabajador
    private String categoriaCargada;

    @FXML
    private ListView<String> listViewEstablecimientosTrabajador;

    @FXML
    private Label labelAltaProducto;

    @FXML
    private MFXButton btnAltaProducto;

    @FXML
    private MFXButton btnModificarProducto;

    @FXML
    private MFXButton btnAltaTrabajador;

    @FXML
    private MFXButton btnModificarTrabajador;

    @FXML
    private MFXTextField campoBuscarProducto;

    @FXML
    private MFXFilterComboBox<String> comboFiltroProducto;

    @FXML
    private MFXTextField campoBuscarTrabajador;

    @FXML
    private MFXFilterComboBox<String> comboFiltroTrabajador;

    private ArrayList<ArrayList<Object>> productos;
    private ArrayList<ArrayList<Object>> trabajadores;

    @FXML
    private ListView<String> listViewCategoriasDisponibles;

    @FXML
    private MFXTextField nombreCategoriaModificar;

    @FXML
    private MFXTextField descripcionCategoriaModificar;

    @FXML
    private MFXFilterComboBox<String> comboSeleccionarEstablCategoria;

    @FXML
    private MFXProgressSpinner spinner;

    //Menú contextual al pulsar click derecho sobre un producto, trabajador o categoría
    private ContextMenu contextMenuProductos = new ContextMenu();
    private ContextMenu contextMenuTrabajadores = new ContextMenu();
    private ContextMenu contextMenuCategorias = new ContextMenu();

    private double xOffset = 0;
    private double yOffset = 0;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //Crea un filtro para archivos PNG, JPG y JPEG
        FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter(
                "Archivos de imagen", "*.png", "*.jpg", "*.jpeg");
        fileChooser.getExtensionFilters().add(imageFilter);

        //Rellena los combobox de los filtros
        comboFiltroProducto.getItems().addAll("Nombre", "Descripción", "Precio", "Stock", "Categorías");
        comboFiltroTrabajador.getItems().addAll("Nombre", "Correo");

        spinner.setVisible(false); //Spinner de carga

        //Añade el evento drag and drop a los paneles de producto, categoría y usuario
        panelAltaProducto.setOnMousePressed(this::onDragPanePressed);
        panelAltaProducto.setOnMouseDragged(this::onDragPaneDragged);

        panelAltaUsuario.setOnMousePressed(this::onDragPanePressed);
        panelAltaUsuario.setOnMouseDragged(this::onDragPaneDragged);

        panelModificarCategoria.setOnMousePressed(this::onDragPanePressed);
        panelModificarCategoria.setOnMouseDragged(this::onDragPaneDragged);

        //TABLA PRODUCTOS
        MenuItem editarProducto = new MenuItem("Editar");
        editarProducto.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Obtener la fila seleccionada
                ArrayList<Object> filaSeleccionada = (ArrayList<Object>) tablaProductos.getSelectionModel().getSelectedItem();

                // Verificar si hay un usuario seleccionado
                if (filaSeleccionada != null) {
                    //Carga los roles y establecimientos para ser editados
                    productoCargado = (String) filaSeleccionada.get(0);
                    modificarProducto(productoCargado);
                }
            }
        });

        MenuItem borrarProducto = new MenuItem("Borrar");
        borrarProducto.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Obtener la fila seleccionada
                ArrayList<Object> filaSeleccionada = (ArrayList<Object>) tablaProductos.getSelectionModel().getSelectedItem();

                // Verificar si hay un usuario seleccionado
                if (filaSeleccionada != null) {
                    productoCargado = (String) filaSeleccionada.get(0);
                    borrarProducto(productoCargado, comboSeleccionarEstablListado.getValue());
                }
            }
        });

        contextMenuProductos.getItems().addAll(editarProducto, borrarProducto);

        tablaProductos.setContextMenu(contextMenuProductos);

        tablaProductos.setRowFactory(tv -> {
            TableRow row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getButton() == MouseButton.SECONDARY && (!row.isEmpty())) {
                    contextMenuProductos.show(tablaProductos, event.getScreenX(), event.getScreenY());
                }
            });
            return row;
        });

        //TABLA TRABAJADORES
        MenuItem editarTrabajador = new MenuItem("Editar");
        editarTrabajador.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Obtener la fila seleccionada
                ArrayList<Object> filaSeleccionada = (ArrayList<Object>) tablaTrabajadores.getSelectionModel().getSelectedItem();

                // Verificar si hay un usuario seleccionado
                if (filaSeleccionada != null) {
                    //Carga los roles y establecimientos para ser editados
                    trabajadorCargado = (String) filaSeleccionada.get(0);
                    modificarTrabajador(trabajadorCargado);
                }
            }
        });

        MenuItem borrarTrabajador = new MenuItem("Borrar");
        borrarTrabajador.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Obtener la fila seleccionada
                ArrayList<Object> filaSeleccionada = (ArrayList<Object>) tablaTrabajadores.getSelectionModel().getSelectedItem();

                // Verificar si hay un usuario seleccionado
                if (filaSeleccionada != null) {
                    trabajadorCargado = (String) filaSeleccionada.get(0);
                    borrarTrabajador(trabajadorCargado);
                }
            }
        });

        contextMenuTrabajadores.getItems().addAll(editarTrabajador, borrarTrabajador);

        tablaTrabajadores.setContextMenu(contextMenuTrabajadores);

        tablaTrabajadores.setRowFactory(tv -> {
            TableRow row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getButton() == MouseButton.SECONDARY && (!row.isEmpty())) {
                    contextMenuTrabajadores.show(tablaTrabajadores, event.getScreenX(), event.getScreenY());
                }
            });
            return row;
        });

        //LISTA CATEGORÍAS
        MenuItem modificarCategoria = new MenuItem("Editar");
        modificarCategoria.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Obtener la fila seleccionada
                String filaSeleccionada = listViewCategoriasDisponibles.getSelectionModel().getSelectedItem();

                // Verificar si hay una categoría seleccionada
                if (filaSeleccionada != null) {
                    categoriaCargada = filaSeleccionada;
                    modificarCategoria(categoriaCargada);
                }
            }
        });
        MenuItem borrarCategoria = new MenuItem("Borrar");
        borrarCategoria.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Obtener la fila seleccionada
                String filaSeleccionada = listViewCategoriasDisponibles.getSelectionModel().getSelectedItem();

                // Verificar si hay una categoría seleccionada
                if (filaSeleccionada != null) {
                    categoriaCargada = filaSeleccionada;
                    borrarCategoria(categoriaCargada, comboSeleccionarEstablCategoria.getValue());
                }
            }
        });

        contextMenuCategorias.getItems().addAll(modificarCategoria, borrarCategoria);

        listViewCategoriasDisponibles.setContextMenu(contextMenuTrabajadores);

        listViewCategoriasDisponibles.setOnContextMenuRequested(event -> {
            contextMenuCategorias.show(listViewCategoriasDisponibles, event.getScreenX(), event.getScreenY());
            event.consume();
        });

        btnMenu.setOnMouseClicked(event -> togglePanelLateral(panelMenu)); //Habilita el panel lateral

        actualizarDashboard(); //Actualiza el dashboard al inicio

        campoBuscarProducto.textProperty().addListener((observable, oldValue, newValue) -> {

            buscarProducto(newValue);

        });

        campoBuscarTrabajador.textProperty().addListener((observable, oldValue, newValue) -> {

            buscarTrabajador(newValue);

        });

    }

    /**
     * Evento al hacer click sobre un panel
     *
     * @param event Evento
     */
    @FXML
    private void onDragPanePressed(MouseEvent event) {
        // Guardar la posición inicial del ratón cuando se presiona el botón
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
        //y ajustar los límites
        panelAltaProducto.setLayoutX(newX);
        panelAltaProducto.setLayoutY(newY);

        panelAltaUsuario.setLayoutX(newX);
        panelAltaUsuario.setLayoutY(newY);

        panelModificarCategoria.setLayoutX(newX);
        panelModificarCategoria.setLayoutY(newY);
    }

    /**
     * Carga una imagen seleccionada en el filechooser, en el atributo imagen, y
     * la establece como imagen del producto
     */
    @FXML
    public void cargarImagen() {

        imagenSeleccionada = fileChooser.showOpenDialog(new Stage());

        // Si no se selecciona una imagen, se utiliza una por defecto
        if (imagenSeleccionada == null) {
            InputStream defaultImageStream = getClass().getResourceAsStream("/no-image.png");
            if (defaultImageStream != null) {
                try {
                    // Crear un archivo temporal para la imagen por defecto
                    File tempFile = File.createTempFile("default-image", ".png");
                    tempFile.deleteOnExit();

                    // Copiar el contenido del flujo de entrada al archivo temporal
                    Files.copy(defaultImageStream, tempFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

                    // Utilizar el archivo temporal como imagen por defecto
                    imagenSeleccionada = tempFile;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        String imagePath = imagenSeleccionada.toURI().toString();
        Image image = new Image(imagePath);

        imagenProducto.setImage(image);

    }

    /**
     * Establece el nombre del usuario logeado
     *
     * @param usuario El usuario que logea
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
        btnAñadirProducto.setDisable(activado);
        btnAñadirTrabajador.setDisable(activado);
        btnHome.setDisable(activado);
        btnAltaCategoria.setDisable(activado);

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
        panelVerProducto.setVisible(false);
        panelTrabajadores.setVisible(false);
        panelInfoUsuario.setVisible(false);
        panelAltaCategoria.setVisible(false);

        actualizarDashboard();

    }

    /**
     * Actualiza el dashboard con los datos del servidor
     */
    public void actualizarDashboard() {
        //Obtiene del servidor los datos a mostrar en el panel de inicio
        Task<ArrayList<Object>> task = new Task<ArrayList<Object>>() {
            @Override
            protected ArrayList<Object> call() throws Exception {
                ArrayList<Object> data = new ArrayList<Object>();

                Message peticion = new Message("SERVIDOR", "DATOS_ESTABL", data);
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
            db_cajaHoy.setText((double) listaDatos.get(0) + "");

            db_trabajadoresOn.setText((int) listaDatos.get(1) + "");

            db_totalTrabajadores.setText((long) listaDatos.get(2) + "");
            db_fechaRecarga.setText((String) listaDatos.get(3));

            db_totalPedidos.setText((long) listaDatos.get(4) + "");
            db_pedidosFin.setText((long) listaDatos.get(5) + "");
            db_pedidosPen.setText((long) listaDatos.get(6) + "");

            spinner.setVisible(false);
        });

        Thread thread = new Thread(task);
        thread.start();
    }

    /**
     * Abre la ventana con la caja generada en cada establecimiento
     *
     * @param e Evento
     */
    @FXML
    public void verCajaEstablecimientos(ActionEvent e) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("verEstablecimientos.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            VerEstablecimientosController controller = fxmlLoader.<VerEstablecimientosController>getController();
            controller.setUsuario(btnLabelUsuario.getText());

            Stage stage = new Stage();
            stage.setTitle("QuickTap - Datos cajas");
            stage.setScene(new Scene(root1, 600, 400));
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    /**
     * Abre la ventana para ver qué trabajadores están conectados
     *
     * @param e
     */
    @FXML
    public void verTrabajadoresConectados(ActionEvent e) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("verTrabajadores.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            VerTrabajadoresController controller = fxmlLoader.<VerTrabajadoresController>getController();
            controller.setUsuario(btnLabelUsuario.getText());

            Stage stage = new Stage();
            stage.setTitle("QuickTap - Listado de trabajadores");
            stage.setScene(new Scene(root1, 600, 400));
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    /**
     * Panel para ver el listado de productos por establecimiento
     *
     * @param e Evento
     */
    @FXML
    private void panelVerProducto(ActionEvent e) {
        labelVentanaActual.setText("Listado de producto");
        panelVerProducto.setVisible(true);
        panelInicio.setVisible(false);
        panelTrabajadores.setVisible(false);
        panelInfoUsuario.setVisible(false);
        panelAltaCategoria.setVisible(false);
        comboFiltroProducto.setValue(comboFiltroProducto.getItems().get(0));

        //Carga la información del usuario (en este caso, sus establecimientos)
        Task<ArrayList<String>> task = new Task<ArrayList<String>>() {
            @Override
            protected ArrayList<String> call() throws Exception {
                ArrayList<Object> data = new ArrayList<Object>();
                data.add(btnLabelUsuario.getText());
                Message peticion = new Message("USUARIO", "GET_DATOS_USUARIO", data);

                ArrayList<String> establUsuario = new ArrayList<String>();
                try {
                    App.out.writeObject(peticion);
                    Message mensajeRespuesta = (Message) App.in.readObject();

                    //Obtiene los establecimientos
                    establUsuario = (ArrayList<String>) mensajeRespuesta.getData().get(4);

                } catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
                return establUsuario;
            }
        };

        task.setOnRunning(event -> {

            spinner.setVisible(true);

        });

        task.setOnSucceeded(event -> {
            ArrayList<String> establUsuario = task.getValue();

            //Carga en el combobox los establecimientos del usuario logeado
            comboSeleccionarEstablListado.getItems().clear();
            comboSeleccionarEstablListado.getItems().addAll(establUsuario);

            spinner.setVisible(false);

        });

        Thread thread = new Thread(task);
        thread.start();

    }

    public void limpiarCampos() {

    }

    /**
     * Panel emergente para dar de alta un nuevo producto
     *
     * @param e Evento
     */
    @FXML
    private void panelAltaProducto(ActionEvent e) {
        panelAltaProducto.setVisible(true);

        //Abre la ventana para dar de alta un nuevo producto
        btnAltaProducto.setVisible(true);
        btnModificarProducto.setVisible(false);
        comboSeleccionarEstabl.setVisible(true);
        labelAltaProducto.setVisible(true);

        //Carga la imagen por defecto del producto
        //imagenSeleccionada = new File(getClass().getClassLoader().getResource("/no-image.png").toExternalForm());
        InputStream defaultImageStream = getClass().getResourceAsStream("/no-image.png");
        if (defaultImageStream != null) {
            try {
                // Crear un archivo temporal para la imagen por defecto
                File tempFile = File.createTempFile("default-image", ".png");
                tempFile.deleteOnExit();

                // Copiar el contenido del flujo de entrada al archivo temporal
                Files.copy(defaultImageStream, tempFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

                // Utilizar el archivo temporal como imagen por defecto
                imagenSeleccionada = tempFile;
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        String imagePath = imagenSeleccionada.toURI().toString();
        Image image = new Image(imagePath);

        imagenProducto.setImage(image);

        //Carga los establecimientos del usuario, para obtener las categorías de cada uno
        Task<ArrayList<String>> task = new Task<ArrayList<String>>() {
            @Override
            protected ArrayList<String> call() throws Exception {
                ArrayList<Object> data = new ArrayList<Object>();
                data.add(btnLabelUsuario.getText());
                Message peticion = new Message("USUARIO", "GET_DATOS_USUARIO", data);

                ArrayList<String> establUsuario = new ArrayList<String>();
                try {
                    App.out.writeObject(peticion);
                    Message mensajeRespuesta = (Message) App.in.readObject();

                    //Obtiene los establecimientos del usuario
                    establUsuario = (ArrayList<String>) mensajeRespuesta.getData().get(4);

                } catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
                return establUsuario;
            }
        };

        task.setOnRunning(event -> {

            spinner.setVisible(true);

        });

        task.setOnSucceeded(event -> {
            ArrayList<String> establUsuario = task.getValue();

            comboSeleccionarEstabl.getItems().clear();
            comboSeleccionarEstabl.getItems().addAll(establUsuario);
            //comboSeleccionarEstabl.setValue(establUsuario.get(0));

            spinner.setVisible(false);
        });

        Thread thread = new Thread(task);
        thread.start();

    }

    /**
     * Rellena el listview de categorias, dependiendo del establecimiento
     * seleccionado
     *
     * @param e Evento
     */
    @FXML
    public void rellenarCategorias(ActionEvent e) {

        //Envía el establecimiento al servidor y recibe sus categorías asociadas
        Task<ArrayList<String>> task = new Task<ArrayList<String>>() {
            @Override
            protected ArrayList<String> call() throws Exception {
                ArrayList<Object> data = new ArrayList<Object>();

                data.add(comboSeleccionarEstabl.getValue());
                Message peticion = new Message("CATEGORIA", "GET_CATEGORIAS", data);

                ArrayList<String> categoriasEstabl = new ArrayList<String>();
                try {
                    App.out.writeObject(peticion);
                    Message mensajeRespuesta = (Message) App.in.readObject();

                    for (Object o : mensajeRespuesta.getData()) {
                        categoriasEstabl.add((String) o);
                    }

                } catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
                return categoriasEstabl;
            }
        };

        task.setOnRunning(event -> {

            spinner.setVisible(true);

        });

        task.setOnSucceeded(event -> {
            ArrayList<String> categoriasEstabl = task.getValue();

            listViewCategorias.getItems().clear();
            listViewCategSeleccionadas.getItems().clear();
            listViewCategorias.getItems().addAll(categoriasEstabl);

            spinner.setVisible(false);
        });

        Thread thread = new Thread(task);
        thread.start();

    }

    @FXML
    public void rellenarCategoriasAlta(ActionEvent e) {

        //Envía el establecimiento al servidor y recibe sus categorías asociadas
        Task<ArrayList<String>> task = new Task<ArrayList<String>>() {
            @Override
            protected ArrayList<String> call() throws Exception {
                ArrayList<Object> data = new ArrayList<Object>();

                data.add(comboSeleccionarEstablCategoria.getValue());
                Message peticion = new Message("CATEGORIA", "GET_CATEGORIAS", data);

                ArrayList<String> categoriasEstabl = new ArrayList<String>();
                try {
                    App.out.writeObject(peticion);
                    Message mensajeRespuesta = (Message) App.in.readObject();

                    for (Object o : mensajeRespuesta.getData()) {
                        categoriasEstabl.add((String) o);
                    }

                } catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
                return categoriasEstabl;
            }
        };

        task.setOnRunning(event -> {

            spinner.setVisible(true);

        });

        task.setOnSucceeded(event -> {
            ArrayList<String> categoriasEstabl = task.getValue();

            listViewCategoriasDisponibles.getItems().clear();
            listViewCategoriasDisponibles.getItems().addAll(categoriasEstabl);

            spinner.setVisible(false);
        });

        Thread thread = new Thread(task);
        thread.start();

    }

    /**
     * Rellena la tabla de productos del establecimiento seleccionado
     *
     * @param e Evento
     */
    @FXML
    public void rellenarProductos(ActionEvent e) {

        //Envía el establecimiento al servidor y recibe sus productos asociados
        Task<ArrayList<ArrayList<Object>>> task = new Task<ArrayList<ArrayList<Object>>>() {
            @Override
            protected ArrayList<ArrayList<Object>> call() throws Exception {
                ArrayList<Object> data = new ArrayList<Object>();

                data.add(comboSeleccionarEstablListado.getValue());
                Message peticion = new Message("PRODUCTO", "GET_PRODUCTOS", data);

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
            productos = task.getValue();

            // Agregar las columnas a la tabla
            columNombreProd.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<Object>, Object>, ObservableValue<Object>>() {
                public ObservableValue<Object> call(TableColumn.CellDataFeatures<List<Object>, Object> c) {
                    return new SimpleObjectProperty<Object>(c.getValue().get(0));
                }
            });

            columDescripcionProd.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<Object>, Object>, ObservableValue<Object>>() {
                public ObservableValue<Object> call(TableColumn.CellDataFeatures<List<Object>, Object> c) {
                    return new SimpleObjectProperty<Object>(c.getValue().get(1));
                }
            });

            columPrecioProd.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<Object>, Object>, ObservableValue<Object>>() {
                public ObservableValue<Object> call(TableColumn.CellDataFeatures<List<Object>, Object> c) {
                    return new SimpleObjectProperty<Object>(c.getValue().get(2));
                }
            });

            columStockProd.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<Object>, Object>, ObservableValue<Object>>() {
                public ObservableValue<Object> call(TableColumn.CellDataFeatures<List<Object>, Object> c) {
                    return new SimpleObjectProperty<Object>(c.getValue().get(3));
                }
            });

            columCategorias.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<Object>, Object>, ObservableValue<Object>>() {
                public ObservableValue<Object> call(TableColumn.CellDataFeatures<List<Object>, Object> c) {
                    return new SimpleObjectProperty<Object>(c.getValue().get(5));
                }
            });

            // Asignar las filas a la tabla
            tablaProductos.setItems(FXCollections.observableArrayList(productos));

            spinner.setVisible(false);
        });

        Thread thread = new Thread(task);
        thread.start();

    }

    /**
     * Rellena la tabla de trabajadores de un establecimiento seleccionado
     *
     * @param e Evento
     */
    @FXML
    public void rellenarTrabajadores(ActionEvent e) {

        //Envía el establecimiento al servidor y recibe sus trabajadores asociados
        Task<ArrayList<ArrayList<Object>>> task = new Task<ArrayList<ArrayList<Object>>>() {
            @Override
            protected ArrayList<ArrayList<Object>> call() throws Exception {
                ArrayList<Object> data = new ArrayList<Object>();

                data.add(comboSeleccionarEstablTrabajador.getValue());
                Message peticion = new Message("ESTABLECIMIENTO", "GET_TRABAJADORES", data);

                ArrayList<ArrayList<Object>> trabajadores = new ArrayList<>();
                try {
                    App.out.writeObject(peticion);
                    Message mensajeRespuesta = (Message) App.in.readObject();

                    ArrayList<Object> datosRecibidos = (ArrayList<Object>) mensajeRespuesta.getData();

                    for (Object listado : datosRecibidos) {
                        trabajadores.add((ArrayList<Object>) listado);
                    }

                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                return trabajadores;
            }
        };

        task.setOnRunning(event -> {

            spinner.setVisible(true);

        });

        task.setOnSucceeded(event -> {
            trabajadores = task.getValue();

            // Agregar las columnas a la tabla
            columNombreTrabaj.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<Object>, Object>, ObservableValue<Object>>() {
                public ObservableValue<Object> call(TableColumn.CellDataFeatures<List<Object>, Object> c) {
                    return new SimpleObjectProperty<Object>(c.getValue().get(0));
                }
            });

            columCorreoTrabaj.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<Object>, Object>, ObservableValue<Object>>() {
                public ObservableValue<Object> call(TableColumn.CellDataFeatures<List<Object>, Object> c) {
                    return new SimpleObjectProperty<Object>(c.getValue().get(1));
                }
            });

            /*
            columPasswTrabaj.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<Object>, Object>, ObservableValue<Object>>() {
                public ObservableValue<Object> call(TableColumn.CellDataFeatures<List<Object>, Object> c) {
                    return new SimpleObjectProperty<Object>(c.getValue().get(2));
                }
            });
             */
            // Asignar las filas a la tabla
            tablaTrabajadores.setItems(FXCollections.observableArrayList(trabajadores));

            spinner.setVisible(false);
        });

        Thread thread = new Thread(task);
        thread.start();

    }

    /**
     * Habilita el panel para dar de alta categorías nuevas en un
     * establecimiento
     *
     * @param e
     */
    @FXML
    private void panelAltaCategoria(ActionEvent e) {
        labelVentanaActual.setText("Nueva categoría");
        panelAltaCategoria.setVisible(true);
        panelVerProducto.setVisible(false);
        panelInicio.setVisible(false);
        panelTrabajadores.setVisible(false);
        panelInfoUsuario.setVisible(false);

        //Carga la información del usuario (en este caso, sus establecimientos)
        Task<ArrayList<String>> task = new Task<ArrayList<String>>() {
            @Override
            protected ArrayList<String> call() throws Exception {
                ArrayList<Object> data = new ArrayList<Object>();
                data.add(btnLabelUsuario.getText());
                Message peticion = new Message("USUARIO", "GET_DATOS_USUARIO", data);

                ArrayList<String> establUsuario = new ArrayList<String>();
                try {
                    App.out.writeObject(peticion);
                    Message mensajeRespuesta = (Message) App.in.readObject();

                    //Obtiene los establecimientos
                    establUsuario = (ArrayList<String>) mensajeRespuesta.getData().get(4);

                } catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
                return establUsuario;
            }
        };

        task.setOnRunning(event -> {

            spinner.setVisible(true);

        });

        task.setOnSucceeded(event -> {
            ArrayList<String> establUsuario = task.getValue();

            listViewEstablCategoria.getItems().clear();
            listViewCategSeleccionadas.getItems().clear();
            listViewEstablCategoria.getItems().addAll(establUsuario);

            comboSeleccionarEstablCategoria.getItems().clear();
            comboSeleccionarEstablCategoria.getItems().addAll(establUsuario);

            spinner.setVisible(false);
        });

        Thread thread = new Thread(task);
        thread.start();

    }

    /**
     * Habilita el panel para ver los trabajadores por establecimiento
     *
     * @param e Evento
     */
    @FXML
    private void panelTrabajadores(ActionEvent e) {
        labelVentanaActual.setText("Listado de trabajadores");
        panelTrabajadores.setVisible(true);
        panelVerProducto.setVisible(false);
        panelInicio.setVisible(false);
        panelInfoUsuario.setVisible(false);
        panelAltaCategoria.setVisible(false);
        comboFiltroTrabajador.setValue(comboFiltroTrabajador.getItems().get(0));

        //Carga la información del usuario (en este caso, sus establecimientos)
        Task<ArrayList<String>> task = new Task<ArrayList<String>>() {
            @Override
            protected ArrayList<String> call() throws Exception {
                ArrayList<Object> data = new ArrayList<Object>();
                data.add(btnLabelUsuario.getText());
                Message peticion = new Message("USUARIO", "GET_DATOS_USUARIO", data);

                ArrayList<String> establUsuario = new ArrayList<String>();
                try {
                    App.out.writeObject(peticion);
                    Message mensajeRespuesta = (Message) App.in.readObject();

                    //Obtiene los establecimientos
                    establUsuario = (ArrayList<String>) mensajeRespuesta.getData().get(4);

                } catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
                return establUsuario;
            }
        };

        task.setOnRunning(event -> {

            spinner.setVisible(true);

        });

        task.setOnSucceeded(event -> {
            ArrayList<String> establUsuario = task.getValue();

            //Carga en el combobox los establecimientos del usuario logeado
            comboSeleccionarEstablTrabajador.getItems().clear();
            comboSeleccionarEstablTrabajador.getItems().addAll(establUsuario);

            spinner.setVisible(false);

        });

        Thread thread = new Thread(task);
        thread.start();

    }

    /**
     * Panel emergente para dar de alta un nuevo trabajador
     *
     * @param e Evento
     */
    @FXML
    private void panelAltaTrabajador(ActionEvent e) {

        panelAltaUsuario.setVisible(true);

        //Abre la ventana para dar de alta un nuevo producto
        btnAltaTrabajador.setVisible(true);
        btnModificarTrabajador.setVisible(false);

        //Carga los establecimientos disponibles
        Task<ArrayList<String>> task = new Task<ArrayList<String>>() {
            @Override
            protected ArrayList<String> call() throws Exception {
                //Devuelve los establecimientos del usuario logeado
                ArrayList<Object> datos = new ArrayList<>();
                datos.add(btnLabelUsuario.getText());
                Message peticion = new Message("ESTABLECIMIENTO", "GET_ESTABLECIMIENTOS_USUARIO", datos);
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
            listViewEstablecimientosTrabajador.getItems().clear();

            spinner.setVisible(false);

        });

        Thread thread = new Thread(task);
        thread.start();

    }

    /**
     * Abre el panel emergente para modificar los datos de un trabajador
     *
     * @param trabajador El nombre del trabajador a modificar, que se envía al
     * servidor para obtener sus datos
     */
    public void modificarTrabajador(String trabajador) {

        //Abre la ventana usada para dar de alta un nuevo trabajador, usada también para modificar sus datos
        panelAltaUsuario.setVisible(true);
        btnAltaTrabajador.setVisible(false);
        btnModificarTrabajador.setVisible(true);

        //Carga la información del trabajador
        Task<ArrayList<Object>> task = new Task<ArrayList<Object>>() {
            @Override
            protected ArrayList<Object> call() throws Exception {
                ArrayList<Object> data1 = new ArrayList<Object>();
                ArrayList<Object> data2 = new ArrayList<Object>();

                //Envía el nombre del trabajador para buscar sus datos
                data1.add(trabajador);

                //Envía el nombre del propietario, para buscar los establecimientos disponibles
                data2.add(btnLabelUsuario.getText());

                Message peticion = new Message("USUARIO", "GET_DATOS_USUARIO", data1);
                Message peticionCategorias = new Message("ESTABLECIMIENTO", "GET_ESTABLECIMIENTOS_USUARIO", data2);

                ArrayList<Object> datosTrabajador = new ArrayList<>();

                try {
                    App.out.writeObject(peticion);
                    Message mensajeRespuesta = (Message) App.in.readObject();

                    App.out.writeObject(peticionCategorias);
                    Message mensajeRespuestaCategorias = (Message) App.in.readObject();

                    datosTrabajador = new ArrayList<>(mensajeRespuesta.getData());

                    ArrayList<String> establecimientosDisponibles = (ArrayList<String>) mensajeRespuestaCategorias.getData().get(0);

                    datosTrabajador.add(establecimientosDisponibles);

                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                return datosTrabajador;
            }
        };

        task.setOnRunning(event -> {

            spinner.setVisible(true);

        });

        task.setOnSucceeded(event -> {
            ArrayList<Object> datosTrabajador = task.getValue();

            nombreUsuario.setText((String) datosTrabajador.get(0));
            correoUsuario.setText((String) datosTrabajador.get(1));
            passwTrabajadorModificar = (String) datosTrabajador.get(2); //Se guarda la contraseña original del usuario
            //passwUsuario.setText((String) datosTrabajador.get(2));

            ArrayList<String> establecimientos = (ArrayList<String>) datosTrabajador.get(4);
            ArrayList<String> establecimientosDisponibles = (ArrayList<String>) datosTrabajador.get(5);

            listViewEstablecimientosTrabajador.getItems().clear();
            listViewEstablecimientosTrabajador.getItems().addAll(establecimientos);
            listViewEstablecimientos.getItems().clear();
            listViewEstablecimientos.getItems().addAll(establecimientosDisponibles);

            spinner.setVisible(false);

        });

        Thread thread = new Thread(task);
        thread.start();

    }

    /**
     * Habilita el panel para ver los datos del usuario logeado
     *
     * @param e Evento
     */
    @FXML
    private void panelInfoUsuario(ActionEvent e) {
        labelVentanaActual.setText("Perfil de Usuario");
        panelInfoUsuario.setVisible(true);
        panelTrabajadores.setVisible(false);
        panelVerProducto.setVisible(false);
        panelInicio.setVisible(false);
        panelAltaCategoria.setVisible(false);

        //Carga la información del usuario
        Task<ArrayList<Object>> task = new Task<ArrayList<Object>>() {
            @Override
            protected ArrayList<Object> call() throws Exception {
                ArrayList<Object> data = new ArrayList<Object>();
                data.add(btnLabelUsuario.getText());
                Message peticion = new Message("USUARIO", "GET_DATOS_USUARIO", data);
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

        task.setOnRunning(event -> {

            spinner.setVisible(true);

        });

        task.setOnSucceeded(event -> {
            ArrayList<Object> datosUsuario = task.getValue();
            nombreUsuarioInfo.setText((String) datosUsuario.get(0));
            correoUsuarioInfo.setText((String) datosUsuario.get(1));
            //passwUsuarioModificar = (String) datosUsuario.get(2); //Se guarda la contraseña original del usuario
            //passwUsuarioInfo.setText((String) datosUsuario.get(2));

            spinner.setVisible(false);

        });

        Thread thread = new Thread(task);
        thread.start();

    }

    /**
     * Modifica un producto de un establecimiento
     *
     * @param producto El nombre del producto, enviado al servidor junto con el
     * establecimiento seleccionado
     */
    public void modificarProducto(String producto) {

        //Abre la ventana usada para dar de alta un nuevo producto, usada también para modificar sus datos
        panelAltaProducto.setVisible(true);
        btnAltaProducto.setVisible(false);
        btnModificarProducto.setVisible(true);
        comboSeleccionarEstabl.setVisible(false);
        labelAltaProducto.setVisible(false);

        //Carga la información del producto
        Task<ArrayList<Object>> task = new Task<ArrayList<Object>>() {
            @Override
            protected ArrayList<Object> call() throws Exception {
                ArrayList<Object> data1 = new ArrayList<Object>();
                ArrayList<Object> data2 = new ArrayList<Object>();

                //Datos para buscar el producto
                data1.add(producto);
                data1.add(comboSeleccionarEstablListado.getValue());

                //Datos para buscar las categorías
                data2.add(comboSeleccionarEstablListado.getValue());

                Message peticion = new Message("PRODUCTO", "GET_DATOS_PRODUCTO", data1);
                Message peticionCategorias = new Message("CATEGORIA", "GET_CATEGORIAS", data2);

                ArrayList<Object> datosProducto = new ArrayList<>();
                ArrayList<String> categoriasDisponibles = new ArrayList<String>();

                try {
                    App.out.writeObject(peticion);
                    Message mensajeRespuesta = (Message) App.in.readObject();

                    App.out.writeObject(peticionCategorias);
                    Message mensajeRespuestaCategorias = (Message) App.in.readObject();

                    datosProducto = new ArrayList<>(mensajeRespuesta.getData());

                    //Añade a la lista devuelta las categorías disponibles
                    for (Object o : mensajeRespuestaCategorias.getData()) {
                        categoriasDisponibles.add((String) o);
                    }

                    datosProducto.add(categoriasDisponibles);

                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                return datosProducto;
            }
        };

        task.setOnRunning(event -> {

            spinner.setVisible(true);

        });

        task.setOnSucceeded(event -> {
            ArrayList<Object> datosProducto = task.getValue();

            nombreProducto.setText((String) datosProducto.get(0));
            descripcionProducto.setText((String) datosProducto.get(1));
            double precio = (double) datosProducto.get(2);
            precioProducto.setText(precio + "");
            int stock = (int) datosProducto.get(3);
            stockProducto.setText(stock + "");
            byte[] imagenBytes = (byte[]) datosProducto.get(4);

            Image imagen = FileUtils.bytesToImage(imagenBytes);

            imagenProducto.setImage(imagen);

            ArrayList<String> categorias = (ArrayList<String>) datosProducto.get(5);
            ArrayList<String> categoriasDisponibles = (ArrayList<String>) datosProducto.get(6);

            listViewCategorias.getItems().clear();
            listViewCategorias.getItems().addAll(categoriasDisponibles);
            listViewCategSeleccionadas.getItems().clear();
            listViewCategSeleccionadas.getItems().addAll(categorias);

            spinner.setVisible(false);

        });

        Thread thread = new Thread(task);
        thread.start();

    }

    /**
     * Modifica una categoría de un establecimiento
     *
     * @param categoria La categoría a modificar
     */
    public void modificarCategoria(String categoria) {

        //Abre la ventana de modificar categoria
        panelModificarCategoria.setVisible(true);

        //Carga la información de la categoría
        Task<ArrayList<Object>> task = new Task<ArrayList<Object>>() {
            @Override
            protected ArrayList<Object> call() throws Exception {
                ArrayList<Object> data = new ArrayList<Object>();

                //Datos para buscar la categoría
                data.add(categoria);
                data.add(comboSeleccionarEstablCategoria.getValue());

                Message peticion = new Message("CATEGORIA", "GET_DATOS_CATEGORIA", data);

                ArrayList<Object> datoscategoria = new ArrayList<>();

                try {
                    App.out.writeObject(peticion);
                    Message mensajeRespuesta = (Message) App.in.readObject();

                    datoscategoria = new ArrayList<>(mensajeRespuesta.getData());

                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                return datoscategoria;
            }
        };

        task.setOnRunning(event -> {

            spinner.setVisible(true);

        });

        task.setOnSucceeded(event -> {
            ArrayList<Object> datoscategoria = task.getValue();

            nombreCategoriaModificar.setText((String) datoscategoria.get(0));
            descripcionCategoriaModificar.setText((String) datoscategoria.get(1));

            spinner.setVisible(false);

        });

        Thread thread = new Thread(task);
        thread.start();

    }

    @FXML
    public void añadirEstabl(ActionEvent e) {

        if (listViewEstablecimientosTrabajador.getItems().contains(listViewEstablecimientos.getSelectionModel().getSelectedItem())) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ERROR");
            alert.setHeaderText("Error al añadir establecimiento");
            alert.setContentText("El usuario ya tiene el establecimiento seleccionado.");
            alert.showAndWait();
        } else {
            listViewEstablecimientosTrabajador.getItems().add(listViewEstablecimientos.getSelectionModel().getSelectedItem());
        }

    }

    @FXML
    public void quitarEstabl(ActionEvent e) {

        if (listViewEstablecimientosTrabajador.getItems().size() == 1) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ERROR");
            alert.setHeaderText("Error al quitar establecimiento");
            alert.setContentText("El usuario debe tener al menos un establecimiento.");
            alert.showAndWait();
        } else {
            listViewEstablecimientosTrabajador.getItems().remove(listViewEstablecimientosTrabajador.getSelectionModel().getSelectedItem());
        }

    }

    /**
     * Añade una categoría del listado de categorias disponibles a las
     * categorias del producto, comprobando que no puedan repetirse
     *
     * @param e Evento
     */
    @FXML
    public void añadirCategoria(ActionEvent e) {

        if (listViewCategSeleccionadas.getItems().contains(listViewCategorias.getSelectionModel().getSelectedItem())) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ERROR");
            alert.setHeaderText("Error al añadir categoría");
            alert.setContentText("El producto ya tiene la categoría seleccionada.");
            alert.showAndWait();
        } else if (listViewCategorias.getItems().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ERROR");
            alert.setHeaderText("Error al añadir categoría");
            alert.setContentText("No hay categorías cargadas.");
            alert.showAndWait();
        } else {
            listViewCategSeleccionadas.getItems().add(listViewCategorias.getSelectionModel().getSelectedItem());
        }

    }

    /**
     * Quita una categoria al producto, comprobando que no pueda quedarse sin
     * ninguna categoría
     *
     * @param e
     */
    @FXML
    public void quitarCategoria(ActionEvent e) {

        /*
        if (listViewCategSeleccionadas.getItems().size() == 1) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ERROR");
            alert.setHeaderText("Error al quitar categoría");
            alert.setContentText("El producto debe tener al menos una categoría.");
            alert.showAndWait();
        } else {
         */
        listViewCategSeleccionadas.getItems().remove(listViewCategSeleccionadas.getSelectionModel().getSelectedItem());
        //}

    }

    /**
     * Cierra la ventana de productos
     *
     * @param e Evento
     */
    @FXML
    public void cerrarVentanaProducto(ActionEvent e) {

        nombreProducto.setText("");
        descripcionProducto.setText("");
        precioProducto.setText("");
        stockProducto.setText("");

        InputStream defaultImageStream = getClass().getResourceAsStream("/no-image.png");
        if (defaultImageStream != null) {
            try {
                // Crear un archivo temporal para la imagen por defecto
                File tempFile = File.createTempFile("default-image", ".png");
                tempFile.deleteOnExit();

                // Copiar el contenido del flujo de entrada al archivo temporal
                Files.copy(defaultImageStream, tempFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

                // Utilizar el archivo temporal como imagen por defecto
                imagenSeleccionada = tempFile;
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        String imagePath = imagenSeleccionada.toURI().toString();
        Image image = new Image(imagePath);

        imagenProducto.setImage(image);

        panelAltaProducto.setVisible(false);

    }

    @FXML
    public void cerrarVentanaCategoria(ActionEvent e) {

        nombreCategoriaModificar.setText("");
        descripcionCategoriaModificar.setText("");

        panelModificarCategoria.setVisible(false);

    }

    /**
     * Cierra la ventana de trabajadores
     *
     * @param e Evento
     */
    @FXML
    public void cerrarVentanaTrabajador(ActionEvent e) {
        nombreUsuario.setText("");
        correoUsuario.setText("");
        passwUsuario.setText("");
        panelAltaUsuario.setVisible(false);
    }

    /**
     * Actualiza los datos del usuario en la BDD. Solo actualiza usuario, correo
     * o contraseña
     *
     * @param e Evento
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
        } else {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Error al modificar los datos");
            alert.setContentText("Debe introducir una contraseña.");
            alert.showAndWait();

        }

    }

    /**
     * Añade la nueva categoría a la BDD
     *
     * @param e Evento
     */
    @FXML
    public void añadirNuevaCategoria(ActionEvent e) {

        if (nombreCategoria.getText().isEmpty() || descripcionCategoria.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Información");
            alert.setHeaderText("ERROR");
            alert.setContentText("Debe rellenar todos los campos");
            alert.showAndWait();

        } else {
            Task<ArrayList<Object>> task = new Task<ArrayList<Object>>() {
                @Override
                protected ArrayList<Object> call() throws Exception {
                    ArrayList<Object> data = new ArrayList<Object>();

                    data.add(nombreCategoria.getText());
                    data.add(descripcionCategoria.getText());
                    data.add(listViewEstablCategoria.getSelectionModel().getSelectedItem());

                    Message peticion = new Message("CATEGORIA", "INSERTAR", data);
                    boolean respuesta = false;
                    ArrayList<Object> devolver = new ArrayList<Object>();
                    try {
                        App.out.writeObject(peticion);
                        Message mensajeRespuesta = (Message) App.in.readObject();
                        respuesta = (boolean) mensajeRespuesta.getData().get(0);

                        devolver.add(respuesta);
                        devolver.add(nombreCategoria.getText());

                    } catch (IOException ex) {
                        Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    return devolver;
                }
            };

            task.setOnRunning(event -> {

                spinner.setVisible(true);

            });

            task.setOnSucceeded(event -> {
                ArrayList<Object> datos = task.getValue();
                boolean respuesta = (boolean) datos.get(0);
                String categoriaNueva = (String) datos.get(1);

                if (respuesta) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Información");
                    alert.setHeaderText("Correcto");
                    alert.setContentText("Categoría dada de alta.");
                    alert.showAndWait();
                    nombreCategoria.setText("");
                    descripcionCategoria.setText("");

                    listViewCategoriasDisponibles.getItems().add(categoriaNueva);

                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Información");
                    alert.setHeaderText("ERROR");
                    alert.setContentText("La categoría ya existe");
                    alert.showAndWait();
                }

                spinner.setVisible(false);

            });

            Thread thread = new Thread(task);
            thread.start();
        }

    }

    /**
     * Añade un nuevo producto a la BDD
     *
     * @param e Evento
     */
    @FXML
    public void añadirNuevoProducto(ActionEvent e) {

        if (nombreProducto.getText().isEmpty() || descripcionProducto.getText().isEmpty() || precioProducto.getText().isEmpty()
                || stockProducto.getText().isEmpty() || listViewCategSeleccionadas.getItems().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Información");
            alert.setHeaderText("ERROR");
            alert.setContentText("Debe rellenar todos los campos");
            alert.showAndWait();

        } else {

            try {
                Task<Boolean> task = new Task<Boolean>() {
                    @Override
                    protected Boolean call() throws Exception {
                        ArrayList<Object> data = new ArrayList<Object>();

                        ArrayList<String> categorias = new ArrayList<>();
                        
                        for(String c : listViewCategSeleccionadas.getItems()){
                            
                            categorias.add(c+";"+comboSeleccionarEstabl.getValue());
                            
                        }
                        
                        data.add(nombreProducto.getText());
                        data.add(descripcionProducto.getText());
                        data.add(comboSeleccionarEstabl.getValue());
                        double precio = Double.parseDouble(precioProducto.getText());
                        data.add(precio);
                        int stock = Integer.parseInt(stockProducto.getText());
                        data.add(stock);

                        data.add(categorias);

                        //Obtiene la imagen seleccionada del file chooser. Si no se pulsó el botón para elegir imagen, carga la por defecto
                        byte[] imagen = FileUtils.fileToBytes(imagenSeleccionada);
                        data.add(imagen);

                        Message peticion = new Message("PRODUCTO", "INSERTAR", data);
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
                        alert.setContentText("Producto dado de alta.");
                        alert.showAndWait();

                        cerrarVentanaProducto(e);
                        rellenarProductos(e);

                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Información");
                        alert.setHeaderText("ERROR");
                        alert.setContentText("El producto ya existe");
                        alert.showAndWait();
                    }

                    spinner.setVisible(false);

                });

                Thread thread = new Thread(task);
                thread.start();

            } catch (NumberFormatException ex) {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Información");
                alert.setHeaderText("ERROR");
                alert.setContentText("El precio y el stock deben tener formato numérico");
                alert.showAndWait();

            }

        }

    }

    /**
     * Añade un nuevo usuario trabajador a la BDD
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

                    //Añade rol trabajador al nuevo usuario
                    roles.add("trabajador"); //Rol trabajador
                    establecimientos.addAll(listViewEstablecimientosTrabajador.getItems());

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

            });

            task.setOnSucceeded(event -> {
                boolean respuesta = task.getValue();

                if (respuesta) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Información");
                    alert.setHeaderText("Correcto");
                    alert.setContentText("Usuario dado de alta.");
                    alert.showAndWait();

                    cerrarVentanaTrabajador(e);
                    rellenarTrabajadores(e);
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Información");
                    alert.setHeaderText("ERROR");
                    alert.setContentText("El correo introducido ya existe");
                    alert.showAndWait();
                }

                spinner.setVisible(false);

            });

            Thread thread = new Thread(task);
            thread.start();
        }

    }

    /**
     * Actualiza los datos de una categoría en la BDD
     *
     * @param e Evento
     */
    @FXML
    private void actualizarDatosCategoria(ActionEvent e) {

        //Envía los nuevos datos al servidor
        Task<Boolean> task = new Task<Boolean>() {
            @Override
            protected Boolean call() throws Exception {

                ArrayList<Object> nuevosDatos = new ArrayList<Object>();

                nuevosDatos.add(categoriaCargada);
                nuevosDatos.add(comboSeleccionarEstablCategoria.getValue());

                nuevosDatos.add(nombreCategoriaModificar.getText());
                nuevosDatos.add(descripcionCategoriaModificar.getText());

                Message peticion = new Message("CATEGORIA", "ACTUALIZAR_DATOS_CATEGORIA", nuevosDatos);
                boolean respuesta = false;
                try {
                    App.out.writeObject(peticion);
                    Message mensajeRespuesta = (Message) App.in.readObject();
                    respuesta = (boolean) mensajeRespuesta.getData().get(0);

                    if (respuesta) {
                        cerrarVentanaCategoria(e);
                        rellenarCategoriasAlta(e);

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
                nombreCategoriaModificar.setText("");
                descripcionCategoriaModificar.setText("");

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
     * Actualiza los datos de un producto en la BDD
     *
     * @param e Evento
     */
    @FXML
    private void actualizarDatosProducto(ActionEvent e) {

        if (nombreProducto.getText().isEmpty() || descripcionProducto.getText().isEmpty() || precioProducto.getText().isEmpty()
                || stockProducto.getText().isEmpty() || listViewCategSeleccionadas.getItems().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Información");
            alert.setHeaderText("ERROR");
            alert.setContentText("Debe rellenar todos los campos");
            alert.showAndWait();

        } else {

            try {
                //Envía los nuevos datos al servidor
                Task<Boolean> task = new Task<Boolean>() {
                    @Override
                    protected Boolean call() throws Exception {

                        ArrayList<Object> nuevosDatos = new ArrayList<Object>();

                        nuevosDatos.add(productoCargado);
                        nuevosDatos.add(comboSeleccionarEstablListado.getValue());

                        nuevosDatos.add(nombreProducto.getText());
                        nuevosDatos.add(descripcionProducto.getText());
                        double precio = Double.parseDouble(precioProducto.getText());
                        nuevosDatos.add(precio);
                        int stock = Integer.parseInt(stockProducto.getText());
                        nuevosDatos.add(stock);

                        byte[] imagenBytes = FileUtils.imageToBytes(imagenProducto.getImage(), "png");
                        nuevosDatos.add(imagenBytes);

                        ArrayList<String> categorias = new ArrayList<>();
                        
                        for(String c : listViewCategSeleccionadas.getItems()){
                            categorias.add(c+";"+comboSeleccionarEstablListado.getValue());
                        }
                        
                        nuevosDatos.add(categorias);

                        Message peticion = new Message("PRODUCTO", "ACTUALIZAR_DATOS_PRODUCTO", nuevosDatos);
                        boolean respuesta = false;
                        try {
                            App.out.writeObject(peticion);
                            Message mensajeRespuesta = (Message) App.in.readObject();
                            respuesta = (boolean) mensajeRespuesta.getData().get(0);

                            if (respuesta) {
                                cerrarVentanaProducto(e);
                                rellenarProductos(e);

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
                        nombreProducto.setText("");
                        descripcionProducto.setText("");
                        precioProducto.setText("");
                        stockProducto.setText("");

                        InputStream defaultImageStream = getClass().getResourceAsStream("/no-image.png");
                        if (defaultImageStream != null) {
                            try {
                                // Crear un archivo temporal para la imagen por defecto
                                File tempFile = File.createTempFile("default-image", ".png");
                                tempFile.deleteOnExit();

                                // Copiar el contenido del flujo de entrada al archivo temporal
                                Files.copy(defaultImageStream, tempFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

                                // Utilizar el archivo temporal como imagen por defecto
                                imagenSeleccionada = tempFile;
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                        }

                        String imagePath = imagenSeleccionada.toURI().toString();
                        Image image = new Image(imagePath);

                        imagenProducto.setImage(image);
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
            } catch (NumberFormatException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Información");
                alert.setHeaderText("ERROR");
                alert.setContentText("El precio y el stock deben tener formato numérico.");
                alert.showAndWait();
            }

        }

    }

    /**
     * Actualiza los datos de un trabajador en la BDD
     *
     * @param e Evento
     */
    @FXML
    private void actualizarDatosTrabajador(ActionEvent e) {

        //Comprueba que los campos no estén vacíos
        if (nombreUsuario.getText().isEmpty() || correoUsuario.getText().isEmpty()
                //|| listViewEstablecimientosTrabajador.getSelectionModel().getSelectedItem() == null) {
                || listViewEstablecimientosTrabajador.getItems().isEmpty() ) {
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

                    nuevosDatos.add(trabajadorCargado);

                    nuevosDatos.add(nombreUsuario.getText());
                    nuevosDatos.add(correoUsuario.getText());

                    //Comprueba si se ha modificado la contraseña. Si no se introduce una, se envía la contraseña original almacenada en memoria
                    if (passwUsuario.getText().isEmpty()) {
                        nuevosDatos.add(passwTrabajadorModificar);
                    } //Si se modifica, envía la nueva contraseña encriptada
                    else {
                        nuevosDatos.add(SHA.encrypt(passwUsuario.getText()));
                    }

                    ArrayList<String> establecimientos = new ArrayList<>(listViewEstablecimientosTrabajador.getItems());
                    nuevosDatos.add(establecimientos);

                    Message peticion = new Message("USUARIO", "ACTUALIZAR_DATOS_TRABAJADOR", nuevosDatos);
                    boolean respuesta = false;
                    try {
                        App.out.writeObject(peticion);
                        Message mensajeRespuesta = (Message) App.in.readObject();
                        respuesta = (boolean) mensajeRespuesta.getData().get(0);

                        if (respuesta) {
                            cerrarVentanaTrabajador(e);
                            rellenarTrabajadores(e);
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

    /**
     * Borra un producto de la BDD
     *
     * @param producto El nombre del producto a borrar
     * @param establecimiento El establecimiento al que pertenece el producto
     */
    private void borrarProducto(String producto, String establecimiento) {

        //Envía los nuevos datos al servidor
        Task<Boolean> task = new Task<Boolean>() {
            @Override
            protected Boolean call() throws Exception {

                ArrayList<Object> data = new ArrayList<Object>();

                data.add(producto);
                data.add(establecimiento);

                Message peticion = new Message("PRODUCTO", "BORRAR", data);
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
                alert.setContentText("Producto eliminado.");
                alert.showAndWait();
                rellenarProductos(null);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText("Error al eliminar el producto");
                alert.setContentText("Compruebe que no está siendo usado en algún pedido.");
                alert.showAndWait();
            }

            spinner.setVisible(false);

        });

        Thread thread = new Thread(task);
        thread.start();

    }

    /**
     * Borra una categoria de un establecimiento
     *
     * @param categoria La categoría a borrar
     * @param establecimiento El establecimiento donde borrarla
     */
    private void borrarCategoria(String categoria, String establecimiento) {

        //Envía los nuevos datos al servidor
        Task<Boolean> task = new Task<Boolean>() {
            @Override
            protected Boolean call() throws Exception {

                ArrayList<Object> data = new ArrayList<Object>();

                data.add(categoria);
                data.add(establecimiento);

                Message peticion = new Message("CATEGORIA", "BORRAR", data);
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
                alert.setContentText("Categoría eliminada.");
                alert.showAndWait();
                rellenarCategoriasAlta(null);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText("Error al eliminar la categoría");
                alert.setContentText("Compruebe que no está siendo usada en algún producto.");
                alert.showAndWait();
            }

            spinner.setVisible(false);

        });

        Thread thread = new Thread(task);
        thread.start();

    }

    /**
     * Borra un trabajador de la BDD
     *
     * @param trabajador El nombre del trabajador a borrar
     */
    private void borrarTrabajador(String trabajador) {

        //Envía los nuevos datos al servidor
        Task<Boolean> task = new Task<Boolean>() {
            @Override
            protected Boolean call() throws Exception {

                ArrayList<Object> data = new ArrayList<Object>();

                data.add(trabajador);

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
                alert.setContentText("Trabajador dado de baja.");
                alert.showAndWait();
                rellenarTrabajadores(null);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText("Error al dar de baja al trabajador");
                alert.showAndWait();
            }

            spinner.setVisible(false);

        });

        Thread thread = new Thread(task);
        thread.start();

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
                Logger.getLogger(PropietarioController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

        Thread thread = new Thread(task);
        thread.start();

    }

    /**
     * Busca productos por el filtro dado
     *
     * @param textoFiltro El dato introducido por el usuario
     */
    private void buscarProducto(String textoFiltro) {

        //Crea un Predicate que filtre las filas según el nombre
        Predicate<List<Object>> filtro = fila -> {

            String campo = "";
            switch (comboFiltroProducto.getValue()) {
                case "Nombre":
                    campo = fila.get(0).toString();
                    break;
                case "Descripción":
                    campo = fila.get(1).toString();
                    break;
                case "Precio":
                    campo = fila.get(2).toString();
                    break;
                case "Stock":
                    campo = fila.get(3).toString();
                    break;
                case "Categorías":
                    campo = fila.get(5).toString();
                    break;
            }

            return campo.contains(textoFiltro); //Verifica si el nombre contiene el texto de filtro
        };

        //Aplica el filtro a la lista de datos
        List<List<Object>> datosFiltrados = productos.stream()
                .filter(filtro)
                .collect(Collectors.toList());

        //Actualiza la tabla con los datos filtrados
        tablaProductos.setItems(FXCollections.observableArrayList(datosFiltrados));
    }

    /**
     * Busca trabajadores por el filtro dado
     *
     * @param textoFiltro El dato introducido por el usuario
     */
    private void buscarTrabajador(String textoFiltro) {

        //Crea un Predicate que filtre las filas según el nombre
        Predicate<List<Object>> filtro = fila -> {

            String campo = "";
            switch (comboFiltroTrabajador.getValue()) {
                case "Nombre":
                    campo = fila.get(0).toString();
                    break;
                case "Correo":
                    campo = fila.get(1).toString();
                    break;
            }

            return campo.contains(textoFiltro); //Verifica si el nombre contiene el texto de filtro
        };

        //Aplica el filtro a la lista de datos
        List<List<Object>> datosFiltrados = trabajadores.stream()
                .filter(filtro)
                .collect(Collectors.toList());

        //Actualiza la tabla con los datos filtrados
        tablaTrabajadores.setItems(FXCollections.observableArrayList(datosFiltrados));
    }

}
