package org.example.quantumapp.controller;

import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.converter.IntegerStringConverter;
import org.example.quantumapp.HelloApplication;
import org.example.quantumapp.dao.EmpleadoDAO;
import org.example.quantumapp.dao.PedidoDAO;
import org.example.quantumapp.model.Empleado;
import org.example.quantumapp.model.Pedido;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EmpleadoController implements Initializable {
    private Empleado empleado;


    @FXML
    private TextField editEstado;

    @FXML
    private TextField editIMetodoPago;

    @FXML
    private TextField editIdCliente;

    @FXML
    private Button btnCerrarSesion;

    @FXML
    private Label textoCambiante;

    @FXML
    private DialogPane insertarDialogPane;


    PedidoDAO pedidoDAO;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnInsertar;

    @FXML
    private Button btnMostrar;

    @FXML
    private TextField buscarPedido;

    @FXML
    private TableColumn<Pedido, String> estadoCol;

    @FXML
    private TableColumn<Pedido, Timestamp> fechaCol;

    @FXML
    private TableColumn<Pedido, Integer> idClienteCol;

    @FXML
    private TableColumn<Pedido, Integer> idCol;

    @FXML
    private TableColumn<Pedido, Integer> idEmpleadoCol;

    @FXML
    private TableColumn<Pedido, String> metodoCol;

    @FXML
    private TableView<Pedido> tablaPedidos;

    private ObservableList<Pedido> pedidos;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        instances();
        GUI();
        actions();

    }


    public void setEmpleado(Empleado empleado){
        this.empleado = empleado;
        animations();
    }

    public void animations(){
        String texto = textoCambiante.getText();
        String textoPersonalizado = texto +" " + empleado.getNombre().toUpperCase();
        Timeline timeline = new Timeline();
        for (int i = 0; i <=textoPersonalizado.length() ; i++) {
            final int indice = i;
            KeyFrame frame = new KeyFrame(Duration.millis(i * 50), e -> {
             textoCambiante.setText(textoPersonalizado.substring(0, indice));
            });
            timeline.getKeyFrames().add(frame);

            }
        timeline.setOnFinished(event ->{
            PauseTransition pausita = new PauseTransition(Duration.seconds(1.5));
            pausita.setOnFinished(event1 -> {
                Timeline timeline2 = new Timeline();

                String quantumText = "QUANTUM CONTROL PANEL";
                for (int i = 0; i <=quantumText.length() ; i++) {
                    final int indice = i;
                    KeyFrame keyFrame = new KeyFrame(Duration.millis(i * 50),event2-> {
                        textoCambiante.setText(quantumText.substring(0,indice));
                    });
                    timeline2.getKeyFrames().add(keyFrame);

                }
                timeline2.play();
            });
            pausita.play();
        });
        timeline.play();


        }
    public void instances() {
        pedidos = FXCollections.observableArrayList();
        tablaPedidos.setItems(pedidos);

    }


    public void actions(){
        btnCerrarSesion.setOnAction(event ->{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Sesión cerrada");
            alert.showAndWait();
            FXMLLoader loader = null;
            try{
                loader = new FXMLLoader(HelloApplication.class.getResource("login-view.fxml"));
                Parent parent = loader.load();
                LoginController controller= loader.getController();

                Scene scene = new Scene(parent,1024,720);

                Stage loginView = (Stage)btnCerrarSesion.getScene().getWindow();
                loginView.setScene(scene);
                loginView.setTitle("Log in");
                loginView.centerOnScreen();
            }catch (IOException e){
                Alert alert1 = new Alert(Alert.AlertType.ERROR);
                alert1.setHeaderText("Error");
                alert1.setContentText("No se ha podido cerrar la sesión");
                alert1.showAndWait();
            }
        } );


        btnMostrar.setOnAction(event -> {
            try {
                pedidoDAO = new PedidoDAO();
                pedidos.clear();
              pedidos.addAll(pedidoDAO.buscarTodosLosPedidos());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Carga exitosa");
                alert.setContentText("Se han cargado todos los pedidos correctamente");
                alert.showAndWait();

            } catch (SQLException e) {
               Alert alert = new Alert(Alert.AlertType.ERROR);
               alert.setHeaderText("Error");
               alert.setContentText("No se ha podido cargar los pedidos");
               alert.showAndWait();
            }

        });

       btnEliminar.setOnAction(event -> {

           Pedido pedidoSeleccionado = tablaPedidos.getSelectionModel().getSelectedItem();
           if (pedidoSeleccionado!=null){
               try {
                   pedidoDAO = new PedidoDAO();
                   pedidoDAO.eliminarPedido(pedidoSeleccionado.getId());
                   pedidos.remove(pedidoSeleccionado);
                   Alert alert = new Alert(Alert.AlertType.INFORMATION);
                   alert.setHeaderText("Proceso completado");
                   alert.setContentText("Se ha eliminado con éxito el pedido con id "+pedidoSeleccionado.getId());
                   alert.showAndWait();
               } catch (SQLException e) {
                   Alert alert = new Alert(Alert.AlertType.ERROR);
                   alert.setHeaderText("Error");
                   alert.setContentText("No se ha podido eliminar el pedido");
                   alert.showAndWait();
               }

           }
       });

       buscarPedido.textProperty().addListener((observable, antiguoValor, nuevoValor) ->{
           try {
               pedidoDAO = new PedidoDAO();
               pedidos.clear();

               if (nuevoValor == null  || nuevoValor.isEmpty()){
                   pedidos.addAll(pedidoDAO.buscarTodosLosPedidos());
               }else {
                   int id = Integer.parseInt(nuevoValor);
                   Pedido resultado = pedidoDAO.buscarPedido(id);
                   if (resultado!=null){
                       pedidos.add(resultado);
                   }
               }
           }catch (NumberFormatException e){

           }catch (SQLException e){
               Alert alert = new Alert(Alert.AlertType.ERROR);
               alert.setHeaderText("Error");
               alert.setContentText("No se ha podido acceder a la base de datos");
               alert.showAndWait();
           }

       });

       btnInsertar.setOnAction(event -> {
            insertarDialogPane.setVisible(true);
            insertarDialogPane.toFront();

       });

       Button aceptar = (Button) insertarDialogPane.lookupButton(ButtonType.OK);
       Button cancelar = (Button) insertarDialogPane.lookupButton(ButtonType.CANCEL);

       cancelar.setOnAction(event -> {
           insertarDialogPane.setVisible(false);

       });

       aceptar.setOnAction(event -> {

           try {
               int idClient = Integer.parseInt(editIdCliente.getText());
               String metodo = editIMetodoPago.getText();
               if (!(metodo.contains("tarjeta") || metodo.contains("transferencia") || metodo.contains("cripto"))){
                   Alert alert = new Alert(Alert.AlertType.ERROR);
                   alert.setHeaderText("Error");
                   alert.setContentText("Introduzca SOLO 'tarjeta', 'transferencia' o 'cripto'");
                   alert.showAndWait();
                   return;
               }
               String estado = editEstado.getText();
               if (!(estado.contains("pendiente") || estado.contains("enviado") || estado.contains("entregado"))){
                   Alert alert = new Alert(Alert.AlertType.ERROR);
                   alert.setHeaderText("Error");
                   alert.setContentText("Introduzca SOLO 'pendiente', 'enviado' o 'entregado'");
                   alert.showAndWait();
                   return;
               }
               Timestamp fechaActual = new Timestamp(System.currentTimeMillis());
               EmpleadoDAO empleadoDAO = new EmpleadoDAO();
               int idEmpleado = empleadoDAO.obtenerIdEmpleadoPorDni(this.empleado.getDni());
               if (idEmpleado == -1){
                   Alert alert = new Alert(Alert.AlertType.ERROR);
                   alert.setHeaderText("Error");
                   alert.setContentText("Empleado no encontrado en la BD");
                   alert.showAndWait();
                   return;
               }

               Pedido nuevoPedido = new Pedido(0,idEmpleado,idClient,fechaActual,metodo,estado);
               pedidoDAO = new PedidoDAO();
               int idGenerado = pedidoDAO.insertarPedido(nuevoPedido);
               if (idGenerado !=-1){
                   nuevoPedido.setId(idGenerado);
                   pedidos.add(nuevoPedido);
                   insertarDialogPane.setVisible(false);
                   Alert alert = new Alert(Alert.AlertType.INFORMATION);
                   alert.setHeaderText("Proceso completado");
                   alert.setContentText("Se ha introducido un nuevo pedido");
                   alert.showAndWait();
               }
           }catch (NumberFormatException e){
               Alert alert = new Alert(Alert.AlertType.ERROR);
               alert.setHeaderText("Error");
               alert.setContentText("El ID del cliente debe ser un número");
               alert.showAndWait();
           }catch (SQLException e){
               Alert alert  = new Alert(Alert.AlertType.ERROR);
               alert.setHeaderText("Error");
               alert.setContentText("Fallo al conectar con la base de datos");
               alert.showAndWait();
           }
       });



    }
    public void actualizarPedido(Pedido pedidoEditable){
        try {
            pedidoDAO= new PedidoDAO();
            pedidoDAO.actualizarPedido(pedidoEditable);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Proceso completado");
            alert.setContentText("Se ha modificado el pedido con éxito");
            alert.showAndWait();

        }catch (SQLException e){
            Alert alert  = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error");
            alert.setContentText("No se ha podido actualizar el pedido");
            alert.showAndWait();
        }
    }

    public void GUI(){
        // int id, int idEmpleado, int idCliente, Timestamp fecha, String metodoPago, String estado
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        idEmpleadoCol.setCellValueFactory(new PropertyValueFactory<>("idEmpleado"));
        idClienteCol.setCellValueFactory(new PropertyValueFactory<>("idCliente"));
        fechaCol.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        metodoCol.setCellValueFactory(new PropertyValueFactory<>("metodoPago"));
        estadoCol.setCellValueFactory(new PropertyValueFactory<>("estado"));


        tablaPedidos.setEditable(true);


        estadoCol.setCellFactory(TextFieldTableCell.forTableColumn());
        estadoCol.setOnEditCommit(event->{
            Pedido pedido = event.getRowValue();
            String estadoAntiguo  = pedido.getEstado();
            pedido.setEstado(event.getNewValue());
            actualizarPedido(pedido);



        });


        metodoCol.setCellFactory(TextFieldTableCell.forTableColumn());
        metodoCol.setOnEditCommit(event -> {
            Pedido pedido = event.getRowValue();
            String valorAntiguo = pedido.getMetodoPago();
            pedido.setMetodoPago(event.getNewValue());
            actualizarPedido(pedido);

        });


        idClienteCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        idClienteCol.setOnEditCommit(event -> {
            Pedido pedido = event.getRowValue();
            Integer clienteAntiguo = pedido.getIdCliente();
            pedido.setIdCliente(event.getNewValue());
            actualizarPedido(pedido);

        });


        idEmpleadoCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        idEmpleadoCol.setOnEditCommit(event -> {
            Pedido pedido = event.getRowValue();
            Integer empleadoAntiguo = pedido.getIdEmpleado();
            pedido.setIdEmpleado(event.getNewValue());
            actualizarPedido(pedido);

        });

    }
    }



