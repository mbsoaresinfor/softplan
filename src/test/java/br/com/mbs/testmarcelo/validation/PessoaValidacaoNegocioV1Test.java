package br.com.mbs.testmarcelo.validation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Ignore;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.util.Experimental;
import org.mockito.Mock;
import org.mockito.Mockito;

import br.com.mbs.testmarcelo.exception.ValidationBusinessException;
import br.com.mbs.testmarcelo.service.StudentService;
import br.com.mbs.testmarcelo.validation.StudentValidationBusinessV1;
import br.com.mbs.testmarcelo.vo.StudentV1;
import junit.framework.Assert;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

@Ignore
class PessoaValidacaoNegocioV1Test {

	
//	private   StudentValidationBusinessV1 validacao;
//	
//	
//	@BeforeEach
//	public void setUpBeforeClass() throws Exception {
//
//		StudentService<StudentV1> pessoaService = Mockito.mock(StudentService.class);
//		validacao = new StudentValidationBusinessV1(pessoaService);
//
//	}
//
//	@Test
//	void testPessoaOk() throws ValidationBusinessException {
//		validacao.validation(criaPessoav1());
//	}
//	
//	
//	@Test
//	public void testSePessoaJaExisteSistemaSalvar() throws ValidationBusinessException {
//		StudentService<StudentV1> pessoaService = Mockito.mock(StudentService.class);
//		Mockito.when(pessoaService.find(anyString())).thenReturn(Optional.of(new StudentV1()));
//		validacao = new StudentValidationBusinessV1(pessoaService);
//		try {
//			StudentV1 p1 = criaPessoav1();
//			validacao.validation(p1);
//			Assert.fail();
//		}catch(Exception e) {}
//	}
//	
//	@Test
//	public void testSePessoaJaExisteSistemaAtualizar() throws ValidationBusinessException {
//		StudentService<StudentV1> pessoaService = Mockito.mock(StudentService.class);
//		Mockito.when(pessoaService.find(anyString())).thenReturn(Optional.of(new StudentV1()));
//		validacao = new StudentValidationBusinessV1(pessoaService);
//		try {
//			StudentV1 p1 = criaPessoav1();
//			p1.setId(2l); // definindo que é um update
//			validacao.validation(p1);			
//		}catch(Exception e) {
//			Assert.fail();
//		}
//	}
//	
//	@Test
//	public void testCpfFormatoPessoaInvalido() throws ValidationBusinessException {
//		StudentV1 p1 = criaPessoav1();
//		try {
//			p1.setCpf("");
//			validacao.validation(p1);
//			Assert.fail();
//		}catch(Exception e) {}
//		try {
//			p1.setCpf(null);
//			validacao.validation(p1);
//			Assert.fail();
//		}catch(Exception e) {}
//		
//		try {
//			p1.setCpf("1234567890");
//			validacao.validation(p1);
//			Assert.fail();
//		}catch(Exception e) {}
//		
//	}
//	
//	@Test
//	public void testDataNascimentoPessoaInvalido() throws ValidationBusinessException {
//		StudentV1 p1 = criaPessoav1();
//		try {
//			p1.setDataNascimento("");
//			validacao.validation(p1);
//			Assert.fail();
//		}catch(Exception e) {}
//		
//		try {
//			p1.setDataNascimento(null);
//			validacao.validation(p1);
//			Assert.fail();
//		}catch(Exception e) {}
//		
//		try {
//			p1.setDataNascimento("abc");
//			validacao.validation(p1);
//			Assert.fail();
//		}catch(Exception e) {}
//		
//		try {
//			p1.setDataNascimento("28/92222");
//			validacao.validation(p1);
//			Assert.fail();
//		}catch(Exception e) {}
//		
//		try {
//			p1.setDataNascimento("2892222");
//			validacao.validation(p1);
//			Assert.fail();
//		}catch(Exception e) {}
//	}
//	
//	@Test
//	public void testNomePessoaInvalido() throws ValidationBusinessException {
//		StudentV1 p1 = criaPessoav1();
//		try {
//			p1.setNome("");
//			validacao.validation(p1);
//			Assert.fail();
//		}catch(Exception e) {}
//		
//		try {
//			p1.setNome(null);
//			validacao.validation(p1);
//			Assert.fail();
//		}catch(Exception e) {}
//	}
//	
//	@Test
//	public void testEmailPessoaInvalido() throws ValidationBusinessException {
//		StudentV1 p1 = criaPessoav1();
//		try {
//			p1.setEmail("teste");
//			validacao.validation(p1);
//			Assert.fail();
//		}catch(Exception e) {}
//		
//		try {
//			p1.setEmail("a");
//			validacao.validation(p1);
//			Assert.fail();
//		}catch(Exception e) {}
//	}
//	
//	private StudentV1 criaPessoav1() {
//		StudentV1 p1 = new StudentV1();
//		p1.setCpf("81422938035");
//		p1.setDataNascimento("26/06/1982");
//		p1.setEmail("marcelo@gmail.com");
//		p1.setNacionalidade("brasileira");
//		p1.setNaturalidade("poa");
//		p1.setNome("marcelo");
//		p1.setSexo("m");
//		
//		return p1;
//	}

}
