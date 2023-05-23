create table aluno (
    id bigint not null auto_increment,
    nome varchar(60) not null,
    matricula varchar(20),
    email varchar(255) not null,
    veiculo_id bigint,

    primary key (id)
);

alter table aluno add constraint fk_aluno_veiculo foreign key (veiculo_id) references veiculo (id);