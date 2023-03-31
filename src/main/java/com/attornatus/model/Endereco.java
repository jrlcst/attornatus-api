package com.attornatus.model;

import com.attornatus.dto.request.EnderecoRequest;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "endereco")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "logradouro")
    private String logradouro;

    @Column(name = "cep")
    private String cep;

    @Column(name = "numero")
    private Integer numero;

    @Column(name = "cidade")
    private String cidade;

    public static Endereco createById(Long id){
        return Endereco.builder().id(id).build();
    }

    public static Endereco createByRequest(EnderecoRequest request){
        return Endereco.builder()
                .id(request.getId())
                .logradouro(request.getLogradouro())
                .cep(request.getCep())
                .numero(request.getNumero())
                .cidade(request.getCidade()).build();
    }
}
