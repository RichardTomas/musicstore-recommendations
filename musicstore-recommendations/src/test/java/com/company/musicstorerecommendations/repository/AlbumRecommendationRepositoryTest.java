package com.company.musicstorerecommendations.repository;

import com.company.musicstorerecommendations.models.AlbumRecommendation;
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
public class AlbumRecommendationRepositoryTest {
    @Autowired
    AlbumRecommendationRepository albumRecommendationRepository;
    @Before
    public void setUp() throws Exception {
        albumRecommendationRepository.deleteAll();
    }
    @Test
    public void shouldAddGetDeleteAlbumRecommendation() {
        AlbumRecommendation albumRecommendation = new AlbumRecommendation();
        albumRecommendation.setAlbumId(3);
        albumRecommendation.setUserId(5);
        albumRecommendation.setLiked(true);

        albumRecommendation = albumRecommendationRepository.save(albumRecommendation);

        AlbumRecommendation albumRecommendation2 = albumRecommendationRepository.findById(albumRecommendation.getAlbumRecommendationId()).get();

        assertEquals(albumRecommendation, albumRecommendation2);

        albumRecommendationRepository.deleteById(albumRecommendation.getAlbumRecommendationId());

        Optional<AlbumRecommendation> albumRecommendation3 = albumRecommendationRepository.findById(albumRecommendation.getAlbumRecommendationId());

        assertEquals(false, albumRecommendation3.isPresent());
    }
    @Test
    public void shouldFindAllAlbumRecommendations() {
        AlbumRecommendation albumRecommendation = new AlbumRecommendation();
        albumRecommendation.setAlbumId(3);
        albumRecommendation.setUserId(6);
        albumRecommendation.setLiked(false);

        AlbumRecommendation albumRecommendation2 = new AlbumRecommendation();
        albumRecommendation2.setAlbumId(4);
        albumRecommendation2.setUserId(8);
        albumRecommendation2.setLiked(true);

        albumRecommendation = albumRecommendationRepository.save(albumRecommendation);
        albumRecommendation2 = albumRecommendationRepository.save(albumRecommendation2);
        List<AlbumRecommendation> allAlbumRecommendations = new ArrayList<>();
        allAlbumRecommendations.add(albumRecommendation);
        allAlbumRecommendations.add(albumRecommendation2);
    }
    @Test
    public void shouldUpdateAlbumRecommendation() {
        AlbumRecommendation albumRecommendation = new AlbumRecommendation();
        albumRecommendation.setAlbumId(3);
        albumRecommendation.setUserId(6);
        albumRecommendation.setLiked(false);

        albumRecommendation = albumRecommendationRepository.save(albumRecommendation);

        albumRecommendation.setAlbumId(8);
        albumRecommendation.setUserId(4);
        albumRecommendation.setLiked(false);

        albumRecommendationRepository.save(albumRecommendation);

        Optional<AlbumRecommendation> albumRecommendation1 = albumRecommendationRepository.findById(albumRecommendation.getAlbumRecommendationId());
        assertEquals(albumRecommendation1.get(), albumRecommendation);
    }
}