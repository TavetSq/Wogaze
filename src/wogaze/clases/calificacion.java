
package wogaze.clases;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Breyner
 */
public class calificacion {
    protected String cDescripcion, cRating, cFecha;
    protected int cIdPedido;
    
    public double calculoCalificacion(int idT) throws SQLException, ClassNotFoundException
    {
       double calcular = 0;
       int nVeces = 0;
       ResultSet rs = conexion.db().createStatement().executeQuery("SELECT id_propuesta, id_trabajador, id_pedido"
        + " FROM propuesta WHERE id_trabajador = " +  idT + "");
       while(rs.next())
       {
          ResultSet rs2 = conexion.db().createStatement().executeQuery("SELECT * FROM pedido "
            + "WHERE id_pedido = " +  rs.getInt("id_pedido") + " AND estado = " + estados.peRealizado + "");
          while(rs2.next())
           {
               ResultSet rs3 = conexion.db().createStatement().executeQuery("SELECT * FROM calificacion "
                + "WHERE id_pedido = " +  rs2.getInt("id_pedido") + "");
               if(rs3.next())
               {
                   if(rs3.getInt("calificacion") != estados.nulo)
                   {
                       calcular = calcular + rs2.getInt("calificacion");
                       nVeces++;
                   }
               }
           } 
       }
       
       calcular = calcular / nVeces;
       return calcular;
    }

    public void setcDescripcion(String cDescripcion) {
        this.cDescripcion = cDescripcion;
    }

    public void setcRating(String cRating) {
        this.cRating = cRating;
    }

    public void setcFecha(String cFecha) {
        this.cFecha = cFecha;
    }

    public void setcIdPedido(int cIdPedido) {
        this.cIdPedido = cIdPedido;
    }

    public String getcDescripcion() {
        return cDescripcion;
    }

    public String getcRating() {
        return cRating;
    }

    public String getcFecha() {
        return cFecha;
    }

    public int getcIdPedido() {
        return cIdPedido;
    }
    
    
    
}
