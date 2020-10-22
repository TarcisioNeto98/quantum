package dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Funcionario;

public class FuncionarioDAO {
	private static final Map<Integer, Funcionario> userMap = new HashMap<Integer, Funcionario>();
	private static int i = 4;
	
	static {
		initFuncionarios();
	}
	
	private static void initFuncionarios() {
		Funcionario funcionario1 = new Funcionario(1, "Matheus", "Rua Abgail Torres, 334, Centro", "Sobral", "Ceará", "64700-387", 2550.98);
		Funcionario funcionario2 = new Funcionario(2, "Pablo", "Rua Abgail Torres, 334, Centro", "Sobral", "Ceará", "64700-387", 2550.98);
		Funcionario funcionario3 = new Funcionario(3, "Joaquim", "Rua Abgail Torres, 334, Centro", "Sobral", "Ceará", "64700-387", 2550.98);

		userMap.put(funcionario1.getId(), funcionario1);
		userMap.put(funcionario2.getId(), funcionario2);
		userMap.put(funcionario3.getId(), funcionario3);
	}

	public static Funcionario getFuncionario(int id) {
		return userMap.get(id);
	}

	public static Funcionario addFuncionario(String nome, String endereco, String cidade, String estado, String cep, double salario) {
		Funcionario funcionario = new Funcionario(i, nome, endereco, cidade, estado, cep, salario);
		userMap.put(funcionario.getId(), funcionario);
		i++;
		return funcionario;
	}

	public static Funcionario updateFuncionario(int id, String nome, String endereco, String cidade, String estado, String cep, double salario) {
		Funcionario funcionario = new Funcionario(id, nome, endereco, cidade, estado, cep, salario);
		userMap.put(funcionario.getId(), funcionario);
		return funcionario;
	}

	public static void deleteFuncionario(int id) {
		if (userMap.containsKey(id)) {
			userMap.remove(id);
		}
	}

	public static List<Funcionario> getAllFuncionarios() {
		return new ArrayList<Funcionario>(userMap.values());
	}
}
