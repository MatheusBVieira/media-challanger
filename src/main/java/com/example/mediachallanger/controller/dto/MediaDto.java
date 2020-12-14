package com.example.mediachallanger.controller.dto;

import java.time.LocalDate;

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
	private LocalDate dataDeCriação;
	private LocalDate dataDeUpload;
	private LocalDate dataDaPublicação;
		
	public MediaDto(Media media) {
		this.id = media.getId();
		this.tipo = media.getTipo();
		this.nome = media.getNome();
		this.descricao = media.getDescricao();
		this.latitude = media.getLatitude();
		this.longitude = media.getLongitude();
		this.dataDeCriação = media.getDataDeCriação();
		this.dataDaPublicação = media.getDataDaPublicação();
		this.dataDeUpload = media.getDataDeUpload();
	}

	public static Page<MediaDto> converter(Page<Media> medias) {
		return medias.map(MediaDto::new);
	}

	public static MediaDto converter(Media media) {
		return new MediaDto(media);
	}
	
}
