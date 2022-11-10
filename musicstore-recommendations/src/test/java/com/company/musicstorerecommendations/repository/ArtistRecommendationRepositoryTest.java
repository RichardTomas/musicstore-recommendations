package com.company.musicstorerecommendations.repository;

import com.company.musicstorerecommendations.models.ArtistRecommendation;
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
public class ArtistRecommendationRepositoryTest {
    @Autowired
    ArtistRecommendationRepository artistRecommendationRepository;
    @Before
    public void setUp() throws Exception {
        artistRecommendationRepository.deleteAll();
    }
    @Test
    public void shouldAddGetDeleteArtistRecommendation() {
        ArtistRecommendation artistRecommendation = new ArtistRecommendation();
        artistRecommendation.setArtistId(3);
        artistRecommendation.setUserId(5);
        artistRecommendation.setLiked(true);

        artistRecommendation = artistRecommendationRepository.save(artistRecommendation);

        ArtistRecommendation artistRecommendation2 = artistRecommendationRepository.findById(artistRecommendation.getArtistRecommendationId()).get();

        assertEquals(artistRecommendation, artistRecommendation2);

        artistRecommendationRepository.deleteById(artistRecommendation.getArtistRecommendationId());

        Optional<ArtistRecommendation> artistRecommendation3 = artistRecommendationRepository.findById(artistRecommendation.getArtistRecommendationId());

        assertEquals(false, artistRecommendation3.isPresent());
    }
    @Test
    public void shouldFindAllArtistRecommendations() {
        ArtistRecommendation artistRecommendation = new ArtistRecommendation();
        artistRecommendation.setArtistId(3);
        artistRecommendation.setUserId(6);
        artistRecommendation.setLiked(false);

        ArtistRecommendation artistRecommendation2 = new ArtistRecommendation();
        artistRecommendation2.setArtistId(4);
        artistRecommendation2.setUserId(8);
        artistRecommendation2.setLiked(true);

        artistRecommendation = artistRecommendationRepository.save(artistRecommendation);
        artistRecommendation2 = artistRecommendationRepository.save(artistRecommendation2);
        List<ArtistRecommendation> allArtistRecommendations = new ArrayList<>();
        allArtistRecommendations.add(artistRecommendation);
        allArtistRecommendations.add(artistRecommendation2);
    }
    @Test
    public void shouldUpdateArtistRecommendation() {
        ArtistRecommendation artistRecommendation = new ArtistRecommendation();
        artistRecommendation.setArtistId(3);
        artistRecommendation.setUserId(6);
        artistRecommendation.setLiked(false);

        artistRecommendation = artistRecommendationRepository.save(artistRecommendation);

        artistRecommendation.setArtistId(8);
        artistRecommendation.setUserId(4);
        artistRecommendation.setLiked(false);

        artistRecommendationRepository.save(artistRecommendation);

        Optional<ArtistRecommendation> artistRecommendation1 = artistRecommendationRepository.findById(artistRecommendation.getArtistRecommendationId());
        assertEquals(artistRecommendation1.get(), artistRecommendation);
    }
}