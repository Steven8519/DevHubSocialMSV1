package com.devhubsocial.api.composite.developer;

public class ContactSummary {
    private final int developerId;
    private final String phoneNumber;
    private final String email;

    public ContactSummary(int developerId, String phoneNumber, String email) {
        this.developerId = developerId;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public int getDeveloperId() {
        return developerId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }
}
