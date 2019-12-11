package com.hanaset.luke.model.Response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PredictionResponse {

    private String bank;

    private Long year;

    private Long month;

    private Long amount;
}
