package util;

public class Validar {
	
	//Validações com expressões regulares.
	
	public static boolean nome(String nome) {
		if(nome.matches("\\s+")) return false;//Verifico se o nome só possui espaços.
		else return nome.matches("[a-zà-úA-ZÁ-Ú ]{4,}");
	}
	
	public static boolean senha(String senha) {
		return senha.matches("^(?=.*\\d)(?=.*[a-zA-Z])(?!.*[\\W_\\x7B-\\xFF]).{8,}$");
	}
	
	public static boolean cep(String cep) {
		if(cep.matches("\\d{8}")) return true;
		return false;
	}
	
	public static boolean endereco(String endereco) {
		return endereco.matches("[a-zà-úA-ZÁ-Ú 0-9]+");
	}
	
	public static boolean email(String email) {
		return email.matches("\\w+@\\w+\\.\\w{2,3}\\.\\w{2,3}");
	}
	
	public static boolean salario(String salario) {
		try {
			Double.parseDouble(salario);
		}catch(Exception e) {
			return false;
		}
		return true;
	}
	
	public static boolean cpf (String cpf) {
		return cpf.matches("^\\d{3}\\d{3}\\d{3}\\d{2}");
	}
	
}
