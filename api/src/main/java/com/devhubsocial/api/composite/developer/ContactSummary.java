package com.devhubsocial.api.composite.developer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
@Getter
public class ContactSummary {
    private final int developerId;
    private final String phoneNumber;
    private final String email;
}
