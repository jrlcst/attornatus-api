package com.attornatus.model;
import com.attornatus.dto.request.PessoaRequest;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "pessoa")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "data_nascimento")
    private String dataNascimento;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "endereco_pessoa", joinColumns = @JoinColumn(name = "pessoa_id"),
            inverseJoinColumns = @JoinColumn(name = "endereco_id"))
    private List<Endereco> enderecos;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "endereco_principal")
    private Endereco enderecoPrincipal;

    public static Pessoa createByRequest(PessoaRequest request){
        return Pessoa.builder()
                .id(request.getId())
                .nome(request.getNome())
                .dataNascimento(request.getDataNascimento())
                .enderecos(request.getEnderecoId() != null ? request.getEnderecoId().stream().map(Endereco::createById).toList() : null)
                .enderecoPrincipal(Endereco.createById(request.getEnderecoPrincipal())).build();
    }
}
