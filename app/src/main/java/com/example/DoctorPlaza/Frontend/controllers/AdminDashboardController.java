/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.example.DoctorPlaza.Frontend.controllers;

import com.example.DoctorPlaza.Frontend.SceneManager;
import com.example.DoctorPlaza.Frontend.dto.AllUsersResponse;
import com.example.DoctorPlaza.Frontend.tasks.HttpTask;
import com.fasterxml.jackson.core.type.TypeReference;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class AdminDashboardController implements Initializable {

    @FXML
    private Button btnAdminDashboard;
    @FXML
    private Button btnPendingApprovals;
    @FXML
    private Button btnUserManagement;
    @FXML
    private Button btnAssignReceptionist;
    @FXML
    private VBox pedningApprovalsContainer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        getUsers();
    }    

    private void getUsers(){
        HttpTask<Void, List<AllUsersResponse>> task = new HttpTask<>(
            "/admin/users",
            null,
            "GET",
            new TypeReference<List<AllUsersResponse>>() {}
        );

        task.setOnSucceeded(e -> {
            List<AllUsersResponse> users = task.getValue();
            populatePendingApprovals(users);
        });

        task.setOnFailed(e -> {
            System.out.println("Failed to fetch users: " + task.getException());
        });

        new Thread(task).start();

    }
    
    private void populatePendingApprovals(List<AllUsersResponse> users) {
        Platform.runLater(() -> {
            pedningApprovalsContainer.getChildren().clear();

            for (AllUsersResponse user : users) {
                HBox userEntry = new HBox(20);
                userEntry.setStyle("-fx-padding: 10; -fx-background-color: #f9f9f9; -fx-background-radius: 10;");

                VBox userDetails = new VBox(3);
                Label nameLabel = new Label(user.getName());
                nameLabel.setStyle("-fx-font-weight: bold;");
                Label emailLabel = new Label(user.getEmail());
                userDetails.getChildren().addAll(nameLabel, emailLabel);

                Region spacer = new Region();
                HBox.setHgrow(spacer, Priority.ALWAYS);

                Label roleLabel = new Label(user.getRole().toString());
                roleLabel.setStyle("-fx-background-color: #e0e0e0; -fx-padding: 3 8 3 8; -fx-background-radius: 8;");

                Label statusLabel = new Label(user.getIsActive() ? "approved" : "pending");
                statusLabel.setStyle(user.getIsActive() ? "-fx-text-fill: green;" : "-fx-text-fill: red;" ); // You can change color dynamically if needed

                userEntry.getChildren().addAll(userDetails, spacer, roleLabel, statusLabel);

                pedningApprovalsContainer.getChildren().add(userEntry);
            }
        });
    }

    
    
    @FXML
    private void handleAdminDashboard(ActionEvent event) {
        System.out.println("already in Admin Dashboard page");
    }

    @FXML
    private void handlePendingApprovals(ActionEvent event) {
        SceneManager.switchScene("com/example/DoctorPlaza/Frontend/admin/pendingApprovals.fxml", new PendingApprovalsController());
    }

    @FXML
    private void handleUserManagement(ActionEvent event) {
        SceneManager.switchScene("com/example/DoctorPlaza/Frontend/admin/userManagement.fxml", new UserManagementController());
    }

    @FXML
    private void handleAssignReceptionist(ActionEvent event) {
        SceneManager.switchScene("com/example/DoctorPlaza/Frontend/admin/assignReceptionist.fxml", new AssignReceptionistController());
    }
    
}
