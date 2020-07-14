package com.devhubsocial.api.composite.developer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
@AllArgsConstructor
@Getter
public class DeveloperAggregate {
    private final int developerId;
    private final String firstName;
    private final String lastName;
    private final List<ContactSummary> contacts;
    private final List<RecruiterSummary> recruiters;
    private final ServiceAddresses serviceAddresses;

}
