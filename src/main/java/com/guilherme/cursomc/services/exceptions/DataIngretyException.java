package com.guilherme.cursomc.services.exceptions;

public class DataIngretyException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public DataIngretyException(String message) {
        super(message);
    }

    public DataIngretyException(String message, Throwable cause) {
        super(message, cause);
    }

}
