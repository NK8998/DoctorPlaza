/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.DoctorPlaza.Frontend.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.net.http.HttpRequest;

/**
 *
 * @author HP
 */
public class HttpService {

    private static final String baseUrl = "http://localhost:8080";
    
    private static final HttpClient client = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(10))
            .build();

    private static final ObjectMapper mapper = new ObjectMapper();
    
    static {
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }


    public static <T, R> R sendRequest(
        String url,
        T requestBody,
        String method,
        TypeReference<R> responseType
    ) throws IOException, InterruptedException {

        HttpRequest.BodyPublisher body = requestBody != null
                ? HttpRequest.BodyPublishers.ofString(mapper.writeValueAsString(requestBody))
                : HttpRequest.BodyPublishers.noBody();

        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + url))
                .header("Content-Type", "application/json");

        HttpRequest request = switch (method.toUpperCase()) {
            case "POST" -> builder.POST(body).build();
            case "PUT" -> builder.PUT(body).build();
            case "PATCH" -> builder.method("PATCH", body).build();
            case "DELETE" -> builder.method("DELETE", body).build();
            case "GET" -> builder.GET().build();
            default -> throw new IllegalArgumentException("Unsupported method: " + method);
        };

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() >= 200 && response.statusCode() < 300) {
            return mapper.readValue(response.body(), responseType);
        } else {
            throw new IOException("HTTP error: " + response.statusCode() + " - " + response.body());
        }
    }

}
