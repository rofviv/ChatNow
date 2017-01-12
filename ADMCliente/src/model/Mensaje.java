package model;

public class Mensaje {
    
    private Usuario emisor;
    private Usuario receptor;
    private int tipoMensaje;
    private String contenido;
    private String fecha;
    
    /**
     * Metodo Constructor
     * 
     * @param emisor autor del mensaje
     * @param tipoMensaje 0: desconectado, 1: conectado, 2: Mensaje Privado, 3: Mensaje Masivo, 4: Usuarios conectados
     * @param fecha en la que se envio el mensaje
     */
    public Mensaje(Usuario emisor, int tipoMensaje, String fecha) {
        this(emisor, null, tipoMensaje, null, fecha);
    }
    
    /**
     * Metodo Constructor
     * 
     * @param emisor autor del mensaje
     * @param mensaje Contenido del mensaje
     * @param fecha Enviada del mensaje
     */
    public Mensaje(Usuario emisor, String mensaje, String fecha) {
        this(emisor, null, 0, mensaje, fecha);
    }

    /**
     * Metodo Constructor
     * 
     * @param emisor autor del mensaje
     * @param receptor destino del mensaje
     * @param tipoMensaje 0: desconectado, 1: conectado, 2: Mensaje Privado, 3: Mensaje Masivo, 4: Usuarios conectados
     * @param contenido Contenido del mensaje
     * @param fecha Enviada del mensaje
     */
    public Mensaje(Usuario emisor, Usuario receptor, int tipoMensaje, String contenido, String fecha) {
        this.emisor = emisor;
        this.receptor = receptor;
        this.tipoMensaje = tipoMensaje;
        this.contenido = contenido;
        this.fecha = fecha;
    }

    /**
     * Getters Usuario Emisor
     * 
     * @return Objeto Usuario
     */
    public Usuario getEmisor() {
        return emisor;
    }

    /**
     * Getters Usuario Receptor
     * 
     * @return Objeto Usuario
     */
    public Usuario getReceptor() {
        return receptor;
    }

    /**
     * Getters tipo de mensaje
     * <p>0: desconectado, 1: conectado, 2: Mensaje Privado, 3: Mensaje Masivo, 4: Usuarios conectados</p>
     * @return numero entero
     */
    public int getTipoMensaje() {
        return tipoMensaje;
    }

    /**
     * Getters del contenido del mensaje
     * @return contenido String
     */
    public String getContenido() {
        return contenido;
    }

    /**
     * Getters fecha enviada del mensaje
     * @return fecha String
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * Setters Objeto Usuario receptor
     * <p>Asigna un Usuario quien recibira el mensaje</p>
     * @param receptor Objeto usuario
     */
    public void setUsuarioDestino(Usuario receptor) {
        this.receptor = receptor;
    }

    /**
     * Asignar tipo de mensaje
     * <p>0: desconectado, 1: conectado, 2: Mensaje Privado, 3: Mensaje Masivo, 4: Usuarios conectados</p>
     * @param tipoMensaje 
     */
    public void setTipoMensaje(int tipoMensaje) {
        this.tipoMensaje = tipoMensaje;
    }  
}
