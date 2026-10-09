public class MotoristaParceiroFrotista extends Motorista {

    private static final double TAXA_PLATAFORMA = 0.20;
    private static final double VALOR_ALUGUEL_VEICULO = 800.0;

    public MotoristaParceiroFrotista(String nome, String placa) {
        super(nome, placa);
    }

    @Override
    public double calcularPagamentoLiquido() {
        double valorLiquido = getValorTotalBruto() * (1 - TAXA_PLATAFORMA) - VALOR_ALUGUEL_VEICULO;
        return Math.max(valorLiquido, 0.0);
    }
}