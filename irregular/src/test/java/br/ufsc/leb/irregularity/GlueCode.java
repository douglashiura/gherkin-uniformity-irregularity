package br.ufsc.leb.irregularity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

import br.ufsc.leb.uniformity.code.Arquivo;
import br.ufsc.leb.uniformity.code.Contrato;
import br.ufsc.leb.uniformity.code.Fatura;
import br.ufsc.leb.uniformity.code.Fornecedor;
import br.ufsc.leb.uniformity.code.Sancao;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class GlueCode {
	private Fornecedor fornecedor;
	private Contrato contrato;
	private Arquivo banco;
	private Sancao sancao;
	private Double valorTotalDaFatura;
	private String codigoDaFatura;

	@Given("o fornecedor com CNPJ igual a {string} está cadastrado")
	public void o_fornecedor_com_CNPJ_igual_a_está_cadastrado(String cnpj) {
		configurarOCadastro();
		fornecedor = banco.obterFornecedor(cnpj);
	}

	private void configurarOCadastro() {
		banco = new Arquivo();
		Fornecedor fornecedor = new Fornecedor("63768444000191");
		Contrato contrato = new Contrato("354/2015", fornecedor);
		banco.cadastar(fornecedor);
		banco.cadastrar(contrato);
		fornecedor = new Fornecedor("50011445000145");
		contrato = new Contrato("142/2017", fornecedor);
		banco.cadastar(fornecedor);
		banco.cadastrar(contrato);
		fornecedor = new Fornecedor("58025324000164");
		contrato = new Contrato("001/2017", fornecedor);
		banco.cadastar(fornecedor);
		banco.cadastrar(contrato);
		fornecedor = new Fornecedor("42172555000160");
		contrato = new Contrato("171/2015", fornecedor);
		banco.cadastar(fornecedor);
		banco.cadastrar(contrato);		
	}

	@Given("esse fornecedor possui um contrato {string}")
	public void esse_fornecedor_possui_um_contrato(String identificadorDeContrato) {
		contrato = fornecedor.obterContrato(identificadorDeContrato);
	}

	@Given("o valor total desse contrato é igual a R$\"{double}\"")
	public void o_valor_total_desse_contrato_é_igual_a_R$(Double valor) {
		contrato.setValor(valor);
	}

	@When("o usuario seleciona o fornecedor com CNPJ igual a {string}")
	public void o_usuario_seleciona_o_fornecedor_com_CNPJ_igual_a(String cnpj) {
		fornecedor = banco.obterFornecedor(cnpj);
	}

	@When("o usuário seleciona o contrato {string}")
	public void o_usuário_seleciona_o_contrato(String identificadorDeContrato) {
		contrato = fornecedor.obterContrato(identificadorDeContrato);
	}

	@Then("o sistema soma o valor de todas as faturas emitidas para esse contrato e diminui do valor total do contrato e apresenta o saldo R$\"{double}\" para o usuário")
	public void o_sistema_soma_o_valor_de_todas_as_faturas_emitidas_para_esse_contrato_e_diminui_do_valor_total_do_contrato_e_apresenta_o_saldo_R$_para_o_usuário(
			Double valor) {
		assertEquals(valor, contrato.getSaldo());
	}

	@Given("existe uma fatura de R$\"{double}\" emitida para esse contrato")
	public void existe_uma_fatura_de_R$_emitida_para_esse_contrato(Double valor) {
		contrato.addFatura(new Fatura(codigoDaFatura, valor));
	}

	@Given("não existem faturas emitidas para esse contrato")
	public void não_existem_faturas_emitidas_para_esse_contrato() {
		assertTrue(contrato.getFaturas().isEmpty());
	}

	@Given("esse contrato possui uma sanção com penalidade")
	public void esse_contrato_possui_uma_sanção_com_penalidade() {
		sancao = new Sancao();
		contrato.addSancao(sancao);
	}

	@Given("essa sanção não tem data de suspensão")
	public void essa_sanção_não_tem_data_de_suspensão() {
	}

	@Given("essa sanção tem data de validade igual a {string}")
	public void essa_sanção_tem_data_de_validade_igual_a(String validade) throws ParseException {
		sancao.setValidade(
				DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM, Locale.US).parse(validade));
	}

	@Given("a data {string} é maior que a data atual")
	public void a_data_é_maior_que_a_data_atual(String validade) throws ParseException {
		assertTrue(
				DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM).parse(validade).after(new Date()));
	}

	@Given("esse fornecedor não possui sanções com penalidade")
	public void esse_fornecedor_não_possui_sanções_com_penalidade() {
	}

	@Given("esse contrato tem saldo igual a R$\"{double}\"")
	public void esse_contrato_tem_saldo_igual_a_R$(Double saldo) {
		contrato.setValor(saldo);
	}

	@When("o usuario informa o número da fatura {string}")
	public void o_usuario_informa_o_número_da_fatura(String codigoDaFatura) {
		this.codigoDaFatura = codigoDaFatura;
	}

	@When("o usuario informa o contrato {string}")
	public void o_usuario_informa_o_contrato(String identificadorDeContrato) {
		contrato = fornecedor.obterContrato(identificadorDeContrato);
	}

	@When("o usuario informa o valor total da fatura R$\"{double}\"")
	public void o_usuario_informa_o_valor_total_da_fatura_R$(Double valorTotalDaFatura) {
		this.valorTotalDaFatura = valorTotalDaFatura;
	}

	@Then("o sistema emite uma mensagem que a fatura foi cadastrada com sucesso")
	public void o_sistema_emite_uma_mensagem_que_a_fatura_foi_cadastrada_com_sucesso() {
		Fatura fatura = new Fatura(codigoDaFatura, valorTotalDaFatura);
		Boolean haSaldoParaFatura = contrato.haSaldo(fatura);
		Boolean adicionada = contrato.addFatura(fatura);
		assertTrue(adicionada);
		assertTrue(haSaldoParaFatura);
	}

	@Then("o sistema emite uma mensagem que não foi possível cadastrar a fatura pois não existe saldo suficiente no contrato selecionado")
	public void o_sistema_emite_uma_mensagem_que_não_foi_possível_cadastrar_a_fatura_pois_não_existe_saldo_suficiente_no_contrato_selecionado() {
		Fatura fatura = new Fatura(codigoDaFatura, valorTotalDaFatura);
		Boolean haSaldoParaFatura = contrato.haSaldo(fatura);
		Boolean adicionada = contrato.addFatura(fatura);
		assertFalse(adicionada);
		assertFalse(haSaldoParaFatura);
		assertNotNull(codigoDaFatura);
	}

	@Then("o sistema emite uma mensagem que não é possível cadastrar uma fatura para este fornecedor, pois ele possui sanções com penalidade ativas")
	public void o_sistema_emite_uma_mensagem_que_não_é_possível_cadastrar_uma_fatura_para_este_fornecedor_pois_ele_possui_sanções_com_penalidade_ativas() {
		Boolean haSancoesAtivas = contrato.haSancoesAtivas();
		Boolean adicionada = contrato.addFatura(new Fatura(codigoDaFatura, 0d));
		assertFalse(adicionada);
		assertTrue(haSancoesAtivas);
	}
}
