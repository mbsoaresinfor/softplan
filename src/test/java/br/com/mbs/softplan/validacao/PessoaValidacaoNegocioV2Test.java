package br.com.mbs.softplan.validacao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.util.Experimental;
import org.mockito.Mock;
import org.mockito.Mockito;

import br.com.mbs.softplan.exception.ValidacaoException;
import br.com.mbs.softplan.service.PessoaService;
import br.com.mbs.softplan.vo.PessoaV1;
import br.com.mbs.softplan.vo.PessoaV2;
import junit.framework.Assert;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

class PessoaValidacaoNegocioV2Test {

	
	private   PessoaValidacaoNegocioV2 validacao;
	
	
	@BeforeEach
	public void setUpBeforeClass() throws Exception {

		validacao = new PessoaValidacaoNegocioV2();

	}

	@Test
	public void testEnderecoPessoaInvalido() {
		PessoaV2 p2 = criaPessoav2();
		try {
			p2.setEndereco(null);
			validacao.valida(p2);
			Assert.fail();
		}catch(Exception e) {}
		
		try {
			p2.setEndereco("");
			validacao.valida(p2);
			Assert.fail();
		}catch(Exception e) {}
		
	}
	
	@Test
	void testPessoaOk() throws ValidacaoException {
		validacao.valida(criaPessoav2());
	}
	
	private PessoaV2 criaPessoav2() {
		PessoaV2 p2 = new PessoaV2();
		p2.setCpf("81422938035");
		p2.setDataNascimento("26/06/1982");
		p2.setEmail("marcelo@gmail.com");
		p2.setNacionalidade("brasileira");
		p2.setNaturalidade("poa");
		p2.setNome("marcelo");
		p2.setSexo("m");
		p2.setEndereco("cachoeirinha");
		return p2;
	}
	
}
