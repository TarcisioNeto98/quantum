package model;

public class Dependente {
	int id;
	String nome;
	String cpf;
	String cargo;
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
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public int getIdFuncionario() {
		return idFuncionario;
	}
	public void setIdFuncionario(int idFuncionario) {
		this.idFuncionario = idFuncionario;
	}
	public Dependente(int id, String nome, String cpf, String cargo, int idFuncionario) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.cargo = cargo;
		this.idFuncionario = idFuncionario;
	}
	@Override
	public String toString() {
		return "Dependente [id=" + id + ", nome=" + nome + ", cpf=" + cpf + ", cargo=" + cargo + ", idFuncionario="
				+ idFuncionario + "]";
	}
	
	
}
