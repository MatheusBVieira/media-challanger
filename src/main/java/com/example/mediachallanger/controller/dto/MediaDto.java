package com.example.mediachallanger.controller.dto;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.data.domain.Page;

import com.example.mediachallanger.model.Media;

import lombok.Getter;

@Getter
public class MediaDto {
	
	private long id;
	private String tipo;
	private String nome;
	private String descricao;
	private String latitude;
	private String longitude;
	private Date DataDeCriacao;
	private LocalDate dataDeUpload;
	private LocalDate dataDaPublicacao;
		
	public MediaDto(Media media) {
		this.id = media.getId();
		this.tipo = media.getTipo();
		this.nome = media.getNome();
		this.descricao = media.getDescricao();
		this.latitude = media.getLatitude();
		this.longitude = media.getLongitude();
		this.DataDeCriacao = media.getDataDeCriacao();
		this.dataDaPublicacao = media.getDataDaPublicacao();
		this.dataDeUpload = media.getDataDeUpload();
	}

	public static Page<MediaDto> converter(Page<Media> medias) {
		return medias.map(MediaDto::new);
	}

	public static MediaDto converter(Media media) {
		return new MediaDto(media);
	}
	
}
