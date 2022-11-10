package com.company.musicstorerecommendations.controller;

import com.company.musicstorerecommendations.models.LabelRecommendation;
import com.company.musicstorerecommendations.repository.LabelRecommendationRepository;
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
@WebMvcTest(LabelRecommendationController.class)
public class LabelRecommendationControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private LabelRecommendationRepository labelRecommendationRepository;

    private ObjectMapper mapper = new ObjectMapper();

    private String inputLabelRecommendationJson;
    private String outputLabelRecommendationJson;
    private LabelRecommendation outputLabelRecommendationModel;

    @Before
    public void setUp() throws Exception {
        LabelRecommendation inputLabelRecommendationModel = new LabelRecommendation();
        inputLabelRecommendationModel.setLabelId(2);
        inputLabelRecommendationModel.setUserId(4);
        inputLabelRecommendationModel.setLiked(true);

        inputLabelRecommendationJson = mapper.writeValueAsString(inputLabelRecommendationModel);

        outputLabelRecommendationModel = new LabelRecommendation();
        outputLabelRecommendationModel.setLabelId(2);
        outputLabelRecommendationModel.setUserId(4);
        outputLabelRecommendationModel.setLiked(true);
        outputLabelRecommendationModel.setLabelRecommendationId(1);

        outputLabelRecommendationJson = mapper.writeValueAsString(outputLabelRecommendationModel);

        doReturn(outputLabelRecommendationModel).when(labelRecommendationRepository).save(inputLabelRecommendationModel);
    }

    @Test
    public void shouldAddLabelRecommendation() throws Exception {
        mockMvc.perform(post("/label-recommendation")
                        .content(inputLabelRecommendationJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputLabelRecommendationJson));
    }

    @Test
    public void shouldUpdateLabelRecommendation() throws Exception {
        LabelRecommendation outputLabelRecommendationModel2 = new LabelRecommendation();
        outputLabelRecommendationModel2.setLabelId(4);
        outputLabelRecommendationModel2.setUserId(3);
        outputLabelRecommendationModel2.setLiked(false);
        outputLabelRecommendationModel2.setLabelRecommendationId(1);

        String outputLabelRecommendationJson2 = mapper.writeValueAsString(outputLabelRecommendationModel2);

        mockMvc.perform(put("/label-recommendation")
                        .content(outputLabelRecommendationJson2)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldDeleteLabelRecommendation() throws Exception {
        mockMvc.perform(delete("/label-recommendation/{id}", 1))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldGetLabelRecommendationById() throws Exception {
        doReturn(Optional.of(outputLabelRecommendationModel)).when(labelRecommendationRepository).findById(Long.valueOf(1));
        mockMvc.perform(get("/label-recommendation/{id}", 1))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputLabelRecommendationJson));
    }

    @Test
    public void shouldGetAllLabelRecommendations() throws Exception {
        String outputLabelRecommendations = mapper.writeValueAsString(Arrays.asList(outputLabelRecommendationModel));
        doReturn(Arrays.asList(outputLabelRecommendationModel)).when(labelRecommendationRepository).findAll();
        mockMvc.perform(get("/label-recommendation"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputLabelRecommendations));
    }
}