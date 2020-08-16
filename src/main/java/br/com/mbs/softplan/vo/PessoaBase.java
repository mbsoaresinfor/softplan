package br.com.mbs.softplan.vo;

import java.io.Serializable;
import java.util.Date;

public class PessoaBase implements Serializable{

	
	private static final long serialVersionUID = 1683187499986631724L;
	
	private Long id;
	private String nome;
	private String sexo;
	private String email;
	private String dataNascimento;
	private String naturalidade;
	private String nacionalidade;
	private String cpf;
	
	
	public Long getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public String getSexo() {
		return sexo;
	}
	public String getEmail() {
		return email;
	}
	
	public String getNaturalidade() {
		return naturalidade;
	}
	public String getNacionalidade() {
		return nacionalidade;
	}
	public String getCpf() {
		return cpf;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setNaturalidade(String naturalidade) {
		this.naturalidade = naturalidade;
	}
	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	
	

}
