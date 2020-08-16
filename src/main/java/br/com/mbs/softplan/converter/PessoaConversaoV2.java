package br.com.mbs.softplan.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.com.mbs.softplan.entidade.PessoaEntidade;
import br.com.mbs.softplan.exception.ValidacaoException;
import br.com.mbs.softplan.service.PessoaService;
import br.com.mbs.softplan.vo.PessoaV1;
import br.com.mbs.softplan.vo.PessoaV2;

@Component("v2")
public class PessoaConversaoV2 implements PessoaConversao<PessoaV2>{

	@Value("${formato.data}")
	private String formatoData = "dd/MM/yyyy";
	
	@Override
	public PessoaEntidade paraPessoaEntidade(PessoaV2 pessoaBase) {
		PessoaEntidade pessoaEntidade = null;
		try {
			pessoaEntidade = processaParaPessoaEntidade(pessoaBase);
		}catch(Exception e) {
			throw new IllegalArgumentException("Ocorreu um erro para transformar " +
						PessoaV1.class.getSimpleName(),e);
		}
		return pessoaEntidade;
	
	}
	
	private  PessoaEntidade processaParaPessoaEntidade(PessoaV2 pessoaV2) throws ParseException  {
		PessoaEntidade pessoaEntidade = new PessoaEntidade();
		pessoaEntidade.setCpf(pessoaV2.getCpf());
		Date date1=new SimpleDateFormat(formatoData).parse(pessoaV2.getDataNascimento()); 
		pessoaEntidade.setDataNascimento(date1);
		pessoaEntidade.setEmail(pessoaV2.getEmail());
		pessoaEntidade.setId(pessoaV2.getId() == null ? 0l : pessoaV2.getId());
		pessoaEntidade.setNacionalidade(pessoaV2.getNacionalidade());
		pessoaEntidade.setNaturalidade(pessoaV2.getNaturalidade());
		pessoaEntidade.setNome(pessoaV2.getNome());
		pessoaEntidade.setSexo(pessoaV2.getSexo());
		pessoaEntidade.setEndereco(pessoaV2.getEndereco());
		
		return pessoaEntidade;
	}
	

	@Override
	public PessoaV2 paraPessoaBase(PessoaEntidade pessaoEntidade) {
		PessoaV2 pessoaV2 = new PessoaV2();
		pessoaV2.setCpf(pessaoEntidade.getCpf());
		String date =new SimpleDateFormat(formatoData).format(pessaoEntidade.getDataNascimento()); 	
		pessoaV2.setDataNascimento(date);
		pessoaV2.setEmail(pessaoEntidade.getEmail());
		pessoaV2.setId(pessaoEntidade.getId() );
		pessoaV2.setNacionalidade(pessaoEntidade.getNacionalidade());
		pessoaV2.setNaturalidade(pessaoEntidade.getNaturalidade());
		pessoaV2.setNome(pessaoEntidade.getNome());
		pessoaV2.setSexo(pessaoEntidade.getSexo());
		pessoaV2.setEndereco(pessaoEntidade.getEndereco());
		
		return pessoaV2;
	}

	@Override
	public void atualizaPessoaEntidade(PessoaV2 pessoaBase, PessoaEntidade pessoaEntdadeParaAtualizar) {
		try {
			this.processaAtualizaPessoaEntidade(pessoaBase, pessoaEntdadeParaAtualizar);
		}catch (Exception e) {
			throw new IllegalArgumentException("Error na atualização da " + pessoaEntdadeParaAtualizar.getClass().getSimpleName());
		}
	}
	
	private void processaAtualizaPessoaEntidade(PessoaV1 pessoa, PessoaEntidade pessoaParaAtualizar) throws ParseException {
		pessoaParaAtualizar.setCpf(pessoa.getCpf());
		Date date1=new SimpleDateFormat(formatoData).parse(pessoa.getDataNascimento()); 
		pessoaParaAtualizar.setDataNascimento(date1);
		pessoaParaAtualizar.setEmail(pessoa.getEmail());
		pessoaParaAtualizar.setNacionalidade(pessoa.getNacionalidade());
		pessoaParaAtualizar.setNaturalidade(pessoa.getNaturalidade());
		pessoaParaAtualizar.setNome(pessoa.getNome());
		pessoaParaAtualizar.setSexo(pessoa.getSexo());		
		pessoaParaAtualizar.setEndereco(pessoaParaAtualizar.getEndereco());
	}

	

}
