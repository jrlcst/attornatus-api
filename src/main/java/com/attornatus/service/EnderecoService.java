package com.attornatus.service;

import com.attornatus.assembler.EnderecoAssembler;
import com.attornatus.dto.request.EnderecoRequest;
import com.attornatus.dto.response.EnderecoResponse;
import com.attornatus.exception.CustomNotFoundException;
import com.attornatus.model.Endereco;
import com.attornatus.repository.EnderecoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EnderecoService {

    private final EnderecoRepository repository;

    private final EnderecoAssembler enderecoAssembler;

    private final PagedResourcesAssembler<Endereco> pagedResourcesAssembler;

    public PagedModel<EnderecoResponse> findAllOrFindByParams(EnderecoRequest request, Pageable pageable) {

        var matcher = ExampleMatcher.matching()
                .withMatcher("logradouro", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withIgnoreNullValues().withIgnoreCase();

        return pagedResourcesAssembler.toModel(
                repository.findAll(Example.of(Endereco.createByRequest(request), matcher), pageable),
                enderecoAssembler);
    }

    public EnderecoResponse findById(Long id) {
        return EnderecoResponse.createByEntity(repository.findById(id).orElseThrow(() -> new CustomNotFoundException("O Endereco com o id: " + id + " não existe")));
    }

    public EnderecoResponse create(EnderecoRequest request) {
        return EnderecoResponse.createByEntity(repository.save(Endereco.createByRequest(request)));
    }

    public EnderecoResponse update(EnderecoRequest request) {
        findById(request.getId());
        return create(request);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
