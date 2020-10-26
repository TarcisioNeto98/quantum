package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name="Consulta")
public class Consulta {
	
	@Id
	@GeneratedValue
	int id;
	String data;
	String valor;
	int idPaciente;
	int idDentista;
	
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public int getIdPaciente() {
		return idPaciente;
	}
	public void setIdPaciente(int idPaciente) {
		this.idPaciente = idPaciente;
	}
	public int getIdDentista() {
		return idDentista;
	}
	public void setIdDentista(int idDentista) {
		this.idDentista = idDentista;
	}
	
	public Consulta() {
		super();
	}
	public Consulta(String data, String valor, int idPaciente, int idDentista) {
		super();
		this.data = data;
		this.valor = valor;
		this.idPaciente = idPaciente;
		this.idDentista = idDentista;
	}
	public Consulta(int id,String valor, String data, int idPaciente, int idDentista) {
		super();
		this.id = id;
		this.valor = valor;
		this.data = data;
		this.idPaciente = idPaciente;
		this.idDentista = idDentista;
	}
	@Override
	public String toString() {
		return "Consulta [id=" + id + ", data=" + data + ", idPaciente=" + idPaciente + ", idDentista=" + idDentista
				+ "]";
	}	
}
