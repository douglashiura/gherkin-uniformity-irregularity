Feature: Emitir Fatura
Scenario: Emitir Uma Fatura Com Sucesso
	Given o fornecedor com CNPJ igual a "96.471.721/0001-59" está cadastrado
	And esse fornecedor não possui sanções com penalidade 
	And esse fornecedor possui um contrato "354/2015"
	And esse contrato tem saldo igual a R$"5000.00"
	When o usuario seleciona o fornecedor com CNPJ igual a "96.471.721/0001-59"
	And o usuario informa o número da fatura "001/2018"
	And o usuario informa o contrato "354/2015"
	And o usuario informa o valor total da fatura R$"200.00"
	Then o sistema emite uma mensagem que a fatura foi cadastrada com sucesso 