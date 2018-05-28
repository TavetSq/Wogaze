package wogaze;


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
import wogaze.clases.recuperacion;

// controles
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

/**
 *
 * @author Breyner
 */
public class recuperarClave extends Application {
    recuperacion rp = new recuperacion();
    Stage recuperacionSt = new Stage();

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Application.launch(args);
    }
    
    // controles
    @FXML
    private TextField tfUsuario, tfEmail;
    @FXML
    private Label lbInformacion;
    
    
    // inicializador
    @Override
    public void start(Stage stage) {
        recuperacionSt = stage;
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("formularios/recuperar.fxml"));
                    loader.setController(this);
                    Parent root = loader.load();
                    Scene scn = new Scene(root, 522, 472);
                    stage.setScene(scn);
                    stage.getIcons().add(new Image(getClass().getResourceAsStream("recursos/wogaze-icon.png")));
                    stage.setResizable(false);
                    stage.show();
                    lbInformacion.setVisible(false);
                    
                } catch (Exception e) {
                    // TODO: handle exception
                    System.err.println("error : " + e.getMessage() + "cause : " + e.getCause() + "\n" + e.getStackTrace());
                }
                
    }

@FXML
public void handlebtnRecuperar(ActionEvent event) throws SQLException, ClassNotFoundException {
    if(rp.validarCampos(tfUsuario.getText(), tfEmail.getText()))
        {
            if(rp.validarDatos(tfUsuario.getText(), tfEmail.getText()))
            {
                lbInformacion.setTextFill(Color.rgb(94, 177, 255));
                lbInformacion.setText("Fue enviada una nueva clave a su correo electrónico.");
                lbInformacion.setVisible(true);
                
            }
            else
            {
                lbInformacion.setTextFill(Color.rgb(255, 92, 92));
                lbInformacion.setText("El usuario y/o email no coinciden.");
                lbInformacion.setVisible(true);
            }
        }
        else
        {
            lbInformacion.setTextFill(Color.rgb(255, 92, 92));
            lbInformacion.setText("Ingrese un usuario y/o email válidos.");
            lbInformacion.setVisible(true);
        }
}

@FXML
public void handlebtnRegresar(ActionEvent event) {
    Stage stage = new Stage();
    new Wogaze().start(stage);
    recuperacionSt.close();
}

}
