/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package persistance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import utils.DAOException;

/**
 *
 * @author object
 */
public class DAOController {
    
    public static final String DRIVER = "org.postgresql.Driver";
    public static final String DBURL = "jdbc:postgresql://colvin.chillan.ubiobio.cl:5432/SisVenta";
    public static final String USER = "grupo0";
    public static final String PASSWORD = "grupo0";

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
