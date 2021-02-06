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
public class SelectionNameBusyException extends RuntimeException{
    
    private String msg;

    public SelectionNameBusyException() {
        msg = "Busy selection name.";
    }

    public SelectionNameBusyException(String message) {
        super(message);
    }
    
    
    
    
    
}
