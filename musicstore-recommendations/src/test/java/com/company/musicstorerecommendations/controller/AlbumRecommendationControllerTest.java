package com.company.musicstorerecommendations.controller;

import com.company.musicstorerecommendations.models.AlbumRecommendation;
import com.company.musicstorerecommendations.repository.AlbumRecommendationRepository;
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
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AlbumRecommendationController.class)
public class AlbumRecommendationControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AlbumRecommendationRepository albumRecommendationRepository;

    private ObjectMapper mapper = new ObjectMapper();

    private String inputAlbumRecommendationJson;
    private String outputAlbumRecommendationJson;
    private AlbumRecommendation outputAlbumRecommendationModel;

    @Before
    public void setUp() throws Exception {
        AlbumRecommendation inputAlbumRecommendationModel = new AlbumRecommendation();
        inputAlbumRecommendationModel.setAlbumId(2);
        inputAlbumRecommendationModel.setUserId(4);
        inputAlbumRecommendationModel.setLiked(true);

        inputAlbumRecommendationJson = mapper.writeValueAsString(inputAlbumRecommendationModel);

        outputAlbumRecommendationModel = new AlbumRecommendation();
        outputAlbumRecommendationModel.setAlbumId(2);
        outputAlbumRecommendationModel.setUserId(4);
        outputAlbumRecommendationModel.setLiked(true);
        outputAlbumRecommendationModel.setAlbumRecommendationId(1);

        outputAlbumRecommendationJson = mapper.writeValueAsString(outputAlbumRecommendationModel);

        doReturn(outputAlbumRecommendationModel).when(albumRecommendationRepository).save(inputAlbumRecommendationModel);
    }

    @Test
    public void shouldAddAlbumRecommendation() throws Exception {
        mockMvc.perform(post("/album-recommendation")
                        .content(inputAlbumRecommendationJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputAlbumRecommendationJson));
    }

    @Test
    public void shouldUpdateAlbumRecommendation() throws Exception {
        AlbumRecommendation outputAlbumRecommendationModel2 = new AlbumRecommendation();
        outputAlbumRecommendationModel2.setAlbumId(4);
        outputAlbumRecommendationModel2.setUserId(3);
        outputAlbumRecommendationModel2.setLiked(false);
        outputAlbumRecommendationModel2.setAlbumRecommendationId(1);

        String outputAlbumRecommendationJson2 = mapper.writeValueAsString(outputAlbumRecommendationModel2);

        mockMvc.perform(put("/album-recommendation")
                        .content(outputAlbumRecommendationJson2)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldDeleteAlbumRecommendation() throws Exception {
        mockMvc.perform(delete("/album-recommendation/{id}", 1))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldGetAlbumRecommendationById() throws Exception {
        doReturn(Optional.of(outputAlbumRecommendationModel)).when(albumRecommendationRepository).findById(Long.valueOf(1));
        mockMvc.perform(get("/album-recommendation/{id}", 1))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputAlbumRecommendationJson));
    }

    @Test
    public void shouldGetAllAlbumRecommendations() throws Exception {
        String outputAlbumRecommendations = mapper.writeValueAsString(Arrays.asList(outputAlbumRecommendationModel));
        doReturn(Arrays.asList(outputAlbumRecommendationModel)).when(albumRecommendationRepository).findAll();
        mockMvc.perform(get("/album-recommendation"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputAlbumRecommendations));
    }
}