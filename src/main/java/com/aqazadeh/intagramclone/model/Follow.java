package com.aqazadeh.intagramclone.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date: 10.03.2024
 * Time: 19:24
 */
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Follow  extends BaseModel{
    @ManyToOne
    @JoinColumn(name = "follower_id")
    private User follower;
    @ManyToOne
    @JoinColumn(name = "following_id")
    private User following;

    @Override
    public String toString() {
        return "Media{" +
                "id=" + super.getId() +
                ", status=" + super.getStatus() +
                ", createdAt=" + super.getCreatedAt() +
                ", updatedAt=" + super.getUpdatedAt() +
                '}';
    }
}
