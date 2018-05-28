
package wogaze;

// javafx
import javafx.event.ActionEvent;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Side;
import javafx.stage.Stage;
import javafx.stage.Modality;
import javafx.stage.WindowEvent;
import javafx.stage.FileChooser;


// otros
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import javafx.embed.swing.SwingFXUtils;
import javax.imageio.ImageIO;

import javafx.geometry.HPos;
import javafx.geometry.VPos;



// controles
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.image.Image;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;


// clases
import wogaze.clases.calificacion;
import wogaze.clases.cargarDatos;
import wogaze.clases.cliente;
import wogaze.clases.conexion;
import wogaze.clases.persona;
import wogaze.clases.trabajador;
import wogaze.clases.estados;

/**
 *
 * @author Breyner
 */
public class panelPrincipal extends Application {
    
    Stage panelPrincipalSt = new Stage();
    private final persona P;
    private final cliente C;
    private final calificacion Ca;
    private final trabajador T;
    
    public panelPrincipal()
    {
        P = new persona();
        Ca = new calificacion();
        T = new trabajador();
        C = new cliente();
    }
        
    public static void main(String[] args) {
        Application.launch(args);
    }
    
    //-------------- Controles
    //------- Ajustes
    //--- General
    @FXML
    private Label lbAjNombre;
    @FXML
    private StackPane panelAjustes;
    @FXML
    private ImageView avatarImgAj;
    @FXML
    private TextField tfNombres, tfApellidos, tfIdentificacion, tfTipoId, tfNuevaContra;
    @FXML
    private ComboBox cbDpto,cbCalle, cbInmueble;
    @FXML
    private TextField tfDir1, tfDir2, tfTelefono, tfInmueble, tfCelular, tfEmail, tfDir3;
    @FXML
    private TextField tfConfirmarNueva, tfClave;
    @FXML
    private Pane panelAjustesCliente, panelAjustesTrabajador;
    
    //--- Parte trabajador
    @FXML
    private TextField tfAreaTrabajador, tfCompetenciasTra, tfHabilidadesTra;
    @FXML
    private TextArea tfPerfilLaboralTra;
    @FXML
    private ComboBox cbExpTra;
    
    //-- Parte cliente
    @FXML
    private TextField tfPedHechos, tfPedCancelados, tfPedCompletados, tfPromCal, tfAreaExigida, tfTrabajadorPref;
    
    
    //------- Escritorio
    //--- General
    @FXML
    private ImageView avatarImg;
    @FXML
    private Pane pnBtnRegresar, pnBusquedaError, pnBusquedaNormal;
    @FXML
    private StackPane panelTrabajador, panelCliente, panelnfoUs, panelBuscar;
    @FXML
    private Label lbNombre;
    @FXML
    private TextField tfBuscar;
    
    //--- Parte cliente
    @FXML
    private TextArea taPerfilP, taHabilidadesTra, taOtrasHabilidadesTra, taNotasVersion, taPropuestaTrabajador;
    @FXML
    private TextArea taMiPedido, taDescripcionTrabajo;
    @FXML
    private Label lbExpLaboral, lbTrabajosHechos, lbReputacion, bAreaPrincipal, lbPropuestasPublicadas, lbWogazeVersion, lbWogazeVersion1;
    @FXML
    private Label lbNombreTrabajador, lbTrabajosCalificados, lbTotalPedidos, lbAreaPrincipal, lbEstadoPed;
    @FXML
    private Label lbFechaLimite, lbFechaPedido, lbNombreCl, lbTituloPedido;
    @FXML
    private ImageView imgAvatarTrabajador, imgTrabajo;
    @FXML
    private Tab tabPedidoActual, tabInfoTrabajador;
    @FXML
    private PieChart stPieChartC;
    @FXML
    private Button btnCalificar;
    
    //--- Parte trabajador
    @FXML
    private Label lbPuntajeDesc, lbPuntaje, lbPieChart, lbDescripcion, taTRultiActu, taTRversionWogaze;
    @FXML
    private Label lbClienteTA, lbDirTA, lbEntregaTA, lbEstadoTA;
    @FXML
    private TextArea taPropuestaTA, taCPropuestaTA, taTRnotasV;
    @FXML
    private PieChart stPieChart;
    @FXML
    private LineChart stLineChart;
    @FXML
    private ImageView imgStar1, imgStar2, imgStar3, imgStar4, imgStar5;
    @FXML
    private TitledPane titlPaneTrabajoActual, titlPaneDetallesTrabajo, titlPaneWogaze;
    
    //--------------------------------------------------------------------------
    
    @FXML
    public void initialize() throws ClassNotFoundException, SQLException, IOException {
        cargarEscritorioUs();
    } 
    
    
    @Override
    public void start(Stage stage) {
        panelPrincipalSt = stage;
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("formularios/panel.fxml"));
                    loader.setController(this);
                    Parent root = loader.load();
                    Scene scn = new Scene(root, 900, 700);
                    stage.setScene(scn);
                    stage.getIcons().add(new Image(getClass().getResourceAsStream("recursos/wogaze-icon.png")));
                    stage.setResizable(false);
                    stage.show();
                    
                    stage.setOnCloseRequest(confirmCloseEventHandler);
                    Button btnCerrar = new Button("Cerrar ventana");
                    btnCerrar.setOnAction(event ->
                            stage.fireEvent(
                                    new WindowEvent(
                                            stage,
                                            WindowEvent.WINDOW_CLOSE_REQUEST
                                    )
                            )
                    );
                } catch (Exception e) {
                    // TODO: handle exception
                    System.err.println("error : " + e.getMessage() + "cause : " + e.getCause() + "\n" + e.getStackTrace());
                }
    }
    
    
    //----------------------------------------------- Panel
    // ------- Trabajador
        
    private void cargarInfoTrabajo() throws SQLException, ClassNotFoundException
    {
        boolean tienePedido = true;
        ResultSet rsPedido = null;
        ResultSet rsPropuesta = conexion.db().createStatement().executeQuery("SELECT * FROM propuesta"
        + " WHERE id_trabajador = " + T.getIdTrabajador() + " AND estado <> " + estados.prHecha + "");
        if(rsPropuesta.next()) 
        { 
            rsPedido = conexion.db().createStatement().executeQuery("SELECT * FROM pedido"
            + " WHERE id_pedido = " + rsPropuesta.getInt("id_pedido") + "");
            if(rsPedido.next() && rsPedido.getInt("estado") == estados.peEnMarcha)
            {
                ResultSet rsCliente = conexion.db().createStatement().executeQuery("SELECT * FROM cliente"
                + " WHERE id_cliente = " + rsPedido.getInt("id_cliente") + "");
                if(rsCliente.next())
                {
                    ResultSet rsInfoCliente = conexion.db().createStatement().executeQuery("SELECT id_us, nombres, apellidos, direccion FROM usuario"
                + " WHERE id_us = " + rsCliente.getInt("id_us") + "");
                    if(rsInfoCliente.next())
                    {
                        lbClienteTA.setText(rsInfoCliente.getString("nombres") + " " + rsInfoCliente.getString("apellidos"));
                        lbDirTA.setText(rsInfoCliente.getString("direccion"));
                        lbEntregaTA.setText("Calculando...");
                        lbEstadoTA.setText(estados.getEstado("propuesta", rsPropuesta.getInt("estado")));
                        taPropuestaTA.setText(rsPropuesta.getString("descripcion"));
                        taCPropuestaTA.setText(rsPedido.getString("descripcion"));

                    }
                } 
            }
            else
            {
                tienePedido = false;
            } 
        }
        else if(!rsPropuesta.next() || rsPedido == null)
        {
            taTRnotasV.setPrefHeight((125 - taTRnotasV.getPrefHeight()) + 48);
            titlPaneWogaze.setPrefHeight(titlPaneWogaze.getPrefHeight()+ 102);
            titlPaneDetallesTrabajo.setVisible(false);
            titlPaneTrabajoActual.setVisible(false);

        } 
    }
    
    private void cargarCalificacionTrabajador() throws SQLException, ClassNotFoundException
    {
        double estrellas = Ca.calculoCalificacion(T.getIdTrabajador());
        lbPuntaje.setText(String.format("%.2f", estrellas));

        imgStar1.setImage(new Image(getClass().getResourceAsStream("recursos/estrella-gris.png")));
        imgStar2.setImage(new Image(getClass().getResourceAsStream("recursos/estrella-gris.png")));
        imgStar3.setImage(new Image(getClass().getResourceAsStream("recursos/estrella-gris.png")));
        imgStar4.setImage(new Image(getClass().getResourceAsStream("recursos/estrella-gris.png")));
        imgStar5.setImage(new Image(getClass().getResourceAsStream("recursos/estrella-gris.png"))); 

            if(estrellas > 0.35 && estrellas < 1)
            {
                imgStar1.setImage(new Image(getClass().getResourceAsStream("recursos/estrella-media.png")));
                lbPuntajeDesc.setText("Los clientes no están conformes contigo");
            }
        else
            if(estrellas >= 1 && estrellas < 1.45)
            {
                imgStar1.setImage(new Image(getClass().getResourceAsStream("recursos/estrella.png")));
                lbPuntajeDesc.setText("Oops, debes mejorar tu rendimiento");
            }
        else
            if(estrellas >= 1.45 && estrellas < 2)
            {
                lbPuntajeDesc.setText("Intenta sacar lo mejor de ti");
                imgStar1.setImage(new Image(getClass().getResourceAsStream("recursos/estrella.png")));
                imgStar2.setImage(new Image(getClass().getResourceAsStream("recursos/estrella-media.png")));
            }
        else
            if(estrellas >= 2 && estrellas < 2.45)
            {
                lbPuntajeDesc.setText("Puedes dar aún más de ti ");
                imgStar1.setImage(new Image(getClass().getResourceAsStream("recursos/estrella.png")));
                imgStar2.setImage(new Image(getClass().getResourceAsStream("recursos/estrella.png")));
            }
        else
            if(estrellas >= 2.45 && estrellas < 3)
            {
                lbPuntajeDesc.setText("Podemos seguir mejorando");
                imgStar1.setImage(new Image(getClass().getResourceAsStream("recursos/estrella.png")));
                imgStar2.setImage(new Image(getClass().getResourceAsStream("recursos/estrella.png")));
                imgStar3.setImage(new Image(getClass().getResourceAsStream("recursos/estrella-media.png")));
            }
        else
            if(estrellas >= 3 && estrellas < 3.45)
            {
                lbPuntajeDesc.setText("Trabajador promedio");
                imgStar1.setImage(new Image(getClass().getResourceAsStream("recursos/estrella.png")));
                imgStar2.setImage(new Image(getClass().getResourceAsStream("recursos/estrella.png")));
                imgStar3.setImage(new Image(getClass().getResourceAsStream("recursos/estrella.png")));
            }
        else
            if(estrellas >= 3.45 && estrellas < 4)
            {
                lbPuntajeDesc.setText("Cada vez lo haces mejor");
                imgStar1.setImage(new Image(getClass().getResourceAsStream("recursos/estrella.png")));
                imgStar2.setImage(new Image(getClass().getResourceAsStream("recursos/estrella.png")));
                imgStar3.setImage(new Image(getClass().getResourceAsStream("recursos/estrella.png")));
                imgStar4.setImage(new Image(getClass().getResourceAsStream("recursos/estrella-media.png")));
            }
        else
            if(estrellas >= 4 && estrellas < 4.45)
            {
                lbPuntajeDesc.setText("No hay nadie como tú, ¡sigue así!");
                imgStar1.setImage(new Image(getClass().getResourceAsStream("recursos/estrella.png")));
                imgStar2.setImage(new Image(getClass().getResourceAsStream("recursos/estrella.png")));
                imgStar3.setImage(new Image(getClass().getResourceAsStream("recursos/estrella.png")));
                imgStar4.setImage(new Image(getClass().getResourceAsStream("recursos/estrella.png")));
            }
        else
            if(estrellas >= 4.45 && estrellas < 5)
            {
                imgStar1.setImage(new Image(getClass().getResourceAsStream("recursos/estrella.png")));
                imgStar2.setImage(new Image(getClass().getResourceAsStream("recursos/estrella.png")));
                imgStar3.setImage(new Image(getClass().getResourceAsStream("recursos/estrella.png")));
                imgStar4.setImage(new Image(getClass().getResourceAsStream("recursos/estrella.png")));
                imgStar5.setImage(new Image(getClass().getResourceAsStream("recursos/estrella-media.png")));
                lbPuntajeDesc.setText("¡Eres un trabajador estupendo!");
            }
        else
            if(estrellas == 5)
            {
                imgStar1.setImage(new Image(getClass().getResourceAsStream("recursos/estrella.png")));
                imgStar2.setImage(new Image(getClass().getResourceAsStream("recursos/estrella.png")));
                imgStar3.setImage(new Image(getClass().getResourceAsStream("recursos/estrella.png")));
                imgStar4.setImage(new Image(getClass().getResourceAsStream("recursos/estrella.png")));
                imgStar5.setImage(new Image(getClass().getResourceAsStream("recursos/estrella.png")));
                lbPuntajeDesc.setText("¡Eres toda una estrella!");
            } 
            else
            {
                lbPuntaje.setText("Oops");
                lbPuntajeDesc.setText("¡No te han calificado aún!");
                imgStar1.setImage(new Image(getClass().getResourceAsStream("recursos/estrella.png")));
                imgStar2.setImage(new Image(getClass().getResourceAsStream("recursos/estrella.png")));
                imgStar3.setImage(new Image(getClass().getResourceAsStream("recursos/estrella.png")));
                imgStar4.setImage(new Image(getClass().getResourceAsStream("recursos/estrella.png")));
                imgStar5.setImage(new Image(getClass().getResourceAsStream("recursos/estrella.png"))); 
            }
    }
    
    private int cantidadPedidosEnDemanda(String trabajo) throws SQLException, ClassNotFoundException
    {
        int valor = 0;
       ResultSet rs = conexion.db().createStatement().executeQuery("SELECT area FROM pedido "
       + "WHERE area = '" + trabajo + "'");
       while(rs.next())
       {
           valor++;
       }
        return valor;
    }
    
    private String calcularPedidosEnDemanda(int index) throws SQLException, ClassNotFoundException
    {
        String valor = "";
        int[] tPCont = new int[13];
        for(int i = 0; i < tPCont.length; i++) { tPCont[i] = 0; }
        int mayor[] = new int[3];
        for(int i = 0; i < mayor.length; i++) { mayor[i] = 0; }
        int idMayor[] = new int[3];
        idMayor[0] = 1;
        idMayor[1] = 2;
        idMayor[2] = 3;
        
           ResultSet rs = conexion.db().createStatement().executeQuery("SELECT area FROM pedido");
           while(rs.next()) 
           { 
               ResultSet rs2 = conexion.db().createStatement().executeQuery("SELECT id_trabajo, trabajo FROM trabajos "
               + "WHERE trabajo = '" + rs.getString("area")+ "'");
               while(rs2.next())
               {
                   tPCont[rs2.getInt("id_trabajo")]++;
               }
           }
           
           for(int i = 0; i < tPCont.length; i++)
           {
               if(mayor[2] < tPCont[i])
               {
                   mayor[2] = tPCont[i];
                   idMayor[2] = i;
               }
               if((mayor[1] > mayor[2]) && (mayor[1] < tPCont[i]))
               {
                   mayor[1] = tPCont[i];
                   idMayor[1] = i;
               }
               if((mayor[2] > mayor[1]) && (mayor[0] > mayor[2]) && (mayor[0] < tPCont[i]))
               {
                   mayor[2] = tPCont[i];
                   idMayor[2] = i;
               }
           }

            ResultSet rs2 = conexion.db().createStatement().executeQuery("SELECT id_trabajo, trabajo FROM trabajos "
                    + "WHERE id_trabajo = " + idMayor[index] + "");
            if(rs2.next())
            {
                valor = rs2.getString("trabajo");
            }   
        return valor;
    }
    
    private int contarTrabajador(String opcion, int idT) throws SQLException, ClassNotFoundException 
    {
        int contador = 0;
        switch(opcion)
        {
            case "pedidos":
            {
                ResultSet rsCantPedHechos = conexion.db().createStatement().executeQuery("SELECT id_propuesta, id_trabajador, estado FROM propuesta"
                   + " WHERE id_trabajador = " + idT + " AND estado = " + estados.prRealizada + "");
                    while(rsCantPedHechos.next())
                    {
                        contador++;
                    }
                return contador;
            }
            case "propuestas":
            {
                ResultSet rsPropuestas = conexion.db().createStatement().executeQuery("SELECT id_propuesta, id_trabajador FROM propuesta"
                + " WHERE id_trabajador = " + idT + "");
                while(rsPropuestas.next())
                {
                    contador++;
                }
                return contador;
            }
        }
        return contador;
    }
    
    // ------- Cliente
    private String calcularAreasExigidas(int index) throws SQLException, ClassNotFoundException
    {
        String valor = "";
        int[] tPCont = new int[13];
        for(int i = 0; i < tPCont.length; i++) { tPCont[i] = 0; }
        int mayor[] = new int[3];
        for(int i = 0; i < mayor.length; i++) { mayor[i] = 0; }
        int idMayor[] = new int[3];
        idMayor[0] = 1;
        idMayor[1] = 2;
        idMayor[2] = 3;
        
           ResultSet rs = conexion.db().createStatement().executeQuery("SELECT area, id_cliente FROM pedido WHERE "
                   + "id_cliente = " + C.getIdCliente() + "");
           while(rs.next()) 
           { 
               ResultSet rs2 = conexion.db().createStatement().executeQuery("SELECT id_trabajo, trabajo FROM trabajos "
               + "WHERE trabajo = '" + rs.getString("area")+ "'");
               while(rs2.next())
               {
                   tPCont[rs2.getInt("id_trabajo")]++;
               }
           }
           
           for(int i = 0; i < tPCont.length; i++)
           {
               if(mayor[2] < tPCont[i])
               {
                   mayor[2] = tPCont[i];
                   idMayor[2] = i;
               }
               if((mayor[1] > mayor[2]) && (mayor[1] < tPCont[i]))
               {
                   mayor[1] = tPCont[i];
                   idMayor[1] = i;
               }
               if((mayor[2] > mayor[1]) && (mayor[0] > mayor[2]) && (mayor[0] < tPCont[i]))
               {
                   mayor[2] = tPCont[i];
                   idMayor[2] = i;
               }
           }

            ResultSet rs2 = conexion.db().createStatement().executeQuery("SELECT id_trabajo, trabajo FROM trabajos "
                    + "WHERE id_trabajo = " + idMayor[index] + "");
            if(rs2.next())
            {
                valor = rs2.getString("trabajo");
            }   
        return valor;
    }
        
    private int cantidadEnAreaExigida(String trabajo) throws SQLException, ClassNotFoundException
    {
        int valor = 0;
       ResultSet rs = conexion.db().createStatement().executeQuery("SELECT area FROM pedido "
       + "WHERE area = '" + trabajo + "'");
       while(rs.next())
       {
           valor++;
       }
        return valor;
    }
    
    // ------- General

    private void cargarInfoPrincipal(String tipo) throws SQLException, ClassNotFoundException, IOException
    {
        try
        {
        if(tipo.equals("cliente"))
        {
            int idTrabajador = -1;
            ResultSet rsProp = conexion.db().createStatement().executeQuery("SELECT * FROM propuesta"
            + " WHERE estado >= " + estados.prAceptada + " AND estado <= " + estados.prCalificacion + "");
            while(rsProp.next())
            {
                ResultSet rsPedido = conexion.db().createStatement().executeQuery("SELECT * FROM pedido"
            + " WHERE id_pedido = " + rsProp.getInt("id_pedido") + " AND estado = " + estados.peEnMarcha + "");
                while(rsPedido.next())
                {
                    if(rsPedido.getInt("id_cliente") == C.getIdCliente())
                    {
                        tabInfoTrabajador.setDisable(false);
                        tabPedidoActual.setDisable(false);
                       idTrabajador = rsProp.getInt("id_trabajador");
                       taMiPedido.setText(rsPedido.getString("descripcion"));
                       break;
                    }
                }
            }
            
            if(idTrabajador != -1)
            {
                ResultSet rsInfoT = conexion.db().createStatement().executeQuery("SELECT * FROM trabajador"
            + " WHERE id_trabajador = " + idTrabajador + "");
                if(rsInfoT.next())
                {
                    double estrellas = Ca.calculoCalificacion(idTrabajador);
                    lbReputacion.setText(String.format("%.2f", estrellas));
                    lbTrabajosHechos.setText(String.valueOf(contarTrabajador("pedidos", idTrabajador)));
                    lbPropuestasPublicadas.setText(String.valueOf(contarTrabajador("propuestas", idTrabajador)));
                    lbAreaPrincipal.setText(rsInfoT.getString("ocupacion_p"));
                    imgAvatarTrabajador.setImage(cargarFotoPerfil(rsInfoT.getInt("id_us")));
                    lbExpLaboral.setText(rsInfoT.getString("tiempo_labor"));
                    taHabilidadesTra.setText(rsInfoT.getString("habilidades"));
                    taOtrasHabilidadesTra.setText(rsInfoT.getString("habilidades_b"));
                    taPerfilP.setText(rsInfoT.getString("perfil_personal"));
                    lbNombreCl.setText(P.getNombre() + " " + P.getApellidos());
                    lbFechaLimite.setText("Calculando...");
                    lbFechaPedido.setText("Calculando...");
                    ResultSet rsInfoT2 = conexion.db().createStatement().executeQuery("SELECT id_us, nombres, apellidos FROM usuario"
                   + " WHERE id_us = " + rsInfoT.getInt("id_us") + "");
                    if(rsInfoT2.next())
                    {
                        lbNombreTrabajador.setText(cargarUnSoloNombre(rsInfoT2.getString("nombres")) + " " + 
                        cargarUnSoloNombre(rsInfoT2.getString("apellidos")));
                    }
                    
                    ResultSet rsPropuesta = conexion.db().createStatement().executeQuery("SELECT * FROM propuesta"
                   + " WHERE id_trabajador = " + idTrabajador + " AND estado <> " + estados.prCancelada + "");
                    if(rsPropuesta.next())
                    {
                        taPropuestaTrabajador.setText(rsPropuesta.getString("descripcion"));
                        if(rsPropuesta.getInt("estado") != estados.prCalificacion)
                        {
                            btnCalificar.setDisable(true);
                            btnCalificar.setStyle("-fx-background-color: #2c2c2c");
                        }
                        else
                        {
                            btnCalificar.setDisable(false);
                            btnCalificar.setStyle("-fx-background-color: #56a7f4");
                        }
                        lbEstadoPed.setText(estados.getEstado("propuesta", rsPropuesta.getInt("estado")));
                    }
                    
                    ResultSet rsInfoTrabajo = conexion.db().createStatement().executeQuery("SELECT * FROM trabajos"
                   + " WHERE trabajo = '" + rsInfoT.getString("ocupacion_p") + "'");
                    if(rsInfoTrabajo.next())
                    {
                        taDescripcionTrabajo.setText(rsInfoTrabajo.getString("descripcion"));
                    }
                }
            }
        }
        else
        {
            
        }
        }
        catch(Exception e)
        {
            System.err.println("error : " + e.getMessage() + "cause : " + e.getCause() + "\n" + e.getStackTrace());
        }
    }

    private void cargarEstadisticas(String tipo) throws SQLException, ClassNotFoundException
    {
        ObservableList<PieChart.Data> pieChartData = null;
        double c1 = 0, c2 = 0, nula = 100;
        if(tipo.equals("cliente"))
        {
            // ------------------- piechart
                pieChartData =
            FXCollections.observableArrayList(
                new PieChart.Data(calcularAreasExigidas(2), cantidadEnAreaExigida(calcularAreasExigidas(2))));
                
                if(cantidadEnAreaExigida(calcularAreasExigidas(0)) != 0)
                    pieChartData.add(new PieChart.Data(calcularAreasExigidas(0), cantidadEnAreaExigida(calcularAreasExigidas(0))));
                
                if(cantidadEnAreaExigida(calcularAreasExigidas(1)) != 0)
                    pieChartData.add(new PieChart.Data(calcularAreasExigidas(1), cantidadEnAreaExigida(calcularAreasExigidas(1))));

            stPieChartC.setLegendSide(Side.BOTTOM);
            stPieChartC.setLabelsVisible(false);
            stPieChartC.setData(pieChartData);
            stPieChartC.setStartAngle(180);
            
            // ------------------- trabajos calificados y pedidos publicados
            int tCalificados = 0;
            int tPedidos = 0;
            ResultSet rTc = conexion.db().createStatement().executeQuery("SELECT id_pedido, id_cliente, estado FROM pedido"
            + " WHERE id_cliente = " + C.getIdCliente() + "");
            while(rTc.next())
            {
                tPedidos++;
                ResultSet rCal = conexion.db().createStatement().executeQuery("SELECT id_pedido, calificacion FROM calificacion"
            + " WHERE calificacion <> " + estados.nulo + "");
                if(rCal.next())
                {
                    tCalificados++;
                }
            }
            lbTrabajosCalificados.setText(String.valueOf(tCalificados));
            lbTotalPedidos.setText(String.valueOf(tPedidos));
            
        }
        else
        {
           // ------------------- line chart
           XYChart.Series series = new XYChart.Series();
           series.getData().add(new XYChart.Data(calcularPedidosEnDemanda(0), cantidadPedidosEnDemanda(calcularPedidosEnDemanda(0))));
           series.getData().add(new XYChart.Data(calcularPedidosEnDemanda(1), cantidadPedidosEnDemanda(calcularPedidosEnDemanda(1))));
           series.getData().add(new XYChart.Data(calcularPedidosEnDemanda(2), cantidadPedidosEnDemanda(calcularPedidosEnDemanda(2))));
           stLineChart.getData().addAll(series);
           
          // ------------------- pie chart
           ResultSet rr1 = conexion.db().createStatement().executeQuery("SELECT id_propuesta, id_trabajador, estado FROM propuesta"
            + " WHERE id_trabajador = " + T.getIdTrabajador() + " AND estado = "+ estados.prHecha +"");
           while(rr1.next()) { c1++; nula = 0; }
           ResultSet rr2 = conexion.db().createStatement().executeQuery("SELECT id_propuesta, id_trabajador, estado FROM propuesta"
            + " WHERE id_trabajador = " + T.getIdTrabajador() + " AND estado <> " + estados.prHecha + "");
           while(rr2.next()) { c2++; nula = 0; }
            lbPieChart.setText("Mis propuestas");
            if(nula != 0)
            {
                pieChartData =
                FXCollections.observableArrayList(new PieChart.Data("Sin estadísticas", nula));
            }
            else
            {
                pieChartData =
            FXCollections.observableArrayList(
                new PieChart.Data("Hechas", c1),
                new PieChart.Data("Aceptadas", c2));
            }
            stPieChart.setLegendSide(Side.BOTTOM);
            stPieChart.setLabelsVisible(false);
            stPieChart.setData(pieChartData);
            stPieChart.setStartAngle(180);
        }
    }
    

    
    //----------------------------------------------- Botones
    // ------- Trabajador
    @FXML
    public void handlebtnInfTrabajo(ActionEvent event)
    {
    }
    
    @FXML
    public void handlebtnInfDetalles(ActionEvent event)
    {
    }
    
    @FXML
    public void handlebtnInfEntrega(ActionEvent event)
    {
    }
    
    @FXML
    public void btnInfCalificacion(ActionEvent event)
    {
    }
    // ------- Cliente
    @FXML
    public void handlebtnCalificar(ActionEvent event)
    {
    }
    // ------- General
    @FXML
    public void handlebtnSalir(ActionEvent event) throws SQLException, ClassNotFoundException {
        panelPrincipalSt.setOnCloseRequest(confirmCloseEventHandler);
            panelPrincipalSt.fireEvent(
                    new WindowEvent(
                        panelPrincipalSt,
                        WindowEvent.WINDOW_CLOSE_REQUEST));
    }
    
    @FXML
    public void handlebtnBuscar(MouseEvent mouseEvent) throws IOException, SQLException, ClassNotFoundException
    {
        realizarBusqueda(tfBuscar.getText());
    }
    
    @FXML
    private void tfBuscarTecla(KeyEvent evt) throws IOException, SQLException, ClassNotFoundException {
        if(evt.getCode() == KeyCode.ENTER)
        {
            realizarBusqueda(tfBuscar.getText());
        }
    }   
    
    @FXML
    public void handlepnBtnRegresar(MouseEvent mouseEvent)
    {
        regresarBusquedad();
    }
    
    @FXML
    public void handlebtnRegresar(MouseEvent mouseEvent)
    {
        cargarPanelBusqueda(false);

    }
    
    private void regresarBusquedad()
    {
        cargarPanelBusqueda(false);
    }
    //----------------------------------------------- Funciones generales
    private void cargarPanelBusqueda(boolean opc)
    {
        if(opc)
        {
            panelBuscar.toFront();
            panelBuscar.setVisible(true);
            panelnfoUs.setVisible(false);
            panelCliente.setVisible(false);
            panelTrabajador.setVisible(false);
        }
        else
        {
            pnBusquedaError.setVisible(false);
            pnBtnRegresar.setVisible(false);
            panelBuscar.setVisible(false);
            pnBusquedaNormal.setVisible(false);
            panelnfoUs.setVisible(true);
            if(P.isCliente())
            {
                panelCliente.toFront();
                panelCliente.setVisible(true);
            }
            else
            {
                panelTrabajador.toFront();
                panelTrabajador.setVisible(true);
            }
        }
    }
    
    
    public String getDireccionFix(String dir) {
        String fixdir = dir;
        String dpto = "";
        if(dir.contains("/")) // eliminar inmueble
        {
            int i = 0;
            fixdir = "";
            while(!String.valueOf(dir.charAt(i)).equals("/"))
            {
               fixdir = fixdir + String.valueOf(dir.charAt(i));
               i++;
            }
        }
        
        int i = 0;
        while(!String.valueOf(dir.charAt(i)).equals(",")) //agregar departamento al final
        {
           dpto = dpto + String.valueOf(dir.charAt(i));
           i++;
        }
        fixdir = fixdir.replace(dpto + ",", "");
        fixdir = fixdir + ", " + dpto;
        return fixdir;
    }
    
    private void realizarBusqueda(String busqueda) throws IOException, SQLException, ClassNotFoundException
    {
        boolean error = true;
        String buscar = "";
        boolean ingresarBuscar = false;
        String comandos[] = {"direccion:", "dir:", "trabajador:", "tr:", "pedido:", "pe:", "cliente:", "cl:", "general:", "gen:"};
        String comando = "";
        for(int i = 0; i < busqueda.length(); i++)
        {
            if(!ingresarBuscar)
                comando = comando + String.valueOf(busqueda.charAt(i));
            
            if(ingresarBuscar)
                buscar = buscar + String.valueOf(busqueda.charAt(i));
            
            if(String.valueOf(busqueda.charAt(i)).equals(":"))
            {
                ingresarBuscar = true;
            }
        }
        for (String comando1 : comandos) {
            if (comando.equals(comando1)) {
                error = false;
            }
        }
        if(error)
        {
            Alert mensaje;
            mensaje = new Alert(Alert.AlertType.ERROR,
            "Comandos disponibles: (pe)dido, (dir)eccion, (tr)abajador, (cl)iente, (gen)eral\n\n"
                    + "Ejemplo\n"
                    + "direccion:Calle 1 No. 23a - 45\ndir:Calle 1 No. 23a");
            mensaje.setTitle("Información");
            mensaje.setHeaderText("¡Oops, no encontramos ningún comando!");
            ButtonType btnCancelar = new ButtonType("Aceptar", ButtonData.CANCEL_CLOSE);
            mensaje.getButtonTypes().setAll(btnCancelar);
            mensaje.showAndWait();
            tfBuscar.setText("");
        }
        else
        {
            cargarPanelBusqueda(true);
            if(buscar.isEmpty())
            {
                cargarPanelBusqueda(false);
                Alert mensaje;
                mensaje = new Alert(Alert.AlertType.INFORMATION,
                "Por favor ingresar un valor a buscar.");
                mensaje.setTitle("Información");
                mensaje.setHeaderText("¡Parece que hay un error en tu búsqueda!");
                ButtonType btnCancelar = new ButtonType("Aceptar", ButtonData.CANCEL_CLOSE);
                mensaje.getButtonTypes().setAll(btnCancelar);
                mensaje.showAndWait();
            }
            else if((comando.equals(comandos[0]) || comando.equals(comandos[1]))) // Dirección
            {
                cargarPanelBusqueda(false);
                if(buscar.equals("cliente") && P.isTrabajador()) // cargar ubicación del cliente
                {
                    ResultSet rs = conexion.db().createStatement().executeQuery("SELECT id_propuesta, id_pedido, id_trabajador, estado FROM propuesta"
                    + " WHERE id_trabajador = " + T.getIdTrabajador() + " "
                    + "AND estado >= " + estados.prAceptada + " AND estado <= " + estados.prCalificacion + "");
                    if(rs.next())
                    {
                        ResultSet rs2 = conexion.db().createStatement().executeQuery("SELECT id_pedido, id_cliente FROM pedido"
                        + " WHERE id_pedido = " + rs.getInt("id_pedido") + "");
                        if(rs2.next())
                        {
                            ResultSet rs3 = conexion.db().createStatement().executeQuery("SELECT id_cliente, id_us FROM cliente"
                        + " WHERE id_cliente = " + rs2.getInt("id_cliente") + "");
                            if(rs3.next())
                            {
                                ResultSet rs4 = conexion.db().createStatement().executeQuery("SELECT id_us, direccion FROM usuario"
                               + " WHERE id_us = " + rs3.getInt("id_us") + "");
                                if(rs4.next())
                                {
                                    mostrarBusqueda("dir", getDireccionFix(rs4.getString("direccion")));

                                }
                            }
                        }
                    }
                    else
                    {
                        Alert mensaje;
                        mensaje = new Alert(Alert.AlertType.INFORMATION,
                        "No estás en un trabajo como para buscar la dirección de tu cliente.");
                        mensaje.setTitle("Información");
                        mensaje.setHeaderText("¡Parece que hay un error en tu búsqueda!");
                        ButtonType btnCancelar = new ButtonType("Aceptar", ButtonData.CANCEL_CLOSE);
                        mensaje.getButtonTypes().setAll(btnCancelar);
                        mensaje.showAndWait();
                    }
                }
                else
                {
                    mostrarBusqueda("dir", buscar);
                }
            }
            else if((comando.equals(comandos[6]) || comando.equals(comandos[7]))) // Cliente
            {
                if(!P.isTrabajador())
                {
                    Alert mensaje;
                    mensaje = new Alert(Alert.AlertType.ERROR,
                    "Este comando solo está disponible para trabajadores.");
                    mensaje.setTitle("Información");
                    mensaje.setHeaderText("¡Parece que hay un error en tu búsqueda!");
                    ButtonType btnCancelar = new ButtonType("Aceptar", ButtonData.CANCEL_CLOSE);
                    mensaje.getButtonTypes().setAll(btnCancelar);
                    mensaje.showAndWait();
                    tfBuscar.setText("");
                    cargarPanelBusqueda(false);
                }
                else
                {
                }
            }
            else
            {
                pnBusquedaError.toFront();
                pnBusquedaError.setVisible(true);
                pnBtnRegresar.setVisible(true);
            }
        }
        
    }

    
    private void mostrarBusqueda(String opcion, String valor) throws IOException
    {
        switch(opcion)
        {
            case "dir":
            {
                String pagina = "https://www.google.com/maps/dir/" + getDireccionFix(P.getDireccion()) + "/" + valor + ",+Colombia";
                Stage stage = new Stage();
                stage.setTitle("Búsqueda de direcciones");
                Scene scene = new Scene(new Browser(pagina),0,0, Color.web("#fafafa"));
                stage.setScene(scene);
                stage.show();
                break;
            }
        }
    }
   
    //---------------- Mostrar una página web 
    // Código fuente http://docs.oracle.com/javafx/2/webview/jfxpub-webview.htm
    class Browser extends Region {
        final WebView browser = new WebView();
        final WebEngine webEngine = browser.getEngine();
        private Node createSpacer() {
            Region spacer = new Region();
            HBox.setHgrow(spacer, Priority.ALWAYS);
            return spacer;
        }
        public Browser()
        {
        }
        
         public Browser(String pagina) {
            getStyleClass().add("browser");
            webEngine.load(pagina);
            getChildren().add(browser);
        }

        @Override protected void layoutChildren() {
        double w = getWidth();
        double h = getHeight();
        layoutInArea(browser,0,0,w,h,0, HPos.CENTER, VPos.CENTER);
    }

        @Override protected double computePrefWidth(double height) {
            return 702;
        }

        @Override protected double computePrefHeight(double width) {
            return 605;
        }     
    }
    //---------------------------------------------------------
    
     private final EventHandler<WindowEvent> confirmCloseEventHandler = event -> {
        Alert closeConfirmation = new Alert(
                Alert.AlertType.CONFIRMATION,
                "Elija una opción para continuar"
        );
        Button exitButton = (Button) closeConfirmation.getDialogPane().lookupButton(
                ButtonType.OK
        );
        exitButton.setText("Salir");
        closeConfirmation.setHeaderText("¿Estás seguro que deseas salir al menú de ingreso?");
        closeConfirmation.initModality(Modality.APPLICATION_MODAL);
        closeConfirmation.initOwner(panelPrincipalSt);

        Optional<ButtonType> closeResponse = closeConfirmation.showAndWait();
        if (!ButtonType.OK.equals(closeResponse.get())) {
            event.consume();
        }
        else
        {
            try
            {
            new cargarDatos().descargarDatos();
            Stage stage = new Stage();
            new Wogaze().start(stage);
            panelPrincipalSt.close();
            } catch(Exception e) { }
        }
    };
     
    private String cargarUnSoloNombre(String n)
    {
        boolean unicoNombre = false;
        for(int i = 0; i < n.length(); i++)
        {
            if(String.valueOf(n.charAt(i)).equals(" "))
            {
                unicoNombre = true;
            }
        }
        if(unicoNombre)
        {
            String nn = "";
            int i = 0;
            while(!String.valueOf(n.charAt(i)).equals(" "))
            {
                nn = nn + n.charAt(i);
                i++;
            }
            n = nn;
        }
        return n;
    }
     
    private void cargarInformacionWogaze() throws SQLException, ClassNotFoundException
     {
         ResultSet rs = conexion.db().createStatement().executeQuery("SELECT * FROM plataforma ORDER BY id_version DESC LIMIT 1");
        if(rs.next()) 
        { 
            lbWogazeVersion.setText("Wogaze " + rs.getString("version"));
            lbWogazeVersion1.setText("Última actualización el día " + rs.getString("fecha"));
            taNotasVersion.setText(rs.getString("notas"));
            taTRversionWogaze.setText(lbWogazeVersion.getText());
            taTRultiActu.setText(lbWogazeVersion1.getText());
            taTRnotasV.setText(taNotasVersion.getText());
        }
     }
     
    private Image cargarFotoPerfil(int idUs) throws SQLException, ClassNotFoundException, IOException
     {
         Blob imagen =  null;
         Image imagenC = null;
         ResultSet rCFP = conexion.db().createStatement().executeQuery("SELECT id_us, imagen FROM usuario"
            + " WHERE id_us = " + idUs + "");
        if(rCFP.next())
        {
            imagen = rCFP.getBlob("imagen");
        }
        if(imagen != null)
        {
            InputStream inS = imagen.getBinaryStream();
            BufferedImage bfI = ImageIO.read(inS);
            imagenC = SwingFXUtils.toFXImage(bfI, null);
        }
        else
        {
            imagenC = new Image(getClass().getResourceAsStream("recursos/avatar-default.png"));
        }
      return imagenC;
     }
    
    @FXML
    private void cargarEscritorio(MouseEvent mouseEvent) throws SQLException, ClassNotFoundException, IOException
    {
        cargarEscritorioUs();
    }
    private void cargarEscritorioUs() throws SQLException, ClassNotFoundException, IOException
    {
        panelAjustes.setVisible(false);
        
        cargarInformacionWogaze();
        panelnfoUs.setVisible(true);
        if(P.isTrabajador())
        {
            panelTrabajador.toFront();
            panelTrabajador.setVisible(true);
            cargarCalificacionTrabajador();
            cargarInfoTrabajo();
            cargarInfoPrincipal("trabajador");
            cargarEstadisticas("trabajador");
        }
        else
        {
            panelCliente.toFront();
            panelCliente.setVisible(true);
            tabInfoTrabajador.setDisable(true);
            tabPedidoActual.setDisable(true);
            cargarInfoPrincipal("cliente");
            cargarEstadisticas("cliente");
        }

        lbNombre.setText("¡Hola, " + cargarUnSoloNombre(P.getNombre()) + " " + cargarUnSoloNombre(P.getApellidos()) + "!");
        lbDescripcion.setText("Tu última conexión fue el día " + P.getUltima_con());
        // foto de perfil 
        avatarImg.setImage(cargarFotoPerfil(P.getIdPersona()));
        avatarImgAj.setImage(cargarFotoPerfil(P.getIdPersona()));
    }
    
    //-------------------------------------------------------------------------
    //-------------------------------------------------------------------------
    //------------------ Ajustes
    @FXML
    private void cargarAjustes(MouseEvent mouseEvent)
    {
        panelAjustes.setVisible(true);
        panelTrabajador.setVisible(false);
        panelCliente.setVisible(false);
        panelnfoUs.setVisible(false);
        panelBuscar.setVisible(false);
        panelAjustes.toFront();
        
        
        String direcciones[] = {"AVIAL", "AUT", "AV", "AC", "AK", "CL", "CR", "CIR",
                "DG", "TV", "ZN"};
        String inmuebles[] = {"N/A", "Apto", "Casa", "Edificio", "Hotel", "Oficina", "Manzana", "Zona", "Bloque",
                "Habitación"};
        cbDpto.getItems().removeAll(cbDpto.getItems());
        cbDpto.getItems().addAll("Amazonas", "Antioquia", "Arauca", "Atlántico", "Bolívar", "Boyacá", "Caldas", "Caquetá", 
        "Casanare", "Cauca", "Cesar", "Chocó", "Córdoba", "Cundinamarca", "Guainía", "Guaviare", "Huila", "La Guajira", "Magdalena", "Meta",
        "Nariño", "Norte de Santander", "Putumayo", "Quindío", "Risaralda", "Santander", "Sucre", "Tolima", "Valle del Cauca", "Vaupés",
        "Vichada", "San andrés y providencia");
        
        cbCalle.getItems().removeAll(cbCalle.getItems());
        cbCalle.getItems().addAll("AVIAL", "AUT", "AV", "AC", "AK", "CL", "CR", "CIR",
                "DG", "TV", "ZN");
        
        cbInmueble.getItems().removeAll(cbInmueble.getItems());
        cbInmueble.getItems().addAll("N/A", "Apto", "Casa", "Edificio", "Hotel", "Oficina", "Manzana", "Zona", "Bloque",
                "Habitación");
        tfNombres.setText(P.getNombre());
        tfApellidos.setText(P.getApellidos());
        tfIdentificacion.setText(String.valueOf(P.getNumDoc()));
        tfTipoId.setText(((P.getTipoDoc()).equals("CC") ? "Cédula de ciudadanía" : "Cédula de extranjería"));
        
        
        String dir1 = "", dir2 = "", dir3 = "", dir4="", inmueble = "N/A", inmueble2 = "";
        String dpto = "";
        
        int k = 0;
        while(!String.valueOf(P.getDireccion().charAt(k)).equals(",")) // Departamento
        {
           dpto = dpto + String.valueOf(P.getDireccion().charAt(k));
           k++;
        }
        
        for (String dir: direcciones) { // Tipo de calle
            if (P.getDireccion().contains(dir)) {
                dir1 = dir;
                break;
            }
        }
        
        k = 0;
        while(!String.valueOf(P.getDireccion().charAt(k)).equals("N")) // Número calle
        {
            dir2 = dir2 + String.valueOf(P.getDireccion().charAt(k));
             k++;
        }
        dir2 = dir2.replaceAll(dpto + ", " + dir1 + " ", "");
        dir2 = dir2.replaceAll(" ", "");
        
        k = 0;
        while(!String.valueOf(P.getDireccion().charAt(k)).equals("-")) // X -
        {
            dir3 = dir3 + String.valueOf(P.getDireccion().charAt(k));
             k++;
        }
        dir3 = dir3.replaceAll(dpto + ", " + dir1 + " " + dir2 + " No.", "");
        
        k = 0;
        if(P.getDireccion().contains("/")) // - X e inmueble
        {
            while(!String.valueOf(P.getDireccion().charAt(k)).equals("/"))
            {
                dir4 = dir4 + String.valueOf(P.getDireccion().charAt(k));
                k++;
            }
            dir4 = dir4.replaceAll(dpto + ", " + dir1 + " " + dir2 + " No." + dir3 + "-", "");
            for(String in: inmuebles)
            {
                if(P.getDireccion().contains(in))
                {
                    inmueble = in;
                    break;
                }
            }
            
            for(int i = P.getDireccion().lastIndexOf(inmueble); i < P.getDireccion().length(); i++)
            {
                inmueble2 = inmueble2 + String.valueOf(P.getDireccion().charAt(i));
            }
            inmueble2 = inmueble2.replaceAll(inmueble + " ", "");
        }
        else // Si no hay inmueble
        {
            for(int i = 0; i < P.getDireccion().length(); i++)
            {
                dir4 = dir4 + String.valueOf(P.getDireccion().charAt(k));
                k++;
            }
            dir4 = dir4.replaceAll(dpto + ", " + dir1 + " " + dir2 + " No." + dir3 + "-", "");

        }

        
        
        cbDpto.getSelectionModel().select(dpto);
        cbCalle.getSelectionModel().select(dir1);
        tfDir1.setText(dir2);
        tfDir2.setText(dir3);
        tfDir3.setText(dir4);
        cbInmueble.getSelectionModel().select(inmueble);
        tfInmueble.setText(inmueble2);
        tfTelefono.setText(String.valueOf(P.getNumTel()));
        tfCelular.setText(String.valueOf(P.getNumCel()));
        tfEmail.setText(P.getEmail());
        lbAjNombre.setText(P.getNombre() + " " + P.getApellidos());

    }
    
    @FXML
    public void handlebtnDarDeBaja(MouseEvent mouseEvent)
    {
        
    }
    
    @FXML
    public void handlebtnDeshacerCambios(MouseEvent mouseEvent)
    {
        
    }
    
    @FXML
    public void handlebtnGuardarCambios(MouseEvent mouseEvent)
    {
        
    }
    
    @FXML
    public void handleavatarImgAj(MouseEvent mouseEvent) throws MalformedURLException, SQLException, ClassNotFoundException, IOException {
        Alert mensaje;
        mensaje = new Alert(Alert.AlertType.CONFIRMATION);
        mensaje.setTitle("Información");
        mensaje.setHeaderText("¿Seguro que deseas cambiar tu foto de perfil?");
        ButtonType btnAceptar = new ButtonType("Si");
        ButtonType btnCancelar = new ButtonType("Cancelar", ButtonData.CANCEL_CLOSE);
        mensaje.getButtonTypes().setAll(btnAceptar, btnCancelar);
        
        Optional<ButtonType> resultado = mensaje.showAndWait();
        if(resultado.get() == btnAceptar)    
        {
        FileChooser avatar = new FileChooser();
        avatar.setTitle("Seleccionar una nueva imagen de perfil");
        avatar.setInitialDirectory(new File(System.getProperty("user.home")));
        avatar.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Imágenes","*.png", "*.jpg", "*.jpeg"));
        File imagen = avatar.showOpenDialog(panelPrincipalSt);
        if(imagen != null) {
             
                String urlImagen = imagen.toURI().toURL().toString();
                Image nuevaImagen = new Image(urlImagen);
                avatarImg.setImage(nuevaImagen);
                avatarImgAj.setImage(nuevaImagen);
                
                PreparedStatement st = conexion.db().prepareStatement("UPDATE usuario SET imagen = ? WHERE id_us = " + P.getIdPersona() + " ");
                BufferedImage nuevaImagenConvertida = SwingFXUtils.fromFXImage(nuevaImagen, null);
                ByteArrayOutputStream os = new ByteArrayOutputStream();
                ImageIO.write(nuevaImagenConvertida, "jpg", os);
                InputStream is = new ByteArrayInputStream(os.toByteArray());
                st.setBinaryStream(1, is);
                st.executeUpdate();
                
                mensaje = new Alert(Alert.AlertType.INFORMATION);
                mensaje.setTitle("Información");
                mensaje.setHeaderText("Foto de perfil actualizada con éxito");
                mensaje.show();
            }  
        }
    }
}

