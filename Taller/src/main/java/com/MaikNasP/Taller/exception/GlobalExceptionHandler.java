package com.MaikNasP.Taller.exception;

import com.MaikNasP.Taller.dto.GlobalHttpResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<GlobalHttpResponse<Object>> manejarRuntimeException(RuntimeException ex) {
        String mensaje = ex.getMessage();
        HttpStatus status;

        if (mensaje.contains("no encontrada")) {
            status = HttpStatus.NOT_FOUND; // 404
        } else if (mensaje.contains("CANCELADA") || mensaje.contains("COMPLETADA")) {
            status = HttpStatus.CONFLICT; // 409
        } else {
            status = HttpStatus.BAD_REQUEST; // 400
        }

        GlobalHttpResponse<Object> response = new GlobalHttpResponse<>();
        response.setSuccess(false);
        response.setMensaje(mensaje);
        response.setData(null);

        return ResponseEntity.status(status).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<GlobalHttpResponse<Object>> manejarException(Exception ex) {
        GlobalHttpResponse<Object> response = new GlobalHttpResponse<>();
        response.setSuccess(false);
        response.setMensaje("Error interno del servidor: " + ex.getMessage());
        response.setData(null);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response); // 500
    }
}