package model;

import java.sql.Date;

public class Cadastro {

	private Integer id;
	private String nome;
	private String email;
	private String telefone;
	private String cep;
	private Boolean avisoEmail;
	private Boolean avisoWhatsapp;
	private Date data;

	public Cadastro() {
		super();
	}

	public Cadastro(Integer id, String nome, String email, String telefone, String cep, String avisoEmail,
			String avisoWhatsapp, Date data) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.cep = cep;
		this.data = data;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Boolean getAvisoEmail() {
		return avisoEmail;
	}

	public void setAvisoEmail(Boolean avisoEmail) {
		this.avisoEmail = avisoEmail;
	}

	public Boolean getAvisoWhatsapp() {
		return avisoWhatsapp;
	}

	public void setAvisoWhatsapp(Boolean avisoWhatsapp) {
		this.avisoWhatsapp = avisoWhatsapp;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

}
