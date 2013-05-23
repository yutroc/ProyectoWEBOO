/**
 * 
 */
package persistance;

import DTO.ProductoDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import utils.DAOException;


/**
 * @author Object
 *
 */
public class DAO {

	private static String SELECT_CUSTOMER= "select * from \"Costumer\" where \"user\" = '";

    public static ArrayList<ProductoDTO> obtenerTodosProductos() throws DAOException {
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
	/**
	 * 
	 */
	public DAO() {
		// TODO Auto-generated constructor stub
	}

 
    

	

}
