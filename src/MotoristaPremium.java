/**
 * Motorista premium da plataforma.
 *
 * Regra de pagamento: a plataforma cobra apenas 15% do valor bruto.
 * Regra extra: a cada corrida registrada, ganha um bonus fixo de R$ 2,00
 * adicionado ao valor bruto ANTES de calcular a taxa da plataforma.
 *
 * Demonstra polimorfismo: sobrescreve registrarCorrida() para aplicar
 * o bonus, reutilizando a logica da classe base via super.
 */
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