/**
 *
 */
package persistance;

import DTO.CategoriaDTO;
import DTO.CompraDTO;
import DTO.ProductoDTO;
import DTO.UsuarioDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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
        UsuarioDTO user = new UsuarioDTO(1, "coso", "perro", null, null, null, null, null, null, null, null, null);


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
        try {
            PreparedStatement pstmt = con.prepareStatement("INSERT INTO \"Producto\"(nombre,descripcion, stock,\"valorOferta\",\"ofertaActiva\",\"idCategoria\",precio)VALUES(?,?,?,?,?,?,?)");
            pstmt.setString(1, producto.getNombre());
            pstmt.setString(2, producto.getDescripcion());
            pstmt.setInt(3, producto.getStock());
            pstmt.setInt(4, producto.getValorOferta());
            pstmt.setBoolean(5, producto.isOfertaActiva());
            pstmt.setInt(6, producto.getIdCategoria());
            pstmt.setInt(7, producto.getPrecio());
            pstmt.executeUpdate();
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
        con.close();

    }

    public void crearCategoria(CategoriaDTO categoria) throws DAOException, SQLException {
        DAOController dc = new DAOController();
        Connection con = dc.getConnection();
        try {
            PreparedStatement pstmt = con.prepareStatement("INSERT INTO \"Categoria\"(nombre)VALUES(?)");
            pstmt.setString(1, categoria.getNombre());
            pstmt.executeUpdate();
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
        con.close();
    }

    public void actualizarCategoria(CategoriaDTO categoria) throws DAOException, SQLException {
        DAOController dc = new DAOController();
        Connection con = dc.getConnection();
        try {
            PreparedStatement pstmt = con.prepareStatement("UPDATE \"Categoria\" SET  nombre= ? WHERE \"idCategoria\"=?");
            pstmt.setString(1, categoria.getNombre());
            pstmt.setInt(2, categoria.getIdCategoria());
            pstmt.executeUpdate();
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
        con.close();
    }

    public void eliminarCategoria(CategoriaDTO categoria) throws DAOException, SQLException {
        DAOController dc = new DAOController();
        Connection con = dc.getConnection();
        try {
            PreparedStatement pstmt = con.prepareStatement("DELETE FROM \"Categoria\" WHERE \"idCategoria\"=?");
            pstmt.setInt(1, categoria.getIdCategoria());
            pstmt.executeUpdate();
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
        con.close();
    }

    public ArrayList<CompraDTO> obtenerCompras() throws DAOException {
        // iniciar variables

        int idCarro = 0; // este deberia ser serial, automatico
        //para crear no deberia ingresarse un idCarro
        String idUsuario = "";
        int estado = 0;
        Date fechaCreacion = new Date();
        Date fechaFinalizacion = new Date();


        ArrayList<CompraDTO> listaCompras = new ArrayList<CompraDTO>();
        // odbc conection
        DAOController dc = new DAOController();
        Connection con = dc.getConnection();
        //statment
        try {
            // setup statement and retrieve results
            PreparedStatement pstmt = con.prepareStatement("SELECT \"idCarro\", \"idUsuario\", \"estado\", \"fechaCreacion\", \"fechaFinalizacion\" FROM \"Compra\" ORDER BY \"idCarro\";");
            ResultSet rs = pstmt.executeQuery();
            //create the transfer object using data from rs
            while (rs.next()) {
                idCarro = rs.getInt("idCarro");
                idUsuario = rs.getString("idUsuario");
                estado = rs.getInt("estado");
                fechaCreacion = rs.getDate("fechaCreacion");
                fechaFinalizacion = rs.getDate("fechaFinalizacion");
                CompraDTO compra = new CompraDTO(idCarro, idUsuario, estado, fechaCreacion, fechaFinalizacion);
                listaCompras.add(compra);
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
        return listaCompras;
    }

    public void editarProducto(ProductoDTO p) throws DAOException, SQLException {
        DAOController dc = new DAOController();
        Connection con = dc.getConnection();

        try {
            PreparedStatement pstmt = con.prepareStatement("UPDATE \"Producto\" SET nombre=? ,descripcion =?, stock =?,\"valorOferta\" =?,\"ofertaActiva\" =?,\"idCategoria\"=?,precio=? WHERE \"idProducto\" =?");
            pstmt.setString(1, p.getNombre());
            pstmt.setString(2, p.getDescripcion());
            pstmt.setInt(3, p.getStock());
            pstmt.setInt(4, p.getValorOferta());
            pstmt.setBoolean(5, p.isOfertaActiva());
            pstmt.setInt(6, p.getIdCategoria());
            pstmt.setInt(7, p.getPrecio());
            pstmt.setInt(8, p.getIdProducto());
            pstmt.executeUpdate();
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
        con.close();
    }

    public void eliminarCompra(CompraDTO compra) throws SQLException, DAOException {
        DAOController dc = new DAOController();
        Connection con = dc.getConnection();
        try {
            PreparedStatement pstmt = con.prepareStatement("DELETE FROM  public.\"Compra\" WHERE  public.\"Compra\".\"idCarro\" = ?");
            pstmt.setInt(1, compra.getIdCarro());
            pstmt.executeUpdate();
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
    }

    public String crearUsuario(UsuarioDTO user) throws DAOException, SQLException {
        DAOController dc = new DAOController();
        Connection con = dc.getConnection();
        try {
            Date fecha = new Date();
            //java.sql.Date sqlDate = new java.sql.Date(fecha.getTime());
            System.out.println("hola 2 " + user.toString());
            PreparedStatement pstmt = con.prepareStatement("INSERT INTO \"Usuario\"(\n"
                    + "            nombre, \"aPaterno\", \"aMaterno\", rut, direccion, comuna, ciudad, \n"
                    + "            email, \"fechaRegistro\", \"contraseña\", telefono, \"idTipoUsuario\", \n"
                    + "            \"idUsuario\")\n"
                    + "    VALUES (?, ?, ?, ?, ?, ?, ?, \n"
                    + "            ?, ?, ?, ?, ?, \n"
                    + "            nextval('\"Usuario_idUsuario_seq\"'::regclass));");

            pstmt.setString(1, user.getNombre());
            pstmt.setString(2, user.getAPaterno());
            pstmt.setString(3, user.getAMaterno());
            pstmt.setString(4, user.getRut());
            pstmt.setString(5, user.getDireccion());
            pstmt.setString(6, user.getComuna());
            pstmt.setString(7, user.getCiudad());
            pstmt.setString(8, user.getEmail());
            java.sql.Date fechaD = new java.sql.Date(user.getFechaRegistro().getTime());
            pstmt.setDate(9, fechaD);
            pstmt.setString(10, user.getContraseña());
            pstmt.setString(11, user.getTelefono());
            pstmt.setInt(12, user.getIdTipo());
            pstmt.executeUpdate();
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
        con.close();
        return "mostrarUsuario.xhtml";
    }

    public ArrayList<UsuarioDTO> obtenerUsuarios() throws DAOException, SQLException {
        int idUsuario = 0;
        String nombre = "";
        String aPaterno = "";
        String aMaterno = "";
        String RUT = "";
        String direccion = "";
        String ciudad = "";
        String comuna = "";
        String email = "";
        Date fecha = new Date();
        String contraseña = "";
        String telefono = "";
        int idTipo = 0;
        ArrayList<UsuarioDTO> usuarios = new ArrayList<UsuarioDTO>();
        // odbc conection
        DAOController dc = new DAOController();
        Connection con = dc.getConnection();
        //statment
        try {
            // setup statement and retrieve results
            PreparedStatement pstmt = con.prepareStatement("SELECT nombre, \"aPaterno\", \"aMaterno\", rut, direccion, comuna, ciudad, \n"
                    + "       email, \"fechaRegistro\", \"contraseña\", telefono, \"idTipoUsuario\", \n"
                    + "       \"idUsuario\"\n"
                    + "  FROM \"Usuario\";");
            ResultSet rs = pstmt.executeQuery();
            //create the transfer object using data from rs
            while (rs.next()) {
                idUsuario = rs.getInt("idUsuario");
                nombre = rs.getString("nombre");
                aPaterno = rs.getString("aPaterno");
                aMaterno = rs.getString("aMaterno");
                RUT = rs.getString("rut");
                direccion = rs.getString("direccion");
                ciudad = rs.getString("ciudad");
                comuna = rs.getString("comuna");
                email = rs.getString("email");
                fecha = rs.getDate("fechaRegistro");
                contraseña = rs.getString("contraseña");
                telefono = rs.getString("telefono");
                idTipo = rs.getInt("idTipoUsuario");
                UsuarioDTO usuario = new UsuarioDTO(idUsuario, nombre, aPaterno, aMaterno, RUT,
                        direccion, ciudad, comuna, email, fecha, contraseña, telefono, idTipo);
                usuarios.add(usuario);
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
        con.close();
        return usuarios;
    }

    public String actualizarUsuario(UsuarioDTO user) throws DAOException, SQLException {
        DAOController dc = new DAOController();
        Connection con = dc.getConnection();

        try {
            PreparedStatement pstmt = con.prepareStatement("UPDATE \"Usuario\" SET nombre=?, \"aPaterno\"=?, \"aMaterno\"=?, rut=?, direccion=?, comuna=?, ciudad=?, email=?, \"contraseña\"=?, telefono=?, \"idTipoUsuario\"=? WHERE \"idUsuario\"=?;");
            pstmt.setString(1, user.getNombre());
            pstmt.setString(2, user.getAPaterno());
            pstmt.setString(3, user.getAMaterno());
            pstmt.setString(4, user.getRut());
            pstmt.setString(5, user.getDireccion());
            pstmt.setString(6, user.getComuna());
            pstmt.setString(7, user.getCiudad());
            pstmt.setString(8, user.getEmail());
            pstmt.setString(9, user.getContraseña());
            pstmt.setString(10, user.getTelefono());
            pstmt.setInt(11, user.getIdTipo());
            pstmt.setInt(12, user.getIdUsuario());
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException(DAOException.IMPOSIBLE_MAKE_QUERY);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                throw new DAOException(DAOException.IMPOSIBLE_CLOSE_CONNECTION);
            }
            return "mostrarUsuario.xhtml";
        }

    }

    public void eliminarUsuario(UsuarioDTO user) throws DAOException, SQLException {

        DAOController dc = new DAOController();
        Connection con = dc.getConnection();
        try {
            PreparedStatement pstmt = con.prepareStatement("DELETE FROM \"Usuario\" WHERE \"idUsuario\"=?");
            pstmt.setInt(1, user.getIdUsuario());
            pstmt.executeUpdate();

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
    }

    public void actualizarOferta(ProductoDTO p) throws DAOException, SQLException {
        DAOController dc = new DAOController();
        Connection con = dc.getConnection();
        try {
            PreparedStatement pstmt = con.prepareStatement("UPDATE \"Producto\" SET  precio=?, \"valorOferta\"=?, \"ofertaActiva\"=? WHERE \"idProducto\"=?;");
            pstmt.setInt(1, p.getPrecio());
            pstmt.setInt(2, p.getValorOferta());
            pstmt.setBoolean(3, p.isOfertaActiva());
            pstmt.setInt(4, p.getIdProducto());

            pstmt.executeUpdate();


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
        con.close();
    }
}