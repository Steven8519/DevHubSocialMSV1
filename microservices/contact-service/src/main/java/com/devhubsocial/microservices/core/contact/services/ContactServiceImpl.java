package com.devhubsocial.microservices.core.contact.services;

import com.devhubsocial.api.core.contact.Contact;
import com.devhubsocial.api.core.contact.ContactService;
import com.devhubsocial.microservices.core.contact.entity.ContactEntity;
import com.devhubsocial.microservices.core.contact.entity.ContactRepository;
import com.devhubsocial.util.exceptions.InvalidInputException;
import com.devhubsocial.util.http.ServiceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ContactServiceImpl implements ContactService {

    private static final Logger LOG = LoggerFactory.getLogger(ContactServiceImpl.class);

    private final ContactRepository contactRepository;

    private final ContactMapper mapper;

    private final ServiceUtil serviceUtil;

    @Autowired
    public ContactServiceImpl(ContactRepository contactRepository, ContactMapper mapper, ServiceUtil serviceUtil) {
        this.contactRepository = contactRepository;
        this.mapper = mapper;
        this.serviceUtil = serviceUtil;
    }

    @Override
    public Contact createContact(Contact body) {
        try {
            ContactEntity contactEntity = mapper.apiToEntity(body);
            ContactEntity newEntity = contactRepository.save(contactEntity);

            LOG.debug("createContact: created a contact entity: {}/{}", body.getDeveloperId(), body.getContactId(), body.getRecruiterId());
            return mapper.entityToApi(newEntity);

        } catch (DataIntegrityViolationException dive) {
            throw new InvalidInputException("Duplicate key, Developer Id: " + body.getDeveloperId()
                    + ", Contact Id:" + body.getContactId() + ", Recruiter Id: " +body.getRecruiterId());
        }
    }

    @Override
    public List<Contact> getContacts(int developerId) {
        if (developerId < 1) throw new InvalidInputException("Invalid developerId: " + developerId);

        List<ContactEntity> entityList = contactRepository.findByDeveloperId(developerId);
        List<Contact> list = mapper.entityListToApiList(entityList);
        list.forEach(e -> e.setServiceAddress(serviceUtil.getServiceAddress()));

        LOG.debug("getContacts: response size: {}", list.size());

        return list;
    }

    @Override
    public void deleteContacts(int developerId) {
        LOG.debug("deleteContacts: tries to delete contacts for the developer with developerId: {}", developerId);
        contactRepository.deleteAll(contactRepository.findByDeveloperId(developerId));
    }
}
