package com.example.mediachallanger.controller;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URLConnection;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.mediachallanger.controller.dto.MediaDto;
import com.example.mediachallanger.controller.form.MediaAtualizacaoForm;
import com.example.mediachallanger.controller.form.MediaForm;
import com.example.mediachallanger.model.Media;
import com.example.mediachallanger.service.MediaService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/media")
public class MediaController {

	@Autowired
	private MediaService mediaService;

	@GetMapping()
	public ResponseEntity<Resource> lista(@RequestParam(value = "nome") String nome,
			@PageableDefault(sort = "nome", direction = Direction.DESC, page = 0, size = 10) Pageable paginacao) throws Exception {
		try {
			Media media = mediaService.buscaUm(nome).get();
			return retornoMedia(media);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error downloading file");
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Resource> detalhar(@PathVariable Long id) throws Exception {

		try {
			Media media = mediaService.buscaUm(id).get();
			return retornoMedia(media);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error downloading file");
		}
	}

	@PostMapping
	@Transactional
	@RequestMapping(consumes = "multipart/form-data")
	public ResponseEntity<MediaDto> cadastrar(@RequestParam("data") @Valid String data,
			@RequestParam("media") MultipartFile file, UriComponentsBuilder uriBuilder) {
		MediaForm form = null;

		byte[] bytes;
		try {
			bytes = file.getBytes();

			form = new ObjectMapper().readValue(data, MediaForm.class);
			form.setMedia(bytes);
			
			File arq = new File(file.getOriginalFilename());
			String tipo = URLConnection.getFileNameMap().getContentTypeFor(arq.getName());
			form.setTipo(tipo);
		} catch (IOException e) {
			e.printStackTrace();
		}

		Media media = mediaService.insere(form);

		URI uri = uriBuilder.path("/media/{id}").buildAndExpand(media.getId()).toUri();
		return ResponseEntity.created(uri).body(new MediaDto(media));
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<Resource> atualizar(@PathVariable Long id, @RequestBody @Valid MediaAtualizacaoForm form,
			UriComponentsBuilder uriBuilder) throws Exception {
		try {
			Media media = mediaService.atualiza(id, form);
			return retornoMedia(media);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error downloading file");
		}
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id) {
		boolean deletado = mediaService.deleta(id);
		if (deletado) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
	
	private ResponseEntity<Resource> retornoMedia(Media media) {
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(media.getTipo().toString()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + media.getNome() + "\"")
				.body(new ByteArrayResource(media.getMedia()));
	}

}
