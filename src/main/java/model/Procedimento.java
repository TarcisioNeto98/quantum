package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name="Procedimento")
public class Procedimento {
	@Id
	@GeneratedValue
	int id;
	String nome;
	double valor;
	String descricao;
	
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
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public Procedimento() {
		super();
	}
	
	public Procedimento(String nome, double valor, String descricao) {
		super();
		this.nome = nome;
		this.valor = valor;
		this.descricao = descricao;
	}
	public Procedimento(int id, String nome, double valor, String descricao) {
		super();
		this.id = id;
		this.nome = nome;
		this.valor = valor;
		this.descricao = descricao;
	}
	@Override
	public String toString() {
		return "Procedimento [id=" + id + ", nome=" + nome + ", valor=" + valor + ", descricao=" + descricao + "]";
	}
	
	
}
