package com.tanveer.onetoone.exception;

public class UserNotFoundException extends RuntimeException{

    private static final long serialVersionUID = -7210431808899939306L;

    public UserNotFoundException(String message)
    {
        super(message);
    }

    public UserNotFoundException(String message, Throwable cause)
    {
        super(message,cause);
    }

    public UserNotFoundException(Throwable cause) {
        super(cause);
    }
}
