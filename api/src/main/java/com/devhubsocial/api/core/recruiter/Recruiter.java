package com.devhubsocial.api.core.recruiter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
@Getter
public class Recruiter {
    private final int developerId;
    private final int recruiterId;
    private final String recruiterName;
    private final String recruitingAgency;
    private final int companyRating;
    private final String serviceAddress;

    public Recruiter() {
        developerId = 0;
        recruiterId = 0;
        recruiterName = null;
        recruitingAgency = null;
        companyRating = 0;
        serviceAddress = null;
    }
}
