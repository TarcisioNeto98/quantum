package controller;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
				consulta = ConsultaDAO.addConsulta(jsonObject.getString("valor"), jsonObject.getString("data"),
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
 
                Consulta consulta = null;
                JSONObject jsonObject = null;
 
                try {
                    // Request
                    jsonObject = new JSONObject(jb.toString());
                    consulta = ConsultaDAO.updateConsulta(Integer.parseInt(params[1]), jsonObject.getString("data"),
                    jsonObject.getInt("idPaciente"), jsonObject.getInt("idDentista"));
 
                    // Response
                    jsonObject = new JSONObject();
                    jsonObject.put("id", consulta.getId());
    				jsonObject.put("data", consulta.getData());
    				jsonObject.put("idPaciente", consulta.getIdPaciente());
    				jsonObject.put("idDentista", consulta.getIdDentista());
 
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
                ConsultaDAO.deleteConsulta(Integer.parseInt(params[1]));
 
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().flush();
            }
        }
	}
}

