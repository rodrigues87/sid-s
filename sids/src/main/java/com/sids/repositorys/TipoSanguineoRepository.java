package com.sids.repositorys;

import com.sids.models.TipoSanguineo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoSanguineoRepository extends JpaRepository<TipoSanguineo, Long> {
    TipoSanguineo findByNomeIgnoreCase(String nome);
}
