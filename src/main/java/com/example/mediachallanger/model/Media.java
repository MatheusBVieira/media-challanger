package com.example.mediachallanger.model;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Media {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Lob
	@Basic(optional = false, fetch = FetchType.LAZY)
	private byte[] media;
	private String tipo;
	@Column(unique = true)
	private String nome;
	private String descricao;
	private String latitude;
	private String longitude;
	private Date dataDeCriacao;
	private LocalDate dataDeUpload;
	private LocalDate dataDaPublicacao;
	
	public Media(byte[] media, String tipo, String nome, String descricao, String latitude, String longitude,
			Date dataDeCriacao) {
		this.media = media;
		this.tipo = tipo;
		this.nome = nome;
		this.descricao = descricao;
		this.latitude = latitude;
		this.longitude = longitude;
		this.dataDeCriacao = dataDeCriacao;
		this.dataDeUpload = LocalDate.now();
		this.dataDaPublicacao = LocalDate.now();
	}
	
	
}
