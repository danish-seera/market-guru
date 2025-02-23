package com.stock_market.market_guru.client.respone;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Summary {
    private General general;
    @JsonProperty("company_data")
    private CompanyData companyData;
}