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

import model.Paciente;
import util.DbUtil;

public class PacienteDAO {
	private static final Map<Integer, Paciente> userMap = new HashMap<Integer, Paciente>();
	private static Connection connection = DbUtil.getConnection();
	
	public static Paciente getPaciente(int id) {
		Paciente paciente = null;
		try {
        	PreparedStatement pStmt = connection.prepareStatement("SELECT * from Paciente where id=?");
        	pStmt.setInt(1, id);
        	ResultSet rs = pStmt.executeQuery();
        	if(rs.next()) {
                paciente = new Paciente(rs.getInt("id"), rs.getString("nome"), rs.getString("email"), rs.getString("cidade"),
                rs.getString("estado"), rs.getString("cep"));
                return paciente;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return paciente;
	}
	
	public static int quantidadePaciente() {
		try {
        	PreparedStatement pStmt = connection.prepareStatement("SELECT COUNT(*) AS quantidade from Paciente");
        	ResultSet rs = pStmt.executeQuery();
        	
        	if(rs.next()) return rs.getInt("quantidade");
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return 0;
	}
	
	public static Paciente addPaciente(String nome, String email, String cidade, String estado, String cep) {
		try {
            PreparedStatement pStmt = connection.prepareStatement("INSERT INTO Paciente(nome, email, cep, cidade, estado) " 
            + "VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            
            pStmt.setString(1, nome);
            pStmt.setString(2, email);
            pStmt.setString(3, cep);
            pStmt.setString(4, cidade);
            pStmt.setString(5, estado);
            pStmt.executeUpdate();
            ResultSet rs = pStmt.getGeneratedKeys();
            
            if (rs.next()) {
                return new Paciente(rs.getInt("id"), rs.getString("nome"), rs.getString("email"),rs.getString("cidade"), rs.getString("estado"), rs.getString("cep"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
	}
	
	public static List<Paciente> getPacienteNomeEmail(String nome, String email){
		List<Paciente> pacientes = new ArrayList<Paciente>();
        try {
        	PreparedStatement pStmt = connection.prepareStatement("SELECT * from Paciente where email=? and nome=?");
        	pStmt.setString(1, email);
        	pStmt.setString(2, nome);
        	ResultSet rs = pStmt.executeQuery();
            while (rs.next()) {
                Paciente paciente = new Paciente(rs.getInt("id"), rs.getString("nome"), rs.getString("email"), rs.getString("cidade"),
                rs.getString("estado"), rs.getString("cep"));
                pacientes.add(paciente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
 
        return pacientes;
	}

	public static Paciente updatePaciente(int id, String nome, String email, String cidade, String estado, String cep) {
		try {
            PreparedStatement pStmt = connection.prepareStatement("UPDATE Paciente SET nome = ?, email = ?, cep = ?, cidade = ?,"
            + "estado = ? WHERE id = ?", Statement.RETURN_GENERATED_KEYS);
            
            pStmt.setString(1, nome);
            pStmt.setString(2, email);
            pStmt.setString(3, cep);
            pStmt.setString(4, cidade);
            pStmt.setString(5, estado);
            pStmt.setInt(6, id);
            pStmt.executeUpdate();
            ResultSet rs = pStmt.getGeneratedKeys();
            
            if (rs.next()) {
                return new Paciente(rs.getInt("id"), rs.getString("nome"), rs.getString("email"),rs.getString("cidade"),
                rs.getString("estado"), rs.getString("cep"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
	}
	

	public static void deletePaciente(int id) {
		try {
			PreparedStatement pStmt = connection.prepareStatement("DELETE from Paciente WHERE id=?");
        	pStmt.setInt(1, id);
        	pStmt.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

	public static List<Paciente> getAllPacientes() {
		return new ArrayList<Paciente>(userMap.values());
	}
}
