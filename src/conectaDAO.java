import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class conectaDAO {

    // Método para conectar ao banco de dados
    public Connection connectDB() {
        Connection conn = null;
        
        try {
            // Estabelece a conexão com o banco de dados MySQL
            conn = DriverManager.getConnection("jdbc:mysql://localhost/uc11?user=root&password=1234");
        } catch (SQLException erro) {
            // Exibe a mensagem de erro caso a conexão falhe
            JOptionPane.showMessageDialog(null, "Erro ConectaDAO: " + erro.getMessage());
        }
        
        return conn;
    }

    // Método para fechar a conexão com o banco
    public void closeConnection(Connection conn) {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao fechar a conexão: " + erro.getMessage());
        }
    }
}
