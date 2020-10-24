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

@WebServlet ("/api/consultas/*")
public class ConsultaService extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public ConsultaService() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// GET BY ID
		String pathInfo = request.getPathInfo();

		if (pathInfo != null) {
			String[] params = pathInfo.split("/");

			if (params.length > 0) {
				Consulta consulta = ConsultaDAO.getConsulta(Integer.parseInt(params[1]));

				if (consulta != null) {
					JSONObject jsonObject = new JSONObject();

					jsonObject.put("id", consulta.getId());
					jsonObject.put("data", consulta.getData());
					jsonObject.put("idPaciente", consulta.getIdPaciente());
					jsonObject.put("idDentista", consulta.getIdDentista());

					response.setContentType("application/json");
					response.setCharacterEncoding("UTF-8");
					response.getWriter().print(jsonObject.toString());
					response.getWriter().flush();
				}
				return;
			}
		}
		
		// GET ALL
		List<Consulta> list = ConsultaDAO.getAllConsultas();

		try {
			JSONArray jArray = new JSONArray();

			for (Consulta consulta : list) {
				JSONObject jsonObject = new JSONObject();

				jsonObject.put("data", consulta.getData());

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

		Consulta consulta = null;
		JSONObject jsonObject = null;
		try {
			// Request
			jsonObject = new JSONObject(jb.toString());
			consulta = ConsultaDAO.addConsulta(jsonObject.getString("valor"),
			jsonObject.getInt("idPaciente"), Integer.parseInt(jsonObject.getString("idDentista")));
			System.out.println(consulta.toString());
			// Response
			jsonObject = new JSONObject();
			jsonObject.put("id", consulta.getId());
			jsonObject.put("data", consulta.getData());
			jsonObject.put("valor", consulta.getValor());
			jsonObject.put("idPaciente", consulta.getIdPaciente());
			jsonObject.put("idDentista", consulta.getIdDentista());
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

