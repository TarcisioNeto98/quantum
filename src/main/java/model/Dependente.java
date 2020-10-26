package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name="Dependente")
public class Dependente {
	
	@Id
	@GeneratedValue
	int id;
	String nome;
	String cpf;
	int idFuncionario;
	
	public String getNome() {
		return nome;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public int getIdFuncionario() {
		return idFuncionario;
	}
	public void setIdFuncionario(int idFuncionario) {
		this.idFuncionario = idFuncionario;
	}
	public Dependente(int id, String nome, String cpf, int idFuncionario) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.idFuncionario = idFuncionario;
	}
	
	public Dependente() {
		super();
	}
	
	public Dependente(String nome, String cpf, int idFuncionario) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.idFuncionario = idFuncionario;
	}
	@Override
	public String toString() {
		return "Dependente [id=" + id + ", nome=" + nome + ", cpf=" + cpf + ", idFuncionario="
				+ idFuncionario + "]";
	}
	
	
}
