Feature: Calcular Saldo Do Fornecedor Por Contrato
Scenario: Calcular Saldo com Sucesso
	Given o fornecedor com CNPJ igual a "96.471.721/0001-59" está cadastrado
	And esse fornecedor possui um contrato "354/2015"
	And o valor total desse contrato é igual a R$"5000.00"
	And existe uma fatura de R$"50.00" emitida para esse contrato
	And existe uma fatura de R$"150.00" emitida para esse contrato
	When o usuario seleciona o fornecedor com CNPJ igual a "96.471.721/0001-59"
	And o usuário seleciona o contrato "354/2015"
	Then o sistema soma o valor de todas as faturas emitidas para esse contrato e diminui do valor total do contrato e apresenta o saldo R$"4800.00" para o usuário
