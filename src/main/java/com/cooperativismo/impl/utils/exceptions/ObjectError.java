package com.cooperativismo.impl.utils.exceptions;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ObjectError {

    private final String message;
    private final String field;
    private final Object parameter;

}
