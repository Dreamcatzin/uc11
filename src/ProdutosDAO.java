import java.sql.*;
import javax.swing.JOptionPane;
import java.util.ArrayList;

public class ProdutosDAO {

    // Conexão com o banco de dados
    private Connection connectDB() {
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/seu_banco", "usuario", "senha");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro na conexão com o banco de dados: " + e.getMessage());
            return null;
        }
    }
public void cadastrarProduto(ProdutosDTO produto) {
    Connection conn = connectDB();  // Método para conectar ao banco
    try {
        // A query para inserir os dados na tabela "produtos"
        String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";
        
        PreparedStatement ps = conn.prepareStatement(sql);
        // Preenchendo os parâmetros da query com os dados do produto
        ps.setString(1, produto.getNome());
        ps.setDouble(2, produto.getValor());
        ps.setString(3, produto.getStatus());  // Exemplo de status: "Disponível"
        
        // Executando a query para inserir o produto no banco de dados
        ps.executeUpdate();
        ps.close();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Erro ao cadastrar produto: " + e.getMessage());
    } finally {
        closeConnection(conn);  // Método para fechar a conexão com o banco
    }
}
public ArrayList<ProdutosDTO> listarProdutos() {
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    Connection conn = connectDB();  // Método para conectar ao banco
    try {
        // A query para selecionar todos os produtos da tabela "produtos"
        String sql = "SELECT * FROM produtos";
        
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        
        // Iterando pelo resultado da consulta e adicionando na lista
        while (rs.next()) {
            ProdutosDTO produto = new ProdutosDTO();
            produto.setId(rs.getInt("id"));
            produto.setNome(rs.getString("nome"));
            produto.setValor((int) rs.getDouble("valor"));
            produto.setStatus(rs.getString("status"));
            listagem.add(produto);
        }
        
        rs.close();
        ps.close();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Erro ao listar produtos: " + e.getMessage());
    } finally {
        closeConnection(conn);  // Método para fechar a conexão com o banco
    }
    return listagem;
}
    // Método para vender um produto, alterando seu status para "Vendido"
    public void venderProduto(int id) {
        Connection conn = connectDB();
        try {
            String sql = "UPDATE produtos SET status = 'Vendido' WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao vender produto: " + e.getMessage());
        } finally {
            closeConnection(conn);
        }
    }

    // Método para fechar a conexão com o banco
    private void closeConnection(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao fechar conexão: " + e.getMessage());
        }
    }
}
