public class ProdutosDTO {
    private long id; // Alterado para 'long'
    private String nome;
    private int valor;
    private String status;

    // Getters e Setters
    public long getId() {
        return id; // Alterado para 'long'
    }

    public void setId(long id) {
        this.id = id; // Alterado para 'long'
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}