package br.com.mbs.testmarcelo.validation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.google.common.collect.PeekingIterator;

import br.com.mbs.testmarcelo.exception.ValidationBusinessException;
import br.com.mbs.testmarcelo.service.StudentService;
import br.com.mbs.testmarcelo.vo.StudentV1;



public class StudentValidationBusinessV1 implements ValidationBusiness {

	
	private StudentService<?> pessoaService;
	
	public StudentValidationBusinessV1() {}
	
	public StudentValidationBusinessV1(StudentService<?> pessoaService) {
		this.pessoaService = pessoaService;
	}
	
	@Override
	public void validation(Object obj) throws ValidationBusinessException {
		// cases special of validation.		
		// its hook, call by StudentService
	}


}
