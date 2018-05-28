
package wogaze.clases;

import java.sql.PreparedStatement;

public class trabajador extends persona {
    protected static String ocupacionP, habilidades, perfilP, tiempoLabor, otrasHabilidades; // rama principal y 5 subramas
    protected static boolean estado;
    protected static int idTrabajador = -1;
    
    public trabajador()
    {
    }
    
    public void agregarTrabajador(int idu, String op, String h, String pp, String tl, String oh, boolean est)
    {
        try
        {
        String consulta = "INSERT INTO trabajador VALUES (null, " + idu + ", '" + op + "', "
        + "'" + h + "', '" + pp + "', '" + tl + "', '" + oh + "', " + est + ")";
        PreparedStatement ps = conexion.db().prepareStatement(consulta);
        ps.executeUpdate();
        }
        catch (Exception e) { 
            System.err.println(e.getMessage()); 
        } 
    }
    
    public void setOcupacionP(String ocupacionP) {
        trabajador.ocupacionP = ocupacionP;
    }

    public void setHabilidades(String habilidades) {
        trabajador.habilidades = habilidades;
    }

    public void setPerfilP(String perfilP) {
        trabajador.perfilP = perfilP;
    }

    public void setTiempoLabor(String tiempoLabor) {
        trabajador.tiempoLabor = tiempoLabor;
    }

    public void setOtrasHabilidades(String otrasHabilidades) {
        trabajador.otrasHabilidades = otrasHabilidades;
    }

    public void setEstado(boolean estado) {
        trabajador.estado = estado;
    }
    
    public int getIdTrabajador() {
        return idTrabajador;
    }

    public void setIdTrabajador(int idTrabajador) {
        trabajador.idTrabajador = idTrabajador;
    }

    public String getOcupacionP() {
        return ocupacionP;
    }

    public String getHabilidades() {
        return habilidades;
    }

    public String getPerfilP() {
        return perfilP;
    }

    public String getTiempoLabor() {
        return tiempoLabor;
    }
    
    public String getOtrasHabilidades() {
        return otrasHabilidades;
    }

    public boolean isEstado() {
        return estado;
    }


    
    
   
}
