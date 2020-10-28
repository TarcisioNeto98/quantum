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

import dao.PacienteDAO;
import model.Paciente;
import util.Validar;

@WebServlet ("/api/pacientes/*")
public class PacienteService extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public PacienteService() {
        super();
    }
    
    public static void enviarJSON(String json, HttpServletResponse response) throws IOException {
    	response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(json);
		response.getWriter().flush();
    }
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Quantidade
		String path = request.getPathInfo();
		
		if(path.contains("quantidade")) {
			System.out.println(path);
			JSONObject json = new JSONObject();
			int quantidade = PacienteDAO.quantidadePaciente();
			json.put("quantidade", quantidade);
			try {
				enviarJSON(json.toString(), response);
			}catch(IOException e) {
				e.printStackTrace();
			}
			return;
			
		}
		//id
		else if(path.contains("id")) {
			int id = Integer.parseInt(path.replace("/id/", ""));
			Paciente paciente = PacienteDAO.getPaciente(id);
			JSONObject json = new JSONObject();
			json.put("nome", paciente.getNome());
			json.put("id", paciente.getId());
			json.put("email", paciente.getEmail());
			json.put("cidade", paciente.getCidade());
			json.put("cep", paciente.getCep());
			json.put("estado", paciente.getEstado());
			try {
				enviarJSON(json.toString(), response);
			}catch(IOException e) {
				e.printStackTrace();
			}
			return;
		}
		// Nome e email
		try {
			List<Paciente> list = PacienteDAO.getPacienteNomeEmail(request.getParameter("nome"),request.getParameter("email"));
			JSONArray jArray = new JSONArray();

			for (Paciente paciente : list) {
				JSONObject jsonObject = new JSONObject();

				jsonObject.put("id", paciente.getId());
				jsonObject.put("nome", paciente.getNome());
				jsonObject.put("email", paciente.getEmail());
				jsonObject.put("cidade", paciente.getCidade());
				jsonObject.put("estado", paciente.getEstado());
				jsonObject.put("cep", paciente.getCep());
				System.out.println(jsonObject.toString());
						
				jArray.put(jsonObject);
			}
					
			System.out.println(jArray.toString());
			enviarJSON(jArray.toString(), response);
			
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

		Paciente paciente = null;
		JSONObject jsonObject = null;
		jsonObject = new JSONObject(jb.toString());
		System.out.println(Validar.nome(jsonObject.getString("nome")));
		System.out.println(Validar.cep(jsonObject.getString("cep")));
		System.out.println(Validar.email(jsonObject.getString("email")));
		System.out.println(Validar.nome(jsonObject.getString("cidade")));
		try {
			if(Validar.nome(jsonObject.getString("nome")) && Validar.email(jsonObject.getString("email")) &&
			Validar.nome(jsonObject.getString("cidade")) && Validar.cep(jsonObject.getString("cep"))) {
			// Request
				paciente = PacienteDAO.addPaciente(jsonObject.getString("nome"), jsonObject.getString("email"), jsonObject.getString("cidade"),
				jsonObject.getString("estado"), jsonObject.getString("cep"));
				System.out.println(paciente.toString());
				// Response
				jsonObject = new JSONObject();
				jsonObject.put("id", paciente.getId());
				jsonObject.put("nome", paciente.getNome());
				jsonObject.put("cidade", paciente.getCidade());
				jsonObject.put("estado", paciente.getEstado());
				jsonObject.put("cep", paciente.getCep());
				response.setStatus(201);
			}
			else {
				response.setStatus(401);
				System.out.println("dddd");
				enviarJSON(jsonObject.toString(), response);
				return;
			}
		} catch (JSONException e) {
		}

		enviarJSON(jsonObject.toString(), response);

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

		Paciente paciente = null;
		JSONObject jsonObject = null;
		jsonObject = new JSONObject(jb.toString());
		try {
			if(Validar.nome(jsonObject.getString("nome")) && Validar.email(jsonObject.getString("email")) &&
			Validar.nome(jsonObject.getString("cidade")) && Validar.cep(jsonObject.getString("cep"))) {
				// Request
				paciente = PacienteDAO.updatePaciente(jsonObject.getInt("id"), jsonObject.getString("nome"), jsonObject.getString("email"), jsonObject.getString("cidade"),jsonObject.getString("estado"), jsonObject.getString("cep"));
				System.out.println(paciente.toString());
				// Response
				jsonObject = new JSONObject();
				jsonObject.put("id", paciente.getId());
				jsonObject.put("nome", paciente.getNome());
				jsonObject.put("cidade", paciente.getCidade());
				jsonObject.put("estado", paciente.getEstado());
				jsonObject.put("cep", paciente.getCep());
				resp.setStatus(201);
			}
			else {
				resp.setStatus(401);
				System.out.println("dddd");
				enviarJSON(jsonObject.toString(), resp);
				return;
			}
		} catch (JSONException e) {
		}

		enviarJSON(jsonObject.toString(), resp);

	}
	
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// DELETE BY ID
        String id = request.getParameter("id");
        PacienteDAO.deletePaciente(Integer.parseInt(id));
	}
}
