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
import dao.ProcedimentoDAO;
import model.Paciente;
import model.Procedimento;
import util.Validar;

@WebServlet ("/api/procedimentos/*")
public class ProcedimentoService extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ProcedimentoService() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// GET BY ID
				String pathInfo = request.getPathInfo();

				if (pathInfo != null) {
					String[] params = pathInfo.split("/");

					if (params.length > 0) {
						Procedimento procedimento = ProcedimentoDAO.getProcedimento(Integer.parseInt(params[1]));

						if (procedimento != null) {
							JSONObject jsonObject = new JSONObject();

							jsonObject.put("id", procedimento.getId());
							jsonObject.put("nome", procedimento.getNome());
							jsonObject.put("valor", procedimento.getValor());
							jsonObject.put("descricao", procedimento.getDescricao());

							response.setContentType("application/json");
							response.setCharacterEncoding("UTF-8");
							response.getWriter().print(jsonObject.toString());
							response.getWriter().flush();
						}
						return;
					}
				}
				
				// GET ALL
				List<Procedimento> list = ProcedimentoDAO.getAllProcedimentos();

				try {
					JSONArray jArray = new JSONArray();

					for (Procedimento procedimento : list) {
						JSONObject jsonObject = new JSONObject();

						jsonObject.put("id", procedimento.getId());
						jsonObject.put("nome", procedimento.getNome());
						jsonObject.put("valor", procedimento.getValor());
						jsonObject.put("descricao", procedimento.getDescricao());

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

		Procedimento procedimento = null;
		JSONObject jsonObject = null;
		try {
			// Request
			jsonObject = new JSONObject(jb.toString());
			
			if(Validar.nome(jsonObject.getString("nome")) && Validar.salario(jsonObject.getString("valor")) 
			&& Validar.nome(jsonObject.getString("descricao"))) {
					
				procedimento = ProcedimentoDAO.addProcedimento(jsonObject.getString("nome"), jsonObject.getString("valor"),
				jsonObject.getString("descricao"));
				System.out.println(procedimento.toString());
				// Response
				jsonObject = new JSONObject();
				jsonObject.put("id", procedimento.getId());
				jsonObject.put("nome", procedimento.getNome());
				jsonObject.put("valor", procedimento.getValor());
				jsonObject.put("descricao", procedimento.getDescricao());
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
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// DELETE BY ID
        String pathInfo = request.getPathInfo();
 
        if (pathInfo != null) {
            String[] params = pathInfo.split("/");
 
            if (params.length > 0) {
                ProcedimentoDAO.deleteProcedimento(Integer.parseInt(params[1]));
 
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().flush();
            }
        }
	}
}
