package model;

import java.io.Serializable;

public class ModelLogin implements Serializable{

	private static final long serialVersionUID = 1L; // compila��o das classes
	
	private Integer codigo;
	private String nome;
	private String email;
	private String login;
	private String senha;
	private String grupo;
	private Boolean useradmin;
	
	public boolean isNovo() { // Verifica se é um novo usu�rio
		if (this.codigo == null) {
			return true; // inserir um novo usu�rio
			
		} else if (this.codigo != null && this.codigo > 0) {
			return false; // atualizar um antigo usu�rio
		}
		return codigo == null;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public Boolean getUseradmin() {
		return useradmin;
	}

	public void setUseradmin(Boolean useradmin) {
		this.useradmin = useradmin;
	}

	
}
