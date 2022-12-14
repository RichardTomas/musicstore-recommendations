package com.company.musicstorerecommendations.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "label_recommendation")
public class LabelRecommendation {
    @Id
    @Column(name = "label_recommendation_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long labelRecommendationId;
    @Column(name = "label_id")
    private long labelId;
    @Column(name = "user_id")
    private long userId;
    private boolean liked;

    public LabelRecommendation() {
    }

    public LabelRecommendation(long labelRecommendationId, long labelId, long userId, boolean liked) {
        this.labelRecommendationId = labelRecommendationId;
        this.labelId = labelId;
        this.userId = userId;
        this.liked = liked;
    }

    public long getLabelRecommendationId() {
        return labelRecommendationId;
    }

    public void setLabelRecommendationId(long labelRecommendationId) {
        this.labelRecommendationId = labelRecommendationId;
    }

    public long getLabelId() {
        return labelId;
    }

    public void setLabelId(long labelId) {
        this.labelId = labelId;
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
        LabelRecommendation that = (LabelRecommendation) o;
        return labelRecommendationId == that.labelRecommendationId && labelId == that.labelId && userId == that.userId && liked == that.liked;
    }

    @Override
    public int hashCode() {
        return Objects.hash(labelRecommendationId, labelId, userId, liked);
    }

    @Override
    public String toString() {
        return "LabelRecommendation{" +
                "labelRecommendationId=" + labelRecommendationId +
                ", labelId=" + labelId +
                ", userId=" + userId +
                ", liked=" + liked +
                '}';
    }
}
