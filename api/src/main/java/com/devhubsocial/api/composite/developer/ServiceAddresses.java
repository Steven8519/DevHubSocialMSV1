package com.devhubsocial.api.composite.developer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
@Getter
public class ServiceAddresses {
    private final String composite;
    private final String developer;
    private final String contact;
    private final String recruiter;

    public ServiceAddresses() {
        composite = null;
        developer = null;
        contact = null;
        recruiter = null;
    }
}
