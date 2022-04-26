package com.sids.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;


@MappedSuperclass
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public abstract class AbstractEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    @Temporal(TemporalType.DATE)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Date dataCriacao;

    @Temporal(TemporalType.DATE)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Date dataModificacao;

    @PrePersist
    public void basicPrePersist() {
        this.dataCriacao = new Date();
    }
    @PreUpdate
    public void basicPreUpdate() {
        this.dataModificacao = new Date();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        AbstractEntity that = (AbstractEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
