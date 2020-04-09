package br.ufsc.leb.uniformity.code;

import java.util.HashMap;
import java.util.Map;

public class Fornecedor {

	private String cnpj;
	private Map<String, Contrato> contratos;

	public Fornecedor(String cnpj) {
		this.cnpj = cnpj;
		contratos = new HashMap<>();
	}

	public Contrato obterContrato(String identificadorDeContrato) {
		return contratos.get(identificadorDeContrato);
	}

	public String getCnpj() {
		return cnpj;
	}

	public void addContrato(Contrato contrato) {
		contratos.put(contrato.getIdentificadorDeContrato(),contrato);
	}

}
