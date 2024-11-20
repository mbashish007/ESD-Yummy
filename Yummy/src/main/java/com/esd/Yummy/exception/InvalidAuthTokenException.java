package com.esd.Yummy.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class InvalidAuthTokenException extends RuntimeException{
    private final String msg;
}
