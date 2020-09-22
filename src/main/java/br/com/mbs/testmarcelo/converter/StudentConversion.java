package br.com.mbs.testmarcelo.converter;



import br.com.mbs.testmarcelo.entity.StudentEntity;

public interface StudentConversion <T>{

	public StudentEntity toStudentEntity(T studentBase)  ;
		
	public T toStudentBase(StudentEntity studentEntity);
	
	public void updateStudentEntity(T studentBase, StudentEntity studentEntityToUpdate) ;		
	
	
}
