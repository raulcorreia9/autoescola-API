create table veiculo (
    id bigint not null auto_increment,
    placa varchar(8) not null,
    cor varchar(20) not null,
    modelo varchar(60) not null,
    ano int not null,
    tipo_veiculo varchar(20) not null,

    primary key (id)
);
