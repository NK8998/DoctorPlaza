/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.DoctorPlaza.Frontend.tasks;

import com.example.DoctorPlaza.Frontend.service.HttpService;
import com.fasterxml.jackson.core.type.TypeReference;
import javafx.concurrent.Task;

/**
 *
 * @author HP
 */
public class HttpTask<T, R> extends Task<R> {

    private final String url;
    private final T requestBody;
    private final String method;
    private final TypeReference<R> responseType;

    public HttpTask(String url, T requestBody, String method, TypeReference<R> responseType) {
        this.url = url;
        this.requestBody = requestBody;
        this.method = method;
        this.responseType = responseType;
    }

    @Override
    protected R call() throws Exception {
        return HttpService.sendRequest(url, requestBody, method, responseType);
    }
}
