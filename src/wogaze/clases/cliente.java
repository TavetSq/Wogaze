
package wogaze.clases;

import java.sql.PreparedStatement;

/**
 *
 * @author Breyner
 */
public class cliente extends persona {
    protected static boolean estado;
    protected static int idCliente = -1;
    
    public cliente()
    {
    }
    
    public void agregarCliente(int idp, boolean est)
    {
        try
        {
        String consulta = "INSERT INTO cliente VALUES (null, " + idp + ", " + est + ")";
        PreparedStatement ps = conexion.db().prepareStatement(consulta);
        ps.executeUpdate();
        }
        catch (Exception e) { 
            System.err.println(e.getMessage()); 
        } 
    }

    public boolean isEstado() {
        return estado;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setEstado(boolean estado) {
        cliente.estado = estado;
    }

    public void setIdCliente(int idCliente) {
        cliente.idCliente = idCliente;
    }

    
    

    
}
