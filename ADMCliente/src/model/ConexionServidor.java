package model;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class ConexionServidor {
    
    Socket s;

    /**
     * <h3>Constructor de un Socket Cliente</h3>
     * <p>Metodo constructor para una conexion al servidor</p>
     * @param host la ip del cliente
     * @param port el puerto del servidor a cual conectarse
     * @throws IOException Captura de errores al intentar conectarse
     */
    public ConexionServidor(String host, int port) throws IOException {
        this.s = new Socket(InetAddress.getByName(host), port);
    }
    
    /**
     * Getters Input entrada del cliente
     * @return InputStream del cliente
     * @throws IOException Captura de errores al intentar obtener inputStream
     */
    public InputStream getInputStream() throws IOException {
        return s.getInputStream();
    }
    
    /**
     * Getters Output salida del cliente
     * @return OutputStream del cliente
     * @throws IOException Captura de errores al intentar obtener OutputStream
     */
    public OutputStream getOutputStream() throws IOException {
        return s.getOutputStream();
    }
}
