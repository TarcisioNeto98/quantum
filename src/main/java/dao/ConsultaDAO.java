package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Consulta;

public class ConsultaDAO {
	
	static EntityManagerFactory factory = Persistence.createEntityManagerFactory("clinicaodontologica");
    static EntityManager manager;
	
	public static Consulta getConsulta(int id) {
		manager = factory.createEntityManager();
		Consulta consulta = manager.find(Consulta.class, id);
		manager.close();
		return consulta;
	}
	
	public static int quantidadeConsulta() {
		manager = factory.createEntityManager();
		System.out.println(manager.createQuery("SELECT COUNT(*) FROM Consulta").getSingleResult());
        String quantidade = manager.createQuery("SELECT COUNT(*) FROM Consulta").getSingleResult().toString();
        
        manager.close();
        
        return Integer.parseInt(quantidade);
	}
	
	public static List<Consulta> getConsultaByData(String data){
		List<Consulta> consultas = null;
		manager = factory.createEntityManager();
		consultas = manager.createQuery("from Consulta WHERE data=?1", Consulta.class).setParameter(1, data).getResultList();
		manager.close();
		return consultas;
	}
	
	public static Consulta addConsulta(String valor, String data, int idPaciente, int idDentista) {
		
		Consulta consulta = new Consulta(valor, data, idPaciente, idDentista);
		 
        manager = factory.createEntityManager();
        manager.getTransaction().begin();
        manager.persist(consulta);
        manager.getTransaction().commit();
        manager.close();
        
        return consulta;
	}

	public static Consulta updateConsulta(int id, String valor, String data, int idPaciente, int idDentista) {
		Consulta consulta = new Consulta(id, valor, data, idPaciente, idDentista);
		
		manager = factory.createEntityManager();
		manager.getTransaction().begin();
		manager.merge(consulta);
		manager.getTransaction().commit();
		manager.close();
		return consulta;
		
	}

	public static Consulta deleteConsulta(int id) {
		manager = factory.createEntityManager();
		Consulta consulta = manager.find(Consulta.class, id);
		
		if(consulta == null) {
			manager.close();
			return consulta;
		}
		manager.getTransaction().begin();
		manager.remove(consulta);
		manager.getTransaction().commit();
		manager.close();
		return consulta;
	}

	public static List<Consulta> getAllConsultas() {
		List<Consulta> consultas = null;
		manager = factory.createEntityManager();
		consultas = manager.createQuery("FROM Consulta", Consulta.class).getResultList();
		manager.close();
		return consultas;
	}
}
