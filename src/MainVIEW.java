import java.awt.FlowLayout;
import javax.swing.*;

public class MainVIEW extends JFrame {

    public MainVIEW() {
        // Inicialização da interface principal
        setTitle("Tela Principal");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton btnAbrirVendas = new JButton("Ir para Vendas");
        btnAbrirVendas.addActionListener(e -> {
            // Abre a tela de vendas
            new vendasVIEW().setVisible(true);
            this.setVisible(false);  // Fecha a tela principal
        });

        this.add(btnAbrirVendas);
        setLayout(new FlowLayout());
    }

    public static void main(String[] args) {
        new MainVIEW().setVisible(true);
    }
}
