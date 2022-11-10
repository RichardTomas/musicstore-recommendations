package com.company.musicstorerecommendations.repository;

import com.company.musicstorerecommendations.models.LabelRecommendation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class LabelRecommendationRepositoryTest {
    @Autowired
    LabelRecommendationRepository labelRecommendationRepository;
    @Before
    public void setUp() throws Exception {
        labelRecommendationRepository.deleteAll();
    }
    @Test
    public void shouldAddGetDeleteLabelRecommendation() {
        LabelRecommendation labelRecommendation = new LabelRecommendation();
        labelRecommendation.setLabelId(3);
        labelRecommendation.setUserId(5);
        labelRecommendation.setLiked(true);

        labelRecommendation = labelRecommendationRepository.save(labelRecommendation);

        LabelRecommendation labelRecommendation2 = labelRecommendationRepository.findById(labelRecommendation.getLabelRecommendationId()).get();

        assertEquals(labelRecommendation, labelRecommendation2);

        labelRecommendationRepository.deleteById(labelRecommendation.getLabelRecommendationId());

        Optional<LabelRecommendation> labelRecommendation3 = labelRecommendationRepository.findById(labelRecommendation.getLabelRecommendationId());

        assertEquals(false, labelRecommendation3.isPresent());
    }
    @Test
    public void shouldFindAllLabelRecommendations() {
        LabelRecommendation labelRecommendation = new LabelRecommendation();
        labelRecommendation.setLabelId(3);
        labelRecommendation.setUserId(6);
        labelRecommendation.setLiked(false);

        LabelRecommendation labelRecommendation2 = new LabelRecommendation();
        labelRecommendation2.setLabelId(4);
        labelRecommendation2.setUserId(8);
        labelRecommendation2.setLiked(true);

        labelRecommendation = labelRecommendationRepository.save(labelRecommendation);
        labelRecommendation2 = labelRecommendationRepository.save(labelRecommendation2);
        List<LabelRecommendation> allLabelRecommendations = new ArrayList<>();
        allLabelRecommendations.add(labelRecommendation);
        allLabelRecommendations.add(labelRecommendation2);
    }
    @Test
    public void shouldUpdateLabelRecommendation() {
        LabelRecommendation labelRecommendation = new LabelRecommendation();
        labelRecommendation.setLabelId(3);
        labelRecommendation.setUserId(6);
        labelRecommendation.setLiked(false);

        labelRecommendation = labelRecommendationRepository.save(labelRecommendation);

        labelRecommendation.setLabelId(8);
        labelRecommendation.setUserId(4);
        labelRecommendation.setLiked(false);

        labelRecommendationRepository.save(labelRecommendation);

        Optional<LabelRecommendation> labelRecommendation1 = labelRecommendationRepository.findById(labelRecommendation.getLabelRecommendationId());
        assertEquals(labelRecommendation1.get(), labelRecommendation);
    }
}