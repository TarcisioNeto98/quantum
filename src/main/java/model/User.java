package model;

public class User {
	int id;
	String nome;
	String login;
	String password;
	String endereco;
	String cep;
	String cidade, estado;
	
	@Override
	public String toString() {
		return "User [id=" + id + ", login=" + login + ", password=" + password + ", endereco=" + endereco + ", cep=" + cep + ", cidade=" + cidade + ", estado=" + estado + "]";
	}
	
	public User(int id, String nome, String login, String password, String endereco, String cep, String cidade, String estado) {
		super();
		this.id = id;
		this.nome = nome;
		this.login = login;
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
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
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
