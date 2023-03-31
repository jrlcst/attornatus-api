package com.attornatus.assembler;

import com.attornatus.controller.EnderecoController;
import com.attornatus.dto.response.EnderecoResponse;
import com.attornatus.exception.CustomNotFoundException;
import com.attornatus.model.Endereco;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class EnderecoAssembler extends RepresentationModelAssemblerSupport<Endereco, EnderecoResponse> {

    public EnderecoAssembler() {
        super(EnderecoController.class, EnderecoResponse.class);
    }

    @Override
    public EnderecoResponse toModel(Endereco entity) {
        EnderecoResponse response = EnderecoResponse.createByEntity(entity);

        try {
            response.add(linkTo(methodOn(EnderecoController.class).findById(entity.getId())).withSelfRel());
        }  catch (CustomNotFoundException e) {
            throw (RuntimeException) e.getCause();
        }

        return response;
    }

}