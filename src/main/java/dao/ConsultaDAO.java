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

import model.Consulta;
import util.DbUtil;

public class ConsultaDAO {
	private static final Map<Integer, Consulta> userMap = new HashMap<Integer, Consulta>();
	private static int i = 4;
	private static Connection connection = DbUtil.getConnection();
	
	static {
		initConsultas();
	}
	
	private static void initConsultas() {
		
	}

	public static Consulta getConsulta(int id) {
		return userMap.get(id);
	}

	public static Consulta addConsulta(String valor, int idPaciente, int idDentista) {
		
		try {
            PreparedStatement pStmt = connection.prepareStatement("INSERT INTO Consulta(valor, idPaciente, idDentista) " 
            + "VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            
            pStmt.setString(1, valor);
            pStmt.setInt(2, idPaciente);
            pStmt.setInt(3, idDentista);
            pStmt.executeUpdate();
            ResultSet rs = pStmt.getGeneratedKeys();
            
            if (rs.next()) {
                return new Consulta(rs.getInt("id"), rs.getString("valor"), rs.getDate("date").toString(), 
                rs.getInt("idPaciente"),rs.getInt("idDentista"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
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
