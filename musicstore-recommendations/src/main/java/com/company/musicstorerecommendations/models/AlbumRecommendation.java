package com.company.musicstorerecommendations.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "album_recommendation")
public class AlbumRecommendation {
    @Id
    @Column(name = "album_recommendation_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long albumRecommendationId;
    @Column(name = "album_id")
    private long albumId;
    @Column(name = "user_id")
    private long userId;
    private boolean liked;

    public AlbumRecommendation() {
    }

    public AlbumRecommendation(long albumRecommendationId, long albumId, long userId, boolean liked) {
        this.albumRecommendationId = albumRecommendationId;
        this.albumId = albumId;
        this.userId = userId;
        this.liked = liked;
    }

    public long getAlbumRecommendationId() {
        return albumRecommendationId;
    }

    public void setAlbumRecommendationId(long albumRecommendationId) {
        this.albumRecommendationId = albumRecommendationId;
    }

    public long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(long albumId) {
        this.albumId = albumId;
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
        AlbumRecommendation that = (AlbumRecommendation) o;
        return albumRecommendationId == that.albumRecommendationId && albumId == that.albumId && userId == that.userId && liked == that.liked;
    }

    @Override
    public int hashCode() {
        return Objects.hash(albumRecommendationId, albumId, userId, liked);
    }

    @Override
    public String toString() {
        return "AlbumRecommendation{" +
                "albumRecommendationId=" + albumRecommendationId +
                ", albumId=" + albumId +
                ", userId=" + userId +
                ", liked=" + liked +
                '}';
    }
}
