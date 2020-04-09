package br.ufsc.leb.uniformity.code;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Contrato {

	private Double valor;
	private List<Fatura> faturas;
	private Fornecedor fornecedor;
	private String identificadorDeContrato;
	private List<Sancao> sancoes;

	public Contrato(String identificadorDeContrato, Fornecedor fornecedor) {
		this.identificadorDeContrato = identificadorDeContrato;
		this.fornecedor = fornecedor;
		faturas = new ArrayList<Fatura>();
		sancoes = new LinkedList<Sancao>();
	}

	public void setValor(Double valor) {
		this.valor = valor;

	}

	public Boolean addFatura(Fatura fatura) {
		if (!haSancoesAtivas() && haSaldo(fatura)) {
			return faturas.add(fatura);
		} else {
			return Boolean.FALSE;
		}
	}

	public Double getSaldo() {
		return valor - valorDasFaturas();
	}

	private Double valorDasFaturas() {
		return faturas.stream().map(fatura -> fatura.getValor()).reduce(0d, Double::sum);
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public String getIdentificadorDeContrato() {
		return identificadorDeContrato;
	}

	public void addSancao(Sancao sancao) {
		sancoes.add(sancao);
	}

	public Boolean haSaldo(Fatura fatura) {
		return getSaldo() >= fatura.getValor();
	}

	public Boolean haSancoesAtivas() {
		return !sancoes.isEmpty();
	}

	public List<Fatura> getFaturas() {
		return faturas;
	}

}
