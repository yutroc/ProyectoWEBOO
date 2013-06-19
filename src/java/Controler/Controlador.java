/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controler;

import DTO.CategoriaDTO;
import java.util.ArrayList;
import DTO.ProductoDTO;
import DTO.UsuarioDTO;
import java.sql.SQLException;
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
    public ArrayList<CategoriaDTO> obtenerTodosCategorias() throws DAOException{
        return dao.obtenerTodosCategoria();         
    }
    public UsuarioDTO validarUser(String nombre,String pass) throws DAOException{
        System.out.println("PASOcontrolador");
        return dao.getCustomer(nombre, pass); 
    }
    
    public ArrayList<ProductoDTO> obtenerProductosSimilares(int idCategoria) throws DAOException {
        return dao.obtenerProductosSimilares(idCategoria);
    }
    
    public void crearProducto(ProductoDTO p) throws DAOException, SQLException{
       dao.crearProducto(p);
    }
     public void crearCategoria(CategoriaDTO p) throws DAOException, SQLException{
       dao.crearCategoria(p);
    }
      public void actualizarCategoria(CategoriaDTO p) throws DAOException, SQLException{
       dao.actualizarCategoria(p);
    }
      public void eliminarCategoria(CategoriaDTO p) throws DAOException, SQLException{
       dao.eliminarCategoria(p);
    }
}
