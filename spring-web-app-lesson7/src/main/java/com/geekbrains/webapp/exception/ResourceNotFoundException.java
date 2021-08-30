package com.geekbrains.webapp.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class ResourceNotFoundException extends RuntimeException {
    private String message;
    private Date date;

    public ResourceNotFoundException(String message) {
        this.message = message;
        this.date = new Date();
    }
}
