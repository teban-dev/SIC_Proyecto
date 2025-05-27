package Login;
import java.util.List;
import java.util.ArrayList;

public class Usuario {
    private String username;
    private String password;
    private String userType;
    
    private static List<Usuario> usuariosRegistrados = new ArrayList<>();
    
    public Usuario(String username, String password, String userType) {
        this.username = username;
        this.password = password;
        this.userType = userType;
        
    }
    
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    
    public String getUserType() {
        return userType;
    }
    
    public static void inicializarUsuarios() {
        usuariosRegistrados.add(new Usuario("admin123", "admin123", "admin"));
        usuariosRegistrados.add(new Usuario("cajero001", "password001", "usuario"));
        usuariosRegistrados.add(new Usuario("cajero002", "password002", "usuario"));
    }
    
    public static boolean validarUsuario(String username, String password) {
        for (Usuario u : usuariosRegistrados) {
            if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean tipoUsuario(String username, String password) {
        for(Usuario u: usuariosRegistrados) {
            if (u.getUsername().equals(username) && u.getPassword().equals(password) && u.getUserType().equals("admin")) {
                return true;
            }
        }
        return false;
    }
    
}
