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
public class InvalidCardNumberException extends Exception{

    public InvalidCardNumberException() {
    }

    public InvalidCardNumberException(String message) {
        super(message);
    }

    public InvalidCardNumberException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidCardNumberException(Throwable cause) {
        super(cause);
    }

    public InvalidCardNumberException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}
