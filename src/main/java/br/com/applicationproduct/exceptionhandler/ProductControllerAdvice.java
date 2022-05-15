package br.com.applicationproduct.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

@ControllerAdvice(basePackages = "br.com.applicationproduct.controller")
public class ProductControllerAdvice {

    @ResponseBody
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<MessageExceptionHandler> productNotFound(ProductNotFoundException productNotFoundException){
        MessageExceptionHandler error = new MessageExceptionHandler(
                new Date(), HttpStatus.NOT_FOUND.value(), "Produto não encontrado");
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MessageExceptionHandler> argumentsNotValid(MethodArgumentNotValidException notValidException){

        BindingResult result = notValidException.getBindingResult();
        List<FieldError> fieldErrorList = result.getFieldErrors();

        StringBuilder stringBuilder = new StringBuilder("Os campos não podem ser nulos: ");
        for (FieldError fieldError : fieldErrorList){
            stringBuilder.append(" | ");
            stringBuilder.append(" -> ");
            stringBuilder.append(fieldError.getField());
            stringBuilder.append(" <- ");
        }

        MessageExceptionHandler messageExceptionHandler = new MessageExceptionHandler(
                new Date(), HttpStatus.BAD_REQUEST.value(), stringBuilder.toString());
        return new ResponseEntity<>(messageExceptionHandler, HttpStatus.BAD_REQUEST);

    }
}
