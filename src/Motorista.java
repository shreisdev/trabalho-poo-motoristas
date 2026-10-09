/**
 * Classe base abstrata que representa um motorista da plataforma.
 *
 * Regras arquiteturais aplicadas:
 * - Classe abstrata: nao pode ser instanciada diretamente (nao existe "new Motorista").
 * - Encapsulamento rigoroso: atributos privados, acesso apenas via metodos.
 * - Metodo abstrato calcularPagamentoLiquido(): cada categoria implementa
 *   a regra de taxa da plataforma (polimorfismo via sobrescrita).
 */
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

    /**
     * Registra uma corrida: soma o valor ao bruto e incrementa a quantidade.
     * Pode ser sobrescrito pelas filhas para aplicar regras extras
     * (ex.: bonus por corrida do MotoristaPremium).
     */
    public void registrarCorrida(double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("O valor da corrida deve ser maior que zero.");
        }
        this.valorTotalBruto += valor;
        this.quantidadeCorridas++;
    }

    /**
     * Metodo protegido: permite que as classes filhas adicionem bonus
     * ao valor bruto sem violar o encapsulamento do atributo.
     */
    protected void adicionarBonusAoValorBruto(double valorBonus) {
        if (valorBonus > 0) {
            this.valorTotalBruto += valorBonus;
        }
    }

    /**
     * Calcula o valor liquido que o motorista tem a receber.
     * A regra de taxa depende da categoria (implementado nas filhas).
     */
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