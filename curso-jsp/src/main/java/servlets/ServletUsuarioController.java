package servlets;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.DAOUsuarioRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelLogin;
import util.ReportUtil;

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
				String codigo = request.getParameter("codigo");
				dao.deletarUsuario(codigo);
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
 			
			// IMPRIMIR NA TELA ----------	
			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("imprimirRelatorioHtml")) {
				
				String nome = request.getParameter("nome");
				 
				if (nome == null || nome.isEmpty()) {
					request.setAttribute("listaUser", dao.buscaUserList());
				}else {
					request.setAttribute("listaUser", dao.buscaUserList(nome));
				}
				 
				request.setAttribute("nome", nome);
				request.getRequestDispatcher("pages/rel-user.jsp").forward(request, response);
				 
			 } else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("imprimirRelatorioPDF")) {
				 
				 String nome = request.getParameter("nome");
				 
				 List<ModelLogin> modelLogins = null;
				 
				 if (nome == null || nome.isEmpty()) {
						request.setAttribute("listaUser", dao.buscaUserList());
					}else {
						request.setAttribute("listaUser", dao.buscaUserList(nome));
					}
				 
				 
				 HashMap<String, Object> params = new HashMap<String, Object>();
				 params.put("PARAM_SUB_REPORT", request.getServletContext().getRealPath("relatorio") + File.separator);
				 
				 byte[] relatorio = new ReportUtil().geraReltorioPDF(modelLogins, "rel-user-jsp", params ,request.getServletContext());
				 
				 
				 response.setHeader("Content-Disposition", "attachment;filename=arquivo.pdf");
				 response.getOutputStream().write(relatorio);
				 
			 }else {
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
			String email = request.getParameter("email");
			String login = request.getParameter("login");
			String senha = request.getParameter("senha");
			String grupo = request.getParameter("grupoUsuario");
			
			
			ModelLogin modelLogin = new ModelLogin();
			
			modelLogin.setCodigo(codigo != null && !codigo.isEmpty() ? Integer.valueOf(codigo) : null);
			modelLogin.setNome(nome);
			modelLogin.setEmail(email);
			modelLogin.setLogin(login);
			modelLogin.setSenha(senha);
			modelLogin.setGrupo(grupo);
			
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
