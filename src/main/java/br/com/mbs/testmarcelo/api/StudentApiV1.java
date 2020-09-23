package br.com.mbs.testmarcelo.api;

import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.ValidationException;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.mbs.testmarcelo.service.StudentService;
import br.com.mbs.testmarcelo.vo.StudentV1;
import io.swagger.annotations.Api;


@RestController(value="API for maintenance student version 1")
@Api(description="Api student version 1")
@RequestMapping("/v1/student")
public class StudentApiV1 implements StudentApi<StudentV1>{


	@Autowired
	private StudentService<StudentV1> studentServiceV1;
	

	@Override
	public ResponseEntity<String> save(@NotNull StudentV1 student) {
		
		 ResponseEntity<String> ret = null;
		 try {
			 Long ra = studentServiceV1.save(student);
			 ret =  new ResponseEntity<>( ra.toString(),HttpStatus.OK);
		 }catch(ValidationException e) {
			 ret = new ResponseEntity<>(e.getMessage(),HttpStatus.METHOD_NOT_ALLOWED);
		 }
		 return ret;
	
	}

	@Override
	public List<StudentV1> getStudents() {		
		return studentServiceV1.find();
	}

	@Override
	public ResponseEntity<StudentV1> getStudent(@NotNull @Min(1) Long id)  {		
		ResponseEntity<StudentV1> responseEntity;
		Optional<StudentV1> optStudentV1 = studentServiceV1.find(id);
		if(optStudentV1.isPresent()){		
			responseEntity = new ResponseEntity<>(optStudentV1.get(),HttpStatus.OK);			
		 }else {
			 responseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND).build();			
		}
		return responseEntity;
	}

	@Override
	public ResponseEntity<Void> delete( @NotNull @Min(1)Long id) {
		ResponseEntity<Void> responseEntity;
		 try {
			 studentServiceV1.delete(id);
			  responseEntity = ResponseEntity.ok().build();
		 }catch( ValidationException e) {
			 responseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		 }
		 return responseEntity;
		
	}

	@Override
	public ResponseEntity<String> update(@NotNull StudentV1 student) {
		 ResponseEntity<String> ret = null;
		 try {
			 studentServiceV1.update(student,student.getRa());
			 ret = ResponseEntity.ok().build();
		 }catch(ValidationException  e) {		
			 ret = new ResponseEntity<>(e.getMessage(),HttpStatus.METHOD_NOT_ALLOWED);
		 }
		 return ret;
	}
	

}
