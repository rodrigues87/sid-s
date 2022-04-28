package com.sids.controllers;


import com.sids.dtos.*;
import com.sids.models.*;
import com.sids.services.*;
import lombok.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tipos-sanguineos")
public class TipoSanguineoController {

    @Autowired
    TipoSanguineoService tipoSanguineoService;

    @GetMapping
    public ResponseEntity<Object> findAll(){

        return new ResponseEntity<>(tipoSanguineoService.findAll(), HttpStatus.OK);

    }

    @PostMapping
    @SneakyThrows
    public ResponseEntity<Object> save(@RequestBody TipoSanguineoDto tipoSanguineoDto){

        return new ResponseEntity<>(tipoSanguineoService.save(tipoSanguineoDto), HttpStatus.CREATED);

    }


    @GetMapping("/{idTipoSanguineo}")
    @SneakyThrows
    public ResponseEntity<Object> getById(@PathVariable(value = "idTipoSanguineo") Long idTipoSanguineo) {

        return new ResponseEntity<>(tipoSanguineoService.findById(idTipoSanguineo), HttpStatus.OK);

    }

    @PutMapping("/{idTipoSanguineo}")
    @SneakyThrows
    public ResponseEntity<Object> update(@PathVariable(value = "idTipoSanguineo") Long idTipoSanguineo, @RequestBody TipoSanguineoDto tipoSanguineoDto){

        return new ResponseEntity<>(tipoSanguineoService.update(idTipoSanguineo, tipoSanguineoDto), HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    @SneakyThrows
    public ResponseEntity<Object> delete(@PathVariable(value = "id") Long id){

        tipoSanguineoService.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/vincular-doador/{idTipoSanguineo}/{idTipoSanguineoDoador}")
    @SneakyThrows
    public ResponseEntity<Object> vincularDoador(@PathVariable(value = "idTipoSanguineo") Long idTipoSanguineo,
                                                 @PathVariable(value = "idTipoSanguineoDoador") Long idTipoSanguineoDoador){


        TipoSanguineo tipoSanguineo = tipoSanguineoService.vincularTipoSanguineoDoador(idTipoSanguineo, idTipoSanguineoDoador);


        return new ResponseEntity<>(tipoSanguineo, HttpStatus.OK);

    }


    @GetMapping("/vincular-recebedor/{idTipoSanguineo}/{idTipoSanguineoRecebedor}")
    @SneakyThrows
    public ResponseEntity<Object> vincularRecebedor(@PathVariable(value = "idTipoSanguineo") Long idTipoSanguineo,
                                                 @PathVariable(value = "idTipoSanguineoRecebedor") Long idTipoSanguineoRecebedor){


        TipoSanguineo tipoSanguineo = tipoSanguineoService.vincularTipoSanguineoRecebedor(idTipoSanguineo, idTipoSanguineoRecebedor);

        return new ResponseEntity<>(tipoSanguineo, HttpStatus.OK);

    }

    @GetMapping("/desvincular-doador/{idTipoSanguineo}/{idTipoSanguineoDoador}")
    @SneakyThrows
    public ResponseEntity<Object> desvincularDoador(@PathVariable(value = "idTipoSanguineo") Long idTipoSanguineo,
                                                    @PathVariable(value = "idTipoSanguineoDoador") Long idTipoSanguineoDoador){


        TipoSanguineo tipoSanguineo = tipoSanguineoService.desvincularTipoSanguineoDoador(idTipoSanguineo, idTipoSanguineoDoador);

        return new ResponseEntity<>(tipoSanguineo, HttpStatus.OK);

    }

    @GetMapping("/desvincular-recebedor/{idTipoSanguineo}/{idTipoSanguineoRecebedor}")
    @SneakyThrows
    public ResponseEntity<Object> desvincularRecebedor(@PathVariable(value = "idTipoSanguineo") Long idTipoSanguineo,
                                                    @PathVariable(value = "idTipoSanguineoRecebedor") Long idTipoSanguineoRecebedor){


        TipoSanguineo tipoSanguineo = tipoSanguineoService.desvincularTipoSanguineoRecebedor(idTipoSanguineo, idTipoSanguineoRecebedor);

        return new ResponseEntity<>(tipoSanguineo, HttpStatus.OK);

    }



}
