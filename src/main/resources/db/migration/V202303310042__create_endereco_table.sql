create table endereco(
    id bigint auto_increment NOT NULL,
    logradouro varchar(500),
    cep varchar(30),
    numero int,
    cidade varchar(200),

    CONSTRAINT pk_endereco_id PRIMARY KEY (id)
)