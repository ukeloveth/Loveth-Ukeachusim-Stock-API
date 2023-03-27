package com.assessment.stockapi.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Stock extends BaseClass implements Serializable {
    private String ticker;
    private String name;
    private String market;
    private String locale;
    private String primaryExchange;
    private String type;
    private Boolean active;
    private String currencyName;
    private String cik;
    private String compositeFigi;
    private String shareClassFigi;
    private String lastUpdatedUtc;
    private int noOfLikes;
}
