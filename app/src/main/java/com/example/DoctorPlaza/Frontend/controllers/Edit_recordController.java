/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.example.DoctorPlaza.Frontend.controllers;

import com.example.DoctorPlaza.Frontend.SceneManager;
import com.example.DoctorPlaza.Frontend.UserSession;
import com.example.DoctorPlaza.Frontend.dto.EditMedicalRecordRequest;
import com.example.DoctorPlaza.Frontend.dto.EditMedicalRecordResponse;
import com.example.DoctorPlaza.Frontend.dto.GetMedicalRecordResponse;
import com.example.DoctorPlaza.Frontend.dto.PatientDoctorResponse;
import com.example.DoctorPlaza.Frontend.dto.PatientVisitResponse;
import com.example.DoctorPlaza.Frontend.tasks.HttpTask;
import com.example.DoctorPlaza.Frontend.utils.Utils;
import static com.example.DoctorPlaza.Frontend.utils.Utils.showError;
import static com.example.DoctorPlaza.Frontend.utils.Utils.showInfo;
import com.fasterxml.jackson.core.type.TypeReference;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class Edit_recordController implements Initializable {

    @FXML
    private Button btnDashboard;
    @FXML
    private Button btnPatientQueue;
    @FXML
    private Button btnEditRecord;
    @FXML
    private VBox recordsContainer;
    @FXML
    private VBox editContainer;
    @FXML
    private TextField txtFilter;
    
    List<GetMedicalRecordResponse> list = new ArrayList<>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        getRecords();
        
        txtFilter.textProperty().addListener((observable, oldValue, newValue) -> {
            List<GetMedicalRecordResponse> filtered = list.stream()
                .filter(p -> p.getName().toLowerCase().contains(newValue.toLowerCase()))
                .collect(Collectors.toList());

            recordsContainer.getChildren().clear();
             for (GetMedicalRecordResponse record : filtered) {
                VBox recordBox = createRecord(record);
                recordsContainer.getChildren().add(recordBox);
            }
        });
    }    
    
    
    private void getRecords(){
        UserSession session = UserSession.getInstance();
        String url = "/doctor/records/" + session.getUserId();

        HttpTask<Void, List<GetMedicalRecordResponse>> task = new HttpTask<>(
            url,
            null,
            "GET",
            new TypeReference<>() {}
        );

        task.setOnSucceeded(e -> {
            list = task.getValue();
            insertElements();
        });

        task.setOnFailed(e -> {
            task.getException().printStackTrace();
        });

        new Thread(task).start();
    }
    
    private void updateRecord(EditMedicalRecordRequest request){
        
        if(request.getDiagnosis() == null || request.getDiagnosis().isBlank()) {
            showError("Diagnosis cannot be blank");
            return;
        }
        if(request.getPrescription() == null || request.getPrescription().isBlank()) {
            showError("Prescription cannot be blank");
            return;
        }
        
        String url = "/doctor/records/update/" + request.getRecordId();

        HttpTask<EditMedicalRecordRequest, EditMedicalRecordResponse> task = new HttpTask<>(
            url,
            request,
            "PATCH",
            new TypeReference<>() {}
        );

        task.setOnSucceeded(e -> {
            showInfo("Record updated successfully");
            getRecords();
        });

        task.setOnFailed(e -> {
            showError("Failure when updating record");
            task.getException().printStackTrace();
        });

        new Thread(task).start();
    }

    private void insertElements() {
        Platform.runLater(() -> {
            recordsContainer.getChildren().clear();

            for (GetMedicalRecordResponse record : list) {
                VBox recordBox = createRecord(record);
                recordsContainer.getChildren().add(recordBox);
            }
        });
    }
    
    private VBox createRecord(GetMedicalRecordResponse record){
                VBox recordBox = new VBox(10);
                recordBox.setStyle("-fx-background-color: white; -fx-padding: 20; -fx-background-radius: 8; -fx-border-color: #ddd;");

                // === Header (Name, Date, Edit button) ===
                HBox header = new HBox(10);
                header.setAlignment(Pos.CENTER_LEFT);

                Label nameLabel = new Label(record.getName());
                nameLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");

                Label dateLabel = new Label(Utils.formatTime(record.getCreatedAt())); // or format it

                Region spacer = new Region();
                HBox.setHgrow(spacer, Priority.ALWAYS);

                Button editButton = new Button("✏️ Edit");
                editButton.setStyle("-fx-background-color: #f1f1f1; -fx-border-color: #ccc; -fx-background-radius: 5;");

                header.getChildren().addAll(nameLabel, dateLabel, spacer, editButton);

                // === Diagnosis Section ===
                VBox diagnosisBox = new VBox(5);
                diagnosisBox.getChildren().addAll(
                        new Label("Diagnosis") {{ setStyle("-fx-font-weight: bold;"); }},
                        new Label(record.getDiagnosis())
                );

                // === Clinical Notes Section ===
                VBox notesBox = new VBox(5);
                notesBox.getChildren().addAll(
                        new Label("Clinical Notes") {{ setStyle("-fx-font-weight: bold;"); }},
                        new Label(record.getNotes()) {{
                            setWrapText(true);
                        }}
                );

                // === Medication Section ===
                VBox medsBox = new VBox(5);
                medsBox.getChildren().addAll(
                        new Label("Medications") {{ setStyle("-fx-font-weight: bold;"); }},
                        new Label(record.getPrescription())
                );

                // === Editable Fields (Hidden by default) ===
                VBox editContainer = new VBox(10);
                editContainer.setVisible(false);
                editContainer.setManaged(false); // Hides space when invisible



                // Prescription Field
                HBox prescEdit = new HBox(60);
                prescEdit.setAlignment(Pos.CENTER);
                Label prescLabel = new Label("Prescription:");
                prescLabel.setPrefWidth(70);
                TextField txtEditPrescription = new TextField(record.getPrescription());
                HBox.setHgrow(txtEditPrescription, Priority.ALWAYS);
                prescEdit.getChildren().addAll(prescLabel, txtEditPrescription);

                // Notes Field
                HBox notesEdit = new HBox(60);
                notesEdit.setAlignment(Pos.CENTER);
                Label notesLabel = new Label("Notes:");
                notesLabel.setPrefWidth(70);
                TextField txtEditNotes = new TextField(record.getNotes());
                HBox.setHgrow(txtEditNotes, Priority.ALWAYS);
                notesEdit.getChildren().addAll(notesLabel, txtEditNotes);
                
                // Follow up Field
                HBox followUpEdit = new HBox(60);
                followUpEdit.setAlignment(Pos.CENTER);
                Label followLabel = new Label("Follow Up:");
                followLabel.setPrefWidth(70);
                TextField txtEditFollowUp = new TextField(record.getFollowUp());
                HBox.setHgrow(txtEditFollowUp, Priority.ALWAYS);
                followUpEdit.getChildren().addAll(followLabel, txtEditFollowUp);

                // Save Button
                HBox saveBox = new HBox();
                saveBox.setAlignment(Pos.CENTER_RIGHT);
                Button btnSave = new Button("Save");

                btnSave.setOnAction(evt -> {
                    // You can send updated fields to backend here
                    String updatedPrescription = txtEditPrescription.getText();
                    String updatedNotes = txtEditNotes.getText();
                    String updatedFollowUp = txtEditFollowUp.getText();

                    // Build request and send update via HttpTask (you have infra for this already)
                    System.out.println("Saving for " + record.getName());
                    System.out.println("New Notes: " + updatedNotes);
                    System.out.println("New prescription" + updatedPrescription);
                    System.out.println("New Follow Up: " + updatedFollowUp);
                    
                    EditMedicalRecordRequest request = new EditMedicalRecordRequest();
                    request.setRecordId(record.getId());
                    request.setDoctorId(record.getDoctorId());
                    request.setDiagnosis(record.getDiagnosis());
                    request.setNotes(updatedNotes);
                    request.setFollowUp(updatedFollowUp);
                    request.setPrescription(updatedPrescription);
                    updateRecord(request);
                    // [Send to server]
                });

                saveBox.getChildren().add(btnSave);

                // Add everything to edit container
                editContainer.getChildren().addAll(prescEdit, notesEdit, followUpEdit, saveBox);

                // Toggle visibility on edit
                editButton.setOnAction(evt -> {
                    boolean isVisible = editContainer.isVisible();
                    editContainer.setVisible(!isVisible);
                    editContainer.setManaged(!isVisible);
                });

                // Add everything to record box
                recordBox.getChildren().addAll(header, diagnosisBox, notesBox, medsBox, editContainer);
                
                return recordBox;
        
    }

    
    @FXML
       private void handleDashboard(ActionEvent event) {
        SceneManager.switchScene("com/example/DoctorPlaza/Frontend/doctor/dashboard.fxml", new DoctorDashboardController());
    }

    @FXML
    private void handlePatientQueue(ActionEvent event) {
        
       SceneManager.switchScene("com/example/DoctorPlaza/Frontend/doctor/patient_queue.fxml", new Patient_queueController());
    }

    private void handleAddRecord(ActionEvent event) {
        SceneManager.switchScene("com/example/DoctorPlaza/Frontend/doctor/add_record.fxml", new Add_recordController());
    }

    @FXML
    private void handleEditRecord(ActionEvent event) {
        System.out.println("Already on Edit Record page. ");
    }
    
}
