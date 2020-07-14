package com.devhubsocial.api.composite.developer;

public class RecruiterSummary {

    private final int recruiterId;
    private final String recruiterName;
    private final String recruitingAgency;
    private final int companyRating;

    public RecruiterSummary(int recruiterId, String recruiterName, String recruitingAgency, int companyRating) {
        this.recruiterId = recruiterId;
        this.recruiterName = recruiterName;
        this.recruitingAgency = recruitingAgency;
        this.companyRating = companyRating;
    }


    public int getRecruiterId() {
        return recruiterId;
    }

    public String getRecruiterName() {
        return recruiterName;
    }

    public String getRecruitingAgency() {
        return recruitingAgency;
    }

    public int getCompanyRating() {
        return companyRating;
    }
}
