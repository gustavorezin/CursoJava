package dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import connection.SingleConnectionBanco;

public class DAOVersionadorBanco implements Serializable {

	private static final long serialVersionUID = 1L;

	private Connection connection;
	
	public DAOVersionadorBanco() {
		connection = SingleConnectionBanco.getConnection();
	}
	
	public boolean arquivoSqlRodado(String nomeArquivo) throws Exception {
		String sql = "SELECT COUNT(1) > 0 AS rodado FROM versionador_banco WHERE arquivo_sql = ?";
		PreparedStatement ps = connection.prepareStatement(sql);
	
		ps.setObject(1, nomeArquivo);
		
		ResultSet rs = ps.executeQuery();
		
		rs.next();
		
		return rs.getBoolean("rodado");
	}
	
	public void salvaArquivoSqlRodado(String nomeArquivo) throws Exception {
		String sql = "INSERT INTO versionador_banco(arquivo_sql) VALUES(?)";
		PreparedStatement ps = connection.prepareStatement(sql);
		
		ps.setObject(1, nomeArquivo);
		
		ps.execute();
 	}
}
