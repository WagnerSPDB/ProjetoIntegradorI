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

-- criando outra tabela
create table tbclientes(
idcli int primary key auto_increment,
nomecli varchar(50) not null,
endcli varchar(100),
fonecli varchar(50) not null,
emailcli varchar(50)
);

describe tbclientes;

-- inserindo elementos na tabela
insert into tbclientes(nomecli,endcli,fonecli,emailcli)
values('Larissa','Rua dos Bobos, 0','9999-9999','lala@gmail.com');
insert into tbclientes(nomecli,endcli,fonecli,emailcli)
values('Wagner','Rua dos Bobos, 0','9999-9999','lala@gmail.com');

select * from tbclientes;

use dbinfox;

create table tbos(
os int primary key auto_increment,
data_os timestamp default current_timestamp, -- gera automaticamente data e hora do servidor
equipamento varchar(150) not null,
defeito varchar(150) not null,
servico varchar(150),
tecnico varchar(30),
valor decimal(10,2), -- resultado vai ter duas casas decimais
idcli int not null, -- busca as informacoes de cadastro dotbclientes
foreign key(idcli) references tbclientes(idcli)
);

describe tbos;

insert into tbos(equipamento,defeito,servico,tecnico,valor,idcli )
values ('notebook','não liga','troca da fonte','Zé', 90.99,2);
insert into tbos(equipamento,defeito,servico,tecnico,valor,idcli )
values ('computador','sem som','troca de saída de som','Miguel', 90.99,1);

select * from tbos;

-- o código abaixo traz informações de duas tabelas
select
O.od,equipamento,defeito,servico,valor,
C.nomecli,fonecli
from tbos as O
inner join tbclientes as C
on (O.idcli = C.idcli);




