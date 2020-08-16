package br.com.mbs.softplan.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.com.mbs.softplan.entidade.PessoaEntidade;
import br.com.mbs.softplan.vo.PessoaV1;

@Component("v1")
public class PessoaConversaoV1 implements PessoaConversao<PessoaV1>{

	@Value("${formato.data}")
	private String formatoData = "dd/MM/yyyy";
	
	@Override
	public PessoaEntidade paraPessoaEntidade(PessoaV1 pessoaBase) {
		PessoaEntidade pessoaEntidade = null;
		try {
			pessoaEntidade = processaParaPessoaEntidade(pessoaBase);
		}catch(Exception e) {
			throw new IllegalArgumentException("Ocorreu um erro para transformar " +
						PessoaV1.class.getSimpleName(),e);
		}
		return pessoaEntidade;
	
	}
	
	private  PessoaEntidade processaParaPessoaEntidade(PessoaV1 pessoaV1) throws ParseException  {
		PessoaEntidade pessoaEntidade = new PessoaEntidade();
		pessoaEntidade.setCpf(pessoaV1.getCpf());
		Date date1=new SimpleDateFormat(formatoData).parse(pessoaV1.getDataNascimento()); 
		pessoaEntidade.setDataNascimento(date1);
		pessoaEntidade.setEmail(pessoaV1.getEmail());
		pessoaEntidade.setId(pessoaV1.getId() == null ? 0l : pessoaV1.getId());
		pessoaEntidade.setNacionalidade(pessoaV1.getNacionalidade());
		pessoaEntidade.setNaturalidade(pessoaV1.getNaturalidade());
		pessoaEntidade.setNome(pessoaV1.getNome());
		pessoaEntidade.setSexo(pessoaV1.getSexo());
		
		return pessoaEntidade;
	}
	

	@Override
	public PessoaV1 paraPessoaBase(PessoaEntidade pessaoEntidade) {
		PessoaV1 pessoaV1 = new PessoaV1();
		pessoaV1.setCpf(pessaoEntidade.getCpf());
		String date =new SimpleDateFormat(formatoData).format(pessaoEntidade.getDataNascimento()); 	
		pessoaV1.setDataNascimento(date);
		pessoaV1.setEmail(pessaoEntidade.getEmail());
		pessoaV1.setId(pessaoEntidade.getId() );
		pessoaV1.setNacionalidade(pessaoEntidade.getNacionalidade());
		pessoaV1.setNaturalidade(pessaoEntidade.getNaturalidade());
		pessoaV1.setNome(pessaoEntidade.getNome());
		pessoaV1.setSexo(pessaoEntidade.getSexo());
		
		return pessoaV1;
	}

	@Override
	public void atualizaPessoaEntidade(PessoaV1 pessoaBase, PessoaEntidade pessoaEntdadeParaAtualizar) {
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
	}

	
	
}
