public abstract class Motorista {

    private final String nome;
    private final String placa;
    private int quantidadeCorridas;
    private double valorTotalBruto;

    protected Motorista(String nome, String placa) {
        this.nome = nome;
        this.placa = placa;
        this.quantidadeCorridas = 0;
        this.valorTotalBruto = 0.0;
    }

    public void registrarCorrida(double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("O valor da corrida deve ser maior que zero.");
        }
        this.valorTotalBruto += valor;
        this.quantidadeCorridas++;
    }

    protected void adicionarBonusAoValorBruto(double valorBonus) {
        if (valorBonus > 0) {
            this.valorTotalBruto += valorBonus;
        }
    }

    public abstract double calcularPagamentoLiquido();

    public String getNome() {
        return nome;
    }

    public String getPlaca() {
        return placa;
    }

    public int getQuantidadeCorridas() {
        return quantidadeCorridas;
    }

    public double getValorTotalBruto() {
        return valorTotalBruto;
    }
}