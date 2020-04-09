Feature: Emitir Fatura
Scenario: Emitir Uma Fatura Com Sucesso
	Given o fornecedor com CNPJ igual a "42172555000160" está cadastrado
	And esse fornecedor não possui sanções com penalidade 
	And esse fornecedor possui um contrato "171/2015"
	And esse contrato tem saldo igual a R$"4965123.65"
	When o usuario seleciona o fornecedor com CNPJ igual a "42172555000160"
	And o usuario informa o número da fatura "001/2018"
	And o usuario informa o contrato "171/2015"
	And o usuario informa o valor total da fatura R$"20000.00"
	Then o sistema emite uma mensagem que a fatura foi cadastrada com sucesso 

