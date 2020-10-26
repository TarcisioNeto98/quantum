package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Dentista;
import util.DbUtil;


public class DentistaDAO {
	
	private static Connection connection = DbUtil.getConnection();
	static EntityManagerFactory factory = Persistence.createEntityManagerFactory("clinicaodontologica");
    static EntityManager manager;
    
	public static Dentista addDentista(String nome, String especialidade) {
		Dentista dentista = new Dentista(nome, especialidade);
		
		manager = factory.createEntityManager();
        manager.getTransaction().begin();
        manager.persist(dentista);
        manager.getTransaction().commit();
        manager.close();
        
        return dentista;
	}
	
	public static List<Dentista> getAllDentistas() {
        List<Dentista> dentistas = null;
        
        manager = factory.createEntityManager();
        dentistas = manager.createQuery("FROM Dentista", Dentista.class).getResultList();
        manager.close();
        return dentistas;
    }
}
