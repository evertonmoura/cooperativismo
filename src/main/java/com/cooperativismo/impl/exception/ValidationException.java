package com.cooperativismo.impl.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ValidationException  extends  RuntimeException{

    public ValidationException() {
        super();
    }
    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }
    public ValidationException(String message) {
        super(message);
    }
    public ValidationException(Throwable cause) {
        super(cause);
    }

}
