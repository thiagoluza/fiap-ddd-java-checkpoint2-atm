package br.com.fiapbank.atm.model;
public class Cliente extends BaseEntity {
    private String nomeCompleto;
    public Cliente(String nomeCompleto) {
        super();
        if (nomeCompleto == null || nomeCompleto.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome completo é obrigatório para cadastrar um cliente.");
        }
        this.nomeCompleto = nomeCompleto.trim();
    }
    public String obterPrimeiroNome() {
        Integer indiceEspaco = nomeCompleto.indexOf(" ");
        if (indiceEspaco == -1) {
            return nomeCompleto;
        }
        return nomeCompleto.substring(0, indiceEspaco);
    }
    public String getNomeCompleto() {
        return nomeCompleto;
    }
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
