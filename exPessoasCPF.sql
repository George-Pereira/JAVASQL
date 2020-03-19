CREATE DATABASE exPessoaCPF
GO
USE exPessoaCPF

CREATE TABLE pessoa
(
cpf			CHAR(11),
nome		VARCHAR(200)
PRIMARY KEY(cpf)
)

DECLARE @cod		INT,
		@desc		VARCHAR(100),
		@valor		DECIMAL(7,2)
SET @cod = 1
WHILE(@cod <=100)
BEGIN
	SET @desc = 'PRODUTO ' + CAST(@cod AS VARCHAR(3))
	IF(@cod % 2 = 0)
	BEGIN
		SET @valor = @cod *1.5
	END
	ELSE
	BEGIN
		SET @valor = @cod * 2.5
	END
	INSERT INTO produto VALUES (@cod, @desc, @valor)
	SET @cod = @cod + 1
END

CREATE PROCEDURE sp_validaCPF(@cpf char(11), @output VARCHAR(MAX) OUTPUT) 
AS
	DECLARE @cont INT,
			@qtndigt INT,
			@digt INT,
			@digt2 INT,
			@char char(1),
			@parcial INT
	SET @cont = 1
	SET @qtndigt = 0
	SET @digt = 0
	SET @parcial = 0
	SET @digt2 = 0
	WHILE(@cont <=11)
	BEGIN
		SET @char = SUBSTRING(@cpf, @cont,1)
		IF(@char IS NOT NULL)
		BEGIN
			SET @qtndigt = @qtndigt + 1
		END
		SET @cont = @cont + 1
	END
	IF(@qtndigt = 11)
	BEGIN
		SET @cont = 10
		WHILE(@cont >= 2)
		BEGIN
			SET @parcial = @parcial + (CAST(SUBSTRING(@cpf, (11 - @cont), 1) AS INT)* @cont)
			SET @cont = @cont - 1
		END
		SET @digt = @parcial % 11
		IF(@digt > 9)
		BEGIN
			SET @digt = 0
		END
		ELSE
		BEGIN
			SET @digt = 11 - @digt
		END
		SET @cont = 11
		WHILE(@cont >=2)
		BEGIN
			SET @digt2 = (@digt2 + ((CAST(SUBSTRING(@cpf, (12 - @cont), 1) AS INT)) * @cont))
			SET @cont = @cont - 1
		END
		SET @digt2 = @digt2 % 11
		IF(@digt2 > 9)
		BEGIN
			SET @digt2 = 0
		END
		ELSE
		BEGIN
			SET @digt2 = 11 - @digt2
		END
		IF(((CAST(SUBSTRING(@cpf, 10, 1) AS INT) = @digt)) AND ((CAST(SUBSTRING(@cpf, 11, 1) AS INT) = @digt2)))
		BEGIN
			SET @output = 'CPF válido!'
		END
		ELSE
		BEGIN
			SET @output = 'CPF inválido'
		END
	END
	ELSE
	BEGIN
		RAISERROR('CPF inválido! Quantidade de dígitos incorreta...', 16, 1)
	END

CREATE PROCEDURE sp_inserePessoa(@cpf CHAR(11), @nome VARCHAR(100), @out VARCHAR(MAX) OUTPUT)
AS
	DECLARE @saida VARCHAR(MAX)
	EXEC sp_validaCPF @cpf, @saida OUTPUT
	IF(@saida = 'CPF válido!')
	BEGIN
		INSERT INTO pessoa VALUES(@cpf, @nome)
		SET @out = 'Inserido com sucesso'
	END
	ELSE
	BEGIN
		SET @out = 'Falha na inserção'
		RAISERROR(@saida, 16,1)
	END

DECLARE @sai VARCHAR(MAX)
EXEC sp_inserePessoa '48589151824', 'Fernando', @sai OUTPUT
