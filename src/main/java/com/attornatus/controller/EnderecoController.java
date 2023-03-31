package com.attornatus.controller;

import com.attornatus.dto.request.EnderecoRequest;
import com.attornatus.dto.response.EnderecoResponse;
import com.attornatus.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

    @Autowired
    private EnderecoService service;

    @GetMapping
    public ResponseEntity<PagedModel<EnderecoResponse>> findAllOrFindByParams(@RequestParam(value = "logradouro", required = false) String logradouro,
                                                                              @RequestParam(value = "cep", required = false) String cep,
                                                                              @RequestParam(value = "numero", required = false) Integer numero,
                                                                              @RequestParam(value = "cidade", required = false) String cidade,
                                                                              Pageable pageable){
        return ResponseEntity.ok(service.findAllOrFindByParams(EnderecoRequest.builder()
                .logradouro(logradouro)
                .cep(cep)
                .numero(numero)
                .cidade(cidade).build(), pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnderecoResponse> findById(@PathVariable("id") Long id){
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<EnderecoResponse> create(@RequestBody EnderecoRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @PutMapping
    public ResponseEntity<EnderecoResponse> update(@RequestBody EnderecoRequest request){
        return ResponseEntity.ok(service.update(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
