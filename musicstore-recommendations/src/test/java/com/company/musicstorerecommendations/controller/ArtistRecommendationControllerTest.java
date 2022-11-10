package com.company.musicstorerecommendations.controller;

import com.company.musicstorerecommendations.models.ArtistRecommendation;
import com.company.musicstorerecommendations.repository.ArtistRecommendationRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ArtistRecommendationController.class)
public class ArtistRecommendationControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ArtistRecommendationRepository artistRecommendationRepository;
    private ObjectMapper mapper = new ObjectMapper();
    private String inputArtistRecommendationJson;
    private String outputArtistRecommendationJson;
    private ArtistRecommendation outputArtistRecommendationModel;
    @Before
    public void setUp() throws Exception {
        ArtistRecommendation inputArtistRecommendationModel = new ArtistRecommendation();
        inputArtistRecommendationModel.setArtistId(2);
        inputArtistRecommendationModel.setUserId(4);
        inputArtistRecommendationModel.setLiked(true);

        inputArtistRecommendationJson = mapper.writeValueAsString(inputArtistRecommendationModel);

        outputArtistRecommendationModel = new ArtistRecommendation();
        outputArtistRecommendationModel.setArtistId(2);
        outputArtistRecommendationModel.setUserId(4);
        outputArtistRecommendationModel.setLiked(true);
        outputArtistRecommendationModel.setArtistRecommendationId(1);

        outputArtistRecommendationJson = mapper.writeValueAsString(outputArtistRecommendationModel);

        doReturn(outputArtistRecommendationModel).when(artistRecommendationRepository).save(inputArtistRecommendationModel);
    }

    @Test
    public void shouldAddArtistRecommendation() throws Exception {
        mockMvc.perform(post("/artist-recommendation")
                        .content(inputArtistRecommendationJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputArtistRecommendationJson));
    }

    @Test
    public void shouldUpdateArtistRecommendation() throws Exception {
        ArtistRecommendation outputArtistRecommendationModel2 = new ArtistRecommendation();
        outputArtistRecommendationModel2.setArtistId(4);
        outputArtistRecommendationModel2.setUserId(3);
        outputArtistRecommendationModel2.setLiked(false);
        outputArtistRecommendationModel2.setArtistRecommendationId(1);

        String outputArtistRecommendationJson2 = mapper.writeValueAsString(outputArtistRecommendationModel2);

        mockMvc.perform(put("/artist-recommendation")
                        .content(outputArtistRecommendationJson2)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldDeleteArtistRecommendation() throws Exception {
        mockMvc.perform(delete("/artist-recommendation/{id}", 1))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldGetArtistRecommendationById() throws Exception {
        doReturn(Optional.of(outputArtistRecommendationModel)).when(artistRecommendationRepository).findById(Long.valueOf(1));
        mockMvc.perform(get("/artist-recommendation/{id}", 1))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputArtistRecommendationJson));
    }

    @Test
    public void shouldGetAllArtistRecommendations() throws Exception {
        String outputArtistRecommendations = mapper.writeValueAsString(Arrays.asList(outputArtistRecommendationModel));
        doReturn(Arrays.asList(outputArtistRecommendationModel)).when(artistRecommendationRepository).findAll();
        mockMvc.perform(get("/artist-recommendation"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputArtistRecommendations));
    }
}