package br.ufsc.leb.uniformity.code;

public class Fatura {

	private Double valor;

	public Fatura(String codigoDaFatura,Double valor) 
	{
		this.valor = valor;
	}

	public Double getValor() {
		return valor;
	}

}
