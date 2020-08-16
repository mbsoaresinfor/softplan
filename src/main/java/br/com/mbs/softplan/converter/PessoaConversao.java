package br.com.mbs.softplan.converter;



import br.com.mbs.softplan.entidade.PessoaEntidade;

public interface PessoaConversao <T>{

	public PessoaEntidade paraPessoaEntidade(T pessoaBase)  ;
		
	public T paraPessoaBase(PessoaEntidade pessaoEntidade);
	
	public void atualizaPessoaEntidade(T pessoaBase, PessoaEntidade pessoaEntdadeParaAtualizar) ;		
	
	
}
