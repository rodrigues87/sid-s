package com.sids.services;


import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.type.*;
import com.sids.dtos.*;
import com.sids.jpaInterfaces.*;
import com.sids.models.*;
import com.sids.repositorys.*;
import com.sids.tools.*;
import javassist.tools.rmi.*;
import org.springframework.beans.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.messaging.simp.*;
import org.springframework.stereotype.*;
import org.springframework.web.multipart.*;

import java.io.*;
import java.util.*;

@Service
public class PessoaService {

    @Autowired
    private SimpMessagingTemplate template;

    @Autowired
    PessoaRepository pessoaRepository;

    @Autowired
    TipoSanguineoService tipoSanguineoService;

    public List<Pessoa> findAll() {

        return pessoaRepository.findAll();
    }

    public Pessoa findById(Long id) throws ObjectNotFoundException {


        return pessoaRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(Constantes.TIPO_SANGUINEO_NAO_ENCONTRADO + id));

    }

    public void deleteById(Long id) throws ObjectNotFoundException {

        this.findById(id);

        pessoaRepository.deleteById(id);
    }

    public Object save(PessoaDto pessoaDto) throws ObjectNotFoundException {

        Pessoa pessoaBanco = this.findByCpf(pessoaDto.getCpf());

        if(pessoaBanco != null){
            return this.update(pessoaBanco.getId(), pessoaDto);
        }


        Pessoa pessoa = new Pessoa();

        BeanUtils.copyProperties(pessoaDto, pessoa);

        TipoSanguineo tipoSanguineo = tipoSanguineoService.findByNome(pessoaDto.getTipoSanguineo());

        pessoa.setTipoSanguineo(tipoSanguineo);

        pessoa = pessoaRepository.save(pessoa);

        template.convertAndSend("/pessoa/save", "teste");

        return pessoa;
    }

    private Pessoa findByCpf(String cpf) {
        return pessoaRepository.findByCpf(cpf);
    }

    public Object update(Long id, PessoaDto pessoaDto) throws ObjectNotFoundException {

        Pessoa pessoa = this.findById(id);

        BeanUtils.copyProperties(pessoaDto, pessoa);

        return pessoaRepository.save(pessoa);
    }

    public Set<Pessoa> findAllPessoasByTipoSanguineo(Long idTipoSanguineo) throws ObjectNotFoundException {

        TipoSanguineo tipoSanguineo = tipoSanguineoService.findById(idTipoSanguineo);

        return tipoSanguineo.getPessoas();
    }

    public Object saveAll(List<PessoaDto> pessoaDtos) throws ObjectNotFoundException {

        for (PessoaDto pessoaDto : pessoaDtos) {

            this.save(pessoaDto);
        }

        return Constantes.PESSOAS_GRUPO_SALVO;
    }

    public Object getQuantidadeCandidatosPorEstado() {

        return pessoaRepository.getQuantidadeCandidatosPorEstado();
    }

    public List<PessoaImcMedioFaixaEtariaDto> getImcMedioPorFaixaEtaria() {

        List<PessoaImcMedioFaixaEtariaDto> pessoaImcMedioFaixaEtariaDtoList = new ArrayList<>();

        List<Pessoa> pessoas = pessoaRepository.findAll();

        List<PessoaImcDto> pessoaDtoList = this.populatePessoaDtoList(pessoas);

        HashMap<String, List<PessoaImcDto>> map = this.createHashMapByFaixaEtaria(pessoaDtoList);

        for (Map.Entry<String, List<PessoaImcDto>> peStringListEntry : map.entrySet()) {

            double soma = 0;
            int listSize = peStringListEntry.getValue().size();

            for (PessoaImcDto pessoaImcDto : peStringListEntry.getValue()) {
                soma = soma + pessoaImcDto.getImc();
            }

            PessoaImcMedioFaixaEtariaDto pessoaImcMedioFaixaEtariaDto = new PessoaImcMedioFaixaEtariaDto();

            pessoaImcMedioFaixaEtariaDto.setImcMedio(soma / listSize);

            pessoaImcMedioFaixaEtariaDto.setFaixaEtaria(peStringListEntry.getValue().get(0).getFaixaEtaria());

            pessoaImcMedioFaixaEtariaDtoList.add(pessoaImcMedioFaixaEtariaDto);

        }

        Collections.sort(pessoaImcMedioFaixaEtariaDtoList);


        return pessoaImcMedioFaixaEtariaDtoList;
    }

    private List<PessoaImcDto> populatePessoaDtoList(List<Pessoa> pessoas) {

        List<PessoaImcDto> pessoaDtoList = new ArrayList<>();

        for (Pessoa pessoa : pessoas) {

            PessoaImcDto pessoaImcDto = new PessoaImcDto(pessoa.getDataNasc().toString(), pessoa.getAltura(), pessoa.getPeso(), pessoa.getSexo());

            pessoaDtoList.add(pessoaImcDto);
        }

        return pessoaDtoList;
    }

    private HashMap<String, List<PessoaImcDto>> createHashMapByFaixaEtaria(List<PessoaImcDto> pessoaDtoList) {

        HashMap<String, List<PessoaImcDto>> map = new HashMap<>();

        for (PessoaImcDto pessoaImcDto : pessoaDtoList) {
            String key = pessoaImcDto.getFaixaEtaria();
            if (map.containsKey(key)) {
                List<PessoaImcDto> list = map.get(key);
                list.add(pessoaImcDto);

            } else {
                List<PessoaImcDto> list = new ArrayList<>();
                list.add(pessoaImcDto);
                map.put(key, list);
            }
        }
        return map;
    }

    public List<PercentualDeObesosBySexo> getPersentualObesosBySexo() {

        return pessoaRepository.getPersentualObesosBySexo();
    }

    public List<MediaIdadeByTipoSanguineo> getMediaIdadeByTipoSanguineo() {
        return pessoaRepository.getMediaIdadeByTipoSanguineo();
    }


    public List<QuantidadeDoadoresPorTipoSanguineo> getQuantidadePossiveisDoadores() {
        return pessoaRepository.findQuantidadeDoadoresPorTipoSanguineo();
    }

    public Long countAll(){

        return pessoaRepository.count();
    }

    public int quantidadeDoadoresAptos(){
        return pessoaRepository.quantidadeDoadoresAptos();
    }

    public List<PessoaDto> uploadFile(MultipartFile file) throws IOException {

        String content = new String(file.getBytes());

        ObjectMapper mapper = new ObjectMapper();

        CollectionType javaType = mapper.getTypeFactory()
                .constructCollectionType(List.class, PessoaDto.class);

        return mapper.readValue(content, javaType);
    }
}
