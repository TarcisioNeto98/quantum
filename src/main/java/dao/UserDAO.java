package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.User;
import util.DbUtil;

public class UserDAO {

    private static Connection connection = DbUtil.getConnection();

	public static User addUser(String nome, String login, String password, String endereco, String cep, String cidade, String estado) {
        try {
        	PreparedStatement pStmt = connection.prepareStatement("SELECT * FROM Usuario WHERE password=?");
        	pStmt.setString(1, password);
        	ResultSet rs = pStmt.executeQuery();
        	
        	if(rs.next()) {
        		return null;
        	}
        	
            pStmt = connection.prepareStatement("INSERT INTO Usuario(nome, email, password, endereco, cep, cidade, estado) " 
            + "VALUES (?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            pStmt.setString(1, nome);
            pStmt.setString(2, login);
            pStmt.setString(3, password);
            pStmt.setString(4, endereco);
            pStmt.setString(5, cep);
            pStmt.setString(6, cidade);
            pStmt.setString(7, estado);
            pStmt.executeUpdate();
            rs = pStmt.getGeneratedKeys();
            if (rs.next()) {
                return new User(rs.getInt("id"), rs.getString("nome"), rs.getString("email"), rs.getString("password"), rs.getString("endereco")
                ,rs.getString("cep"), rs.getString("cidade"), rs.getString("estado"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
 
        return null;
	}
	
	public static User getUserByLoginAndPassword(String email, String password) {
		try {
			PreparedStatement pStmt = connection.prepareStatement("SELECT * FROM Usuario WHERE password=? and email=?");
	    	pStmt.setString(1, password);
	    	pStmt.setString(2, email);
	    	ResultSet rs = pStmt.executeQuery();
	    	if(rs.next()) {
	    		System.out.println(rs.getInt("id"));
	    		return new User(rs.getInt("id"), rs.getString("nome"), rs.getString("email"), rs.getString("password"),
	    		rs.getString("endereco"),rs.getString("cep"), rs.getString("cidade"), rs.getString("estado"));
	    	}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
