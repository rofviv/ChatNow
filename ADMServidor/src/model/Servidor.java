package model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import view.Principal;

/**
 *
 * @author rofviv
 */
public class Servidor {

    private static ServerSocket servidor;
    private static Socket cliente;
    private Thread threadServidor;
    private Thread threadRecibe;
    private Thread threadEnvia;
    private boolean iniciando = false;
    private ArrayList<Usuario> users = new ArrayList();
    private ArrayList<Socket> conexiones = new ArrayList();
    private DefaultListModel modelLista = new DefaultListModel();

    /**
     * Metodo constructor sin parametros
     * llama al estado del Servidor: conectado - desconectado
     */
    public Servidor() {
        estado();
    }

    /**
     * Verifica que orden se esta dando para realizar una accion
     * @param comando iniciar, detener, estado, salir, usuarios
     */
    public void ejecutar(String comando) {
        switch (comando) {
            case "iniciar":
                iniciar();
                break;
            case "detener":
                desconectar();
                break;
            case "estado":
                estado();
                break;
            case "salir":
                apagarServidor();
                break;
            case "usuarios":
                mostrarUsuarios();
                break;
        }
    }

    /**
     * Metodo iniciar.
     * <p>Inicia el servidor para esperar nuevas conexiones</p>
     */
    public void iniciar() {
        if (iniciando) {
            Principal.txtVerMensajes.append("root@Servidor:~$ iniciar\n   El Servidor ya está iniciado.\n");
        } else {
            try {
                servidor = new ServerSocket(11111, 10);
                Principal.txtVerMensajes.append("root@Servidor:~$ iniciar\n   Servidor iniciado\n   Esperando Cliente...\n");
                Principal.lblEstado.setText("1");
                iniciando = true;
                System.out.println(servidor);
                if (null == threadServidor) {
                    manejadorServidor();
                } else {
                    threadServidor.resume();
                }
            } catch (Exception ex) {
                System.err.println("Error en serveSocket " + ex.getMessage() + "\n");
            }
        }
    }

    /**
     * Desconecta el servidor, y cierra todas las conexiones.
     */
    public void desconectar() {
        if (iniciando) {
            iniciando = false;
            try {
                threadServidor.suspend();
                servidor.close();
                if (null != cliente) {
                    cerrarConexiones();
                    threadRecibe.suspend();
                    threadEnvia.suspend();
                }
                desconectarTodosUsuarios();
                Principal.txtVerMensajes.append("root@Servidor:~$ detener\n   Desconectando el servidor, cerrando conexiones...\n");
                Principal.lblEstado.setText("0");
            } catch (IOException ex) {
                System.err.println("Error al cerrar el servidor " + ex.getMessage() + "\n");
            }
        } else {
            Principal.txtVerMensajes.append("root@Servidor:~$ detener\n   El servidor ya está desconectado.\n");
        }

    }

    /**
     * Muestra el estado del servidor, Conectado - Desconectado
     */
    public void estado() {
        String estado = (iniciando) ? "Servidor conectado." : "Servidor Desconectado.";
        Principal.txtVerMensajes.append("root@Servidor:~$ estado\n   Estado: " + estado + "\n");
    }

    /**
     * Apagar el servidor.
     * <p>Espera 7 milesimas de segundo en despedirse "bye" y 7 milesimas de segundo en apagar.</p>
     */
    public void apagarServidor() {
        Principal.txtVerMensajes.append("root@Servidor:~$ salir\n   Apagando el servidor...\n");
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(700);
                    Principal.txtVerMensajes.append("   Bye...\n");
                    Thread.sleep(700);
                    System.exit(0);
                } catch (InterruptedException ex) {
                    System.err.println("Error al apagar el servidor.");
                }
            }
        });
        t.start();

    }

    /**
     * Muestra todos los usuarios conectados en el area de texto
     */
    public void mostrarUsuarios() {
        Principal.txtVerMensajes.append("root@Servidor:~$ usuarios\n   *Usuario activos*\n");
        if (users.isEmpty()) {
            Principal.txtVerMensajes.append("     No hay usuarios conectados.\n");
        } else {
            for (Usuario u : users) {
                Principal.txtVerMensajes.append("     " + u.getNombre() + " - " + u.getIp() + "\n");
            }
        }
    }

    /**
     * Encargado de recibir nuevas conexiones, nuevos clientes
     */
    public void manejadorServidor() {
        threadServidor = new Thread(new Runnable() {
            @Override
            public void run() {
                while (iniciando) {
                    try {
                        cliente = servidor.accept();
                        activarCliente(cliente);
                    } catch (IOException ex) {
                        System.err.println("Error en aceptar clientes " + ex.getMessage() + "\n");
                    }
                }
            }
        });
        threadServidor.start();
    }

    /**
     * Metodo encargado de verificar si existe el usuario
     * @param user Objeto usuario para buscar
     * @return la posicion del usuario en el array
     */
    private int buscarUsuario(Usuario user) {
        int r = -1;
        for (int i = 0; i < users.size(); i++) {
            if (user.getNombre().equals(users.get(i).getNombre())) {
                r = i;
                break;
            }
        }
        return r;
    }

    /**
     * desconectar usuario, eliminar de la lista de usuarios conectados
     * @param user objeto usuario
     * @param fecha hora en la que se desconecto el usuario
     */
    private void desconectarUsuario(Usuario user, String fecha) {
        int r = buscarUsuario(user);
        users.remove(r);
        Principal.txtVerMensajes.append(user.getNombre() + " se ha desconectado " + fecha + ".\n");
        compartirUsuariosConectados();
    }

    /**
     * Elimina a todos los usuarios conectados de la lista.
     */
    private void desconectarTodosUsuarios() {
        users.removeAll(users);
    }

    /**
     * Cierra las conexiones de todos los usuarios para no enviar ni recibir mensajes.
     * @throws IOException Captura de errores al cerrar las conexiones
     */
    private void cerrarConexiones() throws IOException {
        for (Socket conexionCliente : conexiones) {
            conexionCliente.close();
        }
    }

    /**
     * Añade un nuevo usuario a la lista de conectados
     * @param user Objeto usuario
     * @param fecha hora a la que se agrego
     */
    private void nuevoUsuario(Usuario user, String fecha) {
        users.add(user);
        Principal.txtVerMensajes.append(user.getNombre() + " se ha conectado " + fecha + " - "
                + "ip: " + user.getIp() + "\n");
        compartirUsuariosConectados();
    }

    /**
     * Guarda la conexion del usuario nuevo
     * @param cliente Socket
     */
    private void nuevaConexion(Socket cliente) {
        conexiones.add(cliente);
    }

    /**
     * Envio de mensajes privados
     * @param emisor Objeto Usuario quien envia
     * @param receptor Objeto Usuario quien recibe
     * @param msj String del contenido del mensaje
     * @param fecha Hora que se envio el mensaje
     */
    private void mensajePrivado(Usuario emisor, Usuario receptor, String msj, String fecha) {
        Principal.txtVerMensajes.append(emisor.getNombre() + " envio un mensaje a  " + receptor.getNombre() + " -- " + fecha + ".\n");
        emitirMensaje(new Mensaje(emisor, receptor, 0, msj, fecha), 2);
    }

    /**
     * Envio de mensajes masivos, a todos
     * @param user Objeto usuario quien envio el mensaje
     * @param msj Cuerpo del mensaje
     * @param fecha Hora que se envio el mensaje
     */
    private void mensajeMasivo(Usuario user, String msj, String fecha) {
        Principal.txtVerMensajes.append(user.getNombre() + " dice: " + msj + " -- " + fecha + ".\n");
        emitirMensaje(new Mensaje(user, msj, fecha), 3);
    }

    /**
     * Activa el Input y Output de los clientes conectados
     * @param cliente Socket cliente
     */
    private void activarCliente(Socket cliente) {
        recibirMensaje(cliente);
        nuevaConexion(cliente);
    }

    /**
     * Activa la recepcion de mensajes de los clientes, verificando que tipo de mensaje estan enviando los clientes.
     * @param cliente Socket cliente
     */
    private void recibirMensaje(Socket cliente) {
        threadRecibe = new Thread(new Runnable() {
            String mensaje = "";
            boolean usuarioConectado = true;
            ObjectInputStream entrada;
            ObjectOutputStream sal;

            @Override
            public void run() {
                try {
                    sal = new ObjectOutputStream(cliente.getOutputStream());
                    sal.flush();
                    entrada = new ObjectInputStream(cliente.getInputStream());
                } catch (IOException ex) {
                    System.err.println("Error al recibir mensaje del cliente " + ex.getMessage());
                }
                while (usuarioConectado) {
                    try {
                        mensaje = (String) entrada.readObject();
                        switch (mensaje.split("~")[0]) {
                            case "0":
                                desconectarUsuario(new Usuario(mensaje.split("~")[1].split("&")[1], mensaje.split("~")[1].split("&")[0]), mensaje.split("~")[2]);
                                usuarioConectado = false;
                                break;
                            case "1":
                                nuevoUsuario(new Usuario(mensaje.split("~")[1].split("&")[1], mensaje.split("~")[1].split("&")[0], sal), mensaje.split("~")[2]);
                                break;
                            case "2":
                                mensajePrivado(new Usuario(mensaje.split("~")[1].split("&")[1], mensaje.split("~")[1].split("&")[0]), new Usuario(mensaje.split("~")[2].split("&")[1], mensaje.split("~")[2].split("&")[0]),
                                        mensaje.split("~")[3], mensaje.split("~")[4]);
                                break;
                            case "3":
                                mensajeMasivo(new Usuario(mensaje.split("~")[1].split("&")[1], mensaje.split("~")[1].split("&")[0]), mensaje.split("~")[2], mensaje.split("~")[3]);
                                break;
                            default:
                                break;
                        }
                    } catch (IOException ex) {
                        System.err.println("Error al leer el mensaje");
                        usuarioConectado = false;
                    } catch (ClassNotFoundException ex) {
                        System.err.println("Error al leer el mensaje part2");
                        usuarioConectado = false;
                    }
                }
            }
        });
        threadRecibe.start();
    }

    /**
     * Envia el mensaje y el tipo de mensaje a los usuarios
     * @param mensaje Objeto mensaje
     * @param tipo tipo de mensaje
     */
    public void emitirMensaje(Mensaje mensaje, int tipo) {
        threadEnvia = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    if (users.size() == 1) {
                        users.get(0).getSalida().writeObject("-1~" + mensaje.getFecha());
                    } else {
                        switch (tipo) {
                            case 2:
                                emitirMensajePrivado(mensaje);
                                break;
                            case 3:
                                emitirMensajeMasivo(mensaje);
                                break;
                        }
                    }

                } catch (IOException ex) {
                    System.err.println("Error al enviar mensaje del servidor " + ex.getMessage());
                }
            }
        });
        threadEnvia.start();
    }

    /**
     * Comparte a cada usuario los usuarios conectados y desconectados del momento
     */
    public void compartirUsuariosConectados() {
        modelLista.clear();
        String usuarios = "";
        for (int j = 0; j < users.size(); j++) {
            usuarios += users.get(j).getNombre() + "~";
        }
        for (int i = 0; i < users.size(); i++) {
            modelLista.addElement(users.get(i).getNombre() + " - " + users.get(i).getIp());
            try {
                users.get(i).getSalida().writeObject("4~" + usuarios);
            } catch (IOException ex) {
                System.err.println("Error al compartir usuarios " + ex.getMessage());
            }
        }
        Principal.lsUsuarios.setModel(modelLista);
    }

    /**
     * Emite el mensaje privado con el objeto Mensaje
     * @param mensaje Objeto mensaje
     * @throws IOException Captura de errores
     */
    private void emitirMensajePrivado(Mensaje mensaje) throws IOException {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getNombre().equals(mensaje.getReceptor().getNombre())) {
                users.get(i).getSalida().writeObject(mensaje.getEmisor().getNombre() + "~" + mensaje.getContenido() + "~" + mensaje.getFecha());
                users.get(i).getSalida().flush();
            }
        }
    }

    /**
     * Emite el mensaje privado con el objeto Mensaje
     * @param mensaje Objeto mensaje
     * @throws IOException Captura de errores
     */
    private void emitirMensajeMasivo(Mensaje mensaje) throws IOException {
        for (int i = 0; i < users.size(); i++) {
            if (!users.get(i).getNombre().equals(mensaje.getEmisor().getNombre())) {
                users.get(i).getSalida().writeObject(mensaje.getEmisor().getNombre() + "~" + mensaje.getContenido() + "~" + mensaje.getFecha());
                users.get(i).getSalida().flush();
            }
        }
    }
}
