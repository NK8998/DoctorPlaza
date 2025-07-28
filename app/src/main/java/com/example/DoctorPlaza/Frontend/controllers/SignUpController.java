/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.example.DoctorPlaza.Frontend.controllers;
import com.example.DoctorPlaza.Frontend.SceneManager;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class SignUpController implements Initializable {

    @FXML
    private BorderPane signupPane;
    @FXML
    private TextField txtUsername;
    @FXML
    private TextField txtEmail;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private ComboBox<?> roleChoiceBox;
    @FXML
    private ComboBox<?> speciallizationChoiceBox;
    @FXML
    private Label errorLabel;
    @FXML
    private PasswordField txtConfirm;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    private void btnSignUpAction(ActionEvent event) {
        // Reset error label
        errorLabel.setText("");
        
        // Get form values
        String username = txtUsername.getText().trim();
        String email = txtEmail.getText().trim();
        String password = txtPassword.getText();
        String confirmPassword = txtConfirm.getText();
        String role = roleChoiceBox.getValue().toString();

        // Validate username
        if (username.isEmpty()) {
            errorLabel.setText("Full name is required.");
            return;
        }
        if (username.length() < 3) {
            errorLabel.setText("Full name must be at least 3 characters.");
            return;
        }

        // Validate email
        if (email.isEmpty()) {
            errorLabel.setText("Email is required.");
            return;
        }
        if (!isValidEmail(email)) {
            errorLabel.setText("Please enter a valid email address.");
            return;
        }

        // Validate password
        if (password.isEmpty()) {
            errorLabel.setText("Password is required.");
            return;
        }
        if (password.length() < 6) {
            errorLabel.setText("Password must be at least 6 characters.");
            return;
        }
        if (!password.equals(confirmPassword)) {
            errorLabel.setText("Passwords do not match.");
            return;
        }

        // Validate role
        if (role == null) {
            errorLabel.setText("Please select a role.");
            return;
        }

        // If all validations pass
        System.out.println("Registration attempt:");
        System.out.println("Name: " + username);
        System.out.println("Email: " + email);
        System.out.println("Role: " + role);
        
        // Show success message (in a real app, this would be after backend confirmation)
        errorLabel.setTextFill(Color.GREEN);
        errorLabel.setText("Registration successful! (Frontend validation passed)");
        
        // Here you would typically call your backend service
        // registerUser(username, email, password, role);
    }

    private void btnSignInAction(ActionEvent event) {
        // Switch to sign in screen
        SceneManager.switchScene("com/example/DoctorPlaza/Frontend/auth/SignIn.fxml", new SignInController());
    }
    
    // Helper method for email validation
    private boolean isValidEmail(String email) {
        // Simple regex for basic email validation
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return email.matches(emailRegex);
    }
    
}
