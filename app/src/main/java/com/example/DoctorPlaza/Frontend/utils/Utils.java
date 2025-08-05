/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.DoctorPlaza.Frontend.utils;

import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import javafx.scene.control.Alert;

/**
 *
 * @author HP
 */
public class Utils {
    public static String formatDuration(Duration duration) {
        long totalMinutes = duration.toMinutes();
        long hours = totalMinutes / 60;
        long minutes = totalMinutes % 60;

        if (hours > 0 && minutes > 0) {
            return hours + "h " + minutes + "m";
        } else if (hours > 0) {
            return hours + "h";
        } else {
            return minutes + "m";
        }
    }
        
    public static String formatTime(Instant time) {
        return DateTimeFormatter.ofPattern("h:mm a")
            .withZone(ZoneId.systemDefault())
            .format(time);
    }
    
    public static void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void showInfo(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }



        
}
