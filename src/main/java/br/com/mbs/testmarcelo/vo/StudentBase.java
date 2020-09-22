package br.com.mbs.testmarcelo.vo;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class StudentBase implements Serializable{

	
	private static final long serialVersionUID = 1683187499986631724L;
	
	private Long ra;
	@NotBlank(message = "Field name it´s empty")
	private String name;
	@NotBlank(message = "Field email it´s empty")
	@Email(message = "Email invalid")
	private String email;
	@NotBlank(message = "Field cpf it´s empty")
	private String cpf;
	
	public Long getRa() {
		return ra;
	}
	public String getName() {
		return name;
	}
	public String getEmail() {
		return email;
	}
	public String getCpf() {
		return cpf;
	}
	public void setRa(Long ra) {
		this.ra = ra;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	@Override
	public String toString() {
		return "StudentBase [ra=" + ra + ", name=" + name + ", email=" + email + ", cpf=" + cpf + ", toString()="
				+ super.toString() + "]";
	}
	
	
	

}
