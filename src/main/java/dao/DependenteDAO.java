package dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Dependente;

public class DependenteDAO {
	private static final Map<Integer, Dependente> userMap = new HashMap<Integer, Dependente>();
	private static int i = 4;
	
	static {
		initDependentes();
	}
	
	private static void initDependentes() {
		Dependente dependente1 = new Dependente(1, "Maria Alice", "999.999.999-99", "Estudante", 2);
		Dependente dependente2 = new Dependente(2, "Maria Julia", "999.999.999-99", "Estudante", 2);
		Dependente dependente3 = new Dependente(3, "Maria Clara", "999.999.999-99", "Estudante", 2);
		
		userMap.put(dependente1.getId(), dependente1);
		userMap.put(dependente2.getId(), dependente2);
		userMap.put(dependente3.getId(), dependente3);
	}

	public static Dependente getDependente(int id) {
		return userMap.get(id);
	}

	public static Dependente addDependente(int id, String nome, String cpf, String cargo, int idFuncionario) {
		Dependente dependente = new Dependente(i, nome, cpf, cargo, idFuncionario);
		userMap.put(dependente.getId(), dependente);
		i++;
		return dependente;
	}

	public static Dependente updateDependente(int id, String nome, String cpf, String cargo, int idFuncionario) {
		Dependente dependente = new Dependente(id, nome, cpf, cargo, idFuncionario);
		userMap.put(dependente.getId(), dependente);
		return dependente;
	}

	public static void deleteDependente(int id) {
		if (userMap.containsKey(id)) {
			userMap.remove(id);
		}
	}

	public static List<Dependente> getAllDependentes() {
		return new ArrayList<Dependente>(userMap.values());
	}
}
