/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.example.DoctorPlaza.Frontend.controllers;

import com.example.DoctorPlaza.Backend.dto.LoginRequest;
import com.example.DoctorPlaza.Frontend.SceneManager;
import com.example.DoctorPlaza.Frontend.dto.SignupRequest;
import com.example.DoctorPlaza.Frontend.dto.UserResponse;
import com.example.DoctorPlaza.Frontend.service.HttpService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class SignInController implements Initializable {

    @FXML
    private Button btnSignUp;
    @FXML
    private Button btnSignIn;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtPassword;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnSignUpAction(ActionEvent event) {
        SceneManager.switchScene("com/example/DoctorPlaza/Frontend/auth/SignUP.fxml", new SignUpController());
        System.out.println("Switching scenes");
    }

    @FXML
    private void handleSignIn(ActionEvent event) {
        LoginRequest request = new LoginRequest();
        request.setEmail(txtEmail.getText());
        request.setPassword(txtPassword.getText());

        try {
            String url = "http://localhost:8080/auth/login";
            String method = "POST";
            UserResponse response = HttpService.sendRequest(
                url,
                request,
                method,
                UserResponse.class // or whatever your backend returns
            );

            // Do something with response
            System.out.println("Success: " + response);

        } catch (Exception e) {
            e.printStackTrace();
            // Show error to user
        }
    }
    
}
