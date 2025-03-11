import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class vendasVIEW extends javax.swing.JFrame {

    private JTable listaProdutosVendidos;

    public vendasVIEW() {
        initComponents();
        listarProdutosVendidos();
    }

    private void initComponents() {
        // Inicializando componentes (botões, tabelas, etc)
        listaProdutosVendidos = new JTable();
        JScrollPane scrollPane = new JScrollPane(listaProdutosVendidos);
        this.add(scrollPane);

        // Ajustando o botão e outras configurações da tela
        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para voltar à tela anterior, se necessário
                // Por exemplo, voltar para a tela principal
                vendasVIEW.this.dispose(); // Fecha a tela de vendas
                new MainVIEW().setVisible(true); // Abre a tela principal novamente
            }
        });

        // Adicionando o botão de voltar à tela
        this.add(btnVoltar);
        btnVoltar.setBounds(150, 220, 100, 30); // Ajuste a posição do botão conforme necessário

        // Configurações da janela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLayout(null);
        setVisible(true);
    }

    private void listarProdutosVendidos() {
        try {
            ProdutosDAO produtosdao = new ProdutosDAO();
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("ID");
            model.addColumn("Nome");
            model.addColumn("Valor");
            model.addColumn("Status");

            // Usar o método listarProdutosVendidos() que traz apenas produtos vendidos
            ArrayList<ProdutosDTO> listagem = produtosdao.listarProdutos();

            // Adicionar os dados da lista na tabela
            for (ProdutosDTO produto : listagem) {
                model.addRow(new Object[]{
                    produto.getId(),
                    produto.getNome(),
                    produto.getValor(),
                    produto.getStatus()
                });
            }

            // Atualiza o modelo da tabela para exibir os dados
            listaProdutosVendidos.setModel(model);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar produtos vendidos: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new vendasVIEW();
    }
}
