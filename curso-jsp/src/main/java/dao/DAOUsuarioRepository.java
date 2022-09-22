package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connection.SingleConnectionBanco;
import model.ModelLogin;

public class DAOUsuarioRepository {
	
	private Connection connection;
	
	public DAOUsuarioRepository() {
		connection = SingleConnectionBanco.getConnection();
	}
	
	// VALIDAR LOGIN EXISTENTE ********************************************************************
	public boolean validarLogin(String login) throws Exception {
		String sql = "SELECT COUNT(1) > 0 AS existe FROM model_login WHERE login = '"+login+"'";
		PreparedStatement select = connection.prepareStatement(sql);
		ResultSet resultado = select.executeQuery();

		if (resultado.next()) { // se tem uma linha na tabela, retorna verdadeiro
			return resultado.getBoolean("existe");
		}
		return false;
	}
	
	// CADASTRO/ATUALIZAÇÃO DE USUÁRIO ************************************************************
	public ModelLogin salvarUsuario(ModelLogin usuario, Integer userLogado) throws Exception {
		
		if(usuario.isNovo()) { // um novo usuário
			String sql = "INSERT INTO model_login("
					   + "nome, email, login, senha, grupo, cod_user) "
					   + "VALUES (?,?,?,?,?,?);";
			
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps.setObject(1, usuario.getNome());
			ps.setObject(2, usuario.getEmail());
			ps.setObject(3, usuario.getLogin());
			ps.setObject(4, usuario.getSenha());
			ps.setObject(5, usuario.getGrupo());
			ps.setObject(6, userLogado);
			
			ps.execute();
			connection.commit();
			
		} else { // atualizar usuário existente
			String sql = "UPDATE model_login SET "
					   + "nome=?, email=?, login=?, senha=?, grupo=? "
					   + "WHERE codigo = " + usuario.getCodigo() + ";";
			
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps.setObject(1, usuario.getNome());
			ps.setObject(2, usuario.getEmail());
			ps.setObject(3, usuario.getLogin());
			ps.setObject(4, usuario.getSenha());
			ps.setObject(5, usuario.getGrupo());
			
			ps.executeUpdate();
			connection.commit();
			return this.consultarUsuarioLogin(usuario.getLogin()); // mantém na tela usuário atualizado
		}
		
		return null;
	}
	
	// CONSULTAR USUARIO LOGIN ********************************************************************
	public ModelLogin consultarUsuarioLogin(String login) throws Exception {
		
		ModelLogin modelLogin = new ModelLogin();
		
		String sql = "SELECT * FROM model_login WHERE login = '"+login+"' AND useradmin IS false";
		
		PreparedStatement ps = connection.prepareStatement(sql);
		
		ResultSet rs = ps.executeQuery();
		
		if (rs.next()) { // se tem mais uma linha na tabela
			modelLogin.setCodigo(rs.getInt("codigo"));
			modelLogin.setNome(rs.getString("nome"));
			modelLogin.setEmail(rs.getString("email"));
			modelLogin.setLogin(rs.getString("login"));
			modelLogin.setSenha(rs.getString("senha"));
			modelLogin.setUseradmin(rs.getBoolean("useradmin"));
			modelLogin.setGrupo(rs.getString("grupo"));
			}
		return modelLogin;
	}
	
	// CONSULTAR USUARIO CODIGO********************************************************************
	public ModelLogin consultarUsuarioCodigo(Integer codigo) throws Exception {
		
		ModelLogin modelLogin = new ModelLogin();
		
		String sql = "SELECT * FROM model_login WHERE codigo = ? AND useradmin IS false";
		
		PreparedStatement ps = connection.prepareStatement(sql);
		
		ps.setInt(1, codigo);
		
		ResultSet rs = ps.executeQuery();
		
		if (rs.next()) { // se tem mais uma linha na tabela
			modelLogin.setCodigo(rs.getInt("codigo"));
			modelLogin.setNome(rs.getString("nome"));
			modelLogin.setEmail(rs.getString("email"));
			modelLogin.setLogin(rs.getString("login"));
			modelLogin.setSenha(rs.getString("senha"));
			modelLogin.setUseradmin(rs.getBoolean("useradmin"));
			modelLogin.setGrupo(rs.getString("grupo"));
		}
		return modelLogin;
	}
	
	// CONSULTAR USUARIO LOGADO *******************************************************************
	public ModelLogin consultarUsuarioLogado(String login) throws Exception {
		
		ModelLogin modelLogin = new ModelLogin();
		
		String sql = "SELECT * FROM model_login WHERE login = ?";
		
		PreparedStatement ps = connection.prepareStatement(sql);
		
		ps.setString(1, login);
		
		ResultSet rs = ps.executeQuery();
		
		if (rs.next()) { // se tem mais uma linha na tabela
			modelLogin.setCodigo(rs.getInt("codigo"));
			modelLogin.setNome(rs.getString("nome"));
			modelLogin.setEmail(rs.getString("email"));
			modelLogin.setLogin(rs.getString("login"));
			modelLogin.setSenha(rs.getString("senha"));
			modelLogin.setUseradmin(rs.getBoolean("useradmin"));
			modelLogin.setGrupo(rs.getString("grupo"));
		}
		return modelLogin;
	}
	
	// BUSCAR USUÁRIO *****************************************************************************
	public List<ModelLogin> buscaUserList(String nome) throws Exception {
		
		List<ModelLogin> retornoBusca = new ArrayList<ModelLogin>();
		
		String sql = "SELECT * FROM model_login WHERE nome LIKE ? AND useradmin IS false";
		
		PreparedStatement ps = connection.prepareStatement(sql);
		
		ps.setString(1, "%" + nome + "%");
		
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) { // percorrer as linhas da tabela
			ModelLogin modelLogin = new ModelLogin();
			modelLogin.setCodigo(rs.getInt("codigo"));
			modelLogin.setNome(rs.getString("nome"));
			modelLogin.setEmail(rs.getString("email"));
			modelLogin.setLogin(rs.getString("login"));
			modelLogin.setUseradmin(rs.getBoolean("useradmin"));
			modelLogin.setGrupo(rs.getString("grupo"));
			// modelLogin.setSenha(resultado.getString("senha")); não precisa
			
			retornoBusca.add(modelLogin);
		}
		return retornoBusca;
	}
	
	// LISTAR USUARIO *****************************************************************************
	public List<ModelLogin> buscaUserList() throws Exception {
		
		List<ModelLogin> retornoBusca = new ArrayList<ModelLogin>();
		
		String sql = "SELECT * FROM model_login WHERE useradmin IS false";
		
		PreparedStatement ps = connection.prepareStatement(sql);
		
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) { // percorrer as linhas da tabela
			ModelLogin modelLogin = new ModelLogin();
			modelLogin.setCodigo(rs.getInt("codigo"));
			modelLogin.setNome(rs.getString("nome"));
			modelLogin.setEmail(rs.getString("email"));
			modelLogin.setLogin(rs.getString("login"));
			modelLogin.setUseradmin(rs.getBoolean("useradmin"));
			modelLogin.setGrupo(rs.getString("grupo"));
			// modelLogin.setSenha(resultado.getString("senha")); não precisa
			
			retornoBusca.add(modelLogin);
			
		}
		return retornoBusca;
	}
	
	// DELETE USUÁRIO *****************************************************************************
	public void deletarUsuario(String codigo) throws Exception {
		String sql = "DELETE FROM model_login WHERE codigo = ? AND useradmin IS false";
		PreparedStatement ps = connection.prepareStatement(sql);
		
		ps.setInt(1, Integer.valueOf(codigo));
		
		ps.executeUpdate();
		connection.commit();
		
	}

}
