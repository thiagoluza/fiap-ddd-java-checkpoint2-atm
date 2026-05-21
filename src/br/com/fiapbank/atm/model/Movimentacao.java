package br.com.fiapbank.atm.model;
import java.time.LocalDateTime;
public class Movimentacao {
    private LocalDateTime dataHora;
    private TipoMovimentacao tipo;
    private Dinheiro valor;
    public Movimentacao(LocalDateTime dataHora, Dinheiro valor, TipoMovimentacao tipo) {
        this.dataHora = dataHora;
        this.valor = valor;
        this.tipo = tipo;
    }
    public LocalDateTime getDataHora() {
        return dataHora;
    }
    public TipoMovimentacao getTipo() {
        return tipo;
    }
    public Dinheiro getValor() {
        return valor;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Movimentacao other = (Movimentacao) obj;
        return dataHora != null && dataHora.equals(other.dataHora)
                && tipo == other.tipo
                && valor != null && valor.equals(other.valor);
    }
    @Override
    public int hashCode() {
        int result = dataHora != null ? dataHora.hashCode() : 0;
        result = 31 * result + (tipo != null ? tipo.hashCode() : 0);
        result = 31 * result + (valor != null ? valor.hashCode() : 0);
        return result;
    }
}
