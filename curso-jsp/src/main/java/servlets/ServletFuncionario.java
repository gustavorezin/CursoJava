package servlets;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import bean.BEANGraficoSalario;

import dao.DAOFuncionario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelFuncionario;
import util.ReportUtil;

@WebServlet(urlPatterns = {"/ServletFuncionario"})
public class ServletFuncionario extends ServletGenericUtil {
	private static final long serialVersionUID = 1L;

	DAOFuncionario dao = new DAOFuncionario();
	
    public ServletFuncionario() {
    }

	// GET(consulta) ******************************************************************************
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("acao");
		
		try {
			// DELETE COM AJAX ----------
			if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletarajax")) { 
				String codigo = request.getParameter("codigo");
				dao.deletar(codigo);
				response.getWriter().write("Deletado com sucesso!");
				request.getRequestDispatcher("pages/cad-usuario.jsp").forward(request, response);
				
			// BUSCA COM AJAX ----------	
			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarajax")) {
				String nomeBusca = request.getParameter("nomeBusca");
				List<ModelFuncionario> dadosJsonUser = dao.buscaList(nomeBusca);
				ObjectMapper mapper = new ObjectMapper();
				String json = mapper.writeValueAsString(dadosJsonUser);
				response.getWriter().write(json);
			
			// VER E EDITAR ----------	
			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("vereditar")) {
				List<ModelFuncionario> listFuncionarios = dao.buscaList();
				request.setAttribute("listFuncionarios", listFuncionarios);
				
				String codigo = request.getParameter("codigo");
				ModelFuncionario funcionario = dao.consultarCodigo(Integer.valueOf(codigo)); // pega todos os dados do funcionario
				request.setAttribute("mf", funcionario); // atributo que vai preencher a tela
				request.getRequestDispatcher("pages/cad-funcionario.jsp").forward(request, response);
 				
			// IMPRIMIR PDF ----------		
			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("imprimirRelatorioPDF")) {
				 
				String nome = request.getParameter("nome");
				 
				List<ModelFuncionario> mfList = null;
				 
				if (nome == null || nome.isEmpty()) {
					mfList = dao.buscaList();
				}else {
					mfList = dao.buscaList(nome);
				}
				
				HashMap<String, Object> params = new HashMap<String, Object>();
				params.put("PARAM_SUB_REPORT", request.getServletContext().getRealPath("relatorio") + File.separator);
				 
				byte[] relatorio = new ReportUtil().geraReltorioPDF(mfList, "rel_func", params ,request.getServletContext());
				 
				response.setHeader("Content-Disposition", "attachment;filename=arquivo.pdf");
				response.getOutputStream().write(relatorio);	
				
			// IMPRIMIR NA TELA ----------	
			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("imprimirRelatorioHtml")) {
				
				String nome = request.getParameter("nome");
				 
				if (nome == null || nome.isEmpty()) {
					request.setAttribute("listaFunc", dao.buscaList());
				}else {
					request.setAttribute("listaFunc", dao.buscaList(nome));
				}
				 
				request.setAttribute("nome", nome);
				request.getRequestDispatcher("pages/rel-func.jsp").forward(request, response);	
				
			// GERA GRAFICO ----------	
			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("graficoSalario")) {
				
				String cargo = request.getParameter("cargo");
				 
				if (cargo == null || cargo.isEmpty() || cargo == "") {
					BEANGraficoSalario bean = dao.mediaSalario(super.getUserLogado(request));
					ObjectMapper mapper = new ObjectMapper();
					String json = mapper.writeValueAsString(bean);
					response.getWriter().write(json);
				}else {
					BEANGraficoSalario bean = dao.mediaSalario(cargo, super.getUserLogado(request));
					ObjectMapper mapper = new ObjectMapper();
					String json = mapper.writeValueAsString(bean);
					response.getWriter().write(json);
				}
				
				request.setAttribute("cargo", cargo);
				
			} else {
				request.getRequestDispatcher("pages/cad-funcionario.jsp").forward(request, response);
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
			String nome_fantasia = request.getParameter("nome_fantasia");
			String data_nasc = request.getParameter("data_nasc");
			String cpf = request.getParameter("cpf");
			String cargo = request.getParameter("cargo");
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
			String cel1 = request.getParameter("cel1");
			String cel2 = request.getParameter("cel2");
			String email = request.getParameter("email");
			String site = request.getParameter("site");
			
			salario = salario.split("\\ ")[1].replaceAll("\\.", "").replaceAll("\\,", ".");
			
			ModelFuncionario mf = new ModelFuncionario();
			
			mf.setCodigo(codigo != null && !codigo.isEmpty() ? Integer.valueOf(codigo) : null);
			mf.setNome(nome);
			mf.setNome_fantasia(nome_fantasia);
			mf.setData_nasc(new Date(new SimpleDateFormat("yyyy-MM-dd").parse(data_nasc).getTime()));
			mf.setCpf(cpf);
			mf.setCargo(cargo);
			mf.setSalario(Double.valueOf(salario));
			mf.setSexo(sexo);
			mf.setCep(cep);
			mf.setUf(uf);
			mf.setCidade(cidade);
			mf.setLogradouro(logradouro);
			mf.setNumero(numero);
			mf.setBairro(bairro);
			mf.setFone1(fone1);
			mf.setFone2(fone2);
			mf.setCel1(cel1);
			mf.setCel2(cel2);
			mf.setEmail(email);
			mf.setSite(site);
			
			if (dao.validarNome(mf.getNome()) && mf.getCodigo() == null) {
				msg = "Já existe usuário com o mesmo login!";
			} else {
				
				if (mf.isNovo()) {
					msg = "Novo usuário salvo com sucesso!";
				} else {
					msg = "Usuário atualizado!";
				}

				mf = dao.salvar(mf, super.getUserLogado(request));
			}
			
			request.setAttribute("msg", msg);
			
			request.setAttribute("mf", mf);
			
			request.getRequestDispatcher("pages/cad-funcionario.jsp").forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", e.getMessage());
			request.getRequestDispatcher("erro.jsp").forward(request, response);
		}
	}

}
