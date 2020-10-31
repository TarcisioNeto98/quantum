package dao;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import model.User;

public class UserDAO {
    
    static EntityManagerFactory factory = Persistence.createEntityManagerFactory("clinicaodontologica");
    static EntityManager manager;

	public static User addUser(String nome, String login, String password, String endereco, String cep, String cidade, String estado) {
		User usuario = new User(nome, login, password, endereco, cep, cidade, estado);
        manager = factory.createEntityManager();
        
		try{
			User user = manager.createQuery("from Usuario WHERE password=?1", User.class).setParameter(1, password).getSingleResult();
			if(user != null) return null;
		}catch(Exception e) {
		}
		
        manager.getTransaction().begin();
        manager.persist(usuario);
        manager.getTransaction().commit();
        manager.close();
        return usuario;
	}
	
	public static User getUserByLoginAndPassword(String email, String password) {
		manager = factory.createEntityManager();
		User usuario = null;
		try{
			usuario = manager.createQuery("from Usuario WHERE email=?1 AND password=?2", User.class).setParameter(1, email).
			setParameter(2, password).getSingleResult();
			manager.close();
		}catch(NoResultException e) {
			
		}
		return usuario;
	}
}
