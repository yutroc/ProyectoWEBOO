/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controler;

import DTO.CategoriaDTO;
import DTO.CompraDTO;
import java.util.ArrayList;
import DTO.ProductoDTO;
import DTO.UsuarioDTO;
import java.sql.SQLException;
import persistance.DAO;
import utils.DAOException;
import DTO.CompraProductoDTO;

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

    public ArrayList<ProductoDTO> obtenerTodosProductos(String nameProducto) throws DAOException {
        return dao.obtenerTodosProductos(nameProducto);
    }
    public ArrayList<ProductoDTO> obtenerTodosProductos() throws DAOException {
        return dao.obtenerTodosProductos();
    }
    public ArrayList<CategoriaDTO> obtenerTodosCategorias(String nameCategoria) throws DAOException {
        return dao.obtenerTodosCategoria(nameCategoria);
    }

    public UsuarioDTO validarUser(String nombre, String pass) throws DAOException {
        //System.out.println("PASOcontrolador");
        return dao.getCustomer(nombre, pass);
    }

    public ArrayList<ProductoDTO> obtenerProductosSimilares(int idCategoria) throws DAOException {
        return dao.obtenerProductosSimilares(idCategoria);
    }

    public void crearProducto(ProductoDTO p) throws DAOException, SQLException {
        dao.crearProducto(p);
    }

    public void crearCategoria(CategoriaDTO p) throws DAOException, SQLException {
        dao.crearCategoria(p);
    }

    public void actualizarCategoria(CategoriaDTO p) throws DAOException, SQLException {
        dao.actualizarCategoria(p);
    }

    public void eliminarCategoria(CategoriaDTO p) throws DAOException, SQLException {
        dao.eliminarCategoria(p);
    }

    public ArrayList<CompraDTO> obtenerTodasCompras() throws DAOException {
        return dao.obtenerCompras();
    }

    public void editarProducto(ProductoDTO p) throws DAOException, SQLException {
        dao.editarProducto(p);
    }
    
    public void eliminarProducto(ProductoDTO p) throws DAOException, SQLException {
        dao.eliminarProducto(p);
    }

    public void eliminarCompra(CompraDTO c) throws SQLException, DAOException {
        dao.eliminarCompra(c);
    }
     public String crearUsuario(UsuarioDTO user)throws DAOException, SQLException{
        //System.out.println("PASOOOOOOOOOOOO");
        //System.out.println("hola "+ user.toString());
        return dao.crearUsuario(user);
    }
    public ArrayList<UsuarioDTO> obtenerUsuarios(String name) throws DAOException, SQLException{
        return dao.obtenerUsuarios(name);
    }
    public String actualizarUsuario(UsuarioDTO user) throws DAOException, SQLException{
        return dao.actualizarUsuario(user);
    }
    public void eliminarUsuario(UsuarioDTO user) throws DAOException, SQLException{
        dao.eliminarUsuario(user);
    }
    public void actualizarOferta(ProductoDTO p) throws DAOException, SQLException{
         dao.actualizarOferta(p);
    }
     public ArrayList<ProductoDTO> obtenerProductosPorCategoria(int idCategoria) throws DAOException {
        return dao.obtenerProductosPorCategoria(idCategoria);
    }
      public ArrayList<CompraProductoDTO> obtenerCarritos(String idUsuario) throws DAOException {
        return dao.obtenerCarritos(idUsuario);
    }
    public ArrayList<String> tieneCarro(String idUsuario) throws DAOException{
        System.out.println(idUsuario+" Controller");
        return dao.tieneCarro(idUsuario);
    }
    public ArrayList<String> obtenerIdCarrito(String idUsuario)throws DAOException{
        return dao.obtenerIdCarrito(idUsuario);
    }

    public void CrearCarro(String idUsuario) throws DAOException {
        dao.CrearCarro(idUsuario);
    }

    public ArrayList<String> existeEnCarro(int idProducto, int idCarro) throws DAOException {
        return dao.existeEnCarro(idProducto,idCarro);
    }

    public void agregarACarro(int idProducto, int idCarro, ArrayList<ProductoDTO> productos)throws DAOException {
        dao.agregarACarro(idProducto,idCarro, productos);
    }

    public ArrayList<CompraProductoDTO> obtenerProductos(int idCarro) throws DAOException {
        return dao.obtenerProductos(idCarro);
    }

    public void eliminarDeCarrito(int idVenta) throws DAOException {
        dao.elimnarDeCarrito(idVenta);
    }

    public void updateCant(int cantidad, int idVentaProducto)throws DAOException {
        dao.updateCant(cantidad,idVentaProducto);
    }

    public void cambiarCarro(int idCarro)throws DAOException {
        dao.cambiarCarro(idCarro);
    }

}
