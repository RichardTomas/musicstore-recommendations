package com.company.musicstorerecommendations.repository;

import com.company.musicstorerecommendations.models.TrackRecommendation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrackRecommendationRepository extends JpaRepository<TrackRecommendation, Long> {
}
