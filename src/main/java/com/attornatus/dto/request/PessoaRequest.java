package com.attornatus.dto.request;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PessoaRequest {
    private Long id;
    private String nome;
    private String dataNascimento;
    private List<Long> enderecoId;
    private Long enderecoPrincipal;
}
