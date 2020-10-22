package dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Paciente;

public class PacienteDAO {
	private static final Map<Integer, Paciente> userMap = new HashMap<Integer, Paciente>();
	private static int i = 4;

	static {
		initPacientes();
	}

	private static void initPacientes() {
		Paciente paciente1 = new Paciente(1, "Carlos", "carlaweb@bol.com.br", "Sobral", "Ceará", "63700-866");
		Paciente paciente2 = new Paciente(2, "Paula", "paulinha@yahoo.com.br", "Crato", "Ceará", "63420-888");
		Paciente paciente3 = new Paciente(3, "João", "jao17@gmail.com", "Picos", "Piauí", "63735-869");

		userMap.put(paciente1.getId(), paciente1);
		userMap.put(paciente2.getId(), paciente2);
		userMap.put(paciente3.getId(), paciente3);
	}

	public static Paciente getPaciente(int id) {
		return userMap.get(id);
	}

	public static Paciente addPaciente(String nome, String email, String cidade, String estado, String cep) {
		Paciente paciente = new Paciente(i, nome, email, cidade, estado, cep);
		userMap.put(paciente.getId(), paciente);
		i++;
		return paciente;
	}

	public static Paciente updatePaciente(int id, String nome, String email, String cidade, String estado, String cep) {
		Paciente paciente = new Paciente(id, nome, email, cidade, estado, cep);
		userMap.put(paciente.getId(), paciente);
		return paciente;
	}

	public static void deletePaciente(int id) {
		if (userMap.containsKey(id)) {
			userMap.remove(id);
		}
	}

	public static List<Paciente> getAllPacientes() {
		return new ArrayList<Paciente>(userMap.values());
	}
}
