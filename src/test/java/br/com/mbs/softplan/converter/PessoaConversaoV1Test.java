package br.com.mbs.softplan.converter;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.util.Date;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.mbs.softplan.entidade.PessoaEntidade;
import br.com.mbs.softplan.vo.PessoaV1;
import junit.framework.Assert;


public class PessoaConversaoV1Test {

	
	private PessoaConversaoV1 converter = new PessoaConversaoV1();
		
	

	@Test
	public void testParaPessoaV1() {
		PessoaEntidade pe = criaPessoaEntidade();
		PessoaV1 pv1 = converter.paraPessoaBase(pe);
		Assert.assertEquals("1", pv1.getCpf());
		Assert.assertNotNull(pv1.getDataNascimento());
		Assert.assertEquals("2", pv1.getEmail());
		Assert.assertEquals(new Long(15), pv1.getId());
		Assert.assertEquals("na", pv1.getNacionalidade());
		Assert.assertEquals("poa", pv1.getNaturalidade());		
		Assert.assertEquals("marcelo", pv1.getNome());
		Assert.assertEquals("m", pv1.getSexo());
	}
	
	private PessoaEntidade criaPessoaEntidade() {
		PessoaEntidade pe = new PessoaEntidade();
		pe.setCpf("1");
		pe.setDataNascimento(new Date());
		pe.setEmail("2");
		pe.setEndereco("3");
		pe.setId(15l);
		pe.setNacionalidade("na");
		pe.setNaturalidade("poa");
		pe.setNome("marcelo");
		pe.setSexo("m");
		return pe;
	}

	@Test
	public void testAtualizaPessoaEntidadeV1() {
		String endereco = "nao_pode_alterar";
		PessoaEntidade pe = new PessoaEntidade();
		pe.setId(10l);
		// somente PessoaV2 alterar essa propriedade
		pe.setEndereco(endereco);
		converter.atualizaPessoaEntidade(criaPessoaV1(), pe);		
		Assert.assertEquals("1", pe.getCpf());		
		Assert.assertEquals("3", pe.getEmail());
		Assert.assertEquals("4", pe.getNacionalidade());
		Assert.assertEquals("5", pe.getNaturalidade());
		Assert.assertEquals("6", pe.getNome());
		Assert.assertEquals("7", pe.getSexo());
		Assert.assertEquals(10l, pe.getId());
		Assert.assertEquals(endereco, pe.getEndereco());
		
	}
	
	
	@Test
	public void testParaPessoaEntidadeV1()  {
		PessoaEntidade pe = converter.paraPessoaEntidade(criaPessoaV1());
		Assert.assertEquals("1", pe.getCpf());
	
		Assert.assertEquals("3", pe.getEmail());
		Assert.assertEquals("4", pe.getNacionalidade());
		Assert.assertEquals("5", pe.getNaturalidade());
		Assert.assertEquals("6", pe.getNome());
		Assert.assertEquals("7", pe.getSexo());
		Assert.assertEquals(1l, pe.getId());
	}
	
	private PessoaV1 criaPessoaV1() {
		PessoaV1 pessoaV1 = new PessoaV1();
		pessoaV1.setCpf("1");
		pessoaV1.setDataNascimento("26/06/1982");
		pessoaV1.setEmail("3");
		pessoaV1.setId(1l);
		pessoaV1.setNacionalidade("4");
		pessoaV1.setNaturalidade("5");
		pessoaV1.setNome("6");
		pessoaV1.setSexo("7");
		return pessoaV1;
	}

}
