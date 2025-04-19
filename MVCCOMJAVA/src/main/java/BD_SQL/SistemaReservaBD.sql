CREATE TABLE Equipamento(
id_equipamento INT PRIMARY KEY NOT NULL,
nome_produto VARCHAR(50),
quantidade_disponivel INT
);
CREATE TABLE Espaco(
id_espaco INT PRIMARY KEY NOT NULL,
nome_espaco VARCHAR(50),
status VARCHAR(1)	
);
CREATE TABLE Funcionario(
id_funcionario INT PRIMARY KEY NOT NULL,
nome_funcionario varchar(50),
cargo_funcionario varchar(50)	
);
CREATE TABLE Reserva_equipamento(
id_reserva INT PRIMARY KEY NOT NULL,
dt_reserva VARCHAR(50),
id_funcionario INT NOT NULL REFERENCES Funcionario(id_funcionario),
id_equipamento INT NOT NULL REFERENCES Equipamento(id_equipamento)	
);
CREATE TABLE Reserva_espaco(
id_reserva INT PRIMARY KEY NOT NULL,
dt_reserva VARCHAR(50),
id_funcionario INT NOT NULL REFERENCES Funcionario(id_funcionario),
id_espaco INT NOT NULL REFERENCES Espaco(id_espaco)
);