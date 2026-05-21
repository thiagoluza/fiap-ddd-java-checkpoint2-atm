package br.com.fiapbank.atm.application;
import br.com.fiapbank.atm.model.Conta;
import br.com.fiapbank.atm.model.ContaAcesso;
import br.com.fiapbank.atm.model.ContaCorrente;
import br.com.fiapbank.atm.model.ContaPoupanca;
import br.com.fiapbank.atm.model.Cliente;
import br.com.fiapbank.atm.model.Dinheiro;
public class ContaFactory {
    private static ContaFactory instance;
    private ContaFactory() {}
    public static ContaFactory getInstance() {
        if (instance == null) {
            instance = new ContaFactory();
        }
        return instance;
    }
    public Conta criarContaCorrente(Cliente cliente, ContaAcesso contaAcesso, Dinheiro saldo) {
        return new ContaCorrente(cliente, contaAcesso, saldo);
    }
    public Conta criarContaPoupanca(Cliente cliente, ContaAcesso contaAcesso, Dinheiro saldo) {
        return new ContaPoupanca(cliente, contaAcesso, saldo);
    }
}
