package com.company.musicstorerecommendations.repository;

import com.company.musicstorerecommendations.models.ArtistRecommendation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRecommendationRepository extends JpaRepository<ArtistRecommendation, Long> {
}
