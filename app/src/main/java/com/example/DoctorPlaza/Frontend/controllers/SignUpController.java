package com.example.DoctorPlaza.Frontend.controllers;

import com.example.DoctorPlaza.Frontend.SceneManager;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

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
    private PasswordField txtConfirm;
    @FXML
    private ComboBox<String> roleChoiceBox;  // ✅ changed to <String>
    @FXML
    private ComboBox<String> speciallizationChoiceBox; // ✅ changed to <String>
    @FXML
    private Label errorLabel;
    @FXML
    private Button btnSignIn;

    private final List<String> specializations = Arrays.asList(
            "Cardiologist", "Dermatologist", "Pediatrician", "Surgeon", "Neurologist"
    );

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Fill role options
        roleChoiceBox.getItems().addAll("Doctor", "Receptionist");

        // Disable specialization by default
        speciallizationChoiceBox.setDisable(true);

        // Add role change listener
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

    @FXML
    private void btnSignInAction(ActionEvent event) {
        SceneManager.switchScene("com/example/DoctorPlaza/Frontend/auth/SignIn.fxml", new SignInController());
    }
}
