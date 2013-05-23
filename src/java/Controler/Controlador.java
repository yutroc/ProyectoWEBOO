/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controler;

import java.util.ArrayList;
import DTO.ProductoDTO;
import persistance.DAO;
import utils.DAOException;

/**
 *
 * @author Sebastian
 */
public class Controlador {

    public Controlador ctrl;

    public Controlador getInstance() {
        if (ctrl == null) {
            this.ctrl = new Controlador();
            return this.ctrl;
        } else {
            return this.ctrl;
        }
    }
    public ArrayList<ProductoDTO> obtenerTodosProductos() throws DAOException{
        return DAO.obtenerTodosProductos();         
    }
}
