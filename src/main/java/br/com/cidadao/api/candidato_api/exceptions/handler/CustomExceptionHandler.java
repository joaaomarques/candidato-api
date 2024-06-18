package br.com.cidadao.api.candidato_api.exceptions.handler;

import br.com.cidadao.api.candidato_api.exceptions.custom.DataIntegrityException;
import br.com.cidadao.api.candidato_api.exceptions.custom.ObjetoNaoEncontradoException;
import br.com.cidadao.api.candidato_api.exceptions.StandardError;
import br.com.cidadao.api.candidato_api.exceptions.ValidationError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(ObjetoNaoEncontradoException.class)
    public ResponseEntity<StandardError> handleNotFoundException(ObjetoNaoEncontradoException ex, WebRequest request) {

        StandardError standardError = new StandardError(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                System.currentTimeMillis());

         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(standardError);


    }

    @ExceptionHandler(DataIntegrityException.class)
    public ResponseEntity<StandardError> dataIntegrity(DataIntegrityException ex, WebRequest request) {

        StandardError standardError = new StandardError(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standardError);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> methodArgNotValid(MethodArgumentNotValidException e, HttpServletRequest request) {

        ValidationError error = new ValidationError(
                HttpStatus.BAD_REQUEST.value(),
                "Erro de validação",
                System.currentTimeMillis());

       for(FieldError x : e.getBindingResult().getFieldErrors()) {
           error.addError(x.getField(), x.getDefaultMessage());
       }

       return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
