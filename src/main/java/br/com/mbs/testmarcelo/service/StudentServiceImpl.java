package br.com.mbs.testmarcelo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.validation.ValidationException;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.mbs.testmarcelo.converter.StudentConversion;
import br.com.mbs.testmarcelo.entity.StudentEntity;
import br.com.mbs.testmarcelo.repository.StudentRepository;
import br.com.mbs.testmarcelo.validation.ValidationBusiness;

@Service
public class StudentServiceImpl<T> implements StudentService<T> {

	@Autowired
	private StudentRepository studentRepository;
	
	private StudentConversion<T> studentConversion;
	
	private ValidationBusiness validationBusiness;
	
	@Override
	public void setValidation(ValidationBusiness validacaoNegocio) {
		this.validationBusiness = validacaoNegocio;		
	}
	
	@Override
	public void setConversion(StudentConversion<T> pessoaConversao) {
		this.studentConversion = pessoaConversao;
	}
	
	@Override
	public Long save(T student) throws ValidationException {
		validationBusiness.validation(student);
		StudentEntity pessoaEntidade = studentConversion.toStudentEntity(student);
		pessoaEntidade.setDateSave((new Date()));
		return studentRepository.save(pessoaEntidade).getRa();
	}

	@Override
	public void delete(@NotNull @Min(1) Long ra) throws ValidationException {
		StudentEntity pessoaEntidade = studentRepository.find(ra);
		studentRepository.deleteById(pessoaEntidade.getRa());	
	}

	@Override
	public void update(T student,@NotNull @Min(1)Long ra) throws ValidationException {
		StudentEntity pessoaEntidadeDoDB = studentRepository.find(ra);
		validationBusiness.validation(student);		
		studentConversion.updateStudentEntity(student, pessoaEntidadeDoDB);			
		pessoaEntidadeDoDB.setDataUpdate((new Date()));
		studentRepository.save(pessoaEntidadeDoDB);	
	}

	@Override
	public List<T> find() {
		List<T> ret = new ArrayList<>();
		studentRepository.findAll().forEach(p-> ret.add(studentConversion.toStudentBase(p)));
		return ret;
	}

	@Override
	public Optional<T> find(@NotNull @Min(1)Long id) {		
		return studentRepository.findById(id).map(studentConversion::toStudentBase);
	}

		

	
}
