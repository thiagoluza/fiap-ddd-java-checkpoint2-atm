package br.com.fiapbank.atm.application;
import br.com.fiapbank.atm.model.Conta;
public class AutorizacaoService {
    private Conta conta;
    public AutorizacaoService(Conta conta) {
        this.conta = conta;
    }
    public Boolean autorizar(String senha) {
        return conta.getContaAcesso().validarSenha(senha);
    }
    public Boolean isBloqueado() {
        return conta.getContaAcesso().isBloqueado();
    }
}
