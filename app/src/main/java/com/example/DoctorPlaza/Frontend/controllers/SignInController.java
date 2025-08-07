/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.example.DoctorPlaza.Frontend.controllers;

import com.example.DoctorPlaza.Backend.dto.LoginRequest;
import com.example.DoctorPlaza.Frontend.Enums.UserRole;
import static com.example.DoctorPlaza.Frontend.Enums.UserRole.ADMIN;
import static com.example.DoctorPlaza.Frontend.Enums.UserRole.DOCTOR;
import static com.example.DoctorPlaza.Frontend.Enums.UserRole.RECEPTIONIST;
import com.example.DoctorPlaza.Frontend.SceneManager;
import com.example.DoctorPlaza.Frontend.UserSession;
import com.example.DoctorPlaza.Frontend.dto.SignupRequest;
import com.example.DoctorPlaza.Frontend.dto.UserResponse;
import com.example.DoctorPlaza.Frontend.service.HttpService;
import com.example.DoctorPlaza.Frontend.tasks.HttpTask;
import static com.example.DoctorPlaza.Frontend.utils.Utils.showInfo;
import com.fasterxml.jackson.core.type.TypeReference;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
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
    private PasswordField txtPasswordHidden;
    @FXML
    private TextField txtPasswordVisible;
    @FXML
    private Button btnPasswordVisible;
    @FXML
    private Button btnPasswordHidden;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        txtPasswordVisible.setManaged(false);
        txtPasswordVisible.setVisible(false);
        btnPasswordVisible.setVisible(false);
        btnPasswordVisible.setManaged(false);

        // Keep both fields in sync
        txtPasswordVisible.textProperty().bindBidirectional(txtPasswordHidden.textProperty());
    }    

    @FXML
    private void btnSignUpAction(ActionEvent event) {
        SceneManager.switchScene("com/example/DoctorPlaza/Frontend/auth/SignUP.fxml", new SignUpController());
        System.out.println("Switching scenes");
    }

    @FXML
    private void handleSignIn(ActionEvent event) {
        btnSignIn.setDisable(true);
        
        LoginRequest request = new LoginRequest();
        request.setEmail(txtEmail.getText());
        request.setPassword(txtPasswordHidden.getText());
        
        String url = "/auth/login";
        String method = "POST";
        
        HttpTask<Void, UserResponse> task = new HttpTask(
                url,
                request,
                method,
                new TypeReference<UserResponse>(){} // or whatever your backend returns
        );
        
        
        task.setOnSucceeded(e -> {
            btnSignIn.setDisable(false);

            UserResponse response = task.getValue();
            
            if(response.getIsActive()){
                UserSession session = UserSession.getInstance();
                session.setUserId(response.getId());
                session.setName(response.getName());
                session.setEmail(response.getEmail());
                session.setRole(response.getRole());

                navigate(response.getRole());
            }else{
                showInfo("Wait for your approval.");
            }
        });
        
        task.setOnFailed(e -> {
            btnSignIn.setDisable(false);
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
    
}
