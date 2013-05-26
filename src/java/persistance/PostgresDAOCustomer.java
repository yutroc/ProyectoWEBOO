/**
 *
 */
package persistance;

import DTO.UsuarioDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import utils.DAOException;

/**
 * @author Object
 *
 */
public class PostgresDAOCustomer {

    private static String SELECT_CUSTOMER = "select * from \"Usuario\" where \"nombre\" = '";

    /**
     *
     */
    public PostgresDAOCustomer() {
        // TODO Auto-generated constructor stub
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
