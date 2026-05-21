package br.com.fiapbank.atm.presentation;
import br.com.fiapbank.atm.application.AutorizacaoService;
import br.com.fiapbank.atm.application.ContaService;
import br.com.fiapbank.atm.model.Dinheiro;
import br.com.fiapbank.atm.model.Movimentacao;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
public class TerminalBancarioController {
    private ContaService contaService;
    private AutorizacaoService autorizacaoService;
    private final Scanner scanner;
    private static final String REGEX_SENHA_FORTE =
            "^(?=.*[0-9])(?=.*[A-Z])(?=.*[!@#$%^&*()\\-_+=?><]).{8,}$";
    private static final DateTimeFormatter FORMATADOR_DATA_HORA =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    public TerminalBancarioController() {
        this.scanner = new Scanner(System.in);
    }
    public void inicializarServicos(ContaService contaService, AutorizacaoService autorizacaoService) {
        this.contaService = contaService;
        this.autorizacaoService = autorizacaoService;
    }
    public String coletarNomeCompleto() {
        System.out.println("==============================================");
        System.out.println("   Bem-vindo ao FIAP Bank - Terminal ATM");
        System.out.println("==============================================");
        System.out.print("Informe seu nome completo: ");
        return scanner.nextLine().trim();
    }
    public String coletarSenhaForte() {
        String senhaDigitada = "";
        Boolean senhaValida = false;
        while (!senhaValida) {
            System.out.println("\nA senha deve conter:");
            System.out.println("  - No mínimo 8 caracteres");
            System.out.println("  - Ao menos um número");
            System.out.println("  - Ao menos uma letra maiúscula");
            System.out.println("  - Ao menos um caractere especial: !@#$%^&*()-_+=?><");
            System.out.print("Cadastre sua senha: ");
            senhaDigitada = scanner.nextLine();
            if (senhaDigitada.matches(REGEX_SENHA_FORTE)) {
                senhaValida = true;
                System.out.println("✔ Senha cadastrada com sucesso!");
            } else {
                System.out.println("✘ Senha fraca! Tente novamente.");
            }
        }
        return senhaDigitada;
    }
    public Boolean executarAutenticacao(String primeiroNome) {
        System.out.println("\n----------------------------------------------");
        System.out.println("  Autenticação - Terminal FIAP Bank");
        System.out.println("----------------------------------------------");
        while (!autorizacaoService.isBloqueado()) {
            System.out.print("Digite sua senha: ");
            String senhaInformada = scanner.nextLine();
            if (autorizacaoService.autorizar(senhaInformada)) {
                System.out.println("✔ Acesso autorizado! Bem-vindo, " + primeiroNome + ".");
                return true;
            } else {
                if (autorizacaoService.isBloqueado()) {
                    break;
                }
                System.out.println("✘ Senha incorreta! Tente novamente.");
            }
        }
        System.out.println("\n!!! ACESSO BLOQUEADO !!!");
        System.out.println("Número máximo de tentativas atingido. Contate o suporte.");
        return false;
    }
    public void exibirMenuPrincipal() {
        Integer opcao = 0;
        while (!opcao.equals(5)) {
            System.out.println("\n==============================================");
            System.out.println("   FIAP Bank - Menu Principal");
            System.out.println("==============================================");
            System.out.println("   [ 1 ] Consultar Saldo");
            System.out.println("   [ 2 ] Fazer Depósito");
            System.out.println("   [ 3 ] Fazer Saque");
            System.out.println("   [ 4 ] Histórico de Movimentações");
            System.out.println("   [ 5 ] Sair");
            System.out.println("----------------------------------------------");
            System.out.print("Escolha uma opção: ");
            if (scanner.hasNextInt()) {
                opcao = scanner.nextInt();
                scanner.nextLine();
            } else {
                System.out.println("✘ Entrada inválida! Digite um número entre 1 e 5.");
                scanner.nextLine();
                continue;
            }
            switch (opcao) {
                case 1:
                    exibirSaldo();
                    break;
                case 2:
                    realizarDeposito();
                    break;
                case 3:
                    realizarSaque();
                    break;
                case 4:
                    exibirMovimentacoes();
                    break;
                case 5:
                    System.out.println("\n==============================================");
                    System.out.println("  O FIAP Bank agradece sua preferência!");
                    System.out.println("==============================================");
                    break;
                default:
                    System.out.println("✘ Opção inválida! Escolha entre 1 e 5.");
                    break;
            }
        }
    }
    public void exibirSaldo() {
        System.out.println("\n--- Consulta de Saldo ---");
        System.out.println("Saldo atual: " + contaService.obterSaldo());
    }
    public void realizarDeposito() {
        System.out.println("\n--- Depósito ---");
        System.out.print("Informe o valor a depositar: R$ ");
        if (scanner.hasNextDouble()) {
            Double valorInformado = scanner.nextDouble();
            scanner.nextLine();
            try {
                Dinheiro valorDeposito = new Dinheiro(valorInformado.toString());
                contaService.realizarDeposito(valorDeposito);
                System.out.println("✔ Depósito de " + valorDeposito + " realizado com sucesso!");
                System.out.println("  Novo saldo: " + contaService.obterSaldo());
            } catch (IllegalArgumentException e) {
                System.out.println("✘ " + e.getMessage());
            }
        } else {
            System.out.println("✘ Valor inválido! Informe um número válido.");
            scanner.nextLine();
        }
    }
    public void realizarSaque() {
        System.out.println("\n--- Saque ---");
        System.out.print("Informe o valor a sacar: R$ ");
        if (scanner.hasNextDouble()) {
            Double valorInformado = scanner.nextDouble();
            scanner.nextLine();
            try {
                Dinheiro valorSaque = new Dinheiro(valorInformado.toString());
                contaService.realizarSaque(valorSaque);
                System.out.println("✔ Saque de " + valorSaque + " realizado com sucesso!");
                System.out.println("  Novo saldo: " + contaService.obterSaldo());
            } catch (IllegalArgumentException e) {
                System.out.println("✘ " + e.getMessage());
            }
        } else {
            System.out.println("✘ Valor inválido! Informe um número válido.");
            scanner.nextLine();
        }
    }
    public void exibirMovimentacoes() {
        System.out.println("\n--- Histórico de Movimentações ---");
        List<Movimentacao> movimentacoes = contaService.obterMovimentacoes();
        if (movimentacoes.isEmpty()) {
            System.out.println("Nenhuma movimentação registrada.");
            return;
        }
        System.out.printf("%-22s %-12s %s%n", "Data/Hora", "Tipo", "Valor");
        System.out.println("--------------------------------------------------");
        for (Movimentacao movimentacao : movimentacoes) {
            System.out.printf("%-22s %-12s %s%n",
                    movimentacao.getDataHora().format(FORMATADOR_DATA_HORA),
                    movimentacao.getTipo(),
                    movimentacao.getValor());
        }
    }
    public void fecharScanner() {
        scanner.close();
    }
}
