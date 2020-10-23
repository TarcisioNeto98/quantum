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

import model.Funcionario;
import model.Paciente;
import util.DbUtil;

public class FuncionarioDAO {
	private static final Map<Integer, Funcionario> userMap = new HashMap<Integer, Funcionario>();
	private static int i = 4;
	private static Connection connection = DbUtil.getConnection();
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

	public static Funcionario addFuncionario(String nome, String endereco, String cidade, String estado, String cep, String salario) {
		try {
            PreparedStatement pStmt = connection.prepareStatement("INSERT INTO Funcionario(nome, endereco, cep, cidade, estado, salario) " 
            + "VALUES (?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            pStmt.setString(1, nome);
            pStmt.setString(2, endereco);
            pStmt.setString(3, cep);
            pStmt.setString(4, cidade);
            pStmt.setString(5, estado);
            pStmt.setString(6, salario);
            pStmt.executeUpdate();
            ResultSet rs = pStmt.getGeneratedKeys();
            
            if (rs.next()) {
                return new Funcionario(rs.getInt("id"), rs.getString("nome"), rs.getString("endereco"),rs.getString("cidade"), 
                rs.getString("estado"), rs.getString("cep"), Double.parseDouble(rs.getString("salario")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
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
