package com.esd.Yummy.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class ProductNotFoundException extends RuntimeException {
    private final String msg;
}
