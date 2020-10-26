package dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Paciente;

public class PacienteDAO {
	private static final Map<Integer, Paciente> userMap = new HashMap<Integer, Paciente>();
	static EntityManagerFactory factory = Persistence.createEntityManagerFactory("clinicaodontologica");
    static EntityManager manager;
	
	public static Paciente getPaciente(int id) {
		Paciente paciente = null;
		manager = factory.createEntityManager();
		paciente = manager.find(Paciente.class, id);
		manager.close();
		return paciente;
	}
	
	public static int quantidadePaciente() {
		manager = factory.createEntityManager();
		String quantidade = manager.createQuery("SELECT COUNT(*) FROM Paciente").getSingleResult().toString();
		manager.close();
		return Integer.parseInt(quantidade);
	}
	
	public static Paciente addPaciente(String nome, String email, String cidade, String estado, String cep) {
		Paciente paciente = new Paciente(nome, email, cidade, estado, cep);
		manager = factory.createEntityManager();
		manager.getTransaction().begin();
		manager.persist(paciente);
		manager.getTransaction().commit();
		manager.close();
		return paciente;
	}
	
	public static List<Paciente> getPacienteNomeEmail(String nome, String email){
		List<Paciente> pacientes = new ArrayList<Paciente>();
		
		manager = factory.createEntityManager();
		
		pacientes = manager.createQuery("from Paciente WHERE nome=?1 AND email=?2", Paciente.class).setParameter(1, nome).
		setParameter(2, email).getResultList();
		manager.close();
        
        return pacientes;
	}

	public static Paciente updatePaciente(int id, String nome, String email, String cidade, String estado, String cep) {
		Paciente paciente = new Paciente(id, nome, email, cidade, estado, cep);
		manager = factory.createEntityManager();
		manager.getTransaction().begin();
		manager.merge(paciente);
		manager.getTransaction().commit();
		manager.close();
		return paciente;
	}
	

	public static void deletePaciente(int id) {
		manager = factory.createEntityManager();
		
		Paciente paciente = manager.find(Paciente.class, id);
		
		manager.getTransaction().begin();
		manager.remove(paciente);
		manager.getTransaction().commit();
		manager.close();
	}

	public static List<Paciente> getAllPacientes() {
		return new ArrayList<Paciente>(userMap.values());
	}
}
