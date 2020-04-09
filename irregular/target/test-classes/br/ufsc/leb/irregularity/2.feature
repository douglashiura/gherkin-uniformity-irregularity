Feature: Calcular Saldo Do Fornecedor Por Contrato
Scenario: Calcular Saldo com Sucesso Para Contrato Sem Faturas
	Given o fornecedor com CNPJ igual a "50011445000145" está cadastrado
	And esse fornecedor possui um contrato "142/2017"
	And o valor total desse contrato é igual a R$"2265.65"
	And não existem faturas emitidas para esse contrato 
	When o usuario seleciona o fornecedor com CNPJ igual a "50011445000145"
	And o usuário seleciona o contrato "142/2017"
	Then o sistema soma o valor de todas as faturas emitidas para esse contrato e diminui do valor total do contrato e apresenta o saldo R$"2265.65" para o usuário
