create table endereco_pessoa(
    endereco_id bigint auto_increment NOT NULL,
    pessoa_id bigint auto_increment NOT NULL,
    is_principal bit
);

alter table endereco_pessoa ADD CONSTRAINT fk_endereco_id foreign key (endereco_id) references endereco(id);
alter table endereco_pessoa ADD CONSTRAINT fk_pessoa_id foreign key (pessoa_id) references pessoa(id);


