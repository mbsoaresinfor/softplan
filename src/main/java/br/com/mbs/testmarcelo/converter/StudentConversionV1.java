package br.com.mbs.testmarcelo.converter;



import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import br.com.mbs.testmarcelo.entity.StudentEntity;
import br.com.mbs.testmarcelo.vo.StudentV1;

@Component("v1")
public class StudentConversionV1 implements StudentConversion<StudentV1>{

	
	@Override
	public StudentEntity toStudentEntity(StudentV1 pessoaBase) {
		ModelMapper modelMapper = new ModelMapper();
		StudentEntity pessoaEntidade =modelMapper.map(pessoaBase, StudentEntity.class);	
		return pessoaEntidade;
	
	}	
	

	@Override
	public StudentV1 toStudentBase(StudentEntity pessaoEntidade) {
		ModelMapper modelMapper = new ModelMapper();
		StudentV1 studentV1 =modelMapper.map(pessaoEntidade, StudentV1.class);
		return studentV1;
	}

	@Override
	public void updateStudentEntity(StudentV1 studentV1, StudentEntity studentEntity) {
		studentEntity.setEmail(studentV1.getEmail());
		studentEntity.setName(studentV1.getName());
	}
	
	
	
}
