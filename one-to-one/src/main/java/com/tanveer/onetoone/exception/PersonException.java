package com.tanveer.onetoone.exception;

public class PersonException extends RuntimeException {

    private static final long serialVersionUID = -7210431808899939306L;

    public PersonException(String message)
    {
        super(message);
    }

    public PersonException(String message, Throwable cause)
    {
        super(message,cause);
    }

    public PersonException(Throwable cause) {
        super(cause);
    }
}
