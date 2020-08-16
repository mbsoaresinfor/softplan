package br.com.mbs.softplan.validacao;


import br.com.mbs.softplan.exception.ValidacaoException;
import br.com.mbs.softplan.vo.PessoaV2;


public class PessoaValidacaoNegocioV2 
			implements ValidacaoNegocio {

	@Override
	public void valida(Object obj) throws ValidacaoException {
		
		PessoaV2 p2 = (PessoaV2)obj;
		validaEndereco(p2);
		
	}

	private void validaEndereco(PessoaV2 p2) throws ValidacaoException {
		boolean enderecoInvalido = p2.getEndereco() == null || "".equals(p2.getEndereco());
		if(enderecoInvalido) {
			throw new ValidacaoException("Campo endereco da pessoa deve ser preenchido com um valor.");
	    	
		}
		
		
	}




	
	
	

	

}
