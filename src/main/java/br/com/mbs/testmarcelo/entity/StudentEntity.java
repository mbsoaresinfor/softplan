package br.com.mbs.testmarcelo.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "student")
public class StudentEntity implements Serializable {

	
	private static final long serialVersionUID = 6473054288847669735L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long ra;
	
	@NotBlank(message = "Field name it´s empty")
	@Column(name = "name")
	private String name;
	
	@NotBlank(message = "Field email it´s empty")
	@Email
	@Column(name = "email")
	private String email;
	
	@NotBlank(message = "Field cpf it´s empty")
	@Column(name = "cpf")
	private String cpf;
	
	@Column(name = "dateSave")
	private Date dateSave;
	
	@Column(name = "dateUpdate")
	private Date dataUpdate;

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

	public Date getDateSave() {
		return dateSave;
	}

	public Date getDataUpdate() {
		return dataUpdate;
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

	public void setDateSave(Date dateSave) {
		this.dateSave = dateSave;
	}

	public void setDataUpdate(Date dataUpdate) {
		this.dataUpdate = dataUpdate;
	}

	@Override
	public String toString() {
		return "StudentEntity [ra=" + ra + ", name=" + name + ", email=" + email + ", cpf=" + cpf + ", dateSave="
				+ dateSave + ", dataUpdate=" + dataUpdate + "]";
	}
	
	

	
	
	
	
}
