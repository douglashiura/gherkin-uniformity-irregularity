Feature: Emitir Fatura
Scenario: Emitir Uma Fatura Para Contrato Sem Saldo Suficiente
	Given o fornecedor com CNPJ igual a "50011445000145" está cadastrado
	And esse fornecedor não possui sanções com penalidade 
	And esse fornecedor possui um contrato "142/2017"
	And esse contrato tem saldo igual a R$"2265.65"
	When o usuario seleciona o fornecedor com CNPJ igual a "50011445000145"
	And o usuario informa o número da fatura "001/2018"
	And o usuario informa o contrato "142/2017"
	And o usuario informa o valor total da fatura R$"3000.00"
	Then o sistema emite uma mensagem que não foi possível cadastrar a fatura pois não existe saldo suficiente no contrato selecionado
