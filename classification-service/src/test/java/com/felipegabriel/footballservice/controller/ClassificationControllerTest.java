package com.felipegabriel.footballservice.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.ArrayList;
import java.util.List;

import com.felipegabriel.classificationservice.api.service.ClassificationService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.felipegabriel.classificationservice.api.dto.ClassificationDTO;

@ExtendWith(SpringExtension.class)
@WebMvcTest
public class ClassificationControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	ClassificationService classificationService;
	
	@Test
	void shouldGetClassificationBySeason() throws Exception {
		List<ClassificationDTO> classification = new ArrayList<>();
		classification.add(new ClassificationDTO());
		classification.add(new ClassificationDTO());
		
		when(classificationService.findClassificationBySeason(2021))
			.thenReturn(classification);
		
		RequestBuilder request = MockMvcRequestBuilders
			.get("/football/classification-by-season")
			.param("season", "2021")
			.contentType(MediaType.APPLICATION_JSON);
		
		mockMvc
			.perform(request)
			.andExpect(jsonPath("$", Matchers.hasSize(2)));
	}
}
