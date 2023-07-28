package com.webknot.usermanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {

    public enum Status {
        SUCCESS,
        FAILURE,
    }
    private String message;
    private Status status;
}
