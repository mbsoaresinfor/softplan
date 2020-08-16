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
import junit.framework.Assert;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

class PessoaValidacaoNegocioV1Test {

	
	private   PessoaValidacaoNegocioV1 validacao;
	
	
	@BeforeEach
	public void setUpBeforeClass() throws Exception {

		PessoaService<PessoaV1> pessoaService = Mockito.mock(PessoaService.class);
		validacao = new PessoaValidacaoNegocioV1(pessoaService);

	}

	@Test
	void testPessoaOk() throws ValidacaoException {
		validacao.valida(criaPessoav1());
	}
	
	
	@Test
	public void testSePessoaJaExisteSistemaSalvar() throws ValidacaoException {
		PessoaService<PessoaV1> pessoaService = Mockito.mock(PessoaService.class);
		Mockito.when(pessoaService.buscar(anyString())).thenReturn(Optional.of(new PessoaV1()));
		validacao = new PessoaValidacaoNegocioV1(pessoaService);
		try {
			PessoaV1 p1 = criaPessoav1();
			validacao.valida(p1);
			Assert.fail();
		}catch(Exception e) {}
	}
	
	@Test
	public void testSePessoaJaExisteSistemaAtualizar() throws ValidacaoException {
		PessoaService<PessoaV1> pessoaService = Mockito.mock(PessoaService.class);
		Mockito.when(pessoaService.buscar(anyString())).thenReturn(Optional.of(new PessoaV1()));
		validacao = new PessoaValidacaoNegocioV1(pessoaService);
		try {
			PessoaV1 p1 = criaPessoav1();
			p1.setId(2l); // definindo que é um update
			validacao.valida(p1);			
		}catch(Exception e) {
			Assert.fail();
		}
	}
	
	@Test
	public void testCpfFormatoPessoaInvalido() throws ValidacaoException {
		PessoaV1 p1 = criaPessoav1();
		try {
			p1.setCpf("");
			validacao.valida(p1);
			Assert.fail();
		}catch(Exception e) {}
		try {
			p1.setCpf(null);
			validacao.valida(p1);
			Assert.fail();
		}catch(Exception e) {}
		
		try {
			p1.setCpf("1234567890");
			validacao.valida(p1);
			Assert.fail();
		}catch(Exception e) {}
		
	}
	
	@Test
	public void testDataNascimentoPessoaInvalido() throws ValidacaoException {
		PessoaV1 p1 = criaPessoav1();
		try {
			p1.setDataNascimento("");
			validacao.valida(p1);
			Assert.fail();
		}catch(Exception e) {}
		
		try {
			p1.setDataNascimento(null);
			validacao.valida(p1);
			Assert.fail();
		}catch(Exception e) {}
		
		try {
			p1.setDataNascimento("abc");
			validacao.valida(p1);
			Assert.fail();
		}catch(Exception e) {}
		
		try {
			p1.setDataNascimento("28/92222");
			validacao.valida(p1);
			Assert.fail();
		}catch(Exception e) {}
		
		try {
			p1.setDataNascimento("2892222");
			validacao.valida(p1);
			Assert.fail();
		}catch(Exception e) {}
	}
	
	@Test
	public void testNomePessoaInvalido() throws ValidacaoException {
		PessoaV1 p1 = criaPessoav1();
		try {
			p1.setNome("");
			validacao.valida(p1);
			Assert.fail();
		}catch(Exception e) {}
		
		try {
			p1.setNome(null);
			validacao.valida(p1);
			Assert.fail();
		}catch(Exception e) {}
	}
	
	@Test
	public void testEmailPessoaInvalido() throws ValidacaoException {
		PessoaV1 p1 = criaPessoav1();
		try {
			p1.setEmail("teste");
			validacao.valida(p1);
			Assert.fail();
		}catch(Exception e) {}
		
		try {
			p1.setEmail("a");
			validacao.valida(p1);
			Assert.fail();
		}catch(Exception e) {}
	}
	
	private PessoaV1 criaPessoav1() {
		PessoaV1 p1 = new PessoaV1();
		p1.setCpf("81422938035");
		p1.setDataNascimento("26/06/1982");
		p1.setEmail("marcelo@gmail.com");
		p1.setNacionalidade("brasileira");
		p1.setNaturalidade("poa");
		p1.setNome("marcelo");
		p1.setSexo("m");
		
		return p1;
	}

}
