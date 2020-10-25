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
import dao.UserDAO;
import model.User;

@WebServlet ("/api/users/*")
public class UserService extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public UserService() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// GET BY NAME
        if (request.getParameter("email") != null && request.getParameter("password") != null) {
        	
            User user = UserDAO.getUserByLoginAndPassword(request.getParameter("email"), request.getParameter("password"));
 
            if (user != null) {
                JSONObject jsonObject = new JSONObject();
 
				jsonObject.put("id", user.getId());
				jsonObject.put("email", user.getLogin());
				jsonObject.put("password", user.getPassword());
				jsonObject.put("endereco", user.getEndereco());
				jsonObject.put("cep", user.getCep());
				jsonObject.put("cidade", user.getCidade());
				jsonObject.put("estado", user.getEstado());
				
				PacienteService.enviarJSON(jsonObject.toString(), response);
            }
            else {
            	JSONObject jsonObject = new JSONObject();
            	PacienteService.enviarJSON(jsonObject.toString(), response);
            }
            return;
        }

		// GET ALL
		/*List<User> list = UserDAO.getAllUsers();

		try {
			JSONArray jArray = new JSONArray();

			for (User user : list) {
				JSONObject jsonObject = new JSONObject();

				jsonObject.put("id", user.getId());
				jsonObject.put("email", user.getLogin());
				jsonObject.put("password", user.getPassword());
				jsonObject.put("endereco", user.getEndereco());
				jsonObject.put("cep", user.getCep());
				jsonObject.put("cidade", user.getCidade());
				jsonObject.put("estado", user.getEstado());

				jArray.put(jsonObject);
			}

			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().print(jArray.toString());
			response.getWriter().flush();
		} catch (Exception e) {

		}*/

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		StringBuffer jb = new StringBuffer();
		String line = null;
		try {
			BufferedReader reader = request.getReader();
			while ((line = reader.readLine()) != null)
				jb.append(line);
		} catch (Exception e) {
		}

		User user = null;
		JSONObject jsonObject = null;
		try {
			// Request
			jsonObject = new JSONObject(jb.toString());
			user = UserDAO.addUser(jsonObject.getString("nome"), jsonObject.getString("email"), jsonObject.getString("password"),
			jsonObject.getString("endereco"), jsonObject.getString("cep"), jsonObject.getString("cidade"),
			jsonObject.getString("estado"));
			
			if(user == null) {
				jsonObject.put("id", -1);
				PacienteService.enviarJSON(jsonObject.toString(), response);
				return;
			}
			System.out.println(user.toString());
			// Response
			jsonObject = new JSONObject();
			jsonObject.put("id", user.getId());
			jsonObject.put("nome", user.getLogin());
			jsonObject.put("email", user.getLogin());
			jsonObject.put("password", user.getPassword());
			jsonObject.put("endereco", user.getEndereco());
			jsonObject.put("cep", user.getCep());
			jsonObject.put("cidade", user.getCidade());
			jsonObject.put("estado", user.getEstado());
		} catch (JSONException e) {
		}
		PacienteService.enviarJSON(jsonObject.toString(), response);
	}
	
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
 
                User user = null;
                JSONObject jsonObject = null;
 
                try {
                    // Request
                    jsonObject = new JSONObject(jb.toString());
                    user = UserDAO.updateUser(Integer.parseInt(params[1]), jsonObject.getString("login"),
                    jsonObject.getString("password"), jsonObject.getString("endereco"),
                    jsonObject.getString("cep"), jsonObject.getString("cidade"), jsonObject.getString("estado"));
                    
                    jsonObject = new JSONObject();
    				jsonObject.put("id", user.getId());
    				jsonObject.put("email", user.getLogin());
    				jsonObject.put("password", user.getPassword());
    				jsonObject.put("endereco", user.getEndereco());
    				jsonObject.put("cep", user.getCep());
    				jsonObject.put("cidade", user.getCidade());
    				jsonObject.put("estado", user.getEstado());
 
                    // Response
 
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
        /*String pathInfo = request.getPathInfo();
 
        if (pathInfo != null) {
            String[] params = pathInfo.split("/");
 
            if (params.length > 0) {
                UserDAO.deleteUser(Integer.parseInt(params[1]));
 
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().flush();
            }
        }*/
	}

}
