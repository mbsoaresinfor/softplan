package br.com.mbs.testmarcelo.validation;



import br.com.mbs.testmarcelo.exception.ValidationBusinessException;



public interface ValidationBusiness   {
	
	public void validation(Object  obj) throws ValidationBusinessException ;
	
	
}
