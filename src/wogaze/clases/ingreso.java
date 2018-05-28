
package wogaze.clases;

import java.sql.ResultSet;
import java.sql.Statement;

public class ingreso extends persona {
    
    public boolean validarCampos(String us, String cl)
    {
              if(us.length() < 4 || us.length() > 20) return false;
              else if(cl.length() < 4 || cl.length() > 30) return false;
              else if(us.equals("null")) return false;
        return true;
    }  
    
    
    public boolean validarIngreso(String us, String cl)
    {
        try
        {
            Statement stm;
            stm = conexion.db().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stm.executeQuery("SELECT * FROM usuario WHERE usuario = '" + us + "' AND clave = '" + cl + "'");
           if(rs.next())
            {
                return true;
            }
        }
        catch (Exception e) { 
            System.err.println(e.getMessage()); 
        } 
    
     return false;
    }
    
}
