package com.example.mediachallanger.controller.form;

import java.time.LocalDate;

import com.example.mediachallanger.model.Media;
import com.example.mediachallanger.repository.MediaRepository;

import lombok.Getter;

@Getter
public class MediaAtualizacaoForm {

	private String nome;
	private String descricao;
	private LocalDate dataDeCriação;
	
	public Media atualizar(Long id, MediaRepository mediaRepository) {
		Media media = mediaRepository.getOne(id);
		
		media.setNome(nome);
		media.setDescricao(descricao);
		media.setDataDeCriação(dataDeCriação);
		
		return media;
	}
	
}
