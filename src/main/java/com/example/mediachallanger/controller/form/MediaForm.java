package com.example.mediachallanger.controller.form;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.example.mediachallanger.model.Media;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
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
	private Date dataDeCriacao;
	
	public void setMedia(byte[] bytes) {
		this.media = bytes;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public Media converter() {
		Media media = new Media(this.media, tipo, nome, descricao, latitude, longitude, dataDeCriacao);
		
		return media;
	}
	
	
	
}
