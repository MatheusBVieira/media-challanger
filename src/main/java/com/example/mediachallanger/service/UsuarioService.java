package com.example.mediachallanger.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.mediachallanger.controller.dto.UsuarioDto;
import com.example.mediachallanger.controller.form.AtualizacaoUsuarioForm;
import com.example.mediachallanger.controller.form.UsuarioForm;
import com.example.mediachallanger.exception.IdNotFoundException;
import com.example.mediachallanger.model.Usuario;
import com.example.mediachallanger.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	public Page<UsuarioDto> lista(Pageable paginacao) {
		Page<Usuario> usuarios = usuarioRepository.findAll(paginacao);
		return UsuarioDto.converter(usuarios);
	}

	public Usuario insere(UsuarioForm form) {
		Usuario usuario = form.converter();
		usuarioRepository.save(usuario);
		return usuario;
	}

	public Usuario atualizar(Long id, AtualizacaoUsuarioForm form) throws IdNotFoundException {
		Optional<Usuario> optional = usuarioRepository.findById(id);
		if (optional.isPresent()) {
			Usuario aluno = form.atualizar(id, usuarioRepository);
			return aluno;
		}
		throw new IdNotFoundException("Problema na atualização do aluno");
	}

	public boolean deleta(Long id) {
		Optional<Usuario> optional = usuarioRepository.findById(id);
		if (optional.isPresent()) {
			usuarioRepository.deleteById(id);
			return true;
		}
		return false;
	}

}
