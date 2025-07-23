/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.example.DoctorPlaza.Frontend;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import com.example.DoctorPlaza.Frontend.SignInController;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class SignUpController implements Initializable {

    @FXML
    private TextField txtUsername;
    @FXML
    private Button btnSignIn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnSignInAction(ActionEvent event) {
        SceneManager.switchScene("com/example/DoctorPlaza/Frontend/SignIn.fxml", new SignInController());
    }
    
}
