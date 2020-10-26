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

import model.Dependente;
import util.DbUtil;

public class DependenteBrutoDAO {
	private static final Map<Integer, Dependente> userMap = new HashMap<Integer, Dependente>();
	private static int i = 4;
	private static Connection connection = DbUtil.getConnection();
	
	
	public static Dependente getDependente(int id) {
		return userMap.get(id);
	}

	public static Dependente addDependente(String nome, String cpf, int idFuncionario) {
		try {
            PreparedStatement pStmt = connection.prepareStatement("INSERT INTO Dependente(nome, cpf, idFuncionario) " 
            + "VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            
            pStmt.setString(1, nome);
            pStmt.setString(2, cpf);
            pStmt.setInt(3, idFuncionario);
            
            pStmt.executeUpdate();
            ResultSet rs = pStmt.getGeneratedKeys();
            
            if (rs.next()) {
                return new Dependente(rs.getInt("id"), rs.getString("nome"), rs.getString("cpf"),rs.getInt("idFuncionario"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
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
