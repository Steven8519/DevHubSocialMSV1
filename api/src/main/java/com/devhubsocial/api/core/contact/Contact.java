package com.devhubsocial.api.core.contact;

public class Contact {
    private final int developerId;
    private final int recruiterId;
    private final int contactId;
    private final String phoneNumber;
    private final String email;
    private final String serviceAddress;

    public Contact(int developerId, int recruiterId, int contactId, String phoneNumber, String email, String serviceAddress) {
        this.developerId = developerId;
        this.recruiterId = recruiterId;
        this.contactId = contactId;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.serviceAddress = serviceAddress;
    }

    public Contact() {
        developerId = 0;
        recruiterId = 0;
        contactId = 0;
        phoneNumber = null;
        email = null;
        serviceAddress = null;
    }


    public int getDeveloperId() {
        return developerId;
    }

    public int getRecruiterId() {
        return recruiterId;
    }

    public int getContactId() {
        return contactId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getServiceAddress() {
        return serviceAddress;
    }
}
