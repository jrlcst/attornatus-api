package com.attornatus.controller;

import com.attornatus.dto.request.PessoaRequest;
import com.attornatus.dto.response.PessoaResponse;
import com.attornatus.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    @Autowired
    private PessoaService service;

    @GetMapping
    public ResponseEntity<PagedModel<PessoaResponse>> findAllOrFindByParams(@RequestParam(value = "nome", required = false) String nome,
                                                                            @RequestParam(value = "dataNascimento", required = false) String dataNascimento,
                                                                            @RequestParam(value = "enderecoId", required = false) List<Long> enderecoId, Pageable pageable) {
        return ResponseEntity.ok(service.findAllOrFindByParams(PessoaRequest.builder()
                .nome(nome)
                .dataNascimento(dataNascimento)
                .enderecoId(enderecoId).build(), pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PessoaResponse> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<PessoaResponse> create(@RequestBody PessoaRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @PutMapping
    public ResponseEntity<PessoaResponse> update(@RequestBody PessoaRequest request) {
        return ResponseEntity.ok(service.update(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
