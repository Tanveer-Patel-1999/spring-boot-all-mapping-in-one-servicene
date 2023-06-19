package com.tanveer.onetoone.exception;

public class CartException extends RuntimeException{
    private static final long serialVersionUID = -7210431808899939306L;

    public CartException(String message)
    {
        super(message);
    }

    public CartException(String message, Throwable cause)
    {
        super(message,cause);
    }

    public CartException(Throwable cause) {
        super(cause);
    }
}
