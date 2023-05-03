/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.quicktap_escritorio;

import com.jfoenix.controls.JFXListView;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXFilterComboBox;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.legacy.MFXLegacyComboBox;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
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
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import message.Message;
import netscape.javascript.JSObject;
import se.walkercrou.places.GooglePlaces;
import se.walkercrou.places.Place;
import se.walkercrou.places.exception.GooglePlacesException;
import se.walkercrou.places.exception.NoResultsFoundException;
import utilidades.FileUtils;

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
    private MFXButton btnLabelUsuario;

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
    private TableView tablaPedidos;

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
    private MFXFilterComboBox<String> comboSeleccionarEstablPedidos;

    @FXML
    private AnchorPane panelPedidos;

    @FXML
    private MFXFilterComboBox<String> comboSeleccionarEstablTrabajador;

    @FXML
    private AnchorPane panelTrabajadores;

    @FXML
    private TableView tablaTrabajadores;

    @FXML
    private TableColumn<List<Object>, Object> columNombreTrabaj;

    @FXML
    private TableColumn<List<Object>, Object> columCorreoTrabaj;

    @FXML
    private TableColumn<List<Object>, Object> columPasswTrabaj;

    private String trabajadorCargado;

    @FXML
    private ListView<String> listViewEstablecimientosTrabajador;

    @FXML
    private MFXButton btnAltaProducto;

    @FXML
    private MFXButton btnModificarProducto;

    @FXML
    private MFXButton btnAltaTrabajador;

    @FXML
    private MFXButton btnModificarTrabajador;

    private ContextMenu contextMenuProductos = new ContextMenu();
    private ContextMenu contextMenuTrabajadores = new ContextMenu();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

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
                    borrarProducto(productoCargado,comboSeleccionarEstablListado.getValue());
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

        
        
        btnMenu.setOnMouseClicked(event -> togglePanelLateral(panelMenu));

        // Agrega un evento al hacer clic en una fila
//        tablaProductos.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//                if (event.getClickCount() == 1) { // Si se hizo un solo clic
//                    // Obtiene la fila seleccionada
//                    ArrayList<Object> filaSeleccionada = (ArrayList<Object>) tablaProductos.getSelectionModel().getSelectedItem();
//                    if (filaSeleccionada != null) {
//                        System.out.println(filaSeleccionada.get(0));
//                        System.out.println(filaSeleccionada.get(1));
//                        System.out.println(filaSeleccionada.get(2));
//                        System.out.println(filaSeleccionada.get(3));
//
//                        //Carga los roles y establecimientos para ser editados
//                        productoCargado = (String) filaSeleccionada.get(0);
//                        modificarProducto(productoCargado);
//
//                    }
//                }
//            }
//        });

//        tablaTrabajadores.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//                if (event.getClickCount() == 1) { // Si se hizo un solo clic
//                    // Obtiene la fila seleccionada
//                    ArrayList<Object> filaSeleccionada = (ArrayList<Object>) tablaTrabajadores.getSelectionModel().getSelectedItem();
//                    if (filaSeleccionada != null) {
//
//                        //Carga los roles y establecimientos para ser editados
//                        trabajadorCargado = (String) filaSeleccionada.get(0);
//                        modificarTrabajador(trabajadorCargado);
//
//                    }
//                }
//            }
//        });

        actualizarDashboard();

    }

    @FXML
    public void cargarImagen() {

        imagenSeleccionada = fileChooser.showOpenDialog(new Stage());

        if (imagenSeleccionada == null) {
            try {
                imagenSeleccionada = new File(getClass().getResource("/no-image.png").toURI());
            } catch (URISyntaxException ex) {
                ex.printStackTrace();
            }
        }
        String imagePath = imagenSeleccionada.toURI().toString();
        Image image = new Image(imagePath);

        imagenProducto.setImage(image);

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
            btnAñadirProducto.setDisable(true);
            btnAñadirTrabajador.setDisable(true);
            btnHome.setDisable(true);
        } else {
            btnSalir.setDisable(false);
            btnInfoUsuario.setDisable(false);
            btnAñadirProducto.setDisable(false);
            btnAñadirTrabajador.setDisable(false);
            btnHome.setDisable(false);
        }

    }

    @FXML
    private void panelInicio(ActionEvent e) {
        labelVentanaActual.setText("Inicio");
        panelInicio.setVisible(true);
        panelVerProducto.setVisible(false);
        panelTrabajadores.setVisible(false);
        panelInfoUsuario.setVisible(false);
        panelAltaCategoria.setVisible(false);
        panelPedidos.setVisible(false);

        actualizarDashboard();

    }

    public void actualizarDashboard() {
        //Obtiene del servidor los datos a mostrar en el panel de inicio
        Task<ArrayList<Object>> task = new Task<ArrayList<Object>>() {
            @Override
            protected ArrayList<Object> call() throws Exception {
                ArrayList<Object> data = new ArrayList<Object>();
                data.add(btnLabelUsuario.getText());
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

        task.setOnSucceeded(event -> {
            ArrayList<Object> listaDatos = task.getValue();

            //Establece los datos obtenidos del servidor en los paneles
            db_cajaHoy.setText((double) listaDatos.get(0) + "");

            db_trabajadoresOn.setText((int) listaDatos.get(1) + "");

            db_totalTrabajadores.setText((long) listaDatos.get(2) + "");
            db_fechaRecarga.setText((String) listaDatos.get(3));

            db_totalPedidos.setText((long) listaDatos.get(4) + "");
            db_pedidosFin.setText((long) listaDatos.get(5) + "");
            db_pedidosPen.setText((long) listaDatos.get(6) + "");
        });

        Thread thread = new Thread(task);
        thread.start();
    }

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

    @FXML
    private void panelVerProducto(ActionEvent e) {
        labelVentanaActual.setText("Listado de producto");
        panelVerProducto.setVisible(true);
        panelInicio.setVisible(false);
        panelTrabajadores.setVisible(false);
        panelInfoUsuario.setVisible(false);
        panelAltaCategoria.setVisible(false);
        panelPedidos.setVisible(false);

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

        task.setOnSucceeded(event -> {
            ArrayList<String> establUsuario = task.getValue();

            //Carga en el combobox los establecimientos del usuario logeado
            comboSeleccionarEstablListado.getItems().clear();
            comboSeleccionarEstablListado.getItems().addAll(establUsuario);
            //comboSeleccionarEstablListado.setValue(establUsuario.get(0));
            //new Thread(task2).start();

        });

        Thread thread = new Thread(task);
        thread.start();

    }

    public void limpiarCampos() {

    }

    @FXML
    private void panelAltaProducto(ActionEvent e) {
        panelAltaProducto.setVisible(true);

        //Abre la ventana para dar de alta un nuevo producto
        btnAltaProducto.setVisible(true);
        btnModificarProducto.setVisible(false);
        comboSeleccionarEstabl.setVisible(true);

        //Carga la imagen por defecto del producto
        try {
            imagenSeleccionada = new File(getClass().getResource("/no-image.png").toURI());
        } catch (URISyntaxException ex) {
            ex.printStackTrace();
        }

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

        task.setOnSucceeded(event -> {
            ArrayList<String> establUsuario = task.getValue();

            comboSeleccionarEstabl.getItems().clear();
            comboSeleccionarEstabl.getItems().addAll(establUsuario);
            comboSeleccionarEstabl.setValue(establUsuario.get(0));
        });

        Thread thread = new Thread(task);
        thread.start();

    }

    @FXML
    private void panelPedidos(ActionEvent e) {
        labelVentanaActual.setText("Listado de pedidos");
        panelPedidos.setVisible(true);
        panelVerProducto.setVisible(false);
        panelInicio.setVisible(false);
        panelTrabajadores.setVisible(false);
        panelInfoUsuario.setVisible(false);
        panelAltaCategoria.setVisible(false);

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

        task.setOnSucceeded(event -> {
            ArrayList<String> establUsuario = task.getValue();

            //Carga en el combobox los establecimientos del usuario logeado
            comboSeleccionarEstablPedidos.getItems().clear();
            comboSeleccionarEstablPedidos.getItems().addAll(establUsuario);
            //comboSeleccionarEstablListado.setValue(establUsuario.get(0));
            //new Thread(task2).start();

        });

        Thread thread = new Thread(task);
        thread.start();

    }

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

        task.setOnSucceeded(event -> {
            ArrayList<String> categoriasEstabl = task.getValue();

            listViewCategorias.getItems().clear();
            listViewCategSeleccionadas.getItems().clear();
            listViewCategorias.getItems().addAll(categoriasEstabl);
        });

        Thread thread = new Thread(task);
        thread.start();

    }

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

        task.setOnSucceeded(event -> {
            ArrayList<ArrayList<Object>> productos = task.getValue();

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

            // Asignar las filas a la tabla
            tablaProductos.setItems(FXCollections.observableArrayList(productos));
        });

        Thread thread = new Thread(task);
        thread.start();

    }

    @FXML
    public void rellenarPedidos(ActionEvent e) {

        //Envía el establecimiento al servidor y recibe sus pedidos asociados
        Task<ArrayList<ArrayList<Object>>> task = new Task<ArrayList<ArrayList<Object>>>() {
            @Override
            protected ArrayList<ArrayList<Object>> call() throws Exception {
                ArrayList<Object> data = new ArrayList<Object>();

                data.add(comboSeleccionarEstablPedidos.getValue());
                Message peticion = new Message("PEDIDO", "GET_PEDIDOS", data);

                ArrayList<ArrayList<Object>> pedidos = new ArrayList<>();
                try {
                    App.out.writeObject(peticion);
                    Message mensajeRespuesta = (Message) App.in.readObject();

                    ArrayList<Object> datosRecibidos = (ArrayList<Object>) mensajeRespuesta.getData();

                    for (Object listado : datosRecibidos) {
                        pedidos.add((ArrayList<Object>) listado);
                    }

                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                return pedidos;
            }
        };

        task.setOnSucceeded(event -> {
            ArrayList<ArrayList<Object>> pedidos = task.getValue();

            // Agregar las columnas a la tabla
            columPedido.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<Object>, Object>, ObservableValue<Object>>() {
                public ObservableValue<Object> call(TableColumn.CellDataFeatures<List<Object>, Object> c) {
                    return new SimpleObjectProperty<Object>(c.getValue().get(0));
                }
            });

            columCliente.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<Object>, Object>, ObservableValue<Object>>() {
                public ObservableValue<Object> call(TableColumn.CellDataFeatures<List<Object>, Object> c) {
                    return new SimpleObjectProperty<Object>(c.getValue().get(1));
                }
            });

            columProducto.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<Object>, Object>, ObservableValue<Object>>() {
                public ObservableValue<Object> call(TableColumn.CellDataFeatures<List<Object>, Object> c) {
                    return new SimpleObjectProperty<Object>(c.getValue().get(2));
                }
            });

            columCantidad.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<Object>, Object>, ObservableValue<Object>>() {
                public ObservableValue<Object> call(TableColumn.CellDataFeatures<List<Object>, Object> c) {
                    return new SimpleObjectProperty<Object>(c.getValue().get(3));
                }
            });

            columEstado.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<Object>, Object>, ObservableValue<Object>>() {
                public ObservableValue<Object> call(TableColumn.CellDataFeatures<List<Object>, Object> c) {
                    return new SimpleObjectProperty<Object>(c.getValue().get(4));
                }
            });

            // Asignar las filas a la tabla
            tablaPedidos.setItems(FXCollections.observableArrayList(pedidos));
        });

        Thread thread = new Thread(task);
        thread.start();

    }

    public void rellenarProductos() {

        //Envía el establecimiento al servidor y recibe sus categorías asociadas
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

        task.setOnSucceeded(event -> {
            ArrayList<ArrayList<Object>> productos = task.getValue();

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

            // Asignar las filas a la tabla
            tablaProductos.setItems(FXCollections.observableArrayList(productos));
        });

        Thread thread = new Thread(task);
        thread.start();

    }

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

        task.setOnSucceeded(event -> {
            ArrayList<ArrayList<Object>> trabajadores = task.getValue();

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

            columPasswTrabaj.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<Object>, Object>, ObservableValue<Object>>() {
                public ObservableValue<Object> call(TableColumn.CellDataFeatures<List<Object>, Object> c) {
                    return new SimpleObjectProperty<Object>(c.getValue().get(2));
                }
            });

            // Asignar las filas a la tabla
            tablaTrabajadores.setItems(FXCollections.observableArrayList(trabajadores));
        });

        Thread thread = new Thread(task);
        thread.start();

    }

    @FXML
    private void panelAltaCategoria(ActionEvent e) {
        labelVentanaActual.setText("Nueva categoría");
        panelAltaCategoria.setVisible(true);
        panelVerProducto.setVisible(false);
        panelInicio.setVisible(false);
        panelTrabajadores.setVisible(false);
        panelInfoUsuario.setVisible(false);
        panelPedidos.setVisible(false);

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

        task.setOnSucceeded(event -> {
            ArrayList<String> establUsuario = task.getValue();

            listViewEstablCategoria.getItems().clear();
            listViewCategSeleccionadas.getItems().clear();
            listViewEstablCategoria.getItems().addAll(establUsuario);
        });

        Thread thread = new Thread(task);
        thread.start();

    }

    @FXML
    private void panelTrabajadores(ActionEvent e) {
        labelVentanaActual.setText("Listado de trabajadores");
        panelTrabajadores.setVisible(true);
        panelVerProducto.setVisible(false);
        panelInicio.setVisible(false);
        panelInfoUsuario.setVisible(false);
        panelAltaCategoria.setVisible(false);
        panelPedidos.setVisible(false);

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

        task.setOnSucceeded(event -> {
            ArrayList<String> establUsuario = task.getValue();

            //Carga en el combobox los establecimientos del usuario logeado
            comboSeleccionarEstablTrabajador.getItems().clear();
            comboSeleccionarEstablTrabajador.getItems().addAll(establUsuario);
            //comboSeleccionarEstablListado.setValue(establUsuario.get(0));
            //new Thread(task2).start();

        });

        Thread thread = new Thread(task);
        thread.start();

    }

    @FXML
    private void panelAltaTrabajador(ActionEvent e) {

        panelAltaUsuario.setVisible(true);

        //Abre la ventana para dar de alta un nuevo producto
        btnAltaTrabajador.setVisible(true);
        btnModificarTrabajador.setVisible(false);
        //comboSeleccionarEstabl.setVisible(true);

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

        task.setOnSucceeded(event -> {
            ArrayList<String> listaEstabl = task.getValue();
            listViewEstablecimientos.getItems().clear();
            listViewEstablecimientos.getItems().addAll(listaEstabl);
        });

        Thread thread = new Thread(task);
        thread.start();

    }

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
//                    datosProducto.add((String) mensajeRespuesta.getData().get(0));
//                    datosProducto.add((String) mensajeRespuesta.getData().get(1));
//                    datosProducto.add((String) mensajeRespuesta.getData().get(2));
//                    datosProducto.add((String) mensajeRespuesta.getData().get(3));
//                    datosProducto.add((byte[]) mensajeRespuesta.getData().get(4));
                    //datosProducto.add((ArrayList<String>) mensajeRespuesta.getData().get(5));

                    System.out.println("Tamaño:" + datosTrabajador.size());
                    //System.out.println(datosTrabajador);

                    ArrayList<String> establecimientosDisponibles = (ArrayList<String>) mensajeRespuestaCategorias.getData().get(0);

                    //System.out.println(establecimientosDisponibles);
                    datosTrabajador.add(establecimientosDisponibles);

                    //System.out.println(datosTrabajador);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                return datosTrabajador;
            }
        };

        task.setOnSucceeded(event -> {
            ArrayList<Object> datosTrabajador = task.getValue();

            //System.out.println(datosTrabajador);
            nombreUsuario.setText((String) datosTrabajador.get(0));
            correoUsuario.setText((String) datosTrabajador.get(1));
            passwUsuario.setText((String) datosTrabajador.get(2));

            ArrayList<String> establecimientos = (ArrayList<String>) datosTrabajador.get(4);
            ArrayList<String> establecimientosDisponibles = (ArrayList<String>) datosTrabajador.get(5);

            listViewEstablecimientosTrabajador.getItems().clear();
            listViewEstablecimientosTrabajador.getItems().addAll(establecimientos);
            listViewEstablecimientos.getItems().clear();
            listViewEstablecimientos.getItems().addAll(establecimientosDisponibles);

        });

        Thread thread = new Thread(task);
        thread.start();

    }

    @FXML
    private void panelInfoUsuario(ActionEvent e) {
        labelVentanaActual.setText("Perfil de Usuario");
        panelInfoUsuario.setVisible(true);
        panelTrabajadores.setVisible(false);
        panelVerProducto.setVisible(false);
        panelInicio.setVisible(false);
        panelAltaCategoria.setVisible(false);
        panelPedidos.setVisible(false);

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

        task.setOnSucceeded(event -> {
            ArrayList<Object> datosUsuario = task.getValue();
            nombreUsuarioInfo.setText((String) datosUsuario.get(0));
            correoUsuarioInfo.setText((String) datosUsuario.get(1));
            passwUsuarioInfo.setText((String) datosUsuario.get(2));
            rolesUsuarioInfo.getItems().clear();
            rolesUsuarioInfo.getItems().addAll((ArrayList<String>) datosUsuario.get(3));
            establUsuarioInfo.getItems().clear();
            establUsuarioInfo.getItems().addAll((ArrayList<String>) datosUsuario.get(4));
        });

        Thread thread = new Thread(task);
        thread.start();

    }

    public void modificarProducto(String producto) {

        //Abre la ventana usada para dar de alta un nuevo producto, usada también para modificar sus datos
        panelAltaProducto.setVisible(true);
        btnAltaProducto.setVisible(false);
        btnModificarProducto.setVisible(true);
        comboSeleccionarEstabl.setVisible(false);

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
//                    datosProducto.add((String) mensajeRespuesta.getData().get(0));
//                    datosProducto.add((String) mensajeRespuesta.getData().get(1));
//                    datosProducto.add((String) mensajeRespuesta.getData().get(2));
//                    datosProducto.add((String) mensajeRespuesta.getData().get(3));
//                    datosProducto.add((byte[]) mensajeRespuesta.getData().get(4));
                    //datosProducto.add((ArrayList<String>) mensajeRespuesta.getData().get(5));

                    System.out.println("Tamaño:" + datosProducto.size());

                    //Añade a la lista devuelta las categorías disponibles
                    for (Object o : mensajeRespuestaCategorias.getData()) {
                        categoriasDisponibles.add((String) o);
                    }

                    System.out.println(categoriasDisponibles);

                    datosProducto.add(categoriasDisponibles);

                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                return datosProducto;
            }
        };

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

        });

        Thread thread = new Thread(task);
        thread.start();

    }

    @FXML
    public void editarRoles(ActionEvent e) {

        if (panelEditarRoles.isVisible()) {
            panelEditarRoles.setVisible(false);
        } else {
            panelEditarRoles.setVisible(true);
        }

        Task<ArrayList<String>> task = new Task<ArrayList<String>>() {
            @Override
            protected ArrayList<String> call() throws Exception {
                Message peticion = new Message("ROL", "GET_ROLES", new ArrayList<Object>());
                ArrayList<String> listaRoles = new ArrayList<String>();
                try {
                    App.out.writeObject(peticion);
                    Message mensajeRespuesta = (Message) App.in.readObject();
                    listaRoles = (ArrayList<String>) mensajeRespuesta.getData().get(0);

                } catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
                return listaRoles;
            }
        };

        task.setOnSucceeded(event -> {
            ArrayList<String> listaRoles = task.getValue();
            rolesDisponibles.getItems().clear();
            rolesDisponibles.getItems().addAll(listaRoles);
        });

        Thread thread = new Thread(task);
        thread.start();

    }

    @FXML
    public void editarEstablecimientos(ActionEvent e) {

        if (panelEditarEstabl.isVisible()) {
            panelEditarEstabl.setVisible(false);
        } else {
            panelEditarEstabl.setVisible(true);
        }

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

        task.setOnSucceeded(event -> {
            ArrayList<String> listaEstabl = task.getValue();
            establDisponibles.getItems().clear();
            establDisponibles.getItems().addAll(listaEstabl);
        });

        Thread thread = new Thread(task);
        thread.start();

    }

    @FXML
    public void cerrarEditarRoles(ActionEvent e) {
        panelEditarRoles.setVisible(false);
    }

    @FXML
    public void cerrarEditarEstabl(ActionEvent e) {
        panelEditarEstabl.setVisible(false);
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

    //Añade una categoría al producto en el formulario de alta de producto
    @FXML
    public void añadirCategoria(ActionEvent e) {

        if (listViewCategSeleccionadas.getItems().contains(listViewCategorias.getSelectionModel().getSelectedItem())) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ERROR");
            alert.setHeaderText("Error al añadir categoría");
            alert.setContentText("El producto ya tiene la categoría seleccionada.");
            alert.showAndWait();
        } else {
            listViewCategSeleccionadas.getItems().add(listViewCategorias.getSelectionModel().getSelectedItem());
        }

    }

    //Quita una categoría al producto en el formulario de alta de producto
    @FXML
    public void quitarCategoria(ActionEvent e) {

        if (listViewCategSeleccionadas.getItems().size() == 1) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ERROR");
            alert.setHeaderText("Error al quitar categoría");
            alert.setContentText("El producto debe tener al menos una categoría.");
            alert.showAndWait();
        } else {
            listViewCategSeleccionadas.getItems().remove(listViewCategSeleccionadas.getSelectionModel().getSelectedItem());
        }

    }

    @FXML
    public void cerrarVentanaProducto(ActionEvent e) {
        panelAltaProducto.setVisible(false);
    }

    @FXML
    public void cerrarVentanaTrabajador(ActionEvent e) {
        panelAltaUsuario.setVisible(false);
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

                Message peticion = new Message("USUARIO", "ACTUALIZAR_DATOS", nuevosDatos);
                boolean respuesta = false;
                try {
                    App.out.writeObject(peticion);
                    Message mensajeRespuesta = (Message) App.in.readObject();
                    respuesta = (boolean) mensajeRespuesta.getData().get(0);

                    if (respuesta) {
                        btnLabelUsuario.setText(nombreUsuarioInfo.getText());
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
    public void añadirNuevaCategoria(ActionEvent e) {

        if (nombreCategoria.getText().isEmpty() || descripcionCategoria.getText().isEmpty()) {
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

                    data.add(nombreCategoria.getText());
                    data.add(descripcionCategoria.getText());
                    data.add(listViewEstablCategoria.getSelectionModel().getSelectedItem());

                    Message peticion = new Message("CATEGORIA", "INSERTAR", data);
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
                    alert.setContentText("Categoría dada de alta.");
                    alert.showAndWait();
                    nombreCategoria.setText("");
                    descripcionCategoria.setText("");

                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Información");
                    alert.setHeaderText("ERROR");
                    alert.setContentText("La categoría ya existe");
                    alert.showAndWait();
                }

            });

            Thread thread = new Thread(task);
            thread.start();
        }

    }

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
            Task<Boolean> task = new Task<Boolean>() {
                @Override
                protected Boolean call() throws Exception {
                    ArrayList<Object> data = new ArrayList<Object>();

                    ArrayList<String> categorias = new ArrayList<>(listViewCategSeleccionadas.getItems());
                    data.add(nombreProducto.getText());
                    data.add(descripcionProducto.getText());
                    data.add(comboSeleccionarEstabl.getValue());
                    data.add(Double.parseDouble(precioProducto.getText()));
                    data.add(Integer.parseInt(stockProducto.getText()));

                    data.add(categorias);

                    //Obtiene la imagen seleccionada del file chooser. Si no se pulsó el botón para elegir imagen, carga la por defecto
                    //String imagePath = imagenSeleccionada.toURI().toString();
                    //Image image = new Image(imagePath);
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

            task.setOnSucceeded(event -> {
                boolean respuesta = task.getValue();

                if (respuesta) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Información");
                    alert.setHeaderText("Correcto");
                    alert.setContentText("Producto dado de alta.");
                    alert.showAndWait();
                    nombreProducto.setText("");
                    descripcionProducto.setText("");
                    precioProducto.setText("");
                    stockProducto.setText("");

                    //Carga la imagen por defecto del producto
                    try {
                        imagenSeleccionada = new File(getClass().getResource("/no-image.png").toURI());
                    } catch (URISyntaxException ex) {
                        ex.printStackTrace();
                    }

                    String imagePath = imagenSeleccionada.toURI().toString();
                    Image image = new Image(imagePath);

                    imagenProducto.setImage(image);

                    panelAltaProducto.setVisible(false);

                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Información");
                    alert.setHeaderText("ERROR");
                    alert.setContentText("El producto ya existe");
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

                    //Añade rol trabajador al nuevo usuario
                    roles.add("trabajador"); //Rol trabajador
                    establecimientos.add(listViewEstablecimientos.getSelectionModel().getSelectedItem());

                    data.add(nombreUsuario.getText());
                    data.add(correoUsuario.getText());
                    data.add(passwUsuario.getText());
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

    @FXML
    private void actualizarDatosProducto(ActionEvent e) {

        //Envía los nuevos datos al servidor
        Task<Boolean> task = new Task<Boolean>() {
            @Override
            protected Boolean call() throws Exception {

                ArrayList<Object> nuevosDatos = new ArrayList<Object>();

                nuevosDatos.add(productoCargado);
                nuevosDatos.add(comboSeleccionarEstablListado.getValue());

                nuevosDatos.add(nombreProducto.getText());
                nuevosDatos.add(descripcionProducto.getText());
                nuevosDatos.add(precioProducto.getText());
                nuevosDatos.add(stockProducto.getText());

                byte[] imagenBytes = FileUtils.imageToBytes(imagenProducto.getImage(), "png");
                nuevosDatos.add(imagenBytes);

                ArrayList<String> categorias = new ArrayList<>(listViewCategSeleccionadas.getItems());
                nuevosDatos.add(categorias);

                Message peticion = new Message("PRODUCTO", "ACTUALIZAR_DATOS_PRODUCTO", nuevosDatos);
                boolean respuesta = false;
                try {
                    App.out.writeObject(peticion);
                    Message mensajeRespuesta = (Message) App.in.readObject();
                    respuesta = (boolean) mensajeRespuesta.getData().get(0);

                    if (respuesta) {
                        panelAltaProducto.setVisible(false);
                        rellenarProductos(e);
                        //btnLabelUsuario.setText(nombreUsuarioInfo.getText());
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
    private void actualizarDatosTrabajador(ActionEvent e) {

        //Envía los nuevos datos al servidor
        Task<Boolean> task = new Task<Boolean>() {
            @Override
            protected Boolean call() throws Exception {

                ArrayList<Object> nuevosDatos = new ArrayList<Object>();

                nuevosDatos.add(trabajadorCargado);

                nuevosDatos.add(nombreUsuario.getText());
                nuevosDatos.add(correoUsuario.getText());
                nuevosDatos.add(passwUsuario.getText());

                ArrayList<String> establecimientos = new ArrayList<>(listViewEstablecimientosTrabajador.getItems());
                nuevosDatos.add(establecimientos);

                Message peticion = new Message("USUARIO", "ACTUALIZAR_DATOS_TRABAJADOR", nuevosDatos);
                boolean respuesta = false;
                try {
                    App.out.writeObject(peticion);
                    Message mensajeRespuesta = (Message) App.in.readObject();
                    respuesta = (boolean) mensajeRespuesta.getData().get(0);

                    if (respuesta) {
                        panelAltaUsuario.setVisible(false);
                        rellenarTrabajadores(e);
                        //btnLabelUsuario.setText(nombreUsuarioInfo.getText());
                    }

                } catch (IOException ex) {
                    ex.printStackTrace();
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

        });

        Thread thread = new Thread(task);
        thread.start();

    }
    
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
                //alert.setContentText("Compruebe que no está siendo usado en algún pedido.");
                alert.showAndWait();
            }

        });

        Thread thread = new Thread(task);
        thread.start();

    }

    //Envía al servidor el nombre del usuario que cierra sesión, y vuelve a la pantalla de login
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

}
