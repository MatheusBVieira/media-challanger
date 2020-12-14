package com.example.mediachallanger.controller.form;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import com.example.mediachallanger.model.Media;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;

@Getter
public class MediaForm {
	
	private byte[] media;
	private String tipo;
	
	@NotNull
	private String nome;
	private String descricao;
	private String latitude;
	private String longitude;
	
	@NotNull
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataDeCriação;
	
	public void setMedia(byte[] bytes) {
		this.media = bytes;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public Media converter() {
		Media media = new Media(this.media, tipo, nome, descricao, latitude, longitude, dataDeCriação);
		
		return media;
	}
	
	
	
}
