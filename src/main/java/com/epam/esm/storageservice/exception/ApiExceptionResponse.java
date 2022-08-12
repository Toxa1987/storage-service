package com.epam.esm.storageservice.exception;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ApiExceptionResponse {
    String message;
    String exception;
}
