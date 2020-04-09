Feature: Emitir Fatura
Scenario: Emitir Uma Fatura Para Fornecedor Que Possui Sanção Ativa Com Aplicação De Penalidade
	Given o fornecedor com CNPJ igual a "58025324000164" está cadastrado
	And esse fornecedor possui um contrato "001/2017"
	And esse contrato possui uma sanção com penalidade
	And essa sanção não tem data de suspensão
	And essa sanção tem data de validade igual a "Jun 30, 2022 7:03:47 AM"
	And a data "Jun 30, 2022 7:03:47 AM" é maior que a data atual
	When o usuario seleciona o fornecedor com CNPJ igual a "58025324000164"
	Then o sistema emite uma mensagem que não é possível cadastrar uma fatura para este fornecedor, pois ele possui sanções com penalidade ativas
