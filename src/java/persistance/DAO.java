/**
 * 
 */
package persistance;

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
         int idProducto= 0;
         String nombre="";
         String descripcion="";
         byte image=0;
         int stock=0;
         int valorOferta=0;
         boolean ofertaActiva=false;
         int idCategoria=0;
         //_ProductoDTO producto = new ProductoDTO();
         ArrayList<ProductoDTO> listaProductos = new ArrayList<ProductoDTO>();
         // odbc conection
         DAOController dc = new DAOController();
         Connection con = dc.getConnection();     
         //statment
        try {
            // setup statement and retrieve results
            PreparedStatement pstmt = con.prepareStatement("SELECT *\n" + "FROM \"Producto\";");
            ResultSet rs = pstmt.executeQuery();
            //create the transfer object using data from rs
            while(rs.next()){
                idProducto= rs.getInt("idProducto");
                nombre=rs.getString("nombre");
                descripcion = rs.getString("descripcion");
                image = rs.getByte("image");
                stock = rs.getInt("stock");
                valorOferta=rs.getInt("valorOferta");
                ofertaActiva=rs.getBoolean("ofertaActiva");
                idCategoria=rs.getInt("idCategoria");
                /*producto.setIdProducto(idProducto);
                producto.setNombre(nombre);
                producto.setDescripcion(descripcion);
                producto.setImage(image);
                producto.setStock(stock);
                producto.setValorOferta(valorOferta);
                producto.setOfertaActiva(ofertaActiva);
                producto.setIdProducto(idProducto);
                listaProductos.add(producto);*/
                ProductoDTO producto = new ProductoDTO(idProducto,nombre,descripcion,image,stock,valorOferta,ofertaActiva,idCategoria);
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
	
}
