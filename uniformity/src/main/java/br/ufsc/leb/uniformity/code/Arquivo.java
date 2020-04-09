package br.ufsc.leb.uniformity.code;

import java.util.HashMap;
import java.util.Map;

public class Arquivo {


	private Map<String, Fornecedor> fornecedores;

	public Arquivo() {
	fornecedores = new HashMap<String, Fornecedor>();
	}
	public void cadastar(Fornecedor fornecedor) {
		fornecedores.put(fornecedor.getCnpj(),fornecedor);
	}

	public void cadastrar(Contrato contrato) {
		contrato.getFornecedor().addContrato(contrato);
	}

	public Fornecedor obterFornecedor(String cnpj) {
		return fornecedores.get(cnpj);
	}



}
