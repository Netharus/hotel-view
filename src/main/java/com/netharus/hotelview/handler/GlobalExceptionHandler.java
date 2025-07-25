package com.netharus.hotelview.handler;

import com.netharus.hotelview.dto.response.ErrorResponse;
import com.netharus.hotelview.exceptions.HotelNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(HotelNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleHotelNotFoundException(HotelNotFoundException ex,
                                                                      HttpServletRequest request) {
        log.error(ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder()
                .title("Resource not found")
                .detail(ex.getMessage())
                .status(HttpStatus.NOT_FOUND.value())
                .instance(request.getRequestURI())
                .build());
    }
}
