package com.devhubsocial.api.core.recruiter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
@Getter
public class Recruiter {
    private int developerId;
    private int recruiterId;
    private String recruiterName;
    private String recruitingAgency;
    private int companyRating;
    private String serviceAddress;

    public Recruiter() {
        developerId = 0;
        recruiterId = 0;
        recruiterName = null;
        recruitingAgency = null;
        companyRating = 0;
        serviceAddress = null;
    }
}
