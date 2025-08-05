package com.example.DoctorPlaza.Frontend.controllers;

import com.example.DoctorPlaza.Frontend.Enums.UserRole;
import com.example.DoctorPlaza.Frontend.SceneManager;
import com.example.DoctorPlaza.Frontend.UserSession;
import com.example.DoctorPlaza.Frontend.dto.SignupRequest;
import com.example.DoctorPlaza.Frontend.dto.UserResponse;
import com.example.DoctorPlaza.Frontend.service.HttpService;
import com.example.DoctorPlaza.Frontend.tasks.HttpTask;
import com.fasterxml.jackson.core.type.TypeReference;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
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
    private PasswordField txtPassword;
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
    @FXML
    private TextField txtPasswordVisible;
    @FXML
    private PasswordField txtPasswordHidden;
    @FXML
    private Button btnPasswordVisible;
    @FXML
    private Button btnPasswordHidden;
    @FXML
    private PasswordField txtConfirmHidden;
    @FXML
    private TextField txtConfirmVisible;
    @FXML
    private Button btnConfirmVisible;
    @FXML
    private Button btnConfirmHidden;


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
        
        txtPasswordVisible.setManaged(false);
        txtPasswordVisible.setVisible(false);
        btnPasswordVisible.setVisible(false);
        btnPasswordVisible.setManaged(false);
        
        txtConfirmVisible.setManaged(false);
        txtConfirmVisible.setVisible(false);
        btnConfirmVisible.setVisible(false);
        btnConfirmVisible.setManaged(false);
        

        // Keep both fields in sync
        txtPasswordVisible.textProperty().bindBidirectional(txtPasswordHidden.textProperty());
        txtConfirmVisible.textProperty().bindBidirectional(txtConfirmHidden.textProperty());

    }

    @FXML
    private void btnSignInAction(ActionEvent event) {
        SceneManager.switchScene("com/example/DoctorPlaza/Frontend/auth/SignIn.fxml", new SignInController());
    }

    @FXML
    private void handleSignUp(ActionEvent event) {
        signUpBtn.setDisable(true);
        
        SignupRequest request = new SignupRequest();
        request.setName(txtUsername.getText());
        request.setEmail(txtEmail.getText());
        request.setPassword(txtPassword.getText());
        request.setRole(roleChoiceBox.getValue());

        if ("DOCTOR".equalsIgnoreCase(request.getRole())) {
            //request.setBio(bioField.getText());
            request.setSpecialization(speciallizationChoiceBox.getValue());
        }
        String url = "/auth/signup";
        String method = "POST";
        HttpTask<Void, UserResponse> task =  new HttpTask(
                url,
                request,
                method,
                new TypeReference<UserResponse>(){} 
        );
        
        task.setOnSucceeded(e -> {
            UserResponse response = task.getValue();
            // Do something with response
            System.out.println("Success: " + response);
            
            UserSession session = UserSession.getInstance();
            session.setUserId(response.getId());
            session.setName(response.getName());
            session.setEmail(response.getEmail());
            session.setRole(response.getRole());
            
            
            navigate(response.getRole());
        });
        
        task.setOnFailed(e -> {
            signUpBtn.setDisable(false);
            //display toast message
            task.getException().printStackTrace();

        });

        new Thread(task).start();
    }
    
    private void navigate(UserRole role){
        
        switch(role){
            case DOCTOR -> SceneManager.switchScene("com/example/DoctorPlaza/Frontend/doctor/dashboard.fxml", new DoctorDashboardController());
            case RECEPTIONIST -> SceneManager.switchScene("com/example/DoctorPlaza/Frontend/receptionist/receptionistDashboard.fxml", new ReceptionistDashboardController());
            case ADMIN -> SceneManager.switchScene("com/example/DoctorPlaza/Frontend/admin/adminDashboard.fxml", new AdminDashboardController());
            default -> throw new IllegalStateException("Unexpected role: " + role);
        }
        
    }

    @FXML
    private void handleTogglePasswordVisibility(ActionEvent event) {
        boolean isVisible = txtPasswordVisible.isVisible();

        txtPasswordVisible.setVisible(!isVisible);
        txtPasswordVisible.setManaged(!isVisible);
        btnPasswordVisible.setVisible(!isVisible);
        btnPasswordVisible.setManaged(!isVisible);
        
        txtPasswordHidden.setVisible(isVisible);
        txtPasswordHidden.setManaged(isVisible);
        btnPasswordHidden.setVisible(isVisible);
        btnPasswordHidden.setManaged(isVisible);

    }

    @FXML
    private void handleToggleConfirmVisibility(ActionEvent event) {
        
        boolean isVisible = txtConfirmVisible.isVisible();

        txtConfirmVisible.setVisible(!isVisible);
        txtConfirmVisible.setManaged(!isVisible);
        btnConfirmVisible.setVisible(!isVisible);
        btnConfirmVisible.setManaged(!isVisible);
        
        txtConfirmHidden.setVisible(isVisible);
        txtConfirmHidden.setManaged(isVisible);
        btnConfirmHidden.setVisible(isVisible);
        btnConfirmHidden.setManaged(isVisible);
    }

}