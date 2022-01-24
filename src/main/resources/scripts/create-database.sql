Select * from tb_despesa;
INSERT INTO tb_despesa (descricao, valor, data) values ('Supermercado', 190.83,'2022-01-03T18:12:45.3546');
INSERT INTO tb_despesa (descricao, valor, data) values ('Barbeiro', 40.00,'2022-01-02T17:30');
INSERT INTO tb_despesa (descricao, valor, data) values ('Alura', 90,'2022-01-02T9:12');
INSERT INTO tb_despesa (descricao, valor, data) values ('Alura', 90,'2022-02-02T9:13');

Select * from tb_receita;
INSERT INTO tb_receita (descricao, valor, data) values ('Salário', 4985.00,'2021-12-05T2:35:46');
INSERT INTO tb_receita (descricao, valor, data) values ('Imovel Alugado', 1000.00,'2022-01-10T15:10');
INSERT INTO tb_receita (descricao, valor, data) values ('Salário', 5623.43,'2021-01-05T8:30');
INSERT INTO tb_receita (descricao, valor, data) values ('Salário', 5624.44,'2022-01-05T8:31');
INSERT INTO tb_receita (descricao, valor, data) values ('Salário', 5625.45,'2022-02-05T8:32');
INSERT INTO tb_receita (descricao, valor, data) values ('Imovel Alugado', 1000.00,'2022-02-10T15:11');
INSERT INTO tb_receita (descricao, valor, data) values ('Emprestimo', 500.00,'2022-12-7T14:25');




SELECT * FROM TB_RECEITA;
SELECT * FROM TB_RECEITA tb where tb.ID = 1;
SELECT * FROM TB_RECEITA tb where tb.DESCRICAO = 'Salário';
SELECT * FROM TB_RECEITA tb where tb.DESCRICAO = 'Salário' AND tb.DATA = '2021-01-05T8:30';
--Não retorna porque não tem a hora
SELECT * FROM TB_RECEITA tb where tb.DESCRICAO = 'Salário' AND tb.DATA = '2021-01-05';
SELECT * FROM TB_RECEITA tb where tb.DESCRICAO = 'Salário' AND tb.DATA between '2020-12-31' and '2021-01-31';
SELECT * FROM TB_RECEITA tb where tb.DESCRICAO = 'Salário' AND tb.DATA between '2022-01-01' and '2022-01-31';
SELECT * FROM TB_RECEITA tb where tb.DESCRICAO = 'Emprestimo' AND tb.DATA between '2020-12-31' and '2021-01-31';
SELECT * FROM TB_RECEITA tb where tb.DESCRICAO = 'Emprestimo' AND tb.DATA between '2021-01-01' and '2022-01-31';