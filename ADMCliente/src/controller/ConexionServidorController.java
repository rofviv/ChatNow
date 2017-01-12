package controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.DefaultListModel;
import model.ConexionServidor;
import model.Mensaje;
import model.Usuario;
import view.Principal;

public class ConexionServidorController {

    private String host = "";
    private Usuario user;
    private final int port = 11111;
    private boolean servidorConectado = false;
    private boolean intentandoConectar = false;
    private DefaultListModel modelLista = new DefaultListModel();
    
    ConexionServidor cn;
    Thread threadRecibe;

    ObjectInputStream entrada;
    ObjectOutputStream salida;   
    
    /**
     * <h3>Constructor</h3>
     * <p>Recibe un objeto de tipo <code>Usuario</code></p>
     * 
     * @param user Objeto Usuario
     * 
     */
    public ConexionServidorController(Usuario user) {
        this.user = user;
        this.host = user.getIp();
    }

    /**
     * Inicia la conexion con el servidor
     */
    public void iniciar() {
        try {
            cn = new ConexionServidor(this.host, this.port);
            entrada = new ObjectInputStream(cn.getInputStream());
            salida = new ObjectOutputStream(cn.getOutputStream());
            servidorConectado = true;
            if (null != threadRecibe) {
                threadRecibe.resume();
            } else {
                recibirMensaje();
            }
            Principal.txtVerMensajes.append("-- Conectado exitosamente.\n");
            enviarMensaje(new Mensaje(this.user, 1, "Ahora mismo"));
        } catch (IOException ex) {
            if (false == intentandoConectar) {
                Principal.txtVerMensajes.append("-- Servidor desconectado, no podrás enviar ni recibir mensajes.\n");
                intentarConectar();
            }
        }
    }

    /**
     * Metodo para enviar un mensaje al servidor
     * @param mensaje Objeto mensaje
     */
    public void enviarMensaje(Mensaje mensaje) {
        if (servidorConectado) {
            Thread threadEnvia = new Thread(new Runnable() {
                @Override
                public void run() {
                    switch (mensaje.getTipoMensaje()) {
                        case 0:
                            desconectar(mensaje);
                            break;
                        case 1:
                            conectar(mensaje);
                            break;
                        case 2:
                            emitirMensajePrivado(mensaje);
                            break;
                        case 3:
                            emitirMensajeMasivo(mensaje);
                            break;
                    }
                }
            });
            threadEnvia.start();
        } else {
            Principal.txtVerMensajes.append("-- Error al enviar mensaje, Servidor desconectado.\n");
        }
    }

    /**
     * Envia un mensaje de tipo 0 al servidor que esta conexion se ha cerrado, usuario desconectado
     * @param mensaje Objeto mensaje
     */
    private void desconectar(Mensaje mensaje) {
        try {
            salida.writeObject("0~" + mensaje.getEmisor().getNombre() + "&" + mensaje.getEmisor().getIp() + "~" + mensaje.getFecha());
            salida.flush();
        } catch (Exception e) {
            Principal.txtVerMensajes.append("-- Error al enviar mensaje, Servidor desconectado.\n");
        }
    }

    /**
     * Envia un mensaje al servidor de tipo 1, un nuevo usuario se ha conectado
     * @param mensaje 
     */
    private void conectar(Mensaje mensaje) {
        try {
            salida.writeObject("1~" + mensaje.getEmisor().getNombre() + "&" + mensaje.getEmisor().getIp() + "~" + mensaje.getFecha());
            salida.flush();
        } catch (Exception e) {
            Principal.txtVerMensajes.append("-- Error al enviar mensaje, Servidor desconectado.\n");
        }
    }

    /**
     * Emite un mensaje privado
     * @param mensaje Objeto mensaje
     */
    private void emitirMensajePrivado(Mensaje mensaje) {
        try {
            salida.writeObject("2~" + mensaje.getEmisor().getNombre() + "&" + mensaje.getEmisor().getIp() + "~" + mensaje.getReceptor().getNombre() + "&" + mensaje.getReceptor().getIp() + "~" + mensaje.getContenido() + "~" + mensaje.getFecha());
            salida.flush();
            Principal.txtVerMensajes.append("Tu: " + mensaje.getContenido() + " -- " + mensaje.getFecha() + "\n");
        } catch (Exception e) {
            Principal.txtVerMensajes.append("-- Error al enviar mensaje, Servidor desconectado.\n");
        }
    }

    /**
     * Emite un mensaje masivo, para todos
     * @param mensaje Objeto mensaje
     */
    private void emitirMensajeMasivo(Mensaje mensaje) {
        try {
            salida.writeObject("3~" + mensaje.getEmisor().getNombre() + "&" + mensaje.getEmisor().getIp() + "~" + mensaje.getContenido() + "~" + mensaje.getFecha());
            salida.flush();
            Principal.txtVerMensajes.append("Tu: " + mensaje.getContenido() + " -- " + mensaje.getFecha() + "\n");
        } catch (Exception e) {
            Principal.txtVerMensajes.append("-- Error al enviar mensaje, Servidor desconectado.\n");
        }
    }
    
    /**
     * Actualiza la lista de los usuarios conectados
     * @param lista String obtenido por el servidor
     */
    public void listaUsuarios(String lista) {
        modelLista.clear();
        for (int i = 1; i < lista.split("~").length; i++) {
            modelLista.addElement(lista.split("~")[i]);
        }
        Principal.lsUsuarios.setModel(modelLista);
    }

    /**
     * Metodo encargado de escuchar nuevos mensajes y verificando de que tipo son
     */
    public void recibirMensaje() {
        threadRecibe = new Thread(new Runnable() {
            @Override
            public void run() {
                String texto;
                while (servidorConectado) {
                    try {
                        texto = (String) entrada.readObject();
                        if ("4".equals(texto.split("~")[0])) {
                            listaUsuarios(texto);
                        } else if (!"-1".equals(texto.split("~")[0])) {
                            Principal.txtVerMensajes.append(texto.split("~")[0] + " dice: " + texto.split("~")[1] + " -- " + texto.split("~")[2] + ".\n");
                        } else {
                            Principal.txtVerMensajes.append("Solo tu estas conectado. -- " + texto.split("~")[1] + "\n");
                        }
                    } catch (Exception ex) {
                        Principal.txtVerMensajes.append("-- Servidor desconectado, no podrás enviar ni recibir mensajes.\n");
                        servidorConectado = false;
                        intentarConectar();
                        threadRecibe.suspend();
                    }
                }
            }
        });
        threadRecibe.start();
    }

    /**
     * Metodo encargado de conectarse si el servidor se encuentra inactivo
     */
    public void intentarConectar() {
        Thread reintentar = new Thread(new Runnable() {
            @Override
            public void run() {
                intentandoConectar = true;
                while (false == servidorConectado) {
                    Principal.txtVerMensajes.append("-- Intentando conectar...\n");
                    iniciar();
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException ex) {
                        System.err.println("Error al dormir el hilo intentar conectar");
                    }
                }
                if (servidorConectado) {
                    intentandoConectar = false;
                }
            }
        });
        reintentar.start();
    }
}
