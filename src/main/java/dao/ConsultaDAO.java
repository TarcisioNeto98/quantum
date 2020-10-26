package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Consulta;
import util.DbUtil;

public class ConsultaDAO {
	private static final Map<Integer, Consulta> userMap = new HashMap<Integer, Consulta>();
	private static int i = 4;
	private static Connection connection = DbUtil.getConnection();
	
	static EntityManagerFactory factory = Persistence.createEntityManagerFactory("clinicaodontologica");
    static EntityManager manager;
	

	public static Consulta getConsulta(int id) {
		return userMap.get(id);
	}
	
	public static int quantidadeConsulta() {
		manager = factory.createEntityManager();
		System.out.println(manager.createQuery("SELECT COUNT(*) FROM Consulta").getSingleResult());
        String quantidade = manager.createQuery("SELECT COUNT(*) FROM Consulta").getSingleResult().toString();
        
        manager.close();
        
        return Integer.parseInt(quantidade);
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

	public static Consulta updateConsulta(int id, String data, int idPaciente, int idDentista) {
		Consulta consulta = null;
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
