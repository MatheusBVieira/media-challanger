package com.example.mediachallanger.controller;

import java.net.URI;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.mediachallanger.controller.dto.UsuarioDto;
import com.example.mediachallanger.controller.form.AtualizacaoUsuarioForm;
import com.example.mediachallanger.controller.form.UsuarioForm;
import com.example.mediachallanger.exception.IdNotFoundException;
import com.example.mediachallanger.model.Usuario;
import com.example.mediachallanger.service.UsuarioService;


@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@GetMapping
	public Page<UsuarioDto> lista(
			@PageableDefault(sort = "nomeCompleto", direction = Direction.DESC, page = 0, size = 10) Pageable paginacao) {
		return usuarioService.lista(paginacao);
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<UsuarioDto> cadastrar(@RequestBody @Valid UsuarioForm form, UriComponentsBuilder uriBuilder) {
		Usuario usuario = usuarioService.insere(form);

		URI uri = uriBuilder.path("/usuario/{id}").buildAndExpand(usuario.getId()).toUri();
		return ResponseEntity.created(uri).body(new UsuarioDto(usuario));
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<UsuarioDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoUsuarioForm form) {
		try {
			Usuario usuario = usuarioService.atualizar(id, form);
			return ResponseEntity.ok(new UsuarioDto(usuario));
		} catch (IdNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.notFound().build();
		}

	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id) {
		boolean deletado = usuarioService.deleta(id);
		if (deletado) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

}
