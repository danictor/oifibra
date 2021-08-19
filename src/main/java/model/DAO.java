package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DAO {
	// m�dulo de conex�o

	// par�metros de conex�o
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://127.0.0.1:3306/dboifibra?useTimezone=true&serverTimezone=UTC";
	private String user = "root";
	private String password = "759.124D4n";

	// M�todo de conex�o
	private Connection conectar() {
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			return con;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	public void insert(Cadastro cadastro) {
		String create = "insert into cadastro (nome, e_mail, telefone, cep, aviso_e_mail, aviso_whatsapp, data) values (?, ?, ?, ?, ?, ?, ?);";
		try {
			// abro conex�o
			Connection con = conectar();
			// preparo query para execu��o
			PreparedStatement pst = con.prepareStatement(create);
			// substituir os parametros pelas variaveis
			pst.setString(1, cadastro.getNome());
			pst.setString(2, cadastro.getEmail());
			pst.setString(3, cadastro.getTelefone());
			pst.setString(4, cadastro.getCep());
			pst.setBoolean(5, cadastro.getAvisoEmail());
			pst.setBoolean(6, cadastro.getAvisoWhatsapp());
			pst.setDate(7, cadastro.getData());
			// execute
			pst.executeUpdate();
			// encerro conex�o
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public boolean validaCep(String cep) {
		String read = "select * from cep_bloqueado where cep = ? limit 1";
		try {
			// abro conex�o
			Connection con = conectar();
			// preparo query para execu��o
			PreparedStatement pst = con.prepareStatement(read);
			// substituir os parametros pelas variaveis
			pst.setString(1, cep);
			//execute
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()){
				if(rs.getString("cep") != null) {
					return true;
				}
			}
			
			// encerro conex�o
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}
	
}
