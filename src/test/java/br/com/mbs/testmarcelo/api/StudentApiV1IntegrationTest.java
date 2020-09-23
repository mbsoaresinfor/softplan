package br.com.mbs.testmarcelo.api;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collections;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Ignore;
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

import br.com.mbs.testmarcelo.vo.StudentV1;
import junit.framework.Assert;

import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@Ignore
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
class StudentApiV1IntegrationTest {

	static int cont = 1;
	private final String USER = "marcelo";
	private final String PWD = "123";
	
	@LocalServerPort
    int randomServerPort;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	private String getEndPointStudentV1() {		
		return "http://localhost:"+randomServerPort+"/v1/student/";
	}
	
	
	@Test
	void testDeleteStudentRA() throws JSONException {
		// adiciona uma pessoa	
		ResponseEntity<String> responseAdicionaPessoa = addStudent();			
		Integer id = Integer.parseInt(responseAdicionaPessoa.getBody());	
		
		// deleta
		TestRestTemplate testRestTemplate = new TestRestTemplate(USER,PWD);
		testRestTemplate. delete(getEndPointStudentV1() + Long.toString(id) );
		
		// busca para verificar que deletou
		testRestTemplate = new TestRestTemplate(USER,PWD);
		ResponseEntity<String> response = testRestTemplate.
		  getForEntity(getEndPointStudentV1() + Long.toString(id) , String.class);
		
		assertEquals( HttpStatus.NOT_FOUND,response.getStatusCode());	
				
		
	}
	
	
	
	@Test
	void testGetStudentRA() throws JSONException {
		// adiciona uma pessoa
		ResponseEntity<String> responseAdicionaPessoa = addStudent();			
		Integer id = Integer.parseInt(responseAdicionaPessoa.getBody());	
		
		TestRestTemplate testRestTemplate = new TestRestTemplate(USER,PWD);
		ResponseEntity<StudentV1> response = testRestTemplate.
		  getForEntity(getEndPointStudentV1() + Integer.toString(id) , StudentV1.class);
		
		assertEquals( HttpStatus.OK,response.getStatusCode());		
		
	}
	
	
	@Test
	void testGetStudent() throws JSONException {
		testPostStudent();
		TestRestTemplate testRestTemplate = new TestRestTemplate(USER,PWD);
		ResponseEntity<String> response = testRestTemplate.
		  getForEntity(getEndPointStudentV1() , String.class);
		response.getBody();
		assertEquals( HttpStatus.OK,response.getStatusCode());		
		
		
	}
	
	@Test
	void testPutStudent() throws JSONException {
		
		// add student
		ResponseEntity<String> response = addStudent(buildStudentV1("cpf_1","email@bol.com.br","nome"));			
		Integer retorno = Integer.parseInt(response.getBody());		
		
		TestRestTemplate testRestTemplate = new TestRestTemplate(USER,PWD);
		 
		HttpHeaders headers = new HttpHeaders();		
		headers.setContentType(MediaType.APPLICATION_JSON);
		  
	     HttpEntity<String> request =  new HttpEntity<>(buildStudentV1("novo_cpf", 
	    		 "email_alterado@bol.com.br", "nome_alterado", retorno.toString()).toString(),headers);
	        
	     testRestTemplate.put(getEndPointStudentV1(),request);
	     
	     
			ResponseEntity<StudentV1> responseGet = testRestTemplate.
			  getForEntity(getEndPointStudentV1() + Integer.toString(retorno) , StudentV1.class);
		
			StudentV1 studentUpdate = responseGet.getBody();
		
			Assert.assertEquals("novo_cpf", studentUpdate.getCpf());
			Assert.assertEquals("email_alterado@bol.com.br", studentUpdate.getEmail());
			Assert.assertEquals("nome_alterado", studentUpdate.getName());
	}
	

	@Test
	void testPostStudent() throws JSONException {
			 
		ResponseEntity<String> response = addStudent();
		assertEquals( HttpStatus.OK,response.getStatusCode());	
		Integer retorno = Integer.parseInt(response.getBody());				
		assertTrue(retorno > 0);
	
	}
	
	@Test
	void testPostStudentWithProblem() throws JSONException {
		
		//cpf invalid
		ResponseEntity<String> response = addStudent(this.buildStudentV1("", "email@bol.com.br", "teste"));
		assertEquals( HttpStatus.METHOD_NOT_ALLOWED,response.getStatusCode());	
		
		
		//email invalid
		response = addStudent(this.buildStudentV1("cpf_ok", "", "teste"));
		assertEquals( HttpStatus.METHOD_NOT_ALLOWED,response.getStatusCode());	

		
		//name invalid
		response = addStudent(this.buildStudentV1("cpf_ok", "email@bol.com.br", ""));
		assertEquals( HttpStatus.METHOD_NOT_ALLOWED,response.getStatusCode());	

	
	}
	
	
	private ResponseEntity<String>  addStudent() throws JSONException {
		return this.addStudent(buildStudentV1() );
	}
	
	
	private ResponseEntity<String>  addStudent(JSONObject json) throws JSONException {
		TestRestTemplate testRestTemplate = new TestRestTemplate(USER,PWD);
		 
		HttpHeaders headers = new HttpHeaders();		
		headers.setContentType(MediaType.APPLICATION_JSON);
		  
	     HttpEntity<String> request =  new HttpEntity<>(json.toString(), headers);
	           
	        
		ResponseEntity<String> response = testRestTemplate.
		  postForEntity(getEndPointStudentV1(),request,String.class);	 
		
			
		return response;	
		
			
	}
	

	private JSONObject buildStudentV1() throws JSONException {
		JSONObject personJsonObject = new JSONObject();		
	    personJsonObject.put("cpf", "8142293800"+cont++);	    
	    personJsonObject.put("email", "marcelo@gmail.com");    	    
	    personJsonObject.put("name", "marcelo soares");	      		
		return personJsonObject;
	}
	
	private JSONObject buildStudentV1(String cpf,String email,String name) throws JSONException {
		JSONObject personJsonObject = new JSONObject();
	    personJsonObject.put("cpf", cpf);	    
	    personJsonObject.put("email", email);    	    
	    personJsonObject.put("name", name);	      		
		return personJsonObject;
	}
	
	private JSONObject buildStudentV1(String cpf,String email,String name,String ra) throws JSONException {
		JSONObject personJsonObject = new JSONObject();
	    personJsonObject.put("cpf", cpf);	    
	    personJsonObject.put("email", email);    	    
	    personJsonObject.put("name", name);	 
	    personJsonObject.put("ra", ra);
		return personJsonObject;
	}


}
