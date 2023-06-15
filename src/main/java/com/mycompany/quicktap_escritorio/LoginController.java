package com.mycompany.quicktap_escritorio;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXProgressSpinner;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.dialogs.MFXGenericDialog;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import message.Message;
import utilidades.SHA;

public class LoginController implements Initializable {

    @FXML
    private MFXProgressSpinner spinner;

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

    @FXML
    private MFXTextField campoIP;

    @FXML
    private MFXTextField campoPuerto;

    @FXML
    private AnchorPane panelRed;

    @FXML
    private AnchorPane panelPrincipal;

    @FXML
    private AnchorPane panelRegistroAdmin;

    @FXML
    private MFXTextField campoCorreoAdmin;

    @FXML
    private MFXPasswordField campoPasswAdmin;

    @FXML
    private MFXTextField campoUsuarioAdmin;

    private String nombreUsuario;

    private ArrayList<String> listaRoles = new ArrayList<>();

    private double xOffset = 0;
    private double yOffset = 0;

    /**
     * Inicializa la ventana
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //Añade el evento drag and drop al panel principal y al panel de red
        panelRed.setOnMousePressed(this::onDragPanePressed);
        panelRed.setOnMouseDragged(this::onDragPaneDragged);

        panelRegistroAdmin.setOnMousePressed(this::onDragPanePressed);
        panelRegistroAdmin.setOnMouseDragged(this::onDragPaneDragged);
        
        leerDatosRed();


        

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
        //y ajustar los límites
        panelRed.setLayoutX(newX);
        panelRed.setLayoutY(newY);

        panelRegistroAdmin.setLayoutX(newX);
        panelRegistroAdmin.setLayoutY(newY);

    }

    /**
     * Comprueba si existe el usuario administrador. Si es la primera ejecución
     * del servicio, no existe dicho usuario.
     */
    public void existeAdmin() {
        Task<Boolean> task = new Task<Boolean>() {
            @Override
            protected Boolean call() throws Exception {
                ArrayList<Object> data = new ArrayList<Object>();

                Message peticion = new Message("LOGIN", "EXISTE_ADMIN", data);

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

            if (!respuesta) {

                panelRegistroAdmin.setVisible(true);
            }

        });

        Thread thread = new Thread(task);
        thread.start();

    }

    @FXML
    public void registrarAdmin(ActionEvent e) {

        //Comprueba que los campos no estén vacíos
        if (campoUsuarioAdmin.getText().isEmpty() || campoCorreoAdmin.getText().isEmpty() || campoPasswAdmin.getText().isEmpty()) {
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

                    //Añade rol administrador
                    roles.add("administrador");

                    data.add(campoUsuarioAdmin.getText());
                    data.add(campoCorreoAdmin.getText());
                    data.add(SHA.encrypt(campoPasswAdmin.getText()));
                    data.add(roles);

                    Message peticion = new Message("LOGIN", "REGISTRAR_ADMIN", data);
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
                    alert.setContentText("Administrador dado de alta.");
                    alert.showAndWait();
                    campoUsuarioAdmin.setText("");
                    campoCorreoAdmin.setText("");
                    campoPasswAdmin.setText("");
                    panelRegistroAdmin.setVisible(false);
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Información");
                    alert.setHeaderText("ERROR");
                    alert.setContentText("Compruebe los datos introducidos");
                    alert.showAndWait();
                }

                spinner.setVisible(false);

            });

            Thread thread = new Thread(task);
            thread.start();
        }

    }

    /**
     * Logea al usuario, enviando al servidor el correo y contraseña
     * introducidos
     *
     * @param e Evento
     */
    @FXML
    public void login(ActionEvent e) {
        Task<Boolean> task = new Task<Boolean>() {
            @Override
            protected Boolean call() throws Exception {
                ArrayList<Object> data = new ArrayList<Object>();
                data.add(mailField.getText());
                data.add(SHA.encrypt(passwordField.getText()));
                Message peticion = new Message("LOGIN", "LOGIN", data);

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

        task.setOnRunning(event -> {

            spinner.setVisible(true);

        });

        task.setOnSucceeded(event -> {
            boolean respuesta = task.getValue();

            if (respuesta) {

                cargarApp(e); //Carga la ventana principal de la app

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Login");
                alert.setHeaderText("ERROR");
                alert.setContentText("No existe un usuario con los datos introducidos");
                alert.showAndWait();
            }

            spinner.setVisible(false);

        });

        Thread thread = new Thread(task);
        thread.start();

    }

    /**
     * Carga la ventana principal de la aplicación
     *
     * @param e Evento
     */
    public void cargarApp(ActionEvent e) {

        //Realiza la comprobación del rol, para cargar la ventana del administrador o el propietario
        if (listaRoles.contains("administrador")) {

            try {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Login");
                alert.setHeaderText("Bienvenido, " + nombreUsuario + " [Administrador]");
                alert.setContentText("Sesión iniciada");
                alert.showAndWait();

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("admin.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                AdminController controller = fxmlLoader.<AdminController>getController();
                controller.setUsuario(nombreUsuario);

                //Oculta la ventana de Login
                final Node source = (Node) e.getSource();
                final Stage currentStage = (Stage) source.getScene().getWindow();
                currentStage.close();

                Stage stage = new Stage();
                stage.setTitle("QuickTap - Dashboard");
                stage.setScene(new Scene(root1, 914, 652));
                stage.show();

            } catch (IOException ex) {
                ex.printStackTrace();
            }

        } else if (listaRoles.contains("propietario")) {

            try {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Login");
                alert.setHeaderText("Bienvenido, " + nombreUsuario + " [Propietario]");
                alert.setContentText("Sesión iniciada");
                alert.showAndWait();

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("propietario.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                PropietarioController controller = fxmlLoader.<PropietarioController>getController();
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

        } else {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login");
            alert.setHeaderText("ERROR");
            alert.setContentText("El usuario no tiene permisos para iniciar sesión");
            alert.showAndWait();

        }

    }

    /**
     * Desconecta del servidor
     *
     * @param e Evento
     */
    @FXML
    public void salir(ActionEvent e) {

        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                ArrayList<Object> data = new ArrayList<Object>();

                Message peticion = new Message("LOGIN", "SALIR", data);

                try {
                    App.out.writeObject(peticion);

                } catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
                return null;
            }
        };

        task.setOnRunning(event -> {

            spinner.setVisible(true);

        });

        task.setOnSucceeded(event -> {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("QuickTap - Salir");
            alert.setHeaderText("Saliendo...");
            alert.setContentText("Hasta la próxima!");
            alert.showAndWait();

            spinner.setVisible(false);

            System.exit(1);

        });

        Thread thread = new Thread(task);
        thread.start();

        //Si no hay conexión, también cierra la aplicación
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("QuickTap - Salir");
        alert.setHeaderText("Saliendo...");
        alert.setContentText("Hasta la próxima!");
        alert.showAndWait();

        System.exit(1);

    }

    /**
     * Habilita o deshabilita el panel de red
     *
     * @param e Evento
     */
    @FXML
    public void panelRed(ActionEvent e) {

        if (panelRed.isVisible()) {
            panelRed.setVisible(false);
        } else {
            panelRed.setVisible(true);
        }

    }

    public void leerDatosRed() {

        //Utiliza la api de Google para obtener los datos del establecimiento
        Task<Boolean> task = new Task<Boolean>() {
            @Override
            protected Boolean call() throws Exception {

                boolean exito = false;

                BufferedReader br = null;
                
                try {
                    br = new BufferedReader(new FileReader("datos_red.txt"));
                    String linea;

                    while ((linea = br.readLine()) != null) {

                        String[] valores = linea.split("\\;");
                        String ip = valores[0];
                        int puerto = Integer.parseInt(valores[1]);

                        //Intenta la conexión con los nuevos datos, y cierra el panel
                        exito = App.conectar(ip, puerto);

                    }

                } catch (FileNotFoundException ex) {

                } catch (IOException ex) {
                } finally {

                    try {

                        if (br != null) {
                            br.close();
                        }

                    } catch (IOException ex) {
                    }

                }

                return exito;
            }
        };

        task.setOnRunning(event -> {

            spinner.setVisible(true);

        });

        task.setOnSucceeded(event -> {

            boolean exito = task.getValue();

            //panelRed.setVisible(false);

            //Si no hay conexión con el servidor, abre el panel de red
            if (!exito) {

                panelRed.setVisible(true);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText("Se ha producido un error de conexión con el servidor.");
                alert.showAndWait();

            } else {
                panelRed.setVisible(false);
                
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Conexión");
                alert.setHeaderText("Conectado al servidor");
                alert.showAndWait();

                existeAdmin();

                
            }

            spinner.setVisible(false);

        });

        Thread thread = new Thread(task);
        thread.start();

    }

    /**
     * Guarda los datos de red en el fichero de configuración
     *
     * @param e Evento
     */
    @FXML
    public void guardarDatosRed(ActionEvent e) {

        //Utiliza la api de Google para obtener los datos del establecimiento
        Task<Boolean> task = new Task<Boolean>() {
            @Override
            protected Boolean call() throws Exception {

                boolean exito = false;

                PrintWriter pr = null;

                try {
                    pr = new PrintWriter(new FileWriter("datos_red.txt"));

                    pr.println(campoIP.getText() + ";" + campoPuerto.getText());

                    //Intenta la conexión con los nuevos datos, y cierra el panel
                    exito = App.conectar(campoIP.getText(), Integer.parseInt(campoPuerto.getText()));

                } catch (FileNotFoundException ex) {

                } catch (IOException ex) {
                } finally {

                    pr.close();

                }

                return exito;
            }
        };

        task.setOnRunning(event -> {

            spinner.setVisible(true);

        });

        task.setOnSucceeded(event -> {

            boolean exito = task.getValue();

            panelRed.setVisible(false);

            //Si no hay conexión con el servidor, abre el panel de red
            if (!exito) {

                panelRed.setVisible(true);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText("Se ha producido un error de conexión con el servidor.");
                alert.showAndWait();

            } else {
                panelRed.setVisible(false);

                existeAdmin();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Conexión");
                alert.setHeaderText("Conectado al servidor");
                alert.showAndWait();
            }

            spinner.setVisible(false);

        });

        Thread thread = new Thread(task);
        thread.start();

        
    }

}
