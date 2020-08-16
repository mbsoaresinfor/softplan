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
import br.com.mbs.softplan.exception.ValidacaoException;
import br.com.mbs.softplan.service.PessoaService;

import br.com.mbs.softplan.vo.PessoaV1;

import io.swagger.annotations.Api;


@RestController(value="API para manipulacao de Pessoas Versao 1")
@Api(description="Api de Pessoas versao 1")
@RequestMapping("/pessoas/v1")
public class PessoaApiV1 implements PessoaApi<PessoaV1>{


	@Autowired
	private PessoaService<PessoaV1> pessoaServiceV1;
	

	@Override
	public ResponseEntity<String> salvar(PessoaV1 pessoa) {
		
		 ResponseEntity<String> ret = null;
		 try {
			 Long id = pessoaServiceV1.salvar(pessoa);
			 ret =  new ResponseEntity<>( id.toString(),HttpStatus.OK);
		 }catch(ValidacaoException e) {
			 ret = new ResponseEntity<>(e.getMessage(),HttpStatus.METHOD_NOT_ALLOWED);
		 }
		 return ret;
	
	}

	@Override
	public List<PessoaV1> getPessoas() {		
		return pessoaServiceV1.buscar();
	}

	@Override
	public ResponseEntity<PessoaV1> getPessoa(Long id)  {		
		ResponseEntity<PessoaV1> responseEntity;
		Optional<PessoaV1> optPessoaV1 = pessoaServiceV1.buscar(id);
		if(optPessoaV1.isPresent()){		
			responseEntity = new ResponseEntity<>(optPessoaV1.get(),HttpStatus.OK);			
		 }else {
			 responseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND).build();			
		}
		return responseEntity;
	}

	@Override
	public ResponseEntity<Void> deleta(Long id) {
		ResponseEntity<Void> responseEntity;
		 try {
			 pessoaServiceV1.deletar(id);
			  responseEntity = ResponseEntity.ok().build();
		 }catch( ValidacaoException e) {
			 responseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		 }
		 return responseEntity;
		
	}

	@Override
	public ResponseEntity<String> atualiza(PessoaV1 pessoa) {
		 ResponseEntity<String> ret = null;
		 try {
			 pessoaServiceV1.atualizar(pessoa,pessoa.getId());
			 ret = ResponseEntity.ok().build();
		 }catch(ValidacaoException e) {		
			 ret = new ResponseEntity<>(e.getMessage(),HttpStatus.METHOD_NOT_ALLOWED);
		 }
		 return ret;
	}
	

}
