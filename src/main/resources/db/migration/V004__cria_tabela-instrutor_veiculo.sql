create table instrutor_veiculo (
    instrutor_id BIGINT,
    veiculo_id BIGINT,

    PRIMARY KEY (instrutor_id, veiculo_id),

    FOREIGN KEY (instrutor_id) REFERENCES instrutor(id),
    FOREIGN KEY (veiculo_id) REFERENCES veiculo(id)
);