package com.cooperativismo.impl.utils.exceptions;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class ErrorResponse {

        private final String message;
        private final int code;
        private final String status;
        private final String objectName;
        private final List<ObjectError> errors;

}
