/**
 *
 */
package persistance;

import DTO.CategoriaDTO;
import DTO.ProductoDTO;
import DTO.UsuarioDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import utils.DAOException;

public class DAO {

    public DAO() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<ProductoDTO> obtenerTodosProductos() throws DAOException {
        // iniciar variables
        int idProducto = 0;
        String nombre = "";
        String descripcion = "";
        byte image = 0;
        int stock = 0;
        int valorOferta = 0, precio;
        boolean ofertaActiva = false;
        int idCategoria = 0;
        //_ProductoDTO producto = new ProductoDTO();
        ArrayList<ProductoDTO> listaProductos = new ArrayList<ProductoDTO>();
        // odbc conection
        DAOController dc = new DAOController();
        Connection con = dc.getConnection();
        //statment
        try {
            // setup statement and retrieve results
            PreparedStatement pstmt = con.prepareStatement("SELECT \"idProducto\", \"nombre\", \"descripcion\", \"stock\", \"precio\", \"valorOferta\", \"ofertaActiva\", \"idCategoria\"" + "FROM \"Producto\" ORDER BY \"idProducto\";");
            ResultSet rs = pstmt.executeQuery();
            //create the transfer object using data from rs
            while (rs.next()) {
                idProducto = rs.getInt("idProducto");
                nombre = rs.getString("nombre");
                descripcion = rs.getString("descripcion");
                stock = rs.getInt("stock");
                precio = rs.getInt("precio");
                valorOferta = rs.getInt("valorOferta");
                ofertaActiva = rs.getBoolean("ofertaActiva");
                idCategoria = rs.getInt("idCategoria");
                /*producto.setIdProducto(idProducto);
                 producto.setNombre(nombre);
                 producto.setDescripcion(descripcion);
                 producto.setImage(image);
                 producto.setStock(stock);
                 producto.setValorOferta(valorOferta);
                 producto.setOfertaActiva(ofertaActiva);
                 producto.setIdProducto(idProducto);
                 listaProductos.add(producto);*/
                ProductoDTO producto = new ProductoDTO(idProducto, nombre, descripcion, "", stock, valorOferta, precio, ofertaActiva, idCategoria);
                listaProductos.add(producto);
            }
        } catch (Exception e) {
            e.printStackTrace();

            throw new DAOException(DAOException.IMPOSIBLE_MAKE_QUERY);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                throw new DAOException(DAOException.IMPOSIBLE_CLOSE_CONNECTION);
            }
        }
        return listaProductos;
    }

    public ArrayList<CategoriaDTO> obtenerTodosCategoria() throws DAOException {
        // iniciar variables
        int idCategoria = 0;
        String nombre = "";
        byte imagen = 0;

        //_ProductoDTO producto = new ProductoDTO();
        ArrayList<CategoriaDTO> listaCategoria = new ArrayList<CategoriaDTO>();
        // odbc conection
        DAOController dc = new DAOController();
        Connection con = dc.getConnection();
        //statment
        try {
            // setup statement and retrieve results
            PreparedStatement pstmt = con.prepareStatement("SELECT \"idCategoria\", \"nombre\", \"imagen\"" + "FROM \"Categoria\" ORDER BY \"nombre\";");
            ResultSet rs = pstmt.executeQuery();
            //create the transfer object using data from rs
            while (rs.next()) {
                idCategoria = rs.getInt("idCategoria");
                nombre = rs.getString("nombre");


                CategoriaDTO categoria = new CategoriaDTO(idCategoria, nombre, imagen);
                listaCategoria.add(categoria);
            }
        } catch (Exception e) {
            e.printStackTrace();

            throw new DAOException(DAOException.IMPOSIBLE_MAKE_QUERY);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                throw new DAOException(DAOException.IMPOSIBLE_CLOSE_CONNECTION);
            }
        }
        return listaCategoria;
    }

    public UsuarioDTO getCustomer(String nombre, String contraseña) throws DAOException {
        System.out.print("GOGOGOOGOOGOGO");
        //iniciar variables
        String name = "coso";
        UsuarioDTO user = new UsuarioDTO(null, "coso", "perro", null, null, null, null, null, null, null, null, null);


        //make ODBC connection 
        DAOController dc = new DAOController();
        Connection con = dc.getConnection();

        try {
            // setup statement and retrieve results
            PreparedStatement pstmt = con.prepareStatement("select nombre from \"Usuario\" where \"nombre\" = ? and \"contraseña\" = ? ");
            pstmt.setString(1, nombre);
            pstmt.setString(2, contraseña);
            ResultSet rs = pstmt.executeQuery();


            //create the transfer object using data from rs
            while (rs.next()) {
                name = rs.getString("nombre");

            }
            user.setNombre(name);

        } catch (Exception e) {
            e.printStackTrace();

            throw new DAOException(DAOException.IMPOSIBLE_MAKE_QUERY);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                throw new DAOException(DAOException.IMPOSIBLE_CLOSE_CONNECTION);
            }
        }
        return user;
    }

    public static ArrayList<ProductoDTO> obtenerProductosSimilares(int idCategoriaSeleccionada) throws DAOException {
        // iniciar variables
        int idProducto = 0;
        String nombre = "";
        String descripcion = "";
        byte[] image;
        int stock = 0;
        int precio = 0;
        int valorOferta = 0;
        boolean ofertaActiva = false;
        int idCategoria = 0;
        //_ProductoDTO producto = new ProductoDTO();
        ArrayList<ProductoDTO> listaProductos = new ArrayList<ProductoDTO>();
        // odbc conection
        DAOController dc = new DAOController();
        Connection con = dc.getConnection();
        //statment
        try {
            // setup statement and retrieve results
            PreparedStatement pstmt = con.prepareStatement("SELECT \"idProducto\", \"nombre\", \"descripcion\", \"stock\", \"precio\", \"valorOferta\", \"ofertaActiva\", \"idCategoria\" FROM \"Producto\" WHERE \"idCategoria\" = " + idCategoriaSeleccionada + "ORDER BY \"idProducto\" LIMIT 3;");
            ResultSet rs = pstmt.executeQuery();
            //create the transfer object using data from rs
            while (rs.next()) {
                idProducto = rs.getInt("idProducto");
                nombre = rs.getString("nombre");
                descripcion = rs.getString("descripcion");
                stock = rs.getInt("stock");
                precio = rs.getInt("precio");
                valorOferta = rs.getInt("valorOferta");
                ofertaActiva = rs.getBoolean("ofertaActiva");
                idCategoria = rs.getInt("idCategoria");
                /*producto.setIdProducto(idProducto);
                 producto.setNombre(nombre);
                 producto.setDescripcion(descripcion);
                 producto.setImage(image);
                 producto.setStock(stock);
                 producto.setValorOferta(valorOferta);
                 producto.setOfertaActiva(ofertaActiva);
                 producto.setIdProducto(idProducto);
                 listaProductos.add(producto);*/
                ProductoDTO producto = new ProductoDTO(idProducto, nombre, descripcion, "", stock, precio, valorOferta, ofertaActiva, idCategoria);
                listaProductos.add(producto);
            }
        } catch (Exception e) {
            e.printStackTrace();

            throw new DAOException(DAOException.IMPOSIBLE_MAKE_QUERY);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                throw new DAOException(DAOException.IMPOSIBLE_CLOSE_CONNECTION);
            }
        }
        return listaProductos;
    }

    public void crearProducto(ProductoDTO producto) throws DAOException, SQLException {
        DAOController dc = new DAOController();
        Connection con = dc.getConnection();
        PreparedStatement pstmt = con.prepareStatement("INSERT INTO \"Producto\"(\"idProducto\",nombre,descripcion, stock,\"valorOferta\",\"ofertaActiva\",\"idCategoria\",precio)VALUES(?,?,?,?,?,?,?,?)");
        pstmt.setInt(1, producto.getIdProducto());
        pstmt.setString(2, producto.getNombre());
        pstmt.setString(3, producto.getDescripcion());

        pstmt.setInt(4, producto.getStock());

        pstmt.setInt(5, producto.getValorOferta());
        pstmt.setBoolean(6, producto.isOfertaActiva());
        pstmt.setInt(7, producto.getIdCategoria());
        pstmt.setInt(8, producto.getPrecio());
        pstmt.executeUpdate();
        con.close();

    }

    public void crearCategoria(CategoriaDTO categoria) throws DAOException, SQLException {
        DAOController dc = new DAOController();
        Connection con = dc.getConnection();
        PreparedStatement pstmt = con.prepareStatement("INSERT INTO \"Categoria\"(nombre)VALUES(?)");
      
        pstmt.setString(1, categoria.getNombre());

        pstmt.executeUpdate();
        con.close();
    }
    public void actualizarCategoria(CategoriaDTO categoria) throws DAOException, SQLException {
        DAOController dc = new DAOController();
        Connection con = dc.getConnection();
        PreparedStatement pstmt = con.prepareStatement("UPDATE \"Categoria\" SET  nombre= ? WHERE \"idCategoria\"=?");
         pstmt.setString(1, categoria.getNombre());
        pstmt.setInt(2, categoria.getIdCategoria());
       



        pstmt.executeUpdate();
        con.close();
    }
    public void eliminarCategoria(CategoriaDTO categoria) throws DAOException, SQLException {
        DAOController dc = new DAOController();
        Connection con = dc.getConnection();
        PreparedStatement pstmt = con.prepareStatement("DELETE FROM \"Categoria\" WHERE \"idCategoria\"=?");
         
        pstmt.setInt(1, categoria.getIdCategoria());
       



        pstmt.executeUpdate();
        con.close();
    }
}