/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.example.DoctorPlaza.Frontend;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class ReceptionistDashboardController implements Initializable {

    @FXML
    private Button btnDashboard;
    @FXML
    private Button btnRegister;
    @FXML
    private Button btnQueue;
    @FXML
    private Button btnDischarge;
    @FXML
    private Label lblCompleted;
    @FXML
    private Label lblWaiting;
    @FXML
    private FlowPane assignedDcotorsContainer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleDashboard(ActionEvent event) {
    }

    @FXML
    private void handleRegister(ActionEvent event) {
    }

    @FXML
    private void handleQueue(ActionEvent event) {
    }

    @FXML
    private void handleDischarge(ActionEvent event) {
    }
    
}
