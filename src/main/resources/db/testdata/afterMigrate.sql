set foreign_key_checks = 0;

delete from aluno;
delete from instrutor;
delete from instrutor_veiculo;
delete from veiculo;

set foreign_key_checks = 1;

alter table aluno auto_increment = 1;
alter table instrutor auto_increment = 1;
alter table veiculo auto_increment = 1;

insert into instrutor (id, nome, email, cpf, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro)
values (1, 'Juanilson', 'juanilson@email.com', '123.456.789-10', '65073310', 'Rua das Maças', '14', 'B', 'Estrela');
insert into instrutor (id, nome, email, cpf, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro)
values (2, 'Magrão', 'magrao@email.com', '123.456.789-11', '65073310', 'Rua das Maças', '14', 'B', 'Estrela');
insert into instrutor (id, nome, email, cpf, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro)
values (3, 'Hercules', 'hercules@email.com', '123.456.789-12', '65073310', 'Rua das Maças', '14', 'B', 'Estrela');

insert into veiculo (id, placa, cor, modelo, ano, tipo_veiculo) values (1, '1234567', 'preto', 'VW UP', 2014, 'CARRO');
insert into veiculo (id, placa, cor, modelo, ano, tipo_veiculo) values (2, '1234568', 'laranja', 'Onix', 2014, 'CARRO');
insert into veiculo (id, placa, cor, modelo, ano, tipo_veiculo) values (3, '1234569', 'azul', 'CG 150', 2012, 'MOTO');

insert into aluno (id, nome, matricula, email, veiculo_id, instrutor_id) values (1, 'Doquinha', '2023111111', 'doquinha@email.com', 1, 2);
insert into aluno (id, nome, matricula, email, veiculo_id, instrutor_id) values (2, 'Coxinha', '2023222222', 'coxinha@email.com', 2, 3);
insert into aluno (id, nome, matricula, email, veiculo_id, instrutor_id) values (3, 'Rudeus', '2023333333', 'rudeus@email.com', 3, 2);

insert into instrutor_veiculo (instrutor_id, veiculo_id) values (2, 1);
insert into instrutor_veiculo (instrutor_id, veiculo_id) values (2, 3);
insert into instrutor_veiculo (instrutor_id, veiculo_id) values (3, 2);