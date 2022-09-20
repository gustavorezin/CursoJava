package connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnectionBanco {
	private static String url = "jdbc:mysql://localhost:3306/curso_jsp?autoReconnect=true";
	private static String user = "root";
	private static String senha = "unisul.123";
	private static Connection connection = null;
	
	public static Connection getConnection() {
		return connection;
	}
	
	static {
		conectar();
	}
	
	public SingleConnectionBanco() { // quando tiver um instancia vai conectar
		conectar();
	}
	
	private static void conectar() {
		
		try {
			
			if(connection == null) {
				Class.forName("com.mysql.jdbc.Driver"); // Carrega o driver de conex�o do banco
				connection = DriverManager.getConnection(url, user, senha);
				connection.setAutoCommit(false); // para nao efetuar alteracoes no banco sem nosso comando
			}
			
		}catch (Exception e) {
			e.printStackTrace(); // mostrar qualquer erro no momento de conectar
		}
	}
}
