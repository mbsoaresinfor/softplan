package br.com.mbs.testmarcelo.service;

import java.util.List;
import java.util.Optional;

import br.com.mbs.testmarcelo.converter.StudentConversion;
import br.com.mbs.testmarcelo.exception.ValidationBusinessException;
import br.com.mbs.testmarcelo.validation.ValidationBusiness;


public interface StudentService<T> {

	Long save(T student) throws ValidationBusinessException;
	
	void delete(Long ra) throws ValidationBusinessException;
	
	void update(T student,Long ra) throws ValidationBusinessException;
	
	List<T> find();
	
	Optional<T> find(Long ra);
	
	void setConversion(StudentConversion<T> studentConversion);
	
	void setValidation(ValidationBusiness validationBusiness);

	
	
	
}
