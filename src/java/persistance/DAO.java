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
import DTO.CompraProductoDTO;

public class DAO {

    public DAO() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<ProductoDTO> obtenerTodosProductos(String nameProducto) throws DAOException {
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
            PreparedStatement pstmt = con.prepareStatement("SELECT \"idProducto\", \"nombre\", \"descripcion\", \"stock\", \"precio\", \"valorOferta\", \"ofertaActiva\", \"idCategoria\"" + "FROM \"Producto\" where \"nombre\" like ? ORDER BY \"idProducto\";");
              pstmt.setString(1, "%"+nameProducto+"%");
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

    public ArrayList<CategoriaDTO> obtenerTodosCategoria(String nameCategoria) throws DAOException {
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
            PreparedStatement pstmt = con.prepareStatement("SELECT \"idCategoria\", \"nombre\", \"imagen\"" + "FROM \"Categoria\" where \"nombre\" like ? order by \"nombre\";");
            pstmt.setString(1, "%"+nameCategoria+"%");
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
        int tipoUser=9;
        UsuarioDTO user = new UsuarioDTO(1, "coso", "perro", null, null, null, null, null, null, null, null, null);


        //make ODBC connection 
        DAOController dc = new DAOController();
        Connection con = dc.getConnection();

        try {
            // setup statement and retrieve results
            PreparedStatement pstmt = con.prepareStatement("select nombre,\"idTipoUsuario\" from \"Usuario\" where \"nombre\" = ? and \"contraseña\" = ? ");
            pstmt.setString(1, nombre);
            pstmt.setString(2, contraseña);
            ResultSet rs = pstmt.executeQuery();


            //create the transfer object using data from rs
            while (rs.next()) {
                name = rs.getString("nombre");
                tipoUser=rs.getInt("idTipoUsuario");
            }
            user.setNombre(name);
            user.setIdUsuario(tipoUser);

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
    
    public void eliminarProducto(ProductoDTO p) throws DAOException, SQLException {
        DAOController dc = new DAOController();
        Connection con = dc.getConnection();

        try {
            PreparedStatement pstmt = con.prepareStatement("DELETE FROM \"Producto\" WHERE \"idProducto\" =?");
            pstmt.setInt(1, p.getIdProducto());
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
        return "MantenedorUsuarios.xhtml";
    }

    public ArrayList<UsuarioDTO> obtenerUsuarios(String name) throws DAOException, SQLException {
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
                    + "  FROM \"Usuario\" where nombre like ?;");
            pstmt.setString(1,  "%"+ name + "%");
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
    public static ArrayList<ProductoDTO> obtenerProductosPorCategoria(int idCategoriaSeleccionada) throws DAOException {
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
            PreparedStatement pstmt = con.prepareStatement("SELECT \"idProducto\", \"nombre\", \"descripcion\", \"stock\", \"precio\", \"valorOferta\", \"ofertaActiva\", \"idCategoria\" FROM \"Producto\" WHERE \"idCategoria\" = " + idCategoriaSeleccionada + "ORDER BY \"idProducto\";");
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
    public ArrayList<CompraProductoDTO> obtenerCarritos(String idUsuario) throws DAOException {
        DAOController dc = new DAOController();
        Connection con = dc.getConnection();
        int idCarro=0;
        
        try {
            PreparedStatement pstmt = con.prepareStatement("SELECT \"idCarro\", \"idUsuario\" FROM \"Compra\"  WHERE \"idUsuario\" = '?' AND estado = 1;");
            pstmt.setString(1, idUsuario);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
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
        
        return null;
    }
    public ArrayList<String> tieneCarro(String idUsuario) throws DAOException{
        DAOController dc = new DAOController();
        Connection con = dc.getConnection();
        ArrayList<String> carros = new ArrayList<String>();
        int idCarro=0;
        try {
            PreparedStatement pstmt = con.prepareStatement("SELECT \"idCarro\" FROM \"Compra\"  WHERE \"idUsuario\" = ? AND estado = 1;");
            System.out.println(idUsuario+" DAO");
            System.out.println("SELECT \"idCarro\" FROM \"Compra\"  WHERE \"idUsuario\" = 1 AND estado = 1;");
            pstmt.setString(1, idUsuario);
            
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                idCarro = rs.getInt("idCarro");
                System.out.println(idCarro+"aaASDFGHJKLÑERTYUIODFGHJKLTYUIASDANDIABFUISBFUS");
                carros.add(idCarro+"");
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
        return carros;
    }
    public ArrayList<String> obtenerIdCarrito(String idUsuario) throws DAOException {
        DAOController dc = new DAOController();
        Connection con = dc.getConnection();
        ArrayList<String> carros = new ArrayList<String>();
        int idCarro=0;
        try {
            PreparedStatement pstmt = con.prepareStatement("SELECT \"idCarro\" FROM \"Compra\"  WHERE \"idUsuario\" = ? AND estado = 1;");
            pstmt.setString(1, idUsuario);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                idCarro = rs.getInt("idCarro");
                carros.add(idCarro+"");
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
        return carros;
    }
    public void CrearCarro(String idUsuario) throws DAOException {
        DAOController dc = new DAOController();
        Connection con = dc.getConnection();
        Date fecha = new Date();
        try {
            PreparedStatement pstmt = con.prepareStatement("INSERT INTO \"Compra\"(\n" +
"             estado, \"fechaCreacion\", \"idUsuario\")\n" +
"    VALUES ( ?, ?, ?);");
            pstmt.setInt(1, 1);
            java.sql.Date fechaD = new java.sql.Date(fecha.getTime());
            pstmt.setDate(2, fechaD);
            pstmt.setString(3, idUsuario);
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
    public ArrayList<String> existeEnCarro(int idProducto, int idCarro) throws DAOException {
        String idCompra = "";
        DAOController dc = new DAOController();
        Connection con = dc.getConnection();
        ArrayList<String> idVenta = new ArrayList<String>();
        try {
            PreparedStatement pstmt = con.prepareStatement("SELECT \"idVentaProducto\"\n" +
"  FROM \"CompraProducto\"\n" +
"  WHERE \"idCarro\" = ? and \"idProducto\"=?;");
            pstmt.setInt(1, idCarro);
            pstmt.setInt(2, idProducto);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                idCompra = rs.getString("idVentaProducto");
                idVenta.add(idCompra);
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
        return idVenta;
    }
    public void agregarACarro(int idProducto, int idCarro,ArrayList<ProductoDTO> productos) throws DAOException {
        DAOController dc = new DAOController();
        Connection con = dc.getConnection();
        int total=0;
        for(int i=0;i<productos.size();i++){
            if(productos.get(i).getIdProducto()==idProducto){
                total = productos.get(i).getPrecio();
            }
        }
        try {
            PreparedStatement pstmt = con.prepareStatement("INSERT INTO \"CompraProducto\"(\n" +
"            cantidad, total, \"idCarro\", \"idProducto\")\n" +
"    VALUES (?, ?, ?, ?);");
            pstmt.setInt(1, 1);
            pstmt.setInt(2,total);
            pstmt.setInt(3, idCarro);
            pstmt.setInt(4, idProducto);
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
    public ArrayList<CompraProductoDTO> obtenerProductos(int idCarro) throws DAOException {
        DAOController dc = new DAOController();
        Connection con = dc.getConnection();
        int cantidad =0;
        int total = 0;
        int idProducto=0;
        int idVentaProducto =0;
        int idCarroC=0;
        String nombreProducto="";
        int precio=0;
        int precioOferta=0;
        boolean oferta=false;
        ArrayList<CompraProductoDTO> producto = new ArrayList<CompraProductoDTO>();
        try {
            PreparedStatement pstmt = con.prepareStatement("SELECT \n" +
"  \"CompraProducto\".cantidad, \n" +
"  \"CompraProducto\".total, \n" +
"  \"CompraProducto\".\"idCarro\", \n" +
"  \"CompraProducto\".\"idProducto\", \n" +
"  \"CompraProducto\".\"idVentaProducto\", \n" +
"  \"Producto\".nombre, \n" +
"  \"Producto\".precio, \n" +
"  \"Producto\".\"valorOferta\", \n" +
"  \"Producto\".\"ofertaActiva\"\n" +
"FROM \n" +
"  \"CompraProducto\"\n" +
"INNER JOIN \"Producto\" ON \"CompraProducto\".\"idProducto\"=\"Producto\".\"idProducto\"\n" +
"WHERE\n" +
"  \"CompraProducto\".\"idCarro\"=? ;");
           pstmt.setInt(1, idCarro);
           ResultSet rs = pstmt.executeQuery();
           while(rs.next()){
               cantidad = rs.getInt("cantidad");
               total = rs.getInt("total");
               idCarroC=rs.getInt("idCarro");
               idProducto = rs.getInt("idProducto");
               idVentaProducto = rs.getInt("idVentaProducto");
               nombreProducto = rs.getString("nombre");
               precio = rs.getInt("precio");
               precioOferta=rs.getInt("valorOferta");
               oferta=rs.getBoolean("ofertaActiva");
               CompraProductoDTO prod = new CompraProductoDTO(idVentaProducto,cantidad,total,idCarroC,
                       idProducto,nombreProducto,precio,precioOferta,oferta);
               producto.add(prod);
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
        return producto;

    }

    public void elimnarDeCarrito(int idVenta) throws DAOException {
        DAOController dc = new DAOController();
        Connection con = dc.getConnection();
        try {
            PreparedStatement pstmt = con.prepareStatement("DELETE FROM \"CompraProducto\"\n" +
" WHERE \"idVentaProducto\"=?;");
            pstmt.setInt(1, idVenta);
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

    public void updateCant(int cantidad, int idVentaProducto) throws DAOException {
        DAOController dc = new DAOController();
        Connection con = dc.getConnection();
        try {
            PreparedStatement pstmt = con.prepareStatement("UPDATE \"CompraProducto\"\n" +
"SET cantidad=?\n" +
"WHERE \"idVentaProducto\" = ?;");
            pstmt.setInt(1, cantidad);
            pstmt.setInt(2, idVentaProducto);
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

    public void cambiarCarro(int idCarro) throws DAOException {
        DAOController dc = new DAOController();
        Connection con = dc.getConnection();
        try {
            PreparedStatement pstmt = con.prepareStatement("UPDATE \"Compra\"\n" +
"   SET estado=0\n" +
" WHERE \"idCarro\"=?;");
            pstmt.setInt(1, idCarro);
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
}