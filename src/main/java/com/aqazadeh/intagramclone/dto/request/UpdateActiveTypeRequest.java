package com.aqazadeh.intagramclone.dto.request;

import com.aqazadeh.intagramclone.model.enums.PrivacyType;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date: 13.03.2024
 * Time: 01:54
 */
@Data
public class UpdateActiveTypeRequest {
    @NotBlank PrivacyType privacy;
}
