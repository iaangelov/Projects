/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bg.uni_sofia.fmi.oopjava.exceptions;

/**
 *
 * @author John
 */
public class TooManyTriesException extends Exception{

    public TooManyTriesException() {
    }

    public TooManyTriesException(String message) {
        super(message);
    }

    public TooManyTriesException(String message, Throwable cause) {
        super(message, cause);
    }

    public TooManyTriesException(Throwable cause) {
        super(cause);
    }

    public TooManyTriesException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}
