
package wogaze;

/**
 *
 * @author Breyner
 */

// javafx
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

// clases
import wogaze.clases.registro;
import wogaze.clases.cliente;

// base de datos
import java.sql.*;


public class registrobasico extends Application {
    
    registro R = new registro();   
    Stage registrobSt = new Stage();
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Application.launch(args);     
        
    }
    
    // controles
    @FXML
    private TextField tfNombres, tfApellidos, tfCedula, tfCelular, tfDir1, tfDir2, tfDir3;
    @FXML
    private TextField tfEmail, tfTelefono, tfDirInmueble;
    @FXML
    private ComboBox<String> cbTipoRegistro, cbTipoId, cbDepartamento, cbTipoDir, cbTipoDirInm;
    @FXML
    private Label lbError;
    
    @FXML
    public void initialize() {
        cbTipoRegistro.getItems().removeAll(cbTipoRegistro.getItems());
        cbTipoRegistro.getItems().addAll("Trabajador", "Cliente");
        cbTipoRegistro.getSelectionModel().select("Trabajador");
        
        cbTipoId.getItems().removeAll(cbTipoId.getItems());
        cbTipoId.getItems().addAll("CC", "TI", "CE", "PP");
        cbTipoId.getSelectionModel().select("CC");
        
        cbDepartamento.getItems().removeAll(cbDepartamento.getItems());
        cbDepartamento.getItems().addAll("Amazonas", "Antioquia", "Arauca", "Atlántico", "Bolívar", "Boyacá", "Caldas", "Caquetá", 
        "Casanare", "Cauca", "Cesar", "Chocó", "Córdoba", "Cundinamarca", "Guainía", "Guaviare", "Huila", "La Guajira", "Magdalena", "Meta",
        "Nariño", "Norte de Santander", "Putumayo", "Quindío", "Risaralda", "Santander", "Sucre", "Tolima", "Valle del Cauca", "Vaupés",
        "Vichada", "San andrés y providencia");
        cbDepartamento.getSelectionModel().select("Amazonas");
        
        cbTipoDir.getItems().removeAll(cbTipoDir.getItems());
        cbTipoDir.getItems().addAll("AVIAL", "AUT", "AV", "AC", "AK", "CL", "CR", "CIR",
                "DG", "TV", "ZN");
        cbTipoDir.getSelectionModel().select("AVIAL");
        
        cbTipoDirInm.getItems().removeAll(cbTipoDirInm.getItems());
        cbTipoDirInm.getItems().addAll("N/A", "Apto", "Casa", "Edificio", "Hotel", "Oficina", "Manzana", "Zona", "Bloque",
                "Habitación");
        cbTipoDirInm.getSelectionModel().select("N/A");
    }
    @Override
    public void start(Stage stage) {
        registrobSt = stage;
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("formularios/registrobasico.fxml"));
                    loader.setController(this);
                    Parent root = loader.load();
                    Scene scn = new Scene(root, 826, 715);
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
    public void handlebtnSeguir(ActionEvent event) throws SQLException, ClassNotFoundException {
        if(!R.validarCampos(0, tfNombres.getText()))
        {
           lbError.setVisible(true);
           lbError.setText("El nombre debe contener más de 3 carácteres y menos de 40");
        }
        else if(!R.validarCampos(0, tfApellidos.getText()))
        {
           lbError.setVisible(true);
           lbError.setText("Los apellidos deben contener más de 3 carácteres y menos de 40");
        }
        else if(!R.validarCampos(1, tfCedula.getText()))
        {
           lbError.setVisible(true);
           lbError.setText("Ingrese una identificación válida");
        }
        else if(!R.validarCampos(2, tfDir1.getText()) || !R.validarCampos(2, tfDir2.getText()) || !R.validarCampos(2, tfDir3.getText()))
        {
           lbError.setVisible(true);
           lbError.setText("Ingrese una dirección válida");
        }
        else if((!cbTipoDirInm.getValue().equals("N/A") && tfDirInmueble.getText().isEmpty()) || (!cbTipoDirInm.getValue().equals("N/A") && !R.validarCampos(9, tfDirInmueble.getText())))
        {
           lbError.setVisible(true);
           lbError.setText("Ingrese un inmueble válido o seleccione N/A para no seleccionar");
        }
        else if(!R.validarCampos(3, tfTelefono.getText()))
        {
           lbError.setVisible(true);
           lbError.setText("Ingrese un teléfono válido");
        }
        else if(!R.validarCampos(4, tfCelular.getText()))
        {
           lbError.setVisible(true);
           lbError.setText("Ingrese un celular válido");
        }
        else if(tfEmail.getText().isEmpty() || !tfEmail.getText().contains("@") || !tfEmail.getText().contains("."))
        {
           lbError.setVisible(true);
           lbError.setText("Ingrese un email válido");
        }
        else if(!R.comprobarEmail(tfEmail.getText()))
        {
           lbError.setVisible(true);
           lbError.setText("Ingrese un email que ingreso ya existe");
        }
        else
        {
            String direccion = cbDepartamento.getValue() + ", " + cbTipoDir.getValue() + " " + tfDir1.getText() + " No." + tfDir2.getText() + "-" + tfDir3.getText();
            if(!cbTipoDirInm.getValue().equals("N/A"))
                direccion = direccion + " / " + cbTipoDirInm.getValue() + " " + tfDirInmueble.getText();
            
            R.agregarPersona(tfNombres.getText(), tfApellidos.getText(), cbTipoId.getValue(), 
            tfCedula.getText(), tfCelular.getText(), tfTelefono.getText(),
            direccion, tfEmail.getText());
            
            lbError.setVisible(false);
            Stage stage = new Stage();
            if(cbTipoRegistro.getValue().equals("Cliente"))
            {
                new cliente().agregarCliente(R.getIdPersona(), false);
                new terminarRegistro().start(stage);
                registrobSt.hide();
            }
            else
            {
                new registrotrabajador().start(stage);
                registrobSt.hide();
            }
            
        }
     }
    
    @FXML
    public void handlebtnIngresar(ActionEvent event) {
        Stage stage = new Stage();
        new Wogaze().start(stage);
        registrobSt.close();
     }
    
    @FXML
    private void tfNombresValidar(KeyEvent evt) {
        R.validarTexto(evt.getCharacter(), evt);
    }         
    @FXML
    private void tfApellidosValidar(KeyEvent evt) {                                     
        R.validarTexto(evt.getCharacter(), evt);
    }                                    
    @FXML
    private void tfCedulaValidar(KeyEvent evt) {                                  
       R.validarNumero(evt.getCharacter(), evt);
    }                                 
    @FXML
    private void tfTelefonoValidar(KeyEvent evt) {                                    
        R.validarNumero(evt.getCharacter(), evt);
    }                                   
    @FXML
    private void tfCelularValidar(KeyEvent evt) {                                   
        R.validarNumero(evt.getCharacter(), evt);
    }                                  

    
}
