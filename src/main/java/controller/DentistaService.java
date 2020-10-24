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
import dao.DentistaDAO;
import dao.ProcedimentoDAO;
import model.Consulta;
import model.Dentista;
import model.Procedimento;

@WebServlet ("/api/dentistas/*")
public class DentistaService extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public DentistaService() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Dentista> list = DentistaDAO.getAllDentistas();

		try {
			JSONArray jArray = new JSONArray();

			for (Dentista dentista : list) {
				JSONObject jsonObject = new JSONObject();

				jsonObject.put("id", dentista.getId());
				jsonObject.put("nome", dentista.getNome());
				jsonObject.put("especialidade", dentista.getEspecialidade());

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
		// TODO Auto-generated method stub
		StringBuffer jb = new StringBuffer();
		String line = null;
		try {
			BufferedReader reader = request.getReader();
			while ((line = reader.readLine()) != null)
				jb.append(line);
		} catch (Exception e) {
		}

		Dentista dentista = null;
		JSONObject jsonObject = null;
		try {
			// Request
			jsonObject = new JSONObject(jb.toString());
			dentista = DentistaDAO.addDentista(jsonObject.getString("nome"), jsonObject.getString("especialidade"));
			// Response
			jsonObject = new JSONObject();
			jsonObject.put("id", dentista.getId());
			jsonObject.put("nome", dentista.getNome());
			jsonObject.put("especialidade", dentista.getEspecialidade());
		} catch (JSONException e) {
		}

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(jsonObject.toString());
		response.getWriter().flush();

	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
