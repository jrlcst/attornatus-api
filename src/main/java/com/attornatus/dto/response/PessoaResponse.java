package com.attornatus.dto.response;

import com.attornatus.model.Pessoa;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
@Relation(collectionRelation = "pessoas", itemRelation = "pessoa")
public class PessoaResponse extends RepresentationModel<PessoaResponse> {
    private Long id;
    private String nome;
    private String dataNascimento;
    private List<EnderecoResponse> endereco;
    private EnderecoResponse enderecoPrincipal;

    public static PessoaResponse createByEntity(Pessoa entity){
        return PessoaResponse.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .dataNascimento(entity.getDataNascimento())
                .endereco(entity.getEnderecos().stream().map(EnderecoResponse::createByEntity).toList())
                .enderecoPrincipal(EnderecoResponse.createByEntity(entity.getEnderecoPrincipal()))
                .build();
    }
}
