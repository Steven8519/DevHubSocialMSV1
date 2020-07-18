package com.devhubsocial.microservices.core.developer.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Getter
@Setter
@Document(collection="developers")
public class DeveloperEntity {

    @Id
    private String id;

    @Version
    private Integer version;

    @Indexed(unique = true)
    private int developerId;

    private String firstName;
    private String lastName;
    private String typeOfDeveloper;
    private boolean employed;
    private String companyName;

    public DeveloperEntity(int developerId, String firstName, String lastName, String typeOfDeveloper, boolean employed, String companyName) {
        this.developerId = developerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.typeOfDeveloper = typeOfDeveloper;
        this.employed = employed;
        this.companyName = companyName;
    }
}
