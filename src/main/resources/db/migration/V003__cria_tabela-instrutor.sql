create table instrutor (
    id bigint not null auto_increment,
    nome varchar(60) not null,
    email varchar(255) not null,
    cpf varchar(14),

    endereco_numero VARCHAR(10),
    endereco_logradouro VARCHAR(255),
    endereco_complemento VARCHAR(10),
    endereco_bairro VARCHAR(255),
    endereco_cep VARCHAR(10),

    primary key (id)
);