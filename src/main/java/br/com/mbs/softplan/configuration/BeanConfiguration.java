package br.com.mbs.softplan.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import br.com.mbs.softplan.converter.PessoaConversao;
import br.com.mbs.softplan.service.PessoaService;
import br.com.mbs.softplan.service.PessoaServiceImpl;
import br.com.mbs.softplan.validacao.PessoaValidacaoNegocioV1;
import br.com.mbs.softplan.validacao.PessoaValidacaoNegocioV2;
import br.com.mbs.softplan.validacao.ValidacaoNegocioComposite;
import br.com.mbs.softplan.vo.PessoaV1;
import br.com.mbs.softplan.vo.PessoaV2;

@Configuration
public class BeanConfiguration {

	@Qualifier("v1")
	@Autowired
	private PessoaConversao<PessoaV1> pessoaConversaoV1;
	
	@Qualifier("v2")
	@Autowired
	private PessoaConversao<PessoaV2> pessoaConversaoV2;
	
	
		
	@Bean
	public PessoaService<PessoaV1> pessoaServiceV1(){
		PessoaService<PessoaV1> pessoaSrc =new PessoaServiceImpl<PessoaV1>();
		pessoaSrc.setConversao(pessoaConversaoV1);		
		pessoaSrc.setValidacao(new  PessoaValidacaoNegocioV1(pessoaSrc));
		return pessoaSrc;
	}
	
	@Bean
	public PessoaService<PessoaV2> pessoaServiceV2(){
		PessoaService<PessoaV2> pessoaSrc =new PessoaServiceImpl<PessoaV2>();
		pessoaSrc.setConversao(pessoaConversaoV2);
		ValidacaoNegocioComposite composite = new ValidacaoNegocioComposite();
		composite.adicionaValidacaoNegocio(new PessoaValidacaoNegocioV1(pessoaSrc));
		composite.adicionaValidacaoNegocio(new PessoaValidacaoNegocioV2());		
		pessoaSrc.setValidacao(composite);
		return pessoaSrc;
	}
	
	
	
	
}
