package br.com.mbs.testmarcelo.validation;

import javax.validation.ValidationException;

public interface ValidationBusiness   {
	
	public void validation(Object  obj) throws ValidationException ;
	
	
}
