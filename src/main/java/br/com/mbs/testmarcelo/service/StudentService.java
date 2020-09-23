package br.com.mbs.testmarcelo.service;

import java.util.List;
import java.util.Optional;
import javax.validation.ValidationException;

import br.com.mbs.testmarcelo.converter.StudentConversion;
import br.com.mbs.testmarcelo.validation.ValidationBusiness;

public interface StudentService<T> {

	Long save(T student) throws ValidationException;
	
	void delete(Long ra) throws ValidationException;
	
	void update(T student,Long ra) throws ValidationException;
	
	List<T> find();
	
	Optional<T> find(Long ra);
	
	void setConversion(StudentConversion<T> studentConversion);
	
	void setValidation(ValidationBusiness validationBusiness);

	
	
	
}
