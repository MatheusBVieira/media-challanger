package com.example.mediachallanger.controller.form;

import java.util.Date;

import com.example.mediachallanger.model.Sexo;
import com.example.mediachallanger.model.Usuario;
import com.example.mediachallanger.util.IdadeUtil;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioForm {
	
	private String nomeCompleto;
	private String username;
	private String email;
	private String telefone;
	private Sexo sexo;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataDeNascimento;
	private String password;
	
	public Usuario converter() {
		Usuario usuario = new Usuario(nomeCompleto, username, email, telefone, sexo, dataDeNascimento, IdadeUtil.calculaIdade(this.dataDeNascimento),password);
		return usuario;
	}
	
}
