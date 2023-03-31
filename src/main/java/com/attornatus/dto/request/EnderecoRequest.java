package com.attornatus.dto.request;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnderecoRequest {

    private Long id;
    private String logradouro;
    private String cep;
    private Integer numero;
    private String cidade;
}
