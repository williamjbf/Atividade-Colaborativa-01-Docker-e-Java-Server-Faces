create table if NOT EXISTS editora(
                                      codigo serial primary key,
                                      localDeOrigem varchar(255) not null,
    nomeFantasia varchar(255) not null
    );
create table if NOT EXISTS livro(
                                    id serial primary key,
                                    titulo varchar(255) not null,
    dataDeLancamento date not null,
    idEditora int,
    constraint fk_editora foreign key(idEditora) references editora(codigo) on delete CASCADE on update CASCADE
    );