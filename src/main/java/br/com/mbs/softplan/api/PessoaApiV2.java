package br.com.mbs.softplan.api;

import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mbs.softplan.converter.PessoaConversaoV1;
import br.com.mbs.softplan.converter.PessoaConversaoV2;
import br.com.mbs.softplan.exception.ValidacaoException;
import br.com.mbs.softplan.service.PessoaService;

import br.com.mbs.softplan.vo.PessoaV2;
import io.swagger.annotations.Api;


@RestController(value="API para manipulacao de Pessoas Versao 2")
@Api(description="Api de Pessoas Versao 2")
@RequestMapping("/pessoas/v2")
public class PessoaApiV2 implements PessoaApi<PessoaV2>{

	
	@Autowired
	private PessoaService<PessoaV2> pessoaServiceV2;
	


	@Override
	public ResponseEntity<String> salvar(PessoaV2 pessoa) {
		
		 ResponseEntity<String> ret = null;
		 try {
			 Long id = pessoaServiceV2.salvar(pessoa);
			 ret =  new ResponseEntity<>( id.toString(),HttpStatus.OK);
		 }catch(ValidacaoException e) {
			 ret = new ResponseEntity<>(e.getMessage(),HttpStatus.METHOD_NOT_ALLOWED);
		 }
		 return ret;
	
	}

	@Override
	public List<PessoaV2> getPessoas() {		
		return pessoaServiceV2.buscar();
	}

	@Override
	public ResponseEntity<PessoaV2> getPessoa(Long id)  {		
		ResponseEntity<PessoaV2> responseEntity;
		Optional<PessoaV2> optPessoaV2 = pessoaServiceV2.buscar(id);
		if(optPessoaV2.isPresent()){		
			responseEntity = new ResponseEntity<>(optPessoaV2.get(),HttpStatus.OK);			
		 }else {
			 responseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND).build();			
		}
		return responseEntity;
	}

	@Override
	public ResponseEntity<Void> deleta(Long id) {
		ResponseEntity<Void> responseEntity;
		 try {
			 pessoaServiceV2.deletar(id);
			  responseEntity = ResponseEntity.ok().build();
		 }catch( ValidacaoException e) {
			 responseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		 }
		 return responseEntity;
		
	}

	@Override
	public ResponseEntity<String> atualiza(PessoaV2 pessoa) {
		 ResponseEntity<String> ret = null;
		 try {
			 pessoaServiceV2.atualizar(pessoa,pessoa.getId());
			 ret = ResponseEntity.ok().build();
		 }catch(ValidacaoException e) {
			 ret = new ResponseEntity<>(e.getMessage(),HttpStatus.METHOD_NOT_ALLOWED);
		 }
		 return ret;
	}
	

}
