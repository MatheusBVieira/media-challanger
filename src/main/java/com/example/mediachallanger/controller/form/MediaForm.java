package com.example.mediachallanger.controller.form;

import java.time.LocalDate;

import com.example.mediachallanger.model.Media;

import lombok.Getter;

@Getter
public class MediaForm {
	
	private byte[] media;
	private String tipo;
	private String nome;
	private String descricao;
	private String latitude;
	private String longitude;
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
