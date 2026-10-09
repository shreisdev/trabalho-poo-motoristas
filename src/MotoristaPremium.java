public class MotoristaPremium extends Motorista {

    private static final double TAXA_PLATAFORMA = 0.15;
    private static final double BONUS_POR_CORRIDA = 2.0;

    public MotoristaPremium(String nome, String placa) {
        super(nome, placa);
    }

    @Override
    public void registrarCorrida(double valor) {
        super.registrarCorrida(valor);
        adicionarBonusAoValorBruto(BONUS_POR_CORRIDA);
    }

    @Override
    public double calcularPagamentoLiquido() {
        return getValorTotalBruto() * (1 - TAXA_PLATAFORMA);
    }
}