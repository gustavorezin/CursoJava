package servlets;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.DAOUsuarioRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelLogin;

@WebServlet(urlPatterns = {"/ServletUsuarioController"})
public class ServletUsuarioController extends ServletGenericUtil {
	private static final long serialVersionUID = 1L;

	DAOUsuarioRepository dao = new DAOUsuarioRepository();
	
    public ServletUsuarioController() {
    }

	// GET(consulta) ******************************************************************************
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("acao");
		
		try {
			// DELETE COM AJAX ----------
			if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletarajax")) { 
				String cadUser = request.getParameter("codigo");
				dao.deletarUsuario(cadUser);
				response.getWriter().write("Deletado com sucesso!");
				request.getRequestDispatcher("pages/cad-usuario.jsp").forward(request, response);
				
			// BUSCA COM AJAX ----------	
			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarUserAjax")) {
				String nomeBusca = request.getParameter("nomeBusca");
				List<ModelLogin> dadosJsonUser = dao.buscaUserList(nomeBusca);
				ObjectMapper mapper = new ObjectMapper();
				String json = mapper.writeValueAsString(dadosJsonUser);
				response.getWriter().write(json);
			
			// VER E EDITAR ----------	
			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarEditar")) {
				List<ModelLogin> listUsuarios = dao.buscaUserList();
				request.setAttribute("listUsuarios", listUsuarios);
				
				String codigo = request.getParameter("codigo");
				ModelLogin usuario = dao.consultarUsuarioCodigo(Integer.valueOf(codigo)); // pega todos os dados do usuario
				request.setAttribute("modelLogin", usuario); // atributo que vai preencher a tela
				request.getRequestDispatcher("pages/cad-usuario.jsp").forward(request, response);
 				
			} else {
				request.getRequestDispatcher("pages/cad-usuario.jsp").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", e.getMessage());
			request.getRequestDispatcher("erro.jsp").forward(request, response);
		}
	}

	// POST(cadastro) *****************************************************************************
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String msg = "Operação realizada com sucesso!";
			
			String codigo = request.getParameter("codigo");
			String nome = request.getParameter("nome");
			String nomefantasia = request.getParameter("nomefantasia");
			String dataNascimento = request.getParameter("dataNascimento");
			String login = request.getParameter("login");
			String senha = request.getParameter("senha");
			String grupo = request.getParameter("grupoUsuario");
			String salario = request.getParameter("salario");
			String sexo = request.getParameter("sexo");
			String cep = request.getParameter("cep");
			String uf = request.getParameter("uf");
			String cidade = request.getParameter("cidade");
			String logradouro = request.getParameter("logradouro");
			String numero = request.getParameter("numero");
			String bairro = request.getParameter("bairro");
			String fone1 = request.getParameter("fone1");
			String fone2 = request.getParameter("fone2");
			String celular1 = request.getParameter("celular1");
			String celular2 = request.getParameter("celular2");
			String email = request.getParameter("email");
			String site = request.getParameter("site");
			
			salario = salario.split("\\ ")[1].replaceAll("\\.", "").replaceAll("\\,", ".");
			
			ModelLogin modelLogin = new ModelLogin();
			
			modelLogin.setCodigo(codigo != null && !codigo.isEmpty() ? Integer.valueOf(codigo) : null);
			modelLogin.setNome(nome);
			modelLogin.setNomefantasia(nomefantasia);
			modelLogin.setDataNascimento(new Date(new SimpleDateFormat("yyyy-MM-dd").parse(dataNascimento).getTime()));
			modelLogin.setLogin(login);
			modelLogin.setSenha(senha);
			modelLogin.setGrupo(grupo);
			modelLogin.setSalario(Double.valueOf(salario));
			modelLogin.setSexo(sexo);
			modelLogin.setCep(cep);
			modelLogin.setUf(uf);
			modelLogin.setCidade(cidade);
			modelLogin.setLogradouro(logradouro);
			modelLogin.setNumero(numero);
			modelLogin.setBairro(bairro);
			modelLogin.setFone1(fone1);
			modelLogin.setFone2(fone2);
			modelLogin.setCelular1(celular1);
			modelLogin.setCelular2(celular2);
			modelLogin.setEmail(email);
			modelLogin.setSite(site);
			
			if (dao.validarLogin(modelLogin.getLogin()) && modelLogin.getCodigo() == null) {
				msg = "Já existe usuário com o mesmo login!";
			} else {
				
				if (modelLogin.isNovo()) {
					msg = "Novo usuário salvo com sucesso!";
				} else {
					msg = "Usuário atualizado!";
				}

				modelLogin = dao.salvarUsuario(modelLogin, super.getUserLogado(request));
			}
			
			request.setAttribute("msg", msg);
			
			request.setAttribute("modelLogin", modelLogin);
			
			request.getRequestDispatcher("pages/cad-usuario.jsp").forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", e.getMessage());
			request.getRequestDispatcher("erro.jsp").forward(request, response);
		}
	}

}
