/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.example.DoctorPlaza.Frontend.controllers;

import com.example.DoctorPlaza.Frontend.SceneManager;

import com.example.DoctorPlaza.Frontend.dto.AllUsersResponse;
import com.example.DoctorPlaza.Frontend.dto.UserResponse;
import com.example.DoctorPlaza.Frontend.tasks.HttpTask;
import com.fasterxml.jackson.core.type.TypeReference;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
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
public class UserManagementController implements Initializable {

    @FXML
    private Button btnAdminDashboard;
    @FXML
    private Button btnPendingApprovals;
    @FXML
    private Button btnUserManagement;
    @FXML
    private Button btnAssignReceptionist;
    @FXML
    private Label lblTotal;
    @FXML
    private Label lblDoctorsTotal;
    @FXML
    private Label lblReceptionistsTotal;
    @FXML
    private Label lblActive;
    @FXML
    private TextField txtSearchUsers;
    @FXML
    private ComboBox<String> rolesComboBox;
    @FXML
    private ComboBox<String> StatusComboBox;
    @FXML
    private VBox usersContainer;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rolesComboBox.getItems().addAll("admin", "doctor", "receptionist");
        StatusComboBox.getItems().addAll("active", "inactive");

        fetchUsers();
    }

    private void fetchUsers() {
        HttpTask<Void, List<AllUsersResponse>> task = new HttpTask<>(
            "/admin/users",
            null,
            "GET",
            new TypeReference<List<AllUsersResponse>>() {}
        );

        task.setOnSucceeded(e -> {
            List<AllUsersResponse> users = task.getValue();
            if (users != null) {
                updateUserStats(users);
                populateUsers(users);
            }
        });

        task.setOnFailed(e -> {
            System.err.println("Failed to fetch users: " + task.getException());
        });

        new Thread(task).start();
    }

    private void updateUserStats(List<AllUsersResponse> users) {
        long total = users.size();
        long doctors = users.stream().filter(u -> "doctor".equalsIgnoreCase(u.getRole().toString())).count();
        long receptionists = users.stream().filter(u -> "receptionist".equalsIgnoreCase(u.getRole().toString())).count();
        long active = users.stream().filter(u -> u.getIsActive()).count();

        lblTotal.setText(String.valueOf(total));
        lblDoctorsTotal.setText(String.valueOf(doctors));
        lblReceptionistsTotal.setText(String.valueOf(receptionists));
        lblActive.setText(String.valueOf(active));
    }

    private void populateUsers(List<AllUsersResponse> users) {
        Platform.runLater(() -> {
            usersContainer.getChildren().clear();
            for (AllUsersResponse user : users) {
                VBox userCard = createUserCard(user);
                usersContainer.getChildren().add(userCard);
            }
        });
    }

    private VBox createUserCard(AllUsersResponse user) {
        VBox card = new VBox(10);
        card.setStyle("-fx-background-color: white; -fx-border-color: lightgray; -fx-border-radius: 10; -fx-padding: 15;");

        // HBox Top Row
        HBox topRow = new HBox(15);
        topRow.setAlignment(Pos.CENTER_LEFT);

        StackPane avatar = new StackPane();
        avatar.setPrefSize(50, 50);
        avatar.setStyle("-fx-background-color: #f24153; -fx-background-radius: 25;");
        Label initials = new Label(getInitials(user.getName()));
        initials.setStyle("-fx-text-fill: white; -fx-font-weight: bold;");
        avatar.getChildren().add(initials);

        VBox nameAndTags = new VBox(3);
        Label name = new Label(user.getName());
        name.setStyle("-fx-font-weight: bold; -fx-font-size: 16px;");
        HBox tags = new HBox(5);
        Label statusLabel = createTagLabel(user.getIsActive() ? "active" : "inactive");
        Label roleLabel = createTagLabel(user.getRole().toString());
        tags.getChildren().addAll(statusLabel, roleLabel);
        nameAndTags.getChildren().addAll(name, tags);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Label email = new Label("📧 " + user.getEmail());
 
        topRow.getChildren().addAll(avatar, nameAndTags, spacer, email);

        // GridPane for specialization and receptionist
        GridPane grid = new GridPane();
        grid.setHgap(40);
        grid.setVgap(5);
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(50);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(50);
        grid.getColumnConstraints().addAll(col1, col2);

        Label specialization = new Label("Specialization: " + (user.getSpecialization() != null ? user.getSpecialization() : "N/A"));

        grid.add(specialization, 0, 0);

        // Button row
        HBox buttonRow = new HBox(10);
        buttonRow.setAlignment(Pos.CENTER_RIGHT);
        Button manageBtn = new Button("Manage Assignments");
        buttonRow.getChildren().add(manageBtn);

        // Add all to card
        card.getChildren().addAll(topRow, grid, buttonRow);
        return card;
    }

    private Label createTagLabel(String text) {
        Label label = new Label(text);
        label.setStyle("-fx-background-color: #f24153; -fx-text-fill: white; -fx-padding: 2 6; -fx-background-radius: 8;");
        return label;
    }

    private String getInitials(String name) {
        if (name == null || name.isEmpty()) return "??";
        String[] parts = name.trim().split("\\s+");
        StringBuilder initials = new StringBuilder();
        for (String part : parts) {
            if (!part.isEmpty()) initials.append(part.charAt(0));
            if (initials.length() == 2) break;
        }
        return initials.toString().toUpperCase();
    }

    @FXML
    private void handleAdminDashboard(ActionEvent event) {
        SceneManager.switchScene("/com/example/DoctorPlaza/Frontend/admin/adminDashboard.fxml", new AdminDashboardController());

        // Add navigation logic here
        SceneManager.switchScene("com/example/DoctorPlaza/Frontend/admin/adminDashboard.fxml", new AdminDashboardController());

    }

    @FXML
    private void handlePendingApprovals(ActionEvent event) {
        SceneManager.switchScene("/com/example/DoctorPlaza/Frontend/admin/pendingApprovals.fxml", new PendingApprovalsController());

        // Add navigation logic here
        SceneManager.switchScene("com/example/DoctorPlaza/Frontend/admin/pendingApprovals.fxml", new PendingApprovalsController());

    }

    @FXML
    private void handleUserManagement(ActionEvent event) {
        System.out.println("alradey in User Management page");
        // Add navigation logic here
    }

    @FXML
    private void handleAssignReceptionist(ActionEvent event) {
        SceneManager.switchScene("/com/example/DoctorPlaza/Frontend/admin/assignReceptionist.fxml", new AssignReceptionistController());
        // Add navigation logic here
        SceneManager.switchScene("com/example/DoctorPlaza/Frontend/admin/assignReceptionist.fxml", new AssignReceptionistController());
    }
}
