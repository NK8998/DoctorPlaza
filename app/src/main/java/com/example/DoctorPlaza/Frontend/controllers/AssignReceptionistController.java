/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.example.DoctorPlaza.Frontend.controllers;

import com.example.DoctorPlaza.Frontend.Enums.UserRole;
import com.example.DoctorPlaza.Frontend.SceneManager;
import com.example.DoctorPlaza.Frontend.dto.AllUsersResponse;
import com.example.DoctorPlaza.Frontend.dto.AssignReceptionistRequest;
import com.example.DoctorPlaza.Frontend.dto.StringMessageResponse;
import com.example.DoctorPlaza.Frontend.tasks.HttpTask;
import static com.example.DoctorPlaza.Frontend.utils.Utils.showError;
import static com.example.DoctorPlaza.Frontend.utils.Utils.showInfo;
import com.fasterxml.jackson.core.type.TypeReference;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.stream.Collectors;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class AssignReceptionistController implements Initializable {

    @FXML
    private Button btnAdminDashboard;
    @FXML
    private Button btnPendingApprovals;
    @FXML
    private Button btnUserManagement;
    @FXML
    private Button btnAssignReceptionist;
    @FXML
    private VBox doctorsContainer;
    @FXML
    private VBox receptionistsContainer;
    
    // Store selected UUIDs
    private UUID selectedDoctorId;
    private UUID selectedReceptionistId;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fetchActiveUsers();
    }

    private void fetchActiveUsers() {
        HttpTask<Void, List<AllUsersResponse>> task = new HttpTask<>(
            "/admin/active-users",
            null,
            "GET",
            new TypeReference<List<AllUsersResponse>>() {}
        );

        task.setOnSucceeded(e -> {
            List<AllUsersResponse> users = task.getValue();
            if (users != null) {
                populateDoctorsAndReceptionists(users);
            }
        });

        task.setOnFailed(e -> {
            System.err.println("Failed to fetch active users: " + task.getException());
        });

        new Thread(task).start();
    }
    
    private void assignReceptionist(){
        btnAssignReceptionist.setDisable(true);
        //validate both exist first.
        if(selectedDoctorId == null || selectedReceptionistId == null){
            showError("Both doctor ID and receptionist ID must be provided");
            return;
        }
        
        AssignReceptionistRequest request = new AssignReceptionistRequest();
        request.setDoctor_id(selectedDoctorId);
        request.setReceptionist_id(selectedReceptionistId);
        
        HttpTask<AssignReceptionistRequest, StringMessageResponse> task = new HttpTask<>(
            "/admin/assign-receptionist",
            request,
            "PATCH",
            new TypeReference<StringMessageResponse>() {}
        );

        task.setOnSucceeded(e -> {
            showInfo("Receptionist successfully assinged");
            btnAssignReceptionist.setDisable(false);
        });

        task.setOnFailed(e -> {
            btnAssignReceptionist.setDisable(false);
            showError("Failed to assign receptionist");
            System.err.println("Failed to assign receptionist: " + task.getException());
        });
        
        

        new Thread(task).start();
        
    }

    private void populateDoctorsAndReceptionists(List<AllUsersResponse> users) {
        List<AllUsersResponse> doctors = users.stream()
            .filter(u -> u.getRole() == UserRole.DOCTOR)
            .collect(Collectors.toList());

        List<AllUsersResponse> receptionists = users.stream()
            .filter(u -> u.getRole() == UserRole.RECEPTIONIST)
            .collect(Collectors.toList());

        Platform.runLater(() -> {
            doctorsContainer.getChildren().clear();
            receptionistsContainer.getChildren().clear();

            for (AllUsersResponse doctor : doctors) {
                doctorsContainer.getChildren().add(createUserEntry(doctor, true));
            }

            for (AllUsersResponse receptionist : receptionists) {
                receptionistsContainer.getChildren().add(createUserEntry(receptionist, false));
            }
        });
    }

    private Node createUserEntry(AllUsersResponse user, boolean isDoctor) {
        VBox box = new VBox(5);
        box.setPadding(new Insets(10));
        box.setSpacing(5);
        box.setStyle("-fx-background-color: #ffffff; -fx-border-color: #cccccc; -fx-border-radius: 5; -fx-background-radius: 5;");
        box.setCursor(Cursor.HAND);

        Label nameLabel = new Label(user.getName());
        nameLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");
        Label emailLabel = new Label(user.getEmail());
        emailLabel.setStyle("-fx-text-fill: gray;");

        if (isDoctor && user.getSpecialization() != null) {
            Label specLabel = new Label("Specialization: " + user.getSpecialization());
            specLabel.setStyle("-fx-font-size: 12px;");
            box.getChildren().addAll(nameLabel, emailLabel, specLabel);
        } else {
            box.getChildren().addAll(nameLabel, emailLabel);
        }

        box.setOnMouseClicked(event -> {
            if (isDoctor) {
                selectedDoctorId = user.getId();
                highlightSelection(doctorsContainer, box);
            } else {
                selectedReceptionistId = user.getId();
                highlightSelection(receptionistsContainer, box);
            }
            System.out.println("Selected " + (isDoctor ? "Doctor: " : "Receptionist: ") + user.getId());
        });

        return box;
    }

    private void highlightSelection(VBox container, VBox selectedBox) {
        for (Node node : container.getChildren()) {
            node.setStyle("-fx-background-color: #ffffff; -fx-border-color: #cccccc; -fx-border-radius: 5; -fx-background-radius: 5;");
        }
        selectedBox.setStyle("-fx-background-color: #e0f7fa; -fx-border-color: #00796b; -fx-border-width: 2; -fx-border-radius: 5; -fx-background-radius: 5;");
    }

    @FXML
    private void handleAdminDashboard(ActionEvent event) {
        // Navigation logic
    }

    @FXML
    private void handlePendingApprovals(ActionEvent event) {
        // Navigation logic
        SceneManager.switchScene("com/example/DoctorPlaza/Frontend/admin/pendingApprovals.fxml", new PendingApprovalsController());
    }

    @FXML
    private void handleUserManagement(ActionEvent event) {
        // Navigation logic
        SceneManager.switchScene("com/example/DoctorPlaza/Frontend/admin/userManagement.fxml", new UserManagementController());

    }

    @FXML
    private void handleAssignReceptionist(ActionEvent event) {
        // Navigation logic
        SceneManager.switchScene("com/example/DoctorPlaza/Frontend/admin/pendingApprovals.fxml", new PendingApprovalsController());

    }

    @FXML
    private void onAssignReceptionist(ActionEvent event) {
        assignReceptionist();
    }
}
