package com.devhubsocial.microservices.core.recruiter.persistence;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Getter
@Setter
@Document(collection="recruiters")
@CompoundIndex(name = "dev-rec-id", unique = true, def = "{'developerId': 1, 'recruiterId' : 1}")
public class RecruiterEntity {
    @Id
    private String id;

    @Version
    private Integer version;

    private int developerId;
    private int recruiterId;
    private String recruiterName;
    private String recruitingAgency;
    private int companyRating;

    public RecruiterEntity(int developerId, int recruiterId, String recruiterName, String recruitingAgency, int companyRating) {
        this.developerId = developerId;
        this.recruiterId = recruiterId;
        this.recruiterName = recruiterName;
        this.recruitingAgency = recruitingAgency;
        this.companyRating = companyRating;
    }




}
