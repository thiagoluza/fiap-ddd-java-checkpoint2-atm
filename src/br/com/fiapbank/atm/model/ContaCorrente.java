package br.com.fiapbank.atm.model;
public class ContaCorrente extends Conta {
    public static final Double TAXA_MANUTENCAO = -25.00;
    public ContaCorrente(Cliente cliente, Dinheiro saldo) {
        super(cliente, null, saldo, TAXA_MANUTENCAO);
    }
    public ContaCorrente(Cliente cliente, ContaAcesso contaAcesso, Dinheiro saldo) {
        super(cliente, contaAcesso, saldo, TAXA_MANUTENCAO);
    }
    @Override
    protected void aplicarRegraDeTaxa() {
        Dinheiro valorTaxa = new Dinheiro("2.50");
        if (saldo.maiorOuIgualA(valorTaxa)) {
            saldo = saldo.subtrair(valorTaxa);
            registrarMovimentacao(valorTaxa, TipoMovimentacao.TAXA);
        }
    }
    public void aplicarTaxaMensal() {
        Dinheiro valorTaxa = new Dinheiro(String.valueOf(Math.abs(TAXA_MANUTENCAO)));
        if (saldo.maiorOuIgualA(valorTaxa)) {
            saldo = saldo.subtrair(valorTaxa);
            registrarMovimentacao(valorTaxa, TipoMovimentacao.TAXA);
        }
    }
}
