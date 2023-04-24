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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        btnMenu.setOnMouseClicked(event -> togglePanelLateral(panelMenu));
        
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
        panelAltaProducto.setVisible(false);
        panelAltaUsuario.setVisible(false);
        panelInfoUsuario.setVisible(false);
        panelAltaCategoria.setVisible(false);
        
        actualizarDashboard();

    }

    public void actualizarDashboard() {
        //Obtiene del servidor los datos a mostrar en el panel de inicio
        Task<ArrayList<Object>> task = new Task<ArrayList<Object>>() {
            @Override
            protected ArrayList<Object> call() throws Exception {
                ArrayList<Object> data = new ArrayList<Object>();
                data.add(btnLabelUsuario.getText());
                Message peticion = new Message("SERVIDOR","DATOS_ESTABL", data);
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
            db_cajaHoy.setText((double)listaDatos.get(0)+"");
            
            db_trabajadoresOn.setText((int)listaDatos.get(1)+"");
            
            db_totalTrabajadores.setText((long)listaDatos.get(2)+"");
            db_fechaRecarga.setText((String)listaDatos.get(3));
            
            db_totalPedidos.setText((long)listaDatos.get(4)+"");
            db_pedidosFin.setText((long)listaDatos.get(5)+"");
            db_pedidosPen.setText((long)listaDatos.get(6)+"");
        });

        Thread thread = new Thread(task);
        thread.start();
    }

    @FXML
    private void panelAltaProducto(ActionEvent e) {
        labelVentanaActual.setText("Nuevo producto");
        panelAltaProducto.setVisible(true);
        panelInicio.setVisible(false);
        panelAltaUsuario.setVisible(false);
        panelInfoUsuario.setVisible(false);
        panelAltaCategoria.setVisible(false);
        
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
                Message peticion = new Message("USUARIO","GET_DATOS_USUARIO", data);
                
                
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
    public void rellenarCategorias(ActionEvent e){
        
        //Envía el establecimiento al servidor y recibe sus categorías asociadas
        Task<ArrayList<String>> task = new Task<ArrayList<String>>() {
            @Override
            protected ArrayList<String> call() throws Exception {
                ArrayList<Object> data = new ArrayList<Object>();
                
                data.add(comboSeleccionarEstabl.getValue());
                Message peticion = new Message("CATEGORIA","GET_CATEGORIAS", data);
                
                
                ArrayList<String> categoriasEstabl = new ArrayList<String>();
                try {
                    App.out.writeObject(peticion);
                    Message mensajeRespuesta = (Message) App.in.readObject();

                    for(Object o : mensajeRespuesta.getData()){
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
    private void panelAltaCategoria(ActionEvent e) {
        labelVentanaActual.setText("Nueva categoría");
        panelAltaCategoria.setVisible(true);
        panelAltaProducto.setVisible(false);
        panelInicio.setVisible(false);
        panelAltaUsuario.setVisible(false);
        panelInfoUsuario.setVisible(false);
        
        //Carga la información del usuario (en este caso, sus establecimientos)
        Task<ArrayList<String>> task = new Task<ArrayList<String>>() {
            @Override
            protected ArrayList<String> call() throws Exception {
                ArrayList<Object> data = new ArrayList<Object>();
                data.add(btnLabelUsuario.getText());
                Message peticion = new Message("USUARIO","GET_DATOS_USUARIO", data);
                
                
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
    private void panelAltaUsuario(ActionEvent e) {
        labelVentanaActual.setText("Nuevo Usuario");
        panelAltaUsuario.setVisible(true);
        panelAltaProducto.setVisible(false);
        panelInicio.setVisible(false);
        panelInfoUsuario.setVisible(false);
        panelAltaCategoria.setVisible(false);

        //Carga los establecimientos disponibles
        Task<ArrayList<String>> task = new Task<ArrayList<String>>() {
            @Override
            protected ArrayList<String> call() throws Exception {
                //Devuelve los establecimientos del usuario logeado
                ArrayList<Object> datos = new ArrayList<>();
                datos.add(btnLabelUsuario.getText());
                Message peticion = new Message("ESTABLECIMIENTO","GET_ESTABLECIMIENTOS_USUARIO", datos);
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
    private void panelInfoUsuario(ActionEvent e) {
        labelVentanaActual.setText("Perfil de Usuario");
        panelInfoUsuario.setVisible(true);
        panelAltaUsuario.setVisible(false);
        panelAltaProducto.setVisible(false);
        panelInicio.setVisible(false);
        panelAltaCategoria.setVisible(false);

        //Carga la información del usuario
        Task<ArrayList<Object>> task = new Task<ArrayList<Object>>() {
            @Override
            protected ArrayList<Object> call() throws Exception {
                ArrayList<Object> data = new ArrayList<Object>();
                data.add(btnLabelUsuario.getText());
                Message peticion = new Message("USUARIO","GET_DATOS_USUARIO", data);
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
                Message peticion = new Message("ROL","GET_ROLES", new ArrayList<Object>());
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
                Message peticion = new Message("ESTABLECIMIENTO","GET_ESTABLECIMIENTOS", new ArrayList<Object>());
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
            listViewCategSeleccionadas.getItems().remove(listViewCategorias.getSelectionModel().getSelectedItem());
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


                Message peticion = new Message("USUARIO","ACTUALIZAR_DATOS", nuevosDatos);
                boolean respuesta = false;
                try {
                    App.out.writeObject(peticion);
                    Message mensajeRespuesta = (Message) App.in.readObject();
                    respuesta = (boolean) mensajeRespuesta.getData().get(0);
                    
                    if(respuesta){
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
                    
                    Message peticion = new Message("CATEGORIA","INSERTAR", data);
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
                    String imagePath = imagenSeleccionada.toURI().toString();
                    Image image = new Image(imagePath);
                    
                    data.add(image);
                    
                    Message peticion = new Message("PRODUCTO","INSERTAR", data);
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
                    nombreCategoria.setText("");
                    descripcionCategoria.setText("");
                    
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

                    Message peticion = new Message("USUARIO","INSERTAR", data);
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

                Message peticion = new Message("SERVIDOR","LOGOUT", data);
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
