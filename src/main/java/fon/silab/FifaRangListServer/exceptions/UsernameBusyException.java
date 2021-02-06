/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fon.silab.FifaRangListServer.exceptions;

/**
 *
 * @author Veljko
 */
public class UsernameBusyException extends RuntimeException{
    
    private String msg;

    public UsernameBusyException() {
    }

    public UsernameBusyException(String message) {
        super(message);
    }
}