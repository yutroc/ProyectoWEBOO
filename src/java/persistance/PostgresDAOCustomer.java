/**
 * 
 */
package persistance;

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

	private static String SELECT_CUSTOMER= "select * from \"Costumer\" where \"user\" = '";
	/**
	 * 
	 */
	public PostgresDAOCustomer() {
		// TODO Auto-generated constructor stub
	}

 /*   public CostumerTO getCustomer(String user, String password) throws DAOException {
        //iniciar variables
         String firstName="";
         String lastName="";
         String pass="";
         int run=0;
         String usuario="";
         int debt=0;
         int pay=0;
         model.transferObject.CostumerTO costumer = new CostumerTO();
         
        
        //make ODBC connection 
         DAOController dc = new DAOController();
         Connection con = dc.getConnection();
         
        try {
            // setup statement and retrieve results
            PreparedStatement pstmt = con.prepareStatement("select * from \"Customer\" where \"user\" = ? and \"password\" = ? ");
            pstmt.setString(1, user);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            

            //create the transfer object using data from rs
            while(rs.next()){
            firstName = rs.getString("firstName");
            lastName=rs.getString("lastName");
            run = rs.getInt("run");
            usuario = rs.getString("user");
            pass = rs.getString("password");
          } 
            costumer.setPassword(pass);
            costumer.setName(firstName+" "+lastName);
            costumer.setUser(usuario);
            costumer.setRun(run);  
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
        return costumer;
    }
    */
    

	

}
