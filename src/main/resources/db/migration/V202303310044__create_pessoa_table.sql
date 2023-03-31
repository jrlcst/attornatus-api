create table pessoa(
    id bigint auto_increment NOT NULL,
    nome varchar(500),
    data_nascimento varchar(50),
    endereco_principal bigint,

    CONSTRAINT pk_pessoa_id PRIMARY KEY (id)
);

alter table pessoa ADD CONSTRAINT fk_principal_endereco_id foreign key (endereco_principal) references endereco(id);

