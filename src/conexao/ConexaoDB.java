package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author guilh
 */
public class ConexaoDB {
    private static final String URL = "jdbc:mysql://localhost:3306/multitask__db";
    private static final String USER = "root";
    private static final String SENHA = "adm123";
    
    public static Connection conectar() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, SENHA);
        } catch (SQLException e){
            System.out.println("Erro ao conectar ao banco: " + e.getMessage());
            return null;
        } catch (ClassNotFoundException e){
            System.out.println("Driver não encontrado: " + e.getMessage());
            return null;
        }
    }
    
    public static void desconectar(Connection conn) {
        if (conn != null){
            try{
                conn.close();
                System.out.println("Conexão encerrada com sucesso!");
            } catch (SQLException e){
                System.out.println("Erro ao desconectar: " + e.getMessage());
            }
        }
    }
}
