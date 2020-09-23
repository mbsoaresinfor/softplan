package br.com.mbs.testmarcelo.validation;

import java.util.Set;
import javax.validation.*;
import br.com.mbs.testmarcelo.service.StudentService;
import br.com.mbs.testmarcelo.vo.StudentV1;

public class StudentValidationBusinessV1 implements ValidationBusiness {

	
	private StudentService<?> pessoaService;
	
	public StudentValidationBusinessV1() {}
	
	public StudentValidationBusinessV1(StudentService<?> pessoaService) {
		this.pessoaService = pessoaService;
	}
	
	@Override
	public void validation(Object obj) throws ValidationException {
		// cases special of validation.		
		// its hook, call by StudentService
		StudentV1 studentV1 = (StudentV1)obj;
		
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	    Validator validator = factory.getValidator();
	    Set<ConstraintViolation<StudentV1>> violations = validator.validate(studentV1);
	    if (!violations.isEmpty()) {
	    	 StringBuilder messageError = new StringBuilder();
	 	    for (ConstraintViolation<StudentV1> violation : violations) {
	 	         messageError.append(violation.getMessage());
	 	    	messageError.append("\n");
	 	    }
	 	 
	      throw new ValidationException(messageError.toString());
	    }		
	}
	
}
