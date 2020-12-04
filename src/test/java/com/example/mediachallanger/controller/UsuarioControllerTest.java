package com.example.mediachallanger.controller;

import java.net.URI;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles(value = "test")
public class UsuarioControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	@Order(1)
	public void deveInserirERetornarListaDeUsuarios() throws Exception {
		URI uri = new URI("/usuario");
		String json = "{\r\n" + 
				"		\"nomeCompleto\": \"Matheus Bruggemann Vieira\",\r\n" + 
				"		\"username\": \"noft\",\r\n" + 
				"		\"email\": \"matheusbvieira@hotmail.com\",\r\n" + 
				"		\"telefone\": \"48991466677\",\r\n" + 
				"		\"sexo\": \"MASCULINO\",\r\n" + 
				"		\"dataDeNascimento\": \"29/11/2000\"\r\n" + 
				"}";
		
		mockMvc.perform(MockMvcRequestBuilders.post(uri).content(json).contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().is(201));
		
		mockMvc.perform(MockMvcRequestBuilders.get("/usuario").accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.content[-1].nomeCompleto").value("Matheus Bruggemann Vieira"));
	}
	
	@Test
	@Order(2)
	public void deveriaDevolverUsuarioAlterado() throws Exception {
		String json = "{\r\n" + 
				"		\"nomeCompleto\": \"Matheus Bruggemann Vieira\",\r\n" + 
				"		\"email\": \"matheusbruvieira@gmail.com\",\r\n" + 
				"		\"telefone\": \"48991466677\",\r\n" + 
				"		\"password\": \"1234567\"\r\n" + 
				"}";

		mockMvc.perform(
				MockMvcRequestBuilders.put("/usuario/{id}", 1).content(json).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.email").value("matheusbruvieira@gmail.com"));
	}
	
	@Test
	@Order(3)
	public void deveriaRetornar200AoExcluirUmUsuario() throws Exception {
		mockMvc.perform(
				MockMvcRequestBuilders.delete("/usuario/{id}", 1))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
}
