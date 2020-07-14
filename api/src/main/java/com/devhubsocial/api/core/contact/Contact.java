package com.devhubsocial.api.core.contact;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
@Getter
public class Contact {
    private final int developerId;
    private final int recruiterId;
    private final int contactId;
    private final String phoneNumber;
    private final String email;
    private final String serviceAddress;

    public Contact() {
        developerId = 0;
        recruiterId = 0;
        contactId = 0;
        phoneNumber = null;
        email = null;
        serviceAddress = null;
    }
}
