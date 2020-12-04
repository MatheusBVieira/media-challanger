package com.example.mediachallanger.controller.form;

import com.example.mediachallanger.model.Usuario;
import com.example.mediachallanger.repository.UsuarioRepository;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AtualizacaoUsuarioForm {
	
	private String nomeCompleto;
	private String email;
	private String telefone;
	private String password;
	
	public Usuario atualizar(Long id, UsuarioRepository usuarioRepository) {
		Usuario usuario = usuarioRepository.getOne(id);

		usuario.setNomeCompleto(nomeCompleto);
		usuario.setEmail(email);
		usuario.setTelefone(telefone);
		usuario.setPassword(password);

		return usuario;
	}
	
	
}
