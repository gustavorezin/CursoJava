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
					   + "nome, nomefantasia, data_nasc, login, senha, grupo, salario, sexo, "
					   + "cep, uf, cidade, logradouro, numero, bairro, "
					   + "fone1, fone2, celular1, celular2, email, site, cod_user) "
					   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
			
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps.setObject(1, usuario.getNome());
			ps.setObject(2, usuario.getNomefantasia());
			ps.setObject(3, usuario.getDataNascimento());
			ps.setObject(4, usuario.getLogin());
			ps.setObject(5, usuario.getSenha());
			ps.setObject(6, usuario.getGrupo());
			ps.setObject(7, usuario.getSalario());
			ps.setObject(8, usuario.getSexo());
			ps.setObject(9, usuario.getCep());
			ps.setObject(10, usuario.getUf());
			ps.setObject(11, usuario.getCidade());
			ps.setObject(12, usuario.getLogradouro());
			ps.setObject(13, usuario.getNumero());
			ps.setObject(14, usuario.getBairro());
			ps.setObject(15, usuario.getFone1());
			ps.setObject(16, usuario.getFone2());
			ps.setObject(17, usuario.getCelular1());
			ps.setObject(18, usuario.getCelular2());
			ps.setObject(19, usuario.getEmail());
			ps.setObject(20, usuario.getSite());
			ps.setObject(21, userLogado);
			
			ps.execute();
			connection.commit();
			
		} else { // atualizar usuário existente
			String sql = "UPDATE model_login SET "
					   + "nome=?, nomefantasia=?, data_nasc=?, login=?, senha=?, grupo=?, salario=?, sexo=?, "
					   + "cep=?, uf=?, cidade=?, logradouro=?, numero=?, bairro=?, "
					   + "fone1=?, fone2=?, celular1=?, celular2=?, email=?, site=? "
					   + "WHERE codigo = " + usuario.getCodigo() + ";";
			
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps.setObject(1, usuario.getNome());
			ps.setObject(2, usuario.getNomefantasia());
			ps.setObject(3, usuario.getDataNascimento());
			ps.setObject(4, usuario.getLogin());
			ps.setObject(5, usuario.getSenha());
			ps.setObject(6, usuario.getGrupo());
			ps.setObject(7, usuario.getSalario());
			ps.setObject(8, usuario.getSexo());
			ps.setObject(9, usuario.getCep());
			ps.setObject(10, usuario.getUf());
			ps.setObject(11, usuario.getCidade());
			ps.setObject(12, usuario.getLogradouro());
			ps.setObject(13, usuario.getNumero());
			ps.setObject(14, usuario.getBairro());
			ps.setObject(15, usuario.getFone1());
			ps.setObject(16, usuario.getFone2());
			ps.setObject(17, usuario.getCelular1());
			ps.setObject(18, usuario.getCelular2());
			ps.setObject(19, usuario.getEmail());
			ps.setObject(20, usuario.getSite());
			
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
			modelLogin.setNomefantasia(rs.getString("nomefantasia"));
			modelLogin.setDataNascimento(rs.getDate("data_nasc"));
			modelLogin.setLogin(rs.getString("login"));
			modelLogin.setSenha(rs.getString("senha"));
			modelLogin.setUseradmin(rs.getBoolean("useradmin"));
			modelLogin.setGrupo(rs.getString("grupo"));
			modelLogin.setSalario(rs.getDouble("salario"));
			modelLogin.setSexo(rs.getString("sexo"));
			modelLogin.setCep(rs.getString("cep"));
			modelLogin.setUf(rs.getString("uf"));
			modelLogin.setCidade(rs.getString("cidade"));
			modelLogin.setLogradouro(rs.getString("logradouro"));
			modelLogin.setNumero(rs.getString("numero"));
			modelLogin.setBairro(rs.getString("bairro"));
			modelLogin.setFone1(rs.getString("fone1"));
			modelLogin.setFone2(rs.getString("fone2"));
			modelLogin.setCelular1(rs.getString("celular1"));
			modelLogin.setCelular2(rs.getString("celular2"));
			modelLogin.setEmail(rs.getString("email"));
			modelLogin.setSite(rs.getString("site"));
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
			modelLogin.setNomefantasia(rs.getString("nomefantasia"));
			modelLogin.setDataNascimento(rs.getDate("data_nasc"));
			modelLogin.setLogin(rs.getString("login"));
			modelLogin.setSenha(rs.getString("senha"));
			modelLogin.setUseradmin(rs.getBoolean("useradmin"));
			modelLogin.setGrupo(rs.getString("grupo"));
			modelLogin.setSalario(rs.getDouble("salario"));
			modelLogin.setSexo(rs.getString("sexo"));
			modelLogin.setCep(rs.getString("cep"));
			modelLogin.setUf(rs.getString("uf"));
			modelLogin.setCidade(rs.getString("cidade"));
			modelLogin.setLogradouro(rs.getString("logradouro"));
			modelLogin.setNumero(rs.getString("numero"));
			modelLogin.setBairro(rs.getString("bairro"));
			modelLogin.setFone1(rs.getString("fone1"));
			modelLogin.setFone2(rs.getString("fone2"));
			modelLogin.setCelular1(rs.getString("celular1"));
			modelLogin.setCelular2(rs.getString("celular2"));
			modelLogin.setEmail(rs.getString("email"));
			modelLogin.setSite(rs.getString("site"));
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
			modelLogin.setNomefantasia(rs.getString("nomefantasia"));
			modelLogin.setDataNascimento(rs.getDate("data_nasc"));
			modelLogin.setLogin(rs.getString("login"));
			modelLogin.setSenha(rs.getString("senha"));
			modelLogin.setUseradmin(rs.getBoolean("useradmin"));
			modelLogin.setGrupo(rs.getString("grupo"));
			modelLogin.setSalario(rs.getDouble("salario"));
			modelLogin.setSexo(rs.getString("sexo"));
			modelLogin.setCep(rs.getString("cep"));
			modelLogin.setUf(rs.getString("uf"));
			modelLogin.setCidade(rs.getString("cidade"));
			modelLogin.setLogradouro(rs.getString("logradouro"));
			modelLogin.setNumero(rs.getString("numero"));
			modelLogin.setBairro(rs.getString("bairro"));
			modelLogin.setFone1(rs.getString("fone1"));
			modelLogin.setFone2(rs.getString("fone2"));
			modelLogin.setCelular1(rs.getString("celular1"));
			modelLogin.setCelular2(rs.getString("celular2"));
			modelLogin.setEmail(rs.getString("email"));
			modelLogin.setSite(rs.getString("site"));
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
			modelLogin.setNomefantasia(rs.getString("nomefantasia"));
			modelLogin.setDataNascimento(rs.getDate("data_nasc"));
			modelLogin.setLogin(rs.getString("login"));
			modelLogin.setUseradmin(rs.getBoolean("useradmin"));
			modelLogin.setGrupo(rs.getString("grupo"));
			modelLogin.setSalario(rs.getDouble("salario"));
			modelLogin.setSexo(rs.getString("sexo"));
			modelLogin.setCep(rs.getString("cep"));
			modelLogin.setUf(rs.getString("uf"));
			modelLogin.setCidade(rs.getString("cidade"));
			modelLogin.setLogradouro(rs.getString("logradouro"));
			modelLogin.setNumero(rs.getString("numero"));
			modelLogin.setBairro(rs.getString("bairro"));
			modelLogin.setFone1(rs.getString("fone1"));
			modelLogin.setFone2(rs.getString("fone2"));
			modelLogin.setCelular1(rs.getString("celular1"));
			modelLogin.setCelular2(rs.getString("celular2"));
			modelLogin.setEmail(rs.getString("email"));
			modelLogin.setSite(rs.getString("site"));
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
			modelLogin.setNomefantasia(rs.getString("nomefantasia"));
			modelLogin.setDataNascimento(rs.getDate("data_nasc"));
			modelLogin.setLogin(rs.getString("login"));
			modelLogin.setUseradmin(rs.getBoolean("useradmin"));
			modelLogin.setGrupo(rs.getString("grupo"));
			modelLogin.setSalario(rs.getDouble("salario"));
			modelLogin.setSexo(rs.getString("sexo"));
			modelLogin.setCep(rs.getString("cep"));
			modelLogin.setUf(rs.getString("uf"));
			modelLogin.setCidade(rs.getString("cidade"));
			modelLogin.setLogradouro(rs.getString("logradouro"));
			modelLogin.setNumero(rs.getString("numero"));
			modelLogin.setBairro(rs.getString("bairro"));
			modelLogin.setFone1(rs.getString("fone1"));
			modelLogin.setFone2(rs.getString("fone2"));
			modelLogin.setCelular1(rs.getString("celular1"));
			modelLogin.setCelular2(rs.getString("celular2"));
			modelLogin.setEmail(rs.getString("email"));
			modelLogin.setSite(rs.getString("site"));
			// modelLogin.setSenha(resultado.getString("senha")); não precisa
			
			retornoBusca.add(modelLogin);
			
		}
		return retornoBusca;
	}
	
	// DELETE USUÁRIO *****************************************************************************
	public void deletarUsuario(String codUser) throws Exception {
		String sql = "DELETE FROM model_login WHERE codigo = ? AND useradmin IS false";
		PreparedStatement ps = connection.prepareStatement(sql);
		
		ps.setInt(1, Integer.valueOf(codUser));
		
		ps.executeUpdate();
		connection.commit();
		
	}

}
