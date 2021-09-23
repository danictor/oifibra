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

@WebServlet(urlPatterns = {"/Controller", "/saopaulo/insert", "/guarulhos/insert"})
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    DAO dao = new DAO();   
    
    public Controller() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		
		if(action.equals("/saopaulo/insert") || action.equals("/guarulhos/insert")) {
			cadastro(request, response);
		} else if (action.equals("/fechar")) {
			response.sendRedirect("index.html");
		} else {
			response.sendRedirect("index.html");
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}

	// cadastrar
	protected void cadastro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cadastro cadastro = new Cadastro();
		System.out.println("CEP: " + request.getParameter("cep").replaceAll("[\\-\\+\\.\\^:,]",""));
		System.out.println("Número: " + request.getParameter("numFaixada"));
		boolean validaCep = dao.validaCep(request.getParameter("cep").replaceAll("[\\-\\+\\.\\^:,]",""), request.getParameter("numFaixada"));
		
		//setando variaveis
		cadastro.setNome(request.getParameter("nome"));
		cadastro.setEmail(request.getParameter("email"));
		cadastro.setTelefone(request.getParameter("tel").replaceAll("[\\-\\+\\.\\^:,]",""));
		cadastro.setCep(request.getParameter("cep").replaceAll("[\\-\\+\\.\\^:,]",""));
		cadastro.setNumFaixada(request.getParameter("numFaixada"));
		cadastro.setAvisoEmail(request.getParameter("receberEmail") != null && request.getParameter("receberEmail").equals("on") ? true : false);
		cadastro.setAvisoWhatsapp(request.getParameter("receberCall") != null && request.getParameter("receberCall").equals("on") ? true : false);
		cadastro.setData(new Date(new java.util.Date().getTime()));
		//invocar inserir
		dao.insert(cadastro);
		//redireciono
		if(validaCep) {
			response.sendRedirect("enviado.html");
			return;
		}
		
		response.sendRedirect("erro.html");
	}

}
