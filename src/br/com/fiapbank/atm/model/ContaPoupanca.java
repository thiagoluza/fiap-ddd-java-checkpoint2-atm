package br.com.fiapbank.atm.model;
import java.math.BigDecimal;
import java.math.RoundingMode;
public class ContaPoupanca extends Conta {
    public static final Double RENDIMENTO_MENSAL = 1.0;
    public ContaPoupanca(Cliente cliente, Dinheiro saldo) {
        super(cliente, null, saldo, RENDIMENTO_MENSAL);
    }
    public ContaPoupanca(Cliente cliente, ContaAcesso contaAcesso, Dinheiro saldo) {
        super(cliente, contaAcesso, saldo, RENDIMENTO_MENSAL);
    }
    @Override
    protected void aplicarRegraDeTaxa() {
    }
    public void aplicarTaxaMensal() {
        BigDecimal percentual = new BigDecimal(RENDIMENTO_MENSAL.toString())
                .divide(new BigDecimal("100"), 4, RoundingMode.HALF_UP);
        BigDecimal valorRendimento = saldo.getValor().multiply(percentual).setScale(2, RoundingMode.HALF_UP);
        Dinheiro rendimento = new Dinheiro(valorRendimento);
        saldo = saldo.somar(rendimento);
        registrarMovimentacao(rendimento, TipoMovimentacao.RENDIMENTO);
    }
}
