package br.com.fiapbank.atm.model;
public class ContaAcesso {
    public static final Integer MAXIMO_TENTATIVAS = 3;
    private String senha;
    private Integer tentativas;
    private Boolean bloqueado;
    public ContaAcesso(String senha) {
        this.senha = senha;
        this.tentativas = 0;
        this.bloqueado = false;
    }
    public Boolean validarSenha(String senhaInformada) {
        if (bloqueado) {
            return false;
        }
        if (senha.equals(senhaInformada)) {
            resetarTentativas();
            return true;
        }
        tentativas++;
        if (tentativas >= MAXIMO_TENTATIVAS) {
            bloqueado = true;
        }
        return false;
    }
    public Boolean isBloqueado() {
        return bloqueado;
    }
    public void resetarTentativas() {
        this.tentativas = 0;
        this.bloqueado = false;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ContaAcesso other = (ContaAcesso) obj;
        return senha != null && senha.equals(other.senha);
    }
    @Override
    public int hashCode() {
        return senha != null ? senha.hashCode() : 0;
    }
}
