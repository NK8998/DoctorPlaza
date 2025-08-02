/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.DoctorPlaza.Frontend;

import java.io.IOException;
import java.net.URL;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author HP
 */
public class SceneManager {
    private static Stage primaryStage;
    
    public static void setStage(Stage stage){
        primaryStage = stage;
    }
    
    public static void switchScene(String fxmlPath, Object controller){
        try{            
            URL fxmlLocation = SceneManager.class.getClassLoader().getResource(fxmlPath);
            if (fxmlLocation == null) {
                throw new IllegalArgumentException("FXML file not found: " + fxmlPath);
            }
            
            FXMLLoader loader = new FXMLLoader(fxmlLocation);
            //if (controller != null) loader.setController(controller);
            
            Parent newRoot = loader.load();
            
            System.out.println("Switching scenes");

            if (primaryStage.getScene() != null) {
                primaryStage.getScene().setRoot(newRoot);
                ScaleTransition st = new ScaleTransition(Duration.millis(400), newRoot);
                st.setFromX(0.98);
                st.setFromY(0.98);
                st.setToX(1);
                st.setToY(1);
                st.setInterpolator(Interpolator.SPLINE(0.5, 0.0, 0.3, 1.0)); 
                st.play();
            } else {
                primaryStage.setScene(new Scene(newRoot));
            }

            primaryStage.show();
        }catch(IOException e){
           
        }
    }
    
}
