package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name="Usuario")
public class User {
	
	@Id
	@GeneratedValue
	int id;
	String nome;
	String email;
	String password;
	String endereco;
	String cep;
	String cidade, estado;
	
	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", password=" + password + ", endereco=" + endereco + ", cep=" + cep + ", cidade=" + cidade + ", estado=" + estado + "]";
	}
	
	public User() {
		super();
	}
	
	public User(int id, String nome, String email, String password, String endereco, String cep, String cidade, String estado) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.password = password;
		this.endereco = endereco;
		this.cep = cep;
		this.cidade = cidade;
		this.estado = estado;
	}
	
	public User(String nome, String login, String password, String endereco, String cep, String cidade, String estado) {
		super();
		this.nome = nome;
		this.email = login;
		this.password = password;
		this.endereco = endereco;
		this.cep = cep;
		this.cidade = cidade;
		this.estado = estado;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
}
