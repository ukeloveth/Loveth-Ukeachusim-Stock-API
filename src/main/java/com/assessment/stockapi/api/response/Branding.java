package com.assessment.stockapi.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "logo_url",
        "icon_url"
})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Branding {
    @JsonProperty("logo_url")
    private String logoUrl;
    @JsonProperty("icon_url")
    private String iconUrl;
}
