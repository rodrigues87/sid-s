package com.sids.services;


import com.sids.dtos.TipoSanguineoDto;
import com.sids.models.Pessoa;
import com.sids.models.TipoSanguineo;
import com.sids.repositorys.TipoSanguineoRepository;
import com.sids.tools.Constantes;
import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class TipoSanguineoService {

    @Autowired
    TipoSanguineoRepository tipoSanguineoRepository;


    public List<TipoSanguineo> findAll() {

        return tipoSanguineoRepository.findAll();
    }

    public TipoSanguineo findByNome(String nome) {

        return tipoSanguineoRepository.findByNomeIgnoreCase(nome);
    }

    public TipoSanguineo findById(Long id) throws ObjectNotFoundException {


        return tipoSanguineoRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(Constantes.TIPO_SANGUINEO_NAO_ENCONTRADO));

    }

    public void deleteById(Long id) throws ObjectNotFoundException {

        this.findById(id);

        tipoSanguineoRepository.deleteById(id);
    }

    public Object save(TipoSanguineoDto tipoSanguineoDto) throws ObjectNotFoundException {

        if(tipoSanguineoRepository.findByNomeIgnoreCase(tipoSanguineoDto.getNome()) != null){
            throw new ObjectNotFoundException(Constantes.TIPO_SANGUINEO_NOME_DUPLICADO);
        }

        TipoSanguineo tipoSanguineo = new TipoSanguineo();

        BeanUtils.copyProperties(tipoSanguineoDto, tipoSanguineo);

        return tipoSanguineoRepository.save(tipoSanguineo);
    }

    public Object update(Long id, TipoSanguineoDto tipoSanguineoDto) throws ObjectNotFoundException {

        TipoSanguineo tipoSanguineo = this.findById(id);

        BeanUtils.copyProperties(tipoSanguineoDto,tipoSanguineo);

        return tipoSanguineoRepository.save(tipoSanguineo);
    }


    public TipoSanguineo vincularTipoSanguineoDoador(Long idTipoSanguineo, Long idTipoSanguineoAVincular) throws ObjectNotFoundException {

        TipoSanguineo tipoSanguineo = this.findById(idTipoSanguineo);

        TipoSanguineo tipoSanguineoAVincular = this.findById(idTipoSanguineoAVincular);

        tipoSanguineo.getTiposSanguineosDoadores().add(tipoSanguineoAVincular);

        return tipoSanguineoRepository.save(tipoSanguineo);

    }

    public TipoSanguineo vincularTipoSanguineoRecebedor(Long idTipoSanguineo, Long idTipoSanguineoAVincular) throws ObjectNotFoundException {

        TipoSanguineo tipoSanguineo = this.findById(idTipoSanguineo);

        TipoSanguineo tipoSanguineoAVincular = this.findById(idTipoSanguineoAVincular);

        tipoSanguineo.getTiposSanguineosRecebedores().add(tipoSanguineoAVincular);

        return tipoSanguineoRepository.save(tipoSanguineo);

    }

    public TipoSanguineo desvincularTipoSanguineoDoador(Long idTipoSanguineo, Long idTipoSanguineoADesvincular) throws ObjectNotFoundException {

        TipoSanguineo tipoSanguineo = this.findById(idTipoSanguineo);

        TipoSanguineo tipoSanguineoADesvincular = this.findById(idTipoSanguineoADesvincular);

        tipoSanguineo.getTiposSanguineosDoadores().remove(tipoSanguineoADesvincular);

        return tipoSanguineoRepository.save(tipoSanguineo);

    }

    public TipoSanguineo desvincularTipoSanguineoRecebedor(Long idTipoSanguineo, Long idTipoSanguineoADesvincular) throws ObjectNotFoundException {

        TipoSanguineo tipoSanguineo = this.findById(idTipoSanguineo);

        TipoSanguineo tipoSanguineoADesvincular = this.findById(idTipoSanguineoADesvincular);

        tipoSanguineo.getTiposSanguineosRecebedores().remove(tipoSanguineoADesvincular);

        return tipoSanguineoRepository.save(tipoSanguineo);

    }


}
