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
	

	public static Funcionario getFuncionario(int id) {
		Funcionario funcionario = null;
		try {
        	PreparedStatement pStmt = connection.prepareStatement("SELECT * from Funcionario where id=?");
        	pStmt.setInt(1, id);
        	ResultSet rs = pStmt.executeQuery();
        	if(rs.next()) {
                funcionario = new Funcionario(rs.getInt("id"), rs.getString("nome"),rs.getString("email"), rs.getString("endereco"),rs.getString("cidade"), 
                        rs.getString("estado"), rs.getString("cep"), Double.parseDouble(rs.getString("salario")));
                return funcionario;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return funcionario;
	}
	
	public static int quantidadeFuncionario() {
		try {
        	PreparedStatement pStmt = connection.prepareStatement("SELECT COUNT(*) AS quantidade from Funcionario");
        	ResultSet rs = pStmt.executeQuery();
        	
        	if(rs.next()) return rs.getInt("quantidade");
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return 0;
	}
	
	public static Funcionario addFuncionario(String nome, String email, String endereco, String cidade, String estado, String cep, String salario) {
		try {
            PreparedStatement pStmt = connection.prepareStatement("INSERT INTO Funcionario(nome, endereco, cep, cidade, estado, salario, email) " 
            + "VALUES (?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            pStmt.setString(1, nome);
            pStmt.setString(2, endereco);
            pStmt.setString(3, cep);
            pStmt.setString(4, cidade);
            pStmt.setString(5, estado);
            pStmt.setString(6, salario);
            pStmt.setString(7, email);
            pStmt.executeUpdate();
            ResultSet rs = pStmt.getGeneratedKeys();
            
            if (rs.next()) {
                return new Funcionario(rs.getInt("id"), rs.getString("nome"),rs.getString("email"), rs.getString("endereco"),rs.getString("cidade"), 
                rs.getString("estado"), rs.getString("cep"), Double.parseDouble(rs.getString("salario")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
	}
	
	public static List<Funcionario> getFuncionarioNomeEmail(String nome, String email){
		List<Funcionario> funcionarios = new ArrayList<Funcionario>();
        try {
        	PreparedStatement pStmt = connection.prepareStatement("SELECT * from Funcionario where email=? and nome=?");
        	pStmt.setString(1, email);
        	pStmt.setString(2, nome);
        	ResultSet rs = pStmt.executeQuery();
        	
        	while (rs.next()) {
                Funcionario funcionario = new Funcionario(rs.getInt("id"), rs.getString("nome"), rs.getString("email"), 
                rs.getString("endereco"), rs.getString("cidade"), rs.getString("estado"), rs.getString("cep"),
                Double.parseDouble(rs.getString("salario")));
                funcionarios.add(funcionario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
 
        return funcionarios;
	}

	public static Funcionario updateFuncionario(int id, String nome, String email, String endereco, String cidade, String estado, String cep, String salario) {
		try {
            PreparedStatement pStmt = connection.prepareStatement("UPDATE Funcionario SET nome=?, endereco=?, cep=?, cidade=?, estado=?, salario=?, email=? " 
            + "WHERE id = ?", Statement.RETURN_GENERATED_KEYS);
            pStmt.setString(1, nome);
            pStmt.setString(2, endereco);
            pStmt.setString(3, cep);
            pStmt.setString(4, cidade);
            pStmt.setString(5, estado);
            pStmt.setString(6, salario);
            pStmt.setString(7, email);
            pStmt.setInt(8, id);
            pStmt.executeUpdate();
            ResultSet rs = pStmt.getGeneratedKeys();
            
            if (rs.next()) {
                return new Funcionario(rs.getInt("id"), rs.getString("nome"),rs.getString("email"), rs.getString("endereco"),rs.getString("cidade"), 
                rs.getString("estado"), rs.getString("cep"), Double.parseDouble(rs.getString("salario")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
	}

	public static void deleteFuncionario(int id) {
		try {
			PreparedStatement pStmt = connection.prepareStatement("DELETE FROM Funcionario WHERE id=?");
        	pStmt.setInt(1, id);
        	pStmt.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

	public static List<Funcionario> getAllFuncionarios() {
		return new ArrayList<Funcionario>(userMap.values());
	}
}
