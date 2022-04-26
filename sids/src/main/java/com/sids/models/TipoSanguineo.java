package com.sids.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class TipoSanguineo extends AbstractEntity {

    private String nome;

    @ManyToMany()
    @ToString.Exclude
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinTable(joinColumns = @JoinColumn(name = "tipo_sanguineo_id"), inverseJoinColumns = @JoinColumn(name = "tipo_sanguineo_doador_id"))
    private Set<TipoSanguineo> tiposSanguineosDoadores;

    @ManyToMany()
    @ToString.Exclude
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinTable(joinColumns = @JoinColumn(name = "tipo_sanguineo_id"), inverseJoinColumns = @JoinColumn(name = "tipo_sanguineo_recebedor_id"))
    private Set<TipoSanguineo> tiposSanguineosRecebedores;


    @OneToMany(mappedBy = "tipoSanguineo")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ToString.Exclude
    private Set<Pessoa> pessoas;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        TipoSanguineo that = (TipoSanguineo) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
