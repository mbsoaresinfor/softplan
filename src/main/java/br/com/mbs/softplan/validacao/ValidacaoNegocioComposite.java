package br.com.mbs.softplan.validacao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.mbs.softplan.exception.ValidacaoException;

@Component
public class ValidacaoNegocioComposite
		implements ValidacaoNegocio {

	private List<ValidacaoNegocio> validacoes = new ArrayList<ValidacaoNegocio>();
	
	public void adicionaValidacaoNegocio(ValidacaoNegocio validacao) {
		this.validacoes.add(validacao);
	}
	
	@Override
	public void valida(Object obj) throws ValidacaoException {
		
		for(ValidacaoNegocio validacao : validacoes) {
			validacao.valida(obj);
		}
	}

}
