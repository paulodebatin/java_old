create table produto (
	id SERIAL,
    nome varchar(255) not null,
    quantidade bigint,
    preco decimal(10,2),
    primary key (id)
);