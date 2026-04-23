package org.example.quantumapp.controller;

import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;
import org.example.quantumapp.model.Empleado;

import java.net.URL;
import java.util.ResourceBundle;

public class EmpleadoController implements Initializable {
    private Empleado empleado;

    @FXML
    private Button btnCerrarSesion;

    @FXML
    private Label textoCambiante;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


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

    }

