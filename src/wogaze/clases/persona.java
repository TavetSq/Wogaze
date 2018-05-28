package wogaze.clases;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class persona {
    protected static String nombre, apellidos, tipoDoc, direccion, email, usuario, clave, fecha_reg, ultima_con, imgperfil;
    protected static long numDoc, numCel, numTel;
    public static int idPersona;
         
    public persona()
    {
    }
    
    public void agregarPersona(String nm, String ap, String tid, String nid, String cel, String tel, String dir, String em)
    {
        try
        {
        String tiempoActual = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
        String consulta = "INSERT INTO usuario VALUES " +
        "(null, '" + nm + "', '" + ap + "', '" + tid + "', " + nid + ", " + cel + ", " + tel + ", '" + dir + 
        "', '" + em + "', null, null,'" + tiempoActual + "','" + tiempoActual + "', null)";
        PreparedStatement ps = conexion.db().prepareStatement(consulta, Statement.RETURN_GENERATED_KEYS);
        ps.executeUpdate();
        ResultSet rs=ps.getGeneratedKeys();
            if(rs.next()){
                setIdPersona(rs.getInt(1));
            }
        }
        catch (Exception e) { 
            System.err.println(e.getMessage()); 
        } 
    } 
    
    public boolean isCliente()
    {
        if(cliente.idCliente == -1)
            return false;
        
        return true;
    }
    
    public boolean isTrabajador()
    {
        if(trabajador.idTrabajador == -1)
            return false;
        
        return true;
    }
    
    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getTipoDoc() {
        return tipoDoc;
    }
    
    public String getDireccion() {
        return direccion;
    }

    public String getEmail() {
        return email;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getClave() {
        return clave;
    }

    public String getFecha_reg() {
        return fecha_reg;
    }

    public String getUltima_con() {
        return ultima_con;
    }

    public String getImgperfil() {
        return imgperfil;
    }

    public long getNumDoc() {
        return numDoc;
    }

    public long getNumCel() {
        return numCel;
    }

    public long getNumTel() {
        return numTel;
    }
   

    public void setNombre(String nombre) {
        persona.nombre = nombre;
    }

    public void setApellidos(String apellidos) {
        persona.apellidos = apellidos;
    }

    public void setTipoDoc(String tipoDoc) {
        persona.tipoDoc = tipoDoc;
    }

    public void setDireccion(String direccion) {
        persona.direccion = direccion;
    }

    public void setEmail(String email) {
        persona.email = email;
    }

    public void setUsuario(String usuario) {
        persona.usuario = usuario;
    }

    public void setClave(String clave) {
        persona.clave = clave;
    }

    public void setFecha_reg(String fecha_reg) {
        persona.fecha_reg = fecha_reg;
    }

    public void setUltima_con(String ultima_con) {
        persona.ultima_con = ultima_con;
    }

    public void setImgperfil(String imgperfil) {
        persona.imgperfil = imgperfil;
    }

    public void setNumDoc(int numDoc) {
        persona.numDoc = numDoc;
    }

    public void setNumCel(int numCel) {
        persona.numCel = numCel;
    }

    public void setNumTel(int numTel) {
        persona.numTel = numTel;
    }
    
    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        persona.idPersona = idPersona;
    }
    
}
