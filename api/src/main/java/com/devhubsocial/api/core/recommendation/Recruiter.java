package com.devhubsocial.api.core.recommendation;

public class Recruiter {
    private final int developerId;
    private final int recruiterId;
    private final String recruiterName;
    private final String recruitingAgency;
    private final int companyRating;
    private final String serviceAddress;

    public Recruiter(int developerId, int recruiterId, String recruiterName, String recruitingAgency, int companyRating, String serviceAddress) {
        this.developerId = developerId;
        this.recruiterId = recruiterId;
        this.recruiterName = recruiterName;
        this.recruitingAgency = recruitingAgency;
        this.companyRating = companyRating;
        this.serviceAddress = serviceAddress;
    }



    public Recruiter() {
        developerId = 0;
        recruiterId = 0;
        recruiterName = null;
        recruitingAgency = null;
        companyRating = 0;
        serviceAddress = null;
    }

    public int getDeveloperId() {
        return developerId;
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

    public String getServiceAddress() {
        return serviceAddress;
    }
}
