package controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DAO;
import model.Cadastro;

@WebServlet(urlPatterns = {"/Controller", "/main", "/insert"})
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    DAO dao = new DAO();   
    
    public Controller() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		
		if(action.equals("/insert")) {
			cadastro(request, response);
		} else if (action.equals("/fechar")) {
			response.sendRedirect("index.html");
		} else {
			response.sendRedirect("index.html");
		}
		
	}
	
	// cadastrar
	protected void cadastro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cadastro cadastro = new Cadastro();
		System.out.println(request.getParameter("cep").replaceAll("[\\-\\+\\.\\^:,]",""));	
		boolean validaCep = dao.validaCep(request.getParameter("cep").replaceAll("[\\-\\+\\.\\^:,]",""));
		if(validaCep) {
			response.sendRedirect("erro.html");
			return;
		}
		
		//setando variaveis
		cadastro.setNome(request.getParameter("nome"));
		cadastro.setEmail(request.getParameter("email"));
		cadastro.setTelefone(request.getParameter("tel").replaceAll("[\\-\\+\\.\\^:,]",""));
		cadastro.setCep(request.getParameter("cep").replaceAll("[\\-\\+\\.\\^:,]",""));
		cadastro.setAvisoEmail(request.getParameter("receberEmail") != null && request.getParameter("receberEmail").equals("on") ? true : false);
		cadastro.setAvisoWhatsapp(request.getParameter("receberCall") != null && request.getParameter("receberCall").equals("on") ? true : false);
		cadastro.setData(new Date(new java.util.Date().getTime()));
		//invocar inserir
		dao.insert(cadastro);
		//redireciono
		response.sendRedirect("enviado.html");
	}

}
