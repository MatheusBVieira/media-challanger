package com.example.mediachallanger.controller.form;

import java.util.Date;

import com.example.mediachallanger.model.Media;
import com.example.mediachallanger.repository.MediaRepository;

import lombok.Getter;

@Getter
public class MediaAtualizacaoForm {

	private String nome;
	private String descricao;
	private Date DataDeCriacao;
	
	public Media atualizar(Long id, MediaRepository mediaRepository) {
		Media media = mediaRepository.getOne(id);
		
		media.setNome(nome);
		media.setDescricao(descricao);
		media.setDataDeCriacao(DataDeCriacao);
		
		return media;
	}
	
}
