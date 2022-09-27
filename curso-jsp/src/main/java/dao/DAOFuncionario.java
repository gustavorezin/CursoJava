package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.BEANGraficoSalario;
import connection.SingleConnectionBanco;
import model.ModelFuncionario;

public class DAOFuncionario {
	
	private Connection connection;
	
	public DAOFuncionario() {
		connection = SingleConnectionBanco.getConnection();
	}
	
	// VALIDAR NOME EXISTENTE *********************************************************************
	public boolean validarNome(String nome) throws Exception {
		String sql = "SELECT COUNT(1) > 0 AS existe FROM funcionario WHERE nome = ?";
		PreparedStatement ps = connection.prepareStatement(sql);
		
		ps.setObject(1, nome);
		
		ResultSet rs = ps.executeQuery();

		if (rs.next()) { // se tem uma linha na tabela, retorna verdadeiro
			return rs.getBoolean("existe");
		}
		return false;
	}
	
	// CADASTRO/ATUALIZAÇÃO ***********************************************************************
	public ModelFuncionario salvar(ModelFuncionario funcionario, Integer userLogado) throws Exception {
		
		if(funcionario.isNovo()) { // um novo usuário
			String sql = "INSERT INTO funcionario("
					   + "nome, nome_fantasia, cpf, data_nasc, sexo, cargo, salario, "
					   + "cep, uf, cidade, logradouro, numero, bairro, "
					   + "fone1, fone2, cel1, cel2, email, site, cod_user) "
					   + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
			
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps.setObject(1, funcionario.getNome());
			ps.setObject(2, funcionario.getNome_fantasia());
			ps.setObject(3, funcionario.getCpf());
			ps.setObject(4, funcionario.getData_nasc());
			ps.setObject(5, funcionario.getSexo());
			ps.setObject(6, funcionario.getCargo());
			ps.setObject(7, funcionario.getSalario());
			ps.setObject(8, funcionario.getCep());
			ps.setObject(9, funcionario.getUf());
			ps.setObject(10, funcionario.getCidade());
			ps.setObject(11, funcionario.getLogradouro());
			ps.setObject(12, funcionario.getNumero());
			ps.setObject(13, funcionario.getBairro());
			ps.setObject(14, funcionario.getFone1());
			ps.setObject(15, funcionario.getFone2());
			ps.setObject(16, funcionario.getCel1());
			ps.setObject(17, funcionario.getCel2());
			ps.setObject(18, funcionario.getEmail());
			ps.setObject(19, funcionario.getSite());
			ps.setObject(20, userLogado);
			
			ps.execute();
			connection.commit();
			
		} else { // atualizar usuário existente
			String sql = "UPDATE funcionario SET "
					   + "nome=?, nome_fantasia=?, cpf=?, data_nasc=?, sexo=?, cargo=?, salario=?, "
					   + "cep=?, uf=?, cidade=?, logradouro=?, numero=?, bairro=?, "
					   + "fone1=?, fone2=?, cel1=?, cel2=?, email=?, site=? "
					   + "WHERE codigo = ?;";
			
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps.setObject(1, funcionario.getNome());
			ps.setObject(2, funcionario.getNome_fantasia());
			ps.setObject(3, funcionario.getCpf());
			ps.setObject(4, funcionario.getData_nasc());
			ps.setObject(5, funcionario.getSexo());
			ps.setObject(6, funcionario.getCargo());
			ps.setObject(7, funcionario.getSalario());
			ps.setObject(8, funcionario.getCep());
			ps.setObject(9, funcionario.getUf());
			ps.setObject(10, funcionario.getCidade());
			ps.setObject(11, funcionario.getLogradouro());
			ps.setObject(12, funcionario.getNumero());
			ps.setObject(13, funcionario.getBairro());
			ps.setObject(14, funcionario.getFone1());
			ps.setObject(15, funcionario.getFone2());
			ps.setObject(16, funcionario.getCel1());
			ps.setObject(17, funcionario.getCel2());
			ps.setObject(18, funcionario.getEmail());
			ps.setObject(19, funcionario.getSite());
			ps.setObject(20, funcionario.getCodigo());
			
			ps.executeUpdate();
			connection.commit();
			return this.consultarNome(funcionario.getNome()); // mantém na tela usuário atualizado
		}
		
		return null;
	}
	
	// CONSULTAR NOME *****************************************************************************
	public ModelFuncionario consultarNome(String nome) throws Exception {
		
		ModelFuncionario mf = new ModelFuncionario();
		
		String sql = "SELECT * FROM funcionario WHERE nome = ?;";
		PreparedStatement ps = connection.prepareStatement(sql);

		ps.setObject(1, nome);
		
		ResultSet rs = ps.executeQuery();
		
		if (rs.next()) { // se tem mais uma linha na tabela
			mf.setCodigo(rs.getInt("codigo"));
			mf.setNome(rs.getString("nome"));
			mf.setNome_fantasia(rs.getString("nome_fantasia"));
			mf.setCpf(rs.getString("cpf"));
			mf.setData_nasc(rs.getDate("data_nasc"));
			mf.setSexo(rs.getString("sexo"));
			mf.setCargo(rs.getString("cargo"));
			mf.setSalario(rs.getDouble("salario"));
			mf.setCep(rs.getString("cep"));
			mf.setUf(rs.getString("uf"));
			mf.setCidade(rs.getString("cidade"));
			mf.setLogradouro(rs.getString("logradouro"));
			mf.setNumero(rs.getString("numero"));
			mf.setBairro(rs.getString("bairro"));
			mf.setFone1(rs.getString("fone1"));
			mf.setFone2(rs.getString("fone2"));
			mf.setCel1(rs.getString("cel1"));
			mf.setCel2(rs.getString("cel2"));
			mf.setEmail(rs.getString("email"));
			mf.setSite(rs.getString("site"));
			}
		return mf;
	}
	
	// CONSULTAR CODIGO ***************************************************************************
	public ModelFuncionario consultarCodigo(Integer codigo) throws Exception {
		
		ModelFuncionario mf = new ModelFuncionario();
		
		String sql = "SELECT * FROM funcionario WHERE codigo = ?;";
		
		PreparedStatement ps = connection.prepareStatement(sql);
		
		ps.setObject(1, codigo);
		
		ResultSet rs = ps.executeQuery();
		
		if (rs.next()) { // se tem mais uma linha na tabela
			mf.setCodigo(rs.getInt("codigo"));
			mf.setNome(rs.getString("nome"));
			mf.setNome_fantasia(rs.getString("nome_fantasia"));
			mf.setCpf(rs.getString("cpf"));
			mf.setData_nasc(rs.getDate("data_nasc"));
			mf.setSexo(rs.getString("sexo"));
			mf.setCargo(rs.getString("cargo"));
			mf.setSalario(rs.getDouble("salario"));
			mf.setCep(rs.getString("cep"));
			mf.setUf(rs.getString("uf"));
			mf.setCidade(rs.getString("cidade"));
			mf.setLogradouro(rs.getString("logradouro"));
			mf.setNumero(rs.getString("numero"));
			mf.setBairro(rs.getString("bairro"));
			mf.setFone1(rs.getString("fone1"));
			mf.setFone2(rs.getString("fone2"));
			mf.setCel1(rs.getString("cel1"));
			mf.setCel2(rs.getString("cel2"));
			mf.setEmail(rs.getString("email"));
			mf.setSite(rs.getString("site"));
		}
		return mf;
	}
	
	
	// BUSCAR/LISTAR POR NOME *********************************************************************
	public List<ModelFuncionario> buscaList(String nome) throws Exception {
		
		List<ModelFuncionario> retornoBusca = new ArrayList<ModelFuncionario>();
		
		String sql = "SELECT * FROM funcionario WHERE nome LIKE ?;";
		
		PreparedStatement ps = connection.prepareStatement(sql);
		
		ps.setString(1, "%" + nome + "%");
		
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) { // percorrer as linhas da tabela
			ModelFuncionario mf = new ModelFuncionario();
			mf.setCodigo(rs.getInt("codigo"));
			mf.setNome(rs.getString("nome"));
			mf.setNome_fantasia(rs.getString("nome_fantasia"));
			mf.setCpf(rs.getString("cpf"));
			mf.setData_nasc(rs.getDate("data_nasc"));
			mf.setSexo(rs.getString("sexo"));
			mf.setCargo(rs.getString("cargo"));
			mf.setSalario(rs.getDouble("salario"));
			mf.setCep(rs.getString("cep"));
			mf.setUf(rs.getString("uf"));
			mf.setCidade(rs.getString("cidade"));
			mf.setLogradouro(rs.getString("logradouro"));
			mf.setNumero(rs.getString("numero"));
			mf.setBairro(rs.getString("bairro"));
			mf.setFone1(rs.getString("fone1"));
			mf.setFone2(rs.getString("fone2"));
			mf.setCel1(rs.getString("cel1"));
			mf.setCel2(rs.getString("cel2"));
			mf.setEmail(rs.getString("email"));
			mf.setSite(rs.getString("site"));
			
			retornoBusca.add(mf);
		}
		return retornoBusca;
	}
	
	// BUSCAR/LISTAR ******************************************************************************
	public List<ModelFuncionario> buscaList() throws Exception {
		
		List<ModelFuncionario> retornoBusca = new ArrayList<ModelFuncionario>();
		
		String sql = "SELECT * FROM funcionario";
		
		PreparedStatement ps = connection.prepareStatement(sql);
		
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) { // percorrer as linhas da tabela
			ModelFuncionario mf = new ModelFuncionario();
			mf.setCodigo(rs.getInt("codigo"));
			mf.setNome(rs.getString("nome"));
			mf.setNome_fantasia(rs.getString("nome_fantasia"));
			mf.setCpf(rs.getString("cpf"));
			mf.setData_nasc(rs.getDate("data_nasc"));
			mf.setSexo(rs.getString("sexo"));
			mf.setCargo(rs.getString("cargo"));
			mf.setSalario(rs.getDouble("salario"));
			mf.setCep(rs.getString("cep"));
			mf.setUf(rs.getString("uf"));
			mf.setCidade(rs.getString("cidade"));
			mf.setLogradouro(rs.getString("logradouro"));
			mf.setNumero(rs.getString("numero"));
			mf.setBairro(rs.getString("bairro"));
			mf.setFone1(rs.getString("fone1"));
			mf.setFone2(rs.getString("fone2"));
			mf.setCel1(rs.getString("cel1"));
			mf.setCel2(rs.getString("cel2"));
			mf.setEmail(rs.getString("email"));
			mf.setSite(rs.getString("site"));
			
			retornoBusca.add(mf);
		}
		return retornoBusca;
	}
	
	// MEDIA SALARIO ******************************************************************************
	public BEANGraficoSalario mediaSalario(Integer userLogado) throws Exception {
		String sql = "SELECT avg(salario) as media_salarial, cargo from funcionario where cod_user = ? group by cargo;";
		PreparedStatement ps = connection.prepareStatement(sql);
		
		ps.setObject(1, userLogado);
		
		ResultSet rs = ps.executeQuery();
		
		List<Double> salarioList = new ArrayList<Double>();
		List<String> cargoList = new ArrayList<String>();
		
		BEANGraficoSalario bean = new BEANGraficoSalario();
		
		while (rs.next()) {
			Double media_salarial = rs.getDouble("media_salarial");
			String cargo = rs.getString("cargo");
			
			salarioList.add(media_salarial);
			cargoList.add(cargo);
		}
		
		bean.setSalarioList(salarioList);
		bean.setCargoList(cargoList);
		
		return bean;
	}
	
	// MEDIA SALARIO CARGO ************************************************************************
	public BEANGraficoSalario mediaSalario(String cargo, Integer userLogado) throws Exception {
		String sql = "SELECT avg(salario) as media_salarial, cargo from funcionario where cargo = ? AND cod_user = ? group by cargo;";
		PreparedStatement ps = connection.prepareStatement(sql);
		
		ps.setObject(1, cargo);
		ps.setObject(2, userLogado);
		
		ResultSet rs = ps.executeQuery();
		
		List<Double> salarioList = new ArrayList<Double>();
		List<String> cargoList = new ArrayList<String>();
		
		BEANGraficoSalario bean = new BEANGraficoSalario();
		
		while (rs.next()) {
			Double media_salarial = rs.getDouble("media_salarial");
			String cargoFunc = rs.getString("cargo");
			
			salarioList.add(media_salarial);
			cargoList.add(cargoFunc);
		}
		
		bean.setSalarioList(salarioList);
		bean.setCargoList(cargoList);
		
		return bean;
	}
	
	
	// DELETE  ************************************************************************************
	public void deletar(String codigo) throws Exception {
		String sql = "DELETE FROM funcionario WHERE codigo = ?";
		PreparedStatement ps = connection.prepareStatement(sql);
		
		ps.setInt(1, Integer.valueOf(codigo));
		
		ps.executeUpdate();
		connection.commit();
		
	}

}
