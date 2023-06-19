package com.tanveer.onetoone.exception;

public class StudentException extends RuntimeException{
    private static final long serialVersionUID = -7210431808899939306L;

    public StudentException(String message)
    {
        super(message);
    }

    public StudentException(String message, Throwable cause)
    {
        super(message,cause);
    }

    public StudentException(Throwable cause) {
        super(cause);
    }
}
