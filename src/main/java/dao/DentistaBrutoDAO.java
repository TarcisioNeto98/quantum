package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Dentista;
import util.DbUtil;

public class DentistaBrutoDAO {
private static Connection connection = DbUtil.getConnection();
	
	public static Dentista addDentista(String nome, String especialidade) {
		try {
            PreparedStatement pStmt = connection.prepareStatement("INSERT INTO Dentista(nome, especialidade) " 
            + "VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
            pStmt.setString(1, nome);
            pStmt.setString(2, especialidade);
            pStmt.executeUpdate();
            ResultSet rs = pStmt.getGeneratedKeys();
            
            if (rs.next()) {
                return new Dentista(rs.getInt("id"), rs.getString("nome"), rs.getString("especialidade"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
	}
	
	public static List<Dentista> getAllDentistas() {
        List<Dentista> dentistas = new ArrayList<Dentista>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * from Dentista ORDER BY id");
            while (rs.next()) {
                Dentista dentista = new Dentista(rs.getInt("id"), rs.getString("nome"), rs.getString("especialidade"));
                dentistas.add(dentista);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
 
        return dentistas;
    }
}
