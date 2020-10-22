package model;

public class Paciente {
	int id;
	String nome;
	String email;
	String cidade;
	String estado;
	String cep;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public Paciente(int id, String nome, String email, String cidade, String estado, String cep) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.cidade = cidade;
		this.estado = estado;
		this.cep = cep;
	}
	@Override
	public String toString() {
		return "Paciente [id=" + id + ", nome=" + nome + ", email=" + email + ", cidade=" + cidade + ", estado="
				+ estado + ", cep=" + cep + "]";
	}
	
	
	
}
