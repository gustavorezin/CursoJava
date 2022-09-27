package filter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import connection.SingleConnectionBanco;
import dao.DAOVersionadorBanco;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@WebFilter(urlPatterns = {"/pages/*"}) // intercepta todas as requisições que vierem do projeto ou mapeamento
public class FilterAutenticacao implements Filter {
	
	private static Connection connection;

    public FilterAutenticacao() {

    }
	
    // DESTROY ####################################################################################
    // encerra os processos quando o servidor é parado
	public void destroy() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// DO FILTER ##################################################################################
	// onde tudo acontece: intercepta as requisições e as respostas no sistema
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		try {
			HttpServletRequest req = (HttpServletRequest) request;
			HttpSession session = req.getSession();
			
			String usuarioLogado = (String) session.getAttribute("usuario");
			
			String urlParaAutenticar = req.getServletPath();/*Url que está sendo acessada*/
			
			// Validar se está logado senão redireciona para a tela de login 
			if (usuarioLogado == null  && 
					!urlParaAutenticar.equalsIgnoreCase("/pages/ServletLogin")) {/*Não está logado*/
				
				RequestDispatcher redireciona = request.getRequestDispatcher("/index.jsp?url=" + urlParaAutenticar);
				request.setAttribute("msg", "Por favor realize o login!");
				redireciona.forward(request, response);
				return; /*Para a execução e redireciona para o login*/
				
			}else {
				chain.doFilter(request, response);
			}
			connection.commit(); // deu tudo certo, então faz o commit das alterações no banco de dados
		} catch (Exception e) {
			e.printStackTrace();
			
			RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);
			
			try {
				connection.rollback();
			} catch(SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	// INIT #######################################################################################
	// inicia os processos ou recursos quando o servidor sobe o projeto
	public void init(FilterConfig fConfig) throws ServletException {
		connection = SingleConnectionBanco.getConnection();
		
		DAOVersionadorBanco daoVersionadorBanco = new DAOVersionadorBanco();
		
		String caminhoPastaSql = fConfig.getServletContext().getRealPath("versionadorbancosql") + File.separator;
		
		File[] arqSqlList = new File(caminhoPastaSql).listFiles();
		
		try {
			
			for (File file : arqSqlList) {
				boolean arquivoRodado = daoVersionadorBanco.arquivoSqlRodado(file.getName());
			
				if(!arquivoRodado) {
					FileInputStream entradaArquivo = new FileInputStream(file);
					
					Scanner lerArquivo = new Scanner(entradaArquivo, "UTF-8");
					
					StringBuilder sql = new StringBuilder();
					
					while (lerArquivo.hasNext()){
						sql.append(lerArquivo.nextLine());
						sql.append("\n"); 
					}
					
					connection.prepareStatement(sql.toString()).execute();
					daoVersionadorBanco.salvaArquivoSqlRodado(file.getName());

					connection.commit();
					lerArquivo.close();
					
				}
				
			}
			
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

}
