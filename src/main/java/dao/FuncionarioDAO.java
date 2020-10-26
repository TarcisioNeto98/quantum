package dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Funcionario;

public class FuncionarioDAO {
	private static final Map<Integer, Funcionario> userMap = new HashMap<Integer, Funcionario>();
	
	static EntityManagerFactory factory = Persistence.createEntityManagerFactory("clinicaodontologica");
    static EntityManager manager;

	public static Funcionario getFuncionario(int id) {
		Funcionario funcionario = null;
		
		manager = factory.createEntityManager();
		funcionario = manager.find(Funcionario.class, id);
		manager.close();
		return funcionario;
	}
	
	public static int quantidadeFuncionario() {
		manager = factory.createEntityManager();
        String quantidade = manager.createQuery("SELECT COUNT(*) FROM Funcionario").getSingleResult().toString();
        manager.close();
        
        return Integer.parseInt(quantidade);
	}
	
	public static Funcionario addFuncionario(String nome, String email, String endereco, String cidade, String estado, String cep, 
		String salario) {
		Funcionario funcionario = new Funcionario(nome, email, endereco, cidade, estado, cep, Double.parseDouble(salario));
		 
        manager = factory.createEntityManager();
        manager.getTransaction().begin();
        manager.persist(funcionario);
        manager.getTransaction().commit();
        manager.close();
        
        return funcionario;
	}
	
	public static List<Funcionario> getFuncionarioNomeEmail(String nome, String email){
		List<Funcionario> funcionarios = new ArrayList<Funcionario>();
		
		manager = factory.createEntityManager();
		
		funcionarios = manager.createQuery("from Funcionario WHERE nome=?1 AND email=?2", Funcionario.class).setParameter(1, nome).
		setParameter(2, email).getResultList();
        
		manager.close();
		
		return funcionarios;
	}

	public static Funcionario updateFuncionario(int id, String nome, String email, String endereco, String cidade, String estado, String cep, String salario) {
		
		Funcionario funcionario = new Funcionario(id, nome, email, endereco, cidade, estado, cep, Double.parseDouble(salario));
		manager = factory.createEntityManager();
        manager.getTransaction().begin();
        manager.merge(funcionario);
        manager.getTransaction().commit();
        manager.close();
        
        return funcionario;
	}

	public static void deleteFuncionario(int id) {
		manager = factory.createEntityManager();
        
        Funcionario funcionario = manager.find(Funcionario.class, id);
        
        manager.getTransaction().begin();
        manager.remove(funcionario);
        manager.getTransaction().commit();
        manager.close();
	}

	public static List<Funcionario> getAllFuncionarios() {
		return new ArrayList<Funcionario>(userMap.values());
	}
}
