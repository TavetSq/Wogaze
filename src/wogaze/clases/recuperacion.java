package wogaze.clases;


// email
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

// clave
import java.util.Random;

/**
 *
 * @author Breyner
 */
public class recuperacion {
    
    private static String USER_NAME = ""; // ingresa tu usuario de GMAIL sin el @gmail.com.
    private static String PASSWORD = "";  // la contraseña de tu GMAIL
    
    public boolean validarDatos(String us, String em) throws SQLException, ClassNotFoundException
    {
        String clgenerada = generarClave();
        Statement stm;
        stm = conexion.db().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        ResultSet rs = stm.executeQuery("SELECT id_us, clave FROM usuario WHERE usuario = '" + us + "' AND email = '" + em + "'");
        if(rs.next())
        {
            rs.updateString("clave", clgenerada);
            rs.updateRow();
            String desde = USER_NAME;
            String pass = PASSWORD;
            String[] para = { em };
            String asunto = "Recuperación de clave | Wogaze";
            String cuerpo = "¡Gracias por confiar en Wogaze!\n"
                + "Tu nueva contraseña es: " + clgenerada + 
                "\nRecuerda cambiar tu contraseña una vez ingreses al sistema\n"
                + "---------------------\n"
                + "El equipo de Wogaze";
            enviarCorreo(desde, pass, para, asunto, cuerpo);
            return true;
        }

        return false;
    }
    
    public boolean validarCampos(String us, String em)
    {
              if(us.length() < 4 || us.length() > 20) return false;
              else if(em.length() < 4 || em.length() > 55) return false;
              else if(!em.contains("@") || !em.contains(".")) return false;
              else if(us.equals("null")) return false;
        return true;
    }  
        
    private static String generarClave()
    {
        String alfabetoMay = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";
        String alfabetoMin = alfabetoMay.toLowerCase();
        String especiales = "%/-_*#!$=?+";
        String nums = "0123456789";
        String alfanumerico = alfabetoMay + nums + alfabetoMin + especiales;
        char[] caracteres = alfanumerico.toCharArray();
        double tamaño = Math.random()*4+10; // clave entre 10 y 14 digitos
        Random rd = new Random();
        StringBuilder sb = new StringBuilder((int) tamaño);
        for(int i = 0; i < (int) tamaño; i++)
        {
            char c = caracteres[rd.nextInt(caracteres.length)];
            sb.append(c);
        }
        return sb.toString();
    }

    private static void enviarCorreo(String from, String pass, String[] to, String subject, String body) {
        Properties props = System.getProperties();
        String host = "smtp.gmail.com";
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.trust", host);
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", pass);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(from));
            InternetAddress[] toAddress = new InternetAddress[to.length];

            // To get the array of addresses
            for( int i = 0; i < to.length; i++ ) {
                toAddress[i] = new InternetAddress(to[i]);
            }

            for( int i = 0; i < toAddress.length; i++) {
                message.addRecipient(Message.RecipientType.TO, toAddress[i]);
            }

            message.setSubject(subject);
            message.setText(body);
            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, pass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        }
        catch (AddressException ae) {
            ae.printStackTrace();
        }
        catch (MessagingException me) {
            me.printStackTrace();
        }
    }
}
