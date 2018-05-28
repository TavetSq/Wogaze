
package wogaze;

/**
 *
 * @author Breyner
 */

// javafx
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;

// clases
import wogaze.clases.ingreso;

// controles
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import wogaze.clases.cargarDatos;


public class Wogaze extends Application {
    
    ingreso ing = new ingreso();
    Stage wogazeSt = new Stage();

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Application.launch(args);
    }
    
    // controles
    @FXML
    private TextField tfUsuario;
    @FXML
    private PasswordField tfClave;
    @FXML
    private Label lbError;
    
    
    // inicializador
    @Override
    public void start(Stage stage) {
        wogazeSt = stage;
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("formularios/inicio.fxml"));
                    loader.setController(this);
                    Parent root = loader.load();
                    Scene scn = new Scene(root, 741, 347);
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
public void handlebtnIngreso(ActionEvent event) {
    if(ing.validarCampos(tfUsuario.getText(), tfClave.getText()))
        {
            if(ing.validarIngreso(tfUsuario.getText(), tfClave.getText()))
            {
                new cargarDatos().cargarDatosUsuario(tfUsuario.getText(), tfClave.getText());
                lbError.setVisible(false);
                Stage st = new Stage();
                new panelPrincipal().start(st);
                wogazeSt.close();
            }
            else
            {
                lbError.setText("El usuario y/o contraseña son incorrectos.");
                lbError.setVisible(true);
            }
        }
        else
        {
            lbError.setText("Ingrese un usuario y/o contraseña válidos.");
            lbError.setVisible(true);
        }
}

@FXML
public void handlebtnRegistro(ActionEvent event) {
    Stage stage = new Stage();
    new registrobasico().start(stage);
    wogazeSt.hide();
}

@FXML
public void handlebtnOlvidarClave(ActionEvent event) {
    Stage stage = new Stage();
    new recuperarClave().start(stage);
    wogazeSt.hide();
}
    
}
