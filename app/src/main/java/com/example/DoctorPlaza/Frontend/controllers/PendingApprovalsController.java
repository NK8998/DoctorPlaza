/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.example.DoctorPlaza.Frontend.controllers;

import com.example.DoctorPlaza.Frontend.SceneManager;

import com.example.DoctorPlaza.Frontend.dto.AllUsersResponse;
import com.example.DoctorPlaza.Frontend.dto.StringMessageResponse;
import com.example.DoctorPlaza.Frontend.tasks.HttpTask;
import static com.example.DoctorPlaza.Frontend.utils.Utils.showError;
import static com.example.DoctorPlaza.Frontend.utils.Utils.showInfo;
import com.fasterxml.jackson.core.type.TypeReference;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class PendingApprovalsController implements Initializable {

   @FXML
    private Button btnAdminDashboard;
    @FXML
    private Button btnPendingApprovals;
    @FXML
    private Button btnUserManagement;
    @FXML
    private Button btnAssignReceptionist;
    @FXML
    private VBox pendingApprovalsContainer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        fetchPendingApprovals();
    }   
    
    private void fetchPendingApprovals() {
        HttpTask<Void, List<AllUsersResponse>> task = new HttpTask<>(
            "/admin/pending-users",
            null,
            "GET",
            new TypeReference<List<AllUsersResponse>>() {}
        );

        task.setOnSucceeded(e -> {
            List<AllUsersResponse> users = task.getValue();
            renderPendingUsers(users);
        });

        task.setOnFailed(e -> {
            System.out.println("Failed to fetch pending users: " + task.getException().getMessage());
        });

        new Thread(task).start();
    }
    
    private void renderPendingUsers(List<AllUsersResponse> users) {
        Platform.runLater(() -> {
            pendingApprovalsContainer.getChildren().clear();

            for (AllUsersResponse user : users) {
                VBox card = createUserCard(user);
                pendingApprovalsContainer.getChildren().add(card);
            }
        });
    }
    
    private Label createTag(String text) {
        Label tag = new Label(text);
        tag.setStyle("-fx-background-color: #f24153; -fx-text-fill: white; -fx-background-radius: 8; -fx-padding: 2 6;");
        return tag;
    }

    private String getInitials(String name) {
        if (name == null || name.isEmpty()) return "";
        String[] parts = name.split(" ");
        return parts.length >= 2 ? ("" + parts[0].charAt(0) + parts[1].charAt(0)).toUpperCase() : name.substring(0, 2).toUpperCase();
    }

    private void handleApproval(UUID userId, boolean approve) {
        String endpoint = approve 
            ? "/admin/approve-user/" + userId 
            : "/admin/reject-user/" + userId;

        HttpTask<Void, StringMessageResponse> task = new HttpTask<>(
            endpoint,
            null,
            "PATCH",
            new TypeReference<StringMessageResponse>() {}
        );

        task.setOnSucceeded(e -> {
            System.out.println("User " + (approve ? "approved" : "rejected"));
            showInfo("User " + (approve ? "approved" : "rejected"));
            fetchPendingApprovals(); // Refresh list
        });

        task.setOnFailed(e -> {
            showError("Failed to " + (approve ? "approve" : "reject"));
            System.out.println("Failed to " + (approve ? "approve" : "reject") + ": " + task.getException().getMessage());
        });

        new Thread(task).start();
    }


    private VBox createUserCard(AllUsersResponse user) {
        VBox card = new VBox(15);
        card.setStyle("-fx-background-color: white; -fx-border-color: #f24153; -fx-border-radius: 10; -fx-background-radius: 10; -fx-padding: 15;");

        HBox header = new HBox(15);
        header.setAlignment(Pos.CENTER_LEFT);

        StackPane avatar = new StackPane();
        avatar.setPrefSize(50, 50);
        avatar.setStyle("-fx-background-color: #f24153; -fx-background-radius: 25;");
        Label initials = new Label(getInitials(user.getName()));
        initials.setStyle("-fx-text-fill: white; -fx-font-weight: bold;");
        avatar.getChildren().add(initials);

        VBox nameBox = new VBox(3);
        Label nameLabel = new Label(user.getName());
        nameLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 16px;");
        HBox tags = new HBox(5);
        tags.getChildren().addAll(
            createTag(String.valueOf(user.getRole())),
            createTag("high priority")
        );
        nameBox.getChildren().addAll(nameLabel, tags);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        header.getChildren().addAll(avatar, nameBox, spacer);

        GridPane grid = new GridPane();
        grid.setHgap(30);
        grid.setVgap(5);

        String specialization = user.getSpecialization() != null ? user.getSpecialization() : "";
        grid.add(new Label("📧 " + user.getEmail()), 0, 0);
        grid.add(new Label("Specialization: " + specialization), 1, 0);

        HBox buttons = new HBox(10);
        buttons.setAlignment(Pos.CENTER_RIGHT);

        Button approveBtn = new Button("✔ Approve");
        approveBtn.setStyle("-fx-background-color: green; -fx-text-fill: white; -fx-background-radius: 8;");
        approveBtn.setOnAction(e -> handleApproval(user.getId(), true));

        Button rejectBtn = new Button("✘ Reject");
        rejectBtn.setStyle("-fx-background-color: #f77b82; -fx-text-fill: white; -fx-background-radius: 8;");
        rejectBtn.setOnAction(e -> handleApproval(user.getId(), false));

        Button viewBtn = new Button("View Details");

        buttons.getChildren().addAll(approveBtn, rejectBtn, viewBtn);

        card.getChildren().addAll(header, grid, buttons);
        return card;
    }

    @FXML
    private void handleAdminDashboard(ActionEvent event) {
        SceneManager.switchScene("com/example/DoctorPlaza/Frontend/admin/adminDashboard.fxml", new AdminDashboardController());
    }

    @FXML
    private void handlePendingApprovals(ActionEvent event) {
        System.out.println("alradey in Pending Approvals page");
        SceneManager.switchScene("com/example/DoctorPlaza/Frontend/admin/adminDashboard.fxml", new AdminDashboardController());

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
