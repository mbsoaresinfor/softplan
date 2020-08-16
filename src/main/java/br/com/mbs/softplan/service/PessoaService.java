package br.com.mbs.softplan.service;

import java.util.List;
import java.util.Optional;

import br.com.mbs.softplan.converter.PessoaConversao;
import br.com.mbs.softplan.exception.ValidacaoException;
import br.com.mbs.softplan.validacao.ValidacaoNegocio;


public interface PessoaService<T> {

	Long salvar(T pessoa) throws ValidacaoException;
	
	void deletar(Long idPessoa) throws ValidacaoException;
	
	void atualizar(T pessoa,Long idPessoa) throws ValidacaoException;
	
	List<T> buscar();
	
	Optional<T> buscar(Long idPessoa);
	
	void setConversao(PessoaConversao<T> pessoaConversao);
	
	void setValidacao(ValidacaoNegocio validacaoNegocio);

	Optional<T> buscar(String cpf);
	
	
}
