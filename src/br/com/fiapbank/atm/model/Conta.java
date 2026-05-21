package br.com.fiapbank.atm.model;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
public abstract class Conta {
    protected Cliente cliente;
    protected Dinheiro saldo;
    protected Double taxa;
    protected StatusConta status;
    protected LocalDate dataAbertura;
    protected ContaAcesso contaAcesso;
    protected List<Movimentacao> movimentacoes;
    public Conta(Cliente cliente, ContaAcesso contaAcesso, Dinheiro saldo, Double taxaMensal) {
        this.cliente = cliente;
        this.contaAcesso = contaAcesso;
        this.saldo = saldo;
        this.taxa = taxaMensal;
        this.status = StatusConta.ATIVA;
        this.dataAbertura = LocalDate.now();
        this.movimentacoes = new ArrayList<>();
    }
    public void realizarSaque(Dinheiro valor) {
        if (valor.menorOuIgualAZero()) {
            throw new IllegalArgumentException("Valor do saque deve ser maior que zero.");
        }
        if (!saldo.maiorOuIgualA(valor)) {
            throw new IllegalArgumentException("Saldo insuficiente para realizar o saque.");
        }
        sacar(valor);
        registrarMovimentacao(valor, TipoMovimentacao.SAQUE);
        aplicarRegraDeTaxa();
    }
    public void realizarDeposito(Dinheiro valor) {
        if (valor.menorOuIgualAZero()) {
            throw new IllegalArgumentException("Valor do depósito deve ser maior que zero.");
        }
        depositar(valor);
        registrarMovimentacao(valor, TipoMovimentacao.DEPOSITO);
    }
    private void depositar(Dinheiro valor) {
        this.saldo = this.saldo.somar(valor);
    }
    private void sacar(Dinheiro valor) {
        this.saldo = this.saldo.subtrair(valor);
    }
    protected void registrarMovimentacao(Dinheiro valor, TipoMovimentacao tipo) {
        Movimentacao movimentacao = new Movimentacao(LocalDateTime.now(), valor, tipo);
        movimentacoes.add(movimentacao);
    }
    protected abstract void aplicarRegraDeTaxa();
    public Dinheiro getSaldo() {
        return saldo;
    }
    public Cliente getCliente() {
        return cliente;
    }
    public LocalDate getDataAbertura() {
        return dataAbertura;
    }
    public StatusConta getStatus() {
        return status;
    }
    public ContaAcesso getContaAcesso() {
        return contaAcesso;
    }
    public List<Movimentacao> getMovimentacoes() {
        return new ArrayList<>(movimentacoes);
    }
}
