package com.crio.xmeme.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import com.crio.xmeme.service.MemesService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;



@ExtendWith(SpringExtension.class)
@WebMvcTest(value = MemesController.class)
public class MemesControllerTest {

	/** The mock mvc. */
	@Autowired
	private MockMvc mockMvc;

	/** The meme service. */
	@MockBean
	private MemesService memeService;

	
	@Autowired
	private ObjectMapper objectMapper;

	
	
	@Test
	public void shouldReturn404WhenGetById() throws Exception {
		final String memeId = "0";
		when(memeService.getMemeById(Mockito.anyString())).thenReturn(Optional.empty());
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/memes/{id}", memeId)
				.accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilder).andExpect(status().isNotFound());
		verify(memeService, times(1)).getMemeById(Mockito.anyString());
	}

	
}