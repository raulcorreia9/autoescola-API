package com.rc.autoescola.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponseDetails {
    private int status;
    private String message;
    private LocalDateTime timestamp;
}
