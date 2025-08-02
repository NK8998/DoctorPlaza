/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.DoctorPlaza.Frontend.service;

import com.fasterxml.jackson.databind.ObjectMapper;
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

    private static final HttpClient client = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(10))
            .build();

    private static final ObjectMapper mapper = new ObjectMapper();

    public static <T, R> R sendRequest(
            String url,
            T requestBody,
            String method,
            Class<R> responseType
    ) throws IOException, InterruptedException {

        HttpRequest request;

        // Default to empty body for GET
        HttpRequest.BodyPublisher body = requestBody != null
                ? HttpRequest.BodyPublishers.ofString(mapper.writeValueAsString(requestBody))
                : HttpRequest.BodyPublishers.noBody();

        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json");

        switch (method.toUpperCase()) {
            case "POST" -> request = builder.POST(body).build();
            case "PUT" -> request = builder.PUT(body).build();
            case "PATCH" -> request = builder.method("PATCH", body).build();
            case "DELETE" -> request = builder.method("DELETE", body).build();
            case "GET" -> request = builder.GET().build();
            default -> throw new IllegalArgumentException("Unsupported method: " + method);
        }

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() >= 200 && response.statusCode() < 300) {
            return mapper.readValue(response.body(), responseType);
        } else {
            throw new IOException("HTTP error: " + response.statusCode() + " - " + response.body());
        }
    }
}
