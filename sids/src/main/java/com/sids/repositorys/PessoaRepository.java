package com.sids.repositorys;

import com.sids.jpaInterfaces.MediaIdadeByTipoSanguineo;
import com.sids.jpaInterfaces.PercentualDeObesosBySexo;
import com.sids.jpaInterfaces.QuantidadeCandidatosPorEstado;
import com.sids.jpaInterfaces.QuantidadeDoadoresPorTipoSanguineo;
import com.sids.models.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    Pessoa findByCpf(String cpf);

    @Query(value = "SELECT estado, COUNT(estado) as quantidade FROM pessoa GROUP BY estado", nativeQuery = true)
    List<QuantidadeCandidatosPorEstado> getQuantidadeCandidatosPorEstado();

    @Query(value = "SELECT  " +
            "sexo," +
            "COUNT(case when peso/(altura*altura) > 30 then 1 end)/ COUNT(id)*100 as porcentagemDeObesos " +
            "FROM pessoa " +
            "group by sexo", nativeQuery = true)
    List<PercentualDeObesosBySexo> getPersentualObesosBySexo();

    @Query(value = "SELECT " +
            "ts.nome as tipoSanguineo," +
            "SUM(TIMESTAMPDIFF(year, data_nasc, now())) / count(pessoa.id) as mediaDeIdade " +
            "from pessoa " +
            "inner join tipo_sanguineo ts on pessoa.tipo_sanguineo_id = ts.id " +
            "group by tipo_sanguineo_id", nativeQuery = true)
    List<MediaIdadeByTipoSanguineo> getMediaIdadeByTipoSanguineo();

    @Query(value = "select " +
            "       t.nome as tipoSanguineo, " +
            " " +
            "       (select " +
            "            sum( " +
            "                    (select " +
            "                         COUNT(p.tipo_sanguineo_id) " +
            "                     from pessoa p where TIMESTAMPDIFF(year, data_nasc, now()) BETWEEN 16 AND 69 " +
            "                                     AND peso > 50 and p.tipo_sanguineo_id= tsd.tipo_sanguineo_doador_id) " +
            "                ) " +
            " " +
            "        from tipo_sanguineo_tipos_sanguineos_doadores tsd where tipo_sanguineo_id=t.id) as quantidadeDeDoadores " +
            " " +
            "from " +
            "     tipo_sanguineo t", nativeQuery = true)
    List<QuantidadeDoadoresPorTipoSanguineo> findQuantidadeDoadoresPorTipoSanguineo();

    @Query(value = "select\n" +
            "    COUNT(p.tipo_sanguineo_id)\n" +
            "from pessoa p where TIMESTAMPDIFF(year, data_nasc, now()) BETWEEN 16 AND 69\n" +
            "                AND peso > 50", nativeQuery = true)
    int quantidadeDoadoresAptos();

    List<Pessoa> findAllByOrderByDataNasc();
}
