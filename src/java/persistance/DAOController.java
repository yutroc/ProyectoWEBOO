/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package persistance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import static persistance.DAOController.DRIVER;
import utils.DAOException;

/**
 *
 * @author object
 */
class DAOController {
    
    public static final String DRIVER = "org.postgresql.Driver";
    public static final String DBURL = "jdbc:postgresql://146.83.196.216:5432/ServiMoto";
    public static final String USER = "alumnos";
    public static final String PASSWORD = "tallerweb";

    public static Connection getConnection() throws DAOException{
        Connection conn = null;
        try {
                Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {

                throw new DAOException(DAOException.IMPOSIBLE_FIND_DRIVER);
        }
        try {
                conn = DriverManager.getConnection(DBURL, USER, PASSWORD);
        } catch (SQLException e) {
                System.out.println(e);
                throw new DAOException(DAOException.IMPOSIBLE_ESTABLISH_CONNECTION);
        }
        return conn;
    }

}
