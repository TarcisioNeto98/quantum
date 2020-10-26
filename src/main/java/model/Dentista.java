package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name="Dentista")
public class Dentista {

	@Id
	@GeneratedValue
	private int id;
	private String nome;
	private String especialidade;
	
	public Dentista() {
		super();
	}

	public Dentista(String nome, String especialidade) {
		super();
		this.nome = nome;
		this.especialidade = especialidade;
	}

	public Dentista(int id, String nome, String especialidade) {
		super();
		this.id = id;
		this.nome = nome;
		this.especialidade = especialidade;
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

	public String getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}
	
}
