package com.aqazadeh.intagramclone.dto.response;

import com.aqazadeh.intagramclone.model.enums.MediaType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.Value;



@Data
@Builder
public class MediaResponse {
    Long id;
    String url;
    @JsonProperty("media_type")
    MediaType mediaType;
}