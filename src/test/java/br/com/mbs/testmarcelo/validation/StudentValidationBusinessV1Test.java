package br.com.mbs.testmarcelo.validation;

import javax.validation.ValidationException;

import org.junit.Test;

import br.com.mbs.testmarcelo.vo.StudentV1;
import junit.framework.Assert;

public class StudentValidationBusinessV1Test {

	@Test
	public void testValidationOk() {
		StudentValidationBusinessV1 validation = new StudentValidationBusinessV1();
		StudentV1 student = new StudentV1();
		student.setCpf("1");
		student.setEmail("marcelo@bol.com.br");
		student.setName("teste");
		validation.validation(student);
	}
	
	@Test
	public void testValidationWithProblem() {
		StudentValidationBusinessV1 validation = new StudentValidationBusinessV1();
		StudentV1 student = new StudentV1();
		try {
			validation.validation(student);
			Assert.fail();
		}catch(Exception e) {}
		
		try {
			student.setCpf("1");
			validation.validation(student);
			Assert.fail();
		}catch(Exception e) {}
		
		try {
			student.setCpf("1");
			student.setEmail("marcelo@bol.com.br");
			validation.validation(student);
			Assert.fail();
		}catch(Exception e) {}
		
		
		
	}
}
