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
		ResponseEntity<String> response = testRestTemplate.
		  getForEntity(getEndPointStudentV1() + Integer.toString(id) , String.class);
		
		assertEquals( HttpStatus.OK,response.getStatusCode());		
		
	}
	
	
	@Test
	void testGetStudent() throws JSONException {
		testPostStudent();
		TestRestTemplate testRestTemplate = new TestRestTemplate(USER,PWD);
		ResponseEntity<String> response = testRestTemplate.
		  getForEntity(getEndPointStudentV1() , String.class);
		assertEquals( HttpStatus.OK,response.getStatusCode());		
		
		
	}
	
	@Test
	void testPostStudent() throws JSONException {
			 
		ResponseEntity<String> response = addStudent();
		assertEquals( HttpStatus.OK,response.getStatusCode());	
		Integer retorno = Integer.parseInt(response.getBody());				
		assertTrue(retorno > 0);
	
	}
	
	
	
	private ResponseEntity<String>  addStudent() throws JSONException {
		TestRestTemplate testRestTemplate = new TestRestTemplate(USER,PWD);
		 
		HttpHeaders headers = new HttpHeaders();		
		headers.setContentType(MediaType.APPLICATION_JSON);
		  
		JSONObject json = buildStudentV1();
	      
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


}
