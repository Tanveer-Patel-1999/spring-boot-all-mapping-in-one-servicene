package com.tanveer.onetoone.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /*
    1 : create an exceptionMethod()
    2 : exceptionMethod() have two parameters
        1 : exception class name
        2 : WebRequest : we can retrieve an url from web request and send the url to the client
    3 : create an Object of ErrorDetails class and fill the details
    4 : return the object
    5 : annotated a method with : @ExceptionHandler(ExceptionClassName.class)
     */

    //handle specific exception
    @ExceptionHandler(StudentException.class)
    public ResponseEntity<?> handleStudentNotFoundException(StudentException exception, WebRequest request){
        ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(), request.getDescription(false) );
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CartException.class)
    public ResponseEntity<?> handleCartNotFoundException(CartException exception, WebRequest request){
        ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(), request.getDescription(false) );
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    //handle global exception
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobalException(Exception exception, WebRequest request){
        ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(), request.getDescription(false) );
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }


//    handle validation exception : MethodArgumentNotValidException
/*
1 : used map<String , String>
2 : pass the MethodArgumentNotValidException : and import it
3 : annotate as methode as :  @ExceptionHandler(MethodArgumentNotValidException.class)
4 : create a Map<> object : Map<String, String> errorMap = new HashMap<>();
5 : used the object of MethodArgumentNotValidException to :
    methodArgumentNotValidException.getBindingResult().getFieldErrors()
             .forEach(error->{
                 errorMap.put(error.getField(),error.getDefaultMessage());
             });
6 : Response status is BadRequest : @ResponseStatus(HttpStatus.BAD_REQUEST)
 */
 @ResponseStatus(HttpStatus.BAD_REQUEST)
 @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> ArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException){
     Map<String, String> errorMap = new HashMap<>();
     methodArgumentNotValidException.getBindingResult().getFieldErrors()
             .forEach(error->{
                 errorMap.put(error.getField(),error.getDefaultMessage());
             });
     return errorMap;
    }
    
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(UserNotFoundException.class)
    public Map<String, String> handleUserNotFound(UserNotFoundException userNotFoundException){
     Map<String, String> errorMap = new HashMap<>();
     errorMap.put("errorMessage",userNotFoundException.getMessage());
     return errorMap;
    }
//    we not used other exception that time used paren Exception.class
}
