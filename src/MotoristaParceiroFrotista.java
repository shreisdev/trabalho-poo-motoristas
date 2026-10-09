/**
 * Motorista parceiro frotista da plataforma.
 *
 * O motorista aluga o carro da plataforma.
 *
 * Regra de pagamento: a plataforma cobra 20% do valor bruto.
 * Regra extra: desconto fixo de R$ 800,00 (aluguel do veiculo).
 * Se o valor liquido ficar negativo, retorna 0.0 (situacao simplificada:
 * o motorista nao deve nada a plataforma, apenas recebe zero).
 */
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