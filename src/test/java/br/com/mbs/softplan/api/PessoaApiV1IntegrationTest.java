package br.com.mbs.softplan.api;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collections;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.mbs.softplan.vo.PessoaV1;

import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
class PessoaApiV1IntegrationTest {

	static int contador = 1;
	private final String USUARIO = "softplan";
	private final String SENHA = "123";
	
	@LocalServerPort
    int randomServerPort;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	private String getEndPointPessoasV1() {		
		return "http://localhost:"+randomServerPort+"/v1/pessoas/";
	}
	
	
	@Test
	void testDeletePessoasId() throws JSONException {
		// adiciona uma pessoa	
		ResponseEntity<String> responseAdicionaPessoa = adicionaPessoa();			
		Integer id = Integer.parseInt(responseAdicionaPessoa.getBody());	
		
		// deleta
		TestRestTemplate testRestTemplate = new TestRestTemplate(USUARIO,SENHA);
		testRestTemplate. delete(getEndPointPessoasV1() + Integer.toString(id) );
		
		// busca para verificar que deletou
		testRestTemplate = new TestRestTemplate(USUARIO,SENHA);
		ResponseEntity<String> response = testRestTemplate.
		  getForEntity(getEndPointPessoasV1() + Integer.toString(id) , String.class);
		
		assertEquals( HttpStatus.NOT_FOUND,response.getStatusCode());	
				
		
	}
	
	
	@Test
	void testGetPessoasId() throws JSONException {
		// adiciona uma pessoa
		ResponseEntity<String> responseAdicionaPessoa = adicionaPessoa();			
		Integer id = Integer.parseInt(responseAdicionaPessoa.getBody());	
		
		TestRestTemplate testRestTemplate = new TestRestTemplate(USUARIO,SENHA);
		ResponseEntity<String> response = testRestTemplate.
		  getForEntity(getEndPointPessoasV1() + Integer.toString(id) , String.class);
		
		assertEquals( HttpStatus.OK,response.getStatusCode());		
		
	}
	
	
	@Test
	void testGetPessoas() throws JSONException {
		testPostPessoas();
		TestRestTemplate testRestTemplate = new TestRestTemplate(USUARIO,SENHA);
		ResponseEntity<String> response = testRestTemplate.
		  getForEntity(getEndPointPessoasV1() , String.class);
		assertEquals( HttpStatus.OK,response.getStatusCode());		
		
		
	}
	
	@Test
	void testPostPessoas() throws JSONException {
			 
		ResponseEntity<String> response = adicionaPessoa();
		assertEquals( HttpStatus.OK,response.getStatusCode());	
		Integer retorno = Integer.parseInt(response.getBody());				
		assertTrue(retorno > 0);
	
	}
	
	
	
	private ResponseEntity<String>  adicionaPessoa() throws JSONException {
		TestRestTemplate testRestTemplate = new TestRestTemplate(USUARIO,SENHA);
		 
		HttpHeaders headers = new HttpHeaders();		
		headers.setContentType(MediaType.APPLICATION_JSON);
		  
		JSONObject json = criaPessoav1();
	      
	     HttpEntity<String> request =  new HttpEntity<>(json.toString(), headers);
	           
	        
		ResponseEntity<String> response = testRestTemplate.
		  postForEntity(getEndPointPessoasV1(),request,String.class);	 
		
			
		return response;	
		
			
	}
	

	private JSONObject criaPessoav1() throws JSONException {
		JSONObject personJsonObject = new JSONObject();
	    personJsonObject.put("cpf", "8142293800"+contador++);
	    personJsonObject.put("dataNascimento", "26/06/1982");
	    personJsonObject.put("email", "marcelo@gmail.com");
	    personJsonObject.put("nacionalidade", "brasileira");
	    personJsonObject.put("naturalidade", "poa");
	    personJsonObject.put("nome", "marcelo soares");
	    personJsonObject.put("sexo", "m");  		
		return personJsonObject;
	}


}
