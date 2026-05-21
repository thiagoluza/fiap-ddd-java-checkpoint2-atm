package br.com.fiapbank.atm.application;
import br.com.fiapbank.atm.model.Conta;
import br.com.fiapbank.atm.model.Dinheiro;
import br.com.fiapbank.atm.model.Movimentacao;
import java.util.List;
public class ContaService {
    private Conta conta;
    public ContaService(Conta conta) {
        this.conta = conta;
    }
    public void realizarDeposito(Dinheiro valor) {
        conta.realizarDeposito(valor);
    }
    public void realizarSaque(Dinheiro valor) {
        conta.realizarSaque(valor);
    }
    public Dinheiro obterSaldo() {
        return conta.getSaldo();
    }
    public List<Movimentacao> obterMovimentacoes() {
        return conta.getMovimentacoes();
    }
}
