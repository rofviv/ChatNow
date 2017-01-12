package model;

public class Usuario {
    
    private String ip;
    private String nombre;

    /**
     * Metodo constructor del usuario
     * @param ip ip del Usuario
     * @param nombre Nombre del usuario
     */
    public Usuario(String ip, String nombre) {
        this.ip = ip;
        this.nombre = nombre;
    }

    /**
     * Getters ip del usuario
     * @return String ip
     */
    public String getIp() {
        return ip;
    }

    /**
     * Getters nombre del usuario
     * @return String nombre
     */
    public String getNombre() {
        return nombre;
    }
    
}
