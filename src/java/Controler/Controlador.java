/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controler;

import java.util.ArrayList;
import DTO.ProductoDTO;
import DTO.UsuarioDTO;
import persistance.DAO;
import persistance.PostgresDAOCustomer;
import utils.DAOException;

/**
 *
 * @author Sebastian
 */
public class Controlador {

    public Controlador ctrl;
    public PostgresDAOCustomer daoCustomer;

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
    public UsuarioDTO validarUser(String nombre,String pass) throws DAOException{
        return daoCustomer.getCustomer(nombre, pass); 
    }
}
