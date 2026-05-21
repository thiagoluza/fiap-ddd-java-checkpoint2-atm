package br.com.fiapbank.atm;
import br.com.fiapbank.atm.application.AutorizacaoService;
import br.com.fiapbank.atm.application.ContaFactory;
import br.com.fiapbank.atm.application.ContaService;
import br.com.fiapbank.atm.model.Conta;
import br.com.fiapbank.atm.model.Cliente;
import br.com.fiapbank.atm.model.ContaAcesso;
import br.com.fiapbank.atm.model.Dinheiro;
import br.com.fiapbank.atm.presentation.TerminalBancarioController;
public class Main {
    public static void main(String[] args) {
        TerminalBancarioController terminal = new TerminalBancarioController();
        String nomeCompleto = terminal.coletarNomeCompleto();
        String senhaForte = terminal.coletarSenhaForte();
        Cliente cliente = new Cliente(nomeCompleto);
        ContaAcesso contaAcesso = new ContaAcesso(senhaForte);
        Dinheiro saldoInicial = new Dinheiro("0.00");
        ContaFactory factory = ContaFactory.getInstance();
        Conta conta = factory.criarContaCorrente(cliente, contaAcesso, saldoInicial);
        ContaService contaService = new ContaService(conta);
        AutorizacaoService autorizacaoService = new AutorizacaoService(conta);
        terminal.inicializarServicos(contaService, autorizacaoService);
        Boolean acessoAutorizado = terminal.executarAutenticacao(cliente.obterPrimeiroNome());
        if (acessoAutorizado) {
            terminal.exibirMenuPrincipal();
        }
        terminal.fecharScanner();
    }
}
