package com.attornatus.service;

import com.attornatus.assembler.PessoaAssembler;
import com.attornatus.dto.request.PessoaRequest;
import com.attornatus.dto.response.PessoaResponse;
import com.attornatus.exception.CustomNotFoundException;
import com.attornatus.model.Pessoa;
import com.attornatus.repository.PessoaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PessoaService {

    private final PessoaRepository repository;

    private final PessoaAssembler pessoaAssembler;

    private final PagedResourcesAssembler<Pessoa> pagedResourcesAssembler;

    public PagedModel<PessoaResponse> findAllOrFindByParams(PessoaRequest request, Pageable pageable) {
        var matcher = ExampleMatcher.matching()
                .withMatcher("nome", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withIgnoreNullValues().withIgnoreCase();

        return pagedResourcesAssembler.toModel(
                repository.findAll(Example.of(Pessoa.createByRequest(request), matcher), pageable),
                pessoaAssembler);
    }

    public PessoaResponse findById(Long id) {
        return PessoaResponse.createByEntity(repository.findById(id).orElseThrow(() -> new CustomNotFoundException("A Pessoa com o id: " + id + " não existe")));
    }

    public PessoaResponse create(PessoaRequest request) {
        return PessoaResponse.createByEntity(repository.save(Pessoa.createByRequest(request)));
    }

    public PessoaResponse update(PessoaRequest request) {
        findById(request.getId());
        return create(request);
    }

    public void delete(Long id) {
        findById(id);
        repository.deleteById(id);
    }
}
