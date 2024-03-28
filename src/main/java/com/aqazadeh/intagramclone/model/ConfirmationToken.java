package com.aqazadeh.intagramclone.model;

import com.aqazadeh.intagramclone.model.enums.ConfirmationType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date: 15.03.2024
 * Time: 23:59
 */


@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ConfirmationToken extends BaseModel{

    private String token;

    @Enumerated(EnumType.STRING)
    private ConfirmationType type;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    private LocalDateTime expireTime;


}
