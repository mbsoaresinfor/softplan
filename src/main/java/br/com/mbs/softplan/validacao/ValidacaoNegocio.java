package br.com.mbs.softplan.validacao;



import br.com.mbs.softplan.exception.ValidacaoException;



public interface ValidacaoNegocio   {
	
	public void valida(Object  obj) throws ValidacaoException ;
	
	
}
