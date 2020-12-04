package com.example.mediachallanger.controller.dto;

import java.util.Date;

import org.springframework.data.domain.Page;

import com.example.mediachallanger.model.Sexo;
import com.example.mediachallanger.model.Usuario;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;

@Getter
public class UsuarioDto {

	private long id;
	private String nomeCompleto;
	private String username;
	private String email;
	private String telefone;
	private Sexo sexo;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataDeNascimento;
	private int idade;

	public UsuarioDto(Usuario usuario) {
		this.id = usuario.getId();
		this.nomeCompleto = usuario.getNomeCompleto();
		this.username = usuario.getUsername();
		this.email = usuario.getEmail();
		this.telefone = usuario.getTelefone();
		this.sexo = usuario.getSexo();
		this.dataDeNascimento = usuario.getDataDeNascimento();
		this.idade = usuario.getIdade();
	}

	public static Page<UsuarioDto> converter(Page<Usuario> alunos) {
		return alunos.map(UsuarioDto::new);
	}

}
