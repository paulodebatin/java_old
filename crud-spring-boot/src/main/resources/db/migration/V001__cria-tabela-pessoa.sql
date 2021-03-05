create table pessoa (
	id SERIAL,
    nome varchar(255) not null,
    endereco varchar(255),
    numero varchar(20),
    bairro varchar(255),
    cidade varchar(255),
    uf char(2),
    receber_noticias boolean,
    fumante boolean,
    cep varchar(8),
    data_nascimento date,
    primary key (id)
);