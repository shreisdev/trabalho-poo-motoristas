# Sistema de Pagamento de Motoristas

Trabalho da disciplina de Programação Orientada a Objetos.

Simulador do motor financeiro de uma plataforma de mobilidade (estilo Uber/99),
responsável por calcular o pagamento líquido dos motoristas.

## Restrições arquiteturais atendidas

- **Sem interfaces.**
- **Sem composição de objetos complexos** (não existe classe auxiliar tipo
  `CalculadoraDeTaxa`). Tudo é resolvido com **herança, classe abstrata,
  polimorfismo (sobrescrita) e encapsulamento**.
- **Encapsulamento rigoroso**: todos os atributos são `private`; as filhas acessam
  o estado apenas por getters ou pelo método `protected` controlado.
- **Clean Code**: nomes descritivos, constantes nomeadas (sem números mágicos),
  métodos curtos e com responsabilidade única.

## Estrutura

```
motorista-pagamento/
└── src/
    ├── Motorista.java                    (classe base abstrata)
    ├── MotoristaIniciante.java
    ├── MotoristaPremium.java
    ├── MotoristaParceiroFrotista.java
    └── Main.java                         (classe executável)
```

## Diagrama de classes

```
            +-----------------------+
            |  <<abstract>>         |
            |      Motorista        |
            +-----------------------+
            | - nome : String       |
            | - placa : String      |
            | - quantidadeCorridas  |
            | - valorTotalBruto     |
            +-----------------------+
            | + registrarCorrida()  |
            | + calcularPagamento.. |
            +-----------+-----------+
                        |
        +---------------+----------------+
        |               |                |
  Iniciante        Premium          ParceiroFrotista
  (25% taxa)       (15% + R$2,00)   (20% - R$800,00)
  (-R$50 se        por corrida      piso em R$ 0,00
   <5 corridas)
```

## Regras de negócio

| Categoria | Taxa da plataforma | Regra extra |
|---|---|---|
| `MotoristaIniciante` | 25% | Menos de 5 corridas no mês: desconto de R$ 50,00 (taxa de inatividade) |
| `MotoristaPremium` | 15% | Bônus de R$ 2,00 por corrida, somado ao bruto **antes** da taxa |
| `MotoristaParceiroFrotista` | 20% | Desconto fixo de R$ 800,00 (aluguel do veículo). Se o líquido ficar negativo, retorna `0.0` |

## Onde está o polimorfismo

`MotoristaPremium` **sobrescreve** `registrarCorrida(double)` para aplicar o bônus
de R$ 2,00, reutilizando a lógica da base através de `super.registrarCorrida(valor)`.

`Main.imprimirExtrato(Motorista)` recebe qualquer subtipo e chama
`calcularPagamentoLiquido()` — em tempo de execução cada objeto usa a sua própria regra.

## Como compilar e executar

Requer JDK 17 ou superior.

```bash
# na raiz do projeto
javac -d bin src/*.java
java -cp bin Main
```

## Saída esperada

```
=============================== HOLERITES ===============================
Motorista              Placa      Corridas     Valor Bruto      Valor Liquido
------------------------------------------------------------------------
Ana Souza              ABC-1D23   4         R$     230.00 R$     122.50
Bruno Lima             DEF-2E34   5         R$     455.00 R$     386.75
Carlos Rocha           GHI-3F45   1         R$     600.00 R$       0.00
=========================================================================
```

### Conferência dos cálculos

| Motorista | Bruto | Cálculo | Líquido |
|---|---|---|---|
| Ana Souza (Iniciante) | 230,00 | 230 × 0,75 − 50 (4 corridas < 5) | **122,50** |
| Bruno Lima (Premium) | 455,00 | (445 + 5×2) × 0,85 | **386,75** |
| Carlos Rocha (Frotista) | 600,00 | 600 × 0,80 − 800 = −320 → piso | **0,00** |
