
package wogaze.clases;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.scene.input.KeyCode;

public class registro extends persona {

    public void validarTexto(String letra, javafx.scene.input.KeyEvent event)
    {
        for(int i = 0; i < letra.length(); i++)
        {
            if((letra.charAt(i) < 'a' || letra.charAt(i) > 'z') && (letra.charAt(i) < 'A' || letra.charAt(i) > 'Z') && letra.charAt(i) != ' ' && letra.charAt(i) != ',' && letra.charAt(i) != '.' && letra.charAt(i) != ';')
            {
                event.consume(); 
            }
        }
    }
   
   
   public void validarNumero(String letra, javafx.scene.input.KeyEvent event)
    {
        for(int i = 0; i < letra.length(); i++)
        {
            if((letra.charAt(i) < '.' || letra.charAt(i) > '9') && event.getCode() != KeyCode.BACK_SPACE && letra.charAt(i) != ' ')
            {
                event.consume(); 
            }
        }
    } 
   
   public int contarCaracteres(String valor)
   {
       return valor.length();
   }
   
   public boolean comprobarUsuario(String us) throws SQLException, ClassNotFoundException
   {
       ResultSet rs = conexion.db().createStatement().executeQuery("SELECT id_us FROM usuario WHERE usuario = '" + us + "'");
       while(rs.next())
       {
            return false;
        }
       return true;
   }
   
    public boolean comprobarEmail(String em) throws SQLException, ClassNotFoundException
   {
       ResultSet rs = conexion.db().createStatement().executeQuery("SELECT id_us FROM usuario WHERE email = '" + em + "'");
       while(rs.next())
       {
            return false;
        }
       return true;
   }
   
   public boolean comprobarClave(int op, String cl, String confirmada)
   {
       switch(op)
       {
           case 0:
           {
                if(cl.length() < 4 || cl.length() > 30)
                {
                    return false;
                }
                else if(confirmada.length() < 4 || confirmada.length() > 30)
                {
                    return false;
                }
                else if(confirmada.length() != cl.length())
                {
                    return false;
                }
                break;
           }
           
           case 1:
           {
               if(!cl.equals(confirmada))
                {
                    return false;
                }
               break;
           }
       }
       
       return true;
   }
   
   public boolean validarCampos(int op, String valor)
   {
       switch(op)
       {
           case 0: // nombres - apellidos
           {
               if(valor.length() < 3 || valor.length() > 40) 
                   return false;
               break;
           }
           
           case 1: // identificacion
           {
               if(valor.length() < 8 || valor.length() > 15) 
                   return false;
               break;
           }
           
           case 2: // dirección
           {
               if(valor.length() < 1 || valor.length() > 3) 
                   return false;
               break;
           }
           
           case 3: // telefono
           {
               if(valor.length() < 7 || valor.length() > 7) 
                   return false;
               break;
           }
           
           case 4: // celular
           {
               if(valor.length() < 10 || valor.length() > 10) 
                   return false;
               break;
           }
           
           case 5: // usuario
           {
               if(valor.length() < 4 || valor.length() > 30) 
                   return false;
               break;
           }
           // -------------------- Trabajador
           case 6: // habilidades
           {
               int comas = valor.indexOf(",");
               if(valor.length() < 15 || valor.length() > 120) 
                   return false;
               else if(comas == -1) 
                   return false;
               break;
           }
           case 7: // perfil personal
           {
               if(valor.length() < 89 || valor.length() > 520) 
                   return false;
               break;
           }
           //----------------------- Persona
            case 8: // email
           {
               if(valor.length() < 4 || valor.length() > 55) 
                   return false;
               break;
           }
           
           case 9: // inmueble
           {
               if(valor.length() < 1 || valor.length() > 5) 
                   return false;
               break;
           }
       }
       
       return true;
   }

}
