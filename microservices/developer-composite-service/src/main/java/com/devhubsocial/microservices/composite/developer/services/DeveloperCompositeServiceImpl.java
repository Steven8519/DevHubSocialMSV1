package com.devhubsocial.microservices.composite.developer.services;

import com.devhubsocial.api.composite.developer.*;
import com.devhubsocial.api.core.contact.Contact;
import com.devhubsocial.api.core.developer.Developer;
import com.devhubsocial.api.core.recruiter.Recruiter;
import com.devhubsocial.util.exceptions.NotFoundException;
import com.devhubsocial.util.http.ServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class DeveloperCompositeServiceImpl implements DeveloperCompositeService {
    private final ServiceUtil serviceUtil;
    private DeveloperCompositeIntegration integration;

    @Autowired
    public DeveloperCompositeServiceImpl(ServiceUtil serviceUtil, DeveloperCompositeIntegration developerCompositeIntegration) {
        this.serviceUtil = serviceUtil;
        this.integration = developerCompositeIntegration;
    }

    @Override
    public DeveloperAggregate getDeveloper(int developerId) {
        Developer developer = integration.getDeveloper(developerId);
        if (developer == null) throw new NotFoundException("No developer found for developerId: " + developerId);

        List<Recruiter> recuiters = integration.getRecruiters(developerId);

        List<Contact> contacts = integration.getContacts(developerId);

        return createDeveloperAggregate(developer, recuiters, contacts, serviceUtil.getServiceAddress());
    }

    private DeveloperAggregate createDeveloperAggregate(Developer developer, List<Recruiter> recruiters, List<Contact> contacts, String serviceAddress) {

        // 1. Setup Developer info
        int DeveloperId = developer.getDeveloperId();
        String firstName = developer.getFirstName();
        String lastName = developer.getLastName();

        // 2. Copy summary recommendation info, if available
        List<RecruiterSummary> recruiterSummaries = (recruiters == null) ? null :
                recruiters.stream()
                        .map(r -> new RecruiterSummary(r.getRecruiterId(), r.getRecruiterName(), r.getRecruitingAgency(), r.getCompanyRating()))
                        .collect(Collectors.toList());

        // 3. Copy summary review info, if available
        List<ContactSummary> contactSummaries = (contacts == null)  ? null :
                contacts.stream()
                        .map(c -> new ContactSummary(c.getContactId(), c.getPhoneNumber(), c.getEmail()))
                        .collect(Collectors.toList());

        // 4. Create info regarding the involved microservices addresses
        String developerAddress = developer.getServiceAddress();
        String contactAddress = (contacts != null && contacts.size() > 0) ? contacts.get(0).getServiceAddress() : "";
        String recruiterAddress = (recruiters != null && recruiters.size() > 0) ? recruiters.get(0).getServiceAddress() : "";
        ServiceAddresses serviceAddresses = new ServiceAddresses(serviceAddress, developerAddress, contactAddress, recruiterAddress);

        return new DeveloperAggregate(DeveloperId, firstName, lastName, recruiterSummaries, contactSummaries, serviceAddresses);
    }
}
