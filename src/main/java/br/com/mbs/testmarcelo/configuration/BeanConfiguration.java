package br.com.mbs.testmarcelo.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.mbs.testmarcelo.converter.StudentConversion;
import br.com.mbs.testmarcelo.service.StudentService;
import br.com.mbs.testmarcelo.service.StudentServiceImpl;
import br.com.mbs.testmarcelo.validation.StudentValidationBusinessV1;
import br.com.mbs.testmarcelo.vo.StudentV1;

@Configuration
public class BeanConfiguration {

	@Qualifier("v1")
	@Autowired
	private StudentConversion<StudentV1> studentConversionV1;
	
	
		
	@Bean
	public StudentService<StudentV1> pessoaServiceV1(){
		StudentService<StudentV1> pessoaSrc =new StudentServiceImpl<StudentV1>();
		pessoaSrc.setConversion(studentConversionV1);		
		pessoaSrc.setValidation(new  StudentValidationBusinessV1(pessoaSrc));
		return pessoaSrc;
	}
	
	
	
	
}
