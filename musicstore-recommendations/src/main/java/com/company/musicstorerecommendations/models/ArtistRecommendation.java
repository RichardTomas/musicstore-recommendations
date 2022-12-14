package com.company.musicstorerecommendations.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "artist_recommendation")
public class ArtistRecommendation {
    @Id
    @Column(name = "artist_recommendation_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long artistRecommendationId;
    @Column(name = "artist_id")
    private long artistId;
    @Column(name = "user_id")
    private long userId;
    private boolean liked;

    public ArtistRecommendation() {
    }

    public ArtistRecommendation(long artistRecommendationId, long artistId, long userId, boolean liked) {
        this.artistRecommendationId = artistRecommendationId;
        this.artistId = artistId;
        this.userId = userId;
        this.liked = liked;
    }

    public long getArtistRecommendationId() {
        return artistRecommendationId;
    }

    public void setArtistRecommendationId(long artistRecommendationId) {
        this.artistRecommendationId = artistRecommendationId;
    }

    public long getArtistId() {
        return artistId;
    }

    public void setArtistId(long artistId) {
        this.artistId = artistId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArtistRecommendation that = (ArtistRecommendation) o;
        return artistRecommendationId == that.artistRecommendationId && artistId == that.artistId && userId == that.userId && liked == that.liked;
    }

    @Override
    public int hashCode() {
        return Objects.hash(artistRecommendationId, artistId, userId, liked);
    }

    @Override
    public String toString() {
        return "ArtistRecommendation{" +
                "artistRecommendationId=" + artistRecommendationId +
                ", artistId=" + artistId +
                ", userId=" + userId +
                ", liked=" + liked +
                '}';
    }
}
