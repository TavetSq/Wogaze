
package wogaze;

/**
 *
 * @author Breyner
 */

// javafx
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;

// controles
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import wogaze.clases.conexion;

// clases
import wogaze.clases.registro;
import wogaze.clases.trabajador;

public class registrotrabajador extends Application {
    
    registro R = new registro();   
    trabajador T = new trabajador();
    Stage registroTSt = new Stage();
    
    public static void main(String[] args) {
        Application.launch(args);
    }
    
    
    // controles
    @FXML
    private Label lbError;
    @FXML
    private ComboBox<String> cbOcupacionPrincipal, cbTiempoLabor;
    @FXML
    private TextField tfHabilidadPrincipal, tfOtrasHabilidades;
    @FXML
    private TextArea tfPerfilPersonal;
    
    
    @FXML
    public void initialize() throws ClassNotFoundException, SQLException {
       cbOcupacionPrincipal.getItems().removeAll(cbOcupacionPrincipal.getItems());
       cbTiempoLabor.getItems().removeAll(cbTiempoLabor.getItems());
       ResultSet rs;
       rs = conexion.db().createStatement().executeQuery("SELECT * FROM trabajos");
       while(rs.next())
       {
           cbOcupacionPrincipal.getItems().add(rs.getString(2));
       }
       cbOcupacionPrincipal.getSelectionModel().select(cbOcupacionPrincipal.getItems().get(0));
       
       rs = conexion.db().createStatement().executeQuery("SELECT * FROM experiencia");
       while(rs.next())
       {
           cbTiempoLabor.getItems().add(rs.getString(2));
       }
       cbTiempoLabor.getSelectionModel().select(cbTiempoLabor.getItems().get(0));
    }
    
    
    @Override
    public void start(Stage stage) {
        registroTSt = stage;
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("formularios/registrotrabajador.fxml"));
                    loader.setController(this);
                    Parent root = loader.load();
                    Scene scn = new Scene(root, 826, 560);
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
    public void handleBtnCompletar(ActionEvent event) {
        if(!R.validarCampos(6, tfHabilidadPrincipal.getText()))
        {
           lbError.setVisible(true);
           lbError.setText("Debe escribir por lo menos dos habilidades separadas por coma.");
        }
        else if(!R.validarCampos(7, tfPerfilPersonal.getText()))
        {
           lbError.setVisible(true);
           lbError.setText("Carácteres perfil personal: tienes " + R.contarCaracteres(tfPerfilPersonal.getText()) + " / mín. 90 máx. 520");
        }
        else
        {
            T.agregarTrabajador(R.getIdPersona(), cbOcupacionPrincipal.getValue(), tfHabilidadPrincipal.getText(), 
            tfPerfilPersonal.getText(), cbTiempoLabor.getValue(), tfOtrasHabilidades.getText(), false);
            
            Stage stage = new Stage();
            new terminarRegistro().start(stage);
            registroTSt.close();
            lbError.setVisible(false);
        }
     }
    
    @FXML
    public void handlebtnSalir(ActionEvent event) {
        Stage stage = new Stage();
        new Wogaze().start(stage);
        registroTSt.close();
     }
    
    @FXML
    public void tfHPValidar(KeyEvent event) {
        R.validarTexto(event.getCharacter(), event);
     }
    
    @FXML
    public void tfPerfilPValidar(KeyEvent event) {
        R.validarTexto(event.getCharacter(), event);
     }
    
    @FXML
    public void tfOtrasHabilidades(KeyEvent event) {
        R.validarTexto(event.getCharacter(), event);
     }
    
}
