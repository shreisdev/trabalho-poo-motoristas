/**
 * Motorista iniciante da plataforma.
 *
 * Regra de pagamento: a plataforma cobra 25% do valor bruto (recebe 75%).
 * Regra extra: com menos de 5 corridas no mes, desconta R$ 50,00
 * como "taxa de inatividade".
 */
public class MotoristaIniciante extends Motorista {

    private static final double TAXA_PLATAFORMA = 0.25;
    private static final double TAXA_INATIVIDADE = 50.0;
    private static final int CORRIDAS_MINIMAS_PARA_EVITAR_TAXA = 5;

    public MotoristaIniciante(String nome, String placa) {
        super(nome, placa);
    }

    @Override
    public double calcularPagamentoLiquido() {
        double valorLiquido = getValorTotalBruto() * (1 - TAXA_PLATAFORMA);

        if (getQuantidadeCorridas() < CORRIDAS_MINIMAS_PARA_EVITAR_TAXA) {
            valorLiquido -= TAXA_INATIVIDADE;
        }

        return valorLiquido;
    }
}