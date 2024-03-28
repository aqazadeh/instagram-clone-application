package com.aqazadeh.intagramclone.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "post_like")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Like extends BaseModel {

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Override
    public String toString() {
        return "Media{" +
                "id=" + super.getId() +
                ", user=" + this.getUser() +
                ", status=" + super.getStatus() +
                ", createdAt=" + super.getCreatedAt() +
                ", updatedAt=" + super.getUpdatedAt() +
                '}';
    }
}