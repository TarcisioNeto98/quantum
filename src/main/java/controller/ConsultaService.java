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

import dao.ConsultaDAO;
import model.Consulta;
import util.Validar;

@WebServlet ("/api/consultas/*")
public class ConsultaService extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public ConsultaService() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String path = request.getPathInfo();
		
		if(path.contains("quantidade")) {
			JSONObject json = new JSONObject();
			int quantidade = ConsultaDAO.quantidadeConsulta();
			json.put("quantidade", quantidade);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().print(json.toString());
			response.getWriter().flush();
			return;
		}
		
		if(path.contains("data")) {
			String data = path.replace("/data/", "");
			
			System.out.println(data);
			JSONArray jarray = new JSONArray();
			List<Consulta> consultas = ConsultaDAO.getConsultaByData(data);
			
			if(consultas != null) {
				for(Consulta consulta : consultas) {
					JSONObject json = new JSONObject();
					json.put("id", consulta.getId());
					json.put("data", consulta.getData());
					json.put("valor", consulta.getValor());
					json.put("idDentista", consulta.getIdDentista());
					json.put("idPaciente", consulta.getIdPaciente());
					
					jarray.put(json);
				}
				response.setStatus(200);
			}
			else response.setStatus(401);
			
			try {
				System.out.println(jarray.toString());
				PacienteService.enviarJSON(jarray.toString(), response);
			}catch(Exception e) {
				
			}
			return;
		}
		
		if(path.contains("id")) {
			int id = Integer.parseInt(path.replace("/id/", ""));
			
			System.out.println(id);
			JSONObject json = new JSONObject();
			Consulta consulta = ConsultaDAO.getConsulta(id);
			
			if(consulta != null) {
				json.put("id", consulta.getId());
				json.put("data", consulta.getData());
				json.put("valor", consulta.getValor());
				json.put("idDentista", consulta.getIdDentista());
				json.put("idPaciente", consulta.getIdPaciente());
				response.setStatus(200);
			}
			else response.setStatus(401);
			
			try {
				System.out.println(json.toString());
				PacienteService.enviarJSON(json.toString(), response);
			}catch(Exception e) {
				
			}
			return;
		}
		
		JSONArray jarray = new JSONArray();
		List<Consulta> consultas = ConsultaDAO.getAllConsultas();
		
		if(consultas != null) {
			for(Consulta consulta : consultas) {
				JSONObject json = new JSONObject();
				json.put("id", consulta.getId());
				json.put("data", consulta.getData());
				json.put("idDentista", consulta.getIdDentista());
				json.put("idPaciente", consulta.getIdPaciente());
				
				jarray.put(json);
			}
			try {
				PacienteService.enviarJSON(jarray.toString(), response);
			}catch(Exception e) {
				
			}
			
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

		Consulta consulta = null;
		JSONObject jsonObject = null;
		try {
			// Request
			jsonObject = new JSONObject(jb.toString());
			if(Validar.salario(jsonObject.getString("valor"))) {
				consulta = ConsultaDAO.addConsulta(jsonObject.getString("data"),jsonObject.getString("valor"),
				jsonObject.getInt("idPaciente"), Integer.parseInt(jsonObject.getString("idDentista")));
				System.out.println(consulta.toString());
				// Response
				jsonObject = new JSONObject();
				jsonObject.put("id", consulta.getId());
				jsonObject.put("data", consulta.getData());
				jsonObject.put("valor", consulta.getValor());
				jsonObject.put("idPaciente", consulta.getIdPaciente());
				jsonObject.put("idDentista", consulta.getIdDentista());
				response.setStatus(201);
			}
			else {
				response.setStatus(401);
				PacienteService.enviarJSON(jsonObject.toString(), response);
				return;
			}
		} catch (JSONException e) {
		}
		PacienteService.enviarJSON(jsonObject.toString(), response);
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
		
		
		try {
			JSONObject json = new JSONObject(jb.toString());
			
			Consulta consulta = ConsultaDAO.updateConsulta(json.getInt("id"), json.getString("valor"),
			json.getString("data"), json.getInt("idPaciente"), json.getInt("idDentista"));
			
			if(consulta != null) {
				System.out.println(consulta);
				json.put("id", consulta.getId());
				json.put("valor", consulta.getValor());
				json.put("data", consulta.getData());
				json.put("idPaciente", consulta.getIdPaciente());
				json.put("idDentista", consulta.getIdDentista());
				resp.setStatus(200);
			}
			else resp.setStatus(401);
			
			PacienteService.enviarJSON(json.toString(), resp);
			
		}catch(Exception e) {
			
		}
		
	}

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// DELETE BY ID
        String pathInfo = request.getPathInfo();
 
        if (pathInfo != null) {
            String[] params = pathInfo.split("/");
 
            if (params.length > 0) {
                if(ConsultaDAO.deleteConsulta(Integer.parseInt(params[1])) != null) response.setStatus(200);
                else response.setStatus(401);
 
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().flush();
            }
        }
	}
}

