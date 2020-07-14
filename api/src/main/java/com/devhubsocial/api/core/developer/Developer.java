package com.devhubsocial.api.core.developer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
@Getter
@Setter
public class Developer {

    private int developerId;
    private String firstName;
    private String lastName;
    private String typeOfDeveloper;
    private boolean employed;
    private String companyName;
    private String serviceAddress;

    public Developer() {
        developerId = 0;
        firstName = null;
        lastName = null;
        typeOfDeveloper = null;
        employed = false;
        companyName = null;
    }
}
