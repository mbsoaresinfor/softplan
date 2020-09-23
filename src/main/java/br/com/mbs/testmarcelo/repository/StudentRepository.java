package br.com.mbs.testmarcelo.repository;


import java.util.Optional;
import javax.validation.ValidationException;
import org.springframework.data.repository.CrudRepository;
import br.com.mbs.testmarcelo.entity.StudentEntity;


public interface StudentRepository extends 
		CrudRepository<StudentEntity,Long>{

	
	default StudentEntity find(Long ra) throws ValidationException {
			if(ra == null) {
				throw new ValidationException("RA not found");
			}
			Optional<StudentEntity> studentEntity = findById(ra);
			boolean thereIsNot = studentEntity.isPresent() == false;
			if(thereIsNot) {
				throw new ValidationException("Student ra " + ra + " there is not.");
			}
			return studentEntity.get();
	}
	
}
