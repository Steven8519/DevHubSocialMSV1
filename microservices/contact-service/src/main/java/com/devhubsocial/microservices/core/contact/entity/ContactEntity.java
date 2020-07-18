package com.devhubsocial.microservices.core.contact.entity;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "contacts", indexes = { @Index(name = "contacts_unique_idx", unique = true, columnList = "developerId,contactId,recruiterId") })
public class ContactEntity {
    @Id @GeneratedValue
    private int id;

    @Version
    private int version;

    private int developerId;
    private int contactId;
    private int recruiterId;

    private String phoneNumber;
    private String email;

    public ContactEntity(int developerId, int contactId, int recruiterId, String phoneNumber, String email) {
        this.developerId = developerId;
        this.contactId = contactId;
        this.recruiterId = recruiterId;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }
}
