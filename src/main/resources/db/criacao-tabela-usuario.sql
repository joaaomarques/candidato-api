create table usuario(
	ID BIGINT AUTO_INCREMENT UNIQUE NOT NULL,
    USERNAME VARCHAR(50) NOT NULL,
    SENHA VARCHAR(100) NOT NULL,
    PRIMARY KEY(ID)
);