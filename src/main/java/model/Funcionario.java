package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name="Funcionario")
public class Funcionario {
	
	@Id
	@GeneratedValue
	int id;
	String nome;
	String email;
	String endereco;
	String cidade;
	String estado;
	String cep;
	Double salario;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
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
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public Double getSalario() {
		return salario;
	}
	public void setSalario(Double salario) {
		this.salario = salario;
	}
	public Funcionario(int id, String nome, String email, String endereco, String cidade, String estado, String cep, Double salario) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.endereco = endereco;
		this.cidade = cidade;
		this.estado = estado;
		this.cep = cep;
		this.salario = salario;
	}
	
	public Funcionario(String nome, String email, String endereco, String cidade, String estado, String cep,
			Double salario) {
		super();
		this.nome = nome;
		this.email = email;
		this.endereco = endereco;
		this.cidade = cidade;
		this.estado = estado;
		this.cep = cep;
		this.salario = salario;
	}
	
	
	public Funcionario() {
		super();
	}
	
	@Override
	public String toString() {
		return "Funcionario [id=" + id + ", nome=" + nome + ", endereco=" + endereco + ", cidade=" + cidade
				+ ", estado=" + estado + ", cep=" + cep + ", salario=" + salario + "]";
	}
	
	
	
}
