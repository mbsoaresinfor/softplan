package br.com.mbs.softplan.api;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

public interface PessoaApi <T>{

	
		@ApiOperation(value = "Salva uma pessoa")
		@ApiResponses(value = {
			    @ApiResponse(code = 200, message = "Sucesso ao salvar a Pessoa"),			    
			    @ApiResponse(code = 405, message = "Pessoa com problema na validacao"),
			})
		
	 	@RequestMapping( method = RequestMethod.POST, produces="text/plain" )	 
	    ResponseEntity<String> salvar(@RequestBody T pessoa);
		
		
		@ApiOperation(value = "Retorna uma lista de pessoas",responseContainer="List")
		 @ApiResponses(value = {
				    @ApiResponse(code = 200, message = "Sucesso no retorno da lista de pessoas")			   
				})	 
		 @RequestMapping( method = RequestMethod.GET, produces="application/json")	 
		  public List<T> getPessoas();
		
		  
		 
		 @ApiOperation(value = "Retorna uma pessoa")
		 @ApiResponses(value = {
				    @ApiResponse(code = 200, message = "Sucesso no retorno da pessoa"),
				    @ApiResponse(code = 404, message = "Pessoa nao encontrado"),
				})
		 @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces="application/json")	 
		  public ResponseEntity<T> getPessoa(@PathVariable Long id) ;
			
		 
		 
		 @ApiOperation(value = "Deleta uma pessoa")
		 @ApiResponses(value = {
				    @ApiResponse(code = 200, message = "Sucesso na remocao da pessoa"),
				    @ApiResponse(code = 404, message = "Pessoa nao encontrado"),			  
				})	 
		 @DeleteMapping("/{id}")
		 public ResponseEntity<Void> deleta(@PathVariable Long id) ;
			
		 
		 
		 @ApiOperation(value = "Atualiza uma pessoa")
		 @ApiResponses(value = {
				    @ApiResponse(code = 200, message = "Sucesso na atualizacao da pessoa"),
				    @ApiResponse(code = 405, message = "Problema na validacao do Pessoa "),			  
				})	 
		 @RequestMapping( method = RequestMethod.PUT, produces="text/plain")	 
		 public ResponseEntity<String> atualiza(@RequestBody T pessoa);
			


}
