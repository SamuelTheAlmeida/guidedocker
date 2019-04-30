CREATE TABLE tb_clientes
(
	nm_cliente varchar(30),
	cpf_cliente varchar(11),
	end_cliente varchar(60),
	cid_cliente varchar(30),
	est_cliente char(2)
);

INSERT INTO tb_clientes
VALUES
('Joao', '11111111111', 'Rua do Teste 80', 'Curitiba', 'PR'),
('Tester', '00000000000', 'Rua do Teste 120', 'Sao Paulo', 'SP');