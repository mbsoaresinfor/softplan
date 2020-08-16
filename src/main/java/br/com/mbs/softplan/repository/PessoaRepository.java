package br.com.mbs.softplan.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import br.com.mbs.softplan.entidade.PessoaEntidade;
import br.com.mbs.softplan.exception.ValidacaoException;

public interface PessoaRepository extends 
		CrudRepository<PessoaEntidade,Long>{

	List<PessoaEntidade> findByCpf(String cpf);
	
	default PessoaEntidade buscar(Long idPessoa) throws ValidacaoException {
			Optional<PessoaEntidade> pessoaEntidade = findById(idPessoa);
			boolean naoExiste = pessoaEntidade.isPresent() == false;
			if(naoExiste) {
				throw new ValidacaoException("Pessoa com id " + idPessoa + " não existe");
			}
			return pessoaEntidade.get();
	}
	
}
