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
import br.com.mbs.softplan.vo.PessoaV2;
import junit.framework.Assert;


public class PessoaConversaoV2Test {

	
	private PessoaConversaoV2 converter = new PessoaConversaoV2();
		
	

	@Test
	public void testParaPessoaV2() {
		PessoaEntidade pe = criaPessoaEntidade();
		PessoaV2 pv2 = converter.paraPessoaBase(pe);
		Assert.assertEquals("1", pv2.getCpf());
		Assert.assertNotNull(pv2.getDataNascimento());
		Assert.assertEquals("2", pv2.getEmail());
		Assert.assertEquals(new Long(15), pv2.getId());
		Assert.assertEquals("na", pv2.getNacionalidade());
		Assert.assertEquals("poa", pv2.getNaturalidade());		
		Assert.assertEquals("marcelo", pv2.getNome());
		Assert.assertEquals("m", pv2.getSexo());
		Assert.assertEquals("cachoeirinha", pv2.getEndereco());
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
		pe.setEndereco("cachoeirinha");
		return pe;
	}

	@Test
	public void testAtualizaPessoaEntidadeV1() {
		String endereco = "nao_pode_alterar";
		PessoaEntidade pe = new PessoaEntidade();
		pe.setId(10l);
		// somente PessoaV2 alterar essa propriedade
		pe.setEndereco(endereco);
		converter.atualizaPessoaEntidade(criaPessoaV2(), pe);		
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
	public void testParaPessoaEntidadeV2()  {
		PessoaEntidade pe = converter.paraPessoaEntidade(criaPessoaV2());
		Assert.assertEquals("1", pe.getCpf());
	
		Assert.assertEquals("3", pe.getEmail());
		Assert.assertEquals("4", pe.getNacionalidade());
		Assert.assertEquals("5", pe.getNaturalidade());
		Assert.assertEquals("6", pe.getNome());
		Assert.assertEquals("7", pe.getSexo());
		Assert.assertEquals(1l, pe.getId());
		Assert.assertEquals("8", pe.getEndereco());
	}
	
	private PessoaV2 criaPessoaV2() {
		PessoaV2 pessoaV2 = new PessoaV2();
		pessoaV2.setCpf("1");
		pessoaV2.setDataNascimento("26/06/1982");
		pessoaV2.setEmail("3");
		pessoaV2.setId(1l);
		pessoaV2.setNacionalidade("4");
		pessoaV2.setNaturalidade("5");
		pessoaV2.setNome("6");
		pessoaV2.setSexo("7");
		pessoaV2.setEndereco("8");
		return pessoaV2;
	}

}
