package model;

public class Consulta {
	int id;
	String data;
	int idPaciente;
	int idDentista;
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
	public Consulta(int id, String data, int idPaciente, int idDentista) {
		super();
		this.id = id;
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
