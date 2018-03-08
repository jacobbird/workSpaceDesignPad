/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package designpad;

/**
 *
 * @author Jacob
 */
public class EquSideException extends Exception {

    /**
     * Creates a new instance of <code>EquSideException</code> without detail
     * message.
     */
    public EquSideException() {
        
    }

    /**
     * Constructs an instance of <code>EquSideException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public EquSideException(String msg) {
        super(msg);
    }
}
