package com.esd.Yummy.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class LoginFailedException extends RuntimeException{
    private final String msg;
}
