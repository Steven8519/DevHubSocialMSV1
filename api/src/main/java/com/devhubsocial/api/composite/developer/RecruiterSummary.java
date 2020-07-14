package com.devhubsocial.api.composite.developer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
@Getter
public class RecruiterSummary {

    private final int recruiterId;
    private final String recruiterName;
    private final String recruitingAgency;
    private final int companyRating;
}
