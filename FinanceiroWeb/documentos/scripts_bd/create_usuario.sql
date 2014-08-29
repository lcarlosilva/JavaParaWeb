CREATE TABLE usuario(
	codigo INT(11) NOT NULL AUTO_INCREMENT
    ,nome VARCHAR(50) NOT NULL
    ,login VARCHAR(15) NOT NULL
    ,email VARCHAR(100) NOT NULL
    ,senha VARCHAR(10) NOT NULL
    ,nascimento DATE NOT NULL
    ,celular VARCHAR(25)
    ,idioma VARCHAR(5) NOT NULL
    ,ativo tinyint(1) NOT NULL
    ,PRIMARY KEY(codigo)
    ,UNIQUE KEY login (login)
)