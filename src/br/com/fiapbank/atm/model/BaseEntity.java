package br.com.fiapbank.atm.model;
import java.time.LocalDate;
import java.util.UUID;
public abstract class BaseEntity {
    private UUID id;
    private LocalDate dataCriacao;
    public BaseEntity() {
        this.id = UUID.randomUUID();
        this.dataCriacao = LocalDate.now();
    }
    public UUID getId() {
        return id;
    }
    public LocalDate getDataCriacao() {
        return dataCriacao;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        BaseEntity other = (BaseEntity) obj;
        return id != null && id.equals(other.id);
    }
    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
