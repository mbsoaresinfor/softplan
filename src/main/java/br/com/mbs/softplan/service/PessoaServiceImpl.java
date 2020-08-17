package br.com.mbs.softplan.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.mbs.softplan.converter.PessoaConversao;
import br.com.mbs.softplan.entidade.PessoaEntidade;
import br.com.mbs.softplan.exception.ValidacaoException;
import br.com.mbs.softplan.repository.PessoaRepository;
import br.com.mbs.softplan.validacao.ValidacaoNegocio;

@Service
public class PessoaServiceImpl<T> implements PessoaService<T> {

	@Autowired
	private PessoaRepository pessoaRepository;
	
	private PessoaConversao<T> pessoaConversao;
	
	private ValidacaoNegocio validacaoNegocio;
	
	@Override
	public void setValidacao(ValidacaoNegocio validacaoNegocio) {
		this.validacaoNegocio = validacaoNegocio;		
	}
	
	@Override
	public void setConversao(PessoaConversao<T> pessoaConversao) {
		this.pessoaConversao = pessoaConversao;
	}
	
	@Override
	public Long salvar(T pessoa) throws ValidacaoException {
		validacaoNegocio.valida(pessoa);
		PessoaEntidade pessoaEntidade = pessoaConversao.paraPessoaEntidade(pessoa);
		pessoaEntidade.setDataCadastro(new Date());
		return pessoaRepository.save(pessoaEntidade).getId();
	}

	@Override
	public void deletar(Long idPessoa) throws ValidacaoException {
		PessoaEntidade pessoaEntidade = pessoaRepository.buscar(idPessoa);
		pessoaRepository.deleteById(pessoaEntidade.getId());	
	}

	@Override
	public void atualizar(T pessoa,Long idPessoa) throws ValidacaoException {
		PessoaEntidade pessoaEntidadeDoDB = pessoaRepository.buscar(idPessoa);
		validacaoNegocio.valida(pessoa);		
		pessoaConversao.atualizaPessoaEntidade(pessoa, pessoaEntidadeDoDB);			
		pessoaEntidadeDoDB.setDataAtualizacao(new Date());
		pessoaRepository.save(pessoaEntidadeDoDB);	
	}

	@Override
	public List<T> buscar() {
		List<T> ret = new ArrayList<>();
		pessoaRepository.findAll().forEach(p-> ret.add(pessoaConversao.paraPessoaBase(p)));
		return ret;
	}

	@Override
	public Optional<T> buscar(Long idPessoa) {		
		return pessoaRepository.findById(idPessoa).map(pessoaConversao::paraPessoaBase);
	}

	@Override
	public Optional<T> buscar(String cpf) {
		Optional<T> opt = null;
		List<PessoaEntidade> ret  =  pessoaRepository.findByCpf(cpf);
		boolean temPessoaEntidade = ret != null && ret.size() > 0;
		if(temPessoaEntidade) {
			opt =  Optional.of(pessoaConversao.paraPessoaBase(ret.get(0)));
		}else {
			opt = Optional.empty();
		}
		return opt;
	}

	

	
}
