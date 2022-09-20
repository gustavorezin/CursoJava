package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.SingleConnectionBanco;
import model.ModelLogin;

public class DAOLoginRepository {
	
	private Connection connection;
	
	public DAOLoginRepository() {
		connection = SingleConnectionBanco.getConnection();
	}
	
	public boolean validarAutenticacao(ModelLogin modelLogin) throws SQLException {
		String sql = "SELECT * FROM model_login WHERE login = ? AND senha = ? ";
		PreparedStatement select = connection.prepareStatement(sql);
		select.setString(1, modelLogin.getLogin());
		select.setString(2, modelLogin.getSenha());

		ResultSet resultado = select.executeQuery();
		
		if (resultado.next()) {
			return true; //autenticado
		}
		
		return false; // não autenticado
	
	}
	
}
