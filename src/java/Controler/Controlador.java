/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controler;

import java.util.ArrayList;
import DTO.ProductoDTO;
import DTO.UsuarioDTO;
import persistance.DAO;
import utils.DAOException;

/**
 *
 * @author Sebastian
 */
public class Controlador {

    public Controlador ctrl;
    public DAO dao = new DAO();

    public Controlador getInstance() {
        if (ctrl == null) {
            this.ctrl = new Controlador();
            return this.ctrl;
        } else {
            return this.ctrl;
        }
    }
    public ArrayList<ProductoDTO> obtenerTodosProductos() throws DAOException{
        return dao.obtenerTodosProductos();         
    }
    public UsuarioDTO validarUser(String nombre,String pass) throws DAOException{
        System.out.println("PASOcontrolador");
        return dao.getCustomer(nombre, pass); 
    }
    
    public ArrayList<ProductoDTO> obtenerProductosSimilares(int idCategoria) throws DAOException {
        return dao.obtenerProductosSimilares(idCategoria);
    }
    
    public void crearProducto(ProductoDTO p) throws DAOException{
       dao.crearProducto(p);
    }
}
