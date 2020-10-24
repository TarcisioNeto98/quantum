package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import dao.DependenteDAO;
import dao.PacienteDAO;
import model.Dependente;
import model.Paciente;

@WebServlet ("/api/dependentes/*")
public class DependenteService extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DependenteService() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// GET BY ID
				/*String pathInfo = request.getPathInfo();

				if (pathInfo != null) {
					String[] params = pathInfo.split("/");

					if (params.length > 0) {
						Dependente dependente = DependenteDAO.getDependente(Integer.parseInt(params[1]));

						if (dependente != null) {
							JSONObject jsonObject = new JSONObject();

							jsonObject.put("id", dependente.getId());
							jsonObject.put("nome", dependente.getNome() );
							jsonObject.put("cpf", dependente.getCpf());
							jsonObject.put("cargo", dependente.getCargo());
							jsonObject.put("idFuncionario", dependente.getIdFuncionario());
							
							response.setContentType("application/json");
							response.setCharacterEncoding("UTF-8");
							response.getWriter().print(jsonObject.toString());
							response.getWriter().flush();
						}
						return;
					}
				}
				
				// GET ALL
				List<Dependente> list = DependenteDAO.getAllDependentes();

				try {
					JSONArray jArray = new JSONArray();

					for (Dependente dependente : list) {
						JSONObject jsonObject = new JSONObject();

						jsonObject.put("id", dependente.getId());
						jsonObject.put("nome", dependente.getNome() );
						jsonObject.put("cpf", dependente.getCpf());
						jsonObject.put("cargo", dependente.getCargo());
						jsonObject.put("idFuncionario", dependente.getIdFuncionario());

						jArray.put(jsonObject);
					}

					response.setContentType("application/json");
					response.setCharacterEncoding("UTF-8");
					response.getWriter().print(jArray.toString());
					response.getWriter().flush();
				} catch (Exception e) {

				}*/
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StringBuffer jb = new StringBuffer();
		String line = null;
		try {
			BufferedReader reader = request.getReader();
			while ((line = reader.readLine()) != null)
				jb.append(line);
		} catch (Exception e) {
		}

		Dependente dependente = null;
		JSONObject jsonObject = null;
		try {
			// Request
			jsonObject = new JSONObject(jb.toString());
			dependente = DependenteDAO.addDependente(jsonObject.getString("nome"), jsonObject.getString("cpf"),jsonObject.getInt("idFuncionario"));
			
			// Response
			jsonObject = new JSONObject();
			jsonObject.put("id", dependente.getId());
			jsonObject.put("nome", dependente.getNome());
			jsonObject.put("cpf", dependente.getCpf());
			jsonObject.put("idFuncionario", dependente.getIdFuncionario());
		} catch (JSONException e) {
		}

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(jsonObject.toString());
		response.getWriter().flush();
	}
	
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// DELETE BY ID
        String pathInfo = request.getPathInfo();
 
        if (pathInfo != null) {
            String[] params = pathInfo.split("/");
 
            if (params.length > 0) {
                DependenteDAO.deleteDependente(Integer.parseInt(params[1]));
 
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().flush();
            }
        }
	}
}
