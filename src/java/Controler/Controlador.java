/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controler;

/**
 *
 * @author Sebastian
 */
public class Controlador {

    private Controlador ctrl;

    private Controlador() {
    }

    public Controlador getInstance() {
        if (ctrl == null) {
            this.ctrl = new Controlador();
            return this.ctrl;
        } else {
            return this.ctrl;
        }
    }
}
