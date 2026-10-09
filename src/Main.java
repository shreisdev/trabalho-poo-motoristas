import java.util.Locale;

/**
 * Classe executavel que simula o sistema de pagamento de motoristas.
 *
 * Instancia um motorista de cada categoria, registra corridas ficticias
 * e imprime o holerite/extrato de cada um.
 *
 * Demonstra polimorfismo: o metodo imprimirExtrato(Motorista) recebe
 * qualquer Motorista e chama calcularPagamentoLiquido() de forma dinamica.
 */
public class Main {

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);

        MotoristaIniciante iniciante = new MotoristaIniciante("Ana Souza", "ABC-1D23");
        registrarCorridas(iniciante, 60.0, 45.0, 70.0, 55.0);

        MotoristaPremium premium = new MotoristaPremium("Bruno Lima", "DEF-2E34");
        registrarCorridas(premium, 80.0, 95.0, 70.0, 110.0, 90.0);

        MotoristaParceiroFrotista frotista = new MotoristaParceiroFrotista("Carlos Rocha", "GHI-3F45");
        registrarCorridas(frotista, 600.0);

        imprimirHolerites(iniciante, premium, frotista);
    }

    private static void registrarCorridas(Motorista motorista, double... valores) {
        for (double valor : valores) {
            motorista.registrarCorrida(valor);
        }
    }

    private static void imprimirHolerites(Motorista... motoristas) {
        String cabecalho = String.format(
                "%-22s %-10s %-9s %14s %18s",
                "Motorista", "Placa", "Corridas", "Valor Bruto", "Valor Liquido");

        System.out.println("=============================== HOLERITES ===============================");
        System.out.println(cabecalho);
        System.out.println("------------------------------------------------------------------------");

        for (Motorista motorista : motoristas) {
            imprimirExtrato(motorista);
        }

        System.out.println("=========================================================================");
    }

    private static void imprimirExtrato(Motorista motorista) {
        String linha = String.format(
                "%-22s %-10s %-9d R$ %10.2f R$ %10.2f",
                motorista.getNome(),
                motorista.getPlaca(),
                motorista.getQuantidadeCorridas(),
                motorista.getValorTotalBruto(),
                motorista.calcularPagamentoLiquido());

        System.out.println(linha);
    }
}