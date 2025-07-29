package com.example.DoctorPlaza.Frontend.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {

    @FXML
    private TextField txtUsername;

    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private PasswordField txtConfirm;

    @FXML
    private ComboBox<String> roleChoiceBox;

    @FXML
    private ComboBox<String> speciallizationChoiceBox;

    @FXML
    private Label errorLabel;

    private final List<String> specializations = Arrays.asList(
            "Cardiologist", "Dermatologist", "Pediatrician", "Surgeon", "Neurologist"
    );

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        roleChoiceBox.getItems().addAll("Doctor", "Receptionist");

        speciallizationChoiceBox.setDisable(true);

        roleChoiceBox.setOnAction(event -> {
            String selectedRole = roleChoiceBox.getValue();
            if ("Doctor".equals(selectedRole)) {
                speciallizationChoiceBox.setDisable(false);
                speciallizationChoiceBox.getItems().setAll(specializations);
            } else {
                speciallizationChoiceBox.setDisable(true);
                speciallizationChoiceBox.getItems().clear();
            }
        });
    }

    // 🔁 Navigate to Sign In
    @FXML
    private void handleSignInClick(ActionEvent event) {
        try {
            Parent signInRoot = FXMLLoader.load(getClass().getResource("/com/example/DoctorPlaza/Frontend/auth/SignIn.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(signInRoot));
            stage.setTitle("Sign In");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            errorLabel.setText("Error loading Sign In page.");
        }
    }
}
