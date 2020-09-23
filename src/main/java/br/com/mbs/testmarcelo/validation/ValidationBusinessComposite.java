package br.com.mbs.testmarcelo.validation;

import java.util.ArrayList;
import java.util.List;
import javax.validation.ValidationException;
import org.springframework.stereotype.Component;

@Component
public class ValidationBusinessComposite
		implements ValidationBusiness {

	private List<ValidationBusiness> validations = new ArrayList<ValidationBusiness>();
	
	public void addValidationBusiness(ValidationBusiness validacao) {
		this.validations.add(validacao);
	}
	
	@Override
	public void validation(Object obj) throws ValidationException {
		
		for(ValidationBusiness validacao : validations) {
			validacao.validation(obj);
		}
	}

}
