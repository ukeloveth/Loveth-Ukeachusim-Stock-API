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
        "ticker",
        "name",
        "market",
        "locale",
        "primary_exchange",
        "type",
        "active",
        "currency_name",
        "cik",
        "composite_figi",
        "share_class_figi",
        "last_updated_utc",
        "market_cap",
        "phone_number",
        "address",
        "description",
        "sic_code",
        "sic_description",
        "ticker_root",
        "ticker_suffix",
        "homepage_url",
        "total_employees",
        "list_date",
        "branding",
        "share_class_shares_outstanding",
        "weighted_shares_outstanding",
        "round_lot"
})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    @JsonProperty("ticker")
    private String ticker;
    @JsonProperty("name")
    private String name;
    @JsonProperty("market")
    private String market;
    @JsonProperty("locale")
    private String locale;
    @JsonProperty("primary_exchange")
    private String primaryExchange;
    @JsonProperty("type")
    private String type;
    @JsonProperty("active")
    private Boolean active;
    @JsonProperty("currency_name")
    private String currencyName;
    @JsonProperty("cik")
    private String cik;
    @JsonProperty("composite_figi")
    private String compositeFigi;
    @JsonProperty("share_class_figi")
    private String shareClassFigi;
    @JsonProperty("last_updated_utc")
    private String lastUpdatedUtc;
    @JsonProperty("market_cap")
    private Double marketCap;
    @JsonProperty("phone_number")
    private String phoneNumber;
    @JsonProperty("address")
    private Address address;
    @JsonProperty("description")
    private String description;
    @JsonProperty("sic_code")
    private String sicCode;
    @JsonProperty("sic_description")
    private String sicDescription;
    @JsonProperty("ticker_root")
    private String tickerRoot;
    @JsonProperty("ticker_suffix")
    private String tickerSuffix;
    @JsonProperty("homepage_url")
    private String homepageUrl;
    @JsonProperty("total_employees")
    private Integer totalEmployees;
    @JsonProperty("list_date")
    private String listDate;
    @JsonProperty("branding")
    private Branding branding;
    @JsonProperty("share_class_shares_outstanding")
    private Integer shareClassSharesOutstanding;
    @JsonProperty("weighted_shares_outstanding")
    private Integer weightedSharesOutstanding;
    @JsonProperty("round_lot")
    private Integer roundLot;
}
