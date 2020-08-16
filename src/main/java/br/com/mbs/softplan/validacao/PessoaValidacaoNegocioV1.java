package br.com.mbs.softplan.validacao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.google.common.collect.PeekingIterator;

import br.com.mbs.softplan.exception.ValidacaoException;
import br.com.mbs.softplan.service.PessoaService;
import br.com.mbs.softplan.vo.PessoaV1;



public class PessoaValidacaoNegocioV1 implements ValidacaoNegocio {

	@Value("${formato.data}")
	private String formatoData = "dd/MM/yyyy";
	
	private PessoaService<?> pessoaService;
	
	public PessoaValidacaoNegocioV1() {}
	
	public PessoaValidacaoNegocioV1(PessoaService<?> pessoaService) {
		this.pessoaService = pessoaService;
	}
	
	@Override
	public void valida(Object obj) throws ValidacaoException {
		PessoaV1 pessoaV1 =  (PessoaV1)obj;
		validaNome(pessoaV1);
		validaEmail(pessoaV1);
		validaDataNascimento(pessoaV1);
		validaCpf(pessoaV1);
		
	}

	private void validaSePessoaExisteNoSistemaComCpf(PessoaV1 pessoaV1) throws ValidacaoException {
	
		boolean eCAdastro = pessoaV1.getId() == null;
		if(eCAdastro) {
			Optional<?> pessoa = pessoaService.buscar(pessoaV1.getCpf());
			if(pessoa.isPresent()) {
				throw new ValidacaoException("Já existe uma pessoa com este cpf " + pessoaV1.getCpf());
			}
		}
		
	}

	private void validaCpf(PessoaV1 pessoaV1) throws ValidacaoException {
		validaFormatoCpf(pessoaV1);
		validaSePessoaExisteNoSistemaComCpf(pessoaV1);			
	}

	private void validaFormatoCpf(PessoaV1 pessoaV1) throws ValidacaoException {
		// validacao simples de cpf
		int tamanhoMaxCaracCpf = 11;
		String cpf = pessoaV1.getCpf();
		boolean eCpfValidoInvalido = !eNumero(cpf) || (cpf != null && cpf.length() != tamanhoMaxCaracCpf);
		if(eCpfValidoInvalido) {
			throw new ValidacaoException("Campo CPF da pessoa, deve ser preenchida com um valor "
					+ "válido com somente 11 números");
		}
	}

	private boolean eNumero(String numero) {
		boolean ret = false;
		try {
			Double.parseDouble(numero);
			ret = true;
		}catch(Exception e) {			
		}
		return ret;
	}
	
	private void validaDataNascimento(PessoaV1 pessoaV1) throws ValidacaoException {
		try {
			new SimpleDateFormat(formatoData).parse(pessoaV1.getDataNascimento());
		} catch (ParseException e) {
			throw new ValidacaoException("Campo data de nascimento da pessoa esta no formato inválido."
					+ "\nFormato válido: " + formatoData);
		}
		
	}

	private void validaEmail(PessoaV1 pessoaV1) throws ValidacaoException {
	    boolean estaPreechida = pessoaV1.getEmail() != null &&  !"".equals(pessoaV1.getEmail());
		if(estaPreechida) {
		    try {
	            InternetAddress emailAddr = new InternetAddress(pessoaV1.getEmail());
	            emailAddr.validate();
	        } catch (AddressException ex) {
	        	throw new ValidacaoException("Campo email da pessoa deve ser preenchido com um valor válido");
	    	
	        }
		}
	}

	private void validaNome(PessoaV1 pessoaV1) throws ValidacaoException {
		boolean nomeInvalido =pessoaV1.getNome() == null || "".equals(pessoaV1.getNome());
		if(nomeInvalido){
			throw new ValidacaoException("Campo nome da pessoa deve ser preenchido");
		}
		
	}

}
