/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.example.DoctorPlaza.Backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 *
 * @author HP
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class SpringBootLauncher {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
           SpringApplication.run(SpringBootLauncher.class, args);

    }
    
}
