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
import dao.PacienteDAO;
import model.Funcionario;
import model.Paciente;

@WebServlet ("/api/pacientes/*")
public class PacienteService extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public PacienteService() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// GET BY ID
				String pathInfo = request.getPathInfo();

				if (pathInfo != null) {
					String[] params = pathInfo.split("/");

					if (params.length > 0) {
						Paciente paciente = PacienteDAO.getPaciente(Integer.parseInt(params[1]));

						if (paciente != null) {
							JSONObject jsonObject = new JSONObject();

							jsonObject.put("id", paciente.getId());
							jsonObject.put("nome", paciente.getNome());
							jsonObject.put("email", paciente.getEmail());
							jsonObject.put("cidade", paciente.getCidade());
							jsonObject.put("estado", paciente.getEstado());
							jsonObject.put("cep", paciente.getCep());

							response.setContentType("application/json");
							response.setCharacterEncoding("UTF-8");
							response.getWriter().print(jsonObject.toString());
							response.getWriter().flush();
						}
						return;
					}
				}
				
				/*
				// GET BY NAME
		        if (request.getParameter("login") != null) {
		        	System.out.println(request.getParameter("login"));
		        	System.out.println(request.getParameter("password"));
		        	
		            Paciente paciente = PacienteDAO.getPacienteByLogin(request.getParameter("login"));
		 
		            if (user != null) {
		 
		                JSONObject jsonObject = new JSONObject();
		 
		                jsonObject.put("id", user.getId());
		                jsonObject.put("login", user.getLogin());
		                jsonObject.put("password", user.getPassword());
		 
		                response.setContentType("application/json");
		                response.setCharacterEncoding("UTF-8");
		                response.getWriter().print(jsonObject.toString());
		                response.getWriter().flush();
		 
		            }
		            return;
		        } */

				// GET ALL
				List<Paciente> list = PacienteDAO.getAllPacientes();

				try {
					JSONArray jArray = new JSONArray();

					for (Paciente paciente : list) {
						JSONObject jsonObject = new JSONObject();

						jsonObject.put("id", paciente.getId());
						jsonObject.put("nome", paciente.getNome());
						jsonObject.put("email", paciente.getEmail());
						jsonObject.put("cidade", paciente.getCidade());
						jsonObject.put("estado", paciente.getEstado());
						jsonObject.put("cep", paciente.getCep());

						jArray.put(jsonObject);
					}

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

		Paciente paciente = null;
		JSONObject jsonObject = null;
		try {
			// Request
			jsonObject = new JSONObject(jb.toString());
			paciente = PacienteDAO.addPaciente(jsonObject.getString("nome"), jsonObject.getString("email"), jsonObject.getString("cidade"),jsonObject.getString("estado"), jsonObject.getString("cep"));
			System.out.println(paciente.toString());
			// Response
			jsonObject = new JSONObject();
			jsonObject.put("id", paciente.getId());
			jsonObject.put("nome", paciente.getNome());
			jsonObject.put("cidade", paciente.getCidade());
			jsonObject.put("estado", paciente.getEstado());
			jsonObject.put("cep", paciente.getCep());
		} catch (JSONException e) {
		}

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(jsonObject.toString());
		response.getWriter().flush();
		// UPDATE BY ID
        /*String pathInfo = request.getPathInfo();
 
        if (pathInfo != null) {
            String[] params = pathInfo.split("/");
 
            if (params.length > 0) {
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
 
                try {
                    // Request
                    jsonObject = new JSONObject(jb.toString());
                    paciente = PacienteDAO.updatePaciente(Integer.parseInt(params[1]), jsonObject.getString("nome"),
                    		jsonObject.getString("email"), jsonObject.getString("cidade"),
                    		jsonObject.getString("estado"), jsonObject.getString("cep"));
 
                    // Response
                    jsonObject = new JSONObject();
                    jsonObject.put("id", paciente.getId());
                    jsonObject.put("login", paciente.getNome());
                    jsonObject.put("password", paciente.getEmail());
                    jsonObject.put("cidade", paciente.getCidade());
                    jsonObject.put("estado", paciente.getEstado());
                    jsonObject.put("cep", paciente.getCep());
 
                } catch (JSONException e) {
                }
 
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().print(jsonObject.toString());
                response.getWriter().flush();
            }
        }*/
	}
	
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// DELETE BY ID
        String pathInfo = request.getPathInfo();
 
        if (pathInfo != null) {
            String[] params = pathInfo.split("/");
 
            if (params.length > 0) {
                PacienteDAO.deletePaciente(Integer.parseInt(params[1]));
 
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().flush();
            }
        }
	}
}
