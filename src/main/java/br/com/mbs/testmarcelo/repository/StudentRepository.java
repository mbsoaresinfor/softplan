package br.com.mbs.testmarcelo.repository;


import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import br.com.mbs.testmarcelo.entity.StudentEntity;
import br.com.mbs.testmarcelo.exception.ValidationBusinessException;

public interface StudentRepository extends 
		CrudRepository<StudentEntity,Long>{

	
	default StudentEntity find(Long ra) throws ValidationBusinessException {
			if(ra == null) {
				throw new ValidationBusinessException("RA not found");
			}
			Optional<StudentEntity> studentEntity = findById(ra);
			boolean thereIsNot = studentEntity.isPresent() == false;
			if(thereIsNot) {
				throw new ValidationBusinessException("Student ra " + ra + " there is not.");
			}
			return studentEntity.get();
	}
	
}
