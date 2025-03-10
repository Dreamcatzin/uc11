import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutosDAO {

    // Método para conexão com o banco de dados
    private Connection connectDB() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/seu_banco"; // Modifique a URL conforme necessário
        String user = "seu_usuario"; // Seu usuário
        String password = "sua_senha"; // Sua senha

        // Retorna a conexão com o banco de dados
        return DriverManager.getConnection(url, user, password);
    }

    // Método para vender um produto
    public void venderProduto(int produtoId) throws SQLException {
        String query = "UPDATE produtos SET status = 'Vendido' WHERE id = ?";

        try (Connection conn = connectDB(); 
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, produtoId);
            stmt.executeUpdate();
        }
    }

    // Método para listar todos os produtos vendidos
    public List<ProdutosDTO> listarProdutosVendidos() throws SQLException {
        String query = "SELECT * FROM produtos WHERE status = 'Vendido'";
        List<ProdutosDTO> produtosVendidos = new ArrayList<>();

        try (Connection conn = connectDB(); 
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setValor(rs.getDouble("valor")); // Usando getDouble para valores monetários
                produto.setStatus(rs.getString("status"));
                produtosVendidos.add(produto);
            }
        }

        return produtosVendidos;
    }
}
