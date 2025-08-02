package com.example.DoctorPlaza.Frontend.controllers;

import com.example.DoctorPlaza.Frontend.Enums.UserRole;
import com.example.DoctorPlaza.Frontend.SceneManager;
import com.example.DoctorPlaza.Frontend.UserSession;
import com.example.DoctorPlaza.Frontend.dto.SignupRequest;
import com.example.DoctorPlaza.Frontend.dto.UserResponse;
import com.example.DoctorPlaza.Frontend.service.HttpService;
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
    @FXML
    private Button signUpBtn;

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

    @FXML
    private void handleSignUp(ActionEvent event) {
        SignupRequest request = new SignupRequest();
        request.setName(txtUsername.getText());
        request.setEmail(txtEmail.getText());
        request.setPassword(txtPassword.getText());
        request.setRole(roleChoiceBox.getValue());

        if ("DOCTOR".equalsIgnoreCase(request.getRole())) {
            //request.setBio(bioField.getText());
            request.setSpecialization(speciallizationChoiceBox.getValue());
        }

        try {
            String url = "http://localhost:8080/auth/signup";
            String method = "POST";
            UserResponse response = HttpService.sendRequest(
                url,
                request,
                method,
                UserResponse.class // or whatever your backend returns
            );

            // Do something with response
            System.out.println("Success: " + response);
            
            UserSession session = UserSession.getInstance();
            session.setUserId(response.getId());
            session.setName(response.getName());
            session.setEmail(response.getEmail());
            session.setRole(response.getRole());
            
            navigate(response.getRole());
            

        } catch (Exception e) {
            e.printStackTrace();
            // Show error to user
        }
    }
    
    private void navigate(UserRole role){
        
        switch(role){
            case DOCTOR -> SceneManager.switchScene("com/example/DoctorPlaza/Frontend/doctor/dashboard.fxml", new DoctorDashboardController());
            case RECEPTIONIST -> SceneManager.switchScene("com/example/DoctorPlaza/Frontend/receptionist/receptionistDashboard.fxml", new ReceptionistDashboardController());
            case ADMIN -> SceneManager.switchScene("com/example/DoctorPlaza/Frontend/admin/adminDashboard.fxml", new AdminDashboardController());
            default -> throw new IllegalStateException("Unexpected role: " + role);
        }
        
    }

}