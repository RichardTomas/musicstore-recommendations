package com.company.musicstorerecommendations.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "track_recommendation")
public class TrackRecommendation {
    @Id
    @Column(name = "track_recommendation_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long trackRecommendationId;
    @Column(name = "track_id")
    private long trackId;
    @Column(name = "user_id")
    private long userId;
    private boolean liked;

    public TrackRecommendation() {
    }

    public TrackRecommendation(long trackRecommendationId, long trackId, long userId, boolean liked) {
        this.trackRecommendationId = trackRecommendationId;
        this.trackId = trackId;
        this.userId = userId;
        this.liked = liked;
    }

    public long getTrackRecommendationId() {
        return trackRecommendationId;
    }

    public void setTrackRecommendationId(long trackRecommendationId) {
        this.trackRecommendationId = trackRecommendationId;
    }

    public long getTrackId() {
        return trackId;
    }

    public void setTrackId(long trackId) {
        this.trackId = trackId;
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
        TrackRecommendation that = (TrackRecommendation) o;
        return trackRecommendationId == that.trackRecommendationId && trackId == that.trackId && userId == that.userId && liked == that.liked;
    }

    @Override
    public int hashCode() {
        return Objects.hash(trackRecommendationId, trackId, userId, liked);
    }

    @Override
    public String toString() {
        return "TrackRecommendation{" +
                "trackRecommendationId=" + trackRecommendationId +
                ", trackId=" + trackId +
                ", userId=" + userId +
                ", liked=" + liked +
                '}';
    }
}
