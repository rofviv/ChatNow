package model;

/**
 *
 * @author rofviv
 */
public class ComandosServer {
    
    private String[] comandos = {"iniciar", "detener", "estado", "salir", "usuarios"};
    
    /**
     * <p>Metodo para verificar si existe la orden <code>comando</code> en la terminal del servidor.</p>
     * 
     * @param comando
     * @return true o false
     */
    public boolean existeComando(String comando) {
        boolean bool = false;
        for (String c : comandos) {
            if (comando.equals(c)) {
                bool = true;
                break;
            }
        }
        return bool;
    }
    
}
