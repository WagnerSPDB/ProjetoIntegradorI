-- a linha abaixo cria um banco de dados
create database dbinfox;

-- a linha abaixo escolhe o banco de dados a ser utilizado
use dbinfox;

-- o bloco de instruções abaixo cria uma tabela
create table tbusuarios(
iduser int primary key,
usuario varchar(50) not null,
fone varchar(15),
login varchar(15) not null unique,
senha varchar(15) not null
);

-- o comando abaixo descreve a tabela
describe tbusuarios;

-- a linha abaixo insere dados na tabela
-- create
insert into tbusuarios(iduser,usuario,fone,login,senha)
values(3,'wagner','9999-9999','wagner','lala');
insert into tbusuarios(iduser,usuario,fone,login,senha)
values(4,'cibelle','9999-9999','bebel','lala');
insert into tbusuarios(iduser,usuario,fone,login,senha)
values(5,'eric','9999-9999','eric','wagner');

-- a linha abaixo exibe os dados da tabela (CRUD)
-- read -> select
select * from tbusuarios; 

-- a linha abaixo modifica dados na tabela
-- update -> update
update tbusuarios set fone='88 997420027' where iduser=3;

-- a linha abaixo apaga um registro da tabela
-- delete -> delete
delete from tbusuarios where iduser = 3;





