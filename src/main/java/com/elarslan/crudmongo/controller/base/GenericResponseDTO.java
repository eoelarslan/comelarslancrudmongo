package com.elarslan.crudmongo.controller.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

public class GenericResponseDTO<T> {

    private String message;
    private HttpStatus status;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<T> results;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object singleResult;

    public GenericResponseDTO(HttpStatus status, String message, List<T> results) {
        this.status = status;
        this.message = message;

        if (results != null) {
            this.results = results;
        } else {
            this.results = new ArrayList<>();
        }
    }

    public GenericResponseDTO(HttpStatus status, String message, Object singleResult) {
        this.status = status;
        this.message = message;

        if (singleResult != null) {
            this.singleResult = singleResult;
        } else {
            this.singleResult = new ArrayList<>();
        }
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public List<T> getResults() {
        return results;
    }

    public void setResults(ArrayList<T> results) {
        this.results = results;
    }

    public Object getSingleResult() {
        return singleResult;
    }

    public void setSingleResult(Object singleResult) {
        this.singleResult = singleResult;
    }
}
