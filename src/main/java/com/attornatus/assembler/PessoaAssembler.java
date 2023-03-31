package com.attornatus.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.attornatus.controller.PessoaController;
import com.attornatus.dto.response.PessoaResponse;
import com.attornatus.exception.CustomNotFoundException;
import com.attornatus.model.Pessoa;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class PessoaAssembler extends RepresentationModelAssemblerSupport<Pessoa, PessoaResponse> {

    public PessoaAssembler() {
        super(PessoaController.class, PessoaResponse.class);
    }

    @Override
    public PessoaResponse toModel(Pessoa entity) {
        PessoaResponse response = PessoaResponse.createByEntity(entity);

        try {
            response.add(linkTo(methodOn(PessoaController.class).findById(entity.getId())).withSelfRel());
        }  catch (CustomNotFoundException e) {
            throw (RuntimeException) e.getCause();
        }

        return response;
    }

}