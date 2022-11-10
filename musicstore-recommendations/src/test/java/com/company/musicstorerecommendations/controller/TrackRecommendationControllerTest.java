package com.company.musicstorerecommendations.controller;

import com.company.musicstorerecommendations.models.TrackRecommendation;
import com.company.musicstorerecommendations.repository.TrackRecommendationRepository;
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
@WebMvcTest(TrackRecommendationController.class)
public class TrackRecommendationControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private TrackRecommendationRepository trackRecommendationRepository;

    private ObjectMapper mapper = new ObjectMapper();

    private String inputTrackRecommendationJson;
    private String outputTrackRecommendationJson;
    private TrackRecommendation outputTrackRecommendationModel;

    @Before
    public void setUp() throws Exception {
        TrackRecommendation inputTrackRecommendationModel = new TrackRecommendation();
        inputTrackRecommendationModel.setTrackId(2);
        inputTrackRecommendationModel.setUserId(4);
        inputTrackRecommendationModel.setLiked(true);

        inputTrackRecommendationJson = mapper.writeValueAsString(inputTrackRecommendationModel);

        outputTrackRecommendationModel = new TrackRecommendation();
        outputTrackRecommendationModel.setTrackId(2);
        outputTrackRecommendationModel.setUserId(4);
        outputTrackRecommendationModel.setLiked(true);
        outputTrackRecommendationModel.setTrackRecommendationId(1);

        outputTrackRecommendationJson = mapper.writeValueAsString(outputTrackRecommendationModel);

        doReturn(outputTrackRecommendationModel).when(trackRecommendationRepository).save(inputTrackRecommendationModel);
    }

    @Test
    public void shouldAddTrackRecommendation() throws Exception {
        mockMvc.perform(post("/track-recommendation")
                        .content(inputTrackRecommendationJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputTrackRecommendationJson));
    }

    @Test
    public void shouldUpdateTrackRecommendation() throws Exception {
        TrackRecommendation outputTrackRecommendationModel2 = new TrackRecommendation();
        outputTrackRecommendationModel2.setTrackId(4);
        outputTrackRecommendationModel2.setUserId(3);
        outputTrackRecommendationModel2.setLiked(false);
        outputTrackRecommendationModel2.setTrackRecommendationId(1);

        String outputTrackRecommendationJson2 = mapper.writeValueAsString(outputTrackRecommendationModel2);

        mockMvc.perform(put("/track-recommendation")
                        .content(outputTrackRecommendationJson2)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldDeleteTrackRecommendation() throws Exception {
        mockMvc.perform(delete("/track-recommendation/{id}", 1))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldGetTrackRecommendationById() throws Exception {
        doReturn(Optional.of(outputTrackRecommendationModel)).when(trackRecommendationRepository).findById(Long.valueOf(1));
        mockMvc.perform(get("/track-recommendation/{id}", 1))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputTrackRecommendationJson));
    }

    @Test
    public void shouldGetAllTrackRecommendations() throws Exception {
        String outputTrackRecommendations = mapper.writeValueAsString(Arrays.asList(outputTrackRecommendationModel));
        doReturn(Arrays.asList(outputTrackRecommendationModel)).when(trackRecommendationRepository).findAll();
        mockMvc.perform(get("/track-recommendation"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputTrackRecommendations));
    }
}