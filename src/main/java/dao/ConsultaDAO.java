package dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Consulta;

public class ConsultaDAO {
	private static final Map<Integer, Consulta> userMap = new HashMap<Integer, Consulta>();
	private static int i = 4;
	
	static {
		initConsultas();
	}
	
	private static void initConsultas() {
		Consulta consulta1 = new Consulta(1, "12-10-2019", 1, 2);
		Consulta consulta2 = new Consulta(2, "07-04-2020", 2, 4);
		Consulta consulta3 = new Consulta(3, "19-04-2020", 3, 4);

		userMap.put(consulta1.getId(), consulta1);
		userMap.put(consulta2.getId(), consulta2);
		userMap.put(consulta3.getId(), consulta3);
	}

	public static Consulta getConsulta(int id) {
		return userMap.get(id);
	}

	public static Consulta addConsulta(String data, int idPaciente, int idDentista) {
		Consulta consulta = new Consulta(i, data, idPaciente, idDentista);
		userMap.put(consulta.getId(), consulta);
		i++;
		return consulta;
	}

	public static Consulta updateConsulta(int id, String data, int idPaciente, int idDentista) {
		Consulta consulta = new Consulta(id, data, idPaciente, idDentista);
		userMap.put(consulta.getId(), consulta);
		return consulta;
	}

	public static void deleteConsulta(int id) {
		if (userMap.containsKey(id)) {
			userMap.remove(id);
		}
	}

	public static List<Consulta> getAllConsultas() {
		return new ArrayList<Consulta>(userMap.values());
	}
}
