package com.company.musicstorerecommendations.repository;

import com.company.musicstorerecommendations.models.TrackRecommendation;
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
public class TrackRecommendationRepositoryTest {
    @Autowired
    TrackRecommendationRepository trackRecommendationRepository;
    @Before
    public void setUp() throws Exception {
        trackRecommendationRepository.deleteAll();
    }
    @Test
    public void shouldAddGetDeleteTrackRecommendation() {
        TrackRecommendation trackRecommendation = new TrackRecommendation();
        trackRecommendation.setTrackId(3);
        trackRecommendation.setUserId(5);
        trackRecommendation.setLiked(true);

        trackRecommendation = trackRecommendationRepository.save(trackRecommendation);

        TrackRecommendation trackRecommendation2 = trackRecommendationRepository.findById(trackRecommendation.getTrackRecommendationId()).get();

        assertEquals(trackRecommendation, trackRecommendation2);

        trackRecommendationRepository.deleteById(trackRecommendation.getTrackRecommendationId());

        Optional<TrackRecommendation> trackRecommendation3 = trackRecommendationRepository.findById(trackRecommendation.getTrackRecommendationId());

        assertEquals(false, trackRecommendation3.isPresent());
    }
    @Test
    public void shouldFindAllTrackRecommendations() {
        TrackRecommendation trackRecommendation = new TrackRecommendation();
        trackRecommendation.setTrackId(3);
        trackRecommendation.setUserId(6);
        trackRecommendation.setLiked(false);

        TrackRecommendation trackRecommendation2 = new TrackRecommendation();
        trackRecommendation2.setTrackId(4);
        trackRecommendation2.setUserId(8);
        trackRecommendation2.setLiked(true);

        trackRecommendation = trackRecommendationRepository.save(trackRecommendation);
        trackRecommendation2 = trackRecommendationRepository.save(trackRecommendation2);
        List<TrackRecommendation> allTrackRecommendations = new ArrayList<>();
        allTrackRecommendations.add(trackRecommendation);
        allTrackRecommendations.add(trackRecommendation2);
    }
    @Test
    public void shouldUpdateTrackRecommendation() {
        TrackRecommendation trackRecommendation = new TrackRecommendation();
        trackRecommendation.setTrackId(3);
        trackRecommendation.setUserId(6);
        trackRecommendation.setLiked(false);

        trackRecommendation = trackRecommendationRepository.save(trackRecommendation);

        trackRecommendation.setTrackId(8);
        trackRecommendation.setUserId(4);
        trackRecommendation.setLiked(false);

        trackRecommendationRepository.save(trackRecommendation);

        Optional<TrackRecommendation> trackRecommendation1 = trackRecommendationRepository.findById(trackRecommendation.getTrackRecommendationId());
        assertEquals(trackRecommendation1.get(), trackRecommendation);
    }
}