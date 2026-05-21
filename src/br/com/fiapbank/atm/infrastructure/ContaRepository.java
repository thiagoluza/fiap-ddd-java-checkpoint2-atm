package br.com.fiapbank.atm.infrastructure;
import br.com.fiapbank.atm.model.Conta;
import java.util.ArrayList;
import java.util.List;
public class ContaRepository {
    private List<Conta> contas;
    public ContaRepository() {
        this.contas = new ArrayList<>();
    }
    public void salvar(Conta conta) {
        contas.add(conta);
    }
    public List<Conta> listarTodas() {
        return new ArrayList<>(contas);
    }
}
