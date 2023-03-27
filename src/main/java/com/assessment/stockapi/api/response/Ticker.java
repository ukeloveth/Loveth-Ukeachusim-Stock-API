package com.assessment.stockapi.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "request_id",
        "results",
        "status"
})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Ticker {
    @JsonProperty("request_id")
    private String requestId;
    @JsonProperty("results")
    private Result results;
    @JsonProperty("status")
    private String status;
}
