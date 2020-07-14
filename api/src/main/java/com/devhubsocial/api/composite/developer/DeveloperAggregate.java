package com.devhubsocial.api.composite.developer;

import java.util.List;

public class DeveloperAggregate {
    private final int developerId;
    private final String firstName;
    private final String lastName;
    private final List<ContactSummary> contacts;
    private final List<RecruiterSummary> recruiters;
    private final ServiceAddresses serviceAddresses;

    public DeveloperAggregate(int developerId, String firstName, String lastName, List<ContactSummary> contacts, List<RecruiterSummary> recruiters, ServiceAddresses serviceAddresses) {
        this.developerId = developerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.contacts = contacts;
        this.recruiters = recruiters;
        this.serviceAddresses = serviceAddresses;
    }


    public int getDeveloperId() {
        return developerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public List<ContactSummary> getContacts() {
        return contacts;
    }

    public List<RecruiterSummary> getRecruiters() {
        return recruiters;
    }

    public ServiceAddresses getServiceAddresses() {
        return serviceAddresses;
    }
}
