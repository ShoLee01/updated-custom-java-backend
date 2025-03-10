package com.paymentchain.customer.shared.exception;

import jakarta.validation.ConstraintViolation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.util.Set;
import java.util.stream.Collectors;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ResourceValidationException extends RuntimeException{
    public ResourceValidationException(){
        super();
    }

    public ResourceValidationException(String message){
        super();
    }

    public ResourceValidationException(String message, Throwable cause){
        super();
    }

    public <T> ResourceValidationException(String resourceName, Set<ConstraintViolation<T>> violations){
        super(String.format("Not all constraints satisfied for %s: %s", resourceName,
                violations.stream().map(ConstraintViolation::getMessage)
                        .collect(Collectors.joining(". "))));
    }
}