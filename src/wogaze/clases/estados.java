package wogaze.clases;

/**
 *
 * @author Breyner
 */
public class estados {
    
    // general
    public static int nulo = -1;
    
    // propuesta
    public static int prHecha = 0;
    public static int prAceptada = 1;
    public static int prTrabajo = 2;
    public static int prDetalles = 3;
    public static int prEntrega = 4;
    public static int prCalificacion = 5;
    public static int prRealizada = 6;
    public static int prCancelada = 7;
    
    // pedido
    public static int peHecho = 0;
    public static int peEnMarcha = 1;
    public static int peRealizado = 2;
    
    // trabajador
    public static int tDisponible = 0;
    public static int tOcupado = 1;
    
    // cliente
    public static int cSinPedido = 0;
    public static int cEnPedido = 1;
    
    public static String getEstado(String opcion, int id)
    {
        switch(opcion)
        {
            case "propuesta":
            {
                if(id == prHecha)  {  return "Propuesta hecha"; }
                else if(id == prAceptada)  {  return "Propuesta en marcha"; }
                else if(id == prTrabajo)  {  return "El pedido está siendo trabajado"; }
                else if(id == prDetalles)  {  return "Puliendo detalles para culminar"; }
                else if(id == prEntrega)  {  return "Trabajo listo para entregar"; }
                else if(id == prCalificacion)  {  return "Esperando calificación por el trabajo"; }
                else if(id == prRealizada) {  return "Trabajo realizado con éxito"; }
                else { return "El trabajo fue cancelado"; } 
            }
            
            case "pedido":
            {
                if(id == peHecho)  {  return "Pedido hecha"; }
                else if(id == peEnMarcha)  {  return "El pedido está siendo trabajado"; }
                else {  return "Pedido realizado con éxito"; }
            }
            
            case "trabajador":
            {
                if(id == tDisponible)  {  return "Actualmente sin trabajo"; }
                else {  return "Ocupado en un pedido"; }
            }
            
            case "cliente":
            {
                if(id == cSinPedido)  {  return "Sin pedidos en marcha"; }
                else {  return "Actualmente con un pedido en marcha"; }
            }
        }
        return "> Error al cargar estado <";
    }
}
