
package wogaze.clases;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author Breyner
 */
public class cargarDatos {
    private Statement stm;
    private ResultSet rs;
    
    public void descargarDatos() throws SQLException, ClassNotFoundException
    {
        persona P = new persona();
        trabajador T = new trabajador();
        cliente C = new cliente();
        String tiempoActual = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());

        rs = conexion.db().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE).executeQuery("SELECT * FROM usuario WHERE id_us = " + P.getIdPersona() + "");
        if(rs.next())
        {
            rs.updateString("ultima_conexion", tiempoActual); // actualiza ultima conexion
            rs.updateRow();
        }
        
        P.setIdPersona(-1);
        T.setIdTrabajador(-1);
        C.setIdCliente(-1);
        P.setNombre(null);
        P.setApellidos(null);
        P.setTipoDoc(null);
        P.setNumCel(-1);
        P.setNumTel(-1);
        P.setDireccion(null);
        P.setEmail(null);
        P.setUsuario(null);
        P.setClave(null);
        P.setFecha_reg(null);
        P.setUltima_con(null);
        P.setImgperfil(null);
        T.setOcupacionP(null);
        T.setHabilidades(null);
        T.setPerfilP(null);
        T.setTiempoLabor(null);
        T.setOtrasHabilidades(null);
        T.setEstado(false);
        C.setEstado(false);
        
        System.out.println("\t>> Datos de sesión eliminados.");
    }
    
    public void cargarDatos(String tipo) throws SQLException
    {
        switch(tipo)
        {
            case "persona":
            {
                
                persona P = new persona();
                P.setIdPersona(rs.getInt("id_us"));
                P.setNombre(rs.getString("nombres"));
                P.setApellidos(rs.getString("apellidos"));
                P.setTipoDoc(rs.getString("tipo_id"));
                P.setNumDoc(rs.getBigDecimal("num_id").intValue());
                P.setNumCel(rs.getBigDecimal("celular").intValue());
                P.setNumTel(rs.getInt("telefono"));
                P.setDireccion(rs.getString("direccion"));
                P.setEmail(rs.getString("email"));
                P.setUsuario(rs.getString("usuario"));
                P.setClave(rs.getString("clave"));
                P.setFecha_reg(rs.getString("fecha_registro"));
                P.setUltima_con(rs.getString("ultima_conexion"));
                P.setImgperfil(rs.getString("imagen"));
                System.out.println("\t>> Datos de sesión: persona regular cargados.");
                    trabajador T = new trabajador();
                    rs = stm.executeQuery("SELECT * FROM trabajador WHERE id_us = " + P.getIdPersona() + "");
                    if(rs.next())
                    {
                    T.setIdTrabajador(rs.getInt("id_trabajador"));
                    T.setOcupacionP(rs.getString("ocupacion_p"));
                    T.setHabilidades(rs.getString("habilidades"));
                    T.setPerfilP(rs.getString("perfil_personal"));
                    T.setTiempoLabor(rs.getString("tiempo_labor"));
                    T.setOtrasHabilidades(rs.getString("habilidades_b"));
                    T.setEstado(rs.getBoolean("estado"));
                    System.out.println("\t>> Datos de sesión: trabajador cargados.");
                    }
                    
                    cliente C = new cliente();
                    rs = stm.executeQuery("SELECT * FROM cliente WHERE id_us = " + P.getIdPersona() + "");
                    if(rs.next())
                    {
                    C.setIdCliente(rs.getInt("id_cliente"));
                    C.setEstado(rs.getBoolean("estado"));
                    System.out.println("\t>> Datos de sesión: cliente cargados.");
                    }
                break;
            }
            case "pedidos":
            {
                break;
            }
            case "calificaciones":
            {
                break;
            }
            
        }
    }
    
    public void cargarDatosUsuario(String us, String cl)
    {
        try
        {
            stm = conexion.db().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = stm.executeQuery("SELECT * FROM usuario WHERE usuario = '" + us + "' AND clave = '" + cl + "'");
           if(rs.next())
            {
                cargarDatos("persona");
            }
        }
        catch (Exception e) { 
            System.err.println(e.getMessage()); 
        }
    }
    
}
