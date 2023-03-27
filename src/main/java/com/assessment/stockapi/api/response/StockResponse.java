package com.assessment.stockapi.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "results",
        "status",
        "request_id",
        "count",
        "next_url"
})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StockResponse {
    @JsonProperty("results")
    private List<Result> results;
    @JsonProperty("status")
    private String status;
    @JsonProperty("request_id")
    private String requestId;
    @JsonProperty("count")
    private Integer count;
    @JsonProperty("next_url")
    private String nextUrl;
}
