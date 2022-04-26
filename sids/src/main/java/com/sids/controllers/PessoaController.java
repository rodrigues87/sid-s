package com.sids.controllers;


import com.sids.dtos.PessoaDto;
import com.sids.models.Pessoa;
import com.sids.services.PessoaService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/pessoas")
@CrossOrigin("http://localhost:4200/")
public class PessoaController {

    @Autowired
    PessoaService pessoaService;

    @GetMapping
    public ResponseEntity<Object> findAll(){

        return new ResponseEntity<>(pessoaService.findAll(), HttpStatus.OK);

    }

    @GetMapping("/countAll")
    public ResponseEntity<Object> countAll(){

        return new ResponseEntity<>(pessoaService.countAll(), HttpStatus.OK);

    }

    @GetMapping("/quantidadeDoadoresAptos")
    public ResponseEntity<Object> quantidadeDoadoresAptos(){

        return new ResponseEntity<>(pessoaService.quantidadeDoadoresAptos(), HttpStatus.OK);

    }





    @PostMapping
    @SneakyThrows
    public ResponseEntity<Object> save(@RequestBody PessoaDto pessoaDto){

        return new ResponseEntity<>(pessoaService.save(pessoaDto), HttpStatus.CREATED);

    }

    @PostMapping("/save-all")
    @SneakyThrows
    public ResponseEntity<Object> saveAll(@RequestBody List<PessoaDto> pessoaDtos){

        return new ResponseEntity<>(pessoaService.saveAll(pessoaDtos), HttpStatus.CREATED);

    }


    @GetMapping("/{idPessoa}")
    @SneakyThrows
    public ResponseEntity<Object> getById(@PathVariable(value = "idPessoa") Long idPessoa) {

        return new ResponseEntity<>(pessoaService.findById(idPessoa), HttpStatus.OK);

    }

    @PutMapping("/{idPessoa}")
    @SneakyThrows
    public ResponseEntity<Object> update(@PathVariable(value = "idPessoa") Long idPessoa, @RequestBody PessoaDto pessoaDto){

        return new ResponseEntity<>(pessoaService.update(idPessoa, pessoaDto), HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    @SneakyThrows
    public ResponseEntity<Object> delete(@PathVariable(value = "id") Long id){

        pessoaService.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/tipo-sanguineo/{idTipoSanguineo}")
    @SneakyThrows
    public ResponseEntity<Object> findAllPessoasByTipoSanguineo(
            @PathVariable(value = "idTipoSanguineo") Long idTipoSanguineo){


        return new ResponseEntity<>(pessoaService.findAllPessoasByTipoSanguineo(idTipoSanguineo), HttpStatus.OK);

    }

    @GetMapping("/quantidade-candidatos-por-estado")
    public ResponseEntity<Object> getQuantidadeCandidatosPorEstado(){

        return new ResponseEntity<>(pessoaService.getQuantidadeCandidatosPorEstado(), HttpStatus.OK);
    }

    @GetMapping("/imc-medio-por-faixa-etaria")
    public ResponseEntity<Object> getImcMedioPorFaixaEtaria(){

        return  new ResponseEntity<>(pessoaService.getImcMedioPorFaixaEtaria(), HttpStatus.OK);
    }

    @GetMapping("/persentual-obesos-homens-mulheres")
    public ResponseEntity<Object> getPersentualObesosBySexo(){

        return  new ResponseEntity<>(pessoaService.getPersentualObesosBySexo(), HttpStatus.OK);
    }

    @GetMapping("/media-idade-tipo-sanguineo")
    public ResponseEntity<Object> getMediaIdadeByTipoSanguineo(){

        return  new ResponseEntity<>(pessoaService.getMediaIdadeByTipoSanguineo(), HttpStatus.OK);
    }

    @GetMapping("/get-quantidade-possiveis-doadores")
    public ResponseEntity<Object> getQuantidadePossiveisDoadores(){

        return  new ResponseEntity<>(pessoaService.getQuantidadePossiveisDoadores(), HttpStatus.OK);
    }

    @GetMapping("/get-quantidade-possiveis-doadores-push")
    public ResponseEntity<Object> getQuantidadePossiveisDoadoresPush(){

        return  new ResponseEntity<>(pessoaService.getQuantidadePossiveisDoadores(), HttpStatus.OK);
    }

    @PostMapping("/uploadFileCandidatos")
    public ResponseEntity<Object> uploadFile(@RequestParam("file") MultipartFile file) throws Exception {

        List<PessoaDto> pessoaDtoList = pessoaService.uploadFile(file);

        return new ResponseEntity<>( pessoaService.saveAll(pessoaDtoList), HttpStatus.OK);
    }

}
