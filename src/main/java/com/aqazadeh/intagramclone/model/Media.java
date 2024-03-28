package com.aqazadeh.intagramclone.model;

import com.aqazadeh.intagramclone.model.enums.MediaType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date: 10.03.2024
 * Time: 19:29
 */
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Media extends BaseModel {

    private MediaType type;

    private String url;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @Override
    public String toString() {
        return "Media{" +
                "id=" + super.getId() +
                ", type=" + this.getType() +
                ", url='" + this.getUrl() + '\'' +
                ", status=" + super.getStatus() +
                ", createdAt=" + super.getCreatedAt() +
                ", updatedAt=" + super.getUpdatedAt() +
                '}';
    }
}
