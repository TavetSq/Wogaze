
package wogaze.clases;
import java.sql.*;
/**
 *
 * @author Breyner
 */
public class conexion {
    private static Connection conexion = null;
    public static Connection db() throws SQLException, ClassNotFoundException {
        String DB = "wogazedb"; // nombre DB
        String DBP = "3306"; // puerto MySQL
        if(conexion == null)
        {
            try
            {
                Class.forName("com.mysql.jdbc.Driver");
                conexion = DriverManager.getConnection("jdbc:mysql://localhost:" + DBP + "/" + DB, "root", "");
            }
            catch(SQLException e)
            {
                throw new SQLException(e);
            }
            catch(ClassNotFoundException e)
            {
                throw new ClassCastException(e.getMessage());
            }
            finally
            {
                System.out.println("\t\t>> Conexion a la base de datos exitosa");
            }
        }
        return conexion;
    }
}
