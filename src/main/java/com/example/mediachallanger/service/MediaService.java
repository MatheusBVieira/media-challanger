package com.example.mediachallanger.service;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mediachallanger.controller.dto.MediaDto;
import com.example.mediachallanger.controller.form.MediaAtualizacaoForm;
import com.example.mediachallanger.controller.form.MediaForm;
import com.example.mediachallanger.exception.IdNotFoundException;
import com.example.mediachallanger.model.Media;
import com.example.mediachallanger.repository.MediaRepository;

@Service
public class MediaService {
	
	@Autowired
	private MediaRepository mediaRepository;

	public Media insere(@Valid MediaForm form) {
		Media media = form.converter();
		mediaRepository.save(media);
		return media;
	}
	
	public MediaDto detalhar(Long id) {
		return buscaUm(id).map(media -> MediaDto.converter(media)).orElseThrow(IllegalArgumentException::new);
	}
	

	public Media atualiza(Long id, @Valid MediaAtualizacaoForm form) throws IdNotFoundException {
		
		Optional<Media> optional = buscaUm(id);
		if (optional.isPresent()) {
			Media media = form.atualizar(id, mediaRepository);
			return media;
		}
		throw new IdNotFoundException("Problema na atualização do aluno");
	}

	public boolean deleta(Long id) {
		Optional<Media> optional = buscaUm(id);
		if (optional.isPresent()) {
			mediaRepository.deleteById(id);
			return true;
		}
		return false;
	}

	public Optional<Media> buscaUm(Long idMedia) {
		return mediaRepository.findById(idMedia);
	}
	
	public Optional<Media> buscaUm(String nome) {
		return mediaRepository.findByNome(nome);
	}
	
}
