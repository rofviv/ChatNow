package model;

import java.io.ObjectOutputStream;

public class Usuario {
    
    private String ip;
    private String nombre;
    private ObjectOutputStream salida;

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
     * Metodo constructor del usuario
     * @param ip ip del usuario
     * @param nombre nombre del usuario
     * @param salida OutputStream del Socket cliente
     */
    public Usuario(String ip, String nombre, ObjectOutputStream salida) {
        this(ip, nombre);
        this.salida = salida;
    }

    /**
     * Getters ip del usuario
     * @return String ip
     */
    public String getIp() {
        return this.ip;
    }

    /**
     * Getters nombre del usuario
     * @return String nombre
     */
    public String getNombre() {
        return this.nombre;
    }
    
    /**
     * Getters OutputStream del socket cliente del usuario
     * @return OutputStream del socket Cliente del usuario
     */
    public ObjectOutputStream getSalida() {
        return this.salida;
    }
    
}
