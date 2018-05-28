package wogaze;

// javafx
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.event.ActionEvent;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;

// controles
import javafx.scene.control.PasswordField;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import wogaze.clases.cargarDatos;
import wogaze.clases.conexion;

// clases
import wogaze.clases.registro;

/**
 *
 * @author Breyner
 */
public class terminarRegistro extends Application {
    
    Stage registroTerSt = new Stage();
    registro R = new registro(); 
    
    public static void main(String[] args) {
        Application.launch(args);
    }
    
    // controles
    @FXML
    private Label lbError;
    @FXML
    private TextField tfUsuario;
    @FXML
    private PasswordField tfClave, tfConfClave;
    
    @Override
    public void start(Stage stage) {
        registroTerSt = stage;
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("formularios/terminarRegistro.fxml"));
                    loader.setController(this);
                    Parent root = loader.load();
                    Scene scn = new Scene(root, 826, 505);
                    stage.setScene(scn);
                    stage.getIcons().add(new Image(getClass().getResourceAsStream("recursos/wogaze-icon.png")));
                    stage.setResizable(false);
                    stage.show();
                    lbError.setVisible(false);
                    
                } catch (Exception e) {
                    // TODO: handle exception
                    System.err.println("error : " + e.getMessage() + "cause : " + e.getCause() + "\n" + e.getStackTrace());
                }
    }
    
    @FXML
    public void handlebtnRegistro(ActionEvent event) throws SQLException, ClassNotFoundException {
        
        if(!R.validarCampos(5, tfUsuario.getText()) || tfUsuario.getText().equals("nuevo"))
        {
           lbError.setVisible(true);
           lbError.setText("Ingrese un usuario válido");
        }
        else if(!R.comprobarUsuario(tfUsuario.getText()))
        {
           lbError.setVisible(true);
           lbError.setText("El usuario que ingresó ya existe");
        }
        else if(!R.comprobarClave(0, tfClave.getText(), tfConfClave.getText()))
        {
            lbError.setVisible(true);
            lbError.setText("Ingrese una contraseña válida");
        }
        else if(!R.comprobarClave(1, tfClave.getText(), tfConfClave.getText()))
        {
            lbError.setVisible(true);
            lbError.setText("Las contraseñas no coinciden");
        }
        else
        {
            String consulta = "UPDATE usuario SET usuario = '" + tfUsuario.getText() + 
             "', clave = '" + tfClave.getText() + "' WHERE id_us = '" + R.getIdPersona() + "'";
            PreparedStatement ps = conexion.db().prepareStatement(consulta, Statement.RETURN_GENERATED_KEYS);
            ps.executeUpdate();
            
            new cargarDatos().cargarDatosUsuario(tfUsuario.getText(), tfClave.getText());
            Stage stage = new Stage();
            new panelPrincipal().start(stage);
            lbError.setVisible(false);
            registroTerSt.close();
            
        }
    }
    
}
