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
import javafx.scene.control.Button;
import javafx.scene.layout.Region;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleAdminDashboard(ActionEvent event) {
        SceneManager.switchScene("/com/example/DoctorPlaza/Frontend/admin/adminDashboard.fxml", new AdminDashboardController());
    }

    @FXML
    private void handlePendingApprovals(ActionEvent event) {
        SceneManager.switchScene("/com/example/DoctorPlaza/Frontend/admin/pendingApprovals.fxml", new PendingApprovalsController());
    }

    @FXML
    private void handleUserManagement(ActionEvent event) {
        System.out.println("alradey in User Management page");
    }

    @FXML
    private void handleAssignReceptionist(ActionEvent event) {
        SceneManager.switchScene("/com/example/DoctorPlaza/Frontend/admin/assignReceptionist.fxml", new AssignReceptionistController());
    }
    
}
