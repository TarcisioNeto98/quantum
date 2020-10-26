package dao;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Dependente;

public class DependenteDAO {
	private static final Map<Integer, Dependente> userMap = new HashMap<Integer, Dependente>();
	
	static EntityManagerFactory factory = Persistence.createEntityManagerFactory("clinicaodontologica");
    static EntityManager manager;
    
	public static Dependente getDependente(int id) {
		return userMap.get(id);
	}

	public static Dependente addDependente(String nome, String cpf, int idFuncionario) {
		Dependente dependente = new Dependente(nome, cpf, idFuncionario);
		manager = factory.createEntityManager();
		manager.getTransaction().begin();
		manager.persist(dependente);
		manager.getTransaction().commit();
		manager.close();
		
		return dependente;
	}

	public static Dependente updateDependente(int id, String nome, String cpf, String cargo, int idFuncionario) {
		
		return null;
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
