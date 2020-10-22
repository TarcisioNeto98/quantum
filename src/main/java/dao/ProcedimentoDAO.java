package dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Procedimento;

public class ProcedimentoDAO {
	private static final Map<Integer, Procedimento> userMap = new HashMap<Integer, Procedimento>();
	private static int i = 4;
	
	static {
		initProcedimentos();
	}
	
	private static void initProcedimentos() {
		Procedimento procedimento1 = new Procedimento(1, "Extração", 250.00, "Extração de qualquer natureza. Observação: Raio-X não incluso");
		Procedimento procedimento2 = new Procedimento(2, "Limpeza", 150.00, "Limpeza completa com aplicação de fluor");
		Procedimento procedimento3 = new Procedimento(3, "Canal", 400.00, "Canal de qualquer complexidade");

		userMap.put(procedimento1.getId(), procedimento1);
		userMap.put(procedimento2.getId(), procedimento2);
		userMap.put(procedimento3.getId(), procedimento3);
	}

	public static Procedimento getProcedimento(int id) {
		return userMap.get(id);
	}

	public static Procedimento addProcedimento(int id, String nome, double valor, String descricao) {
		Procedimento procedimento = new Procedimento(i, nome, valor, descricao);
		userMap.put(procedimento.getId(), procedimento);
		i++;
		return procedimento;
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
