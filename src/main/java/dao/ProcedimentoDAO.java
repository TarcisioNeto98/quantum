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
import model.Funcionario;
import model.Procedimento;
import util.DbUtil;

public class ProcedimentoDAO {
	private static final Map<Integer, Procedimento> userMap = new HashMap<Integer, Procedimento>();
	
	static EntityManagerFactory factory = Persistence.createEntityManagerFactory("clinicaodontologica");
    static EntityManager manager;
	
	public static Procedimento getProcedimento(int id) {
		return userMap.get(id);
	}

	public static Procedimento addProcedimento(String nome, String valor, String descricao) {
		Procedimento proc = new Procedimento(nome, Double.parseDouble(valor), descricao);
		 
        manager = factory.createEntityManager();
        manager.getTransaction().begin();
        manager.persist(proc);
        manager.getTransaction().commit();
        manager.close();
        
        return proc;
	}

	public static Procedimento updateProcedimento(int id, String nome, double valor, String descricao) {
		Procedimento procedimento = new Procedimento(id, nome, valor, descricao);
		userMap.put(procedimento.getId(), procedimento);
		return procedimento;
	}

	public static void deleteProcedimento(int id) {
		if (userMap.containsKey(id)) {
			userMap.remove(id);
		}
	}

	public static List<Procedimento> getAllProcedimentos() {
		return new ArrayList<Procedimento>(userMap.values());
	}
	
}
