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

import dao.FuncionarioDAO;
import model.Funcionario;
import util.Validar;

@WebServlet ("/api/funcionarios/*")
public class FuncionarioService extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public FuncionarioService() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String path = request.getPathInfo();
		
		if(path.contains("quantidade")) {
			JSONObject json = new JSONObject();
			int quantidade = FuncionarioDAO.quantidadeFuncionario();
			json.put("quantidade", quantidade);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().print(json.toString());
			response.getWriter().flush();
			return;
		}
		
		if(path.contains("id")) {
			JSONObject json = new JSONObject();
			int id = Integer.parseInt(path.replace("/id/", ""));
			Funcionario funcionario = FuncionarioDAO.getFuncionario(id);
			json.put("id", funcionario.getId());
			json.put("nome", funcionario.getNome());
			json.put("email", funcionario.getEmail());
			json.put("endereco", funcionario.getEndereco());
			json.put("cidade", funcionario.getCidade());
			json.put("estado", funcionario.getEstado());
			json.put("cep", funcionario.getCep());
			json.put("salario", funcionario.getSalario());
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().print(json.toString());
			response.getWriter().flush();
			return;
		}
		
		// GET ALL
		/*List<Funcionario> list = FuncionarioDAO.getAllFuncionarios();

		try {
			JSONArray jArray = new JSONArray();

			for (Funcionario funcionario : list) {
				JSONObject jsonObject = new JSONObject();

				jsonObject.put("id", funcionario.getId());
				jsonObject.put("nome", funcionario.getNome());
				jsonObject.put("endereco", funcionario.getEndereco());
				jsonObject.put("cidade", funcionario.getCidade());
				jsonObject.put("estado", funcionario.getEstado());
				jsonObject.put("cep", funcionario.getCep());
				jsonObject.put("salario", funcionario.getSalario());

				jArray.put(jsonObject);
			}

			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().print(jArray.toString());
			response.getWriter().flush();
		} catch (Exception e) {

		}*/
		
		try {
			List<Funcionario> list = FuncionarioDAO.getFuncionarioNomeEmail(request.getParameter("nome"),request.getParameter("email"));
			JSONArray jArray = new JSONArray();

			for (Funcionario funcionario : list) {
				JSONObject jsonObject = new JSONObject();

				jsonObject.put("id", funcionario.getId());
				jsonObject.put("nome", funcionario.getNome());
				jsonObject.put("email", funcionario.getEmail());
				jsonObject.put("cidade", funcionario.getCidade());
				jsonObject.put("estado", funcionario.getEstado());
				jsonObject.put("cep", funcionario.getCep());
				jsonObject.put("endereco", funcionario.getEndereco());
				jsonObject.put("salario", funcionario.getSalario());
				System.out.println(jsonObject.toString());
						
				jArray.put(jsonObject);
			}
					
			System.out.println(jArray.toString());
					
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().print(jArray.toString());
			response.getWriter().flush();
		} catch (Exception e) {

		}

		
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

		Funcionario funcionario = null;
		JSONObject jsonObject = new JSONObject(jb.toString());
		try {
			// Request
			if(Validar.nome(jsonObject.getString("nome")) && Validar.cep(jsonObject.getString("cep")) 
			&& Validar.endereco(jsonObject.getString("endereco")) && Validar.salario(jsonObject.getString("salario"))
			&& Validar.nome(jsonObject.getString("cidade")) && Validar.email(jsonObject.getString("email"))) {
				funcionario = FuncionarioDAO.addFuncionario(jsonObject.getString("nome"),jsonObject.getString("email"),
				jsonObject.getString("endereco"), jsonObject.getString("cidade"), jsonObject.getString("estado"),
				jsonObject.getString("cep"), jsonObject.getString("salario"));
				// Response
				jsonObject = new JSONObject();
				jsonObject.put("id", funcionario.getId());
				jsonObject.put("nome", funcionario.getNome());
				jsonObject.put("email", funcionario.getEmail());
				jsonObject.put("endereco", funcionario.getEndereco());
				jsonObject.put("cidade", funcionario.getCidade());
				jsonObject.put("estado", funcionario.getEstado());
				jsonObject.put("cep", funcionario.getCep());
				jsonObject.put("salario", funcionario.getSalario());
				response.setStatus(201);
			}
			else {
				response.setStatus(401);
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().print(jsonObject.toString());
				response.getWriter().flush();
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(jsonObject.toString());
		response.getWriter().flush();
		
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		StringBuffer jb = new StringBuffer();
		String line = null;
		try {
			BufferedReader reader = req.getReader();
			while ((line = reader.readLine()) != null)
				jb.append(line);
		} catch (Exception e) {
		}

		Funcionario funcionario = null;
		JSONObject jsonObject = null;
		try {
			// Request
			jsonObject = new JSONObject(jb.toString());
			if(Validar.nome(jsonObject.getString("nome")) && Validar.cep(jsonObject.getString("cep")) 
			&& Validar.endereco(jsonObject.getString("endereco"))
			&& Validar.nome(jsonObject.getString("cidade")) && Validar.email(jsonObject.getString("email"))) {
				funcionario = FuncionarioDAO.updateFuncionario(jsonObject.getInt("id"), jsonObject.getString("nome"), 
				jsonObject.getString("email"), jsonObject.getString("endereco"), jsonObject.getString("cidade"),
				jsonObject.getString("estado"), jsonObject.getString("cep"), jsonObject.getDouble("salario"));
				// Response
				jsonObject = new JSONObject();
				jsonObject.put("id", funcionario.getId());
				jsonObject.put("nome", funcionario.getNome());
				jsonObject.put("email", funcionario.getEmail());
				jsonObject.put("endereco", funcionario.getEndereco());
				jsonObject.put("cidade", funcionario.getCidade());
				jsonObject.put("estado", funcionario.getEstado());
				jsonObject.put("cep", funcionario.getCep());
				jsonObject.put("salario", funcionario.getSalario());
				resp.setStatus(201);
			}
			else {
				resp.setStatus(401);
				resp.setContentType("application/json");
				resp.setCharacterEncoding("UTF-8");
				resp.getWriter().print(jsonObject.toString());
				resp.getWriter().flush();
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().print(jsonObject.toString());
		resp.getWriter().flush();

	}

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// DELETE BY ID
        int id = Integer.parseInt(request.getParameter("id"));
        if(FuncionarioDAO.deleteFuncionario(id) != null) response.setStatus(200);
        else response.setStatus(401);
        
        response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().flush();
	}
}
