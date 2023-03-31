package com.attornatus.dto.response;

import com.attornatus.model.Endereco;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
@Relation(collectionRelation = "enderecos", itemRelation = "endereco")
public class EnderecoResponse extends RepresentationModel<PessoaResponse> {

    private Long id;
    private String logradouro;
    private String cep;
    private Integer numero;
    private String cidade;

    public static EnderecoResponse createByEntity(Endereco entity){
        return EnderecoResponse.builder()
                .id(entity.getId())
                .logradouro(entity.getLogradouro())
                .cep(entity.getCep())
                .numero(entity.getNumero())
                .cidade(entity.getCidade()).build();
    }
}
