package com.company.musicstorerecommendations.repository;

import com.company.musicstorerecommendations.models.AlbumRecommendation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRecommendationRepository extends JpaRepository<AlbumRecommendation, Long> {
}
