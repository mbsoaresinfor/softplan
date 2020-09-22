package br.com.mbs.testmarcelo.converter;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import br.com.mbs.testmarcelo.entity.StudentEntity;
import br.com.mbs.testmarcelo.vo.StudentV1;


public class StudentConversionV1TEst {

	
	private StudentConversionV1 converter = new StudentConversionV1();
		
	@Test
	public void testToStudentV1() {		
		StudentV1 studentV1 = converter.toStudentBase(buildStudentEntity());
		Assert.assertEquals("cpf", studentV1.getCpf());		
		Assert.assertEquals("email", studentV1.getEmail());		
		Assert.assertEquals("marcelo", studentV1.getName());
		Assert.assertEquals(new Long(1), studentV1.getRa());		
	}
	
	@Test
	public void testToStudentEntity()  {
		StudentEntity se = converter.toStudentEntity(buildStudentV1());
		Assert.assertEquals("cpf", se.getCpf());	
		Assert.assertEquals("email", se.getEmail());
		Assert.assertEquals("marcelo", se.getName());
		Assert.assertEquals(new Long(1l), se.getRa());
	}
	
	

	@Test
	public void testUpdateStudentEntity() {
		
		StudentEntity studentEntity = buildStudentEntity();
		StudentV1 studentV1 = buildStudentV1();

		studentV1.setRa(10l);
		studentV1.setCpf("can_not_update");
		studentV1.setName("name_update");
		studentV1.setEmail("email_update");
		
		converter.updateStudentEntity(studentV1, studentEntity);		
		Assert.assertEquals("cpf", studentEntity.getCpf());		
		Assert.assertEquals("email_update", studentEntity.getEmail());
		Assert.assertEquals("name_update", studentEntity.getName());
		Assert.assertEquals(new Long(1l), studentEntity.getRa());
		
	}
	
	private StudentEntity buildStudentEntity() {
		StudentEntity studentEntity = new StudentEntity();
		studentEntity.setCpf("cpf");		
		studentEntity.setEmail("email");		
		studentEntity.setRa(1l);		
		studentEntity.setName("marcelo");		
		return studentEntity;
	}
	
	private StudentV1 buildStudentV1() {
		StudentV1 studentV1 = new StudentV1();
		studentV1.setCpf("cpf");		
		studentV1.setEmail("email");
		studentV1.setName("marcelo");
		studentV1.setRa(1l);
		return studentV1;
	}

}
